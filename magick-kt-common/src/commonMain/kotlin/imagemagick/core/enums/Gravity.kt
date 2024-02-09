package imagemagick.core.enums

//
// /!\ do not modify order: it's directly linked to the underlying C library enum/order /!\
//

/**
 * Specifies the placement gravity.
 */
public enum class Gravity {
    /** Undefined. */
    UNDEFINED,

    /** Forget. */
    FORGET, // = UNDEFINED,

    /** Northwest. */
    NORTHWEST, // = 1,

    /** North. */
    NORTH, // = 2,

    /** Northeast. */
    NORTHEAST, // = 3,

    /** West. */
    WEST, // = 4,

    /** Center. */
    CENTER, // = 5,

    /** East. */
    EAST, // = 6,

    /** Southwest. */
    SOUTHWEST, // = 7,

    /** South. */
    SOUTH, // = 8,

    /** Southeast. */
    SOUTHEAST, // = 9,
}
