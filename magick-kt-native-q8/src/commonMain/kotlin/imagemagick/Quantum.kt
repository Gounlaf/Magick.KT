package imagemagick

import imagemagick.core.QuantumQuantum
import imagemagick.native.NativeQuantum
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.math.max
import kotlin.math.min

enum class BitDepth {
    Q8,
    Q16,
    Q16HDRI,
}

val Q = BitDepth.Q8

@ExperimentalForeignApi
inline fun UShort.quantum(): QuantumType = Quantum.convert(this)

@ExperimentalForeignApi
inline fun UInt.quantum(): QuantumType = Quantum.convert(this)

@ExperimentalForeignApi
inline fun Int.quantum(): QuantumType = Quantum.convert(this)

@ExperimentalForeignApi
inline fun Long.quantum(): QuantumType = Quantum.convert(this)

@ExperimentalForeignApi
inline fun Float.quantum(): QuantumType = Quantum.convert(this)

@ExperimentalForeignApi
inline fun Double.quantum(): QuantumType = Quantum.convert(this)

@ExperimentalForeignApi
inline fun QuantumType.quantum(): QuantumType = this

@ExperimentalForeignApi
object Quantum : QuantumQuantum<QuantumType> {
    override val depth: UShort = NativeQuantum.depth.toUShort()
    override val max: QuantumType = NativeQuantum.max

    private val maxd: Double = NativeQuantum.max.toDouble()

    inline fun convert(value: UByte): QuantumType = value

    inline fun convert(value: UInt): QuantumType = convert(value.toUByte())

    fun convert(value: Float): QuantumType {
        if (value < 0.0) {
            return 0u
        }

        if (value > maxd) {
            return max
        }

        return value.toUInt().toUByte()
    }

    fun convert(value: Double): QuantumType {
        if (value < 0.0) {
            return 0u
        }

        if (value > maxd) {
            return max
        }

        return value.toUInt().toUByte()
    }

    fun convert(value: Int): QuantumType {
        if (value < 0) {
            return 0u
        }

        if (value > maxd) {
            return max
        }

        return value.toUByte()
    }

    fun convert(value: Long): QuantumType {
        if (value < 0) {
            return 0u
        }

        if (value > maxd) {
            return max
        }

        return value.toUByte()
    }

    fun convert(value: UShort): QuantumType {
        // https://github.com/dlemstra/Magick.NET/discussions/1497
        return ((value + 128u) / 257u).toUByte()
    }

    inline fun scaleToUbyte(value: QuantumType): UByte = NativeQuantum.scaleToUByte(value)

    fun scaleToQuantum(value: Double): QuantumType = convert(min(max(0.0, value * maxd), maxd))

    fun scaleToDouble(value: QuantumType): Double = (1.0 / max.toDouble()) * value.toDouble()
}
