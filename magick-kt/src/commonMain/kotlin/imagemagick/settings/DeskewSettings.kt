package imagemagick.settings

import imagemagick.core.types.Percentage
import imagemagick.core.settings.DeskewSettings as IDeskewSettings

/**
 * Class that contains setting for the deskew operation.
 */
public data class DeskewSettings(
    override var autoCrop: Boolean = false,
    override var threshold: Percentage = Percentage()
) : IDeskewSettings
