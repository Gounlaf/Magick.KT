@file:Suppress("KDocMissingDocumentation")

package imagemagick.bridge

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.PrimaryInfo
import libMagickNative.PrimaryInfo_Dispose
import libMagickNative.PrimaryInfo_X_Get
import libMagickNative.PrimaryInfo_X_Set
import libMagickNative.PrimaryInfo_Y_Get
import libMagickNative.PrimaryInfo_Y_Set
import libMagickNative.PrimaryInfo_Z_Get
import libMagickNative.PrimaryInfo_Z_Set

@ExperimentalForeignApi
public inline fun CPointer<PrimaryInfo>.dispose(): Unit = PrimaryInfo_Dispose(this)

@ExperimentalForeignApi
public inline fun CPointer<PrimaryInfo>.x(): Double = PrimaryInfo_X_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<PrimaryInfo>.x(value: Double): Unit = PrimaryInfo_X_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<PrimaryInfo>.y(): Double = PrimaryInfo_Y_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<PrimaryInfo>.y(value: Double): Unit = PrimaryInfo_Y_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<PrimaryInfo>.z(): Double = PrimaryInfo_Z_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<PrimaryInfo>.z(value: Double): Unit = PrimaryInfo_Z_Set(this, value)
