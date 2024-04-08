@file:Suppress("KDocMissingDocumentation")

package imagemagick.exceptions

import imagemagick.core.types.Percentage

@Throws(IllegalArgumentException::class)
public inline fun throwIfEmpty(value: String): Unit =
    require(value.isNotEmpty()) {
        "Value cannot be empty."
    }

@Throws(IllegalArgumentException::class)
public inline fun throwIfEmpty(
    parameterName: String,
    value: String,
): Unit =
    require(value.isNotEmpty()) {
        "Value cannot be empty; parameter: $parameterName."
    }

@Throws(IllegalArgumentException::class)
public inline fun throwIfEmpty(
    parameterName: String,
    value: DoubleArray,
): Unit =
    require(value.isNotEmpty()) {
        "Value cannot be empty; parameter: $parameterName."
    }

@Throws(IllegalArgumentException::class)
public inline fun throwIfEmpty(value: UByteArray): Unit =
    require(value.isNotEmpty()) {
        "Value cannot be empty."
    }

@Throws(IllegalArgumentException::class)
public inline fun throwIfNegative(
    parameterName: String,
    value: Percentage,
): Unit =
    require(value.toDouble() >= 0.0) {
        "Value should not be negative; parameter: $parameterName."
    }

@Throws(IllegalArgumentException::class)
public inline fun throwIfNegative(
    parameterName: String,
    value: UInt,
): Unit =
    require(value >= 0u) {
        "Value should not be negative; parameter: $parameterName."
    }

@Throws(IllegalArgumentException::class)
public inline fun throwIfNegative(
    parameterName: String,
    value: Int,
): Unit =
    require(value >= 0) {
        "Value should not be negative; parameter: $parameterName."
    }

@Throws(IllegalArgumentException::class)
public inline fun throwIfNegative(
    parameterName: String,
    value: ULong,
): Unit =
    require(value >= 0u) {
        "Value should not be negative; parameter: $parameterName."
    }

@Throws(IllegalArgumentException::class)
public inline fun throwIfNegative(
    parameterName: String,
    value: Long,
): Unit =
    require(value >= 0) {
        "Value should not be negative; parameter: $parameterName."
    }

@Throws(IllegalArgumentException::class)
public inline fun throwIfNegative(
    parameterName: String,
    value: Double,
): Unit =
    require(value >= 0.0) {
        "Value should not be negative; parameter: $parameterName."
    }

@Throws(IllegalArgumentException::class)
public inline fun throwIfFalse(
    parameterName: String,
    condition: Boolean,
    message: String,
): Unit =
    require(condition) {
        "$message; parameter: $parameterName."
    }

@Throws(IllegalArgumentException::class)
public inline fun throwIfTrue(
    parameterName: String,
    condition: Boolean,
    message: String,
): Unit =
    require(!condition) {
        "$message; parameter: $parameterName."
    }

@Throws(IllegalArgumentException::class)
public inline fun throwIfOutOfRange(
    parameterName: String,
    index: UInt,
    length: UInt,
): Unit = require(index <= length)

@Throws(IllegalArgumentException::class)
public inline fun throwIfOutOfRange(
    parameterName: String,
    value: Double,
    range: ClosedFloatingPointRange<Double>,
): Unit = require(value in range)
