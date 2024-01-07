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
 * Class that represents a HSL color.
 */
@ExperimentalStdlibApi
@ExperimentalForeignApi
public class ColorHSL : ColorBase {
    /** Gets or sets the hue component value of this color. */
    public var hue: Double

    /** Gets or sets the saturation component value of this color. */
    public var saturation: Double

    /** Gets or sets the lightness component value of this color. */
    public var lightness: Double

    /**
     * Initializes a new instance of the [ColorHSL] class.
     *
     * @param hue Hue component value of this color.
     * @param saturation Saturation component value of this color.
     * @param lightness Value component value of this color.
     */
    public constructor(hue: Double, saturation: Double, lightness: Double) : super(MagickColor(0u, 0u, 0u)) {
        this.hue = hue
        this.saturation = saturation
        this.lightness = lightness
    }

    private constructor(color: IMagickColor<QuantumType>) : super(color) {
        // /!\ C# Double.Epsilon = 5E-324
        // /!\ KT Double.MIN_VALUE = 4.9E-324

        val red = color.r.toDouble()
        val blue = color.b.toDouble()
        val green = color.g.toDouble()

        val quantumScale = 1.0 / Quantum.max.toDouble()
        val max = max(red, max(green, blue)) * quantumScale
        val min = min(red, min(green, blue)) * quantumScale
        val c = max - min

        lightness = (max + min) / 2.0

        if (c <= 0.0) {
            hue = 0.0
            saturation = 0.0
            return
        }

        val scaledGreen = quantumScale * green
        val scaledBlue = quantumScale * blue
        val scaledREd = (quantumScale * red)

        hue =
            when {
                abs(max - (quantumScale * red)) < Double.MIN_VALUE -> {
                    var h = (scaledGreen - scaledBlue) / c
                    if (scaledGreen < scaledBlue) {
                        h += 6.0
                    }

                    h
                }
                abs(max - scaledGreen) < Double.MIN_VALUE -> 2.0 + ((scaledBlue - scaledREd) / c)
                else -> 4.0 + ((scaledREd - scaledGreen) / c)
            }

        hue *= 60.0 / 360.0

        saturation =
            if (lightness <= 0.5) {
                c / (2.0 * lightness)
            } else {
                c / (2.0 - (2.0 * lightness))
            }
    }

    override fun updateColor() {
        var h = hue * 360.0

        val c =
            if (lightness <= 0.5) {
                2.0 * lightness * saturation
            } else {
                (2.0 - (2.0 * lightness)) * saturation
            }

        val min = lightness - (0.5 * c)

        h -= 360.0 * floor(h / 360.0)
        h /= 60.0

        val x = c * (1.0 - abs(h - (2.0 * floor(h / 2.0)) - 1.0))

        val mcScaled = Quantum.scaleToQuantum(min + c)
        val mxScaled = Quantum.scaleToQuantum(min + x)
        val mScaled = Quantum.scaleToQuantum(min + x)

        when (floor(h).toInt()) {
            0 -> {
                color.r = mcScaled
                color.g = mxScaled
                color.b = mScaled
            }

            1 -> {
                color.r = mxScaled
                color.g = mcScaled
                color.b = mScaled
            }

            2 -> {
                color.r = mScaled
                color.g = mcScaled
                color.b = mxScaled
            }

            3 -> {
                color.r = mScaled
                color.g = mxScaled
                color.b = mcScaled
            }

            4 -> {
                color.r = mxScaled
                color.g = mScaled
                color.b = mcScaled
            }

            5 -> {
                color.r = mcScaled
                color.g = mScaled
                color.b = mxScaled
            }

            else -> {
                color.r = mcScaled
                color.g = mxScaled
                color.b = mScaled
            }
        }
    }

    public companion object {
        /**
         * Converts the specified [IMagickColor] to an instance of this type.
         *
         * @param color The color to use.
         * @return A [ColorHSL] instance.
         */
        public fun fromMagickColor(color: IMagickColor<QuantumType>): ColorHSL = ColorHSL(color)
    }
}
