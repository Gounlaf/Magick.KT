package imagemagick.native

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
class NativeMagickGeometry : AutoCloseable {
    private val ptr: CPointer<GeometryInfo> = create()

    override fun close() {
        ptr.dispose()
    }

    fun initialize(value: String): List<GeometryFlags> = enabledValues<GeometryFlags>(BitMask(ptr.initialize(value).toULong()))

    val width: Double get() = ptr.width()
    val height: Double get() = ptr.height()
    val x: Double get() = ptr.x()
    val y: Double get() = ptr.y()

    companion object {
        @Throws(IllegalStateException::class)
        inline fun create(): CPointer<GeometryInfo> =
            MagickGeometry_Create() ?: error("Failed to instantiate native MagickGeometry")

        inline fun CPointer<GeometryInfo>.dispose() = MagickGeometry_Dispose(this)

        inline fun CPointer<GeometryInfo>.initialize(value: String): MagickStatusType =
            MagickGeometry_Initialize(this, value)

        inline fun CPointer<GeometryInfo>.width(): Double = MagickGeometry_Width_Get(this)
//        inline fun CPointer<GeometryInfo>.width(): Double = this.pointed.rho

        inline fun CPointer<GeometryInfo>.height(): Double = MagickGeometry_Height_Get(this)
//        inline fun CPointer<GeometryInfo>.height(): Double = this.pointed.sigma

        inline fun CPointer<GeometryInfo>.x(): Double = MagickGeometry_X_Get(this)
//        inline fun CPointer<GeometryInfo>.x(): Double = this.pointed.xi

        inline fun CPointer<GeometryInfo>.y(): Double = MagickGeometry_Y_Get(this)
//        inline fun CPointer<GeometryInfo>.y(): Double = this.pointed.psi
    }
}
