package imagemagick.types

import imagemagick.core.types.PrimaryInfo
import imagemagick.core.types.ChromaticityInfo as IChromaticityInfo

/**
 * PrimaryInfo information.
 *
 * @constructor Initializes a new instance of the [ChromaticityInfo] class.
 */
public data class ChromaticityInfo(
    override val blue: PrimaryInfo,
    override val green: PrimaryInfo,
    override val red: PrimaryInfo,
    override val white: PrimaryInfo,
) : IChromaticityInfo
