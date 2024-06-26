package imagemagick.core

import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.CompressionMethod
import imagemagick.core.enums.Interlace
import imagemagick.core.enums.MagickFormat
import imagemagick.core.exceptions.MagickException
import imagemagick.core.types.Density
import kotlinx.io.Source
import kotlinx.io.files.Path

/**
 * Interface that contains basic information about an image.
 */
public interface MagickImageInfo {
    /** Gets the color space of the image. */
    public val colorSpace: ColorSpace

    /** Gets the compression method of the image. */
    public val compression: CompressionMethod

    /** Gets the density of the image. */
    public val density: Density?

    /** Gets the original file name of the image (only available if read from disk). */
    public val fileName: String?

    /** Gets the format of the image. */
    public val format: MagickFormat

    /** Gets the height of the image. */
    public val height: UInt

    /** Gets the type of interlacing. */
    public val interlace: Interlace

    /** Gets the JPEG/MIFF/PNG compression level. */
    public val quality: UInt

    /** Gets the width of the image. */
    public val width: UInt

    /**
     * Read basic information about an image.
     *
     * @param data The byte array to read the information from.*
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun read(data: UByteArray)

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
    public fun read(
        data: UByteArray,
        offset: UInt,
        count: UInt,
    )

    /**
     * Read basic information about an image.
     *
     * @param file The file to read the image from.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun read(file: Path)

    /**
     * Read basic information about an image.
     *
     * @param stream The stream to read the image data from.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun read(stream: Source)

    /**
     * Read basic information about an image.
     *
     * @param fileName The fully qualified name of the image file, or the relative image file name.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun read(fileName: String)
}
