package imagemagick.core

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import platform.posix.size_t

inline fun <reified T : Enum<T>> size_t.toEnum(): T = this.toInt().let { enum ->
    enumValues<T>().firstOrNull { it.ordinal == enum }
} ?: throw IllegalArgumentException("Can't convert given ordinal $this to enum of type ${T::class}")

@ExperimentalForeignApi
inline fun <reified T : Enum<T>> T.toNative(): size_t = this.ordinal.convert()
