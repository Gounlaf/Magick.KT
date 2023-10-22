package imagemagick.core.settings

import imagemagick.core.types.MagickGeometry
import kotlinx.cinterop.ExperimentalForeignApi

/**
 * Class that contains setting for when an image is being read.
 */
@ExperimentalForeignApi
@ExperimentalStdlibApi
interface MagickReadSettings : MagickSettings {
    /** Gets or sets the defines that should be set before the image is read. */
//    var defines: IReadDefines?

    /** Gets or sets the specified area to extract from the image. */
    var extractArea: MagickGeometry

    /** Gets or sets the index of the image to read from a multi layer/frame image. */
    var frameIndex: UInt?
//
    /** Gets or sets the number of images to read from a multi layer/frame image. */
    var frameCount: UInt?

    /** Gets or sets the height. */
    var height: UInt?

    /**
     * Gets or sets a value indicating whether the exif profile should be used to update some of the
     * properties of the image (e.g. <see cref="IMagickImage.Density"/>, <see cref="IMagickImage.Orientation"/>).
     */
    var syncImageWithExifProfile: Boolean

    /**
     * Gets or sets a value indicating whether the monochrome reader shoul be used. This is
     * supported by: PCL, PDF, PS and XPS.
     */
    var useMonochrome: Boolean

    /** Gets or sets the width. */
    var width: UInt?
}
