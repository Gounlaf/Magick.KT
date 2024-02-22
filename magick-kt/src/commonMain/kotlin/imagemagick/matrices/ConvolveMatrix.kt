package imagemagick.matrices

import imagemagick.exceptions.throwIfTrue
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarray
import org.jetbrains.kotlinx.multik.api.zeros
import org.jetbrains.kotlinx.multik.ndarray.data.D2
import imagemagick.core.matrices.ConvolveMatrix as IConvolveMatrix

/**
 * Encapsulates a convolution kernel.
 */
public class ConvolveMatrix : DoubleMatrix, IConvolveMatrix {
    /**
     * Initializes a new instance of the [ConvolveMatrix] class.
     *
     * @param order The order (odd number).
     */
    public constructor(order: UInt) : super(order, mk.zeros<Double>(order.toInt(), order.toInt())) {
        throwIfTrue("order", order < 1u, "Invalid order specified, value has to be at least 1.")
        throwIfTrue("order", order.rem(2u) == 0u, "Order must be an odd number.")
    }

    /**
     * Initializes a new instance of the [ConvolveMatrix] class.
     *
     * @param order The order (odd number).
     * @param values The values to initialize the matrix with.
     */
    public constructor(order: UInt, vararg values: Double) : super(
        order,
        mk.ndarray<Double, D2>(values.asList(), intArrayOf(order.toInt(), order.toInt()))
    ) {
        throwIfTrue("order", order < 1u, "Invalid order specified, value has to be at least 1.")
        throwIfTrue("order", order.rem(2u) == 0u, "Order must be an odd number.")
    }
}
