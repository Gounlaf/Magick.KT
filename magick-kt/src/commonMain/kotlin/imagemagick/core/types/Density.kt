package imagemagick.core.types

import imagemagick.core.enums.DensityUnit
import imagemagick.exceptions.Throw
import imagemagick.helpers.toString

/**
 * Represents the density of an image.
 *
 * @constructor Initializes a new instance of the [Density] class.
 *
 * @property x The x resolution.
 * @property y The y resolution.
 * @property units The units.
 */
data class Density(val x: Double, val y: Double, val units: DensityUnit) {
    /**
     * Initializes a new instance of the [Density] class with the density set to inches.
     *
     * @param xy The x and y.
     */
    constructor(xy: Number) : this(xy.toDouble(), xy.toDouble())

    /**
     * Initializes a new instance of the [Density] class.
     *
     * @param xy The x and y.
     * @param units The units.
     */
    constructor(xy: Number, units: DensityUnit) : this(xy.toDouble(), xy.toDouble(), units)

    /**
     * Initializes a new instance of the [Density] class with the density set to [DensityUnit.PIXELS_PER_INCH].
     *
     * @param x The x.
     * @param y The y.
     */
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble(), DensityUnit.PIXELS_PER_INCH)

    /**
     * Initializes a new instance of the [Density] class.
     *
     * @param x The x.
     * @param y The y.
     * @param units The units.
     */
    constructor(x: Number, y: Number, units: DensityUnit) : this(x.toDouble(), y.toDouble(), units)

    /**
     * Returns a string that represents the current [Density].

     * @return A string that represents the current [Density].
     */
    override fun toString(): String = toString(units)

    /**
     * Returns a string that represents the current [Density].
     *
     * @param units The units to use.
     *
     * @return A string that represents the current [Density].
     */
    fun toString(units: DensityUnit): String = when {
        this.units == units || units == DensityUnit.UNDEFINED -> Companion.toString(x, y, units)
        this.units == DensityUnit.PIXELS_PER_CENTIMETER && units == DensityUnit.PIXELS_PER_INCH -> toString(
            x * 2.54,
            y * 2.54,
            units
        )

        else -> toString(x / 2.54, y / 2.54, units)
    }

    companion object {
        private val REGEX = Regex(
            pattern = "(?<x>[0-9]*\\.[0-9]+)x(?<y>[0-9]*\\.[0-9]+)(?>\\s(?<unit>cm|inch))?",
            options = setOf(RegexOption.IGNORE_CASE)
        )

        /**
         * Initializes a new instance of the [Density] class.
         *
         * @param value Density specifications in the form: `<x>x<y>[inch/cm]` (where x, y are numbers).
         */
        @Throws(IllegalArgumentException::class)
        fun create(value: String): Density {
            Throw.ifEmpty(value)

            val density = try {
                val match = REGEX.matchEntire(value)

                match?.let {
                    val (rawX, rawY, rawUnit) = match.destructured

                    val x = rawX.toDouble()
                    val y = if (rawY.isBlank()) x else rawY.toDouble()
                    // RegexOption.IGNORE_CASE -> force lowercase
                    val units = when (rawUnit.lowercase()) {
                        "cm" -> DensityUnit.PIXELS_PER_CENTIMETER
                        "inch" -> DensityUnit.PIXELS_PER_INCH
                        else -> DensityUnit.UNDEFINED
                    }

                    Density(x, y, units)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                throw IllegalArgumentException("Invalid density specified.", e)
            }

            return density ?: throw IllegalArgumentException("Invalid density specified.")
        }

        private fun toString(x: Number, y: Number, units: DensityUnit): String =
            "${x.toDouble().toString(true)}x${y.toDouble().toString(true)}"
                .plus(
                    when (units) {
                        DensityUnit.PIXELS_PER_INCH -> " inch"
                        DensityUnit.PIXELS_PER_CENTIMETER -> " cm"
                        DensityUnit.UNDEFINED -> ""
                    }
                )
    }
}
