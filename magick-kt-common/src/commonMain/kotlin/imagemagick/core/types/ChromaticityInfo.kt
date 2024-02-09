package imagemagick.core.types

import imagemagick.core.types.PrimaryInfo as IPrimaryInfo

/**
 * Chromaticity information.
 */
interface ChromaticityInfo {
    /**
     * Gets the chromaticity blue primary point.
     */
    val blue: IPrimaryInfo

    /**
     * Gets the chromaticity green primary point.
     */
    val green: IPrimaryInfo

    /**
     * Gets the chromaticity red primary point.
     */
    val red: IPrimaryInfo

    /**
     * Gets the chromaticity white primary point.
     */
    val white: IPrimaryInfo
}
