package imagemagick.core.types

import kotlin.math.roundToInt

/**
 * Represents a percentage value.
 *
 * @constructor Initializes a new instance of the [Percentage].
 *
 * @property value The value (0% = 0.0, 100% = 100.0)
 */
public data class Percentage(private val value: Double = 0.0) : Comparable<Percentage> {

    /**
     * Initializes a new instance of the [Percentage].
     *
     * @param value The value (0% = 0.0, 100% = 100.0).
     */
    public constructor(value: Int) : this(value.toDouble())

    public constructor(value: UInt) : this(value.toDouble())

    public companion object {
        public inline fun Int.percent(): Percentage = Percentage(this)
        public inline fun UInt.percent(): Percentage = Percentage(this)
        public inline fun Double.percent(): Percentage = Percentage(this)
    }

    public operator fun times(other: Int): Int = ((other * value) / 100.0).toInt()
    public operator fun times(other: UInt): UInt = ((other.toDouble() * value) / 100.0).toUInt()
    public operator fun times(other: Double): Double = other * value / 100.0
    public operator fun times(other: Percentage): Double = times(other.value)

    /**
     * Returns a double that represents the current percentage.
     *
     * @return A double that represents the current percentage.
     */
    public fun toDouble(): Double = value

    // FIXME: according to documentation, it's equivalent to System.MidpointRounding.ToPositiveInfinity
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.math/round-to-int.html#roundtoint
    public fun toInt(): Int = value.roundToInt()
    public fun toUInt(): UInt = value.roundToInt().toUInt()

    override fun compareTo(other: Percentage): Int = value.compareTo(other.value)

    // C# format: "{0:0.##}%"
    // https://github.com/dlemstra/Magick.NET/discussions/1445#discussioncomment-7111873
    override fun toString(): String = value.toString().substringBefore(".0").plus('%')
}
