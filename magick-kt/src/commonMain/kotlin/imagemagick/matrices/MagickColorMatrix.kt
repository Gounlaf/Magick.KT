package imagemagick.matrices

import imagemagick.core.matrices.ConvolveMatrix
import imagemagick.exceptions.throwIfTrue
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarray
import org.jetbrains.kotlinx.multik.api.zeros
import org.jetbrains.kotlinx.multik.ndarray.data.D2

/**
 * Encapsulates a color matrix in the order of 1 to 6 (1x1 through 6x6).
 */
public class MagickColorMatrix : DoubleMatrix, ConvolveMatrix {
    /**
     * Initializes a new instance of the [MagickColorMatrix] class.
     *
     * @param order The order (odd number).
     */
    public constructor(order: UInt) : super(order, mk.zeros<Double>(order.toInt(), order.toInt())) {
        throwIfTrue("order", order < 1u, "Invalid order specified, value has to be at least 1.")
        throwIfTrue("order", order > 6u, "Invalid order specified, range 1-6.")
    }

    /**
     * Initializes a new instance of the [MagickColorMatrix] class.
     *
     * @param order The order (odd number).
     * @param values The values to initialize the matrix with.
     */
    public constructor(order: UInt, vararg values: Double) : super(
        order,
        mk.ndarray<Double, D2>(values.asList(), intArrayOf(order.toInt(), order.toInt())),
    ) {
        throwIfTrue("order", order < 1u, "Invalid order specified, value has to be at least 1.")
        throwIfTrue("order", order > 6u, "Invalid order specified, range 1-6.")
    }
}
