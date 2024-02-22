package imagemagick.core.types

/**
 * Encapsulates the error information.
 */
public interface MagickErrorInfo {
    /**
     * Gets the mean error per pixel computed when an image is color reduced.
     */
    public val meanErrorPerPixel: Double

    /**
     * Gets the normalized maximum error per pixel computed when an image is color reduced.
     */
    public val normalizedMaximumError: Double

    /**
     * Gets the normalized mean error per pixel computed when an image is color reduced.
     */
    public val normalizedMeanError: Double
}
