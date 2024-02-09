package imagemagick.core.enums

//
// /!\ do not modify order: it's directly linked to the underlying C library enum/order /!\
//

/**
 * Specified the photo orientation of the image.
 */
public enum class OrientationType {
    /** Undefined. */
    UNDEFINED,

    /** Top left. */
    TOP_LEFT,

    /** Top right. */
    TOP_RIGHT,

    /** Bottom right. */
    BOTTOM_RIGHT,

    /** Bottom left. */
    BOTTOM_LEFT,

    /** Left top. */
    LEFT_TOP,

    /** Right top. */
    RIGHT_TOP,

    /** Right bottom. */
    RIGHT_BOTTOM,

    /** Left bottom. */
    LEFT_BOTTOM,
}
