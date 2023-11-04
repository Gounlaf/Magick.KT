package imagemagick.native

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.MagickRectangle_FromPageSize
import libMagickNative.MagickRectangle_Height_Get
import libMagickNative.MagickRectangle_Width_Get
import libMagickNative.MagickRectangle_X_Get
import libMagickNative.MagickRectangle_Y_Get
import libMagickNative.RectangleInfo
import platform.posix.size_t
import platform.posix.ssize_t

@ExperimentalForeignApi
internal object NativeMagickRectangle {
//    companion object {
        internal inline fun fromPageSize(value: String): CPointer<RectangleInfo>? =
            MagickRectangle_FromPageSize(value)

        internal inline fun CPointer<RectangleInfo>.width(): size_t = MagickRectangle_Width_Get(this)

        internal inline fun CPointer<RectangleInfo>.height(): size_t = MagickRectangle_Height_Get(this)

        internal inline fun CPointer<RectangleInfo>.x(): ssize_t = MagickRectangle_X_Get(this)

        internal inline fun CPointer<RectangleInfo>.y(): ssize_t = MagickRectangle_Y_Get(this)
//    }
}
