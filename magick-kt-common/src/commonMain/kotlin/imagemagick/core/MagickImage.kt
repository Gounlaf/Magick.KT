package imagemagick.core

import imagemagick.core.enums.AlphaOption
import imagemagick.core.enums.AutoThresholdMethod
import imagemagick.core.enums.Channels
import imagemagick.core.enums.ClassType
import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.ColorType
import imagemagick.core.enums.CompositeOperator
import imagemagick.core.enums.CompressionMethod
import imagemagick.core.enums.Endian
import imagemagick.core.enums.ErrorMetric
import imagemagick.core.enums.FilterType
import imagemagick.core.enums.GifDisposeMethod
import imagemagick.core.enums.Gravity
import imagemagick.core.enums.Interlace
import imagemagick.core.enums.MagickFormat
import imagemagick.core.enums.NoiseType
import imagemagick.core.enums.OrientationType
import imagemagick.core.enums.PixelChannel
import imagemagick.core.enums.PixelInterpolateMethod
import imagemagick.core.enums.RenderingIntent
import imagemagick.core.enums.VirtualPixelMethod
import imagemagick.core.exceptions.MagickException
import imagemagick.core.types.Density
import imagemagick.core.types.Percentage
import imagemagick.core.types.Percentage.Companion.percent
import kotlin.math.sqrt
import okio.Path
import okio.Source
import imagemagick.core.drawables.DrawableAffine as IDrawableAffine
import imagemagick.core.matrices.MagickColorMatrix as IMagickColorMatrix
import imagemagick.core.types.ChromaticityInfo as IChromaticityInfo
import imagemagick.core.types.MagickErrorInfo as IMagickErrorInfo
import imagemagick.core.types.MagickGeometry as IMagickGeometry

/**
 * Interface that represents an ImageMagick image.
 */
public interface MagickImage {
//    /**
//     * Event that will be raised when progress is reported by this image.
//     */
//    event EventHandler<ProgressEventArgs> Progress;
//
//    /**
//     * Event that will we raised when a warning is thrown by ImageMagick.
//     */
//    event EventHandler<WarningEventArgs> Warning;
//
    /**
     * Gets or sets the time in 1/100ths of a second which must expire before splaying the next image in an
     * animated sequence.
     */
    public var animationDelay: UInt

    /**
     * Gets or sets the number of iterations to loop an animation (e.g. Netscape loop extension) for.
     */
    public var animationIterations: UInt

    /**
     * Gets or sets the ticks per seconds for the animation delay.
     */
    public var animationTicksPerSecond: UInt

    /**
     * Gets the names of the artifacts.
     */
    public val artifactNames: Sequence<String>

    /**
     * Gets the names of the attributes.
     */
    public val attributeNames: Sequence<String>

    /**
     * Gets the height of the image before transformations.
     */
    public val baseHeight: UInt

    /**
     * Gets the width of the image before transformations.
     */
    public val baseWidth: UInt

    /**
     * Gets or sets a value indicating whether black point compensation should be used.
     */
    public var blackPointCompensation: Boolean

    /**
     * Gets the smallest bounding box enclosing non-border pixels. The current fuzz value is used
     * when discriminating between pixels.
     */
    public val boundingBox: IMagickGeometry?

    /**
     * Gets the number of channels that the image contains.
     */
    public val channelCount: UInt

    /**
     * Gets the channels of the image.
     */
    public val channels: Sequence<PixelChannel>

    /**
     * Gets or sets the chromaticity of the image.
     */
    public var chromaticity: IChromaticityInfo

    /**
     * Gets or sets the image class (DirectClass or PseudoClass)
     * NOTE: Setting a DirectClass image to PseudoClass will result in the loss of color information
     * if the number of colors in the image is greater than the maximum palette size (either 256 (Q8)
     * or 65536 (Q16).
     */
    public var classType: ClassType

    /**
     * Gets or sets the distance where colors are considered equal.
     */
    public var colorFuzz: Percentage

    /**
     * Gets or sets the colormap size (number of colormap entries).
     */
    public var colormapSize: UInt

    /**
     * Gets or sets the color space of the image.
     */
    public var colorSpace: ColorSpace

    /**
     * Gets or sets the color type of the image.
     */
    public var colorType: ColorType

    /**
     * Gets or sets the comment text of the image.
     */
    public var comment: String?

    /**
     * Gets or sets the composition operator to be used when composition is implicitly used (such as for image flattening).
     */
    public var compose: CompositeOperator

    /**
     * Gets the compression method of the image.
     */
    public val compression: CompressionMethod

    /**
     * Gets or sets the vertical and horizontal resolution in pixels of the image.
     */
    public var density: Density

    /**
     * Gets or sets the depth (bits allocated to red/green/blue components).
     */
    public var depth: UInt

    /**
     * Gets the preferred size of the image when encoding.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public val encodingGeometry: IMagickGeometry?

    /**
     * Gets or sets the endianness (little like Intel or big like SPARC) for image formats which support
     * endian-specific options.
     */
    public var endian: Endian

    /**
     * Gets the original file name of the image (only available if read from disk).
     */
    public val fileName: String?

    /**
     * Gets or sets the filter to use when resizing image.
     */
    public var filterType: FilterType

    /**
     * Gets or sets the format of the image.
     */
    public var format: MagickFormat

    /**
     * Gets the gamma level of the image.
     */
    public val gamma: Double

    /**
     * Gets or sets the gif disposal method.
     */
    public var gifDisposeMethod: GifDisposeMethod

    /**
     * Gets or sets a value indicating whether the image supports transparency (alpha channel).
     */
    public var hasAlpha: Boolean

    /**
     * Gets the height of the image.
     */
    public val height: UInt

    /**
     * Gets or sets the type of interlacing to use.
     */
    public val interlace: Interlace

    /**
     * Gets or sets the pixel color interpolate method to use.
     */
    public var interpolate: PixelInterpolateMethod

    /**
     * Gets a value indicating whether none of the pixels in the image have an alpha value other
     * than OpaqueAlpha (QuantumRange).
     */
    public val isOpaque: Boolean

    /**
     * Gets or sets the label of the image.
     */
    public var label: String?

    /**
     * Gets or sets the photo orientation of the image.
     */
    public var orientation: OrientationType

    /**
     * Gets or sets the preferred size and location of an image canvas.
     */
    public var page: IMagickGeometry

    /**
     * Gets the names of the profiles.
     */
    public val profileNames: Sequence<String>

    /**
     * Gets or sets the JPEG/MIFF/PNG compression level (default 75).
     */
    public var quality: UInt

    /**
     * Gets or sets the type of rendering intent.
     */
    public var renderingIntent: RenderingIntent

    /**
     * Gets the signature of this image.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public val signature: String

    /**
     * Gets the number of colors in the image.
     */
    public val totalColors: UInt

    /**
     * Gets or sets the virtual pixel method.
     */
    public var virtualPixelMethod: VirtualPixelMethod

    /**
     * Gets the width of the image.
     */
    public val width: UInt

    /**
     * Adaptive-blur image with the default blur factor (0x1).
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun adaptiveBlur(): Unit = adaptiveBlur(0.0, 1.0)

    /**
     * Adaptive-blur image with specified blur factor.
     *
     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun adaptiveBlur(radius: Double): Unit = adaptiveBlur(radius, 1.0)

    /**
     * Adaptive-blur image with specified blur factor.
     *
     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
     * @param sigma The standard deviation of the Laplacian, in pixels.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun adaptiveBlur(radius: Double, sigma: Double): Unit

    /**
     * Resize using mesh interpolation. It works well for small resizes of less than +/- 50%
     * of the original image size. For larger resizing on images a full filtered and slower resize
     * function should be used instead.
     * <para />
     * Resize will fit the image into the requested size. It does NOT fill, the requested box size.
     * Use the [IMagickGeometry] overload for more control over the resulting size.
     *
     * @param width The new width.
     * @param height The new height.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun adaptiveResize(width: UInt, height: UInt)

    /**
     * Resize using mesh interpolation. It works well for small resizes of less than +/- 50%
     * of the original image size. For larger resizing on images a full filtered and slower resize
     * function should be used instead.
     *
     * @param geometry The geometry to use.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun adaptiveResize(geometry: IMagickGeometry)

    /**
     * Adaptively sharpens the image by sharpening more intensely near image edges and less
     * intensely far from edges.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun adaptiveSharpen(): Unit = adaptiveSharpen(0.0, 1.0)

    /**
     * Adaptively sharpens the image by sharpening more intensely near image edges and less
     * intensely far from edges.
     *
     * @param channels The channel(s) that should be sharpened.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun adaptiveSharpen(channels: Channels): Unit = adaptiveSharpen(0.0, 1.0, channels)

    /**
     * Adaptively sharpens the image by sharpening more intensely near image edges and less
     * intensely far from edges.
     *
     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
     * @param sigma The standard deviation of the Laplacian, in pixels.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun adaptiveSharpen(radius: Double, sigma: Double): Unit = adaptiveSharpen(0.0, 1.0, Channels.UNDEFINED)

    /**
     * Adaptively sharpens the image by sharpening more intensely near image edges and less
     * intensely far from edges.
     *
     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
     * @param sigma The standard deviation of the Laplacian, in pixels.
     * @param channels The channel(s) that should be sharpened.
     */
    @Throws(MagickException::class)
    public fun adaptiveSharpen(radius: Double, sigma: Double, channels: Channels): Unit

