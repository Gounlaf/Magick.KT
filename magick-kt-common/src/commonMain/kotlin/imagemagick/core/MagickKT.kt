package imagemagick.core

public interface MagickKT {
    /** * Gets the ImageMagick features. */
    public val features: String

    /** Gets the information about the supported formats. */
    public val supportedFormats: List<MagickFormatInfo>
}
