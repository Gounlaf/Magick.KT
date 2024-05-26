package imagemagick.statistics

import imagemagick.MagickImage.Companion.nativeInstance
import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.PixelChannel
import imagemagick.core.statistics.PerceptualHash
import imagemagick.exceptions.throwIfEmpty
import imagemagick.exceptions.throwIfFalse
import imagemagick.exceptions.throwIfOutOfRange
import imagemagick.magicknative.statistics.NativePerceptualHashList
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.contracts.ExperimentalContracts
import kotlin.experimental.ExperimentalNativeApi
import imagemagick.core.MagickImage as IMagickImage
import imagemagick.core.statistics.PerceptualHash as IPerceptualHash

//
// NOTES: The implementation differs from Magick.NET
// ChannelPerceptualHash are not stored in a list, but in 3 variables directly
//

/**
 * Contains the perceptual hash of one or more image channels.
 */
@ExperimentalForeignApi
public class PerceptualHash : IPerceptualHash {
    //    private val channels: Map<PixelChannel, ChannelPerceptualHash>
    private val red: ChannelPerceptualHash
    private val green: ChannelPerceptualHash
    private val blue: ChannelPerceptualHash

    /**
     * Initializes a new instance of the [PerceptualHash] class.
     *
     * @param hash The hash.
     */
    @ExperimentalStdlibApi
    public constructor(hash: String) : this(hash, *defaultColorSpaces)

    /**
     * Initializes a new instance of the [PerceptualHash] class.
     *
     * @param hash The hash.
     * @param colorSpaces The colorspaces that were used to create this hash.
     */
    @ExperimentalStdlibApi
    public constructor(hash: String, vararg colorSpaces: ColorSpace) {
        throwIfEmpty("hash", hash)
        validateColorSpaces(colorSpaces)

        val length = 35 * colorSpaces.size
        throwIfFalse("hash", hash.length == 3 * length, "Invalid hash size.")

        val parts = hash.chunked(length)

//        channels =
//            mapOf(
//                PixelChannel.RED to ChannelPerceptualHash(PixelChannel.RED, colorSpaces, parts[0]),
//                PixelChannel.GREEN to ChannelPerceptualHash(PixelChannel.GREEN, colorSpaces, parts[1]),
//                PixelChannel.BLUE to ChannelPerceptualHash(PixelChannel.BLUE, colorSpaces, parts[2]),
//            )

        red = ChannelPerceptualHash(PixelChannel.RED, colorSpaces, parts[0])
        green = ChannelPerceptualHash(PixelChannel.GREEN, colorSpaces, parts[1])
        blue = ChannelPerceptualHash(PixelChannel.BLUE, colorSpaces, parts[2])
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @ExperimentalStdlibApi
    internal constructor(
        image: IMagickImage,
        colorSpaces: Array<out ColorSpace>,
        nativePerceptualHash: NativePerceptualHashList,
    ) {
        val nativeImage = image.nativeInstance()

//        channels = listOfNotNull(
//            nativePerceptualHash.createChannelPerceptualHash(nativeImage, PixelChannel.RED)?.let {
//                ChannelPerceptualHash(PixelChannel.RED, colorSpaces, it)
//            },
//            nativePerceptualHash.createChannelPerceptualHash(nativeImage, PixelChannel.GREEN)?.let {
//                ChannelPerceptualHash(PixelChannel.GREEN, colorSpaces, it)
//            },
//            nativePerceptualHash.createChannelPerceptualHash(nativeImage, PixelChannel.BLUE)?.let {
//                ChannelPerceptualHash(PixelChannel.BLUE, colorSpaces, it)
//            },
//        ).associateBy { it.channel }

        red = nativePerceptualHash.createChannelPerceptualHash(nativeImage, PixelChannel.RED)?.let {
            ChannelPerceptualHash(PixelChannel.RED, colorSpaces, it)
        } ?: error("Failed to create channel perceptual hash for PixelChannel.RED")
        green = nativePerceptualHash.createChannelPerceptualHash(nativeImage, PixelChannel.GREEN)?.let {
            ChannelPerceptualHash(PixelChannel.GREEN, colorSpaces, it)
        } ?: error("Failed to create channel perceptual hash for PixelChannel.GREEN")
        blue = nativePerceptualHash.createChannelPerceptualHash(nativeImage, PixelChannel.BLUE)?.let {
            ChannelPerceptualHash(PixelChannel.BLUE, colorSpaces, it)
        } ?: error("Failed to create channel perceptual hash for PixelChannel.BLUE")
    }

    //    override fun getChannel(channel: PixelChannel): ChannelPerceptualHash? = channels[channel]
    override fun getChannel(channel: PixelChannel): ChannelPerceptualHash? =
        when (channel) {
            PixelChannel.RED -> red
            PixelChannel.GREEN -> green
            PixelChannel.BLUE -> blue
            else -> null
        }

    override fun sumSquaredDistance(other: PerceptualHash): Double {
        val otherRed = other.getChannel(PixelChannel.RED)
        val otherGreen = other.getChannel(PixelChannel.GREEN)
        val otherBlue = other.getChannel(PixelChannel.BLUE)

        if (otherRed == null || otherGreen == null || otherBlue == null) {
            throw UnsupportedOperationException("The other perceptual hash should contain a red, green and blue channel.")
        }

        return red.sumSquaredDistance(otherRed) +
            green.sumSquaredDistance(otherGreen) +
            blue.sumSquaredDistance(otherBlue)
    }

    @ExperimentalStdlibApi
    override fun toString(): String = red.toString() + green.toString() + blue.toString()

    internal companion object {
        val defaultColorSpaces: Array<ColorSpace> = arrayOf(ColorSpace.SRGB, ColorSpace.HCLP)

        fun validateColorSpaces(colorSpaces: Array<out ColorSpace>) {
            throwIfOutOfRange("colorSpaces", 1, 6, colorSpaces.size) {
                "Invalid number of colorspaces, the minimum is 1 and the maximum is 6."
            }

            throwIfFalse(
                "colorSpaces",
                colorSpaces.distinct().size == colorSpaces.size,
                "Specifying the same colorspace more than once is not allowed.",
            )
        }
    }
}
