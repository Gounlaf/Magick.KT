package imagemagick.matrices

import imagemagick.exceptions.throwIfOutOfRange
import imagemagick.exceptions.throwIfTrue
import imagemagick.magicknative.matrices.NativeDoubleMatrix
import kotlinx.cinterop.ExperimentalForeignApi
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set
import org.jetbrains.kotlinx.multik.ndarray.operations.toDoubleArray
import imagemagick.core.matrices.DoubleMatrix as IDoubleMatrix

/**
 * Encapsulates a matrix of doubles.
 */
public abstract class DoubleMatrix protected constructor(
    override val order: UInt,
    protected val values: D2Array<Double>,
) : IDoubleMatrix {
    override fun get(
        x: UInt,
        y: UInt,
    ): Double = values[y.toInt(), x.toInt()]

    override fun set(
        x: UInt,
        y: UInt,
        value: Double,
    ) {
        values[y.toInt(), x.toInt()] = value
    }

    override fun setColumn(
        x: UInt,
        vararg values: Double,
    ) {
        throwIfOutOfRange("x", x, order)
        throwIfTrue("values", values.size != order.toInt(), "Invalid length")
        for (y in 0u until order) {
            set(x, y, values[y.toInt()])
        }
    }

    override fun setRow(
        y: UInt,
        vararg values: Double,
    ) {
        throwIfOutOfRange("y", y, order)
        throwIfTrue("values", values.size != order.toInt(), "Invalid length")
        for (x in 0u until order) {
            set(x, y, values[x.toInt()])
        }
    }

    override fun toArray(): DoubleArray = values.toDoubleArray()

    @ExperimentalStdlibApi
    @ExperimentalForeignApi
    public companion object {
        internal fun nativeInstance(matrix: IDoubleMatrix): NativeDoubleMatrix =
            NativeDoubleMatrix.create(matrix.order.toULong(), matrix.toArray())
    }
}
