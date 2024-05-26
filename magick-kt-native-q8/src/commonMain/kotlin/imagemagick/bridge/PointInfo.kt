@file:Suppress("KDocMissingDocumentation")

package imagemagick.bridge

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import libMagickNative.PointInfo
import libMagickNative.PointInfoCollection_Dispose
import libMagickNative.PointInfoCollection_GetX
import libMagickNative.PointInfoCollection_GetY
import libMagickNative.PointInfo_X_Get
import libMagickNative.PointInfo_Y_Get
import platform.posix.size_t

@ExperimentalForeignApi
public data class PointInfoCollection(val ptr: CPointer<PointInfo>, val length: size_t) : AutoCloseable {
    override fun close(): Unit = dispose()

    public inline fun dispose(): Unit = PointInfoCollection_Dispose(ptr)

    public inline fun getX(index: ULong): Double = PointInfoCollection_GetX(ptr, index.convert())

    public inline fun getY(index: ULong): Double = PointInfoCollection_GetY(ptr, index.convert())
}

@ExperimentalForeignApi
public inline val CPointer<PointInfo>.x: Double get() = PointInfo_X_Get(this)

@ExperimentalForeignApi
public inline val CPointer<PointInfo>.y: Double get() = PointInfo_Y_Get(this)
