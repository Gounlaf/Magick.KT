package imagemagick.types

import imagemagick.core.types.Percentage.Companion.percent
import imagemagick.magicknative.types.NativeMagickRectangle
import kotlinx.cinterop.ExperimentalForeignApi
import imagemagick.core.MagickImage as IMagickImage
import imagemagick.core.types.MagickGeometry as IMagickGeometry

@Suppress("KDocMissingDocumentation")
public data class MagickRectangle(val x: Int, val y: Int, val width: UInt, val height: UInt) {
    public companion object {
        @ExperimentalStdlibApi
        @ExperimentalForeignApi
        public fun fromPageSize(pageSize: String): MagickRectangle? =
            NativeMagickRectangle.fromPageSize(pageSize)?.use { it.toMagickRectangle() }

        public fun fromGeometry(
            geometry: IMagickGeometry,
            image: IMagickImage,
        ): MagickRectangle =
            when (geometry.isPercentage) {
                true ->
                    MagickRectangle(
                        geometry.x,
                        geometry.y,
                        geometry.width.percent() * image.width,
                        geometry.height.percent() * image.height,
                    )

                false -> MagickRectangle(geometry.x, geometry.y, geometry.width, geometry.height)
            }

        @ExperimentalStdlibApi
        @ExperimentalForeignApi
        private inline fun NativeMagickRectangle.toMagickRectangle(): MagickRectangle = MagickRectangle(x, y, width, height)

        @ExperimentalForeignApi
        @ExperimentalStdlibApi
        public inline fun MagickRectangle.toNative(): NativeMagickRectangle =
            NativeMagickRectangle().apply {
                x = this@toNative.x
                y = this@toNative.y
                width = this@toNative.width
                height = this@toNative.height
            }
    }
}
