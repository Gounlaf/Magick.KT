package imagemagick.settings

import imagemagick.QuantumType
import imagemagick.core.colors.MagickColorQuantum
import imagemagick.core.enums.ErrorMetric
import imagemagick.core.settings.CompareSettingsQuantum
import kotlinx.cinterop.ExperimentalForeignApi

/**
 * Class that contains setting for the compare operations.
 */
@ExperimentalForeignApi
public data class CompareSettings(
    override var metric: ErrorMetric = ErrorMetric.UNDEFINED,
    override var highlightColor: MagickColorQuantum<QuantumType>? = null,
    override var lowlightColor: MagickColorQuantum<QuantumType>? = null,
    override var masklightColor: MagickColorQuantum<QuantumType>? = null,
) : CompareSettingsQuantum<QuantumType>
