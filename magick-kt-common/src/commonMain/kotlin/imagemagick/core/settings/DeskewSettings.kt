package imagemagick.core.settings

import imagemagick.core.types.Percentage

/**
 * Class that contains setting for the deskew operation.
 */
public interface DeskewSettings {
    /**
     * Gets or sets a value indicating whether the image should be auto cropped after deskewing.
     */
    public var autoCrop: Boolean

    /**
     * Gets or sets the threshold.
     */
    public var threshold: Percentage
}
