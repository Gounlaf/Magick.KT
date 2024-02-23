package imagemagick.core

/**
 * Convert any ordinal value to enum
 *
 * @throws IllegalArgumentException
 */
public inline fun <reified T : Enum<T>> ULong.toMagick(): T =
    this.toInt().let { enum ->
        enumValues<T>().firstOrNull { it.ordinal == enum }
    } ?: throw IllegalArgumentException("Can't convert given ordinal $this to enum of type ${T::class}")

/**
 * Convert any Enum to "native" version using the ordinal property
 */
public inline fun <reified T : Enum<T>> T.toNative(): ULong = this.ordinal.toULong()
