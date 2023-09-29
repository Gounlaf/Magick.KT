package imagemagick.core.types

import net.sergeych.sprintf.sprintf
import kotlin.math.roundToInt

/**
 * Represents a percentage value.
 *
 * @constructor Initializes a new instance of the [Percentage].
 *
 * @property value The value (0% = 0.0, 100% = 100.0)
 */
data class Percentage(private val value: Double = 0.0) : Comparable<Percentage> {

    /**
     * Initializes a new instance of the [Percentage].
     *
     * @param value The value (0% = 0.0, 100% = 100.0).
     */
    constructor(value: Int) : this(value.toDouble())

    companion object {
        inline fun Int.toPercentage(): Percentage = Percentage(this)
        inline fun Double.toPercentage(): Percentage = Percentage(this)
    }

    operator fun times(other: Int): Int = ((other * value) / 100.0).toInt()
    operator fun times(other: Double): Double = other * value / 100.0
    operator fun times(other: Percentage): Double = times(other.value)

    /**
     * Returns a double that represents the current percentage.
     *
     * @return A double that represents the current percentage.
     */
    fun toDouble(): Double = value

    // FIXME: according to documentation, it's equivalent to System.MidpointRounding.ToPositiveInfinity
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.math/round-to-int.html#roundtoint
    fun toInt(): Int = value.roundToInt()

    override fun compareTo(other: Percentage): Int = value.compareTo(other.value)

    // C# format: "{0:0.##}%"
    // https://github.com/dlemstra/Magick.NET/discussions/1445#discussioncomment-7111873
    override fun toString(): String = "%.2f".sprintf(value).substringBefore(".00").plus('%')
}
