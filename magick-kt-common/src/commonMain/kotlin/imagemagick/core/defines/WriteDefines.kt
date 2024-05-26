package imagemagick.core.defines

import imagemagick.core.enums.MagickFormat

/**
 * Interface for defines that are used when writing an image.
 */
public interface WriteDefines : Defines {
    /**
     * Gets the format where the defines are for.
     */
    public val format: MagickFormat
}
