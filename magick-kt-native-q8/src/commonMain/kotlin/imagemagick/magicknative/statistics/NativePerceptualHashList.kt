@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative.statistics

import imagemagick.bridge.dispose
import imagemagick.bridge.getInstance
import imagemagick.core.enums.PixelChannel
import imagemagick.magicknative.NativeMagickImage
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.ChannelPerceptualHash

@ExperimentalStdlibApi
@ExperimentalForeignApi
public class NativePerceptualHashList(public val ptr: CPointer<ChannelPerceptualHash>) : AutoCloseable {
    override fun close() {
        ptr.dispose()
    }

    public fun createChannelPerceptualHash(
        image: NativeMagickImage,
        channel: PixelChannel,
    ): NativeChannelPerceptualHash? = ptr.getInstance(image.ptr, channel)?.let { NativeChannelPerceptualHash(it) }
}
