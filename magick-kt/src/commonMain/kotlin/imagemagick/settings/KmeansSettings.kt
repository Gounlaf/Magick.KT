package imagemagick.settings

import imagemagick.core.settings.KmeansSettings as IKmeansSettings

/**
 * Class that contains setting for the kmeans operation.
 */
public data class KmeansSettings(
    override var seedColors: String?,
    override var numberColors: UInt,
    override var maxIterations: UInt = 100u,
    override var tolerance: Double = 0.01,
) : IKmeansSettings
