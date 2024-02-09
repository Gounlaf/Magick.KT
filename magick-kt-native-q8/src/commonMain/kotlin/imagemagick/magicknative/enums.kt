package imagemagick.magicknative

import imagemagick.core.enums.AutoThresholdMethod
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.AutoThresholdMethod as NativeAutoThresholdMethod

@ExperimentalForeignApi
public fun AutoThresholdMethod.toNativeEnum(): NativeAutoThresholdMethod = when(this) {
    AutoThresholdMethod.UNDEFINED -> NativeAutoThresholdMethod.UndefinedThresholdMethod
    AutoThresholdMethod.KAPUR -> NativeAutoThresholdMethod.KapurThresholdMethod
    AutoThresholdMethod.OTSU -> NativeAutoThresholdMethod.OTSUThresholdMethod
    AutoThresholdMethod.TRIANGLE -> NativeAutoThresholdMethod.TriangleThresholdMethod
}
