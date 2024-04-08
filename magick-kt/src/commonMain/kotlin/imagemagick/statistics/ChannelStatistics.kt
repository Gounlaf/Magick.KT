package imagemagick.statistics

import imagemagick.core.enums.PixelChannel
import imagemagick.magicknative.statistics.NativeChannelStatistics
import kotlinx.cinterop.ExperimentalForeignApi
import imagemagick.core.statistics.ChannelStatistics as IChannelStatistics

internal class ChannelStatistics private constructor(
    override val channel: PixelChannel,
    override val depth: UInt,
    override val entropy: Double,
    override val kurtosis: Double,
    override val maximum: Double,
    override val mean: Double,
    override val minimum: Double,
    override val skewness: Double,
    override val standardDeviation: Double,
) : IChannelStatistics {
    companion object {
        @ExperimentalStdlibApi
        @ExperimentalForeignApi
        internal fun create(
            channel: PixelChannel,
            source: NativeChannelStatistics,
        ): ChannelStatistics =
            ChannelStatistics(
                channel,
                source.depth.toUInt(),
                source.entropy,
                source.kurtosis,
                source.maximum,
                source.mean,
                source.minimum,
                source.skewness,
                source.standardDeviation,
            )
    }
}
