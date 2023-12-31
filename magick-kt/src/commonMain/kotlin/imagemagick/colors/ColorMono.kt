package imagemagick.colors

import imagemagick.Quantum
import imagemagick.QuantumType
import imagemagick.quantum
import kotlinx.cinterop.ExperimentalForeignApi
import imagemagick.core.colors.MagickColorQuantum as IMagickColor

/**
 * Class that represents a monochrome color.
 */
@ExperimentalForeignApi
@ExperimentalStdlibApi
public class ColorMono : ColorBase {
    /** Gets or sets a value indicating whether the color is black or white. */
    public var isBlack: Boolean = false

    private constructor(isBlack: Boolean) : this(if (isBlack) MagickColors.Black else MagickColors.White)

    private constructor(color: IMagickColor<QuantumType>) : super(color) {
        isBlack =
            when (color) {
                MagickColors.Black -> true
                MagickColors.White -> false
                else -> error("Invalid color specified.")
            }
    }

    override fun updateColor() {
        val c: QuantumType =
            if (isBlack) {
                0.0.quantum()
            } else {
                Quantum.max
            }

        color.r = c
        color.g = c
        color.b = c
    }

    public companion object {
        /**
         * Gets a new instance of the [ColorMono] class that is black.
         */
        public val black: ColorMono get() = ColorMono(true)

        /**
         * Gets a new instance of the [ColorMono] class that is black.
         */
        public val white: ColorMono get() = ColorMono(false)

        public fun fromMagickColor(color: IMagickColor<QuantumType>): ColorMono = ColorMono(color)
    }
}
