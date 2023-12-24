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
    inline fun ifNegative(parameterName: String, value: Percentage) = require(value.toDouble() >= 0.0) {
        "Value should not be negative; parameter: $parameterName"
    }

    @Throws(IllegalArgumentException::class)
    inline fun ifFalse(parameterName: String, condition: Boolean, message: String) = require(condition) {
        "$message; parameter: $parameterName"
    }

    @Throws(IllegalArgumentException::class)
    inline fun ifTrue(parameterName: String, condition: Boolean, message: String) = require(!condition) {
        "$message; parameter: $parameterName"
    }
}
