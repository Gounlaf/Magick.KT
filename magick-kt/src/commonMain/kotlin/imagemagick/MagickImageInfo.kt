package imagemagick

import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.CompressionMethod
import imagemagick.core.enums.Interlace
import imagemagick.core.enums.MagickFormat
import imagemagick.core.types.Density
import okio.Path
import imagemagick.core.MagickImageInfo as Interface

class MagickImageInfo : Interface {
    override val colorSpace: ColorSpace
        get() = TODO("Not yet implemented")
    override val compression: CompressionMethod
        get() = TODO("Not yet implemented")
    override val density: Density?
        get() = TODO("Not yet implemented")
    override val fileName: String?
        get() = TODO("Not yet implemented")
    override val format: MagickFormat
        get() = TODO("Not yet implemented")
    override val height: UInt
        get() = TODO("Not yet implemented")
    override val interlace: Interlace
        get() = TODO("Not yet implemented")
    override val quality: UInt
        get() = TODO("Not yet implemented")
    override val width: UInt
        get() = TODO("Not yet implemented")

    override fun read(data: ByteArray) {
        TODO("Not yet implemented")
    }

    override fun read(data: ByteArray, offset: UInt, count: UInt) {
        TODO("Not yet implemented")
    }

    override fun read(file: Path) {
        TODO("Not yet implemented")
    }

    override fun read(fileName: String) {
        TODO("Not yet implemented")
    }
}
