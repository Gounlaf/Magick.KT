package imagemagick.native


import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.MagickBooleanType

@ExperimentalForeignApi
fun MagickBooleanType.toPrimitive(): Boolean = this == 1u

@ExperimentalForeignApi
fun Boolean.toNative(): MagickBooleanType = if (this) 1u else 0u
