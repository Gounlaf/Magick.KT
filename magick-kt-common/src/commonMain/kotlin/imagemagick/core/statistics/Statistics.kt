package imagemagick.core.statistics

import imagemagick.core.enums.PixelChannel
import imagemagick.core.statistics.ChannelStatistics as IChannelStatistics

/**
 * Encapsulation of the ImageMagick statistics object.
 */
public interface Statistics {
    /**
     * Gets the channels.
     */
    public val channels: Set<PixelChannel>

    /**
     * Returns the statistics for the all the channels.
     *
     * @return The statistics for the all the channels.
     */
    public fun composite(): IChannelStatistics

    /**
     * Returns the statistics for the specified channel.
     *
     * @param channel The channel to get the statistics for.
     * @return The statistics for the specified channel.
     */
    public fun getChannel(channel: PixelChannel): IChannelStatistics?
}
