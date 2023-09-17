package imagemagick.core

import libMagickNative.MagickBooleanType

fun MagickBooleanType.toBoolean(): Boolean = this.toInt() == 1
