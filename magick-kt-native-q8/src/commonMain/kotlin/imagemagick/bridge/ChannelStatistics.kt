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
public inline val CPointer<ChannelStatistics>.depth: size_t get() = ChannelStatistics_Depth_Get(this)

@ExperimentalForeignApi
public inline val CPointer<ChannelStatistics>.entropy: Double get() = ChannelStatistics_Entropy_Get(this)

@ExperimentalForeignApi
public inline val CPointer<ChannelStatistics>.kurtosis: Double get() = ChannelStatistics_Kurtosis_Get(this)

@ExperimentalForeignApi
public inline val CPointer<ChannelStatistics>.maximum: Double get() = ChannelStatistics_Maximum_Get(this)

@ExperimentalForeignApi
public inline val CPointer<ChannelStatistics>.mean: Double get() = ChannelStatistics_Mean_Get(this)

@ExperimentalForeignApi
public inline val CPointer<ChannelStatistics>.minimum: Double get() = ChannelStatistics_Minimum_Get(this)

@ExperimentalForeignApi
public inline val CPointer<ChannelStatistics>.skewness: Double get() = ChannelStatistics_Skewness_Get(this)

@ExperimentalForeignApi
public inline val CPointer<ChannelStatistics>.standardDeviation: Double get() = ChannelStatistics_StandardDeviation_Get(this)
