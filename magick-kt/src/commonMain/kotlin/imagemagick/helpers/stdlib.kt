package imagemagick.helpers

fun Boolean.toString(trueStr: String, falseStr: String) = if (this) trueStr else falseStr


/**
 * In CSharp, Double.toString() don't print the decimal if number is round
 */
fun Double.toString(removeDecimalIfRound: Boolean = false) = toString().let {
    if (removeDecimalIfRound) it.removeSuffix(".0") else it
}

fun Double.isWhole(): Boolean = this % 1 == 0.0
