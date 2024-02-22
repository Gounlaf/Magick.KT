package imagemagick.core.enums

/**
 * Specifies the error metric types.
 */
public enum class ErrorMetric {
    /** Undefined. */
    UNDEFINED,

    /** Absolute. */
    ABSOLUTE,

    /** Fuzz. */
    FUZZ,

    /** Mean absolute. */
    MEAN_ABSOLUTE,

    /** Mean error per pixel. */
    MEAN_ERROR_PER_PIXEL,

    /** Mean squared. */
    MEAN_SQUARED,

    /** Normalized cross correlation. */
    NORMALIZED_CROSS_CORRELATION,

    /** Peak absolute. */
    PEAK_ABSOLUTE,

    /** Peak signal to noise ratio. */
    PEAK_SIGNAL_TO_NOISE_RATIO,

    /** Perceptual hash. */
    PERCEPTUAL_HASH,

    /** Root mean squared. */
    ROOT_MEAN_SQUARED,

    /** Structural similarity. */
    STRUCTURAL_SIMILARITY,

    /** Structural dissimilarity. */
    STRUCTURAL_DISSIMILARITY,
}
