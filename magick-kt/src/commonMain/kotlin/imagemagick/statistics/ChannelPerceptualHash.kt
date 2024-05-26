package imagemagick.statistics

import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.PixelChannel
import imagemagick.exceptions.throwIfOutOfRange
import imagemagick.helpers.empty
import imagemagick.magicknative.statistics.NativeChannelPerceptualHash
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.math.abs
import imagemagick.core.statistics.ChannelPerceptualHash as IChannelPerceptualHash

/**
 * Contains the perceptual hash of one image channel.
 */
@ExperimentalForeignApi
public class ChannelPerceptualHash : IChannelPerceptualHash {
    override val channel: PixelChannel

    private val huPhashes: List<HuPhashList>
    private var hash: String = String.empty

    internal constructor(
        pixelChannel: PixelChannel,
        colorSpaces: Array<out ColorSpace>,
        nativeChannelPerceptualHash: NativeChannelPerceptualHash,
    ) {
        channel = pixelChannel
        huPhashes =
            colorSpaces.mapIndexed { colorSpaceIndex, colorSpace ->
                val huPhashList = HuPhashList(colorSpace)
                val index = colorSpaceIndex.toUInt()
                for (i in 0u..<NUMBER_OF_VALUES.toUInt()) {
                    nativeChannelPerceptualHash.getHuPhash(index, i)
                }

                huPhashList
            }
    }

    @ExperimentalStdlibApi
    internal constructor(pixelChannel: PixelChannel, colorSpaces: Array<out ColorSpace>, hash: String) {
        channel = pixelChannel
        huPhashes = parseHash(colorSpaces, hash)
    }

    override fun huPhash(
        colorSpace: ColorSpace,
        index: Int,
    ): Double {
        throwIfOutOfRange("index", index, 0..<NUMBER_OF_VALUES)

        val huPhashList =
            getHuPhashListByColorSpace(colorSpace)
                ?: throw IllegalArgumentException("Invalid colorspace specified: $colorSpace")

        return huPhashList[index]
    }

    private inline fun getHuPhashListByColorSpace(colorSpace: ColorSpace): HuPhashList? =
        huPhashes.firstOrNull { it.colorSpace == colorSpace }

    override fun sumSquaredDistance(other: IChannelPerceptualHash): Double {
        // TODO Update me after issue resolved https://github.com/dlemstra/Magick.NET/issues/1629
        val otherChannelPerceptualHash = other as ChannelPerceptualHash
        var ssd = 0.0

        huPhashes.forEach { huPhashList ->
            val otherHuPhashList = otherChannelPerceptualHash.getHuPhashListByColorSpace(huPhashList.colorSpace)

            for (i in 0..<NUMBER_OF_VALUES) {
                val a = huPhashList[i]
                val b = otherHuPhashList?.get(i) ?: 0.0

                ssd += (a - b) * (a - b)
            }
        }

        return ssd
    }

    @ExperimentalStdlibApi
    override fun toString(): String = hash.takeUnless { it.isEmpty() } ?: computeAndSetHash()

    @ExperimentalStdlibApi
    private fun parseHash(
        colorSpaces: Array<out ColorSpace>,
        hash: String,
    ): List<HuPhashList> {
        this.hash = hash

        var offset = 0
        return colorSpaces.map { colorSpace ->
            val huPhashList = HuPhashList(colorSpace)

            for (i in 0..<NUMBER_OF_VALUES) {
                val hex = hash.substring(offset, offset + 5).hexToInt(HexFormat.Default)
                offset += 5

                var value: Double = hex / powerOfTen(hex shr 17)

                if ((hex and (1 shl 16)) != 0) {
                    value = -value
                }

                huPhashList[i] = value
            }

            huPhashList
        }
    }

    @ExperimentalStdlibApi
    private fun computeAndSetHash(): String {
        val computedHash = StringBuilder()

        huPhashes.forEach {
            for (i in 0..<NUMBER_OF_VALUES) {
                var value = it[i]

                var hex = 0
                while (hex < 7 && abs(value * 10) < 65536) {
                    value *= 10
                    hex++
                }

                hex = hex shl 1
                if (value < 0.0) {
                    hex = hex or 1
                }

                hex = (hex shl 16) + (if (value < 0.0) -(value - 0.5) else (value + 0.5)).toInt()

                computedHash.append(hex.toHexString(HexFormat.Default))
            }
        }

        hash = computedHash.toString()

        return hash
    }

    private class HuPhashList(
        val colorSpace: ColorSpace,
        private val values: DoubleArray,
    ) {
        constructor(colorSpace: ColorSpace) : this(
            colorSpace,
            doubleArrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
        )

        operator fun get(index: Int) = values[index]

        operator fun set(
            index: Int,
            value: Double,
        ) {
            values[index] = value
        }
    }

    private companion object {
        private const val NUMBER_OF_VALUES = 7

        private inline fun powerOfTen(power: Int): Double =
            when (power) {
                2 -> 100.0
                3 -> 1000.0
                4 -> 10000.0
                5 -> 100000.0
                6 -> 1000000.0
                else -> 10.0
            }
    }
}
