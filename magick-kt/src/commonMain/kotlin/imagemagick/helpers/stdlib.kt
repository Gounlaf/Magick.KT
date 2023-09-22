package imagemagick.helpers

fun Boolean.toString(trueStr: String, falseStr: String) = if (this) trueStr else falseStr
