package imagemagick.exceptions

import imagemagick.core.types.Percentage

internal object Throw {
    @Throws(IllegalArgumentException::class)
    inline fun ifEmpty(value: String) = require(value.isNotEmpty()) {
        "Value cannot be empty."
    }

    @Throws(IllegalArgumentException::class)
    inline fun ifEmpty(value: ByteArray) = require(value.isNotEmpty()) {
        "Value cannot be empty."
    }

    @Throws(IllegalArgumentException::class)
    inline fun ifNegative(value: Percentage) = require(value.toDouble() <= 0.0) {
        "Value should not be negative."
    }
}
