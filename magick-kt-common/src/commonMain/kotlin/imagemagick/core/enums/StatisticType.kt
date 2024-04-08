package imagemagick.core.enums

//
// /!\ do not modify order: it's directly linked to the underlying C library enum/order /!\
//

/**
 * Specifies the statistic types.
 */
public enum class StatisticType {
    /** Undefined. */
    UNDEFINED,

    /** Gradient. */
    GRADIENT,

    /** Maximum. */
    MAXIMUM,

    /** Mean. */
    MEAN,

    /** Median. */
    MEDIAN,

    /** Minimum. */
    MINIMUM,

    /** Mode. */
    MODE,

    /** Nonpeak. */
    NONPEAK,

    /** RootMeanSquare. */
    ROOT_MEAN_SQUARE,

    /** StandardDeviation. */
    STANDARD_DEVIATION,
}
