package imagemagick.core

import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.CompressionMethod
import imagemagick.core.enums.Interlace
import imagemagick.core.enums.MagickFormat
import imagemagick.core.types.Density
import imagemagick.exceptions.MagickException
import okio.Path

interface MagickImageInfo {
    /** Gets the color space of the image. */
    val colorSpace: ColorSpace

    /** Gets the compression method of the image. */
    val compression: CompressionMethod

    /** Gets the density of the image. */
    val density: Density?

    /** Gets the original file name of the image (only available if read from disk). */
    val fileName: String?

    /** Gets the format of the image. */
    val format: MagickFormat

    /** Gets the height of the image. */
    val height: UInt

    /** Gets the type of interlacing. */
    val interlace: Interlace

    /** Gets the JPEG/MIFF/PNG compression level. */
    val quality: UInt

    /** Gets the width of the image. */
    val width: UInt

    /**
     * Read basic information about an image.
     *
     * @param data The byte array to read the information from.*
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    fun read(data: ByteArray)

    /**
     * Read basic information about an image.
     *
     * @param data The byte array to read the information from.
     * @param offset The offset at which to begin reading data.
     * @param count The maximum number of bytes to read.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    fun read(data: ByteArray, offset: UInt, count: UInt)

    /**
     * Read basic information about an image.
     *
     * @param file The file to read the image from.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    fun read(file: Path)

    // Doesn't exist on native; Streams are only available on JVM
    // / <summary>
    // / Read basic information about an image.
    // / </summary>
    // / <param name="stream">The stream to read the image data from.</param>
    // / <exception cref="MagickException">Thrown when an error is raised by ImageMagick.</exception>
    // void Read(Stream stream);

    /**
     * Read basic information about an image.
     *
     * @param fileName The fully qualified name of the image file, or the relative image file name.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    fun read(fileName: String)
}
