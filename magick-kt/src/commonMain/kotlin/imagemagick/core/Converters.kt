package imagemagick.core

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import libMagickNative.MagickBooleanType
import platform.posix.size_t

@OptIn(ExperimentalForeignApi::class)
fun MagickBooleanType.toPrimitive(): Boolean = this == 1u

@OptIn(ExperimentalForeignApi::class)
fun Boolean.toNative(): MagickBooleanType = if (this) 1u else 0u

inline fun <reified T : Enum<T>> size_t.toEnum(): T = this.toInt().let { enum ->
    enumValues<T>().firstOrNull { it.ordinal == enum }
} ?: throw IllegalArgumentException("Can't convert given ordinal $this to enum of type ${T::class}")

@ExperimentalForeignApi
inline fun <reified T : Enum<T>> T.toNative(): size_t = this.ordinal.convert()