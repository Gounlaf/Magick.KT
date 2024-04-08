@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ShortVar
import kotlinx.cinterop.toKStringFromUtf16
import kotlinx.cinterop.toKStringFromUtf8
import libMagickNative.MagickBooleanType

@ExperimentalForeignApi
public inline fun MagickBooleanType.toPrimitive(): Boolean = this == 1u

@ExperimentalForeignApi
public inline fun Boolean.toNative(): MagickBooleanType = if (this) 1u else 0u

@ExperimentalForeignApi
@Throws(IllegalStateException::class)
public inline fun CPointer<ShortVar>?.toNonNullKString(): String =
    this?.toKStringFromUtf16() ?: throw IllegalStateException("The string value should never be null.")

@ExperimentalForeignApi
@Throws(IllegalStateException::class)
public inline fun CPointer<ByteVar>?.toNonNullKString(): String =
    this?.toKStringFromUtf8() ?: throw IllegalStateException("The string value should never be null.")