    /**
     * Local adaptive threshold image.
     * http://www.dai.ed.ac.uk/HIPR2/adpthrsh.htm.
     *
     * @param width The width of the pixel neighborhood.
     * @param height The height of the pixel neighborhood.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun adaptiveThreshold(width: UInt, height: UInt): Unit =
        adaptiveThreshold(width, height, 0.0, Channels.UNDEFINED)

    /**
     * Local adaptive threshold image.
     * http://www.dai.ed.ac.uk/HIPR2/adpthrsh.htm.
     *
     * @param width The width of the pixel neighborhood.
     * @param height The height of the pixel neighborhood.
     * @param channels The channel(s) that should be thresholded.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun adaptiveThreshold(width: UInt, height: UInt, channels: Channels): Unit =
        adaptiveThreshold(width, height, 0.0, channels)

    /**
     * Local adaptive threshold image.
     * http://www.dai.ed.ac.uk/HIPR2/adpthrsh.htm.
     *
     * @param width The width of the pixel neighborhood.
     * @param height The height of the pixel neighborhood.
     * @param bias Constant to subtract from pixel neighborhood mean (+/-)(0-QuantumRange).
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun adaptiveThreshold(width: UInt, height: UInt, bias: Double): Unit =
        adaptiveThreshold(width, height, bias, Channels.UNDEFINED)

    /**
     * Local adaptive threshold image.
     * http://www.dai.ed.ac.uk/HIPR2/adpthrsh.htm.
     *
     * @param width The width of the pixel neighborhood.
     * @param height The height of the pixel neighborhood.
     * @param bias Constant to subtract from pixel neighborhood mean (+/-)(0-QuantumRange).
     * @param channels The channel(s) that should be thresholded.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun adaptiveThreshold(width: UInt, height: UInt, bias: Double, channels: Channels): Unit

    /**
     * Local adaptive threshold image.
     * http://www.dai.ed.ac.uk/HIPR2/adpthrsh.htm.
     *
     * @param width The width of the pixel neighborhood.
     * @param height The height of the pixel neighborhood.
     * @param biasPercentage Constant to subtract from pixel neighborhood mean.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun adaptiveThreshold(width: UInt, height: UInt, biasPercentage: Percentage): Unit =
        adaptiveThreshold(width, height, biasPercentage, Channels.UNDEFINED)

    /**
     * Local adaptive threshold image.
     * http://www.dai.ed.ac.uk/HIPR2/adpthrsh.htm.
     *
     * @param width The width of the pixel neighborhood.
     * @param height The height of the pixel neighborhood.
     * @param biasPercentage Constant to subtract from pixel neighborhood mean.
     * @param channels The channel(s) that should be thresholded.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun adaptiveThreshold(width: UInt, height: UInt, biasPercentage: Percentage, channels: Channels): Unit

    /**
     * Add noise to image with the specified noise type.
     *
     * @param noiseType The type of noise that should be added to the image.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun addNoise(noiseType: NoiseType): Unit = addNoise(noiseType, 1.0, Channels.UNDEFINED)

    /**
     * Add noise to the specified channel of the image with the specified noise type.
     *
     * @param noiseType The type of noise that should be added to the image.
     * @param channels The channel(s) where the noise should be added.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun addNoise(noiseType: NoiseType, channels: Channels): Unit = addNoise(noiseType, 1.0, channels)

    /**
     * Add noise to image with the specified noise type.
     *
     * @param noiseType The type of noise that should be added to the image.
     * @param attenuate Attenuate the random distribution.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun addNoise(noiseType: NoiseType, attenuate: Double): Unit =
        addNoise(noiseType, attenuate, Channels.UNDEFINED)

    /**
     * Add noise to the specified channel of the image with the specified noise type.
     *
     * @param noiseType The type of noise that should be added to the image.
     * @param attenuate Attenuate the random distribution.
     * @param channels The channel(s) where the noise should be added.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun addNoise(noiseType: NoiseType, attenuate: Double, channels: Channels): Unit

    /**
     * Affine Transform image.
     *
     * @param affineMatrix The affine matrix to use.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun affineTransform(affineMatrix: IDrawableAffine): Unit

    /**
     * Applies the specified alpha option.
     *
     * @param value The option to use.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun alpha(value: AlphaOption): Unit

    /**
     * Annotate using specified text, and bounding area.
     *
     * @param text The text to use.
     * @param boundingArea The bounding area.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun annotate(text: String, boundingArea: IMagickGeometry): Unit =
        annotate(text, boundingArea, Gravity.UNDEFINED, 0.0)

    /**
     * Annotate using specified text, bounding area, and placement gravity.
     *
     * @param text The text to use.
     * @param boundingArea The bounding area.
     * @param gravity The placement gravity.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun annotate(text: String, boundingArea: IMagickGeometry, gravity: Gravity): Unit =
        annotate(text, boundingArea, gravity, 0.0)

    /**
     * Annotate using specified text, bounding area, and placement gravity.
     *
     * @param text The text to use.
     * @param boundingArea The bounding area.
     * @param gravity The placement gravity.
     * @param angle The rotation.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun annotate(text: String, boundingArea: IMagickGeometry, gravity: Gravity, angle: Double): Unit

    /**
     * Annotate with text (bounding area is entire image) and placement gravity.
     *
     * @param text The text to use.
     * @param gravity The placement gravity.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun annotate(text: String, gravity: Gravity): Unit

    /**
     * Extracts the 'mean' from the image and adjust the image to try make set its gamma appropriatally.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun autoGamma(): Unit = autoGamma(Channels.COMPOSITE)

    /**
     * Extracts the 'mean' from the image and adjust the image to try make set its gamma appropriatally.
     *
     * @param channels The channel(s) to set the gamma for.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun autoGamma(channels: Channels): Unit

    /**
     * Adjusts the levels of a particular image channel by scaling the minimum and maximum values
     * to the full quantum range.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun autoLevel(): Unit = autoLevel(Channels.UNDEFINED)

    /**
     * Adjusts the levels of a particular image channel by scaling the minimum and maximum values
     * to the full quantum range.
     *
     * @param channels The channel(s) to level.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun autoLevel(channels: Channels): Unit

    /**
     * Adjusts an image so that its orientation is suitable for viewing.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun autoOrient(): Unit

    /**
     * Automatically selects a threshold and replaces each pixel in the image with a black pixel if
     * the image intentsity is less than the selected threshold otherwise white.
     *
     * @param method The threshold method.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun autoThreshold(method: AutoThresholdMethod): Unit

    /**
     * Applies a non-linear, edge-preserving, and noise-reducing smoothing filter.
     *
     * @param width The width of the neighborhood in pixels.
     * @param height The height of the neighborhood in pixels.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun bilateralBlur(width: UInt, height: UInt): Unit {
        val intensitySigma = sqrt((width * width).toDouble() + (height * height).toDouble())
        bilateralBlur(width, height, intensitySigma, intensitySigma * 0.25)
    }

    /**
     * Applies a non-linear, edge-preserving, and noise-reducing smoothing filter.
     *
     * @param width The width of the neighborhood in pixels.
     * @param height The height of the neighborhood in pixels.
     * @param intensitySigma The sigma in the intensity space.
     * @param spatialSigma The sigma in the coordinate space.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun bilateralBlur(width: UInt, height: UInt, intensitySigma: Double, spatialSigma: Double): Unit

    /**
     * Forces all pixels below the threshold into black while leaving all pixels at or above
     * the threshold unchanged.
     *
     * @param threshold The threshold to use.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun blackThreshold(threshold: Percentage): Unit = blackThreshold(threshold, Channels.COMPOSITE)

    /**
     * Forces all pixels below the threshold into black while leaving all pixels at or above
     * the threshold unchanged.
     *
     * @param threshold The threshold to use.
     * @param channels The channel(s) to make black.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun blackThreshold(threshold: Percentage, channels: Channels): Unit

    /**
     * Simulate a scene at nighttime in the moonlight.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun blueShift(): Unit = blueShift(1.5)

    /**
     * Simulate a scene at nighttime in the moonlight.
     *
     * @param factor The factor to use.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun blueShift(factor: Double): Unit

    /**
     * Blur image with the default blur factor (0x1).
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun blur(): Unit = blur(0.0, 1.0)

    /**
     * Blur image the specified channel of the image with the default blur factor (0x1).
     *
     * @param channels The channel(s) that should be blurred.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun blur(channels: Channels): Unit = blur(0.0, 1.0, channels)

    /**
     * Blur image with specified blur factor.
     *
     * @param radius The radius of the Gaussian in pixels, not counting the center pixel.
     * @param sigma The standard deviation of the Laplacian, in pixels.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun blur(radius: Double, sigma: Double): Unit = blur(radius, sigma, Channels.UNDEFINED)

    /**
     * Blur image with specified blur factor and channel.
     *
     * @param radius The radius of the Gaussian in pixels, not counting the center pixel.
     * @param sigma The standard deviation of the Laplacian, in pixels.
     * @param channels The channel(s) that should be blurred.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun blur(radius: Double, sigma: Double, channels: Channels): Unit

    /**
     * Border image (add border to image).
     *
     * @param size The size of the border.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun border(size: UInt): Unit = border(size, size)

    /**
     * Border image (add border to image).
     *
     * @param width The width of the border.
     * @param height The height of the border.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun border(width: UInt, height: UInt): Unit

    /**
     * Border image (add border to image).
     *
     * @param percentage The size of the border.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun border(percentage: Percentage): Unit = border(percentage * width, percentage * height)

    /**
     * Changes the brightness and/or contrast of an image. It converts the brightness and
     * contrast parameters into slope and intercept and calls a polynomical function to apply
     * to the image.
     *
     * @param brightness The brightness.
     * @param contrast The contrast.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun brightnessContrast(brightness: Percentage, contrast: Percentage): Unit =
        brightnessContrast(brightness, contrast, Channels.UNDEFINED)

    /**
     * Changes the brightness and/or contrast of an image. It converts the brightness and
     * contrast parameters into slope and intercept and calls a polynomical function to apply
     * to the image.
     *
     * @param brightness The brightness.
     * @param contrast The contrast.
     * @param channels The channel(s) that should be changed.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun brightnessContrast(brightness: Percentage, contrast: Percentage, channels: Channels): Unit

    /**
     * Uses a multi-stage algorithm to detect a wide range of edges in images.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun cannyEdge(): Unit = cannyEdge(0.0, 1.0, 10.percent(), 10.percent())

    /**
     * Uses a multi-stage algorithm to detect a wide range of edges in images.
     *
     * @param radius The radius of the gaussian smoothing filter.
     * @param sigma The sigma of the gaussian smoothing filter.
     * @param lower Percentage of edge pixels in the lower threshold.
     * @param upper Percentage of edge pixels in the upper threshold.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun cannyEdge(radius: Double, sigma: Double, lower: Percentage, upper: Percentage): Unit

    /**
     * Charcoal effect image (looks like charcoal sketch).
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun charcoal(): Unit = charcoal(0.0, 1.0)

    /**
     * Charcoal effect image (looks like charcoal sketch).
     *
     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
     * @param sigma The standard deviation of the Laplacian, in pixels.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun charcoal(radius: Double, sigma: Double): Unit

    /**
     * Chop image (remove vertical or horizontal subregion of image) using the specified geometry.
     *
     * @param geometry The geometry to use.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun chop(geometry: IMagickGeometry): Unit

    /**
     * Chop image (remove horizontal subregion of image).
     *
     * @param offset The X offset from origin.
     * @param width The width of the part to chop horizontally.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun chopHorizontal(offset: Int, width: UInt): Unit

    /**
     * Chop image (remove horizontal subregion of image).
     *
     * @param offset The Y offset from origin.
     * @param height The height of the part to chop vertically.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun chopVertical(offset: Int, height: UInt): Unit

    /**
     * A variant of adaptive histogram equalization in which the contrast amplification is limited,
     * so as to reduce this problem of noise amplification.
     *
     * @param xTiles The percentage of tile divisions to use in horizontal direction.
     * @param yTiles The percentage of tile divisions to use in vertical direction.
     * @param numberBins The number of bins for histogram ("dynamic range").
     * @param clipLimit The contrast limit for localised changes in contrast. A limit less than 1 results in
     *    standard non-contrast limited AHE.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun clahe(xTiles: Percentage, yTiles: Percentage, numberBins: UInt, clipLimit: Double): Unit =
        clahe(xTiles * width, yTiles * height, numberBins, clipLimit)

    /**
     * A variant of adaptive histogram equalization in which the contrast amplification is limited,
     * so as to reduce this problem of noise amplification.
     *
     * @param xTiles The number of tile divisions to use in horizontal direction.
     * @param yTiles The number of tile divisions to use in vertical direction.
     * @param numberBins The number of bins for histogram ("dynamic range").
     * @param clipLimit The contrast limit for localised changes in contrast. A limit less than 1 results in
     *    standard non-contrast limited AHE.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun clahe(xTiles: UInt, yTiles: UInt, numberBins: UInt, clipLimit: Double): Unit

    /**
     * Set each pixel whose value is below zero to zero and any the pixel whose value is above
     * the quantum range to the quantum range (Quantum.Max) otherwise the pixel value
     * remains unchanged.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun clamp(): Unit = clamp(Channels.UNDEFINED)

    /**
     * Set each pixel whose value is below zero to zero and any the pixel whose value is above
     * the quantum range to the quantum range (Quantum.Max) otherwise the pixel value
     * remains unchanged.
     *
     * @param channels The channel(s) to clamp.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun clamp(channels: Channels): Unit

    /**
     * Sets the image clip mask based on any clipping path information if it exists. The clipping
     * path can be removed with [RemoveWriteMask]. This operating takes effect inside
     * the clipping path.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun clip(): Unit = clip("#1")

    /**
     * Sets the image clip mask based on any clipping path information if it exists. The clipping
     * path can be removed with [removeWriteMask]. This operating takes effect inside
     * the clipping path.
     *
     * @param pathName Name of clipping path resource. If name is preceded by #, use clipping path numbered by name.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun clip(pathName: String): Unit

    /**
     * Sets the image clip mask based on any clipping path information if it exists. The clipping
     * path can be removed with [removeWriteMask]. This operating takes effect outside
     * the clipping path.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun clipOutside(): Unit = clipOutside("#1")

    /**
     * Sets the image clip mask based on any clipping path information if it exists. The clipping
     * path can be removed with [removeWriteMask]. This operating takes effect outside
     * the clipping path.
     *
     * @param pathName Name of clipping path resource. If name is preceded by #, use clipping path numbered by name.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun clipOutside(pathName: String): Unit

    /**
     * Apply a color lookup table (CLUT) to the image.
     *
     * @param image The image to use.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun clut(image: MagickImage): Unit = clut(image, PixelInterpolateMethod.UNDEFINED)

    /**
     * Apply a color lookup table (CLUT) to the image.
     *
     * @param image The image to use.
     * @param method Pixel interpolate method.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun clut(image: MagickImage, method: PixelInterpolateMethod): Unit = clut(image, method, Channels.UNDEFINED)

    /**
     * Apply a color lookup table (CLUT) to the image.
     *
     * @param image The image to use.
     * @param method Pixel interpolate method.
     * @param channels The channel(s) to clut.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun clut(image: MagickImage, method: PixelInterpolateMethod, channels: Channels): Unit

    /**
     * Applies the color decision list from the specified ASC CDL file.
     *
     * @param fileName The file to read the ASC CDL information from.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun colorDecisionList(fileName: String): Unit

    /**
     * Apply a color matrix to the image channels.
     *
     * @param matrix The color matrix to use.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun colorMatrix(matrix: IMagickColorMatrix): Unit

    /**
     * Compare current image with another image and returns error information.
     *
     * @param image The other image to compare with this image.
     * @return The error information.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun compare(image: MagickImage): IMagickErrorInfo

    /**
     * Returns the distortion based on the specified metric.
     *
     * @param image The other image to compare with this image.
     * @param metric The metric to use.
     * @return The distortion based on the specified metric.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun compare(image: MagickImage, metric: ErrorMetric): Double = compare(image, metric, Channels.UNDEFINED)

    /**
     * Returns the distortion based on the specified metric.
     *
     * @param image The other image to compare with this image.
     * @param metric The metric to use.
     * @param channels The channel(s) to compare.
     * @return The distortion based on the specified metric.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun compare(image: MagickImage, metric: ErrorMetric, channels: Channels): Double

    /**
     * Returns the distortion based on the specified metric.
     *
     * @param image The other image to compare with this image.
     * @param metric The metric to use.
     * @param difference The image that will contain the difference.
     * @return The distortion based on the specified metric.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun compare(image: MagickImage, metric: ErrorMetric, difference: MagickImage): Double =
        compare(image, metric, difference, Channels.UNDEFINED)

    /**
     * Returns the distortion based on the specified metric.
     *
     * @param image The other image to compare with this image.
     * @param metric The metric to use.
     * @param difference The image that will contain the difference.
     * @param channels The channel(s) to compare.
     * @return The distortion based on the specified metric.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun compare(image: MagickImage, metric: ErrorMetric, difference: MagickImage, channels: Channels): Double

    /**
     * Compose an image onto another at specified offset using the 'In' operator.
     *
     * @param image The image to composite with this image.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(image: MagickImage): Unit = composite(image, CompositeOperator.IN)

    /**
     * Compose an image onto another at specified offset using the 'In' operator.
     *
     * @param image The image to composite with this image.
     * @param channels The channel(s) to composite.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(image: MagickImage, channels: Channels): Unit =
        composite(image, CompositeOperator.IN, channels)

    /**
     * Compose an image onto another using the specified algorithm.
     *
     * @param image The image to composite with this image.
     * @param compose The algorithm to use.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(image: MagickImage, compose: CompositeOperator): Unit = composite(image, 0, 0, compose)

    /**
     * Compose an image onto another using the specified algorithm.
     *
     * @param image The image to composite with this image.
     * @param compose The algorithm to use.
     * @param channels The channel(s) to composite.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(image: MagickImage, compose: CompositeOperator, channels: Channels): Unit =
        composite(image, 0, 0, compose, channels)

    /**
     * Compose an image onto another at specified offset using the specified algorithm.
     *
     * @param image The image to composite with this image.
     * @param compose The algorithm to use.
     * @param args The arguments for the algorithm (compose:args).
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(image: MagickImage, compose: CompositeOperator, args: String?): Unit =
        composite(image, 0, 0, compose, args)

    /**
     * Compose an image onto another at specified offset using the specified algorithm.
     *
     * @param image The image to composite with this image.
     * @param compose The algorithm to use.
     * @param args The arguments for the algorithm (compose:args).
     * @param channels The channel(s) to composite.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(image: MagickImage, compose: CompositeOperator, args: String?, channels: Channels): Unit =
        composite(image, 0, 0, compose, args, channels)

    /**
     * Compose an image onto another at specified offset using the 'In' operator.
     *
     * @param image The image to composite with this image.
     * @param x The X offset from origin.
     * @param y The Y offset from origin.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(image: MagickImage, x: Int, y: Int): Unit = composite(image, x, y, CompositeOperator.IN)

    /**
     * Compose an image onto another at specified offset using the 'In' operator.
     *
     * @param image The image to composite with this image.
     * @param x The X offset from origin.
     * @param y The Y offset from origin.
     * @param channels The channel(s) to composite.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(image: MagickImage, x: Int, y: Int, channels: Channels): Unit =
        composite(image, x, y, CompositeOperator.IN, channels)

    /**
     * Compose an image onto another at specified offset using the specified algorithm.
     *
     * @param image The image to composite with this image.
     * @param x The X offset from origin.
     * @param y The Y offset from origin.
     * @param compose The algorithm to use.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(image: MagickImage, x: Int, y: Int, compose: CompositeOperator): Unit =
        composite(image, x, y, compose, null)

    /**
     * Compose an image onto another at specified offset using the specified algorithm.
     *
     * @param image The image to composite with this image.
     * @param x The X offset from origin.
     * @param y The Y offset from origin.
     * @param compose The algorithm to use.
     * @param channels The channel(s) to composite.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(image: MagickImage, x: Int, y: Int, compose: CompositeOperator, channels: Channels): Unit =
        composite(image, x, y, compose, null, channels)


    /**
     * Compose an image onto another at specified offset using the specified algorithm.
     *
     * @param image The image to composite with this image.
     * @param x The X offset from origin.
     * @param y The Y offset from origin.
     * @param compose The algorithm to use.
     * @param args The arguments for the algorithm (compose:args).
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(image: MagickImage, x: Int, y: Int, compose: CompositeOperator, args: String?): Unit =
        composite(image, x, y, compose, args, Channels.UNDEFINED)

    /**
     * Compose an image onto another at specified offset using the specified algorithm.
     *
     * @param image The image to composite with this image.
     * @param x The X offset from origin.
     * @param y The Y offset from origin.
     * @param compose The algorithm to use.
     * @param args The arguments for the algorithm (compose:args).
     * @param channels The channel(s) to composite.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(
        image: MagickImage,
        x: Int,
        y: Int,
        compose: CompositeOperator,
        args: String?,
        channels: Channels,
    ): Unit

    /**
     * Compose an image onto another at specified offset using the 'In' operator.
     *
     * @param image The image to composite with this image.
     * @param gravity The placement gravity.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(image: MagickImage, gravity: Gravity): Unit = composite(image, gravity, CompositeOperator.IN)

    /**
     * Compose an image onto another at specified offset using the 'In' operator.
     *
     * @param image The image to composite with this image.
     * @param gravity The placement gravity.
     * @param channels The channel(s) to composite.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(image: MagickImage, gravity: Gravity, channels: Channels): Unit =
        composite(image, gravity, CompositeOperator.IN, channels)

    /**
     * Compose an image onto another at specified offset using the specified algorithm.
     *
     * @param image The image to composite with this image.
     * @param gravity The placement gravity.
     * @param compose The algorithm to use.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(image: MagickImage, gravity: Gravity, compose: CompositeOperator): Unit =
        composite(image, gravity, compose, null)

    /**
     * Compose an image onto another at specified offset using the specified algorithm.
     *
     * @param image The image to composite with this image.
     * @param gravity The placement gravity.
     * @param compose The algorithm to use.
     * @param channels The channel(s) to composite.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(image: MagickImage, gravity: Gravity, compose: CompositeOperator, channels: Channels): Unit =
        composite(image, gravity, compose, null, channels)

    /**
     * Compose an image onto another at specified offset using the specified algorithm.
     *
     * @param image The image to composite with this image.
     * @param gravity The placement gravity.
     * @param compose The algorithm to use.
     * @param args The arguments for the algorithm (compose:args).
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(image: MagickImage, gravity: Gravity, compose: CompositeOperator, args: String?): Unit =
        composite(image, gravity, compose, args, Channels.UNDEFINED)

    /**
     * Compose an image onto another at specified offset using the specified algorithm.
     *
     * @param image The image to composite with this image.
     * @param gravity The placement gravity.
     * @param compose The algorithm to use.
     * @param args The arguments for the algorithm (compose:args).
     * @param channels The channel(s) to composite.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(
        image: MagickImage,
        gravity: Gravity,
        compose: CompositeOperator,
        args: String?,
        channels: Channels,
    ): Unit = composite(image, gravity, 0, 0, compose, args, channels)

    /**
     * Compose an image onto another at specified offset using the 'In' operator.
     *
     * @param image The image to composite with this image.
     * @param gravity The placement gravity.
     * @param x The X offset from origin.
     * @param y The Y offset from origin.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(image: MagickImage, gravity: Gravity, x: Int, y: Int): Unit =
        composite(image, gravity, x, y, CompositeOperator.IN)

    /**
     * Compose an image onto another at specified offset using the 'In' operator.
     *
     * @param image The image to composite with this image.
     * @param gravity The placement gravity.
     * @param x The X offset from origin.
     * @param y The Y offset from origin.
     * @param channels The channel(s) to composite.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(image: MagickImage, gravity: Gravity, x: Int, y: Int, channels: Channels): Unit =
        composite(image, gravity, x, y, CompositeOperator.IN, channels)

    /**
     * Compose an image onto another at specified offset using the 'In' operator.
     *
     * @param image The image to composite with this image.
     * @param gravity The placement gravity.
     * @param x The X offset from origin.
     * @param y The Y offset from origin.
     * @param compose The algorithm to use.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(image: MagickImage, gravity: Gravity, x: Int, y: Int, compose: CompositeOperator): Unit =
        composite(image, gravity, x, y, compose, null)

    /**
     * Compose an image onto another at specified offset using the 'In' operator.
     *
     * @param image The image to composite with this image.
     * @param gravity The placement gravity.
     * @param x The X offset from origin.
     * @param y The Y offset from origin.
     * @param compose The algorithm to use.
     * @param channels The channel(s) to composite.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(
        image: MagickImage,
        gravity: Gravity,
        x: Int,
        y: Int,
        compose: CompositeOperator,
        channels: Channels,
    ): Unit = composite(image, gravity, x, y, compose, null, channels)

    /**
     * Compose an image onto another at specified offset using the specified algorithm.
     *
     * @param image The image to composite with this image.
     * @param gravity The placement gravity.
     * @param x The X offset from origin.
     * @param y The Y offset from origin.
     * @param compose The algorithm to use.
     * @param args The arguments for the algorithm (compose:args).
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(
        image: MagickImage,
        gravity: Gravity,
        x: Int,
        y: Int,
        compose: CompositeOperator,
        args: String?,
    ): Unit = composite(image, gravity, x, y, compose, args, Channels.UNDEFINED)

    /**
     * Compose an image onto another at specified offset using the specified algorithm.
     *
     * @param image The image to composite with this image.
     * @param gravity The placement gravity.
     * @param x The X offset from origin.
     * @param y The Y offset from origin.
     * @param compose The algorithm to use.
     * @param args The arguments for the algorithm (compose:args).
     * @param channels The channel(s) to composite.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun composite(
        image: MagickImage,
        gravity: Gravity,
        x: Int,
        y: Int,
        compose: CompositeOperator,
        args: String?,
        channels: Channels,
    ): Unit

    /**
     * Contrast image (enhance intensity differences in image).
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun contrast()

    /**
     * A simple image enhancement technique that attempts to improve the contrast in an image by
     * 'stretching' the range of intensity values it contains to span a desired range of values.
     * It differs from the more sophisticated histogram equalization in that it can only apply a
     * linear scaling function to the image pixel values. As a result the 'enhancement' is less harsh.
     *
     * @param blackPoint The black point.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun contrastStretch(blackPoint: Percentage): Unit = contrastStretch(blackPoint, blackPoint)

    /**
     * A simple image enhancement technique that attempts to improve the contrast in an image by
     * 'stretching' the range of intensity values it contains to span a desired range of values.
     * It differs from the more sophisticated histogram equalization in that it can only apply a
     * linear scaling function to the image pixel values. As a result the 'enhancement' is less harsh.
     *
     * @param blackPoint The black point.
     * @param whitePoint The white point.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun contrastStretch(blackPoint: Percentage, whitePoint: Percentage): Unit =
        contrastStretch(blackPoint, whitePoint, Channels.UNDEFINED)

    /**
     * A simple image enhancement technique that attempts to improve the contrast in an image by
     * 'stretching' the range of intensity values it contains to span a desired range of values.
     * It differs from the more sophisticated histogram equalization in that it can only apply a
     * linear scaling function to the image pixel values. As a result the 'enhancement' is less harsh.
     *
     * @param blackPoint The black point.
     * @param whitePoint The white point.
     * @param channels The channel(s) to constrast stretch.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun contrastStretch(blackPoint: Percentage, whitePoint: Percentage, channels: Channels): Unit

//    /**
//     * Returns the convex hull points of an image canvas.
//     *
//     * @return The convex hull points of an image canvas.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IEnumerable<PointD> ConvexHull();
//
//    /**
//     * Convolve image. Applies a user-specified convolution to the image.
//     *
//     * @param matrix The convolution matrix.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun convolve(matrix: IConvolveMatrix)
//
//    /**
//     * Copies pixels from the source image to the destination image.
//     *
//     * @param source The source image to copy the pixels from.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun copyPixels(source: MagickImage)
//
//    /**
//     * Copies pixels from the source image to the destination image.
//     *
//     * @param source The source image to copy the pixels from.
//     * @param channels The channels to copy.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun copyPixels(source: MagickImage, channels: Channels)
//
//    /**
//     * Copies pixels from the source image to the destination image.
//     *
//     * @param source The source image to copy the pixels from.
//     * @param geometry The geometry to copy.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun copyPixels(source: MagickImage, geometry: IMagickGeometry)
//
//    /**
//     * Copies pixels from the source image to the destination image.
//     *
//     * @param source The source image to copy the pixels from.
//     * @param geometry The geometry to copy.
//     * @param channels The channels to copy.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun copyPixels(source: MagickImage, geometry: IMagickGeometry, channels: Channels)
//
//    /**
//     * Copies pixels from the source image as defined by the geometry the destination image at
//     * the specified offset.
//     *
//     * @param source The source image to copy the pixels from.
//     * @param geometry The geometry to copy.
//     * @param x The X offset to start the copy from.
//     * @param y The Y offset to start the copy from.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun copyPixels(source: MagickImage, geometry: IMagickGeometry, x: Int, y: Int)
//
//    /**
//     * Copies pixels from the source image as defined by the geometry the destination image at
//     * the specified offset.
//     *
//     * @param source The source image to copy the pixels from.
//     * @param geometry The geometry to copy.
//     * @param x The X offset to copy the pixels to.
//     * @param y The Y offset to copy the pixels to.
//     * @param channels The channels to copy.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun copyPixels(source: MagickImage, geometry: IMagickGeometry, x: Int, y: Int, channels: Channels)
//
//    /**
//     * Crop image (subregion of original image). RePage should be called unless the Page information
//     * is needed.
//     *
//     * @param width The width of the subregion.
//     * @param height The height of the subregion.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun crop(width: Int, height: Int)
//
//    /**
//     * Crop image (subregion of original image). RePage should be called unless the Page information
//     * is needed.
//     *
//     * @param width The width of the subregion.
//     * @param height The height of the subregion.
//     * @param gravity The position where the cropping should start from.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun crop(width: Int, height: Int, gravity: Gravity)
//
//    /**
//     * Crop image (subregion of original image). RePage should be called unless the Page information
//     * is needed.
//     *
//     * @param geometry The subregion to crop.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun crop(geometry: IMagickGeometry)
//
//    /**
//     * Crop image (subregion of original image). RePage should be called unless the Page information
//     * is needed.
//     *
//     * @param geometry The subregion to crop.
//     * @param gravity The position where the cropping should start from.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun crop(geometry: IMagickGeometry, gravity: Gravity)
//
//    /**
//     * Displaces an image's colormap by a given number of positions.
//     *
//     * @param amount Displace the colormap this amount.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun cycleColormap(amount: Int)
//
//    /**
//     * Converts cipher pixels to plain pixels.
//     *
//     * @param passphrase The password that was used to encrypt the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun decipher(passphrase: String)
//
//    /**
//     * Removes skew from the image. Skew is an artifact that occurs in scanned images because of
//     * the camera being misaligned, imperfections in the scanning or surface, or simply because
//     * the paper was not placed completely flat when scanned. The value of threshold ranges
//     * from 0 to QuantumRange.
//     *
//     * @param threshold The threshold.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     * @return The angle that was used.
//     */
//    public fun deskew(threshold: Percentage): Double
//
//    /**
//     * Removes skew from the image. Skew is an artifact that occurs in scanned images because of
//     * the camera being misaligned, imperfections in the scanning or surface, or simply because
//     * the paper was not placed completely flat when scanned. The value of threshold ranges
//     * from 0 to QuantumRange.
//     *
//     * @param settings The deskew settings.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     * @return The angle that was used.
//     */
//    public fun deskew(settings: IDeskewSettings): Double
//
//    /**
//     * Despeckle image (reduce speckle noise).
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun despeckle()
//
//    /**
//     * Determines the bit depth (bits allocated to red/green/blue components). Use the Depth
//     * property to get the current value.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     * @return The bit depth (bits allocated to red/green/blue components).
//     */
//    public fun determineBitDepth(): Int
//
//    /**
//     * Determines the bit depth (bits allocated to red/green/blue components) of the specified channel.
//     *
//     * @param channels The channel to get the depth for.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     * @return The bit depth (bits allocated to red/green/blue components) of the specified channel.
//     */
//    public fun determineBitDepth(channels: Channels): Int
//
//    /**
//     * Determines the color type of the image. This method can be used to automatically make the
//     * type GrayScale.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     * @return The color type of the image.
//     */
//    public fun determineColorType(): ColorType
//
//    /**
//     * Distorts an image using various distortion methods, by mapping color lookups of the source
//     * image to a new destination image of the same size as the source image.
//     *
//     * @param method The distortion method to use.
//     * @param arguments An array containing the arguments for the distortion.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun distort(method: DistortMethod, vararg arguments: Double)
//
//    /**
//     * Distorts an image using various distortion methods, by mapping color lookups of the source
//     * image to a new destination image usually of the same size as the source image, unless
//     * 'bestfit' is set to true.
//     *
//     * @param method The distortion method to use.
//     * @param settings The settings for the distort operation.
//     * @param arguments An array containing the arguments for the distortion.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun distort(method: DistortMethod, settings: IDistortSettings, vararg arguments: Double)
//
//    /**
//     * Draw on image using one or more drawables.
//     *
//     * @param drawables The drawable(s) to draw on the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun draw(vararg drawables: IDrawable)
//
//    /**
//     * Draw on image using a collection of drawables.
//     *
//     * @param drawables The drawables to draw on the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun draw()
//
//    /**
//     * Edge image (highlight edges in image).
//     *
//     * @param radius The radius of the pixel neighborhood.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun edge(radius: Double)
//
//    /**
//     * Emboss image (highlight edges with 3D effect) with default value (0x1).
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun emboss()
//
//    /**
//     * Emboss image (highlight edges with 3D effect).
//     *
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun emboss(radius: Double, sigma: Double)
//
//    /**
//     * Converts pixels to cipher-pixels.
//     *
//     * @param passphrase The password that to encrypt the image with.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun encipher(passphrase: String)
//
//    /**
//     * Applies a digital filter that improves the quality of a noisy image.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun enhance()
//
//    /**
//     * Applies a histogram equalization to the image.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun equalize()
//
//    /**
//     * Applies a histogram equalization to the image.
//     *
//     * @param channels The channel(s) to apply the operator on.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun equalize(channels: Channels)
//
//    /**
//     * Apply an arithmetic or bitwise operator to the image pixel quantums.
//     *
//     * @param channels The channel(s) to apply the operator on.
//     * @param evaluateFunction The function.
//     * @param arguments The arguments for the function.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun evaluate(channels: Channels, evaluateFunction: EvaluateFunction, vararg arguments: Double)
//
//    /**
//     * Apply an arithmetic or bitwise operator to the image pixel quantums.
//     *
//     * @param channels The channel(s) to apply the operator on.
//     * @param evaluateOperator The operator.
//     * @param value The value.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun evaluate(channels: Channels, evaluateOperator: EvaluateOperator, value: Double)
//
//    /**
//     * Apply an arithmetic or bitwise operator to the image pixel quantums.
//     *
//     * @param channels The channel(s) to apply the operator on.
//     * @param evaluateOperator The operator.
//     * @param percentage The value.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun evaluate(channels: Channels, evaluateOperator: EvaluateOperator, percentage: Percentage)
//
//    /**
//     * Apply an arithmetic or bitwise operator to the image pixel quantums.
//     *
//     * @param channels The channel(s) to apply the operator on.
//     * @param geometry The geometry to use.
//     * @param evaluateOperator The operator.
//     * @param value The value.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun evaluate(channels: Channels, geometry: IMagickGeometry, evaluateOperator: EvaluateOperator, value: Double)
//
//    /**
//     * Apply an arithmetic or bitwise operator to the image pixel quantums.
//     *
//     * @param channels The channel(s) to apply the operator on.
//     * @param geometry The geometry to use.
//     * @param evaluateOperator The operator.
//     * @param percentage The value.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun evaluate(channels: Channels, geometry: IMagickGeometry, evaluateOperator: EvaluateOperator, percentage: Percentage)
//
//    /**
//     * Extend the image as defined by the width and height.
//     *
//     * @param width The width to extend the image to.
//     * @param height The height to extend the image to.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun extent(width: Int, height: Int)
//
//    /**
//     * Extend the image as defined by the width and height.
//     *
//     * @param x The X offset from origin.
//     * @param y The Y offset from origin.
//     * @param width The width to extend the image to.
//     * @param height The height to extend the image to.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun extent(x: Int, y: Int, width: Int, height: Int)
//
//    /**
//     * Extend the image as defined by the width and height.
//     *
//     * @param width The width to extend the image to.
//     * @param height The height to extend the image to.
//     * @param gravity The placement gravity.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun extent(width: Int, height: Int, gravity: Gravity)
//
//    /**
//     * Extend the image as defined by the rectangle.
//     *
//     * @param geometry The geometry to extend the image to.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun extent(geometry: IMagickGeometry)
//
//    /**
//     * Extend the image as defined by the geometry.
//     *
//     * @param geometry The geometry to extend the image to.
//     * @param gravity The placement gravity.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun extent(geometry: IMagickGeometry, gravity: Gravity)
//
//    /**
//     * Flip image (reflect each scanline in the vertical direction).
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun flip()
//
//    /**
//     * Flop image (reflect each scanline in the horizontal direction).
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun flop()
//
//    /**
//     * Obtain font metrics for text string given current font, pointsize, and density settings.
//     *
//     * @param text The text to get the font metrics for.
//     * @return The font metrics for text.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    ITypeMetric? FontTypeMetrics(string text);
//
//    /**
//     * Obtain font metrics for text string given current font, pointsize, and density settings.
//     *
//     * @param text The text to get the font metrics for.
//     * @param ignoreNewlines Specifies if newlines should be ignored.
//     * @return The font metrics for text.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    ITypeMetric? FontTypeMetrics(string text, Boolean ignoreNewlines);
//
//    /**
//     * Formats the specified expression, more info here: http://www.imagemagick.org/script/escape.php.
//     *
//     * @param expression The expression, more info here: http://www.imagemagick.org/script/escape.php.
//     * @return The result of the expression.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    string? FormatExpression(string expression);
//
//    /**
//     * Frame image with the default geometry (25x25+6+6).
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun frame()
//
//    /**
//     * Frame image with the specified geometry.
//     *
//     * @param geometry The geometry of the frame.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun frame(geometry: IMagickGeometry)
//
//    /**
//     * Frame image with the specified with and height.
//     *
//     * @param width The width of the frame.
//     * @param height The height of the frame.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun frame(width: Int, height: Int)
//
//    /**
//     * Frame image with the specified with, height, innerBevel and outerBevel.
//     *
//     * @param width The width of the frame.
//     * @param height The height of the frame.
//     * @param innerBevel The inner bevel of the frame.
//     * @param outerBevel The outer bevel of the frame.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun frame(width: Int, height: Int, innerBevel: Int, outerBevel: Int)
//
//    /**
//     * Applies a mathematical expression to the image.
//     *
//     * @param expression The expression to apply.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun fx(expression: String)
//
//    /**
//     * Applies a mathematical expression to the image.
//     *
//     * @param expression The expression to apply.
//     * @param channels The channel(s) to apply the expression to.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun fx(expression: String, channels: Channels)
//
//    /**
//     * Gamma correct image.
//     *
//     * @param gamma The image gamma.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun gammaCorrect(gamma: Double)
//
//    /**
//     * Gamma correct image.
//     *
//     * @param gamma The image gamma for the channel.
//     * @param channels The channel(s) to gamma correct.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun gammaCorrect(gamma: Double, channels: Channels)
//
//    /**
//     * Gaussian blur image.
//     *
//     * @param radius The number of neighbor pixels to be included in the convolution.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun gaussianBlur(radius: Double)
//
//    /**
//     * Gaussian blur image.
//     *
//     * @param radius The number of neighbor pixels to be included in the convolution.
//     * @param channels The channel(s) to blur.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun gaussianBlur(radius: Double, channels: Channels)
//
//    /**
//     * Gaussian blur image.
//     *
//     * @param radius The number of neighbor pixels to be included in the convolution.
//     * @param sigma The standard deviation of the gaussian bell curve.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun gaussianBlur(radius: Double, sigma: Double)
//
//    /**
//     * Gaussian blur image.
//     *
//     * @param radius The number of neighbor pixels to be included in the convolution.
//     * @param sigma The standard deviation of the gaussian bell curve.
//     * @param channels The channel(s) to blur.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun gaussianBlur(radius: Double, sigma: Double, channels: Channels)
//
//    /**
//     * Retrieve the 8bim profile from the image.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     * @return The 8bim profile from the image.
//     */
//    IEightBimProfile? Get8BimProfile();
//
//    /**
//     * Returns the value of the artifact with the specified name.
//     *
//     * @param name The name of the artifact.
//     * @return The value of the artifact with the specified name.
//     */
//    string? GetArtifact(string name);
//

