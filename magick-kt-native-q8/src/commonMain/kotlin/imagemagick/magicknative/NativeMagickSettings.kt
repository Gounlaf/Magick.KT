package imagemagick.magicknative

import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.ColorType
import imagemagick.core.enums.CompressionMethod
import imagemagick.core.enums.Endian
import imagemagick.core.enums.Interlace
import imagemagick.core.toMagick
import imagemagick.core.toNative
import imagemagick.magicknative.colors.NativeMagickColor
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
public class NativeMagickSettings : AutoCloseable {
    public val ptr: CPointer<ImageInfo> = create()

    public override fun close() {
        ptr.dispose()
    }

    public inline fun dispose(): Unit = ptr.dispose()

    public inline var antiAlias: Boolean
        get() = ptr.antiAlias()
        set(value) = ptr.antiAlias(value)

    public inline var backgroundColor: NativeMagickColor?
        get() = ptr.backgroundColor()
        set(value) = ptr.backgroundColor(value)

    public inline var colorSpace: ColorSpace
        get() = ptr.colorSpace()
        set(value) = ptr.colorSpace(value)

    public inline var colorType: ColorType
        get() = ptr.colorType()
        set(value) = ptr.colorType(value)

    public inline var compression: CompressionMethod
        get() = ptr.compression()
        set(value) = ptr.compression(value)

    public inline var debug: Boolean
        get() = ptr.debug()
        set(value) = ptr.debug(value)

    public inline var density: String?
        get() = ptr.density()
        set(value) = ptr.density(value)

    public inline var depth: UInt
        get() = ptr.depth()
        set(value) = ptr.depth(value)

    public inline var endian: Endian
        get() = ptr.endian()
        set(value) = ptr.endian(value)

    public inline var extract: String?
        get() = ptr.extract()
        set(value) = ptr.extract(value)

    public inline var format: String?
        get() = ptr.format()
        set(value) = ptr.format(value)

    public inline var fontPointsize: Double
        get() = ptr.fontPointsize()
        set(value) = ptr.fontPointsize(value)

    public inline var monochrome: Boolean
        get() = ptr.monochrome()
        set(value) = ptr.monochrome(value)

    public inline var interlace: Interlace
        get() = ptr.interlace()
        set(value) = ptr.interlace(value)

    public inline var verbose: Boolean
        get() = ptr.verbose()
        set(value) = ptr.verbose(value)

    public inline fun colorFuzz(value: Double): Unit = ptr.colorFuzz(value)

    public inline fun fileName(value: String?): Unit = ptr.fileName(value)

    public inline fun numberScenes(value: UInt): Unit = ptr.numberScenes(value)

    public inline fun option(
        key: String,
        value: String?,
    ): Unit = ptr.option(key, value)

    public inline fun page(value: String?): Unit = ptr.page(value)

    public inline fun ping(value: Boolean): Unit = ptr.ping(value)

    public inline fun quality(value: UInt): Unit = ptr.quality(value)

    public inline fun scene(value: UInt): Unit = ptr.scene(value)

    public inline fun scenes(value: String?): Unit = ptr.scenes(value)

    public inline fun size(value: String?): Unit = ptr.size(value)

