package imagemagick.core.enums

//
// /!\ do not modify order: it's directly linked to the underlying C library enum/order /!\
//

/**
 * Specifies the interlace types.
 */
public enum class Interlace {
    /** Undefined */
    UNDEFINED,

    /** NoInterlace */
    NOINTERLACE,

    /** Line */
    LINE,

    /** Plane */
    PLANE,

    /** Partition */
    PARTITION,

    /** Gif */
    GIF,

    /** Jpeg */
    JPEG,

    /** Png */
    PNG,
}
