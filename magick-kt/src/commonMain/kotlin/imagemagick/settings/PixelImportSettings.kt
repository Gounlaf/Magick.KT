package imagemagick.settings

import imagemagick.core.enums.PixelMapping
import imagemagick.core.enums.StorageType
import imagemagick.core.settings.PixelImportSettings as IPixelImportSettings

/**
 * Class that contains setting for when pixels are imported.
 *
 * @constructor Initializes a new instance of the [PixelImportSettings] class.
 *
 * @param x The X offset from origin.
 * @param y The Y offset from origin.
 * @param width The width.
 * @param height The height.
 * @param storageType The pixel storage type.
 * @param mapping The mapping of the pixels (e.g. RGB/RGBA/ARGB).
 */
public data class PixelImportSettings(
    override val x: Int,
    override val y: Int,
    override val height: UInt,
    override val width: UInt,
    override val storageType: StorageType,
    override var mapping: String,
) : IPixelImportSettings {
    /**
     * Initializes a new instance of the [PixelImportSettings] class.
     *
     * @param width The width.
     * @param height The height.
     * @param storageType The pixel storage type.
     * @param mapping The mapping of the pixels (e.g. RGB/RGBA/ARGB).
     */
    public constructor(width: UInt, height: UInt, storageType: StorageType, mapping: PixelMapping) : this(
        width,
        height,
        storageType,
        mapping.toString(),
    )

    /**
     * Initializes a new instance of the [PixelImportSettings] class.
     *
     * @param width The width.
     * @param height The height.
     * @param storageType The pixel storage type.
     * @param mapping The mapping of the pixels (e.g. RGB/RGBA/ARGB).
     */
    public constructor(width: UInt, height: UInt, storageType: StorageType, mapping: String) : this(
        0,
        0,
        width,
        height,
        storageType,
        mapping,
    )

    /**
     * Initializes a new instance of the [PixelImportSettings] class.
     *
     * @param x The X offset from origin.
     * @param y The Y offset from origin.
     * @param width The width.
     * @param height The height.
     * @param storageType The pixel storage type.
     * @param mapping The mapping of the pixels (e.g. RGB/RGBA/ARGB).
     */
    public constructor(
        x: Int,
        y: Int,
        width: UInt,
        height: UInt,
        storageType: StorageType,
        mapping: PixelMapping,
    ) : this(
        x,
        y,
        width,
        height,
        storageType,
        mapping.toString(),
    )
}
