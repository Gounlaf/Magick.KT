package imagemagick.core.settings

import imagemagick.core.colors.MagickColorQuantum
import imagemagick.core.enums.ErrorMetric

/**
 * Class that contains setting for the compare operations.
 *
 * @param TQuantumType The quantum type.
 */
public interface CompareSettingsQuantum<TQuantumType> where TQuantumType : Any {
    /**
     * Gets or sets the error metric to use.
     */
    public var metric: ErrorMetric

    /**
     * Gets or sets the color that emphasize pixel differences.
     */
    public var highlightColor: MagickColorQuantum<TQuantumType>?

    /**
     * Gets or sets the color that de-emphasize pixel differences.
     */
    public var lowlightColor: MagickColorQuantum<TQuantumType>?

    /**
     * Gets or sets the color of pixels that are inside the read mask.
     */
    public var masklightColor: MagickColorQuantum<TQuantumType>?
}
