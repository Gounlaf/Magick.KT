package imagemagick.core.drawables

/**
 * Adjusts the current affine transformation matrix with the specified affine transformation matrix.
 * Note that the current affine transform is adjusted rather than replaced.
 */
public interface DrawableAffine {
    /**
     * Gets or sets the X coordinate scaling element.
     */
    public var scaleX: Double

    /**
     * Gets or sets the Y coordinate scaling element.
     */
    public var scaleY: Double

    /**
     * Gets or sets the X coordinate shearing element.
     */
    public var shearX: Double

    /**
     * Gets or sets the Y coordinate shearing element.
     */
    public var shearY: Double

    /**
     * Gets or sets the X coordinate of the translation element.
     */
    public var translateX: Double

    /**
     * Gets or sets the Y coordinate of the translation element.
     */
    public var translateY: Double

    /**
     * Reset to default.
     */
    public fun reset()

    /**
     * Sets the origin of coordinate system.
     *
     * @param translateX The X coordinate of the translation element.
     * @param translateY The Y coordinate of the translation element.
     */
    public fun transformOrigin(
        translateX: Double,
        translateY: Double,
    )

    /**
     * Sets the rotation to use.
     *
     * @param angle The angle of the rotation.
     */
    public fun transformRotation(angle: Double)

    /**
     * Sets the scale to use.
     *
     * @param scaleX The X coordinate scaling element.
     * @param scaleY The Y coordinate scaling element.
     */
    public fun transformScale(
        scaleX: Double,
        scaleY: Double,
    )

    /**
     * Skew to use in X axis.
     *
     * @param skewX The X skewing element.
     */
    public fun transformSkewX(skewX: Double)

    /**
     * Skew to use in Y axis.
     *
     * @param skewY The Y skewing element.
     */
    public fun transformSkewY(skewY: Double)
}
