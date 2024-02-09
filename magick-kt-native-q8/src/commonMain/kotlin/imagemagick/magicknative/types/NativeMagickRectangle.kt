package imagemagick.magicknative.types

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import libMagickNative.MagickRectangle_Create
import libMagickNative.MagickRectangle_Dispose
import libMagickNative.MagickRectangle_FromPageSize
import libMagickNative.MagickRectangle_Height_Get
import libMagickNative.MagickRectangle_Height_Set
import libMagickNative.MagickRectangle_Width_Get
import libMagickNative.MagickRectangle_Width_Set
import libMagickNative.MagickRectangle_X_Get
import libMagickNative.MagickRectangle_X_Set
import libMagickNative.MagickRectangle_Y_Get
import libMagickNative.MagickRectangle_Y_Set
import libMagickNative.RectangleInfo
import platform.posix.size_t
import platform.posix.ssize_t

@ExperimentalStdlibApi
@ExperimentalForeignApi
public class NativeMagickRectangle : AutoCloseable {
    public val ptr: CPointer<RectangleInfo>

    public constructor() {
        ptr = create()
    }

    public constructor(instance: CPointer<RectangleInfo>) {
        ptr = instance
    }

    override fun close() {
        ptr.dispose()
    }

    public var x: Int
        get() = ptr.x().toInt()
        set(value) = ptr.x(value.convert())

    public var y: Int
        get() = ptr.y().toInt()
        set(value) = ptr.y(value.convert())

    public var width: UInt
        get() = ptr.width().toUInt()
        set(value) = ptr.width(value.convert())

    public var height: UInt
        get() = ptr.height().toUInt()
        set(value) = ptr.height(value.convert())

    public companion object {
        @Throws(IllegalStateException::class)
        public inline fun create(): CPointer<RectangleInfo> =
            MagickRectangle_Create() ?: error("Failed to instantiate native MagickRectangle")

        public inline fun fromPageSize(value: String): NativeMagickRectangle? =
            MagickRectangle_FromPageSize(value)?.let {
                NativeMagickRectangle(it)
            }

        public inline fun CPointer<RectangleInfo>.dispose(): Unit = MagickRectangle_Dispose(this)

        public inline fun CPointer<RectangleInfo>.width(): size_t = MagickRectangle_Width_Get(this)

        public inline fun CPointer<RectangleInfo>.width(value: size_t): Unit = MagickRectangle_Width_Set(this, value)

        public inline fun CPointer<RectangleInfo>.height(): size_t = MagickRectangle_Height_Get(this)

        public inline fun CPointer<RectangleInfo>.height(value: size_t): Unit = MagickRectangle_Height_Set(this, value)

        public inline fun CPointer<RectangleInfo>.x(): ssize_t = MagickRectangle_X_Get(this)

        public inline fun CPointer<RectangleInfo>.x(value: ssize_t): Unit = MagickRectangle_X_Set(this, value)

        public inline fun CPointer<RectangleInfo>.y(): ssize_t = MagickRectangle_Y_Get(this)

        public inline fun CPointer<RectangleInfo>.y(value: ssize_t): Unit = MagickRectangle_Y_Set(this, value)
    }
}
