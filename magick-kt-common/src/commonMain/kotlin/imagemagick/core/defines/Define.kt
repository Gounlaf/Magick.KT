package imagemagick.core.defines

import imagemagick.core.enums.MagickFormat

/**
 * Interface for a define.
 */
public interface Define {
    /**
     * Gets the format to set the define for.
     */
    public val format: MagickFormat

    /**
     * Gets the name of the define.
     */
    public val name: String

    /**
     * Gets the value of the define.
     */
    public val value: String
}
