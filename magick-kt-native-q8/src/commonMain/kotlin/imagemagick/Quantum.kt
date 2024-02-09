package imagemagick

import imagemagick.core.QuantumQuantum
import imagemagick.magicknative.NativeQuantum
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.math.max
import kotlin.math.min

public enum class BitDepth {
    Q8,
    Q16,
    Q16HDRI,
}

public val Q: BitDepth = BitDepth.Q8

@ExperimentalForeignApi
public inline fun UShort.quantum(): QuantumType = Quantum.convert(this)

@ExperimentalForeignApi
public inline fun UInt.quantum(): QuantumType = Quantum.convert(this)

@ExperimentalForeignApi
public inline fun Int.quantum(): QuantumType = Quantum.convert(this)

@ExperimentalForeignApi
public inline fun Long.quantum(): QuantumType = Quantum.convert(this)

@ExperimentalForeignApi
public inline fun Float.quantum(): QuantumType = Quantum.convert(this)

@ExperimentalForeignApi
public inline fun Double.quantum(): QuantumType = Quantum.convert(this)

@ExperimentalForeignApi
public inline fun QuantumType.quantum(): QuantumType = this

@ExperimentalForeignApi
public object Quantum : QuantumQuantum<QuantumType> {
    override val depth: UShort = NativeQuantum.depth.toUShort()
    override val max: QuantumType = NativeQuantum.max

    public val maxd: Double = NativeQuantum.max.toDouble()

    public inline fun convert(value: UByte): QuantumType = value

    public inline fun convert(value: UInt): QuantumType = convert(value.toUByte())

    public fun convert(value: Float): QuantumType {
        if (value < 0.0) {
            return 0u
        }

        if (value > maxd) {
            return max
        }

        return value.toUInt().toUByte()
    }

    public fun convert(value: Double): QuantumType {
        if (value < 0.0) {
            return 0u
        }

        if (value > maxd) {
            return max
        }

        return value.toUInt().toUByte()
    }

    public fun convert(value: Int): QuantumType {
        if (value < 0) {
            return 0u
        }

        if (value > maxd) {
            return max
        }

        return value.toUByte()
    }

    public fun convert(value: Long): QuantumType {
        if (value < 0) {
            return 0u
        }

        if (value > maxd) {
            return max
        }

        return value.toUByte()
    }

    public inline fun convert(value: UShort): QuantumType {
        // https://github.com/dlemstra/Magick.NET/discussions/1497
        return ((value + 128u) / 257u).toUByte()
    }

    public inline fun scaleToUbyte(value: QuantumType): UByte = NativeQuantum.scaleToUByte(value)

    public inline fun scaleToQuantum(value: Double): QuantumType = convert(min(max(0.0, value * maxd), maxd))

    public inline fun scaleToDouble(value: QuantumType): Double = (1.0 / maxd) * value.toDouble()
}
