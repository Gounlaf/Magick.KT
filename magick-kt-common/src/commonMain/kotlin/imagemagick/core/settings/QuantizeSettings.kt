package imagemagick.core.settings

import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.DitherMethod

/**
 * Class that contains setting for quantize operations.
 */
public interface QuantizeSettings {
    /**
     * Gets or sets the maximum number of colors to quantize to.
     */
    public var colors: UInt

    /**
     * Gets or sets the colorspace to quantize in.
     */
    public var colorSpace: ColorSpace

    /**
     * Gets or sets the dither method to use.
     */
    public var ditherMethod: DitherMethod?

    /**
     * Gets or sets a value indicating whether errors should be measured.
     */
    public var measureErrors: Boolean

    /**
     * Gets or sets the quantization tree-depth.
     */
    public var treeDepth: UInt
}
