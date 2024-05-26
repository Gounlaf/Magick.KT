package imagemagick.types

import imagemagick.bridge.x
import imagemagick.bridge.y
import imagemagick.core.types.PointD
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.PointInfo as NativePointInfo

internal data class PointInfo(val x: Double, val y: Double) {
    companion object {
        @ExperimentalForeignApi
        internal fun create(source: CPointer<NativePointInfo>): PointInfo =
            PointInfo(
                x = source.x,
                y = source.y,
            )
    }

    fun toPointD(): PointD = PointD(x, y)
}
