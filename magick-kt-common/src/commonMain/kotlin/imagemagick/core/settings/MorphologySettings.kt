package imagemagick.core.settings

import imagemagick.core.enums.Channels
import imagemagick.core.enums.Kernel
import imagemagick.core.enums.MorphologyMethod
import imagemagick.core.types.MagickGeometry
import imagemagick.core.types.Percentage

/**
 * Class that contains setting for the morphology operation.
 */
public interface MorphologySettings {
    /**
     * Gets or sets the channels to apply the kernel to.
     */
    public var channels: Channels

    /**
     * Gets or sets the bias to use when the method is Convolve.
     */
    public var convolveBias: Percentage?

    /**
     * Gets or sets the scale to use when the method is Convolve.
     */
    public var convolveScale: MagickGeometry?

    /**
     * Gets or sets the number of iterations.
     */
    public var iterations: Int

    /**
     * Gets or sets built-in kernel.
     */
    public var kernel: Kernel

    /**
     * Gets or sets kernel arguments.
     */
    public var kernelArguments: String

    /**
     * Gets or sets the morphology method.
     */
    public var method: MorphologyMethod

    /**
     * Gets or sets user supplied kernel.
     */
    public var userKernel: String?
}
