package imagemagick.helpers

inline fun CharSequence?.isNotNullOrEmpty(): Boolean = !isNullOrEmpty()
inline fun CharSequence?.isNotNullOrBlank(): Boolean = !isNullOrBlank()

inline fun Boolean.toString(trueStr: String, falseStr: String) = if (this) trueStr else falseStr

/**
 * In CSharp, Double.toString() don't print the decimal if number is round
 */
inline fun Double.toString(removeDecimalIfRound: Boolean = false) = toString().let {
    if (removeDecimalIfRound) it.removeSuffix(".0") else it
}

inline fun Double.isWhole(): Boolean = this % 1 == 0.0
