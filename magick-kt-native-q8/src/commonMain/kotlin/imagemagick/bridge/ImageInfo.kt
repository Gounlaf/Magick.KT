@file:Suppress("KDocMissingDocumentation")

package imagemagick.bridge

import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.ColorType
import imagemagick.core.enums.CompressionMethod
import imagemagick.core.enums.Endian
import imagemagick.core.enums.Interlace
import imagemagick.core.toMagick
import imagemagick.core.toNative
import imagemagick.magicknative.colors.NativeMagickColor
import imagemagick.magicknative.toNative
import imagemagick.magicknative.toPrimitive
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import kotlinx.cinterop.toKString
import libMagickNative.ImageInfo
import libMagickNative.MagickSettings_AntiAlias_Get
import libMagickNative.MagickSettings_AntiAlias_Set
import libMagickNative.MagickSettings_BackgroundColor_Get
import libMagickNative.MagickSettings_BackgroundColor_Set
import libMagickNative.MagickSettings_ColorSpace_Get
import libMagickNative.MagickSettings_ColorSpace_Set
import libMagickNative.MagickSettings_ColorType_Get
import libMagickNative.MagickSettings_ColorType_Set
import libMagickNative.MagickSettings_Compression_Get
import libMagickNative.MagickSettings_Compression_Set
import libMagickNative.MagickSettings_Debug_Get
import libMagickNative.MagickSettings_Debug_Set
import libMagickNative.MagickSettings_Density_Get
import libMagickNative.MagickSettings_Density_Set
import libMagickNative.MagickSettings_Depth_Get
import libMagickNative.MagickSettings_Depth_Set
import libMagickNative.MagickSettings_Dispose
import libMagickNative.MagickSettings_Endian_Get
import libMagickNative.MagickSettings_Endian_Set
import libMagickNative.MagickSettings_Extract_Get
import libMagickNative.MagickSettings_Extract_Set
import libMagickNative.MagickSettings_FontPointsize_Get
import libMagickNative.MagickSettings_FontPointsize_Set
import libMagickNative.MagickSettings_Format_Get
import libMagickNative.MagickSettings_Format_Set
import libMagickNative.MagickSettings_Interlace_Get
import libMagickNative.MagickSettings_Interlace_Set
import libMagickNative.MagickSettings_Monochrome_Get
import libMagickNative.MagickSettings_Monochrome_Set
import libMagickNative.MagickSettings_SetColorFuzz
import libMagickNative.MagickSettings_SetFileName
import libMagickNative.MagickSettings_SetNumberScenes
import libMagickNative.MagickSettings_SetOption
import libMagickNative.MagickSettings_SetPage
import libMagickNative.MagickSettings_SetPing
import libMagickNative.MagickSettings_SetQuality
import libMagickNative.MagickSettings_SetScene
import libMagickNative.MagickSettings_SetScenes
import libMagickNative.MagickSettings_SetSize
import libMagickNative.MagickSettings_Verbose_Get
import libMagickNative.MagickSettings_Verbose_Set

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.dispose(): Unit = MagickSettings_Dispose(this)

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.antiAlias(): Boolean = MagickSettings_AntiAlias_Get(this).toPrimitive()

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.antiAlias(value: Boolean): Unit =
    MagickSettings_AntiAlias_Set(this, value.toNative())

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<ImageInfo>.backgroundColor(): NativeMagickColor? =
    MagickSettings_BackgroundColor_Get(this)?.let { NativeMagickColor(it) }

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<ImageInfo>.backgroundColor(value: NativeMagickColor?): Unit =
    MagickSettings_BackgroundColor_Set(this, value?.ptr)

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.colorFuzz(value: Double): Unit = MagickSettings_SetColorFuzz(this, value)

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.colorSpace(): ColorSpace = MagickSettings_ColorSpace_Get(this).toMagick()

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.colorSpace(value: ColorSpace): Unit =
    MagickSettings_ColorSpace_Set(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.colorType(): ColorType = MagickSettings_ColorType_Get(this).toMagick()

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.colorType(value: ColorType): Unit =
    MagickSettings_ColorType_Set(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.compression(): CompressionMethod = MagickSettings_Compression_Get(this).toMagick()

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.compression(value: CompressionMethod): Unit =
    MagickSettings_Compression_Set(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.debug(): Boolean = MagickSettings_Debug_Get(this).toPrimitive()

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.debug(value: Boolean): Unit = MagickSettings_Debug_Set(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.density(): String? = MagickSettings_Density_Get(this)?.toKString()

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.density(value: String?): Unit = MagickSettings_Density_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.depth(): UInt = MagickSettings_Depth_Get(this).toUInt()

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.depth(value: UInt): Unit = MagickSettings_Depth_Set(this, value.convert())

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.endian(): Endian = MagickSettings_Endian_Get(this).toMagick()

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.endian(value: Endian): Unit = MagickSettings_Endian_Set(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.extract(): String? = MagickSettings_Extract_Get(this)?.toKString()

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.extract(value: String?): Unit = MagickSettings_Extract_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.fileName(value: String?): Unit = MagickSettings_SetFileName(this, value)

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.format(): String? = MagickSettings_Format_Get(this)?.toKString()

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.format(value: String?): Unit = MagickSettings_Format_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.fontPointsize(): Double = MagickSettings_FontPointsize_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.fontPointsize(value: Double): Unit = MagickSettings_FontPointsize_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.interlace(): Interlace = MagickSettings_Interlace_Get(this).toMagick()

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.interlace(value: Interlace): Unit =
    MagickSettings_Interlace_Set(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.monochrome(): Boolean = MagickSettings_Monochrome_Get(this).toPrimitive()

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.monochrome(value: Boolean): Unit =
    MagickSettings_Monochrome_Set(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.numberScenes(value: UInt): Unit =
    MagickSettings_SetNumberScenes(this, value.convert())

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.option(key: String, value: String?): Unit =
    MagickSettings_SetOption(this, key, value)

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.page(value: String?): Unit = MagickSettings_SetPage(this, value)

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.ping(value: Boolean): Unit = MagickSettings_SetPing(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.quality(value: UInt): Unit = MagickSettings_SetQuality(this, value.convert())

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.scene(value: UInt): Unit = MagickSettings_SetScene(this, value.convert())

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.scenes(value: String?): Unit = MagickSettings_SetScenes(this, value)

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.size(value: String?): Unit = MagickSettings_SetSize(this, value)

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.verbose(): Boolean = MagickSettings_Verbose_Get(this).toPrimitive()

@ExperimentalForeignApi
public inline fun CPointer<ImageInfo>.verbose(value: Boolean): Unit = MagickSettings_Verbose_Set(this, value.toNative())
