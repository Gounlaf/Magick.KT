package imagemagick.exceptions

import imagemagick.core.types.Percentage

@Throws(IllegalArgumentException::class)
public inline fun throwIfEmpty(value: String): Unit = require(value.isNotEmpty()) {
    "Value cannot be empty."
}

@Throws(IllegalArgumentException::class)
public inline fun throwIfEmpty(parameterName: String, value: String): Unit = require(value.isNotEmpty()) {
    "Value cannot be empty; parameter: $parameterName."
}

@Throws(IllegalArgumentException::class)
public inline fun throwIfEmpty(value: UByteArray): Unit = require(value.isNotEmpty()) {
    "Value cannot be empty."
}

@Throws(IllegalArgumentException::class)
public inline fun throwIfNegative(parameterName: String, value: Percentage): Unit = require(value.toDouble() >= 0.0) {
    "Value should not be negative; parameter: $parameterName."
}

@Throws(IllegalArgumentException::class)
public inline fun throwIfFalse(parameterName: String, condition: Boolean, message: String): Unit = require(condition) {
    "$message; parameter: $parameterName."
}

@Throws(IllegalArgumentException::class)
public inline fun throwIfTrue(parameterName: String, condition: Boolean, message: String): Unit = require(!condition) {
    "$message; parameter: $parameterName."
}

@Throws(IllegalArgumentException::class)
public inline fun throwIfOutOfRange(parameterName: String, index: UInt, length: UInt): Unit = require(index <= length)
