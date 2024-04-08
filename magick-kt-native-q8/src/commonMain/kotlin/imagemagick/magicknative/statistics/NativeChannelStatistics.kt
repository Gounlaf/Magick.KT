@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative.statistics

import imagemagick.bridge.depth
import imagemagick.bridge.dispose
import imagemagick.bridge.entropy
import imagemagick.bridge.kurtosis
import imagemagick.bridge.maximum
import imagemagick.bridge.mean
import imagemagick.bridge.minimum
import imagemagick.bridge.skewness
import imagemagick.bridge.standardDeviation
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.ChannelStatistics
import platform.posix.size_t

@ExperimentalForeignApi
@ExperimentalStdlibApi
public class NativeChannelStatistics(public val ptr: CPointer<ChannelStatistics>) : AutoCloseable {
    public inline val depth: size_t
        get() = ptr.depth()

    public inline val entropy: Double
        get() = ptr.entropy()

    public inline val kurtosis: Double
        get() = ptr.kurtosis()

    public inline val maximum: Double
        get() = ptr.maximum()

    public inline val mean: Double
        get() = ptr.mean()

    public inline val minimum: Double
        get() = ptr.minimum()

    public inline val skewness: Double
        get() = ptr.skewness()

    public inline val standardDeviation: Double
        get() = ptr.standardDeviation()

    override fun close() {
        ptr.dispose()
    }
}
