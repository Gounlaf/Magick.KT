package imagemagick.core.enums

//
// /!\ do not modify order: it's directly linked to the underlying C library enum/order /!\
//

/**
 * Specifies the virtual pixel methods.
 */
public enum class VirtualPixelMethod {
    /** Undefined. */
    UNDEFINED,

    /** Background. */
    BACKGROUND,

    /** Dither. */
    DITHER,

    /** Edge. */
    EDGE,

    /** Mirror. */
    MIRROR,

    /** Random. */
    RANDOM,

    /** Tile. */
    TILE,

    /** Transparent. */
    TRANSPARENT,

    /** Mask. */
    MASK,

    /** Black. */
    BLACK,

    /** Gray. */
    GRAY,

    /** White. */
    WHITE,

    /** Horizontal tile. */
    HORIZONTAL_TILE,

    /** Vertical tile. */
    VERTICAL_TILE,

    /** Horizontal tile edge. */
    HORIZONTAL_TILE_EDGE,

    /** Vertical tile edge. */
    VERTICAL_TILE_EDGE,

    /** Checker tile. */
    CHECKER_TILE,
}
