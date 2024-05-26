package imagemagick.core.statistics

import imagemagick.core.enums.PixelChannel
import imagemagick.core.types.PointD

/**
 * The normalized moments of one image channels.
 */
public interface ChannelMoments {
    /**
     * Gets the centroid.
     */
    public val centroid: PointD

    /**
     * Gets the channel of this moment.
     */
    public val channel: PixelChannel

    /**
     * Gets the ellipse axis.
     */
    public val ellipseAxis: PointD

    /**
     * Gets the ellipse angle.
     */
    public val ellipseAngle: Double

    /**
     * Gets the ellipse eccentricity.
     */
    public val ellipseEccentricity: Double

    /**
     * Gets the ellipse intensity.
     */
    public val ellipseIntensity: Double

    /**
     * Returns the Hu invariants.
     *
     * @param index The index to use.
     * @return The Hu invariants.
     */
    public fun huInvariants(index: Int): Double
}
