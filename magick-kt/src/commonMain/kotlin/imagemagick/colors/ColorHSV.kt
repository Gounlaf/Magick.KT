package imagemagick.colors

import imagemagick.Quantum
import imagemagick.QuantumType
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import imagemagick.core.colors.MagickColorQuantum as IMagickColor

/**
 * Class that represents a HSV color.
 */
@ExperimentalForeignApi
@ExperimentalStdlibApi
public class ColorHSV : ColorBase {
    /** Gets or sets the hue component value of this color. */
    public var hue: Double

    /** Gets or sets the saturation component value of this color. */
    public var saturation: Double

    /** Gets or sets the value component value of this color. */
    public var value: Double

    /**
     * Initializes a new instance of the [ColorHSV] class.
     *
     * @param hue Hue component value of this color.
     * @param saturation Saturation component value of this color.
     * @param value Value component value of this color.
     */
    public constructor(hue: Double, saturation: Double, value: Double) : super(MagickColor(0u, 0u, 0u)) {
        this.hue = hue
        this.saturation = saturation
        this.value = value
    }

    private constructor(color: IMagickColor<QuantumType>) : super(color) {
        // /!\ C# Double.Epsilon = 5E-324
        // /!\ KT Double.MIN_VALUE = 4.9E-324

        val red = color.r.toDouble()
        val blue = color.b.toDouble()
        val green = color.g.toDouble()

        hue = 0.0
        saturation = 0.0
        value = 0.0

        val min = min(min(red, green), blue)
        val max = max(max(red, green), blue)

        if (abs(max) < Double.MIN_VALUE) {
            return
        }

        val delta = max - min
        saturation = delta / max
        value = (1.0 / Quantum.max.toDouble()) * max

        if (abs(delta) < Double.MIN_VALUE) {
            return
        }

        hue =
            when {
                abs(red - max) < Double.MIN_VALUE -> (green - blue) / delta
                abs(green - max) < Double.MIN_VALUE -> 2.0 + ((blue - red) / delta)
                else -> 4.0 + ((red - green) / delta)
            }

        hue /= 6.0

        if (hue < 0.0) {
            hue += 1.0
        }
    }

    /**
     * Performs a hue shift with the specified degrees.
     *
     * @param degrees The degrees.
     */
    public fun hueShift(degrees: Double) {
        hue += degrees / 360.0

        while (hue >= 1.0) {
            hue -= 1.0
        }

        while (hue < 0.0) {
            hue += 1.0
        }
    }

    override fun updateColor() {
        val valueScaled = Quantum.scaleToQuantum(value)

        if (abs(saturation) < Double.MIN_VALUE) {
            color.r = valueScaled
            color.g = valueScaled
            color.b = valueScaled
            return
        }

        val h = 6.0 * (hue - floor(hue))
        val f = h - floor(h)

        val p = Quantum.scaleToQuantum(value * (1.0 - saturation))
        val q = Quantum.scaleToQuantum(value * (1.0 - (saturation * f)))
        val t = Quantum.scaleToQuantum(value * (1.0 - (saturation * (1.0 - f))))

        when (h.toInt()) {
            0 -> {
                color.r = valueScaled
                color.g = t
                color.b = p
            }

            1 -> {
                color.r = q
                color.g = valueScaled
                color.b = p
            }

            2 -> {
                color.r = p
                color.g = valueScaled
                color.b = t
            }

            3 -> {
                color.r = p
                color.g = q
                color.b = valueScaled
            }

            4 -> {
                color.r = t
                color.g = p
                color.b = valueScaled
            }

            5 -> {
                color.r = valueScaled
                color.g = p
                color.b = q
            }

            else -> {
                color.r = valueScaled
                color.g = t
                color.b = p
            }
        }
    }

    public companion object {
        /**
         * Converts the specified [IMagickColor] to an instance of this type.
         *
         * @param color The color to use.
         * @return A [ColorYUV] instance.
         */
        public fun fromMagickColor(color: IMagickColor<QuantumType>): ColorHSV = ColorHSV(color)
    }
}
