@file:Suppress("KDocMissingDocumentation")

package imagemagick.bridge

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.MagickRectangle_Dispose
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

@ExperimentalForeignApi
public inline fun CPointer<RectangleInfo>.dispose(): Unit = MagickRectangle_Dispose(this)

@ExperimentalForeignApi
public inline fun CPointer<RectangleInfo>.width(): size_t = MagickRectangle_Width_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<RectangleInfo>.width(value: size_t): Unit = MagickRectangle_Width_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<RectangleInfo>.height(): size_t = MagickRectangle_Height_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<RectangleInfo>.height(value: size_t): Unit = MagickRectangle_Height_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<RectangleInfo>.x(): ssize_t = MagickRectangle_X_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<RectangleInfo>.x(value: ssize_t): Unit = MagickRectangle_X_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<RectangleInfo>.y(): ssize_t = MagickRectangle_Y_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<RectangleInfo>.y(value: ssize_t): Unit = MagickRectangle_Y_Set(this, value)
