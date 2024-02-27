package imagemagick.core.types

/**
 * Used to obtain font metrics for text string given current font, pointsize, and density settings.
 */
public interface TypeMetric {
    /**
     * Gets the ascent, the distance in pixels from the text baseline to the highest/upper grid coordinate
     * used to place an outline point.
     *
     */
    public val ascent: Double

    /**
     * Gets the descent, the distance in pixels from the baseline to the lowest grid coordinate used to
     * place an outline point. Always a negative value.
     *
     */
    public val descent: Double

    /**
     * Gets the maximum horizontal advance in pixels.
     */
    public val maxHorizontalAdvance: Double

    /**
     * Gets the text height in pixels.
     */
    public val textHeight: Double

    /**
     * Gets the text width in pixels.
     */
    public val textWidth: Double

    /**
     * Gets the underline position.
     */
    public val underlinePosition: Double

    /**
     * Gets the underline thickness.
     */
    public val underlineThickness: Double
}
