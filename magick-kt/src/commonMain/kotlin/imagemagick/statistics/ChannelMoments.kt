package imagemagick.statistics

import imagemagick.core.enums.PixelChannel
import imagemagick.core.types.PointD
import imagemagick.magicknative.statistics.NativeChannelMoments
import imagemagick.types.PointInfo
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import imagemagick.core.statistics.ChannelMoments as IChannelMoments

/**
 * The normalized moments of one image channels.
 */
public data class ChannelMoments(
    override val centroid: PointD,
    override val channel: PixelChannel,
    override val ellipseAxis: PointD,
    override val ellipseAngle: Double,
    override val ellipseEccentricity: Double,
    override val ellipseIntensity: Double,
    private val huInvariants: DoubleArray,
) : IChannelMoments {
    override fun huInvariants(index: Int): Double = huInvariants[index]

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ChannelMoments) return false

        if (centroid != other.centroid) return false
        if (channel != other.channel) return false
        if (ellipseAxis != other.ellipseAxis) return false
        if (ellipseAngle != other.ellipseAngle) return false
        if (ellipseEccentricity != other.ellipseEccentricity) return false
        if (ellipseIntensity != other.ellipseIntensity) return false
        if (!huInvariants.contentEquals(other.huInvariants)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = centroid.hashCode()
        result = 31 * result + channel.hashCode()
        result = 31 * result + ellipseAxis.hashCode()
        result = 31 * result + ellipseAngle.hashCode()
        result = 31 * result + ellipseEccentricity.hashCode()
        result = 31 * result + ellipseIntensity.hashCode()
        result = 31 * result + huInvariants.contentHashCode()
        return result
    }

    public companion object {
        @ExperimentalForeignApi
        internal fun create(
            channel: PixelChannel,
            source: NativeChannelMoments,
        ): ChannelMoments {
            return ChannelMoments(
                centroid = PointInfo.create(source.centroid).toPointD(),
                channel = channel,
                ellipseAxis = PointInfo.create(source.ellipseAxis).toPointD(),
                ellipseAngle = source.ellipseAngle,
                ellipseEccentricity = source.ellipseEccentricity,
                ellipseIntensity = source.ellipseIntensity,
                huInvariants =
                    DoubleArray(8).apply {
                        for (index in 0 until 8) {
                            this[index] = source.huInvariants(index.convert())
                        }
                    },
            )
        }
    }
}
