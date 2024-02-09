package imagemagick.magicknative.types

import imagemagick.enums.GeometryFlags
import imagemagick.helpers.BitMask
import imagemagick.helpers.enabledValues
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.GeometryInfo
import libMagickNative.MagickGeometry_Create
import libMagickNative.MagickGeometry_Dispose
import libMagickNative.MagickGeometry_Height_Get
import libMagickNative.MagickGeometry_Initialize
import libMagickNative.MagickGeometry_Width_Get
import libMagickNative.MagickGeometry_X_Get
import libMagickNative.MagickGeometry_Y_Get
import libMagickNative.MagickStatusType

@ExperimentalForeignApi
@ExperimentalStdlibApi
public class NativeMagickGeometry : AutoCloseable {
    private val ptr: CPointer<GeometryInfo>

    public constructor() {
        ptr = create()
    }

    public constructor(instance: CPointer<GeometryInfo>) {
        ptr = instance
    }

    override fun close() {
        ptr.dispose()
    }

    public fun initialize(value: String): List<GeometryFlags> = enabledValues<GeometryFlags>(BitMask(ptr.initialize(value).toULong()))

    public val width: Double get() = ptr.width()
    public val height: Double get() = ptr.height()
    public val x: Double get() = ptr.x()
    public val y: Double get() = ptr.y()

    public companion object {
        @Throws(IllegalStateException::class)
        public inline fun create(): CPointer<GeometryInfo> = MagickGeometry_Create() ?: error("Failed to instantiate native MagickGeometry")

        public inline fun CPointer<GeometryInfo>.dispose(): Unit = MagickGeometry_Dispose(this)

        public inline fun CPointer<GeometryInfo>.initialize(value: String): MagickStatusType = MagickGeometry_Initialize(this, value)

        public inline fun CPointer<GeometryInfo>.width(): Double = MagickGeometry_Width_Get(this)
//        inline fun CPointer<GeometryInfo>.width(): Double = this.pointed.rho

        public inline fun CPointer<GeometryInfo>.height(): Double = MagickGeometry_Height_Get(this)
//        inline fun CPointer<GeometryInfo>.height(): Double = this.pointed.sigma

        public inline fun CPointer<GeometryInfo>.x(): Double = MagickGeometry_X_Get(this)
//        inline fun CPointer<GeometryInfo>.x(): Double = this.pointed.xi

        public inline fun CPointer<GeometryInfo>.y(): Double = MagickGeometry_Y_Get(this)
//        inline fun CPointer<GeometryInfo>.y(): Double = this.pointed.psi
    }
}
