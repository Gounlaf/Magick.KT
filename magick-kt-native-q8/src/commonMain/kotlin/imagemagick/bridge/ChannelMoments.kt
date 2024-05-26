@file:Suppress("KDocMissingDocumentation")

package imagemagick.bridge

import imagemagick.core.enums.PixelChannel
import imagemagick.core.toNative
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.ChannelMoments
import libMagickNative.ChannelMoments_Centroid_Get
import libMagickNative.ChannelMoments_EllipseAngle_Get
import libMagickNative.ChannelMoments_EllipseAxis_Get
import libMagickNative.ChannelMoments_EllipseEccentricity_Get
import libMagickNative.ChannelMoments_EllipseIntensity_Get
import libMagickNative.ChannelMoments_GetHuInvariants
import libMagickNative.Moments_DisposeList
import libMagickNative.Moments_GetInstance
import libMagickNative.PointInfo
import platform.posix.size_t

@ExperimentalForeignApi
public inline fun CPointer<ChannelMoments>.dispose(): Unit = Moments_DisposeList(this)

@ExperimentalForeignApi
public inline fun CPointer<ChannelMoments>.getInstance(channel: PixelChannel): CPointer<ChannelMoments>? =
    Moments_GetInstance(this, channel.toNative())

@ExperimentalForeignApi
public inline val CPointer<ChannelMoments>.centroid: CPointer<PointInfo>? get() = ChannelMoments_Centroid_Get(this)

@ExperimentalForeignApi
public inline val CPointer<ChannelMoments>.ellipseAxis: CPointer<PointInfo>? get() = ChannelMoments_EllipseAxis_Get(this)

@ExperimentalForeignApi
public inline val CPointer<ChannelMoments>.ellipseAngle: Double get() = ChannelMoments_EllipseAngle_Get(this)

@ExperimentalForeignApi
public inline val CPointer<ChannelMoments>.ellipseEccentricity: Double
    get() =
        ChannelMoments_EllipseEccentricity_Get(
            this,
        )

@ExperimentalForeignApi
public inline val CPointer<ChannelMoments>.ellipseIntensity: Double get() = ChannelMoments_EllipseIntensity_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<ChannelMoments>.huInvariants(index: size_t): Double = ChannelMoments_GetHuInvariants(this, index)
