package imagemagick.native

import imagemagick.enums.GeometryFlags
import imagemagick.helpers.BitMask
import imagemagick.helpers.enabledValues
import imagemagick.native.NativeMagickGeometry.Companion.width
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
internal class NativeMagickGeometry : AutoCloseable {
    internal val native: CPointer<GeometryInfo> = create()

    override fun close() {
        native.dispose()
    }

    fun initialize(value: String): List<GeometryFlags> = enabledValues<GeometryFlags>(BitMask(native.initialize(value).toULong()))

    val width: Double get() = native.width()
    val height: Double get() = native.height()
    val x: Double get() = native.x()
    val y: Double get() = native.y()

    companion object {
        @Throws(IllegalStateException::class)
        internal inline fun create(): CPointer<GeometryInfo> =
            MagickGeometry_Create() ?: error("Failed to instantiate native MagickGeometry")

        internal inline fun CPointer<GeometryInfo>.dispose() = MagickGeometry_Dispose(this)

        internal inline fun CPointer<GeometryInfo>.initialize(value: String): MagickStatusType =
            MagickGeometry_Initialize(this, value)

        internal inline fun CPointer<GeometryInfo>.width(): Double = MagickGeometry_Width_Get(this)
//        internal inline fun CPointer<GeometryInfo>.width(): Double = this.pointed.rho

        internal inline fun CPointer<GeometryInfo>.height(): Double = MagickGeometry_Height_Get(this)
//        internal inline fun CPointer<GeometryInfo>.height(): Double = this.pointed.sigma

        internal inline fun CPointer<GeometryInfo>.x(): Double = MagickGeometry_X_Get(this)
//        internal inline fun CPointer<GeometryInfo>.x(): Double = this.pointed.xi

        internal inline fun CPointer<GeometryInfo>.y(): Double = MagickGeometry_Y_Get(this)
//        internal inline fun CPointer<GeometryInfo>.y(): Double = this.pointed.psi
    }
}
