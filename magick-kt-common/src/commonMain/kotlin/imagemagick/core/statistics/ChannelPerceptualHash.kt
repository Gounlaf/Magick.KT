package imagemagick.core.statistics

import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.PixelChannel

/**
 * Contains the perceptual hash of one image channel.
 */
public interface ChannelPerceptualHash {
    /**
     * Gets the channel.
     */
    public val channel: PixelChannel

    /**
     * Returns the hu perceptual hash for the specified colorspace.
     *
     * @param colorSpace The colorspace to use.
     * @param index The index to use.
     * @return The hu perceptual hash for the specified colorspace.
     */
    public fun huPhash(
        colorSpace: ColorSpace,
        index: Int,
    ): Double

    /**
     * Returns the sum squared difference between this hash and the other hash.
     *
     * @param other The [ChannelPerceptualHash] to get the distance of.
     * @return The sum squared difference between this hash and the other hash.
     */
    public fun sumSquaredDistance(other: ChannelPerceptualHash): Double

    /**
     * Returns a string representation of this hash.
     *
     * @return A string representation of this hash.
     */
    override fun toString(): String
}
