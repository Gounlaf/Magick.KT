package imagemagick.settings

import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.DitherMethod
import imagemagick.magicknative.settings.NativeQuantizeSettings
import kotlinx.cinterop.ExperimentalForeignApi
import imagemagick.core.settings.QuantizeSettings as IQuantizeSettings

/**
 * Class that contains setting for quantize operations.
 */
public data class QuantizeSettings(
    override var colors: UInt = 256u,
    override var colorSpace: ColorSpace = ColorSpace.UNDEFINED,
    override var ditherMethod: DitherMethod? = DitherMethod.RIEMERSMA,
    override var measureErrors: Boolean = false,
    override var treeDepth: UInt = 0u,
) : IQuantizeSettings

@ExperimentalForeignApi
@ExperimentalStdlibApi
internal fun IQuantizeSettings.createNativeInstance(): NativeQuantizeSettings =
    NativeQuantizeSettings().also { ni ->
        ni.setColors(colors)
        ni.setColorSpace(colorSpace)
        ni.setDitherMethod(ditherMethod ?: DitherMethod.NO)
        ni.setMeasureError(measureErrors)
        ni.setTreeDepth(treeDepth)
    }
