package imagemagick.colors

import imagemagick.QuantumType
import imagemagick.core.types.Percentage
import kotlinx.cinterop.ExperimentalForeignApi
import imagemagick.core.colors.MagickColorQuantum as IMagickColor

/**
 * Base class for colors.
 *
 * @constructor Initializes a new instance of the [ColorBase] class.
 */
@ExperimentalForeignApi
public abstract class ColorBase(
    /** Actual color of this instance. */
    protected open val color: IMagickColor<QuantumType>,
) : Comparable<ColorBase?> {
    override fun compareTo(other: ColorBase?): Int {
        if (other == null) {
            return 1
        }

        updateColor()
        other.updateColor()

        return color.compareTo(other.color)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is ColorBase) {
            return false
        }

        if (super.equals(other)) {
            return true
        }

        updateColor()
        other.updateColor()

        return color == other.color
    }

    /**
     * Determines whether the specified color is fuzzy equal to the current color.
     *
     * @param other The color to compare this color with.
     * @param fuzz The fuzz factor.
     *
     * @return True when the specified color is fuzzy equal to the current instance.
     */
    public fun fuzzyEquals(
        other: ColorBase?,
        fuzz: Percentage,
    ): Boolean {
        if (other == null) {
            return false
        }

        if (super.equals(other)) {
            return true
        }

        updateColor()
        other.updateColor()

        return color.fuzzyEquals(other.color, fuzz)
    }

    override fun hashCode(): Int {
        updateColor()

        return color.hashCode()
    }

    /**
     * Converts the value of this instance to an equivalent [IMagickColor].
     *
     * @return A [IMagickColor] instance.
     */
    @ExperimentalStdlibApi
    public open fun toMagickColor(): IMagickColor<QuantumType> {
        updateColor()

        return MagickColor(color)
    }

    /**
     * Converts the value of this instance to a hexadecimal string.
     *
     * @return The [String].
     */
    @ExperimentalStdlibApi
    override fun toString(): String = toMagickColor().toString()

    /**
     * Updates the color value from an inherited class.
     */
    protected open fun updateColor() {
    }
}
