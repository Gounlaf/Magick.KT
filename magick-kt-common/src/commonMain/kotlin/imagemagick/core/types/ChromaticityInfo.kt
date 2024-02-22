package imagemagick.core.types

import imagemagick.core.types.PrimaryInfo as IPrimaryInfo

/**
 * Chromaticity information.
 */
public interface ChromaticityInfo {
    /**
     * Gets the chromaticity blue primary point.
     */
    public val blue: IPrimaryInfo

    /**
     * Gets the chromaticity green primary point.
     */
    public val green: IPrimaryInfo

    /**
     * Gets the chromaticity red primary point.
     */
    public val red: IPrimaryInfo

    /**
     * Gets the chromaticity white primary point.
     */
    public val white: IPrimaryInfo
}
