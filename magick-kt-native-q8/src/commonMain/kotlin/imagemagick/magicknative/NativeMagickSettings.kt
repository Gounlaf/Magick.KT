@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative

import imagemagick.bridge.antiAlias
import imagemagick.bridge.backgroundColor
import imagemagick.bridge.colorFuzz
import imagemagick.bridge.colorSpace
import imagemagick.bridge.colorType
import imagemagick.bridge.compression
import imagemagick.bridge.debug
import imagemagick.bridge.density
import imagemagick.bridge.depth
import imagemagick.bridge.dispose
import imagemagick.bridge.endian
import imagemagick.bridge.extract
import imagemagick.bridge.fileName
import imagemagick.bridge.fontPointsize
import imagemagick.bridge.format
import imagemagick.bridge.interlace
import imagemagick.bridge.monochrome
import imagemagick.bridge.numberScenes
import imagemagick.bridge.option
import imagemagick.bridge.page
import imagemagick.bridge.ping
import imagemagick.bridge.quality
import imagemagick.bridge.scene
import imagemagick.bridge.scenes
import imagemagick.bridge.size
import imagemagick.bridge.verbose
import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.ColorType
import imagemagick.core.enums.CompressionMethod
import imagemagick.core.enums.Endian
import imagemagick.core.enums.Interlace
import imagemagick.magicknative.colors.NativeMagickColor
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.ImageInfo
import libMagickNative.MagickSettings_Create

@ExperimentalForeignApi
@ExperimentalStdlibApi
public class NativeMagickSettings : AutoCloseable {
    public val ptr: CPointer<ImageInfo> =
        MagickSettings_Create() ?: error("Failed to instantiate native MagickSettings")

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

    public inline fun option(key: String, value: String?): Unit = ptr.option(key, value)

    public inline fun page(value: String?): Unit = ptr.page(value)

    public inline fun ping(value: Boolean): Unit = ptr.ping(value)

    public inline fun quality(value: UInt): Unit = ptr.quality(value)

    public inline fun scene(value: UInt): Unit = ptr.scene(value)

    public inline fun scenes(value: String?): Unit = ptr.scenes(value)

    public inline fun size(value: String?): Unit = ptr.size(value)
}
