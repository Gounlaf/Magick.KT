package imagemagick.core.enums

//
// /!\ do not modify order: it's directly linked to the underlying C library enum/order /!\
//

/**
 * Specifies the types of rendering intent.
 */
public enum class RenderingIntent {
    /** Undefined. */
    UNDEFINED,

    /** Saturation. */
    SATURATION,

    /** Perceptual. */
    PERCEPTUAL,

    /** Absolute. */
    ABSOLUTE,

    /** Relative. */
    RELATIVE,
}
