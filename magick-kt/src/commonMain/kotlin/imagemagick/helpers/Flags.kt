package imagemagick.helpers

// Credits to http://www.ipreferjim.com/2017/03/prototyping-a-flagsbitmasks-implementation-in-kotlin-1-1-1/
// https://gist.github.com/jimschubert/b1437e210cdb9c411acde6654b4cdd01
// applied some modifications

data class BitMask(val value: ULong)

interface Flags {
    val bit: ULong

    fun toBitMask(): BitMask = BitMask(bit)
}

infix fun Flags.and(other: ULong): BitMask = BitMask(bit and other)
infix fun <T : Flags> Flags.or(other: T): BitMask = BitMask(bit or other.bit)

infix operator fun Flags.plus(other: Flags): BitMask = BitMask(bit or other.bit)

inline fun <reified T> enabledValues(mask: BitMask): List<T> where T : Enum<T>, T : Flags {
    return enumValues<T>().filter {
        mask hasFlag it
    }
}

infix fun BitMask.or(other: Flags): BitMask = BitMask(value or other.bit)

infix operator fun BitMask.plus(other: BitMask): BitMask = BitMask(value or other.value)
infix operator fun BitMask.plus(other: Flags): BitMask = BitMask(value or other.bit)

infix fun <T : Flags> BitMask.hasFlag(which: T): Boolean {
    // an Undefined flag is a special case.
    if (value == 0uL || (value > 0uL && which.bit == 0uL)) return false

    return value and which.bit == which.bit
}

infix fun <T : Flags> BitMask.unset(which: T): BitMask = BitMask(value xor which.bit)
