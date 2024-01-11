package imagemagick.types

import imagemagick.native.NativeMagickRectangle
import imagemagick.native.NativeMagickRectangle.dispose
import imagemagick.native.NativeMagickRectangle.height
import imagemagick.native.NativeMagickRectangle.width
import imagemagick.native.NativeMagickRectangle.x
import imagemagick.native.NativeMagickRectangle.y
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.RectangleInfo

public data class MagickRectangle(val x: Int, val y: Int, val width: UInt, val height: UInt) {
    public companion object {
        @ExperimentalForeignApi
        public fun fromPageSize(pageSize: String): MagickRectangle? =
            NativeMagickRectangle.fromPageSize(pageSize)?.let {
                val rectangle = it.toMagickRectangle()

                it.dispose()

                rectangle
            }

        @ExperimentalForeignApi
        private inline fun CPointer<RectangleInfo>.toMagickRectangle(): MagickRectangle =
            MagickRectangle(
                x = x().toInt(),
                y = y().toInt(),
                width = width().toUInt(),
                height = height().toUInt(),
            )
    }
}
