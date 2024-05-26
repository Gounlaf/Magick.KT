@file:Suppress("KDocMissingDocumentation")

package imagemagick.bridge

import imagemagick.core.enums.PixelChannel
import imagemagick.core.toNative
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.ChannelPerceptualHash
import libMagickNative.ChannelPerceptualHash_GetHuPhash
import libMagickNative.Image
import libMagickNative.PerceptualHash_DisposeList
import libMagickNative.PerceptualHash_GetInstance
import platform.posix.size_t

@ExperimentalForeignApi
public inline fun CPointer<ChannelPerceptualHash>.dispose(): Unit = PerceptualHash_DisposeList(this)

@ExperimentalForeignApi
public inline fun CPointer<ChannelPerceptualHash>.getInstance(
    image: CPointer<Image>,
    channel: PixelChannel,
): CPointer<ChannelPerceptualHash>? = PerceptualHash_GetInstance(image, this, channel.toNative())

@ExperimentalForeignApi
public inline fun CPointer<ChannelPerceptualHash>.getHuPhash(
    colorSpaceIndex: size_t,
    index: size_t,
): Double = ChannelPerceptualHash_GetHuPhash(this, colorSpaceIndex, index)
