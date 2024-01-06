package imagemagick.colors

import imagemagick.QuantumType
import kotlinx.cinterop.ExperimentalForeignApi
import imagemagick.core.colors.MagickColorQuantum as IMagickColor

/**
 * Class that represents a RGB color.
 *
 * @constructor Initializes a new instance of the [ColorRGB] class.
 */
@ExperimentalForeignApi
@ExperimentalStdlibApi
public class ColorRGB(override val color: IMagickColor<QuantumType>) : ColorBase(color) {
    /** Gets or sets the blue component value of this color. */
    public var b: QuantumType
        get() = color.b
        set(value) {
            color.b = value
        }

    /** Gets or sets the green component value of this color. */
    public var g: QuantumType
        get() = color.g
        set(value) {
            color.g = value
        }

    /** Gets or sets the red component value of this color. */
    public var r: QuantumType
        get() = color.r
        set(value) {
            color.r = value
        }

    public constructor(red: QuantumType, green: QuantumType, blue: QuantumType) : this(MagickColor(red, green, blue))

    /**
     * Returns the complementary color for this color.
     *
     * @return A [ColorRGB] instance.
     */
    public fun complementaryColor(): ColorRGB {
        val hsv = ColorHSV.fromMagickColor(toMagickColor())

        hsv.hueShift(180.0)

        return ColorRGB(hsv.toMagickColor())
    }

    public companion object {
        public fun fromMagickColor(color: IMagickColor<QuantumType>): ColorRGB = ColorRGB(color)
    }
}
