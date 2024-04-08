package imagemagick.core.matrices

/**
 * Encapsulates a matrix of doubles.
 */
public interface DoubleMatrix {
    /**
     * Gets the order of the matrix.
     */
    public val order: UInt

    /**
     * Get the value at the specified x/y position.
     *
     * @param x The x position.
     * @param y The y position.
     */
    public operator fun get(
        x: UInt,
        y: UInt,
    ): Double

    /**
     * Set the value at the specified x/y position.
     *
     * @param x The x position.
     * @param y The y position.
     */
    public operator fun set(
        x: UInt,
        y: UInt,
        value: Double,
    )

    /**
     * Gets the value at the specified x/y position.
     *
     * @param x The x position.
     * @param y The y position.
     * @return The value at the specified x/y position.
     */
    public fun getValue(
        x: UInt,
        y: UInt,
    ): Double = get(x, y)

    /**
     * Set the column at the specified x position.
     *
     * @param x The x position.
     * @param values The values.
     */
    public fun setColumn(
        x: UInt,
        vararg values: Double,
    )

    /**
     * Set the row at the specified y position.
     *
     * @param y The y position.
     * @param values The values.
     */
    public fun setRow(
        y: UInt,
        vararg values: Double,
    )

    /**
     * Set the value at the specified x/y position.
     *
     * @param x The x position.
     * @param y The y position.
     * @param value The value.
     */
    public fun setValue(
        x: UInt,
        y: UInt,
        value: Double,
    ): Unit = set(x, y, value)

    /**
     * Returns a string that represents the current DoubleMatrix.
     *
     * @return The double array.
     */
    public fun toArray(): DoubleArray
}
