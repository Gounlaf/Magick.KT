package imagemagick.helpers

public inline fun CharSequence?.isNotNullOrEmpty(): Boolean = !isNullOrEmpty()

public inline fun CharSequence?.isNotNullOrBlank(): Boolean = !isNullOrBlank()

@ExperimentalStdlibApi
public inline fun CharSequence.hexToUByte(format: HexFormat = HexFormat.Default): UByte = toString().hexToByte(format).toUByte()

public inline fun Boolean.toString(
    trueStr: String,
    falseStr: String,
): String = if (this) trueStr else falseStr

/**
 * In CSharp, Double.toString() don't print the decimal if number is round
 */
public inline fun Double.toString(removeDecimalIfRound: Boolean = false): String =
    toString().let {
        if (removeDecimalIfRound) it.removeSuffix(".0") else it
    }

public inline fun Double.isWhole(): Boolean = this % 1 == 0.0

public inline fun <reified T : Enum<T>> enumValueOf(
    name: String?,
    default: T,
): T =
    name.takeUnless { str -> str.isNullOrBlank() }?.let {
        try {
            enumValueOf<T>(it)
        } catch (e: IllegalArgumentException) {
            default
        }
    } ?: default
