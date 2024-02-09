package imagemagick.core.enums

//
// /!\ do not modify order: it's directly linked to the underlying C library enum/order /!\
//

/**
 * Specified the type of noise that should be added to the image.
 */
public enum class NoiseType {
    /** Undefined. */
    UNDEFINED,

    /** Uniform. */
    UNIFORM,

    /** Gaussian. */
    GAUSSIAN,

    /** MultiplicativeGaussian. */
    MULTIPLICATIVE_GAUSSIAN,

    /** Impulse. */
    IMPULSE,

    /** Poisson. */
    LAPLACIAN,

    /** Poisson. */
    POISSON,

    /** Random. */
    RANDOM,
}
