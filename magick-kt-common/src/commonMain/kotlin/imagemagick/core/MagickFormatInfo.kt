package imagemagick.core

import imagemagick.core.enums.MagickFormat

/**
 * Interface that contains information about an image format.
 */
public interface MagickFormatInfo {
    /** Gets a value indicating whether the format can be read multithreaded. */
    public val canReadMultithreaded: Boolean

    /** Gets a value indicating whether the format can be written multithreaded. */
    public val canWriteMultithreaded: Boolean

    /** Gets the description of the format. */
    public val description: String?

    /** Gets the format. */
    public val format: MagickFormat

    /** Gets the mime type. */
    public val mimeType: String?

    /** Gets the module. */
    public val moduleFormat: MagickFormat

    /** Gets a value indicating whether the format supports multiple frames. */
    public val supportsMultipleFrames: Boolean

    /** Gets a value indicating whether the format can be read. */
    public val supportsReading: Boolean

    /** Gets a value indicating whether the format can be written. */
    public val supportsWriting: Boolean

    /**
     * Returns a string that represents the current format.
     *
     * @return A string that represents the current format.
     */
    override fun toString(): String

    /**
     * Unregisters this format.
     *
     * @return True when the format was found and unregistered.
     */
    public fun unregister(): Boolean
}
