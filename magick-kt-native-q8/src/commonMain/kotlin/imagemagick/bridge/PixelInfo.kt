@file:Suppress("KDocMissingDocumentation")

package imagemagick.bridge

import imagemagick.QuantumType
import imagemagick.magicknative.colors.NativeMagickColor
import imagemagick.magicknative.toNative
import imagemagick.magicknative.toPrimitive
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.MagickColor_Alpha_Get
import libMagickNative.MagickColor_Alpha_Set
import libMagickNative.MagickColor_Black_Get
import libMagickNative.MagickColor_Black_Set
import libMagickNative.MagickColor_Blue_Get
import libMagickNative.MagickColor_Blue_Set
import libMagickNative.MagickColor_Count_Get
import libMagickNative.MagickColor_Dispose
import libMagickNative.MagickColor_FuzzyEquals
import libMagickNative.MagickColor_Green_Get
import libMagickNative.MagickColor_Green_Set
import libMagickNative.MagickColor_Initialize
import libMagickNative.MagickColor_IsCMYK_Get
import libMagickNative.MagickColor_IsCMYK_Set
import libMagickNative.MagickColor_Red_Get
import libMagickNative.MagickColor_Red_Set
import libMagickNative.PixelInfo

@ExperimentalStdlibApi
@ExperimentalForeignApi
public inline fun CPointer<PixelInfo>?.toMagick(): NativeMagickColor? = this?.let { NativeMagickColor(it) }

@ExperimentalForeignApi
public inline fun CPointer<PixelInfo>.dispose(): Unit = MagickColor_Dispose(this)

@ExperimentalForeignApi
public inline fun CPointer<PixelInfo>.count(): ULong = MagickColor_Count_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<PixelInfo>.red(): QuantumType = MagickColor_Red_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<PixelInfo>.red(value: QuantumType): Unit = MagickColor_Red_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<PixelInfo>.green(): QuantumType = MagickColor_Green_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<PixelInfo>.green(value: QuantumType): Unit = MagickColor_Green_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<PixelInfo>.blue(): QuantumType = MagickColor_Blue_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<PixelInfo>.blue(value: QuantumType): Unit = MagickColor_Blue_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<PixelInfo>.alpha(): QuantumType = MagickColor_Alpha_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<PixelInfo>.alpha(value: QuantumType): Unit = MagickColor_Alpha_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<PixelInfo>.black(): QuantumType = MagickColor_Black_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<PixelInfo>.black(value: QuantumType): Unit = MagickColor_Black_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<PixelInfo>.isCMYK(): Boolean = MagickColor_IsCMYK_Get(this).toPrimitive()

@ExperimentalForeignApi
public inline fun CPointer<PixelInfo>.isCMYK(value: Boolean): Unit = MagickColor_IsCMYK_Set(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<PixelInfo>.fuzzyEquals(
    other: CPointer<PixelInfo>,
    fuzz: QuantumType,
): Boolean = MagickColor_FuzzyEquals(this, other, fuzz).toPrimitive()

@ExperimentalForeignApi
public inline fun CPointer<PixelInfo>.initialize(value: String): Boolean = MagickColor_Initialize(this, value).toPrimitive()
