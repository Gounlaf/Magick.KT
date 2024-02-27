package imagemagick.settings

import imagemagick.core.types.MagickGeometry
import imagemagick.core.settings.DistortSettings as IDistortSettings

/**
 * Class that contains setting for the distort operation.
 */
public data class DistortSettings(
    override var bestfit: Boolean = false,
    override var scale: Double? = null,
    override var viewport: MagickGeometry? = null,
) : IDistortSettings
