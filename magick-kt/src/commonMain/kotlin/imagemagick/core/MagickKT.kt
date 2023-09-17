package imagemagick.core

interface MagickKT {
    /** * Gets the ImageMagick features. */
    val features: String

    /** Gets the information about the supported formats. */
    val supportedFormats: List<MagickFormatInfo>
}
