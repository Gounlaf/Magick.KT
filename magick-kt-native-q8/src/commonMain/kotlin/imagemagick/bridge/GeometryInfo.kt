@file:Suppress("KDocMissingDocumentation")

package imagemagick.bridge

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.GeometryInfo
import libMagickNative.MagickGeometry_Dispose
import libMagickNative.MagickGeometry_Height_Get
import libMagickNative.MagickGeometry_Initialize
import libMagickNative.MagickGeometry_Width_Get
import libMagickNative.MagickGeometry_X_Get
import libMagickNative.MagickGeometry_Y_Get
import libMagickNative.MagickStatusType

@ExperimentalForeignApi
public inline fun CPointer<GeometryInfo>.dispose(): Unit = MagickGeometry_Dispose(this)

@ExperimentalForeignApi
public inline fun CPointer<GeometryInfo>.initialize(value: String): MagickStatusType = MagickGeometry_Initialize(this, value)

@ExperimentalForeignApi
public inline fun CPointer<GeometryInfo>.width(): Double = MagickGeometry_Width_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<GeometryInfo>.height(): Double = MagickGeometry_Height_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<GeometryInfo>.x(): Double = MagickGeometry_X_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<GeometryInfo>.y(): Double = MagickGeometry_Y_Get(this)
