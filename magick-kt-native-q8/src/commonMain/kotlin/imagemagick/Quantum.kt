package imagemagick

import imagemagick.core.QuantumQuantum
import imagemagick.native.NativeQuantum
import kotlinx.cinterop.ExperimentalForeignApi

enum class BitDepth {
    Q8,
    Q16,
    Q16HDRI,
}

val Q = BitDepth.Q8


@ExperimentalForeignApi
object QuantumImpl : QuantumQuantum<QuantumType> {
    override val depth: UShort = NativeQuantum.depth.toUShort()
    override val max: QuantumType = NativeQuantum.max

    fun convert(value: UByte): QuantumType {
        return value
    }

    fun convert(value: Double): QuantumType {
        if (value < 0) {
            return 0u
        }

        if (value > max.toDouble()) {
            return max
        }

        return value.toUInt().toUByte()
    }

    fun convert(value: Int): QuantumType {
        if (value < 0) {
            return 0u
        }

        if (value > max.toDouble()) {
            return max
        }

        return value.toUByte()
    }

    fun convert(value: UShort): QuantumType {
        // https://github.com/dlemstra/Magick.NET/discussions/1497
        return (value.plus(128u)).div(257u).toUByte()
    }

    fun scaleToUbyte(value: QuantumType): UByte = NativeQuantum.scaleToUByte(value)
}
