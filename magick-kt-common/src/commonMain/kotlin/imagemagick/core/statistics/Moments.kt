package imagemagick.core.statistics

import imagemagick.core.enums.PixelChannel
import imagemagick.core.statistics.ChannelMoments as IChannelMoments

/**
 * The normalized moments of one or more image channels.
 */
public interface Moments {
    /**
     * Gets the moments for the all the channels.
     *
     * @return The moments for the all the channels.
     */
    public fun composite(): IChannelMoments

    /**
     * Gets the moments for the specified channel.
     *
     * @param channel The channel to get the moments for.
     * @return The moments for the specified channel.
     */
    public fun getChannel(channel: PixelChannel): IChannelMoments?
}
