package imagemagick.core

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import platform.posix.size_t

public inline fun <reified T : Enum<T>> size_t.toMagick(): T =
    this.toInt().let { enum ->
        enumValues<T>().firstOrNull { it.ordinal == enum }
    } ?: throw IllegalArgumentException("Can't convert given ordinal $this to enum of type ${T::class}")

@ExperimentalForeignApi
public inline fun <reified T : Enum<T>> T.toNative(): size_t = this.ordinal.convert()