    /**
     * Returns the value of a named image attribute.
     *
     * @param name The name of the attribute.
     * @return The value of a named image attribute.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun getAttribute(name: String): String?

//    /**
//     * Returns the default clipping path. Null will be returned if the image has no clipping path.
//     *
//     * @return The default clipping path. Null will be returned if the image has no clipping path.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    string? GetClippingPath();
//
//    /**
//     * Returns the clipping path with the specified name. Null will be returned if the image has no clipping path.
//     *
//     * @param pathName Name of clipping path resource. If name is preceded by #, use clipping path numbered by name.
//     * @return The clipping path with the specified name. Null will be returned if the image has no clipping path.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    string? GetClippingPath(string pathName);
//
//    /**
//     * Retrieve the color profile from the image.
//     *
//     * @return The color profile from the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IColorProfile? GetColorProfile();
//
//    /**
//     * Retrieve the exif profile from the image.
//     *
//     * @return The exif profile from the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IExifProfile? GetExifProfile();
//
//    /**
//     * Retrieve the iptc profile from the image.
//     *
//     * @return The iptc profile from the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IIptcProfile? GetIptcProfile();
//
//    /**
//     * Retrieve a named profile from the image.
//     *
//     * @param name The name of the profile (e.g. "ICM", "IPTC", or a generic profile name).
//     * @return A named profile from the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IImageProfile? GetProfile(string name);
//
//    /**
//     * Retrieve the xmp profile from the image.
//     *
//     * @return The xmp profile from the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IXmpProfile? GetXmpProfile();
//
//    /**
//     * Converts the colors in the image to gray.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun grayscale()
//
//    /**
//     * Converts the colors in the image to gray.
//     *
//     * @param method The pixel intensity method to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun grayscale(method: PixelIntensityMethod)
//
//    /**
//     * Apply a color lookup table (Hald CLUT) to the image.
//     *
//     * @param image The image to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun haldClut(image: MagickImage)
//
//    /**
//     * Gets a value indicating whether a profile with the specified name already exists on the image.
//     *
//     * @param name The name of the profile.
//     * @return A value indicating whether a profile with the specified name already exists on the image.
//     */
//    public fun hasProfile(name: String): Boolean
//
//    /**
//     * Identifies lines in the image.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun houghLine()
//
//    /**
//     * Identifies lines in the image.
//     *
//     * @param width The width of the neighborhood.
//     * @param height The height of the neighborhood.
//     * @param threshold The line count threshold.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun houghLine(width: Int, height: Int, threshold: Int)
//
//    /**
//     * Implode image (special effect).
//     *
//     * @param amount The extent of the implosion.
//     * @param method Pixel interpolate method.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun implode(amount: Double, method: PixelInterpolateMethod)
//
//    /**
//     * Import pixels from the specified byte array.
//     *
//     * @param data The byte array to read the image data from.
//     * @param settings The import settings to use when importing the pixels.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun importPixels(data: UByteArray, settings: IPixelImportSettings)
//
//    /**
//     * Import pixels from the specified byte array.
//     *
//     * @param data The byte array to read the image data from.
//     * @param offset The offset at which to begin reading data.
//     * @param settings The import settings to use when importing the pixels.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun importPixels(data: UByteArray, offset: Int, settings: IPixelImportSettings)
//
//    /**
//     * Resize image to specified size using the specified interpolation method.
//     *
//     * @param width The new width.
//     * @param height The new height.
//     * @param method Pixel interpolate method.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun interpolativeResize(width: Int, height: Int, method: PixelInterpolateMethod)
//
//    /**
//     * Resize image to specified size using the specified interpolation method.
//     *
//     * @param geometry The geometry to use.
//     * @param method Pixel interpolate method.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun interpolativeResize(geometry: IMagickGeometry, method: PixelInterpolateMethod)
//
//    /**
//     * Resize image to specified size using the specified interpolation method.
//     *
//     * @param percentage The percentage.
//     * @param method Pixel interpolate method.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun interpolativeResize(percentage: Percentage, method: PixelInterpolateMethod)
//
//    /**
//     * Resize image to specified size using the specified interpolation method.
//     *
//     * @param percentageWidth The percentage of the width.
//     * @param percentageHeight The percentage of the height.
//     * @param method Pixel interpolate method.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun interpolativeResize(percentageWidth: Percentage, percentageHeight: Percentage, method: PixelInterpolateMethod)
//
//    /**
//     * Inverse contrast image (diminish intensity differences in image).
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseContrast()
//
//    /**
//     * Applies the reversed level operation to just the specific channels specified. It compresses
//     * the full range of color values, so that they lie between the given black and white points.
//     * Gamma is applied before the values are mapped. Uses a midpoint of 1.0.
//     *
//     * @param blackPointPercentage The darkest color in the image. Colors darker are set to zero.
//     * @param whitePointPercentage The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseLevel(blackPointPercentage: Percentage, whitePointPercentage: Percentage)
//
//    /**
//     * Applies the reversed level operation to just the specific channels specified. It compresses
//     * the full range of color values, so that they lie between the given black and white points.
//     * Gamma is applied before the values are mapped. Uses a midpoint of 1.0.
//     *
//     * @param blackPointPercentage The darkest color in the image. Colors darker are set to zero.
//     * @param whitePointPercentage The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @param channels The channel(s) to level.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseLevel(blackPointPercentage: Percentage, whitePointPercentage: Percentage, channels: Channels)
//
//    /**
//     * Applies the reversed level operation to just the specific channels specified. It compresses
//     * the full range of color values, so that they lie between the given black and white points.
//     * Gamma is applied before the values are mapped.
//     *
//     * @param blackPointPercentage The darkest color in the image. Colors darker are set to zero.
//     * @param whitePointPercentage The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @param midpoint The gamma correction to apply to the image. (Useful range of 0 to 10).
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseLevel(blackPointPercentage: Percentage, whitePointPercentage: Percentage, midpoint: Double)
//
//    /**
//     * Applies the reversed level operation to just the specific channels specified. It compresses
//     * the full range of color values, so that they lie between the given black and white points.
//     * Gamma is applied before the values are mapped.
//     *
//     * @param blackPointPercentage The darkest color in the image. Colors darker are set to zero.
//     * @param whitePointPercentage The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @param midpoint The gamma correction to apply to the image. (Useful range of 0 to 10).
//     * @param channels The channel(s) to level.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseLevel(blackPointPercentage: Percentage, whitePointPercentage: Percentage, midpoint: Double, channels: Channels)
//
//    /**
//     * Adjust the image contrast with an inverse non-linear sigmoidal contrast algorithm.
//     *
//     * @param contrast The contrast.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseSigmoidalContrast(contrast: Double)
//
//    /**
//     * Adjust the image contrast with an inverse non-linear sigmoidal contrast algorithm.
//     *
//     * @param contrast The contrast to use.
//     * @param midpoint The midpoint to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseSigmoidalContrast(contrast: Double, midpoint: Double)
//
//    /**
//     * Adjust the image contrast with an inverse non-linear sigmoidal contrast algorithm.
//     *
//     * @param contrast The contrast to use.
//     * @param midpoint The midpoint to use.
//     * @param channels The channel(s) that should be adjusted.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseSigmoidalContrast(contrast: Double, midpoint: Double, channels: Channels)
//
//    /**
//     * Adjust the image contrast with an inverse non-linear sigmoidal contrast algorithm.
//     *
//     * @param contrast The contrast to use.
//     * @param midpointPercentage The midpoint to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseSigmoidalContrast(contrast: Double, midpointPercentage: Percentage)
//
//    /**
//     * Applies k-means color reduction to an image. This is a colorspace clustering or segmentation technique.
//     *
//     * @param settings The kmeans settings.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun kmeans(settings: IKmeansSettings)
//
//    /**
//     * An edge preserving noise reduction filter.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun kuwahara()
//
//    /**
//     * An edge preserving noise reduction filter.
//     *
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun kuwahara(radius: Double, sigma: Double)
//
//    /**
//     * Adjust the levels of the image by scaling the colors falling between specified white and
//     * black points to the full available quantum range. Uses a midpoint of 1.0.
//     *
//     * @param blackPointPercentage The darkest color in the image. Colors darker are set to zero.
//     * @param whitePointPercentage The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun level(blackPointPercentage: Percentage, whitePointPercentage: Percentage)
//
//    /**
//     * Adjust the levels of the image by scaling the colors falling between specified white and
//     * black points to the full available quantum range. Uses a midpoint of 1.0.
//     *
//     * @param blackPointPercentage The darkest color in the image. Colors darker are set to zero.
//     * @param whitePointPercentage The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @param channels The channel(s) to level.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun level(blackPointPercentage: Percentage, whitePointPercentage: Percentage, channels: Channels)
//
//    /**
//     * Adjust the levels of the image by scaling the colors falling between specified white and
//     * black points to the full available quantum range.
//     *
//     * @param blackPointPercentage The darkest color in the image. Colors darker are set to zero.
//     * @param whitePointPercentage The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @param gamma The gamma correction to apply to the image. (Useful range of 0 to 10).
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun level(blackPointPercentage: Percentage, whitePointPercentage: Percentage, gamma: Double)
//
//    /**
//     * Adjust the levels of the image by scaling the colors falling between specified white and
//     * black points to the full available quantum range.
//     *
//     * @param blackPointPercentage The darkest color in the image. Colors darker are set to zero.
//     * @param whitePointPercentage The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @param gamma The gamma correction to apply to the image. (Useful range of 0 to 10).
//     * @param channels The channel(s) to level.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun level(blackPointPercentage: Percentage, whitePointPercentage: Percentage, gamma: Double, channels: Channels)
//
//    /**
//     * Discards any pixels below the black point and above the white point and levels the remaining pixels.
//     *
//     * @param blackPoint The black point.
//     * @param whitePoint The white point.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun linearStretch(blackPoint: Percentage, whitePoint: Percentage)
//
//    /**
//     * Rescales image with seam carving.
//     *
//     * @param width The new width.
//     * @param height The new height.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun liquidRescale(width: Int, height: Int)
//
//    /**
//     * Rescales image with seam carving.
//     *
//     * @param width The new width.
//     * @param height The new height.
//     * @param deltaX Maximum seam transversal step (0 means straight seams).
//     * @param rigidity Introduce a bias for non-straight seams (typically 0).
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun liquidRescale(width: Int, height: Int, deltaX: Double, rigidity: Double)
//
//    /**
//     * Rescales image with seam carving.
//     *
//     * @param geometry The geometry to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun liquidRescale(geometry: IMagickGeometry)
//
//    /**
//     * Rescales image with seam carving.
//     *
//     * @param percentage The percentage.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun liquidRescale(percentage: Percentage)
//
//    /**
//     * Rescales image with seam carving.
//     *
//     * @param percentageWidth The percentage of the width.
//     * @param percentageHeight The percentage of the height.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun liquidRescale(percentageWidth: Percentage, percentageHeight: Percentage)
//
//    /**
//     * Rescales image with seam carving.
//     *
//     * @param percentageWidth The percentage of the width.
//     * @param percentageHeight The percentage of the height.
//     * @param deltaX Maximum seam transversal step (0 means straight seams).
//     * @param rigidity Introduce a bias for non-straight seams (typically 0).
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun liquidRescale(percentageWidth: Percentage, percentageHeight: Percentage, deltaX: Double, rigidity: Double)
//
//    /**
//     * Local contrast enhancement.
//     *
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param strength The strength of the blur mask.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun localContrast(radius: Double, strength: Percentage)
//
//    /**
//     * Local contrast enhancement.
//     *
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param strength The strength of the blur mask.
//     * @param channels The channel(s) that should be changed.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun localContrast(radius: Double, strength: Percentage, channels: Channels)
//
//    /**
//     * Lower image (darken the edges of an image to give a 3-D lowered effect).
//     *
//     * @param size The size of the edges.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun lower(size: Int)
//
//    /**
//     * Magnify image by integral size.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun magnify()
//
//    /**
//     * Remap image colors with closest color from reference image.
//     *
//     * @param image The image to use.
//     * @return The error informaton.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun map(image: MagickImage): IMagickErrorInfo
//
//    /**
//     * Remap image colors with closest color from reference image.
//     *
//     * @param image The image to use.
//     * @param settings Quantize settings.
//     * @return The error informaton.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun map(image: MagickImage, settings: IQuantizeSettings): IMagickErrorInfo
//
//    /**
//     * Delineate arbitrarily shaped clusters in the image.
//     *
//     * @param size The width and height of the pixels neighborhood.
//     */
//    public fun meanShift(size: Int)
//
//    /**
//     * Delineate arbitrarily shaped clusters in the image.
//     *
//     * @param size The width and height of the pixels neighborhood.
//     * @param colorDistance The color distance.
//     */
//    public fun meanShift(size: Int, colorDistance: Percentage)
//
//    /**
//     * Delineate arbitrarily shaped clusters in the image.
//     *
//     * @param width The width of the pixels neighborhood.
//     * @param height The height of the pixels neighborhood.
//     */
//    public fun meanShift(width: Int, height: Int)
//
//    /**
//     * Delineate arbitrarily shaped clusters in the image.
//     *
//     * @param width The width of the pixels neighborhood.
//     * @param height The height of the pixels neighborhood.
//     * @param colorDistance The color distance.
//     */
//    public fun meanShift(width: Int, height: Int, colorDistance: Percentage)
//
//    /**
//     * Filter image by replacing each pixel component with the median color in a circular neighborhood.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun medianFilter()
//
//    /**
//     * Filter image by replacing each pixel component with the median color in a circular neighborhood.
//     *
//     * @param radius The radius of the pixel neighborhood.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun medianFilter(radius: Int)
//
//    /**
//     * Reduce image by integral size.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun minify()
//
//    /**
//     * Returns the points that form the minimum bounding box around the image foreground objects with
//     * the "Rotating Calipers" algorithm. he method also returns these properties: minimum-bounding-box:area,
//     * minimum-bounding-box:width, minimum-bounding-box:height, and minimum-bounding-box:angle.
//     *
//     * @return The points that form the minimum bounding box around the image foreground objects.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IEnumerable<PointD> MinimumBoundingBox();
//
//    /**
//     * Modulate percent brightness of an image.
//     *
//     * @param brightness The brightness percentage.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun modulate(brightness: Percentage)
//
//    /**
//     * Modulate percent saturation and brightness of an image.
//     *
//     * @param brightness The brightness percentage.
//     * @param saturation The saturation percentage.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun modulate(brightness: Percentage, saturation: Percentage)
//
//    /**
//     * Modulate percent hue, saturation, and brightness of an image.
//     *
//     * @param brightness The brightness percentage.
//     * @param saturation The saturation percentage.
//     * @param hue The hue percentage.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun modulate(brightness: Percentage, saturation: Percentage, hue: Percentage)
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     *
//     * @param method The morphology method.
//     * @param kernel Built-in kernel.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun morphology(method: MorphologyMethod, kernel: Kernel)
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     *
//     * @param method The morphology method.
//     * @param kernel Built-in kernel.
//     * @param channels The channels to apply the kernel to.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun morphology(method: MorphologyMethod, kernel: Kernel, channels: Channels)
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     *
//     * @param method The morphology method.
//     * @param kernel Built-in kernel.
//     * @param channels The channels to apply the kernel to.
//     * @param iterations The number of iterations.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun morphology(method: MorphologyMethod, kernel: Kernel, channels: Channels, iterations: Int)
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     *
//     * @param method The morphology method.
//     * @param kernel Built-in kernel.
//     * @param iterations The number of iterations.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun morphology(method: MorphologyMethod, kernel: Kernel, iterations: Int)
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     *
//     * @param method The morphology method.
//     * @param kernel Built-in kernel.
//     * @param arguments Kernel arguments.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun morphology(method: MorphologyMethod, kernel: Kernel)
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     *
//     * @param method The morphology method.
//     * @param kernel Built-in kernel.
//     * @param arguments Kernel arguments.
//     * @param channels The channels to apply the kernel to.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun morphology(method: MorphologyMethod, kernel: Kernel, channels: Channels)
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     *
//     * @param method The morphology method.
//     * @param kernel Built-in kernel.
//     * @param arguments Kernel arguments.
//     * @param channels The channels to apply the kernel to.
//     * @param iterations The number of iterations.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun morphology(method: MorphologyMethod, kernel: Kernel, channels: Channels, iterations: Int)
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     *
//     * @param method The morphology method.
//     * @param kernel Built-in kernel.
//     * @param arguments Kernel arguments.
//     * @param iterations The number of iterations.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun morphology(method: MorphologyMethod, kernel: Kernel, iterations: Int)
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     *
//     * @param method The morphology method.
//     * @param userKernel User suplied kernel.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun morphology(method: MorphologyMethod, userKernel: String)
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     *
//     * @param method The morphology method.
//     * @param userKernel User suplied kernel.
//     * @param channels The channels to apply the kernel to.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun morphology(method: MorphologyMethod, userKernel: String, channels: Channels)
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     *
//     * @param method The morphology method.
//     * @param userKernel User suplied kernel.
//     * @param channels The channels to apply the kernel to.
//     * @param iterations The number of iterations.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun morphology(method: MorphologyMethod, userKernel: String, channels: Channels, iterations: Int)
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     *
//     * @param method The morphology method.
//     * @param userKernel User suplied kernel.
//     * @param iterations The number of iterations.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun morphology(method: MorphologyMethod, userKernel: String, iterations: Int)
//
//    /**
//     * Applies a kernel to the image according to the given mophology settings.
//     *
//     * @param settings The morphology settings.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun morphology(settings: IMorphologySettings)
//
//    /**
//     * Returns the normalized moments of one or more image channels.
//     *
//     * @return The normalized moments of one or more image channels.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun moments(): IMoments
//
//    /**
//     * Motion blur image with specified blur factor.
//     *
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @param angle The angle the object appears to be comming from (zero degrees is from the right).
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun motionBlur(radius: Double, sigma: Double, angle: Double)
//
//    /**
//     * Negate colors in image.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun negate()
//
//    /**
//     * Negate colors in image for the specified channel.
//     *
//     * @param channels The channel(s) that should be negated.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun negate(channels: Channels)
//
//    /**
//     * Negate the grayscale colors in image.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun negateGrayscale()
//
//    /**
//     * Negate the grayscale colors in image for the specified channel.
//     *
//     * @param channels The channel(s) that should be negated.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun negateGrayscale(channels: Channels)
//
//    /**
//     * Normalize image (increase contrast by normalizing the pixel values to span the full range
//     * of color values).
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun normalize()
//
//    /**
//     * Oilpaint image (image looks like oil painting).
//     */
//    public fun oilPaint()
//
//    /**
//     * Oilpaint image (image looks like oil painting).
//     *
//     * @param radius The radius of the circular neighborhood.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun oilPaint(radius: Double, sigma: Double)
//
//    /**
//     * Perform a ordered dither based on a number of pre-defined dithering threshold maps, but over
//     * multiple intensity levels.
//     *
//    /// <param name="thresholdMap">A string containing the name of the threshold dither map to use,
//    /// followed by zero or more numbers representing the number of color levels tho dither between.</param>
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun orderedDither(thresholdMap: String)
//
//    /**
//     * Perform a ordered dither based on a number of pre-defined dithering threshold maps, but over
//     * multiple intensity levels.
//     *
//    /// <param name="thresholdMap">A string containing the name of the threshold dither map to use,
//    /// followed by zero or more numbers representing the number of color levels tho dither between.</param>
//     * @param channels The channel(s) to dither.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun orderedDither(thresholdMap: String, channels: Channels)
//
//    /**
//     * Set each pixel whose value is less than epsilon to epsilon or -epsilon (whichever is closer)
//     * otherwise the pixel value remains unchanged.
//     *
//     * @param epsilon The epsilon threshold.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun perceptible(epsilon: Double)
//
//    /**
//     * Set each pixel whose value is less than epsilon to epsilon or -epsilon (whichever is closer)
//     * otherwise the pixel value remains unchanged.
//     *
//     * @param epsilon The epsilon threshold.
//     * @param channels The channel(s) to perceptible.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun perceptible(epsilon: Double, channels: Channels)
//
//    /**
//     * Returns the perceptual hash of this image.
//     *
//     * @return The perceptual hash of this image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IPerceptualHash? PerceptualHash();
//

