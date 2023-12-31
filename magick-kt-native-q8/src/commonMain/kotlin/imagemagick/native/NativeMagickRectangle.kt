package imagemagick.native

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.MagickRectangle_Dispose
import libMagickNative.MagickRectangle_FromPageSize
import libMagickNative.MagickRectangle_Height_Get
import libMagickNative.MagickRectangle_Width_Get
import libMagickNative.MagickRectangle_X_Get
import libMagickNative.MagickRectangle_Y_Get
import libMagickNative.RectangleInfo

@ExperimentalForeignApi
object NativeMagickRectangle {
    inline fun fromPageSize(value: String): CPointer<RectangleInfo>? =
        MagickRectangle_FromPageSize(value)

    inline fun CPointer<RectangleInfo>.dispose() = MagickRectangle_Dispose(this)

    inline fun CPointer<RectangleInfo>.width(): ULong = MagickRectangle_Width_Get(this)

    inline fun CPointer<RectangleInfo>.height(): ULong = MagickRectangle_Height_Get(this)

    inline fun CPointer<RectangleInfo>.x(): Long = MagickRectangle_X_Get(this)

    inline fun CPointer<RectangleInfo>.y(): Long = MagickRectangle_Y_Get(this)
}
