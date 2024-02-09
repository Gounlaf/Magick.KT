package imagemagick.core.drawables

/**
 * Adjusts the current affine transformation matrix with the specified affine transformation matrix.
 * Note that the current affine transform is adjusted rather than replaced.
 */
interface DrawableAffine {
    /**
     * Gets or sets the X coordinate scaling element.
     */
    var scaleX: Double

    /**
     * Gets or sets the Y coordinate scaling element.
     */
    var scaleY: Double

    /**
     * Gets or sets the X coordinate shearing element.
     */
    var shearX: Double

    /**
     * Gets or sets the Y coordinate shearing element.
     */
    var shearY: Double

    /**
     * Gets or sets the X coordinate of the translation element.
     */
    var translateX: Double

    /**
     * Gets or sets the Y coordinate of the translation element.
     */
    var translateY: Double

    /**
     * Reset to default.
     */
    fun reset()

    /**
     * Sets the origin of coordinate system.
     *
     * @param translateX The X coordinate of the translation element.
     * @param translateY The Y coordinate of the translation element.
     */
    fun transformOrigin(
        translateX: Double,
        translateY: Double,
    )

    /**
     * Sets the rotation to use.
     *
     * @param angle The angle of the rotation.
     */
    fun transformRotation(angle: Double)

    /**
     * Sets the scale to use.
     *
     * @param scaleX The X coordinate scaling element.
     * @param scaleY The Y coordinate scaling element.
     */
    fun transformScale(
        scaleX: Double,
        scaleY: Double,
    )

    /**
     * Skew to use in X axis.
     *
     * @param skewX The X skewing element.
     */
    fun transformSkewX(skewX: Double)

    /**
     * Skew to use in Y axis.
     *
     * @param skewY The Y skewing element.
     */
    fun transformSkewY(skewY: Double)
}
