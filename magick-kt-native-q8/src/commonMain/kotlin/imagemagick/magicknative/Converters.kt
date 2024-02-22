package imagemagick.magicknative

import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.MagickBooleanType

@ExperimentalForeignApi
public inline fun MagickBooleanType.toPrimitive(): Boolean = this == 1u

@ExperimentalForeignApi
public inline fun Boolean.toNative(): MagickBooleanType = if (this) 1u else 0u
