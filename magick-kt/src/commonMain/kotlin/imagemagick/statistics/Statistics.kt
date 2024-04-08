package imagemagick.statistics

import imagemagick.MagickImage
import imagemagick.bridge.getInstance
import imagemagick.core.enums.Channels
import imagemagick.core.enums.PixelChannel
import imagemagick.helpers.associateWithNotNull
import imagemagick.magicknative.statistics.NativeChannelStatistics
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.contracts.ExperimentalContracts
import kotlin.experimental.ExperimentalNativeApi
import imagemagick.core.statistics.ChannelStatistics as IChannelStatistics
import imagemagick.core.statistics.Statistics as IStatistics

@ExperimentalForeignApi
@ExperimentalStdlibApi
@ExperimentalContracts
@ExperimentalNativeApi
internal class Statistics(
    image: MagickImage,
    list: CPointer<libMagickNative.ChannelStatistics>,
    channels: Channels,
) : IStatistics {
    private val channelsMap: Map<PixelChannel, ChannelStatistics> =
        image.channels
            .filter { ((channels.bit shr it.channel) and 1uL) != 0uL }
            .plus(PixelChannel.COMPOSITE)
            .associateWithNotNull { channel ->
                list.getInstance(channel)?.let { ChannelStatistics.create(channel, NativeChannelStatistics(it)) }
            }

    override val channels: Set<PixelChannel> = channelsMap.keys

    override fun composite(): IChannelStatistics = getChannel(PixelChannel.COMPOSITE)!!

    override fun getChannel(channel: PixelChannel): IChannelStatistics? = channelsMap[channel]
}
