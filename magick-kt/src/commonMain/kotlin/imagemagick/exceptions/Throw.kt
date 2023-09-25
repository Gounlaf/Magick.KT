package imagemagick.exceptions

internal object Throw {
    @Throws(IllegalArgumentException::class)
    inline fun ifEmpty(value: String) = require(value.isNotEmpty()) {
        "Value cannot be empty."
    }

    @Throws(IllegalArgumentException::class)
    inline fun ifEmpty(value: ByteArray) = require(value.isNotEmpty()) {
        "Value cannot be empty."
    }
}
