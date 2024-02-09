package imagemagick.types

import imagemagick.core.types.Percentage.Companion.percent
import imagemagick.magicknative.types.NativeMagickRectangle
import kotlinx.cinterop.ExperimentalForeignApi
import imagemagick.core.MagickImage as IMagickImage
import imagemagick.core.types.MagickGeometry as IMagickGeometry

public data class MagickRectangle(val x: Int, val y: Int, val width: UInt, val height: UInt) {
    public companion object {
        @ExperimentalStdlibApi
        @ExperimentalForeignApi
        public fun fromPageSize(pageSize: String): MagickRectangle? =
            NativeMagickRectangle.fromPageSize(pageSize)?.use { it.toMagickRectangle() }

        public fun fromGeometry(geometry: IMagickGeometry, image: IMagickImage): MagickRectangle {
            var width = geometry.width
            var height = geometry.height

            if (geometry.isPercentage) {
                width = width.percent() * image.width
                height = height.percent() * image.height
            }

            return MagickRectangle(geometry.x, geometry.y, width, height)
        }

        @ExperimentalStdlibApi
        @ExperimentalForeignApi
        private inline fun NativeMagickRectangle.toMagickRectangle(): MagickRectangle = MagickRectangle(x, y, width, height)

        @ExperimentalForeignApi
        @ExperimentalStdlibApi
        public inline fun MagickRectangle.toNative(): NativeMagickRectangle = NativeMagickRectangle().apply {
            x = this@toNative.x
            y = this@toNative.y
            width = this@toNative.width
            height = this@toNative.height
        }
    }
}
