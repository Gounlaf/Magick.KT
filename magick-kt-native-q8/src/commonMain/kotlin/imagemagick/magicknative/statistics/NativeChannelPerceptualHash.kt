@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative.statistics

import imagemagick.bridge.getHuPhash
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import libMagickNative.ChannelPerceptualHash

@ExperimentalForeignApi
public class NativeChannelPerceptualHash(public val ptr: CPointer<ChannelPerceptualHash>) {
    public fun getHuPhash(
        colorSpaceIndex: UInt,
        index: UInt,
    ): Double = ptr.getHuPhash(colorSpaceIndex.convert(), index.convert())
}