    /**
     * Reads only metadata and not the pixel data.
     *
     * @param data The byte array to read the information from.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun ping(data: UByteArray)

    /**
     * Reads only metadata and not the pixel data.
     *
     * @param data The byte array to read the image data from.
     * @param offset The offset at which to begin reading data.
     * @param count The maximum number of bytes to read.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun ping(
        data: UByteArray,
        offset: UInt,
        count: UInt,
    )

    /**
     * Reads only metadata and not the pixel data.
     *
     * @param file The file to read the image from.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun ping(file: Path)

    /**
     * Reads only metadata and not the pixel data.
     *
     * @param stream The stream to read the image data from.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun ping(stream: Source)

    /**
     * Reads only metadata and not the pixel data.
     *
     * @param fileName The fully qualified name of the image file, or the relative image file name.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun ping(fileName: String)
//
//    /**
//     * Simulates a polaroid picture.
//     *
//     * @param caption The caption to put on the image.
//     * @param angle The angle of image.
//     * @param method Pixel interpolate method.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun polaroid(caption: String, angle: Double, method: PixelInterpolateMethod)
//
//    /**
//     * Reduces the image to a limited number of colors for a "poster" effect.
//     *
//     * @param levels Number of color levels allowed in each channel.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun posterize(levels: Int)
//
//    /**
//     * Reduces the image to a limited number of colors for a "poster" effect.
//     *
//     * @param levels Number of color levels allowed in each channel.
//     * @param channels The channel(s) to posterize.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun posterize(levels: Int, channels: Channels)
//
//    /**
//     * Reduces the image to a limited number of colors for a "poster" effect.
//     *
//     * @param levels Number of color levels allowed in each channel.
//     * @param method Dither method to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun posterize(levels: Int, method: DitherMethod)
//
//    /**
//     * Reduces the image to a limited number of colors for a "poster" effect.
//     *
//     * @param levels Number of color levels allowed in each channel.
//     * @param method Dither method to use.
//     * @param channels The channel(s) to posterize.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun posterize(levels: Int, method: DitherMethod, channels: Channels)
//
//    /**
//     * Sets an internal option to preserve the color type.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun preserveColorType()
//
//    /**
//     * Quantize image (reduce number of colors).
//     *
//     * @param settings Quantize settings.
//     * @return The error information.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IMagickErrorInfo? Quantize(IQuantizeSettings settings);
//
//    /**
//     * Raise image (lighten the edges of an image to give a 3-D raised effect).
//     *
//     * @param size The size of the edges.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun raise(size: Int)
//
//    /**
//     * Changes the value of individual pixels based on the intensity of each pixel compared to a
//     * random threshold. The result is a low-contrast, two color image.
//     *
//     * @param percentageLow The low threshold.
//     * @param percentageHigh The high threshold.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun randomThreshold(percentageLow: Percentage, percentageHigh: Percentage)
//
//    /**
//     * Changes the value of individual pixels based on the intensity of each pixel compared to a
//     * random threshold. The result is a low-contrast, two color image.
//     *
//     * @param percentageLow The low threshold.
//     * @param percentageHigh The high threshold.
//     * @param channels The channel(s) to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun randomThreshold(percentageLow: Percentage, percentageHigh: Percentage, channels: Channels)
//
//    /**
//     * Applies soft and hard thresholding.
//     *
//     * @param percentageLowBlack Defines the minimum black threshold value.
//     * @param percentageLowWhite Defines the minimum white threshold value.
//     * @param percentageHighWhite Defines the maximum white threshold value.
//     * @param percentageHighBlack Defines the maximum black threshold value.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun rangeThreshold(percentageLowBlack: Percentage, percentageLowWhite: Percentage, percentageHighWhite: Percentage, percentageHighBlack: Percentage)
//

    /**
     * Read single image frame.
     *
     * @param data The byte array to read the image data from.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun read(data: UByteArray)

    /**
     * Read single image frame.
     *
     * @param data The byte array to read the image data from.
     * @param offset The offset at which to begin reading data.
     * @param count The maximum number of bytes to read.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun read(
        data: UByteArray,
        offset: UInt,
        count: UInt,
    )

    /**
     * Read single image frame.
     *
     * @param data The byte array to read the image data from.
     * @param offset The offset at which to begin reading data.
     * @param count The maximum number of bytes to read.
     * @param format The format to use.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun read(
        data: UByteArray,
        offset: UInt,
        count: UInt,
        format: MagickFormat,
    )

    /**
     * Read single image frame.
     *
     * @param data The byte array to read the image data from.
     * @param format The format to use.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun read(
        data: UByteArray,
        format: MagickFormat,
    )

    /**
     * Read single image frame.
     *
     * @param file The file to read the image from.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun read(file: Path)

    /**
     * Read single image frame.
     *
     * @param file The file to read the image from.
     * @param width The width.
     * @param height The height.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun read(
        file: Path,
        width: UInt,
        height: UInt,
    )

    /**
     * Read single image frame.
     *
     * @param file The file to read the image from.
     * @param format The format to use.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun read(
        file: Path,
        format: MagickFormat,
    )

    /**
     * Read single image frame.
     *
     * @param stream The stream to read the image data from.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun read(stream: Source)

    /**
     * Read single image frame.
     *
     * @param stream The stream to read the image data from.
     * @param format The format to use.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun read(
        stream: Source,
        format: MagickFormat,
    )

    /**
     * Read single image frame.
     *
     * @param fileName The fully qualified name of the image file, or the relative image file name.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun read(fileName: String)

    /**
     * Read single image frame.
     *
     * @param fileName The fully qualified name of the image file, or the relative image file name.
     * @param width The width.
     * @param height The height.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun read(
        fileName: String,
        width: UInt,
        height: UInt,
    )

    /**
     * Read single image frame.
     *
     * @param fileName The fully qualified name of the image file, or the relative image file name.
     * @param format The format to use.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun read(
        fileName: String,
        format: MagickFormat,
    )
//
//    /**
//     * Read single image frame.
//     *
//     * @param file The file to read the image from.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun readAsync(file: Path): Task
//
//    /**
//     * Read single image frame.
//     *
//     * @param file The file to read the image from.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun readAsync(file: Path, cancellationToken: CancellationToken): Task
//
//    /**
//     * Read single image frame.
//     *
//     * @param file The file to read the image from.
//     * @param format The format to use.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun readAsync(file: Path, format: MagickFormat): Task
//
//    /**
//     * Read single image frame.
//     *
//     * @param file The file to read the image from.
//     * @param format The format to use.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun readAsync(file: Path, format: MagickFormat, cancellationToken: CancellationToken): Task
//
//    /**
//     * Read single image frame.
//     *
//     * @param stream The stream to read the image data from.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun readAsync(stream: Source): Task
//
//    /**
//     * Read single image frame.
//     *
//     * @param stream The stream to read the image data from.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun readAsync(stream: Source, cancellationToken: CancellationToken): Task
//
//    /**
//     * Read single image frame.
//     *
//     * @param stream The stream to read the image data from.
//     * @param format The format to use.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun readAsync(stream: Source, format: MagickFormat): Task
//
//    /**
//     * Read single image frame.
//     *
//     * @param stream The stream to read the image data from.
//     * @param format The format to use.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun readAsync(stream: Source, format: MagickFormat, cancellationToken: CancellationToken): Task
//
//    /**
//     * Read single image frame.
//     *
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun readAsync(fileName: String): Task
//
//    /**
//     * Read single image frame.
//     *
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun readAsync(fileName: String, cancellationToken: CancellationToken): Task
//
//    /**
//     * Read single image frame.
//     *
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param format The format to use.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun readAsync(fileName: String, format: MagickFormat): Task
//
//    /**
//     * Read single image frame.
//     *
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param format The format to use.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun readAsync(fileName: String, format: MagickFormat, cancellationToken: CancellationToken): Task
//
//    /**
//     * Reduce noise in image using a noise peak elimination filter.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun reduceNoise()
//
//    /**
//     * Reduce noise in image using a noise peak elimination filter.
//     *
//     * @param order The order to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun reduceNoise(order: Int)
//
//    /**
//     * Associates a mask with the image as defined by the specified region.
//     *
//     * @param region The mask region.
//     */
//    public fun regionMask(region: IMagickGeometry)

