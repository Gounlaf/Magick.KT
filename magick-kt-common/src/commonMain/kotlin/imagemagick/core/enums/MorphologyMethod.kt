package imagemagick.core.enums

/**
 * Specifies the morphology methods.
 */
public enum class MorphologyMethod {
    /** Undefined. */
    UNDEFINED,

    /** Convolve. */
    CONVOLVE,

    /** Correlate. */
    CORRELATE,

    /** Erode. */
    ERODE,

    /** Dilate. */
    DILATE,

    /** Erode intensity. */
    ERODE_INTENSITY,

    /** Dilate intensity. */
    DILATE_INTENSITY,

    /** Iterative distance. */
    ITERATIVE_DISTANCE,

    /** Open. */
    OPEN,

    /** Close. */
    CLOSE,

    /** Open intensity. */
    OPEN_INTENSITY,

    /** Close intensity. */
    CLOSE_INTENSITY,

    /** Smooth. */
    SMOOTH,

    /** Edge in. */
    EDGE_IN,

    /** Edge out. */
    EDGE_OUT,

    /** Edge. */
    EDGE,

    /** Top hat. */
    TOP_HAT,

    /** Bottom hat. */
    BOTTOM_HAT,

    /** Hit and miss. */
    HIT_AND_MISS,

    /** Thinning. */
    THINNING,

    /** Thicken. */
    THICKEN,

    /** Distance. */
    DISTANCE,

    /** Voronoi. */
    VORONOI,
}
