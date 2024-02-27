package imagemagick.core.settings

import imagemagick.core.types.MagickGeometry as IMagickGeometry

/**
 * Class that contains setting for the distort operation.
 */
public interface DistortSettings {
    /**
     * Gets or sets a value indicating whether distort attempt to 'bestfit' the size of the resulting image.
     */
    public var bestfit: Boolean

    /**
     * Gets or sets a value to scale the size of the output canvas by this amount to provide a method of
     * Zooming, and for super-sampling the results.
     *
     */
    public var scale: Double?

    /**
     * Gets or sets the viewport that directly set the output image canvas area and offest to use for the
     * resulting image, rather than use the original images canvas, or a calculated 'bestfit' canvas.
     *
     */
    public var viewport: IMagickGeometry?
}
