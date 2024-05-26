@file:Suppress("KDocMissingDocumentation")

package imagemagick.bridge

import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.DitherMethod
import imagemagick.core.toNative
import imagemagick.magicknative.toNative
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.QuantizeInfo
import libMagickNative.QuantizeSettings_Dispose
import libMagickNative.QuantizeSettings_SetColorSpace
import libMagickNative.QuantizeSettings_SetColors
import libMagickNative.QuantizeSettings_SetDitherMethod
import libMagickNative.QuantizeSettings_SetMeasureErrors
import libMagickNative.QuantizeSettings_SetTreeDepth
import platform.posix.size_t

@ExperimentalForeignApi
public inline fun CPointer<QuantizeInfo>.dispose(): Unit = QuantizeSettings_Dispose(this)

@ExperimentalForeignApi
public inline fun CPointer<QuantizeInfo>.setColors(value: size_t): Unit = QuantizeSettings_SetColors(this, value)

@ExperimentalForeignApi
public inline fun CPointer<QuantizeInfo>.setColorSpace(value: ColorSpace): Unit = QuantizeSettings_SetColorSpace(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<QuantizeInfo>.setDitherMethod(value: DitherMethod): Unit =
    QuantizeSettings_SetDitherMethod(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<QuantizeInfo>.setMeasureError(value: Boolean): Unit = QuantizeSettings_SetMeasureErrors(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<QuantizeInfo>.setTreeDepth(value: size_t): Unit = QuantizeSettings_SetTreeDepth(this, value)
