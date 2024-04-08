package imagemagick.core.profiles.color

import imagemagick.core.enums.ColorSpace
import imagemagick.core.profiles.ImageProfile

/**
 * Interface that describes an ICM/ICC color profile.
 */
public interface ColorProfile : ImageProfile {
    /**
     * Gets the color space of the profile.
     */
    public val colorSpace: ColorSpace

    /**
     * Gets the copyright of the profile.
     */
    public val copyright: String?

    /**
     * Gets the description of the profile.
     */
    public val description: String?

    /**
     * Gets the manufacturer of the profile.
     */
    public val manufacturer: String?

    /**
     * Gets the model of the profile.
     */
    public val model: String?
}
