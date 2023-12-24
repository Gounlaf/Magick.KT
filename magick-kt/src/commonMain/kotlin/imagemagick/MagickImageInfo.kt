package imagemagick

import imagemagick.core.MagickImageQuantum
import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.CompressionMethod
import imagemagick.core.enums.Interlace
import imagemagick.core.enums.MagickFormat
import imagemagick.core.types.Density
import imagemagick.exceptions.MagickException
import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path
import okio.Source
import imagemagick.core.MagickImageInfo as IMagickImageInfo

/**
 * Class that contains basic information about an image.
 *
 * @constructor Initializes a new instance of the [MagickImageInfo] class.
 */
class MagickImageInfo() : IMagickImageInfo {
    override lateinit var colorSpace: ColorSpace
        private set
    override lateinit var compression: CompressionMethod
        private set
    override var density: Density? = null
        private set
    override var fileName: String? = null
        private set
    override lateinit var format: MagickFormat
        private set
    override var height: UInt = 0u
        private set
    override lateinit var interlace: Interlace
        private set
    override var quality: UInt = 0u
        private set
    override var width: UInt = 0u
        private set

    /**
     * Initializes a new instance of the [MagickImageInfo] class.
     *
     * @param data The byte array to read the information from.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    constructor(data: ByteArray) : this() {
        read(data)
    }

    /**
     * Initializes a new instance of the [MagickImageInfo] class.
     *
     * @param data The byte array to read the information from.
     * @param offset The offset at which to begin reading data.
     * @param count The maximum number of bytes to read.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    constructor(data: ByteArray, offset: UInt, count: UInt) : this() {
        read(data, offset, count)
    }

    /**
     * Initializes a new instance of the [MagickImageInfo] class.
     *
     * @param file The file to read the image from.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    constructor(file: Path) : this() {
        read(file)
    }

    /**
     * Initializes a new instance of the [MagickImageInfo] class.
     *
     * @param stream The stream to read the image data from.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    constructor(stream: Source) : this() {
        read(stream)
    }

    /**
     * Initializes a new instance of the [MagickImageInfo] class.
     *
     * @param fileName The fully qualified name of the image file, or the relative image file name.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    constructor(fileName: String) : this() {
        read(fileName)
    }

    /**
     * Read basic information about an image.
     *
     * @param data The byte array to read the information from.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @OptIn(ExperimentalStdlibApi::class, ExperimentalForeignApi::class)
    @Throws(MagickException::class)
    override fun read(data: ByteArray) {
        MagickImage().use {
            it.ping(data)
            initialize(it)
        }
    }

    /**
     * Read basic information about an image.
     *
     * @param data The byte array to read the information from.
     * @param offset The offset at which to begin reading data.
     * @param count The maximum number of bytes to read.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @OptIn(ExperimentalStdlibApi::class, ExperimentalForeignApi::class)
    @Throws(MagickException::class)
    override fun read(data: ByteArray, offset: UInt, count: UInt) {
        MagickImage().use {
            it.ping(data)
            initialize(it)
        }
    }

    /**
     * Read basic information about an image.
     *
     * @param file The file to read the image from.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @OptIn(ExperimentalStdlibApi::class, ExperimentalForeignApi::class)
    @Throws(MagickException::class)
    override fun read(file: Path) {
        MagickImage().use {
            it.ping(file)
            initialize(it)
        }
    }

    /**
     * Read basic information about an image.
     *
     * @param stream The stream to read the image data from.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @OptIn(ExperimentalStdlibApi::class, ExperimentalForeignApi::class)
    @Throws(MagickException::class)
    override fun read(stream: Source) {
        MagickImage().use {
            it.ping(stream)
            initialize(it)
        }
    }

    /**
     * Read basic information about an image.
     *
     * @param fileName The fully qualified name of the image file, or the relative image file name.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @OptIn(ExperimentalStdlibApi::class, ExperimentalForeignApi::class)
    @Throws(MagickException::class)
    override fun read(fileName: String) {
        MagickImage().use {
            it.ping(fileName)
            initialize(it)
        }
    }

    private fun initialize(image: MagickImageQuantum<QuantumType>) {
        colorSpace = image.colorSpace
        compression = image.compression
        density = image.density
        fileName = image.fileName
        format = image.format
        height = image.height
        interlace = image.interlace
        quality = image.quality
        width = image.width
    }
}
