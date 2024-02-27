package imagemagick.types

import imagemagick.core.types.TypeMetric as ITypeMetric

/**
 * Used to obtain font metrics for text string given current font, pointsize, and density settings.
 */
public data class TypeMetric(
    override val ascent: Double,
    override val descent: Double,
    override val maxHorizontalAdvance: Double,
    override val textHeight: Double,
    override val textWidth: Double,
    override val underlinePosition: Double,
    override val underlineThickness: Double,
) : ITypeMetric
