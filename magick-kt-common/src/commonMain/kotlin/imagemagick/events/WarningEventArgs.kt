package imagemagick.events

import imagemagick.core.exceptions.warning.MagickWarningException

/**
 * Arguments for the Warning event.
 */
public data class WarningEventArgs(
    /**
     * The warning that was raised.
     */
    public val exception: MagickWarningException,
) {
    /**
     * Gets the message of the exception.
     */
    public val message: String?
        get() = exception.message
}
