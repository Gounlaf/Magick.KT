package imagemagick.core.defines

import imagemagick.core.enums.MagickFormat

/**
 * Interface for a define.
 */
public interface Define {
    /**
     * Gets the format to set the define for.
     */
    val format: MagickFormat

    /**
     * Gets the name of the define.
     */
    val name: String

    /**
     * Gets the value of the define.
     */
    val value: String
}
