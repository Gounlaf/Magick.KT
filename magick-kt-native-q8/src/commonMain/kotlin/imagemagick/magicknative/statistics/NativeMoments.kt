@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative.statistics

import imagemagick.bridge.dispose
import imagemagick.bridge.getInstance
import imagemagick.core.enums.PixelChannel
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.ChannelMoments

@ExperimentalForeignApi
public class NativeMoments(public val ptr: CPointer<ChannelMoments>) : AutoCloseable {
    override fun close(): Unit = dispose()

    public inline fun dispose(): Unit = ptr.dispose()

    // C# NativeMoments.GetInstance(list, channel)
    public inline fun getChannelMoments(channel: PixelChannel): NativeChannelMoments? =
        ptr.getInstance(channel)?.let {
            NativeChannelMoments(it, channel)
        }
}
