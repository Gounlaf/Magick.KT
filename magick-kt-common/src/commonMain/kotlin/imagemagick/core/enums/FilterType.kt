package imagemagick.core.enums

//
// /!\ do not modify order: it's directly linked to the underlying C library enum/order /!\
//

/**
 * Specifies the filter types.
 */
public enum class FilterType {
    /** Undefined. */
    UNDEFINED,

    /** Point. */
    POINT,

    /** Box. */
    BOX,

    /** Triangle. */
    TRIANGLE,

    /** Hermite. */
    HERMITE,

    /** Hann. */
    HANN,

    /** Hamming. */
    HAMMING,

    /** Blackman. */
    BLACKMAN,

    /** Gaussian. */
    GAUSSIAN,

    /** Quadratic. */
    QUADRATIC,

    /** Cubic. */
    CUBIC,

    /** Catrom. */
    CATROM,

    /** Mitchell. */
    MITCHELL,

    /** Jinc. */
    JINC,

    /** Sinc. */
    SINC,

    /** Sinc fast. */
    SINC_FAST,

    /** Kaiser. */
    KAISER,

    /** Welch. */
    WELCH,

    /** Parzen. */
    PARZEN,

    /** Bohman. */
    BOHMAN,

    /** Bartlett. */
    BARTLETT,

    /** Lagrange. */
    LAGRANGE,

    /** Lanczos. */
    LANCZOS,

    /** Lanczos sharp. */
    LANCZOS_SHARP,

    /** Lanczos 2. */
    LANCZOS2,

    /** Lanczos 2 sharp. */
    LANCZOS2_SHARP,

    /** Robidoux. */
    ROBIDOUX,

    /** Robidoux sharp. */
    ROBIDOUX_SHARP,

    /** Cosine. */
    COSINE,

    /** Spline. */
    SPLINE,

    /** Lanczos radius. */
    LANCZOS_RADIUS,

    /** Cubic spline. */
    CUBIC_SPLINE,
}
