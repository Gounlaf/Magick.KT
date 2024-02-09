package imagemagick.core.enums

//
// /!\ do not modify order: it's directly linked to the underlying C library enum/order /!\
//

/**
 * Specifies the pixel interpolate methods.
 */
public enum class PixelInterpolateMethod {
    /** Undefined. */
    UNDEFINED,

    /** Average. */
    AVERAGE,

    /** Average9. */
    AVERAGE9,

    /** Average16. */
    AVERAGE16,

    /** Background. */
    BACKGROUND,

    /** Bilinear. */
    BILINEAR,

    /** Blend. */
    BLEND,

    /** Catrom. */
    CATROM,

    /** Integer. */
    INTEGER,

    /** Mesh. */
    MESH,

    /** Nearest. */
    NEAREST,

    /** Spline. */
    SPLINE,
}
