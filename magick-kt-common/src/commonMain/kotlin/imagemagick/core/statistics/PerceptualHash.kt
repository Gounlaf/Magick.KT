package imagemagick.core.statistics

import imagemagick.core.enums.PixelChannel
import imagemagick.core.statistics.ChannelPerceptualHash as IChannelPerceptualHash

/**
 * Contains the perceptual hash of one or more image channels.
 */
public interface PerceptualHash {
    /**
     * Returns the perceptual hash for the specified channel.
     *
     * @param channel The channel to get the has for.
     * @return The perceptual hash for the specified channel.
     */
    public fun getChannel(channel: PixelChannel): IChannelPerceptualHash?

    /**
     * Returns the sum squared difference between this hash and the other hash.
     *
     * @param other The [PerceptualHash] to get the distance of.
     * @return The sum squared difference between this hash and the other hash.
     */
    public fun sumSquaredDistance(other: PerceptualHash): Double

    /**
     * Returns a string representation of this hash.
     *
     * @return A [String].
     */
    override fun toString(): String
}
