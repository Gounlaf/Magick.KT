package imagemagick.exceptions

import imagemagick.core.types.Percentage

public object Throw {
    @Throws(IllegalArgumentException::class)
    public inline fun ifEmpty(
        parameterName: String,
        value: String,
    ): Unit =
        require(value.isNotEmpty()) {
            "$parameterName: Value cannot be empty."
        }

    @Throws(IllegalArgumentException::class)
    public inline fun ifEmpty(value: String): Unit =
        require(value.isNotEmpty()) {
            "Value cannot be empty."
        }

    @Throws(IllegalArgumentException::class)
    public inline fun ifEmpty(value: ByteArray): Unit =
        require(value.isNotEmpty()) {
            "Value cannot be empty."
        }

    @Throws(IllegalArgumentException::class)
    public inline fun ifEmpty(value: UByteArray): Unit =
        require(value.isNotEmpty()) {
            "Value cannot be empty."
        }

    @Throws(IllegalArgumentException::class)
    public inline fun ifNegative(
        parameterName: String,
        value: Percentage,
    ): Unit =
        require(value.toDouble() >= 0.0) {
            "$parameterName: Value should not be negative"
        }

    @Throws(IllegalArgumentException::class)
    public inline fun ifFalse(
        parameterName: String,
        condition: Boolean,
        message: String,
    ): Unit =
        require(condition) {
            "$message; parameter: $parameterName"
        }

    @Throws(IllegalArgumentException::class)
    public inline fun ifTrue(
        parameterName: String,
        condition: Boolean,
        message: String,
    ): Unit =
        require(!condition) {
            "$parameterName: $message"
        }
}
