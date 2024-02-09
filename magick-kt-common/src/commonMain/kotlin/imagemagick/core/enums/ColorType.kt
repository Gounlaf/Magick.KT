package imagemagick.core.enums

//
// /!\ do not modify order: it's directly linked to the underlying C library enum/order /!\
//

/**
 * Specifies the color type of the image.
 */
public enum class ColorType {
    /** Undefined. */
    UNDEFINED,

    /** Bilevel. */
    BILEVEL,

    /** Grayscale. */
    GRAYSCALE,

    /** GrayscaleAlpha. */
    GRAYSCALE_ALPHA,

    /** Palette. */
    PALETTE,

    /** PaletteAlpha. */
    PALETTE_ALPHA,

    /** TrueColor. */
    TRUE_COLOR,

    /** TrueColorAlpha. */
    TRUE_COLOR_ALPHA,

    /** ColorSeparation. */
    COLOR_SEPARATION,

    /** ColorSeparationAlpha. */
    COLOR_SEPARATION_ALPHA,

    /** Optimize. */
    OPTIMIZE,

    /** PaletteBilevelAlpha. */
    PALETTE_BILEVEL_ALPHA,
}
