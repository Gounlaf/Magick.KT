@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative.statistics

import imagemagick.bridge.centroid
import imagemagick.bridge.ellipseAngle
import imagemagick.bridge.ellipseAxis
import imagemagick.bridge.ellipseEccentricity
import imagemagick.bridge.ellipseIntensity
import imagemagick.bridge.huInvariants
import imagemagick.core.enums.PixelChannel
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.ChannelMoments
import libMagickNative.PointInfo
import platform.posix.size_t

@ExperimentalForeignApi
public class NativeChannelMoments(
    private val ptr: CPointer<ChannelMoments>,
    private val channel: PixelChannel,
) {
    public val centroid: CPointer<PointInfo>
        get() = ptr.centroid ?: error("Failed to get CPointer<PointInfo>.centroid")

    public val ellipseAxis: CPointer<PointInfo>
        get() = ptr.ellipseAxis ?: error("Failed to get CPointer<PointInfo>.ellipseAxis")

    public val ellipseAngle: Double get() = ptr.ellipseAngle

    public val ellipseEccentricity: Double get() = ptr.ellipseEccentricity

    public val ellipseIntensity: Double get() = ptr.ellipseIntensity

    public fun huInvariants(index: size_t): Double = ptr.huInvariants(index)
}
