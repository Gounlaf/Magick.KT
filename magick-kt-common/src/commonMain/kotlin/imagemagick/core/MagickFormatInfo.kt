package imagemagick.core

import imagemagick.core.enums.MagickFormat

interface MagickFormatInfo {
    /** Gets a value indicating whether the format can be read multithreaded. */
    val canReadMultithreaded: Boolean

    /** Gets a value indicating whether the format can be written multithreaded. */
    val canWriteMultithreaded: Boolean

    /** Gets the description of the format. */
    val description: String?

    /** Gets the format. */
    val format: MagickFormat

    /** Gets the mime type. */
    val mimeType: String?

    /** Gets the module. */
    val moduleFormat: MagickFormat

    /** Gets a value indicating whether the format supports multiple frames. */
    val supportsMultipleFrames: Boolean

    /** Gets a value indicating whether the format can be read. */
    val supportsReading: Boolean

    /** Gets a value indicating whether the format can be written. */
    val supportsWriting: Boolean

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
    fun unregister(): Boolean
}
