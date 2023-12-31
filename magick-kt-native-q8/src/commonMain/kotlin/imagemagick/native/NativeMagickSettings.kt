package imagemagick.native

import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.ColorType
import imagemagick.core.enums.CompressionMethod
import imagemagick.core.enums.Endian
import imagemagick.core.enums.Interlace
import imagemagick.core.toEnum
import imagemagick.core.toNative
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import kotlinx.cinterop.toKString
import libMagickNative.ImageInfo
import libMagickNative.MagickSettings_AntiAlias_Get
import libMagickNative.MagickSettings_AntiAlias_Set
import libMagickNative.MagickSettings_ColorSpace_Get
import libMagickNative.MagickSettings_ColorSpace_Set
import libMagickNative.MagickSettings_ColorType_Get
import libMagickNative.MagickSettings_ColorType_Set
import libMagickNative.MagickSettings_Compression_Get
import libMagickNative.MagickSettings_Compression_Set
import libMagickNative.MagickSettings_Create
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
import libMagickNative.MagickSettings_Font_Get
import libMagickNative.MagickSettings_Font_Set
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
@ExperimentalStdlibApi
class NativeMagickSettings(
    val ptr: CPointer<ImageInfo>? = create()
) : AutoCloseable {
    override fun close() {
        ptr?.dispose()
    }

    fun dispose() = ptr?.dispose()

    fun antiAlias(): Boolean = ptr?.antiAlias() ?: false

    fun antiAlias(value: Boolean) = ptr?.antiAlias(value)

    fun backgroundColor(): Nothing = TODO()
    fun backgroundColor(value: Any): Nothing = TODO()

    fun colorSpace(): ColorSpace = ptr?.colorSpace() ?: ColorSpace.UNDEFINED
    fun colorSpace(value: ColorSpace) = ptr?.colorSpace(value)

    fun colorType(): ColorType = ptr?.colorType() ?: ColorType.UNDEFINED
    fun colorType(value: ColorType) = ptr?.colorType(value)

    fun compression(): CompressionMethod = ptr?.compression() ?: CompressionMethod.UNDEFINED

    fun compression(value: CompressionMethod) = ptr?.compression(value)

    fun debug(): Boolean = ptr?.debug() ?: false
    fun debug(value: Boolean) = ptr?.debug(value)

    fun density(): String? = ptr?.density()
    fun density(value: String?) = ptr?.density(value)

    fun depth(): UInt = ptr?.depth() ?: 0u
    fun depth(value: UInt) = ptr?.depth(value)

    fun endian(): Endian = ptr?.endian() ?: Endian.UNDEFINED
    fun endian(value: Endian) = ptr?.endian(value)

    fun extract(): String? = ptr?.extract()
    fun extract(value: String) = ptr?.extract(value)

    fun format(): String? = ptr?.format()
    fun format(value: String?) = ptr?.format(value)

    fun font(): String? = ptr?.font()
    fun font(value: String) = ptr?.font(value)

    fun fontPointsize(): Double = ptr?.fontPointsize() ?: 0.0
    fun fontPointsize(value: Double) = ptr?.fontPointsize(value)

    fun monochrome(): Boolean = ptr?.monochrome() ?: false

    fun monochrome(value: Boolean) = ptr?.monochrome(value)

    fun interlace(): Interlace = ptr?.interlace() ?: Interlace.UNDEFINED
    fun interlace(value: Interlace) = ptr?.interlace(value)

    fun verbose(): Boolean = ptr?.verbose() ?: false
    fun verbose(value: Boolean) = ptr?.verbose(value)

    fun colorFuzz(value: Double) = ptr?.colorFuzz(value)

    fun fileName(value: String?) = ptr?.fileName(value)

    fun numberScenes(value: UInt) = ptr?.numberScenes(value)

    fun option(key: String, value: String?) = ptr?.option(key, value)

    fun page(value: String?) = ptr?.page(value)

    fun ping(value: Boolean) = ptr?.ping(value)

    fun quality(value: UInt) = ptr?.quality(value)

    fun scene(value: UInt) = ptr?.scene(value)

    fun scenes(value: String?) = ptr?.scenes(value)

    fun size(value: String?) = ptr?.size(value)

    companion object {
        @Throws(IllegalStateException::class)
        inline fun create(): CPointer<ImageInfo> =
            MagickSettings_Create() ?: error("Failed to instantiate native MagickSettings")

        inline fun CPointer<ImageInfo>.dispose() = MagickSettings_Dispose(this)

        inline fun CPointer<ImageInfo>.antiAlias(): Boolean = MagickSettings_AntiAlias_Get(this).toPrimitive()

        inline fun CPointer<ImageInfo>.antiAlias(value: Boolean) =
            MagickSettings_AntiAlias_Set(this, value.toNative())

        inline fun CPointer<ImageInfo>.backgroundColor(): Nothing = TODO()
        inline fun CPointer<ImageInfo>.backgroundColor(value: Any): Nothing = TODO()

        inline fun CPointer<ImageInfo>.colorSpace(): ColorSpace = MagickSettings_ColorSpace_Get(this).toEnum()
        inline fun CPointer<ImageInfo>.colorSpace(value: ColorSpace) =
            MagickSettings_ColorSpace_Set(this, value.toNative())

        inline fun CPointer<ImageInfo>.colorType(): ColorType = MagickSettings_ColorType_Get(this).toEnum()
        inline fun CPointer<ImageInfo>.colorType(value: ColorType) =
            MagickSettings_ColorType_Set(this, value.toNative())

        inline fun CPointer<ImageInfo>.compression(): CompressionMethod =
            MagickSettings_Compression_Get(this).toEnum()

        inline fun CPointer<ImageInfo>.compression(value: CompressionMethod) =
            MagickSettings_Compression_Set(this, value.toNative())

        inline fun CPointer<ImageInfo>.debug(): Boolean = MagickSettings_Debug_Get(this).toPrimitive()
        inline fun CPointer<ImageInfo>.debug(value: Boolean) = MagickSettings_Debug_Set(this, value.toNative())

        inline fun CPointer<ImageInfo>.density(): String? = MagickSettings_Density_Get(this)?.toKString()
        inline fun CPointer<ImageInfo>.density(value: String?) = MagickSettings_Density_Set(this, value)

        inline fun CPointer<ImageInfo>.depth(): UInt = MagickSettings_Depth_Get(this).toUInt()
        inline fun CPointer<ImageInfo>.depth(value: UInt) = MagickSettings_Depth_Set(this, value.convert())

        inline fun CPointer<ImageInfo>.endian(): Endian = MagickSettings_Endian_Get(this).toEnum()
        inline fun CPointer<ImageInfo>.endian(value: Endian) =
            MagickSettings_Endian_Set(this, value.toNative())

        inline fun CPointer<ImageInfo>.extract(): String? = MagickSettings_Extract_Get(this)?.toKString()
        inline fun CPointer<ImageInfo>.extract(value: String) = MagickSettings_Extract_Set(this, value)

        inline fun CPointer<ImageInfo>.format(): String? = MagickSettings_Format_Get(this)?.toKString()
        inline fun CPointer<ImageInfo>.format(value: String?) = MagickSettings_Format_Set(this, value)

        inline fun CPointer<ImageInfo>.font(): String? = MagickSettings_Font_Get(this)?.toKString()
        inline fun CPointer<ImageInfo>.font(value: String) = MagickSettings_Font_Set(this, value)

        inline fun CPointer<ImageInfo>.fontPointsize(): Double = MagickSettings_FontPointsize_Get(this)
        inline fun CPointer<ImageInfo>.fontPointsize(value: Double) =
            MagickSettings_FontPointsize_Set(this, value)

        inline fun CPointer<ImageInfo>.monochrome(): Boolean =
            MagickSettings_Monochrome_Get(this).toPrimitive()

        inline fun CPointer<ImageInfo>.monochrome(value: Boolean) =
            MagickSettings_Monochrome_Set(this, value.toNative())

        inline fun CPointer<ImageInfo>.interlace(): Interlace = MagickSettings_Interlace_Get(this).toEnum()
        inline fun CPointer<ImageInfo>.interlace(value: Interlace) =
            MagickSettings_Interlace_Set(this, value.toNative())

        inline fun CPointer<ImageInfo>.verbose(): Boolean = MagickSettings_Verbose_Get(this).toPrimitive()
        inline fun CPointer<ImageInfo>.verbose(value: Boolean) =
            MagickSettings_Verbose_Set(this, value.toNative())

        inline fun CPointer<ImageInfo>.colorFuzz(value: Double) = MagickSettings_SetColorFuzz(this, value)

        inline fun CPointer<ImageInfo>.fileName(value: String?) = MagickSettings_SetFileName(this, value)

        inline fun CPointer<ImageInfo>.numberScenes(value: UInt) =
            MagickSettings_SetNumberScenes(this, value.convert())

        inline fun CPointer<ImageInfo>.option(key: String, value: String?) =
            MagickSettings_SetOption(this, key, value)

        inline fun CPointer<ImageInfo>.page(value: String?) = MagickSettings_SetPage(this, value)

        inline fun CPointer<ImageInfo>.ping(value: Boolean) = MagickSettings_SetPing(this, value.toNative())

        inline fun CPointer<ImageInfo>.quality(value: UInt) = MagickSettings_SetQuality(this, value.convert())

        inline fun CPointer<ImageInfo>.scene(value: UInt) = MagickSettings_SetScene(this, value.convert())

        inline fun CPointer<ImageInfo>.scenes(value: String?) = MagickSettings_SetScenes(this, value)

        inline fun CPointer<ImageInfo>.size(value: String?) = MagickSettings_SetSize(this, value)
    }
}
