@file:Suppress("KDocMissingDocumentation")

package imagemagick.bridge

import imagemagick.core.enums.PixelChannel
import imagemagick.core.toNative
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.ChannelStatistics
import libMagickNative.ChannelStatistics_Depth_Get
import libMagickNative.ChannelStatistics_Entropy_Get
import libMagickNative.ChannelStatistics_Kurtosis_Get
import libMagickNative.ChannelStatistics_Maximum_Get
import libMagickNative.ChannelStatistics_Mean_Get
import libMagickNative.ChannelStatistics_Minimum_Get
import libMagickNative.ChannelStatistics_Skewness_Get
import libMagickNative.ChannelStatistics_StandardDeviation_Get
import libMagickNative.Statistics_DisposeList
import libMagickNative.Statistics_GetInstance
import platform.posix.size_t

@ExperimentalForeignApi
public inline fun CPointer<ChannelStatistics>.dispose(): Unit = Statistics_DisposeList(this)

@ExperimentalForeignApi
public inline fun CPointer<ChannelStatistics>.getInstance(channel: PixelChannel): CPointer<ChannelStatistics>? =
    Statistics_GetInstance(this, channel.toNative())

@ExperimentalForeignApi
public inline fun CPointer<ChannelStatistics>.depth(): size_t = ChannelStatistics_Depth_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<ChannelStatistics>.entropy(): Double = ChannelStatistics_Entropy_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<ChannelStatistics>.kurtosis(): Double = ChannelStatistics_Kurtosis_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<ChannelStatistics>.maximum(): Double = ChannelStatistics_Maximum_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<ChannelStatistics>.mean(): Double = ChannelStatistics_Mean_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<ChannelStatistics>.minimum(): Double = ChannelStatistics_Minimum_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<ChannelStatistics>.skewness(): Double = ChannelStatistics_Skewness_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<ChannelStatistics>.standardDeviation(): Double = ChannelStatistics_StandardDeviation_Get(this)
