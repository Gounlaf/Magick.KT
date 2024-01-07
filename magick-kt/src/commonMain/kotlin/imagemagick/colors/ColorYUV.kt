package imagemagick.colors

import imagemagick.Quantum
import imagemagick.QuantumType
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import imagemagick.core.colors.MagickColorQuantum as IMagickColor

/**
 * Class that represents a YUV color.
 */
@ExperimentalForeignApi
@ExperimentalStdlibApi
public class ColorYUV : ColorBase {
    /** Gets or sets the U component value of this color. (value beteeen -0.5 and 0.5). */
    public var u: Double

    /** Gets or sets the V component value of this color. (value beteeen -0.5 and 0.5). */
    public var v: Double

    /** Gets or sets the Y component value of this color. (value beteeen 0.5 and 1.0). */
    public var y: Double

    /**
     * Initializes a new instance of the [ColorYUV] class.
     *
     * @param y Y component value of this color.
     * @param u U component value of this color.
     * @param v V component value of this color.
     */
    public constructor(y: Double, u: Double, v: Double) : super(MagickColor(0.convert(), 0.convert(), 0.convert())) {
        this.y = y
        this.u = u
        this.v = v
    }

    private constructor(color: IMagickColor<QuantumType>) : super(color) {
        val max = Quantum.max.toDouble()
        val maxRatio = 1.0 / max
        val r = color.r.toDouble()
        val g = color.g.toDouble()
        val b = color.b.toDouble()

        y = maxRatio * ((0.298839 * r) + (0.586811 * g) + (0.11435 * b))
        u = (maxRatio * ((-0.147 * r) - (0.289 * g) + (0.436 * b))) + 0.5
        v = (maxRatio * ((0.615 * r) - (0.515 * g) - (0.1 * b))) + 0.5
    }

    override fun updateColor() {
        color.r =
            Quantum.scaleToQuantum((y - (3.945707070708279e-05 * (u - 0.5)) + (1.1398279671717170825 * (v - 0.5))))
        color.g =
            Quantum.scaleToQuantum((y - (0.3946101641414141437 * (u - 0.5)) - (0.5805003156565656797 * (v - 0.5))))
        color.b =
            Quantum.scaleToQuantum((y + (2.0319996843434342537 * (u - 0.5)) - (4.813762626262513e-04 * (v - 0.5))))
    }

    public companion object {
        /**
         * Converts the specified [IMagickColor] to an instance of this type.
         *
         * @param color The color to use.
         * @return A [ColorYUV] instance.
         */
        public fun fromMagickColor(color: IMagickColor<QuantumType>): ColorYUV = ColorYUV(color)
    }
}
