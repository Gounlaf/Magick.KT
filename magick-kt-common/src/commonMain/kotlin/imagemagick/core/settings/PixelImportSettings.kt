package imagemagick.core.settings

import imagemagick.core.enums.StorageType

/**
 * Class that contains setting for when pixels are imported.
 */
public interface PixelImportSettings {
    /**
     * Gets the height of the pixel area.
     */
    public val height: UInt

    /**
     * Gets the width of the pixel area.
     */
    public val width: UInt

    /**
     * Gets the X offset from origin.
     */
    public val x: Int

    /**
     * Gets the Y offset from origin.
     */
    public val y: Int

    /**
     * Gets or sets the mapping of the pixels (e.g. RGB/RGBA/ARGB).
     */
    public var mapping: String

    /**
     * Gets the pixel storage type.
     */
    public val storageType: StorageType
}
