package imagemagick.core.statistics

import imagemagick.core.enums.PixelChannel

/**
 * Encapsulation of the ImageMagick cannel statistics object.
 */
public interface ChannelStatistics {
    /**
     * Gets the channel.
     */
    public val channel: PixelChannel

    /**
     * Gets the depth of the channel.
     */
    public val depth: UInt

    /**
     * Gets the entropy.
     */
    public val entropy: Double

    /**
     * Gets the kurtosis.
     */
    public val kurtosis: Double

    /**
     * Gets the maximum value observed.
     */
    public val maximum: Double

    /**
     * Gets the average (mean) value observed.
     */
    public val mean: Double

    /**
     * Gets the minimum value observed.
     */
    public val minimum: Double

    /**
     * Gets the skewness.
     */
    public val skewness: Double

    /**
     * Gets the standard deviation, sqrt(variance).
     */
    public val standardDeviation: Double
}
