package imagemagick.types

import imagemagick.core.types.MagickErrorInfo as IMagickErrorInfo

/**
 * Encapsulates the error information.
 */
public class MagickErrorInfo internal constructor(
    override val meanErrorPerPixel: Double,
    override val normalizedMaximumError: Double,
    override val normalizedMeanError: Double,
) : IMagickErrorInfo {
    internal constructor() : this(0.0, 0.0, 0.0)
}
