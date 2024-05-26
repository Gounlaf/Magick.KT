package imagemagick.statistics

import imagemagick.MagickImage
import imagemagick.core.enums.PixelChannel
import imagemagick.helpers.associateWithNotNull
import imagemagick.magicknative.statistics.NativeMoments
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.contracts.ExperimentalContracts
import kotlin.experimental.ExperimentalNativeApi
import imagemagick.core.statistics.ChannelMoments as IChannelMoments
import imagemagick.core.statistics.Moments as IMoments

/**
 * The normalized moments of one or more image channels.
 */
@ExperimentalForeignApi
public class Moments : IMoments {
    private val channels: Map<PixelChannel, IChannelMoments>

    @ExperimentalContracts
    @ExperimentalNativeApi
    @ExperimentalStdlibApi
    internal constructor(image: MagickImage, nativeMoments: NativeMoments) {
        channels =
            image.channels.associateWithNotNull { pixelChannel ->
                nativeMoments.getChannelMoments(pixelChannel)?.let {
                    ChannelMoments.create(pixelChannel, it)
                }
            }
    }

    override fun composite(): IChannelMoments = channels.getValue(PixelChannel.COMPOSITE)

    override fun getChannel(channel: PixelChannel): IChannelMoments? = channels[channel]
}
