package imagemagick.colors

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import imagemagick.QuantumImpl
import imagemagick.QuantumType
import kotlinx.cinterop.ExperimentalForeignApi
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
    public constructor(y: Double, u: Double, v: Double) : super(
        MagickColor(
            QuantumImpl.convert(0),
            QuantumImpl.convert(0),
            QuantumImpl.convert(0),
        ),
    ) {
        this.y = y
        this.u = u
        this.v = v
    }

    private constructor(color: IMagickColor<QuantumType>) : super(color) {
        val max = QuantumImpl.max.toDouble()
        val maxRatio = 1.0 / max
        val r = color.r.toDouble()
        val g = color.g.toDouble()
        val b = color.b.toDouble()

        y = maxRatio * ((0.298839 * r) + (0.586811 * g) + (0.11435 * b))
        u = (maxRatio * ((-0.147 * r) - (0.289 * g) + (0.436 * b))) + 0.5
        v = (maxRatio * ((0.615 * r) - (0.515 * g) - (0.1 * b))) + 0.5
    }

    override fun updateColor() {
        val yBig = y.toBigDecimal()
        val uBig = (u - 0.5).toBigDecimal()
        val vBig = (v - 0.5).toBigDecimal()

        color.r = QuantumImpl.scaleToQuantum((yBig - (ruFactor * uBig) + (rvFactor * vBig)).doubleValue())
        color.g = QuantumImpl.scaleToQuantum((yBig - (guFactor * uBig) - (gvFactor * vBig)).doubleValue())
        color.b = QuantumImpl.scaleToQuantum((yBig + (buFactor * uBig) - (bvFactor * vBig)).doubleValue())
    }

    public companion object {
        private val ruFactor: BigDecimal = BigDecimal.parseString("3.945707070708279e-05")
        private val rvFactor: BigDecimal = BigDecimal.parseString("1.1398279671717170825")

        private val guFactor: BigDecimal = BigDecimal.parseString("0.3946101641414141437")
        private val gvFactor: BigDecimal = BigDecimal.parseString("0.5805003156565656797")

        private val buFactor: BigDecimal = BigDecimal.parseString("2.0319996843434342537")
        private val bvFactor: BigDecimal = BigDecimal.parseString("4.813762626262513e-04")

        /**
         * Converts the specified [IMagickColor] to an instance of this type.
         *
         * @param color The color to use.
         * @return A [ColorYUV] instance.
         */
        public fun fromMagickColor(color: IMagickColor<QuantumType>): ColorYUV = ColorYUV(color)
    }
}
