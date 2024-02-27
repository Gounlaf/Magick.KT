package imagemagick.core.enums

//
// /!\ do not modify order: it's directly linked to the underlying C library enum/order /!\
//

/**
 * Specifies the pixel intensity methods.
 */
public enum class PixelIntensityMethod {
    /** Undefined. */
    UNDEFINED,

    /** Average. */
    AVERAGE,

    /** Brightness. */
    BRIGHTNESS,

    /** Lightness. */
    LIGHTNESS,

    /** MS. */
    M_S,

    /** Rec601Luma. */
    REC601_LUMA,

    /** Rec601Luminance. */
    REC601_LUMINANCE,

    /** Rec709Luma. */
    REC709_LUMA,

    /** Rec709Luminance. */
    REC709_LUMINANCE,

    /** RMS. */
    R_M_S,
}
