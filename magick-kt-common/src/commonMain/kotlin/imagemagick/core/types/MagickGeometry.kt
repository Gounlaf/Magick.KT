package imagemagick.core.types

interface MagickGeometry : Comparable<MagickGeometry?> {
    /**
     * Gets a value indicating whether the value is an aspect ratio.
     */
    val aspectRatio: Boolean

    /**
     * Gets or sets a value indicating whether the image is resized based on the smallest fitting dimension (^).
     */
    var fillArea: Boolean

    /**
     * Gets or sets a value indicating whether the image is resized if image is greater than size (&gt;).
     */
    var greater: Boolean

    /**
     * Gets or sets the height of the geometry.
     */
    var height: UInt

    /**
     * Gets or sets a value indicating whether the image is resized without preserving aspect ratio (!).
     */
    var ignoreAspectRatio: Boolean

    /**
     * Gets or sets a value indicating whether the width and height are expressed as percentages.
     */
    var isPercentage: Boolean

    /**
     * Gets or sets a value indicating whether the image is resized if the image is less than size (&lt;).
     */
    var less: Boolean

    /**
     * Gets or sets a value indicating whether the image is resized using a pixel area count limit (@).
     */
    var limitPixels: Boolean

    /**
     * Gets or sets the width of the geometry.
     */
    var width: UInt

    /**
     * Gets or sets the X offset from origin.
     */
    var x: Int

    /**
     * Gets or sets the Y offset from origin.
     */
    var y: Int

    /**
     * Initializes the geometry using the specified value.
     *
     * @param x The X offset from origin.
     * @param y The Y offset from origin.
     * @param width The width.
     * @param height The height.
     */
    fun initialize(
        x: Int,
        y: Int,
        width: UInt,
        height: UInt,
    )

    /**
     * Returns a string that represents the current [MagickGeometry].
     *
     * @return A string that represents the current [MagickGeometry].
     */
    override fun toString(): String
}
