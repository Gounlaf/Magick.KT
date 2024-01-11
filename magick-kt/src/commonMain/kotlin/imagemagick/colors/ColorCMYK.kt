package imagemagick.colors

import imagemagick.Quantum
import imagemagick.QuantumType
import imagemagick.core.types.Percentage
import imagemagick.exceptions.Throw
import imagemagick.helpers.PercentageHelper
import kotlinx.cinterop.ExperimentalForeignApi
import imagemagick.core.colors.MagickColorQuantum as IMagickColor

/**
 * Class that represents a CMYK color.
 */
@ExperimentalStdlibApi
@ExperimentalForeignApi
public class ColorCMYK : ColorBase {
    /** Gets or sets the alpha component value of this color. */
    public var a: QuantumType
        get() = color.a
        set(value) {
            color.a = value
        }

    /** Gets or sets the cyan component value of this color. */
    public var c: QuantumType
        get() = color.r
        set(value) {
            color.r = value
        }

    /** Gets or sets the key (black) component value of this color. */
    public var k: QuantumType
        get() = color.k
        set(value) {
            color.k = value
        }

    /** Gets or sets the magenta component value of this color. */
    public var m: QuantumType
        get() = color.g
        set(value) {
            color.g = value
        }

    /** Gets or sets the yellow component value of this color. */
    public var y: QuantumType
        get() = color.b
        set(value) {
            color.b = value
        }

    /**
     * Initializes a new instance of the [ColorCMYK] class.
     *
     * @param cyan Cyan component value of this color.
     * @param magenta Magenta component value of this color.
     * @param yellow Yellow component value of this color.
     * @param key Key (black) component value of this color.
     */
    public constructor(cyan: Percentage, magenta: Percentage, yellow: Percentage, key: Percentage) : super(
        MagickColor(
            PercentageHelper.toQuantumType(cyan),
            PercentageHelper.toQuantumType(magenta),
            PercentageHelper.toQuantumType(yellow),
            PercentageHelper.toQuantumType(key),
            Quantum.max,
        ),
    )

    /**
     * Initializes a new instance of the [ColorCMYK] class.
     *
     * @param cyan Cyan component value of this color.
     * @param magenta Magenta component value of this color.
     * @param yellow Yellow component value of this color.
     * @param key Key (black) component value of this color.
     * @param alpha Alpha component value of this color.
     */
    public constructor(
        cyan: Percentage,
        magenta: Percentage,
        yellow: Percentage,
        key: Percentage,
        alpha: Percentage,
    ) : super(
        MagickColor(
            PercentageHelper.toQuantumType(cyan),
            PercentageHelper.toQuantumType(magenta),
            PercentageHelper.toQuantumType(yellow),
            PercentageHelper.toQuantumType(key),
            PercentageHelper.toQuantumType(alpha),
        ),
    )

    /**
     * Initializes a new instance of the [ColorCMYK] class.
     *
     * @param cyan Cyan component value of this color.
     * @param magenta Magenta component value of this color.
     * @param yellow Yellow component value of this color.
     * @param key Key (black) component value of this color.
     */
    public constructor(cyan: QuantumType, magenta: QuantumType, yellow: QuantumType, key: QuantumType) : super(
        MagickColor(cyan, magenta, yellow, key, Quantum.max),
    )

    /**
     * Initializes a new instance of the [ColorCMYK] class.
     *
     * @param cyan Cyan component value of this color.
     * @param magenta Magenta component value of this color.
     * @param yellow Yellow component value of this color.
     * @param key Key (black) component value of this color.
     * @param alpha Alpha component value of this color.
     */
    public constructor(
        cyan: QuantumType,
        magenta: QuantumType,
        yellow: QuantumType,
        key: QuantumType,
        alpha: QuantumType,
    ) : super(MagickColor(cyan, magenta, yellow, key, alpha))

    public constructor(color: String) : super(createColor(color))

    private constructor(color: IMagickColor<QuantumType>) : super(color)

    public companion object {
        /**
         * Converts the specified [IMagickColor] to an instance of this type.
         *
         * @param color The color to use.
         * @return A [ColorCMYK] instance.
         */
        public fun fromMagickColor(color: IMagickColor<QuantumType>): ColorCMYK = ColorCMYK(color)

        private fun createColor(color: String): IMagickColor<QuantumType> {
            Throw.ifEmpty(color)

            if (!color.startsWith('#')) {
                throw IllegalArgumentException("Invalid color specified\"")
            }

            val (success, colors) = HexColor.tryParse(color)
            if (!success || colors.size != 4) {
                throw IllegalArgumentException("Invalid hex value.")
            }

            return MagickColor(
                cyan = colors[0],
                magenta = colors[1],
                yellow = colors[2],
                black = colors[3],
                alpha = Quantum.max,
            )
        }
    }
}