    public companion object {
        @Throws(IllegalStateException::class)
        public inline fun create(): CPointer<ImageInfo> =
            MagickSettings_Create() ?: error("Failed to instantiate native MagickSettings")

        public inline fun CPointer<ImageInfo>.dispose(): Unit = MagickSettings_Dispose(this)

        public inline fun CPointer<ImageInfo>.antiAlias(): Boolean = MagickSettings_AntiAlias_Get(this).toPrimitive()

        public inline fun CPointer<ImageInfo>.antiAlias(value: Boolean): Unit =
            MagickSettings_AntiAlias_Set(this, value.toNative())

        public inline fun CPointer<ImageInfo>.backgroundColor(): NativeMagickColor? =
            MagickSettings_BackgroundColor_Get(this)?.let { NativeMagickColor(it) }

        public inline fun CPointer<ImageInfo>.backgroundColor(value: NativeMagickColor?): Unit =
            MagickSettings_BackgroundColor_Set(this, value?.ptr)

        public inline fun CPointer<ImageInfo>.colorFuzz(value: Double): Unit = MagickSettings_SetColorFuzz(this, value)

        public inline fun CPointer<ImageInfo>.colorSpace(): ColorSpace = MagickSettings_ColorSpace_Get(this).toMagick()

        public inline fun CPointer<ImageInfo>.colorSpace(value: ColorSpace): Unit =
            MagickSettings_ColorSpace_Set(this, value.toNative())

        public inline fun CPointer<ImageInfo>.colorType(): ColorType = MagickSettings_ColorType_Get(this).toMagick()

        public inline fun CPointer<ImageInfo>.colorType(value: ColorType): Unit =
            MagickSettings_ColorType_Set(this, value.toNative())

        public inline fun CPointer<ImageInfo>.compression(): CompressionMethod =
            MagickSettings_Compression_Get(this).toMagick()

        public inline fun CPointer<ImageInfo>.compression(value: CompressionMethod): Unit =
            MagickSettings_Compression_Set(this, value.toNative())

        public inline fun CPointer<ImageInfo>.debug(): Boolean = MagickSettings_Debug_Get(this).toPrimitive()

        public inline fun CPointer<ImageInfo>.debug(value: Boolean): Unit =
            MagickSettings_Debug_Set(this, value.toNative())

        public inline fun CPointer<ImageInfo>.density(): String? = MagickSettings_Density_Get(this)?.toKString()

        public inline fun CPointer<ImageInfo>.density(value: String?): Unit = MagickSettings_Density_Set(this, value)

        public inline fun CPointer<ImageInfo>.depth(): UInt = MagickSettings_Depth_Get(this).toUInt()

        public inline fun CPointer<ImageInfo>.depth(value: UInt): Unit = MagickSettings_Depth_Set(this, value.convert())

        public inline fun CPointer<ImageInfo>.endian(): Endian = MagickSettings_Endian_Get(this).toMagick()

        public inline fun CPointer<ImageInfo>.endian(value: Endian): Unit =
            MagickSettings_Endian_Set(this, value.toNative())

        public inline fun CPointer<ImageInfo>.extract(): String? = MagickSettings_Extract_Get(this)?.toKString()

        public inline fun CPointer<ImageInfo>.extract(value: String?): Unit = MagickSettings_Extract_Set(this, value)

        public inline fun CPointer<ImageInfo>.fileName(value: String?): Unit = MagickSettings_SetFileName(this, value)

        public inline fun CPointer<ImageInfo>.format(): String? = MagickSettings_Format_Get(this)?.toKString()

        public inline fun CPointer<ImageInfo>.format(value: String?): Unit = MagickSettings_Format_Set(this, value)

        public inline fun CPointer<ImageInfo>.fontPointsize(): Double = MagickSettings_FontPointsize_Get(this)

        public inline fun CPointer<ImageInfo>.fontPointsize(value: Double): Unit =
            MagickSettings_FontPointsize_Set(this, value)

        public inline fun CPointer<ImageInfo>.interlace(): Interlace = MagickSettings_Interlace_Get(this).toMagick()

        public inline fun CPointer<ImageInfo>.interlace(value: Interlace): Unit =
            MagickSettings_Interlace_Set(this, value.toNative())

        public inline fun CPointer<ImageInfo>.monochrome(): Boolean = MagickSettings_Monochrome_Get(this).toPrimitive()

        public inline fun CPointer<ImageInfo>.monochrome(value: Boolean): Unit =
            MagickSettings_Monochrome_Set(this, value.toNative())

        public inline fun CPointer<ImageInfo>.numberScenes(value: UInt): Unit =
            MagickSettings_SetNumberScenes(this, value.convert())

        public inline fun CPointer<ImageInfo>.option(
            key: String,
            value: String?,
        ): Unit = MagickSettings_SetOption(this, key, value)

        public inline fun CPointer<ImageInfo>.page(value: String?): Unit = MagickSettings_SetPage(this, value)

        public inline fun CPointer<ImageInfo>.ping(value: Boolean): Unit =
            MagickSettings_SetPing(this, value.toNative())

        public inline fun CPointer<ImageInfo>.quality(value: UInt): Unit =
            MagickSettings_SetQuality(this, value.convert())

        public inline fun CPointer<ImageInfo>.scene(value: UInt): Unit = MagickSettings_SetScene(this, value.convert())

        public inline fun CPointer<ImageInfo>.scenes(value: String?): Unit = MagickSettings_SetScenes(this, value)

        public inline fun CPointer<ImageInfo>.size(value: String?): Unit = MagickSettings_SetSize(this, value)

        public inline fun CPointer<ImageInfo>.verbose(): Boolean = MagickSettings_Verbose_Get(this).toPrimitive()

        public inline fun CPointer<ImageInfo>.verbose(value: Boolean): Unit =
            MagickSettings_Verbose_Set(this, value.toNative())
    }
}
