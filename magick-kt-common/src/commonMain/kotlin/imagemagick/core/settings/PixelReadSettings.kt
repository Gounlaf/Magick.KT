package imagemagick.core.settings

import imagemagick.core.enums.StorageType
import imagemagick.core.settings.MagickReadSettings as IMagickReadSettings

/**
 * Class that contains setting for when pixels are read.
 */
public interface PixelReadSettings<TQuantumType> where TQuantumType : Any {
    /**
     * Gets or sets the mapping of the pixels (e.g. RGB/RGBA/ARGB).
     */
    public var mapping: String?

    /**
     * Gets or sets the pixel storage type.
     */
    public var storageType: StorageType

    /**
     * Gets the settings to use when reading the image.
     */
    public val readSettings: IMagickReadSettings<TQuantumType>
}
