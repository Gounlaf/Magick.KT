package imagemagick.core.types

import imagemagick.exceptions.throwIfTrue
import imagemagick.helpers.toString

/**
 * Struct for a point with doubles.
 *
 * @constructor Initializes a new instance of the [PointD] class.
 * @param x The x.
 * @param y The y.
 */
public data class PointD(val x: Double, val y: Double) {
    /**
     * Initializes a new instance of the [PointD] class with default value.
     */
    public constructor() : this(0.0, 0.0)

    /**
     * Initializes a new instance of the [PointD] class.
     * @param xy The x and y.
     */
    public constructor(xy: Double) : this(xy, xy)

    /**
     * Initializes a new instance of the [PointD] class.
     * @param x The x.
     * @param y The y.
     */
    public constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())

    /**
     * Initializes a new instance of the [PointD] class.
     * @param xy The x and y.
     */
    public constructor(xy: Int) : this(xy.toDouble(), xy.toDouble())

    override fun toString(): String = "${x.toString(removeDecimalIfRound = true)}x${y.toString(removeDecimalIfRound = true)}"

    public companion object {
        /**
         * Initializes a new instance of the [PointD] class.
         *
         * @param value PointD specifications in the form: &lt;x&gt;x&lt;y&gt; (where x, y are numbers).
         */
        public fun create(value: String): PointD {
            throwIfTrue("value", value.isEmpty(), "Invalid point specified.")
            val values = value.split("x")
            throwIfTrue("value", values.size > 2, "Invalid point specified.")

            val x: Double = try {
                values[0].toDouble()
            } catch (e: NumberFormatException) {
                throw IllegalArgumentException("Invalid point specified.", e)
            }

            val y: Double = if (values.size == 2) {
                try {
                    values[1].toDouble()
                } catch (e: NumberFormatException) {
                    throw IllegalArgumentException("Invalid point specified.", e)
                }
            } else {
                x
            }

            return PointD(x, y)
        }
    }
}