    /**
     * Removes the artifact with the specified name.
     *
     * @param name The name of the artifact.
     */
    public fun removeArtifact(name: String)

    /**
     * Removes the attribute with the specified name.
     *
     * @param name The name of the attribute.
     */
    public fun removeAttribute(name: String)
//
//    /**
//     * Removes the region mask of the image.
//     */
//    public fun removeRegionMask()
//
//    /**
//     * Remove a profile from the image.
//     *
//     * @param profile The profile to remove.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun removeProfile(profile: IImageProfile)
//
//    /**
//     * Remove a named profile from the image.
//     *
//     * @param name The name of the profile (e.g. "ICM", "IPTC", or a generic profile name).
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun removeProfile(name: String)
//
//    /**
//     * Removes the associated read mask of the image.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun removeReadMask()
//
//    /**
//     * Removes the associated write mask of the image.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun removeWriteMask()
//
//    /**
//     * Resets the page property of this image.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun rePage()
//
//    /**
//     * Resize image in terms of its pixel size.
//     *
//     * @param resolutionX The new X resolution.
//     * @param resolutionY The new Y resolution.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun resample(resolutionX: Double, resolutionY: Double)
//
//    /**
//     * Resize image in terms of its pixel size.
//     *
//     * @param density The density to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun resample(density: PointD)
//
//    /**
//     * Resize image to specified size.
//     * <para />
//     * Resize will fit the image into the requested size. It does NOT fill, the requested box size.
//     * Use the [IMagickGeometry] overload for more control over the resulting size.
//     *
//     * @param width The new width.
//     * @param height The new height.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun resize(width: Int, height: Int)
//
//    /**
//     * Resize image to specified geometry.
//     *
//     * @param geometry The geometry to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun resize(geometry: IMagickGeometry)
//
//    /**
//     * Resize image to specified percentage.
//     *
//     * @param percentage The percentage.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun resize(percentage: Percentage)
//
//    /**
//     * Resize image to specified percentage.
//     *
//     * @param percentageWidth The percentage of the width.
//     * @param percentageHeight The percentage of the height.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun resize(percentageWidth: Percentage, percentageHeight: Percentage)
//
//    /**
//     * Roll image (rolls image vertically and horizontally).
//     *
//     * @param x The X offset from origin.
//     * @param y The Y offset from origin.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun roll(x: Int, y: Int)
//
//    /**
//     * Rotate image clockwise by specified number of degrees.
//     *
//    /// <remarks>Specify a negative number for <paramref name="degrees"/> to rotate counter-clockwise.</remarks>
//     * @param degrees The number of degrees to rotate (positive to rotate clockwise, negative to rotate counter-clockwise).
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun rotate(degrees: Double)
//
//    /**
//     * Rotational blur image.
//     *
//     * @param angle The angle to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun rotationalBlur(angle: Double)
//
//    /**
//     * Rotational blur image.
//     *
//     * @param angle The angle to use.
//     * @param channels The channel(s) to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun rotationalBlur(angle: Double, channels: Channels)
//
//    /**
//     * Resize image by using pixel sampling algorithm.
//     * <para />
//     * Resize will fit the image into the requested size. It does NOT fill, the requested box size.
//     * Use the [IMagickGeometry] overload for more control over the resulting size.
//     *
//     * @param width The new width.
//     * @param height The new height.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sample(width: Int, height: Int)
//
//    /**
//     * Resize image by using pixel sampling algorithm.
//     *
//     * @param geometry The geometry to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sample(geometry: IMagickGeometry)
//
//    /**
//     * Resize image by using pixel sampling algorithm to the specified percentage.
//     *
//     * @param percentage The percentage.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sample(percentage: Percentage)
//
//    /**
//     * Resize image by using pixel sampling algorithm to the specified percentage.
//     *
//     * @param percentageWidth The percentage of the width.
//     * @param percentageHeight The percentage of the height.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sample(percentageWidth: Percentage, percentageHeight: Percentage)
//
//    /**
//     * Resize image by using simple ratio algorithm.
//     * <para />
//     * Resize will fit the image into the requested size. It does NOT fill, the requested box size.
//     * Use the [IMagickGeometry] overload for more control over the resulting size.
//     *
//     * @param width The new width.
//     * @param height The new height.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun scale(width: Int, height: Int)
//
//    /**
//     * Resize image by using simple ratio algorithm.
//     *
//     * @param geometry The geometry to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun scale(geometry: IMagickGeometry)
//
//    /**
//     * Resize image by using simple ratio algorithm to the specified percentage.
//     *
//     * @param percentage The percentage.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun scale(percentage: Percentage)
//
//    /**
//     * Resize image by using simple ratio algorithm to the specified percentage.
//     *
//     * @param percentageWidth The percentage of the width.
//     * @param percentageHeight The percentage of the height.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun scale(percentageWidth: Percentage, percentageHeight: Percentage)
//
//    /**
//     * Segment (coalesce similar image components) by analyzing the histograms of the color
//     * components and identifying units that are homogeneous with the fuzzy c-means technique.
//     * Also uses QuantizeColorSpace and Verbose image attributes.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun segment()
//
//    /**
//     * Segment (coalesce similar image components) by analyzing the histograms of the color
//     * components and identifying units that are homogeneous with the fuzzy c-means technique.
//     * Also uses QuantizeColorSpace and Verbose image attributes.
//     *
//     * @param quantizeColorSpace Quantize colorspace.
//    /// <param name="clusterThreshold">This represents the minimum number of pixels contained in
//    /// a hexahedra before it can be considered valid (expressed as a percentage).</param>
//    /// <param name="smoothingThreshold">The smoothing threshold eliminates noise in the second
//    /// derivative of the histogram. As the value is increased, you can expect a smoother second
//    /// derivative.</param>
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun segment(quantizeColorSpace: ColorSpace, clusterThreshold: Double, smoothingThreshold: Double)
//
//    /**
//     * Selectively blur pixels within a contrast threshold. It is similar to the unsharpen mask
//     * that sharpens everything with contrast above a certain threshold.
//     *
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Gaussian, in pixels.
//     * @param threshold Only pixels within this contrast threshold are included in the blur operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun selectiveBlur(radius: Double, sigma: Double, threshold: Double)
//
//    /**
//     * Selectively blur pixels within a contrast threshold. It is similar to the unsharpen mask
//     * that sharpens everything with contrast above a certain threshold.
//     *
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Gaussian, in pixels.
//     * @param threshold Only pixels within this contrast threshold are included in the blur operation.
//     * @param channels The channel(s) to blur.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun selectiveBlur(radius: Double, sigma: Double, threshold: Double, channels: Channels)
//
//    /**
//     * Selectively blur pixels within a contrast threshold. It is similar to the unsharpen mask
//     * that sharpens everything with contrast above a certain threshold.
//     *
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Gaussian, in pixels.
//     * @param thresholdPercentage Only pixels within this contrast threshold are included in the blur operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun selectiveBlur(radius: Double, sigma: Double, thresholdPercentage: Percentage)
//
//    /**
//     * Selectively blur pixels within a contrast threshold. It is similar to the unsharpen mask
//     * that sharpens everything with contrast above a certain threshold.
//     *
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Gaussian, in pixels.
//     * @param thresholdPercentage Only pixels within this contrast threshold are included in the blur operation.
//     * @param channels The channel(s) to blur.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun selectiveBlur(radius: Double, sigma: Double, thresholdPercentage: Percentage, channels: Channels)
//
//    /**
//     * Applies a special effect to the image, similar to the effect achieved in a photo darkroom
//     * by sepia toning.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sepiaTone()
//
//    /**
//     * Applies a special effect to the image, similar to the effect achieved in a photo darkroom
//     * by sepia toning.
//     *
//     * @param threshold The tone threshold.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sepiaTone(threshold: Percentage)
//
    /**
     * Inserts the artifact with the specified name and value into the artifact tree of the image.
     *
     * @param name The name of the artifact.
     * @param value The value of the artifact.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun setArtifact(name: String, value: String)

    /**
     * Inserts the artifact with the specified name and value into the artifact tree of the image.
     *
     * @param name The name of the artifact.
     * @param flag The value of the artifact.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun setArtifact(name: String, flag: Boolean)

//    /**
//     * Lessen (or intensify) when adding noise to an image.
//     *
//     * @param attenuate The attenuate value.
//     */
//    public fun setAttenuate(attenuate: Double)
//
    /**
     * Sets a named image attribute.
     *
     * @param name The name of the attribute.
     * @param value The value of the attribute.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun setAttribute(name: String, value: String)

//    /**
//     * Sets a named image attribute.
//     *
//     * @param name The name of the attribute.
//     * @param flag The value of the attribute.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun setAttribute(name: String, flag: Boolean)
//
//    /**
//     * Set the bit depth (bits allocated to red/green/blue components).
//     *
//     * @param value The depth.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun setBitDepth(value: Int)
//
//    /**
//     * Set the bit depth (bits allocated to red/green/blue components) of the specified channel.
//     *
//     * @param value The depth.
//     * @param channels The channel to set the depth for.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun setBitDepth(value: Int, channels: Channels)
//
//    /**
//     * Sets the default clipping path.
//     *
//     * @param value The clipping path.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun setClippingPath(value: String)
//
//    /**
//     * Sets the clipping path with the specified name.
//     *
//     * @param value The clipping path.
//     * @param pathName Name of clipping path resource. If name is preceded by #, use clipping path numbered by name.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun setClippingPath(value: String, pathName: String)
//
//    /**
//     * Gets the compression of the image. This method should only be used when the encoder uses the compression of the image. For
//     * most usecases Setting.Compression should be used instead.
//     *
//     * @param compression The compression method.
//     */
//    public fun setCompression(compression: CompressionMethod)
//
//    /**
//     * Set the specified profile of the image. If a profile with the same name already exists it will be overwritten.
//     *
//     * @param profile The profile to set.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun setProfile(profile: IImageProfile)
//
//    /**
//     * Set the specified profile of the image. If a profile with the same name already exists it will be overwritten.
//     *
//     * @param profile The profile to set.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun setProfile(profile: IColorProfile)
//
//    /**
//     * Set the specified profile of the image. If a profile with the same name already exists it will be overwritten.
//     *
//     * @param profile The profile to set.
//     * @param mode The color transformation mode.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun setProfile(profile: IColorProfile, mode: ColorTransformMode)
//
//    /**
//     * Sets the associated read mask of the image. The mask must be the same dimensions as the image and
//     * only contain the colors black and white.
//     *
//     * @param image The image that contains the read mask.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun setReadMask(image: MagickImage)
//
//    /**
//     * Sets the associated write mask of the image. The mask must be the same dimensions as the image and
//     * only contains the colors black and white or have grayscale values that will cause blended updates of
//     * the image.
//     *
//     * @param image The image that contains the write mask.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun setWriteMask(image: MagickImage)
//
//    /**
//     * Simulate an image shadow.
//     *
//     * @param x the shadow x-offset.
//     * @param y the shadow y-offset.
//     * @param sigma The standard deviation of the Gaussian, in pixels.
//     * @param alpha Transparency percentage.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun shadow(x: Int, y: Int, sigma: Double, alpha: Percentage)
//
//    /**
//     * Sharpen pixels in image.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sharpen()
//
//    /**
//     * Sharpen pixels in image.
//     *
//     * @param channels The channel(s) that should be sharpened.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sharpen(channels: Channels)
//
//    /**
//     * Sharpen pixels in image.
//     *
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sharpen(radius: Double, sigma: Double)
//
//    /**
//     * Sharpen pixels in image.
//     *
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @param channels The channel(s) that should be sharpened.
//     */
//    public fun sharpen(radius: Double, sigma: Double, channels: Channels)
//
//    /**
//     * Shave pixels from image edges.
//     *
//     * @param size The size of to shave of the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun shave(size: Int)
//
//    /**
//     * Shave pixels from image edges.
//     *
//     * @param leftRight The number of pixels to shave left and right.
//     * @param topBottom The number of pixels to shave top and bottom.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun shave(leftRight: Int, topBottom: Int)
//
//    /**
//     * Shear image (create parallelogram by sliding image by X or Y axis).
//     *
//     * @param xAngle Specifies the number of x degrees to shear the image.
//     * @param yAngle Specifies the number of y degrees to shear the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun shear(xAngle: Double, yAngle: Double)
//
//    /**
//     * Adjust the image contrast with a non-linear sigmoidal contrast algorithm.
//     *
//     * @param contrast The contrast.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sigmoidalContrast(contrast: Double)
//
//    /**
//     * Adjust the image contrast with a non-linear sigmoidal contrast algorithm.
//     *
//     * @param contrast The contrast to use.
//     * @param midpoint The midpoint to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sigmoidalContrast(contrast: Double, midpoint: Double)
//
//    /**
//     * Adjust the image contrast with a non-linear sigmoidal contrast algorithm.
//     *
//     * @param contrast The contrast to use.
//     * @param midpoint The midpoint to use.
//     * @param channels The channel(s) that should be adjusted.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sigmoidalContrast(contrast: Double, midpoint: Double, channels: Channels)
//
//    /**
//     * Adjust the image contrast with a non-linear sigmoidal contrast algorithm.
//     *
//     * @param contrast The contrast to use.
//     * @param midpointPercentage The midpoint to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sigmoidalContrast(contrast: Double, midpointPercentage: Percentage)
//
//    /**
//     * Simulates a pencil sketch.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sketch()
//
//    /**
//     * Simulates a pencil sketch. We convolve the image with a Gaussian operator of the given
//     * radius and standard deviation (sigma). For reasonable results, radius should be larger than sigma.
//     * Use a radius of 0 and sketch selects a suitable radius for you.
//     *
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @param angle Apply the effect along this angle.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sketch(radius: Double, sigma: Double, angle: Double)
//
//    /**
//     * Solarize image (similar to effect seen when exposing a photographic film to light during
//     * the development process).
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun solarize()
//
//    /**
//     * Solarize image (similar to effect seen when exposing a photographic film to light during
//     * the development process).
//     *
//     * @param factor The factor to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun solarize(factor: Double)
//
//    /**
//     * Sort pixels within each scanline in ascending order of intensity.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sortPixels()
//
//    /**
//     * Solarize image (similar to effect seen when exposing a photographic film to light during
//     * the development process).
//     *
//     * @param factorPercentage The factor to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun solarize(factorPercentage: Percentage)
//
//    /**
//     * Splice the background color into the image.
//     *
//     * @param geometry The geometry to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun splice(geometry: IMagickGeometry)
//
//    /**
//     * Spread pixels randomly within image.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun spread()
//
//    /**
//     * Spread pixels randomly within image by specified amount.
//     *
//     * @param radius Choose a random pixel in a neighborhood of this extent.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun spread(radius: Double)
//
//    /**
//     * Spread pixels randomly within image by specified amount.
//     *
//     * @param method Pixel interpolate method.
//     * @param radius Choose a random pixel in a neighborhood of this extent.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun spread(method: PixelInterpolateMethod, radius: Double)
//
//    /**
//     * Makes each pixel the min / max / median / mode / etc. of the neighborhood of the specified width
//     * and height.
//     *
//     * @param type The statistic type.
//     * @param width The width of the pixel neighborhood.
//     * @param height The height of the pixel neighborhood.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun statistic(type: StatisticType, width: Int, height: Int)
//
//    /**
//     * Returns the image statistics.
//     *
//     * @return The image statistics.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun statistics(): IStatistics
//
//    /**
//     * Returns the image statistics.
//     *
//     * @return The image statistics.
//     * @param channels The channel(s) that should be used.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun statistics(channels: Channels): IStatistics
//
//    /**
//     * Shade image using distant light source.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun shade()
//
//    /**
//     * Shade image using distant light source.
//     *
//     * @param azimuth The azimuth of the light source direction.
//     * @param elevation The elevation of the light source direction.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun shade(azimuth: Double, elevation: Double)
//
//    /**
//     * Shade image using distant light source.
//     *
//     * @param azimuth The azimuth of the light source direction.
//     * @param elevation The elevation of the light source direction.
//     * @param channels The channel(s) that should be shaded.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun shade(azimuth: Double, elevation: Double, channels: Channels)
//
//    /**
//     * Shade image using distant light source and make it grayscale.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun shadeGrayscale()
//
//    /**
//     * Shade image using distant light source and make it grayscale.
//     *
//     * @param azimuth The azimuth of the light source direction.
//     * @param elevation The elevation of the light source direction.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun shadeGrayscale(azimuth: Double, elevation: Double)
//
//    /**
//     * Shade image using distant light source and make it grayscale.
//     *
//     * @param azimuth The azimuth of the light source direction.
//     * @param elevation The elevation of the light source direction.
//     * @param channels The channel(s) that should be shaded.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun shadeGrayscale(azimuth: Double, elevation: Double, channels: Channels)
//
//    /**
//     * Simulate an image shadow.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun shadow()
//
//    /**
//     * Add a digital watermark to the image (based on second image).
//     *
//     * @param watermark The image to use as a watermark.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun stegano(watermark: MagickImage)
//
//    /**
//     * Create an image which appears in stereo when viewed with red-blue glasses (Red image on
//     * left, blue on right).
//     *
//     * @param rightImage The image to use as the right part of the resulting image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun stereo(rightImage: MagickImage)
//
//    /**
//     * Strips an image of all profiles and comments.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun strip()
//
//    /**
//     * Swirl image (image pixels are rotated by degrees).
//     *
//     * @param degrees The number of degrees.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun swirl(degrees: Double)
//
//    /**
//     * Swirl image (image pixels are rotated by degrees).
//     *
//     * @param method Pixel interpolate method.
//     * @param degrees The number of degrees.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun swirl(method: PixelInterpolateMethod, degrees: Double)
//
//    /**
//     * Channel a texture on image background.
//     *
//     * @param image The image to use as a texture on the image background.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun texture(image: MagickImage)
//
//    /**
//     * Threshold image.
//     *
//     * @param percentage The threshold percentage.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun threshold(percentage: Percentage)
//
//    /**
//     * Threshold image.
//     *
//     * @param percentage The threshold percentage.
//     * @param channels The channel(s) that should be thresholded.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun threshold(percentage: Percentage, channels: Channels)
//
//    /**
//     * Resize image to thumbnail size and remove all the image profiles except the icc/icm profile.
//     * <para />
//     * Resize will fit the image into the requested size. It does NOT fill, the requested box size.
//     * Use the [IMagickGeometry] overload for more control over the resulting size.
//     *
//     * @param width The new width.
//     * @param height The new height.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun thumbnail(width: Int, height: Int)
//
//    /**
//     * Resize image to thumbnail size and remove all the image profiles except the icc/icm profile.
//     *
//     * @param geometry The geometry to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun thumbnail(geometry: IMagickGeometry)
//
//    /**
//     * Resize image to thumbnail size and remove all the image profiles except the icc/icm profile.
//     *
//     * @param percentage The percentage.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun thumbnail(percentage: Percentage)
//
//    /**
//     * Resize image to thumbnail size and remove all the image profiles except the icc/icm profile.
//     *
//     * @param percentageWidth The percentage of the width.
//     * @param percentageHeight The percentage of the height.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun thumbnail(percentageWidth: Percentage, percentageHeight: Percentage)
//
//    /**
//     * Compose an image repeated across and down the image.
//     *
//     * @param image The image to composite with this image.
//     * @param compose The algorithm to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun tile(image: MagickImage, compose: CompositeOperator)
//
//    /**
//     * Compose an image repeated across and down the image.
//     *
//     * @param image The image to composite with this image.
//     * @param compose The algorithm to use.
//     * @param args The arguments for the algorithm (compose:args).
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun tile(image: MagickImage, compose: CompositeOperator)
//
//    /**
//     * Converts this instance to a base64 [string].
//     *
//     * @return A base64 [string].
//     */
//    public fun toBase64(): String
//
//    /**
//     * Converts this instance to a base64 [string].
//     *
//     * @param format The format to use.
//     * @return A base64 [string].
//     */
//    public fun toBase64(format: MagickFormat): String
//
//    /**
//     * Converts this instance to a [byte] array.
//     *
//     * @return A [byte] array.
//     */
//    public fun toUByteArray(): UByteArray
//
//    /**
//     * Converts this instance to a [byte] array.
//     *
//     * @param defines The defines to set.
//     * @return A [byte] array.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun toUByteArray(defines: IWriteDefines): UByteArray
//
//    /**
//     * Converts this instance to a [byte] array.
//     *
//     * @param format The format to use.
//     * @return A [byte] array.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun toUByteArray(format: MagickFormat): UByteArray
//
//    /**
//     * Transforms the image from the colorspace of the source profile to the target profile. This
//     * requires the image to have a color profile. Nothing will happen if the image has no color profile.
//     *
//     * @param target The target color profile.
//     * @return True when the colorspace was transformed otherwise false.
//     */
//    public fun transformColorSpace(target: IColorProfile): Boolean
//
//    /**
//     * Transforms the image from the colorspace of the source profile to the target profile. This
//     * requires the image to have a color profile. Nothing will happen if the image has no color profile.
//     *
//     * @param target The target color profile.
//     * @param mode The color transformation mode.
//     * @return True when the colorspace was transformed otherwise false.
//     */
//    public fun transformColorSpace(target: IColorProfile, mode: ColorTransformMode): Boolean
//
//    /**
//     * Transforms the image from the colorspace of the source profile to the target profile. The
//     * source profile will only be used if the image does not contain a color profile. Nothing
//     * will happen if the source profile has a different colorspace then that of the image.
//     *
//     * @param source The source color profile.
//     * @param target The target color profile.
//     * @return True when the colorspace was transformed otherwise false.
//     */
//    public fun transformColorSpace(source: IColorProfile, target: IColorProfile): Boolean
//
//    /**
//     * Transforms the image from the colorspace of the source profile to the target profile. The
//     * source profile will only be used if the image does not contain a color profile. Nothing
//     * will happen if the source profile has a different colorspace then that of the image.
//     *
//     * @param source The source color profile.
//     * @param target The target color profile.
//     * @param mode The color transformation mode.
//     * @return True when the colorspace was transformed otherwise false.
//     */
//    public fun transformColorSpace(source: IColorProfile, target: IColorProfile, mode: ColorTransformMode): Boolean
//
//    /**
//     * Creates a horizontal mirror image by reflecting the pixels around the central y-axis while
//     * rotating them by 90 degrees.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun transpose()
//
//    /**
//     * Creates a vertical mirror image by reflecting the pixels around the central x-axis while
//     * rotating them by 270 degrees.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun transverse()
//
//    /**
//     * Trim edges that are the background color from the image.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun trim()
//
//    /**
//     * Trim the specified edges that are the background color from the image.
//     *
//     * @param edges The edges that need to be trimmed.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun trim(vararg edges: Gravity)
//
//    /**
//     * Trim edges that are the background color from the image.
//     *
//     * @param percentBackground The percentage of background pixels permitted in the outer rows and columns.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun trim(percentBackground: Percentage)
//
//    /**
//     * Replace image with a sharpened version of the original image using the unsharp mask algorithm.
//     *
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun unsharpMask(radius: Double, sigma: Double)
//
//    /**
//     * Replace image with a sharpened version of the original image using the unsharp mask algorithm.
//     *
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @param channels The channel(s) that should be sharpened.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun unsharpMask(radius: Double, sigma: Double, channels: Channels)
//
//    /**
//     * Replace image with a sharpened version of the original image using the unsharp mask algorithm.
//     *
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//    /// <param name="amount">The percentage of the difference between the original and the blur image
//    /// that is added back into the original.</param>
//     * @param threshold The threshold in pixels needed to apply the diffence amount.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun unsharpMask(radius: Double, sigma: Double, amount: Double, threshold: Double)
//
//    /**
//     * Replace image with a sharpened version of the original image using the unsharp mask algorithm.
//     *
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//    /// <param name="amount">The percentage of the difference between the original and the blur image
//    /// that is added back into the original.</param>
//     * @param threshold The threshold in pixels needed to apply the diffence amount.
//     * @param channels The channel(s) that should be sharpened.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun unsharpMask(radius: Double, sigma: Double, amount: Double, threshold: Double, channels: Channels)
//
//    /**
//     * Softens the edges of the image in vignette style.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun vignette()
//
//    /**
//     * Softens the edges of the image in vignette style.
//     *
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @param x The x ellipse offset.
//     * @param y the y ellipse offset.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun vignette(radius: Double, sigma: Double, x: Int, y: Int)
//
//    /**
//     * Map image pixels to a sine wave.
//     *
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun wave()
//
//    /**
//     * Map image pixels to a sine wave.
//     *
//     * @param method Pixel interpolate method.
//     * @param amplitude The amplitude.
//     * @param length The length of the wave.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun wave(method: PixelInterpolateMethod, amplitude: Double, length: Double)
//
//    /**
//     * Removes noise from the image using a wavelet transform.
//     *
//     * @param thresholdPercentage The threshold for smoothing.
//     */
//    public fun waveletDenoise(thresholdPercentage: Percentage)
//
//    /**
//     * Removes noise from the image using a wavelet transform.
//     *
//     * @param thresholdPercentage The threshold for smoothing.
//     * @param softness Attenuate the smoothing threshold.
//     */
//    public fun waveletDenoise(thresholdPercentage: Percentage, softness: Double)
//
//    /**
//     * Apply a white balancing to an image according to a grayworld assumption in the LAB colorspace.
//     */
//    public fun whiteBalance()
//
//    /**
//     * Apply a white balancing to an image according to a grayworld assumption in the LAB colorspace.
//     *
//     * @param vibrance The vibrance.
//     */
//    public fun whiteBalance(vibrance: Percentage)
//
//    /**
//     * Forces all pixels above the threshold into white while leaving all pixels at or below
//     * the threshold unchanged.
//     *
//     * @param threshold The threshold to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun whiteThreshold(threshold: Percentage)
//
//    /**
//     * Forces all pixels above the threshold into white while leaving all pixels at or below
//     * the threshold unchanged.
//     *
//     * @param threshold The threshold to use.
//     * @param channels The channel(s) to make black.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun whiteThreshold(threshold: Percentage, channels: Channels)
//
//    /**
//     * Writes the image to the specified file.
//     *
//     * @param file The file to write the image to.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun write(file: Path)
//
//    /**
//     * Writes the image to the specified file.
//     *
//     * @param file The file to write the image to.
//     * @param defines The defines to set.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun write(file: Path, defines: IWriteDefines)
//
//    /**
//     * Writes the image to the specified file.
//     *
//     * @param file The file to write the image to.
//     * @param format The format to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun write(file: Path, format: MagickFormat)
//
//    /**
//     * Writes the image to the specified stream.
//     *
//     * @param stream The stream to write the image data to.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun write(stream: Source)
//
//    /**
//     * Writes the image to the specified stream.
//     *
//     * @param stream The stream to write the image data to.
//     * @param defines The defines to set.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun write(stream: Source, defines: IWriteDefines)
//
//    /**
//     * Writes the image to the specified stream.
//     *
//     * @param stream The stream to write the image data to.
//     * @param format The format to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun write(stream: Source, format: MagickFormat)
//
//    /**
//     * Writes the image to the specified file name.
//     *
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun write(fileName: String)
//
//    /**
//     * Writes the image to the specified file name.
//     *
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param defines The defines to set.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun write(fileName: String, defines: IWriteDefines)
//
//    /**
//     * Writes the image to the specified file name.
//     *
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param format The format to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun write(fileName: String, format: MagickFormat)
//
//    /**
//     * Writes the image to the specified file.
//     *
//     * @param file The file to write the image to.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun writeAsync(file: Path): Task
//
//    /**
//     * Writes the image to the specified file.
//     *
//     * @param file The file to write the image to.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun writeAsync(file: Path, cancellationToken: CancellationToken): Task
//
//    /**
//     * Writes the image to the specified file.
//     *
//     * @param file The file to write the image to.
//     * @param defines The defines to set.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun writeAsync(file: Path, defines: IWriteDefines): Task
//
//    /**
//     * Writes the image to the specified file.
//     *
//     * @param file The file to write the image to.
//     * @param defines The defines to set.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun writeAsync(file: Path, defines: IWriteDefines, cancellationToken: CancellationToken): Task
//
//    /**
//     * Writes the image to the specified file.
//     *
//     * @param file The file to write the image to.
//     * @param format The format to use.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun writeAsync(file: Path, format: MagickFormat): Task
//
//    /**
//     * Writes the image to the specified file.
//     *
//     * @param file The file to write the image to.
//     * @param format The format to use.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun writeAsync(file: Path, format: MagickFormat, cancellationToken: CancellationToken): Task
//
//    /**
//     * Writes the image to the specified stream.
//     *
//     * @param stream The stream to write the image data to.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun writeAsync(stream: Source): Task
//
//    /**
//     * Writes the image to the specified stream.
//     *
//     * @param stream The stream to write the image data to.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun writeAsync(stream: Source, cancellationToken: CancellationToken): Task
//
//    /**
//     * Writes the image to the specified stream.
//     *
//     * @param stream The stream to write the image data to.
//     * @param defines The defines to set.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun writeAsync(stream: Source, defines: IWriteDefines): Task
//
//    /**
//     * Writes the image to the specified stream.
//     *
//     * @param stream The stream to write the image data to.
//     * @param defines The defines to set.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun writeAsync(stream: Source, defines: IWriteDefines, cancellationToken: CancellationToken): Task
//
//    /**
//     * Writes the image to the specified stream.
//     *
//     * @param stream The stream to write the image data to.
//     * @param format The format to use.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun writeAsync(stream: Source, format: MagickFormat): Task
//
//    /**
//     * Writes the image to the specified stream.
//     *
//     * @param stream The stream to write the image data to.
//     * @param format The format to use.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun writeAsync(stream: Source, format: MagickFormat, cancellationToken: CancellationToken): Task
//
//    /**
//     * Writes the image to the specified file name.
//     *
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun writeAsync(fileName: String): Task
//
//    /**
//     * Writes the image to the specified file name.
//     *
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun writeAsync(fileName: String, cancellationToken: CancellationToken): Task
//
//    /**
//     * Writes the image to the specified file name.
//     *
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param defines The defines to set.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun writeAsync(fileName: String, defines: IWriteDefines): Task
//
//    /**
//     * Writes the image to the specified file name.
//     *
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param defines The defines to set.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun writeAsync(fileName: String, defines: IWriteDefines, cancellationToken: CancellationToken): Task
//
//    /**
//     * Writes the image to the specified file name.
//     *
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param format The format to use.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun writeAsync(fileName: String, format: MagickFormat): Task
//
//    /**
//     * Writes the image to the specified file name.
//     *
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param format The format to use.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
// //     public fun writeAsync(fileName: String, format: MagickFormat, cancellationToken: CancellationToken): Task
}
