package imagemagick.magicknative

import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.MagickBooleanType

@ExperimentalForeignApi
public fun MagickBooleanType.toPrimitive(): Boolean = this == 1u

@ExperimentalForeignApi
public fun Boolean.toNative(): MagickBooleanType = if (this) 1u else 0u
