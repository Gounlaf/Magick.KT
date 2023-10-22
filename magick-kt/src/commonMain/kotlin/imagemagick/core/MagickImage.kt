package imagemagick.core

interface MagickImage {
//    /**
//     * Gets or sets the time in 1/100ths of a second which must expire before splaying the next image in an
//     * animated sequence.
//     */int AnimationDelay { get; set; }
//
//    /**
//     * Gets or sets the number of iterations to loop an animation (e.g. Netscape loop extension) for.
//     */int AnimationIterations { get; set; }
//
//    /**
//     * Gets or sets the ticks per seconds for the animation delay.
//     */int AnimationTicksPerSecond { get; set; }
//
//    /**
//     * Gets the names of the artifacts.
//     */IEnumerable<string> ArtifactNames { get; }
//
//    /**
//     * Gets the names of the attributes.
//     */IEnumerable<string> AttributeNames { get; }
//
    /**
     * Gets the height of the image before transformations.
     */
    val baseHeight: UInt

    /**
     * Gets the width of the image before transformations.
     */
    val baseWidth: UInt

    /**
     * Gets or sets a value indicating whether black point compensation should be used.
     */
    val blackPointCompensation: Boolean

//    /**
//     * Gets the smallest bounding box enclosing non-border pixels. The current fuzz value is used
//     * when discriminating between pixels.
//     */IMagickGeometry? BoundingBox { get; }
//
//    /**
//     * Gets the number of channels that the image contains.
//     */int ChannelCount { get; }
//
//    /**
//     * Gets the channels of the image.
//     */IEnumerable<PixelChannel> Channels { get; }
//
//    /**
//     * Gets or sets the chromaticity blue primary point.
//     */IPrimaryInfo ChromaBluePrimary { get; set; }
//
//    /**
//     * Gets or sets the chromaticity green primary point.
//     */IPrimaryInfo ChromaGreenPrimary { get; set; }
//
//    /**
//     * Gets or sets the chromaticity red primary point.
//     */IPrimaryInfo ChromaRedPrimary { get; set; }
//
//    /**
//     * Gets or sets the chromaticity white primary point.
//     */IPrimaryInfo ChromaWhitePoint { get; set; }
//
//    /**
//     * Gets or sets the image class (DirectClass or PseudoClass)
//     * NOTE: Setting a DirectClass image to PseudoClass will result in the loss of color information
//     * if the number of colors in the image is greater than the maximum palette size (either 256 (Q8)
//     * or 65536 (Q16).
//     */ClassType ClassType { get; set; }
//
//    /**
//     * Gets or sets the distance where colors are considered equal.
//     */Percentage ColorFuzz { get; set; }
//
//    /**
//     * Gets or sets the colormap size (number of colormap entries).
//     */int ColormapSize { get; set; }
//
//    /**
//     * Gets or sets the color space of the image.
//     */ColorSpace ColorSpace { get; set; }
//
//    /**
//     * Gets or sets the color type of the image.
//     */ColorType ColorType { get; set; }
//
//    /**
//     * Gets or sets the comment text of the image.
//     */string? Comment { get; set; }
//
//    /**
//     * Gets or sets the composition operator to be used when composition is implicitly used (such as for image flattening).
//     */CompositeOperator Compose { get; set; }
//
//    /**
//     * Gets the compression method of the image.
//     */CompressionMethod Compression { get; }
//
//    /**
//     * Gets or sets the vertical and horizontal resolution in pixels of the image.
//     */Density Density { get; set; }
//
//    /**
//     * Gets or sets the depth (bits allocated to red/green/blue components).
//     */int Depth { get; set; }
//
//    /**
//     * Gets the preferred size of the image when encoding.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    IMagickGeometry? EncodingGeometry { get; }
//
//    /**
//     * Gets or sets the endianness (little like Intel or big like SPARC) for image formats which support
//     * endian-specific options.
//     */Endian Endian { get; set; }
//
//    /**
//     * Gets the original file name of the image (only available if read from disk).
//     */string? FileName { get; }
//
//    /**
//     * Gets or sets the filter to use when resizing image.
//     */FilterType FilterType { get; set; }
//
//    /**
//     * Gets or sets the format of the image.
//     */MagickFormat Format { get; set; }
//
//    /**
//     * Gets the information about the format of the image.
//     */IMagickFormatInfo? FormatInfo { get; }
//
//    /**
//     * Gets the gamma level of the image.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    double Gamma { get; }
//
//    /**
//     * Gets or sets the gif disposal method.
//     */GifDisposeMethod GifDisposeMethod { get; set; }
//
//    /**
//     * Gets or sets a value indicating whether the image supports transparency (alpha channel).
//     */bool HasAlpha { get; set; }
//
//    /**
//     * Gets the height of the image.
//     */int Height { get; }
//
//    /**
//     * Gets or sets the type of interlacing to use.
//     */Interlace Interlace { get; set; }
//
//    /**
//     * Gets or sets the pixel color interpolate method to use.
//     */PixelInterpolateMethod Interpolate { get; set; }
//
//    /**
//     * Gets a value indicating whether the instance is disposed.
//     */bool IsDisposed { get; }
//
//    /**
//     * Gets a value indicating whether none of the pixels in the image have an alpha value other
//     * than OpaqueAlpha (QuantumRange).
//     */bool IsOpaque { get; }
//
//    /**
//     * Gets or sets the label of the image.
//     */string? Label { get; set; }
//
//    /**
//     * Gets or sets the photo orientation of the image.
//     */OrientationType Orientation { get; set; }
//
//    /**
//     * Gets or sets the preferred size and location of an image canvas.
//     */IMagickGeometry Page { get; set; }
//
//    /**
//     * Gets the names of the profiles.
//     */IEnumerable<string> ProfileNames { get; }
//
//    /**
//     * Gets or sets the JPEG/MIFF/PNG compression level (default 75).
//     */int Quality { get; set; }
//
//    /**
//     * Gets or sets the type of rendering intent.
//     */RenderingIntent RenderingIntent { get; set; }
//
//    /**
//     * Gets the signature of this image.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    string Signature { get; }
//
//    /**
//     * Gets the number of colors in the image.
//     */int TotalColors { get; }
//
//    /**
//     * Gets or sets the virtual pixel method.
//     */VirtualPixelMethod VirtualPixelMethod { get; set; }
//
//    /**
//     * Gets the width of the image.
//     */int Width { get; }
//
//    /**
//     * Adaptive-blur image with the default blur factor (0x1).
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AdaptiveBlur();
//
//    /**
//     * Adaptive-blur image with specified blur factor.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AdaptiveBlur(double radius);
//
//    /**
//     * Adaptive-blur image with specified blur factor.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AdaptiveBlur(double radius, double sigma);
//
//    /**
//     * Resize using mesh interpolation. It works well for small resizes of less than +/- 50%
//     * of the original image size. For larger resizing on images a full filtered and slower resize
//     * function should be used instead.
//     * <para />
//     * Resize will fit the image into the requested size. It does NOT fill, the requested box size.
//     * Use the [IMagickGeometry] overload for more control over the resulting size.
//     * @param width The new width.
//     * @param height The new height.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AdaptiveResize(int width, int height);
//
//    /**
//     * Resize using mesh interpolation. It works well for small resizes of less than +/- 50%
//     * of the original image size. For larger resizing on images a full filtered and slower resize
//     * function should be used instead.
//     * @param geometry The geometry to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AdaptiveResize(IMagickGeometry geometry);
//
//    /**
//     * Adaptively sharpens the image by sharpening more intensely near image edges and less
//     * intensely far from edges.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AdaptiveSharpen();
//
//    /**
//     * Adaptively sharpens the image by sharpening more intensely near image edges and less
//     * intensely far from edges.
//     * @param channels The channel(s) that should be sharpened.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AdaptiveSharpen(Channels channels);
//
//    /**
//     * Adaptively sharpens the image by sharpening more intensely near image edges and less
//     * intensely far from edges.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AdaptiveSharpen(double radius, double sigma);
//
//    /**
//     * Adaptively sharpens the image by sharpening more intensely near image edges and less
//     * intensely far from edges.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @param channels The channel(s) that should be sharpened.
//    void AdaptiveSharpen(double radius, double sigma, Channels channels);
//
//    /**
//     * Local adaptive threshold image.
//     * http://www.dai.ed.ac.uk/HIPR2/adpthrsh.htm.
//     * @param width The width of the pixel neighborhood.
//     * @param height The height of the pixel neighborhood.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AdaptiveThreshold(int width, int height);
//
//    /**
//     * Local adaptive threshold image.
//     * http://www.dai.ed.ac.uk/HIPR2/adpthrsh.htm.
//     * @param width The width of the pixel neighborhood.
//     * @param height The height of the pixel neighborhood.
//     * @param channels The channel(s) that should be thresholded.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AdaptiveThreshold(int width, int height, Channels channels);
//
//    /**
//     * Local adaptive threshold image.
//     * http://www.dai.ed.ac.uk/HIPR2/adpthrsh.htm.
//     * @param width The width of the pixel neighborhood.
//     * @param height The height of the pixel neighborhood.
//     * @param bias Constant to subtract from pixel neighborhood mean (+/-)(0-QuantumRange).
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AdaptiveThreshold(int width, int height, double bias);
//
//    /**
//     * Local adaptive threshold image.
//     * http://www.dai.ed.ac.uk/HIPR2/adpthrsh.htm.
//     * @param width The width of the pixel neighborhood.
//     * @param height The height of the pixel neighborhood.
//     * @param bias Constant to subtract from pixel neighborhood mean (+/-)(0-QuantumRange).
//     * @param channels The channel(s) that should be thresholded.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AdaptiveThreshold(int width, int height, double bias, Channels channels);
//
//    /**
//     * Local adaptive threshold image.
//     * http://www.dai.ed.ac.uk/HIPR2/adpthrsh.htm.
//     * @param width The width of the pixel neighborhood.
//     * @param height The height of the pixel neighborhood.
//     * @param biasPercentage Constant to subtract from pixel neighborhood mean.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AdaptiveThreshold(int width, int height, Percentage biasPercentage);
//
//    /**
//     * Local adaptive threshold image.
//     * http://www.dai.ed.ac.uk/HIPR2/adpthrsh.htm.
//     * @param width The width of the pixel neighborhood.
//     * @param height The height of the pixel neighborhood.
//     * @param biasPercentage Constant to subtract from pixel neighborhood mean.
//     * @param channels The channel(s) that should be thresholded.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AdaptiveThreshold(int width, int height, Percentage biasPercentage, Channels channels);
//
//    /**
//     * Add noise to image with the specified noise type.
//     * @param noiseType The type of noise that should be added to the image.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AddNoise(NoiseType noiseType);
//
//    /**
//     * Add noise to the specified channel of the image with the specified noise type.
//     * @param noiseType The type of noise that should be added to the image.
//     * @param channels The channel(s) where the noise should be added.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AddNoise(NoiseType noiseType, Channels channels);
//
//    /**
//     * Add noise to image with the specified noise type.
//     * @param noiseType The type of noise that should be added to the image.
//     * @param attenuate Attenuate the random distribution.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AddNoise(NoiseType noiseType, double attenuate);
//
//    /**
//     * Add noise to the specified channel of the image with the specified noise type.
//     * @param noiseType The type of noise that should be added to the image.
//     * @param attenuate Attenuate the random distribution.
//     * @param channels The channel(s) where the noise should be added.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AddNoise(NoiseType noiseType, double attenuate, Channels channels);
//
//    /**
//     * Affine Transform image.
//     * @param affineMatrix The affine matrix to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AffineTransform(IDrawableAffine affineMatrix);
//
//    /**
//     * Applies the specified alpha option.
//     * @param value The option to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Alpha(AlphaOption value);
//
//    /**
//     * Annotate using specified text, and bounding area.
//     * @param text The text to use.
//     * @param boundingArea The bounding area.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Annotate(string text, IMagickGeometry boundingArea);
//
//    /**
//     * Annotate using specified text, bounding area, and placement gravity.
//     * @param text The text to use.
//     * @param boundingArea The bounding area.
//     * @param gravity The placement gravity.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Annotate(string text, IMagickGeometry boundingArea, Gravity gravity);
//
//    /**
//     * Annotate using specified text, bounding area, and placement gravity.
//     * @param text The text to use.
//     * @param boundingArea The bounding area.
//     * @param gravity The placement gravity.
//     * @param angle The rotation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Annotate(string text, IMagickGeometry boundingArea, Gravity gravity, double angle);
//
//    /**
//     * Annotate with text (bounding area is entire image) and placement gravity.
//     * @param text The text to use.
//     * @param gravity The placement gravity.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Annotate(string text, Gravity gravity);
//
//    /**
//     * Extracts the 'mean' from the image and adjust the image to try make set its gamma appropriatally.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AutoGamma();
//
//    /**
//     * Extracts the 'mean' from the image and adjust the image to try make set its gamma appropriatally.
//     * @param channels The channel(s) to set the gamma for.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AutoGamma(Channels channels);
//
//    /**
//     * Adjusts the levels of a particular image channel by scaling the minimum and maximum values
//     * to the full quantum range.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AutoLevel();
//
//    /**
//     * Adjusts the levels of a particular image channel by scaling the minimum and maximum values
//     * to the full quantum range.
//     * @param channels The channel(s) to level.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AutoLevel(Channels channels);
//
//    /**
//     * Adjusts an image so that its orientation is suitable for viewing.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AutoOrient();
//
//    /**
//     * Automatically selects a threshold and replaces each pixel in the image with a black pixel if
//     * the image intentsity is less than the selected threshold otherwise white.
//     * @param method The threshold method.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void AutoThreshold(AutoThresholdMethod method);
//
//    /**
//     * Applies a non-linear, edge-preserving, and noise-reducing smoothing filter.
//     * @param width The width of the neighborhood in pixels.
//     * @param height The height of the neighborhood in pixels.\
//    void BilateralBlur(int width, int height);
//
//    /**
//     * Applies a non-linear, edge-preserving, and noise-reducing smoothing filter.
//     * @param width The width of the neighborhood in pixels.
//     * @param height The height of the neighborhood in pixels.
//     * @param intensitySigma The sigma in the intensity space.
//     * @param spatialSigma The sigma in the coordinate space.
//    void BilateralBlur(int width, int height, double intensitySigma, double spatialSigma);
//
//    /**
//     * Forces all pixels below the threshold into black while leaving all pixels at or above
//     * the threshold unchanged.
//     * @param threshold The threshold to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void BlackThreshold(Percentage threshold);
//
//    /**
//     * Forces all pixels below the threshold into black while leaving all pixels at or above
//     * the threshold unchanged.
//     * @param threshold The threshold to use.
//     * @param channels The channel(s) to make black.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void BlackThreshold(Percentage threshold, Channels channels);
//
//    /**
//     * Simulate a scene at nighttime in the moonlight.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void BlueShift();
//
//    /**
//     * Simulate a scene at nighttime in the moonlight.
//     * @param factor The factor to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void BlueShift(double factor);
//
//    /**
//     * Blur image with the default blur factor (0x1).
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Blur();
//
//    /**
//     * Blur image the specified channel of the image with the default blur factor (0x1).
//     * @param channels The channel(s) that should be blurred.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Blur(Channels channels);
//
//    /**
//     * Blur image with specified blur factor.
//     * @param radius The radius of the Gaussian in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Blur(double radius, double sigma);
//
//    /**
//     * Blur image with specified blur factor and channel.
//     * @param radius The radius of the Gaussian in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @param channels The channel(s) that should be blurred.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Blur(double radius, double sigma, Channels channels);
//
//    /**
//     * Border image (add border to image).
//     * @param size The size of the border.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Border(int size);
//
//    /**
//     * Border image (add border to image).
//     * @param width The width of the border.
//     * @param height The height of the border.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Border(int width, int height);
//
//    /**
//     * Border image (add border to image).
//     * @param percentage The size of the border.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Border(Percentage percentage);
//
//    /**
//     * Changes the brightness and/or contrast of an image. It converts the brightness and
//     * contrast parameters into slope and intercept and calls a polynomical function to apply
//     * to the image.
//     * @param brightness The brightness.
//     * @param contrast The contrast.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void BrightnessContrast(Percentage brightness, Percentage contrast);
//
//    /**
//     * Changes the brightness and/or contrast of an image. It converts the brightness and
//     * contrast parameters into slope and intercept and calls a polynomical function to apply
//     * to the image.
//     * @param brightness The brightness.
//     * @param contrast The contrast.
//     * @param channels The channel(s) that should be changed.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void BrightnessContrast(Percentage brightness, Percentage contrast, Channels channels);
//
//    /**
//     * Uses a multi-stage algorithm to detect a wide range of edges in images.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void CannyEdge();
//
//    /**
//     * Uses a multi-stage algorithm to detect a wide range of edges in images.
//     * @param radius The radius of the gaussian smoothing filter.
//     * @param sigma The sigma of the gaussian smoothing filter.
//     * @param lower Percentage of edge pixels in the lower threshold.
//     * @param upper Percentage of edge pixels in the upper threshold.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void CannyEdge(double radius, double sigma, Percentage lower, Percentage upper);
//
//    /**
//     * Charcoal effect image (looks like charcoal sketch).
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Charcoal();
//
//    /**
//     * Charcoal effect image (looks like charcoal sketch).
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Charcoal(double radius, double sigma);
//
//    /**
//     * Chop image (remove vertical or horizontal subregion of image) using the specified geometry.
//     * @param geometry The geometry to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Chop(IMagickGeometry geometry);
//
//    /**
//     * Chop image (remove horizontal subregion of image).
//     * @param offset The X offset from origin.
//     * @param width The width of the part to chop horizontally.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void ChopHorizontal(int offset, int width);
//
//    /**
//     * Chop image (remove horizontal subregion of image).
//     * @param offset The Y offset from origin.
//     * @param height The height of the part to chop vertically.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void ChopVertical(int offset, int height);
//
//    /**
//     * A variant of adaptive histogram equalization in which the contrast amplification is limited,
//     * so as to reduce this problem of noise amplification.
//     * @param xTiles The percentage of tile divisions to use in horizontal direction.
//     * @param yTiles The percentage of tile divisions to use in vertical direction.
//     * @param numberBins The number of bins for histogram ("dynamic range").
//     * @param clipLimit The contrast limit for localised changes in contrast. A limit less than 1
//     * results in standard non-contrast limited AHE.
//    void Clahe(Percentage xTiles, Percentage yTiles, int numberBins, double clipLimit);
//
//    /**
//     * A variant of adaptive histogram equalization in which the contrast amplification is limited,
//     * so as to reduce this problem of noise amplification.
//     * @param xTiles The number of tile divisions to use in horizontal direction.
//     * @param yTiles The number of tile divisions to use in vertical direction.
//     * @param numberBins The number of bins for histogram ("dynamic range").
//     * @param clipLimit The contrast limit for localised changes in contrast. A limit less than 1
//     * results in standard non-contrast limited AHE.
//    void Clahe(int xTiles, int yTiles, int numberBins, double clipLimit);
//
//    /**
//     * Set each pixel whose value is below zero to zero and any the pixel whose value is above
//     * the quantum range to the quantum range (Quantum.Max) otherwise the pixel value
//     * remains unchanged.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Clamp();
//
//    /**
//     * Set each pixel whose value is below zero to zero and any the pixel whose value is above
//     * the quantum range to the quantum range (Quantum.Max) otherwise the pixel value
//     * remains unchanged.
//     * @param channels The channel(s) to clamp.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Clamp(Channels channels);
//
//    /**
//     * Sets the image clip mask based on any clipping path information if it exists. The clipping
//     * path can be removed with [RemoveWriteMask]. This operating takes effect inside
//     * the clipping path.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Clip();
//
//    /**
//     * Sets the image clip mask based on any clipping path information if it exists. The clipping
//     * path can be removed with [RemoveWriteMask]. This operating takes effect inside
//     * the clipping path.
//     * @param pathName Name of clipping path resource. If name is preceded by #, use
//     * clipping path numbered by name.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Clip(string pathName);
//
//    /**
//     * Sets the image clip mask based on any clipping path information if it exists. The clipping
//     * path can be removed with [RemoveWriteMask]. This operating takes effect outside
//     * the clipping path.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void ClipOutside();
//
//    /**
//     * Sets the image clip mask based on any clipping path information if it exists. The clipping
//     * path can be removed with [RemoveWriteMask]. This operating takes effect outside
//     * the clipping path.
//     * @param pathName Name of clipping path resource. If name is preceded by #, use
//     * clipping path numbered by name.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void ClipOutside(string pathName);
//
//    /**
//     * Apply a color lookup table (CLUT) to the image.
//     * @param image The image to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Clut(IMagickImage image);
//
//    /**
//     * Apply a color lookup table (CLUT) to the image.
//     * @param image The image to use.
//     * @param method Pixel interpolate method.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Clut(IMagickImage image, PixelInterpolateMethod method);
//
//    /**
//     * Apply a color lookup table (CLUT) to the image.
//     * @param image The image to use.
//     * @param method Pixel interpolate method.
//     * @param channels The channel(s) to clut.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Clut(IMagickImage image, PixelInterpolateMethod method, Channels channels);
//
//    /**
//     * Applies the color decision list from the specified ASC CDL file.
//     * @param fileName The file to read the ASC CDL information from.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void ColorDecisionList(string fileName);
//
//    /**
//     * Apply a color matrix to the image channels.
//     * @param matrix The color matrix to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void ColorMatrix(IMagickColorMatrix matrix);
//
//    /**
//     * Compare current image with another image and returns error information.
//     * @param image The other image to compare with this image.
//     * @return The error information.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    IMagickErrorInfo Compare(IMagickImage image);
//
//    /**
//     * Returns the distortion based on the specified metric.
//     * @param image The other image to compare with this image.
//     * @param metric The metric to use.
//     * @return The distortion based on the specified metric.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    double Compare(IMagickImage image, ErrorMetric metric);
//
//    /**
//     * Returns the distortion based on the specified metric.
//     * @param image The other image to compare with this image.
//     * @param metric The metric to use.
//     * @param channels The channel(s) to compare.
//     * @return The distortion based on the specified metric.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    double Compare(IMagickImage image, ErrorMetric metric, Channels channels);
//
//    /**
//     * Returns the distortion based on the specified metric.
//     * @param image The other image to compare with this image.
//     * @param metric The metric to use.
//     * @param difference The image that will contain the difference.
//     * @return The distortion based on the specified metric.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    double Compare(IMagickImage image, ErrorMetric metric, IMagickImage difference);
//
//    /**
//     * Returns the distortion based on the specified metric.
//     * @param image The other image to compare with this image.
//     * @param metric The metric to use.
//     * @param difference The image that will contain the difference.
//     * @param channels The channel(s) to compare.
//     * @return The distortion based on the specified metric.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    double Compare(IMagickImage image, ErrorMetric metric, IMagickImage difference, Channels channels);
//
//    /**
//     * Compose an image onto another at specified offset using the 'In' operator.
//     * @param image The image to composite with this image.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image);
//
//    /**
//     * Compose an image onto another at specified offset using the 'In' operator.
//     * @param image The image to composite with this image.
//     * @param channels The channel(s) to composite.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, Channels channels);
//
//    /**
//     * Compose an image onto another using the specified algorithm.
//     * @param image The image to composite with this image.
//     * @param compose The algorithm to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, CompositeOperator compose);
//
//    /**
//     * Compose an image onto another using the specified algorithm.
//     * @param image The image to composite with this image.
//     * @param compose The algorithm to use.
//     * @param channels The channel(s) to composite.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, CompositeOperator compose, Channels channels);
//
//    /**
//     * Compose an image onto another at specified offset using the specified algorithm.
//     * @param image The image to composite with this image.
//     * @param compose The algorithm to use.
//     * @param args The arguments for the algorithm (compose:args).
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, CompositeOperator compose, string? args);
//
//    /**
//     * Compose an image onto another at specified offset using the specified algorithm.
//     * @param image The image to composite with this image.
//     * @param compose The algorithm to use.
//     * @param args The arguments for the algorithm (compose:args).
//     * @param channels The channel(s) to composite.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, CompositeOperator compose, string? args, Channels channels);
//
//    /**
//     * Compose an image onto another at specified offset using the 'In' operator.
//     * @param image The image to composite with this image.
//     * @param x The X offset from origin.
//     * @param y The Y offset from origin.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, int x, int y);
//
//    /**
//     * Compose an image onto another at specified offset using the 'In' operator.
//     * @param image The image to composite with this image.
//     * @param x The X offset from origin.
//     * @param y The Y offset from origin.
//     * @param channels The channel(s) to composite.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, int x, int y, Channels channels);
//
//    /**
//     * Compose an image onto another at specified offset using the specified algorithm.
//     * @param image The image to composite with this image.
//     * @param x The X offset from origin.
//     * @param y The Y offset from origin.
//     * @param compose The algorithm to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, int x, int y, CompositeOperator compose);
//
//    /**
//     * Compose an image onto another at specified offset using the specified algorithm.
//     * @param image The image to composite with this image.
//     * @param x The X offset from origin.
//     * @param y The Y offset from origin.
//     * @param compose The algorithm to use.
//     * @param channels The channel(s) to composite.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, int x, int y, CompositeOperator compose, Channels channels);
//
//    /**
//     * Compose an image onto another at specified offset using the specified algorithm.
//     * @param image The image to composite with this image.
//     * @param x The X offset from origin.
//     * @param y The Y offset from origin.
//     * @param compose The algorithm to use.
//     * @param args The arguments for the algorithm (compose:args).
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, int x, int y, CompositeOperator compose, string? args);
//
//    /**
//     * Compose an image onto another at specified offset using the specified algorithm.
//     * @param image The image to composite with this image.
//     * @param x The X offset from origin.
//     * @param y The Y offset from origin.
//     * @param compose The algorithm to use.
//     * @param args The arguments for the algorithm (compose:args).
//     * @param channels The channel(s) to composite.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, int x, int y, CompositeOperator compose, string? args, Channels channels);
//
//    /**
//     * Compose an image onto another at specified offset using the 'In' operator.
//     * @param image The image to composite with this image.
//     * @param gravity The placement gravity.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, Gravity gravity);
//
//    /**
//     * Compose an image onto another at specified offset using the 'In' operator.
//     * @param image The image to composite with this image.
//     * @param gravity The placement gravity.
//     * @param channels The channel(s) to composite.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, Gravity gravity, Channels channels);
//
//    /**
//     * Compose an image onto another at specified offset using the specified algorithm.
//     * @param image The image to composite with this image.
//     * @param gravity The placement gravity.
//     * @param compose The algorithm to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, Gravity gravity, CompositeOperator compose);
//
//    /**
//     * Compose an image onto another at specified offset using the specified algorithm.
//     * @param image The image to composite with this image.
//     * @param gravity The placement gravity.
//     * @param compose The algorithm to use.
//     * @param channels The channel(s) to composite.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, Gravity gravity, CompositeOperator compose, Channels channels);
//
//    /**
//     * Compose an image onto another at specified offset using the specified algorithm.
//     * @param image The image to composite with this image.
//     * @param gravity The placement gravity.
//     * @param compose The algorithm to use.
//     * @param args The arguments for the algorithm (compose:args).
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, Gravity gravity, CompositeOperator compose, string? args);
//
//    /**
//     * Compose an image onto another at specified offset using the specified algorithm.
//     * @param image The image to composite with this image.
//     * @param gravity The placement gravity.
//     * @param compose The algorithm to use.
//     * @param args The arguments for the algorithm (compose:args).
//     * @param channels The channel(s) to composite.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, Gravity gravity, CompositeOperator compose, string? args, Channels channels);
//
//    /**
//     * Compose an image onto another at specified offset using the 'In' operator.
//     * @param image The image to composite with this image.
//     * @param gravity The placement gravity.
//     * @param x The X offset from origin.
//     * @param y The Y offset from origin.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, Gravity gravity, int x, int y);
//
//    /**
//     * Compose an image onto another at specified offset using the 'In' operator.
//     * @param image The image to composite with this image.
//     * @param gravity The placement gravity.
//     * @param x The X offset from origin.
//     * @param y The Y offset from origin.
//     * @param channels The channel(s) to composite.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, Gravity gravity, int x, int y, Channels channels);
//
//    /**
//     * Compose an image onto another at specified offset using the 'In' operator.
//     * @param image The image to composite with this image.
//     * @param gravity The placement gravity.
//     * @param x The X offset from origin.
//     * @param y The Y offset from origin.
//     * @param compose The algorithm to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, Gravity gravity, int x, int y, CompositeOperator compose);
//
//    /**
//     * Compose an image onto another at specified offset using the 'In' operator.
//     * @param image The image to composite with this image.
//     * @param gravity The placement gravity.
//     * @param x The X offset from origin.
//     * @param y The Y offset from origin.
//     * @param compose The algorithm to use.
//     * @param channels The channel(s) to composite.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, Gravity gravity, int x, int y, CompositeOperator compose, Channels channels);
//
//    /**
//     * Compose an image onto another at specified offset using the specified algorithm.
//     * @param image The image to composite with this image.
//     * @param gravity The placement gravity.
//     * @param x The X offset from origin.
//     * @param y The Y offset from origin.
//     * @param compose The algorithm to use.
//     * @param args The arguments for the algorithm (compose:args).
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, Gravity gravity, int x, int y, CompositeOperator compose, string? args);
//
//    /**
//     * Compose an image onto another at specified offset using the specified algorithm.
//     * @param image The image to composite with this image.
//     * @param gravity The placement gravity.
//     * @param x The X offset from origin.
//     * @param y The Y offset from origin.
//     * @param compose The algorithm to use.
//     * @param args The arguments for the algorithm (compose:args).
//     * @param channels The channel(s) to composite.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Composite(IMagickImage image, Gravity gravity, int x, int y, CompositeOperator compose, string? args, Channels channels);
//
//    /**
//     * Contrast image (enhance intensity differences in image).
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Contrast();
//
//    /**
//     * A simple image enhancement technique that attempts to improve the contrast in an image by
//     * 'stretching' the range of intensity values it contains to span a desired range of values.
//     * It differs from the more sophisticated histogram equalization in that it can only apply a
//     * linear scaling function to the image pixel values. As a result the 'enhancement' is less harsh.
//     * @param blackPoint The black point.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void ContrastStretch(Percentage blackPoint);
//
//    /**
//     * A simple image enhancement technique that attempts to improve the contrast in an image by
//     * 'stretching' the range of intensity values it contains to span a desired range of values.
//     * It differs from the more sophisticated histogram equalization in that it can only apply a
//     * linear scaling function to the image pixel values. As a result the 'enhancement' is less harsh.
//     * @param blackPoint The black point.
//     * @param whitePoint The white point.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void ContrastStretch(Percentage blackPoint, Percentage whitePoint);
//
//    /**
//     * A simple image enhancement technique that attempts to improve the contrast in an image by
//     * 'stretching' the range of intensity values it contains to span a desired range of values.
//     * It differs from the more sophisticated histogram equalization in that it can only apply a
//     * linear scaling function to the image pixel values. As a result the 'enhancement' is less harsh.
//     * @param blackPoint The black point.
//     * @param whitePoint The white point.
//     * @param channels The channel(s) to constrast stretch.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void ContrastStretch(Percentage blackPoint, Percentage whitePoint, Channels channels);
//
//    /**
//     * Returns the convex hull points of an image canvas.
//     */ * @return The convex hull points of an image canvas.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    IEnumerable<PointD> ConvexHull();
//
//    /**
//     * Convolve image. Applies a user-specified convolution to the image.
//     * @param matrix The convolution matrix.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Convolve(IConvolveMatrix matrix);
//
//    /**
//     * Copies pixels from the source image to the destination image.
//     * @param source The source image to copy the pixels from.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void CopyPixels(IMagickImage source);
//
//    /**
//     * Copies pixels from the source image to the destination image.
//     * @param source The source image to copy the pixels from.
//     * @param channels The channels to copy.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void CopyPixels(IMagickImage source, Channels channels);
//
//    /**
//     * Copies pixels from the source image to the destination image.
//     * @param source The source image to copy the pixels from.
//     * @param geometry The geometry to copy.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void CopyPixels(IMagickImage source, IMagickGeometry geometry);
//
//    /**
//     * Copies pixels from the source image to the destination image.
//     * @param source The source image to copy the pixels from.
//     * @param geometry The geometry to copy.
//     * @param channels The channels to copy.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void CopyPixels(IMagickImage source, IMagickGeometry geometry, Channels channels);
//
//    /**
//     * Copies pixels from the source image as defined by the geometry the destination image at
//     * the specified offset.
//     * @param source The source image to copy the pixels from.
//     * @param geometry The geometry to copy.
//     * @param x The X offset to start the copy from.
//     * @param y The Y offset to start the copy from.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void CopyPixels(IMagickImage source, IMagickGeometry geometry, int x, int y);
//
//    /**
//     * Copies pixels from the source image as defined by the geometry the destination image at
//     * the specified offset.
//     * @param source The source image to copy the pixels from.
//     * @param geometry The geometry to copy.
//     * @param x The X offset to copy the pixels to.
//     * @param y The Y offset to copy the pixels to.
//     * @param channels The channels to copy.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void CopyPixels(IMagickImage source, IMagickGeometry geometry, int x, int y, Channels channels);
//
//    /**
//     * Crop image (subregion of original image). RePage should be called unless the Page information
//     * is needed.
//     * @param width The width of the subregion.
//     * @param height The height of the subregion.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Crop(int width, int height);
//
//    /**
//     * Crop image (subregion of original image). RePage should be called unless the Page information
//     * is needed.
//     * @param width The width of the subregion.
//     * @param height The height of the subregion.
//     * @param gravity The position where the cropping should start from.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Crop(int width, int height, Gravity gravity);
//
//    /**
//     * Crop image (subregion of original image). RePage should be called unless the Page information
//     * is needed.
//     * @param geometry The subregion to crop.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Crop(IMagickGeometry geometry);
//
//    /**
//     * Crop image (subregion of original image). RePage should be called unless the Page information
//     * is needed.
//     * @param geometry The subregion to crop.
//     * @param gravity The position where the cropping should start from.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Crop(IMagickGeometry geometry, Gravity gravity);
//
//    /**
//     * Displaces an image's colormap by a given number of positions.
//     * @param amount Displace the colormap this amount.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void CycleColormap(int amount);
//
//    /**
//     * Converts cipher pixels to plain pixels.
//     * @param passphrase The password that was used to encrypt the image.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Decipher(string passphrase);
//
//    /**
//     * Removes skew from the image. Skew is an artifact that occurs in scanned images because of
//     * the camera being misaligned, imperfections in the scanning or surface, or simply because
//     * the paper was not placed completely flat when scanned. The value of threshold ranges
//     * from 0 to QuantumRange.
//     * @param threshold The threshold.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//     * @return The angle that was used.
//    double Deskew(Percentage threshold);
//
//    /**
//     * Removes skew from the image. Skew is an artifact that occurs in scanned images because of
//     * the camera being misaligned, imperfections in the scanning or surface, or simply because
//     * the paper was not placed completely flat when scanned. The value of threshold ranges
//     * from 0 to QuantumRange.
//     * @param settings The deskew settings.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//     * @return The angle that was used.
//    double Deskew(IDeskewSettings settings);
//
//    /**
//     * Despeckle image (reduce speckle noise).
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Despeckle();
//
//    /**
//     * Determines the bit depth (bits allocated to red/green/blue components). Use the Depth
//     * property to get the current value.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//     * @return The bit depth (bits allocated to red/green/blue components).
//    int DetermineBitDepth();
//
//    /**
//     * Determines the bit depth (bits allocated to red/green/blue components) of the specified channel.
//     * @param channels The channel to get the depth for.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//     * @return The bit depth (bits allocated to red/green/blue components) of the specified channel.
//    int DetermineBitDepth(Channels channels);
//
//    /**
//     * Determines the color type of the image. This method can be used to automatically make the
//     * type GrayScale.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//     * @return The color type of the image.
//    ColorType DetermineColorType();
//
//    /**
//     * Distorts an image using various distortion methods, by mapping color lookups of the source
//     * image to a new destination image of the same size as the source image.
//     * @param method The distortion method to use.
//     * @param arguments An array containing the arguments for the distortion.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Distort(DistortMethod method, params double[] arguments);
//
//    /**
//     * Distorts an image using various distortion methods, by mapping color lookups of the source
//     * image to a new destination image usually of the same size as the source image, unless
//     * 'bestfit' is set to true.
//     * @param method The distortion method to use.
//     * @param settings The settings for the distort operation.
//     * @param arguments An array containing the arguments for the distortion.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Distort(DistortMethod method, IDistortSettings settings, params double[] arguments);
//
//    /**
//     * Draw on image using one or more drawables.
//     * @param drawables The drawable(s) to draw on the image.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Draw(params IDrawable[] drawables);
//
//    /**
//     * Draw on image using a collection of drawables.
//     * @param drawables The drawables to draw on the image.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Draw(IEnumerable<IDrawable> drawables);
//
//    /**
//     * Edge image (highlight edges in image).
//     * @param radius The radius of the pixel neighborhood.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Edge(double radius);
//
//    /**
//     * Emboss image (highlight edges with 3D effect) with default value (0x1).
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Emboss();
//
//    /**
//     * Emboss image (highlight edges with 3D effect).
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Emboss(double radius, double sigma);
//
//    /**
//     * Converts pixels to cipher-pixels.
//     * @param passphrase The password that to encrypt the image with.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Encipher(string passphrase);
//
//    /**
//     * Applies a digital filter that improves the quality of a noisy image.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Enhance();
//
//    /**
//     * Applies a histogram equalization to the image.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Equalize();
//
//    /**
//     * Applies a histogram equalization to the image.
//     * @param channels The channel(s) to apply the operator on.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Equalize(Channels channels);
//
//    /**
//     * Apply an arithmetic or bitwise operator to the image pixel quantums.
//     * @param channels The channel(s) to apply the operator on.
//     * @param evaluateFunction The function.
//     * @param arguments The arguments for the function.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Evaluate(Channels channels, EvaluateFunction evaluateFunction, params double[] arguments);
//
//    /**
//     * Apply an arithmetic or bitwise operator to the image pixel quantums.
//     * @param channels The channel(s) to apply the operator on.
//     * @param evaluateOperator The operator.
//     * @param value The value.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Evaluate(Channels channels, EvaluateOperator evaluateOperator, double value);
//
//    /**
//     * Apply an arithmetic or bitwise operator to the image pixel quantums.
//     * @param channels The channel(s) to apply the operator on.
//     * @param evaluateOperator The operator.
//     * @param percentage The value.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Evaluate(Channels channels, EvaluateOperator evaluateOperator, Percentage percentage);
//
//    /**
//     * Apply an arithmetic or bitwise operator to the image pixel quantums.
//     * @param channels The channel(s) to apply the operator on.
//     * @param geometry The geometry to use.
//     * @param evaluateOperator The operator.
//     * @param value The value.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Evaluate(Channels channels, IMagickGeometry geometry, EvaluateOperator evaluateOperator, double value);
//
//    /**
//     * Apply an arithmetic or bitwise operator to the image pixel quantums.
//     * @param channels The channel(s) to apply the operator on.
//     * @param geometry The geometry to use.
//     * @param evaluateOperator The operator.
//     * @param percentage The value.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Evaluate(Channels channels, IMagickGeometry geometry, EvaluateOperator evaluateOperator, Percentage percentage);
//
//    /**
//     * Extend the image as defined by the width and height.
//     * @param width The width to extend the image to.
//     * @param height The height to extend the image to.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Extent(int width, int height);
//
//    /**
//     * Extend the image as defined by the width and height.
//     * @param x The X offset from origin.
//     * @param y The Y offset from origin.
//     * @param width The width to extend the image to.
//     * @param height The height to extend the image to.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Extent(int x, int y, int width, int height);
//
//    /**
//     * Extend the image as defined by the width and height.
//     * @param width The width to extend the image to.
//     * @param height The height to extend the image to.
//     * @param gravity The placement gravity.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Extent(int width, int height, Gravity gravity);
//
//    /**
//     * Extend the image as defined by the rectangle.
//     * @param geometry The geometry to extend the image to.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Extent(IMagickGeometry geometry);
//
//    /**
//     * Extend the image as defined by the geometry.
//     * @param geometry The geometry to extend the image to.
//     * @param gravity The placement gravity.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Extent(IMagickGeometry geometry, Gravity gravity);
//
//    /**
//     * Flip image (reflect each scanline in the vertical direction).
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Flip();
//
//    /**
//     * Flop image (reflect each scanline in the horizontal direction).
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Flop();
//
//    /**
//     * Obtain font metrics for text string given current font, pointsize, and density settings.
//     * @param text The text to get the font metrics for.
//     * @return The font metrics for text.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    ITypeMetric? FontTypeMetrics(string text);
//
//    /**
//     * Obtain font metrics for text string given current font, pointsize, and density settings.
//     * @param text The text to get the font metrics for.
//     * @param ignoreNewlines Specifies if newlines should be ignored.
//     * @return The font metrics for text.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    ITypeMetric? FontTypeMetrics(string text, bool ignoreNewlines);
//
//    /**
//     * Formats the specified expression, more info here: http://www.imagemagick.org/script/escape.php.
//     * @param expression The expression, more info here: http://www.imagemagick.org/script/escape.php.
//     * @return The result of the expression.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    string? FormatExpression(string expression);
//
//    /**
//     * Frame image with the default geometry (25x25+6+6).
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Frame();
//
//    /**
//     * Frame image with the specified geometry.
//     * @param geometry The geometry of the frame.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Frame(IMagickGeometry geometry);
//
//    /**
//     * Frame image with the specified with and height.
//     * @param width The width of the frame.
//     * @param height The height of the frame.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Frame(int width, int height);
//
//    /**
//     * Frame image with the specified with, height, innerBevel and outerBevel.
//     * @param width The width of the frame.
//     * @param height The height of the frame.
//     * @param innerBevel The inner bevel of the frame.
//     * @param outerBevel The outer bevel of the frame.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Frame(int width, int height, int innerBevel, int outerBevel);
//
//    /**
//     * Applies a mathematical expression to the image.
//     * @param expression The expression to apply.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Fx(string expression);
//
//    /**
//     * Applies a mathematical expression to the image.
//     * @param expression The expression to apply.
//     * @param channels The channel(s) to apply the expression to.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Fx(string expression, Channels channels);
//
//    /**
//     * Gamma correct image.
//     * @param gamma The image gamma.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void GammaCorrect(double gamma);
//
//    /**
//     * Gamma correct image.
//     * @param gamma The image gamma for the channel.
//     * @param channels The channel(s) to gamma correct.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void GammaCorrect(double gamma, Channels channels);
//
//    /**
//     * Gaussian blur image.
//     * @param radius The number of neighbor pixels to be included in the convolution.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void GaussianBlur(double radius);
//
//    /**
//     * Gaussian blur image.
//     * @param radius The number of neighbor pixels to be included in the convolution.
//     * @param channels The channel(s) to blur.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void GaussianBlur(double radius, Channels channels);
//
//    /**
//     * Gaussian blur image.
//     * @param radius The number of neighbor pixels to be included in the convolution.
//     * @param sigma The standard deviation of the gaussian bell curve.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void GaussianBlur(double radius, double sigma);
//
//    /**
//     * Gaussian blur image.
//     * @param radius The number of neighbor pixels to be included in the convolution.
//     * @param sigma The standard deviation of the gaussian bell curve.
//     * @param channels The channel(s) to blur.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void GaussianBlur(double radius, double sigma, Channels channels);
//
//    /**
//     * Retrieve the 8bim profile from the image.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//     * @return The 8bim profile from the image.
//    IEightBimProfile? Get8BimProfile();
//
//    /**
//     * Returns the value of the artifact with the specified name.
//     * @param name The name of the artifact.
//     * @return The value of the artifact with the specified name.
//    string? GetArtifact(string name);
//
//    /**
//     * Returns the value of a named image attribute.
//     * @param name The name of the attribute.
//     * @return The value of a named image attribute.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    string? GetAttribute(string name);
//
//    /**
//     * Returns the default clipping path. Null will be returned if the image has no clipping path.
//     */ * @return The default clipping path. Null will be returned if the image has no clipping path.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    string? GetClippingPath();
//
//    /**
//     * Returns the clipping path with the specified name. Null will be returned if the image has no clipping path.
//     * @param pathName Name of clipping path resource. If name is preceded by #, use clipping path numbered by name.
//     * @return The clipping path with the specified name. Null will be returned if the image has no clipping path.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    string? GetClippingPath(string pathName);
//
//    /**
//     * Retrieve the color profile from the image.
//     */ * @return The color profile from the image.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    IColorProfile? GetColorProfile();
//
//    /**
//     * Retrieve the exif profile from the image.
//     */ * @return The exif profile from the image.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    IExifProfile? GetExifProfile();
//
//    /**
//     * Retrieve the iptc profile from the image.
//     */ * @return The iptc profile from the image.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    IIptcProfile? GetIptcProfile();
//
//    /**
//     * Retrieve a named profile from the image.
//     * @param name The name of the profile (e.g. "ICM", "IPTC", or a generic profile name).
//     * @return A named profile from the image.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    IImageProfile? GetProfile(string name);
//
//    /**
//     * Retrieve the xmp profile from the image.
//     */ * @return The xmp profile from the image.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    IXmpProfile? GetXmpProfile();
//
//    /**
//     * Converts the colors in the image to gray.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Grayscale();
//
//    /**
//     * Converts the colors in the image to gray.
//     * @param method The pixel intensity method to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Grayscale(PixelIntensityMethod method);
//
//    /**
//     * Apply a color lookup table (Hald CLUT) to the image.
//     * @param image The image to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void HaldClut(IMagickImage image);
//
//    /**
//     * Gets a value indicating whether a profile with the specified name already exists on the image.
//     * @param name The name of the profile.
//     * @return A value indicating whether a profile with the specified name already exists on the image.
//    bool HasProfile(string name);
//
//    /**
//     * Identifies lines in the image.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void HoughLine();
//
//    /**
//     * Identifies lines in the image.
//     * @param width The width of the neighborhood.
//     * @param height The height of the neighborhood.
//     * @param threshold The line count threshold.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void HoughLine(int width, int height, int threshold);
//
//    /**
//     * Implode image (special effect).
//     * @param amount The extent of the implosion.
//     * @param method Pixel interpolate method.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Implode(double amount, PixelInterpolateMethod method);
//
//    /**
//     * Import pixels from the specified byte array.
//     * @param data The byte array to read the image data from.
//     * @param settings The import settings to use when importing the pixels.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void ImportPixels(byte[] data, IPixelImportSettings settings);
//
//    /**
//     * Import pixels from the specified byte array.
//     * @param data The byte array to read the image data from.
//     * @param offset The offset at which to begin reading data.
//     * @param settings The import settings to use when importing the pixels.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void ImportPixels(byte[] data, int offset, IPixelImportSettings settings);
//
//    /**
//     * Resize image to specified size using the specified interpolation method.
//     * @param width The new width.
//     * @param height The new height.
//     * @param method Pixel interpolate method.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void InterpolativeResize(int width, int height, PixelInterpolateMethod method);
//
//    /**
//     * Resize image to specified size using the specified interpolation method.
//     * @param geometry The geometry to use.
//     * @param method Pixel interpolate method.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void InterpolativeResize(IMagickGeometry geometry, PixelInterpolateMethod method);
//
//    /**
//     * Resize image to specified size using the specified interpolation method.
//     * @param percentage The percentage.
//     * @param method Pixel interpolate method.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void InterpolativeResize(Percentage percentage, PixelInterpolateMethod method);
//
//    /**
//     * Resize image to specified size using the specified interpolation method.
//     * @param percentageWidth The percentage of the width.
//     * @param percentageHeight The percentage of the height.
//     * @param method Pixel interpolate method.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void InterpolativeResize(Percentage percentageWidth, Percentage percentageHeight, PixelInterpolateMethod method);
//
//    /**
//     * Inverse contrast image (diminish intensity differences in image).
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void InverseContrast();
//
//    /**
//     * Applies the reversed level operation to just the specific channels specified. It compresses
//     * the full range of color values, so that they lie between the given black and white points.
//     * Gamma is applied before the values are mapped. Uses a midpoint of 1.0.
//     * @param blackPointPercentage The darkest color in the image. Colors darker are set to zero.
//     * @param whitePointPercentage The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void InverseLevel(Percentage blackPointPercentage, Percentage whitePointPercentage);
//
//    /**
//     * Applies the reversed level operation to just the specific channels specified. It compresses
//     * the full range of color values, so that they lie between the given black and white points.
//     * Gamma is applied before the values are mapped. Uses a midpoint of 1.0.
//     * @param blackPointPercentage The darkest color in the image. Colors darker are set to zero.
//     * @param whitePointPercentage The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @param channels The channel(s) to level.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void InverseLevel(Percentage blackPointPercentage, Percentage whitePointPercentage, Channels channels);
//
//    /**
//     * Applies the reversed level operation to just the specific channels specified. It compresses
//     * the full range of color values, so that they lie between the given black and white points.
//     * Gamma is applied before the values are mapped.
//     * @param blackPointPercentage The darkest color in the image. Colors darker are set to zero.
//     * @param whitePointPercentage The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @param midpoint The gamma correction to apply to the image. (Useful range of 0 to 10).
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void InverseLevel(Percentage blackPointPercentage, Percentage whitePointPercentage, double midpoint);
//
//    /**
//     * Applies the reversed level operation to just the specific channels specified. It compresses
//     * the full range of color values, so that they lie between the given black and white points.
//     * Gamma is applied before the values are mapped.
//     * @param blackPointPercentage The darkest color in the image. Colors darker are set to zero.
//     * @param whitePointPercentage The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @param midpoint The gamma correction to apply to the image. (Useful range of 0 to 10).
//     * @param channels The channel(s) to level.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void InverseLevel(Percentage blackPointPercentage, Percentage whitePointPercentage, double midpoint, Channels channels);
//
//    /**
//     * Adjust the image contrast with an inverse non-linear sigmoidal contrast algorithm.
//     * @param contrast The contrast.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void InverseSigmoidalContrast(double contrast);
//
//    /**
//     * Adjust the image contrast with an inverse non-linear sigmoidal contrast algorithm.
//     * @param contrast The contrast to use.
//     * @param midpoint The midpoint to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void InverseSigmoidalContrast(double contrast, double midpoint);
//
//    /**
//     * Adjust the image contrast with an inverse non-linear sigmoidal contrast algorithm.
//     * @param contrast The contrast to use.
//     * @param midpoint The midpoint to use.
//     * @param channels The channel(s) that should be adjusted.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void InverseSigmoidalContrast(double contrast, double midpoint, Channels channels);
//
//    /**
//     * Adjust the image contrast with an inverse non-linear sigmoidal contrast algorithm.
//     * @param contrast The contrast to use.
//     * @param midpointPercentage The midpoint to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void InverseSigmoidalContrast(double contrast, Percentage midpointPercentage);
//
//    /**
//     * Applies k-means color reduction to an image. This is a colorspace clustering or segmentation technique.
//     * @param settings The kmeans settings.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Kmeans(IKmeansSettings settings);
//
//    /**
//     * An edge preserving noise reduction filter.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Kuwahara();
//
//    /**
//     * An edge preserving noise reduction filter.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Kuwahara(double radius, double sigma);
//
//    /**
//     * Adjust the levels of the image by scaling the colors falling between specified white and
//     * black points to the full available quantum range. Uses a midpoint of 1.0.
//     * @param blackPointPercentage The darkest color in the image. Colors darker are set to zero.
//     * @param whitePointPercentage The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Level(Percentage blackPointPercentage, Percentage whitePointPercentage);
//
//    /**
//     * Adjust the levels of the image by scaling the colors falling between specified white and
//     * black points to the full available quantum range. Uses a midpoint of 1.0.
//     * @param blackPointPercentage The darkest color in the image. Colors darker are set to zero.
//     * @param whitePointPercentage The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @param channels The channel(s) to level.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Level(Percentage blackPointPercentage, Percentage whitePointPercentage, Channels channels);
//
//    /**
//     * Adjust the levels of the image by scaling the colors falling between specified white and
//     * black points to the full available quantum range.
//     * @param blackPointPercentage The darkest color in the image. Colors darker are set to zero.
//     * @param whitePointPercentage The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @param gamma The gamma correction to apply to the image. (Useful range of 0 to 10).
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Level(Percentage blackPointPercentage, Percentage whitePointPercentage, double gamma);
//
//    /**
//     * Adjust the levels of the image by scaling the colors falling between specified white and
//     * black points to the full available quantum range.
//     * @param blackPointPercentage The darkest color in the image. Colors darker are set to zero.
//     * @param whitePointPercentage The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @param gamma The gamma correction to apply to the image. (Useful range of 0 to 10).
//     * @param channels The channel(s) to level.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Level(Percentage blackPointPercentage, Percentage whitePointPercentage, double gamma, Channels channels);
//
//    /**
//     * Discards any pixels below the black point and above the white point and levels the remaining pixels.
//     * @param blackPoint The black point.
//     * @param whitePoint The white point.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void LinearStretch(Percentage blackPoint, Percentage whitePoint);
//
//    /**
//     * Rescales image with seam carving.
//     * @param width The new width.
//     * @param height The new height.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void LiquidRescale(int width, int height);
//
//    /**
//     * Rescales image with seam carving.
//     * @param width The new width.
//     * @param height The new height.
//     * @param deltaX Maximum seam transversal step (0 means straight seams).
//     * @param rigidity Introduce a bias for non-straight seams (typically 0).
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void LiquidRescale(int width, int height, double deltaX, double rigidity);
//
//    /**
//     * Rescales image with seam carving.
//     * @param geometry The geometry to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void LiquidRescale(IMagickGeometry geometry);
//
//    /**
//     * Rescales image with seam carving.
//     * @param percentage The percentage.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void LiquidRescale(Percentage percentage);
//
//    /**
//     * Rescales image with seam carving.
//     * @param percentageWidth The percentage of the width.
//     * @param percentageHeight The percentage of the height.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void LiquidRescale(Percentage percentageWidth, Percentage percentageHeight);
//
//    /**
//     * Rescales image with seam carving.
//     * @param percentageWidth The percentage of the width.
//     * @param percentageHeight The percentage of the height.
//     * @param deltaX Maximum seam transversal step (0 means straight seams).
//     * @param rigidity Introduce a bias for non-straight seams (typically 0).
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void LiquidRescale(Percentage percentageWidth, Percentage percentageHeight, double deltaX, double rigidity);
//
//    /**
//     * Local contrast enhancement.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param strength The strength of the blur mask.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void LocalContrast(double radius, Percentage strength);
//
//    /**
//     * Local contrast enhancement.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param strength The strength of the blur mask.
//     * @param channels The channel(s) that should be changed.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void LocalContrast(double radius, Percentage strength, Channels channels);
//
//    /**
//     * Lower image (darken the edges of an image to give a 3-D lowered effect).
//     * @param size The size of the edges.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Lower(int size);
//
//    /**
//     * Magnify image by integral size.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Magnify();
//
//    /**
//     * Remap image colors with closest color from reference image.
//     * @param image The image to use.
//     * @return The error informaton.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    IMagickErrorInfo Map(IMagickImage image);
//
//    /**
//     * Remap image colors with closest color from reference image.
//     * @param image The image to use.
//     * @param settings Quantize settings.
//     * @return The error informaton.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    IMagickErrorInfo Map(IMagickImage image, IQuantizeSettings settings);
//
//    /**
//     * Delineate arbitrarily shaped clusters in the image.
//     * @param size The width and height of the pixels neighborhood.
//    void MeanShift(int size);
//
//    /**
//     * Delineate arbitrarily shaped clusters in the image.
//     * @param size The width and height of the pixels neighborhood.
//     * @param colorDistance The color distance.
//    void MeanShift(int size, Percentage colorDistance);
//
//    /**
//     * Delineate arbitrarily shaped clusters in the image.
//     * @param width The width of the pixels neighborhood.
//     * @param height The height of the pixels neighborhood.
//    void MeanShift(int width, int height);
//
//    /**
//     * Delineate arbitrarily shaped clusters in the image.
//     * @param width The width of the pixels neighborhood.
//     * @param height The height of the pixels neighborhood.
//     * @param colorDistance The color distance.
//    void MeanShift(int width, int height, Percentage colorDistance);
//
//    /**
//     * Filter image by replacing each pixel component with the median color in a circular neighborhood.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void MedianFilter();
//
//    /**
//     * Filter image by replacing each pixel component with the median color in a circular neighborhood.
//     * @param radius The radius of the pixel neighborhood.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void MedianFilter(int radius);
//
//    /**
//     * Reduce image by integral size.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Minify();
//
//    /**
//     * Returns the points that form the minimum bounding box around the image foreground objects with
//     * the "Rotating Calipers" algorithm. he method also returns these properties: minimum-bounding-box:area,
//     * minimum-bounding-box:width, minimum-bounding-box:height, and minimum-bounding-box:angle.
//     */ * @return The points that form the minimum bounding box around the image foreground objects.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    IEnumerable<PointD> MinimumBoundingBox();
//
//    /**
//     * Modulate percent brightness of an image.
//     * @param brightness The brightness percentage.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Modulate(Percentage brightness);
//
//    /**
//     * Modulate percent saturation and brightness of an image.
//     * @param brightness The brightness percentage.
//     * @param saturation The saturation percentage.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Modulate(Percentage brightness, Percentage saturation);
//
//    /**
//     * Modulate percent hue, saturation, and brightness of an image.
//     * @param brightness The brightness percentage.
//     * @param saturation The saturation percentage.
//     * @param hue The hue percentage.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Modulate(Percentage brightness, Percentage saturation, Percentage hue);
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     * @param method The morphology method.
//     * @param kernel Built-in kernel.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Morphology(MorphologyMethod method, Kernel kernel);
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     * @param method The morphology method.
//     * @param kernel Built-in kernel.
//     * @param channels The channels to apply the kernel to.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Morphology(MorphologyMethod method, Kernel kernel, Channels channels);
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     * @param method The morphology method.
//     * @param kernel Built-in kernel.
//     * @param channels The channels to apply the kernel to.
//     * @param iterations The number of iterations.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Morphology(MorphologyMethod method, Kernel kernel, Channels channels, int iterations);
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     * @param method The morphology method.
//     * @param kernel Built-in kernel.
//     * @param iterations The number of iterations.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Morphology(MorphologyMethod method, Kernel kernel, int iterations);
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     * @param method The morphology method.
//     * @param kernel Built-in kernel.
//     * @param arguments Kernel arguments.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Morphology(MorphologyMethod method, Kernel kernel, string? arguments);
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     * @param method The morphology method.
//     * @param kernel Built-in kernel.
//     * @param arguments Kernel arguments.
//     * @param channels The channels to apply the kernel to.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Morphology(MorphologyMethod method, Kernel kernel, string? arguments, Channels channels);
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     * @param method The morphology method.
//     * @param kernel Built-in kernel.
//     * @param arguments Kernel arguments.
//     * @param channels The channels to apply the kernel to.
//     * @param iterations The number of iterations.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Morphology(MorphologyMethod method, Kernel kernel, string? arguments, Channels channels, int iterations);
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     * @param method The morphology method.
//     * @param kernel Built-in kernel.
//     * @param arguments Kernel arguments.
//     * @param iterations The number of iterations.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Morphology(MorphologyMethod method, Kernel kernel, string? arguments, int iterations);
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     * @param method The morphology method.
//     * @param userKernel User suplied kernel.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Morphology(MorphologyMethod method, string userKernel);
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     * @param method The morphology method.
//     * @param userKernel User suplied kernel.
//     * @param channels The channels to apply the kernel to.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Morphology(MorphologyMethod method, string userKernel, Channels channels);
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     * @param method The morphology method.
//     * @param userKernel User suplied kernel.
//     * @param channels The channels to apply the kernel to.
//     * @param iterations The number of iterations.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Morphology(MorphologyMethod method, string userKernel, Channels channels, int iterations);
//
//    /**
//     * Applies a kernel to the image according to the given mophology method.
//     * @param method The morphology method.
//     * @param userKernel User suplied kernel.
//     * @param iterations The number of iterations.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Morphology(MorphologyMethod method, string userKernel, int iterations);
//
//    /**
//     * Applies a kernel to the image according to the given mophology settings.
//     * @param settings The morphology settings.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Morphology(IMorphologySettings settings);
//
//    /**
//     * Returns the normalized moments of one or more image channels.
//     */ * @return The normalized moments of one or more image channels.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    IMoments Moments();
//
//    /**
//     * Motion blur image with specified blur factor.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @param angle The angle the object appears to be comming from (zero degrees is from the right).
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void MotionBlur(double radius, double sigma, double angle);
//
//    /**
//     * Negate colors in image.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Negate();
//
//    /**
//     * Negate colors in image for the specified channel.
//     * @param channels The channel(s) that should be negated.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Negate(Channels channels);
//
//    /**
//     * Negate the grayscale colors in image.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void NegateGrayscale();
//
//    /**
//     * Negate the grayscale colors in image for the specified channel.
//     * @param channels The channel(s) that should be negated.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void NegateGrayscale(Channels channels);
//
//    /**
//     * Normalize image (increase contrast by normalizing the pixel values to span the full range
//     * of color values).
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Normalize();
//
//    /**
//     * Oilpaint image (image looks like oil painting).
//     */void OilPaint();
//
//    /**
//     * Oilpaint image (image looks like oil painting).
//     * @param radius The radius of the circular neighborhood.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void OilPaint(double radius, double sigma);
//
//    /**
//     * Perform a ordered dither based on a number of pre-defined dithering threshold maps, but over
//     * multiple intensity levels.
//     * @param thresholdMap A string containing the name of the threshold dither map to use,
//     * followed by zero or more numbers representing the number of color levels tho dither between.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void OrderedDither(string thresholdMap);
//
//    /**
//     * Perform a ordered dither based on a number of pre-defined dithering threshold maps, but over
//     * multiple intensity levels.
//     * @param thresholdMap A string containing the name of the threshold dither map to use,
//     * followed by zero or more numbers representing the number of color levels tho dither between.
//     * @param channels The channel(s) to dither.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void OrderedDither(string thresholdMap, Channels channels);
//
//    /**
//     * Set each pixel whose value is less than epsilon to epsilon or -epsilon (whichever is closer)
//     * otherwise the pixel value remains unchanged.
//     * @param epsilon The epsilon threshold.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Perceptible(double epsilon);
//
//    /**
//     * Set each pixel whose value is less than epsilon to epsilon or -epsilon (whichever is closer)
//     * otherwise the pixel value remains unchanged.
//     * @param epsilon The epsilon threshold.
//     * @param channels The channel(s) to perceptible.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Perceptible(double epsilon, Channels channels);
//
//    /**
//     * Returns the perceptual hash of this image.
//     */ * @return The perceptual hash of this image.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    IPerceptualHash? PerceptualHash();
//
//    /**
//     * Reads only metadata and not the pixel data.
//     * @param data The byte array to read the information from.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Ping(byte[] data);
//
//    /**
//     * Reads only metadata and not the pixel data.
//     * @param data The byte array to read the image data from.
//     * @param offset The offset at which to begin reading data.
//     * @param count The maximum number of bytes to read.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Ping(byte[] data, int offset, int count);
//
//    /**
//     * Reads only metadata and not the pixel data.
//     * @param file The file to read the image from.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Ping(FileInfo file);
//
//    /**
//     * Reads only metadata and not the pixel data.
//     * @param stream The stream to read the image data from.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Ping(Stream stream);
//
//    /**
//     * Reads only metadata and not the pixel data.
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Ping(string fileName);
//
//    /**
//     * Simulates a polaroid picture.
//     * @param caption The caption to put on the image.
//     * @param angle The angle of image.
//     * @param method Pixel interpolate method.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Polaroid(string caption, double angle, PixelInterpolateMethod method);
//
//    /**
//     * Reduces the image to a limited number of colors for a "poster" effect.
//     * @param levels Number of color levels allowed in each channel.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Posterize(int levels);
//
//    /**
//     * Reduces the image to a limited number of colors for a "poster" effect.
//     * @param levels Number of color levels allowed in each channel.
//     * @param channels The channel(s) to posterize.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Posterize(int levels, Channels channels);
//
//    /**
//     * Reduces the image to a limited number of colors for a "poster" effect.
//     * @param levels Number of color levels allowed in each channel.
//     * @param method Dither method to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Posterize(int levels, DitherMethod method);
//
//    /**
//     * Reduces the image to a limited number of colors for a "poster" effect.
//     * @param levels Number of color levels allowed in each channel.
//     * @param method Dither method to use.
//     * @param channels The channel(s) to posterize.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Posterize(int levels, DitherMethod method, Channels channels);
//
//    /**
//     * Sets an internal option to preserve the color type.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void PreserveColorType();
//
//    /**
//     * Quantize image (reduce number of colors).
//     * @param settings Quantize settings.
//     * @return The error information.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    IMagickErrorInfo? Quantize(IQuantizeSettings settings);
//
//    /**
//     * Raise image (lighten the edges of an image to give a 3-D raised effect).
//     * @param size The size of the edges.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Raise(int size);
//
//    /**
//     * Changes the value of individual pixels based on the intensity of each pixel compared to a
//     * random threshold. The result is a low-contrast, two color image.
//     * @param percentageLow The low threshold.
//     * @param percentageHigh The high threshold.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void RandomThreshold(Percentage percentageLow, Percentage percentageHigh);
//
//    /**
//     * Changes the value of individual pixels based on the intensity of each pixel compared to a
//     * random threshold. The result is a low-contrast, two color image.
//     * @param percentageLow The low threshold.
//     * @param percentageHigh The high threshold.
//     * @param channels The channel(s) to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void RandomThreshold(Percentage percentageLow, Percentage percentageHigh, Channels channels);
//
//    /**
//     * Applies soft and hard thresholding.
//     * @param percentageLowBlack Defines the minimum black threshold value.
//     * @param percentageLowWhite Defines the minimum white threshold value.
//     * @param percentageHighWhite Defines the maximum white threshold value.
//     * @param percentageHighBlack Defines the maximum black threshold value.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void RangeThreshold(Percentage percentageLowBlack, Percentage percentageLowWhite, Percentage percentageHighWhite, Percentage percentageHighBlack);
//

    /**
     * Read single image frame.
     *
     * @param data The byte array to read the image data from.
     *
     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
     */
    fun read(data: ByteArray)

//    /**
//     * Read single image frame.
//     * @param data The byte array to read the image data from.
//     * @param offset The offset at which to begin reading data.
//     * @param count The maximum number of bytes to read.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Read(byte[] data, int offset, int count);
//
//    /**
//     * Read single image frame.
//     * @param data The byte array to read the image data from.
//     * @param offset The offset at which to begin reading data.
//     * @param count The maximum number of bytes to read.
//     * @param format The format to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Read(byte[] data, int offset, int count, MagickFormat format);
//
//    /**
//     * Read single image frame.
//     * @param data The byte array to read the image data from.
//     * @param format The format to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Read(byte[] data, MagickFormat format);
//
//    /**
//     * Read single image frame.
//     * @param file The file to read the image from.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Read(FileInfo file);
//
//    /**
//     * Read single image frame.
//     * @param file The file to read the image from.
//     * @param width The width.
//     * @param height The height.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Read(FileInfo file, int width, int height);
//
//    /**
//     * Read single image frame.
//     * @param file The file to read the image from.
//     * @param format The format to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Read(FileInfo file, MagickFormat format);
//
//    /**
//     * Read single image frame.
//     * @param stream The stream to read the image data from.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Read(Stream stream);
//
//    /**
//     * Read single image frame.
//     * @param stream The stream to read the image data from.
//     * @param format The format to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Read(Stream stream, MagickFormat format);
//
//    /**
//     * Read single image frame.
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Read(string fileName);
//
//    /**
//     * Read single image frame.
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param width The width.
//     * @param height The height.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Read(string fileName, int width, int height);
//
//    /**
//     * Read single image frame.
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param format The format to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Read(string fileName, MagickFormat format);
//
//    /**
//     * Read single image frame.
//     * @param file The file to read the image from.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task ReadAsync(FileInfo file);
//
//    /**
//     * Read single image frame.
//     * @param file The file to read the image from.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task ReadAsync(FileInfo file, CancellationToken cancellationToken);
//
//    /**
//     * Read single image frame.
//     * @param file The file to read the image from.
//     * @param format The format to use.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task ReadAsync(FileInfo file, MagickFormat format);
//
//    /**
//     * Read single image frame.
//     * @param file The file to read the image from.
//     * @param format The format to use.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task ReadAsync(FileInfo file, MagickFormat format, CancellationToken cancellationToken);
//
//    /**
//     * Read single image frame.
//     * @param stream The stream to read the image data from.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task ReadAsync(Stream stream);
//
//    /**
//     * Read single image frame.
//     * @param stream The stream to read the image data from.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task ReadAsync(Stream stream, CancellationToken cancellationToken);
//
//    /**
//     * Read single image frame.
//     * @param stream The stream to read the image data from.
//     * @param format The format to use.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task ReadAsync(Stream stream, MagickFormat format);
//
//    /**
//     * Read single image frame.
//     * @param stream The stream to read the image data from.
//     * @param format The format to use.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task ReadAsync(Stream stream, MagickFormat format, CancellationToken cancellationToken);
//
//    /**
//     * Read single image frame.
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task ReadAsync(string fileName);
//
//    /**
//     * Read single image frame.
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task ReadAsync(string fileName, CancellationToken cancellationToken);
//
//    /**
//     * Read single image frame.
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param format The format to use.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task ReadAsync(string fileName, MagickFormat format);
//
//    /**
//     * Read single image frame.
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param format The format to use.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task ReadAsync(string fileName, MagickFormat format, CancellationToken cancellationToken);
//
//    /**
//     * Reduce noise in image using a noise peak elimination filter.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void ReduceNoise();
//
//    /**
//     * Reduce noise in image using a noise peak elimination filter.
//     * @param order The order to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void ReduceNoise(int order);
//
//    /**
//     * Associates a mask with the image as defined by the specified region.
//     * @param region The mask region.
//    void RegionMask(IMagickGeometry region);
//
//    /**
//     * Removes the artifact with the specified name.
//     * @param name The name of the artifact.
//    void RemoveArtifact(string name);
//
//    /**
//     * Removes the attribute with the specified name.
//     * @param name The name of the attribute.
//    void RemoveAttribute(string name);
//
//    /**
//     * Removes the region mask of the image.
//     */void RemoveRegionMask();
//
//    /**
//     * Remove a profile from the image.
//     * @param profile The profile to remove.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    public void RemoveProfile(IImageProfile profile);
//
//    /**
//     * Remove a named profile from the image.
//     * @param name The name of the profile (e.g. "ICM", "IPTC", or a generic profile name).
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void RemoveProfile(string name);
//
//    /**
//     * Removes the associated read mask of the image.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void RemoveReadMask();
//
//    /**
//     * Removes the associated write mask of the image.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void RemoveWriteMask();
//
//    /**
//     * Resets the page property of this image.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void RePage();
//
//    /**
//     * Resize image in terms of its pixel size.
//     * @param resolutionX The new X resolution.
//     * @param resolutionY The new Y resolution.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Resample(double resolutionX, double resolutionY);
//
//    /**
//     * Resize image in terms of its pixel size.
//     * @param density The density to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Resample(PointD density);
//
//    /**
//     * Resize image to specified size.
//     * <para />
//     * Resize will fit the image into the requested size. It does NOT fill, the requested box size.
//     * Use the [IMagickGeometry] overload for more control over the resulting size.
//     * @param width The new width.
//     * @param height The new height.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Resize(int width, int height);
//
//    /**
//     * Resize image to specified geometry.
//     * @param geometry The geometry to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Resize(IMagickGeometry geometry);
//
//    /**
//     * Resize image to specified percentage.
//     * @param percentage The percentage.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Resize(Percentage percentage);
//
//    /**
//     * Resize image to specified percentage.
//     * @param percentageWidth The percentage of the width.
//     * @param percentageHeight The percentage of the height.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Resize(Percentage percentageWidth, Percentage percentageHeight);
//
//    /**
//     * Roll image (rolls image vertically and horizontally).
//     * @param x The X offset from origin.
//     * @param y The Y offset from origin.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Roll(int x, int y);
//
//    /**
//     * Rotate image clockwise by specified number of degrees.
//     */ * <remarks>Specify a negative number for <paramref name="degrees"/> to rotate counter-clockwise.</remarks>
//     * @param degrees The number of degrees to rotate (positive to rotate clockwise, negative to rotate counter-clockwise).
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Rotate(double degrees);
//
//    /**
//     * Rotational blur image.
//     * @param angle The angle to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void RotationalBlur(double angle);
//
//    /**
//     * Rotational blur image.
//     * @param angle The angle to use.
//     * @param channels The channel(s) to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void RotationalBlur(double angle, Channels channels);
//
//    /**
//     * Resize image by using pixel sampling algorithm.
//     * <para />
//     * Resize will fit the image into the requested size. It does NOT fill, the requested box size.
//     * Use the [IMagickGeometry] overload for more control over the resulting size.
//     * @param width The new width.
//     * @param height The new height.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Sample(int width, int height);
//
//    /**
//     * Resize image by using pixel sampling algorithm.
//     * @param geometry The geometry to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Sample(IMagickGeometry geometry);
//
//    /**
//     * Resize image by using pixel sampling algorithm to the specified percentage.
//     * @param percentage The percentage.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Sample(Percentage percentage);
//
//    /**
//     * Resize image by using pixel sampling algorithm to the specified percentage.
//     * @param percentageWidth The percentage of the width.
//     * @param percentageHeight The percentage of the height.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Sample(Percentage percentageWidth, Percentage percentageHeight);
//
//    /**
//     * Resize image by using simple ratio algorithm.
//     * <para />
//     * Resize will fit the image into the requested size. It does NOT fill, the requested box size.
//     * Use the [IMagickGeometry] overload for more control over the resulting size.
//     * @param width The new width.
//     * @param height The new height.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Scale(int width, int height);
//
//    /**
//     * Resize image by using simple ratio algorithm.
//     * @param geometry The geometry to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Scale(IMagickGeometry geometry);
//
//    /**
//     * Resize image by using simple ratio algorithm to the specified percentage.
//     * @param percentage The percentage.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Scale(Percentage percentage);
//
//    /**
//     * Resize image by using simple ratio algorithm to the specified percentage.
//     * @param percentageWidth The percentage of the width.
//     * @param percentageHeight The percentage of the height.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Scale(Percentage percentageWidth, Percentage percentageHeight);
//
//    /**
//     * Segment (coalesce similar image components) by analyzing the histograms of the color
//     * components and identifying units that are homogeneous with the fuzzy c-means technique.
//     * Also uses QuantizeColorSpace and Verbose image attributes.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Segment();
//
//    /**
//     * Segment (coalesce similar image components) by analyzing the histograms of the color
//     * components and identifying units that are homogeneous with the fuzzy c-means technique.
//     * Also uses QuantizeColorSpace and Verbose image attributes.
//     * @param quantizeColorSpace Quantize colorspace.
//     * @param clusterThreshold This represents the minimum number of pixels contained in
//     * a hexahedra before it can be considered valid (expressed as a percentage).
//     * @param smoothingThreshold The smoothing threshold eliminates noise in the second
//     * derivative of the histogram. As the value is increased, you can expect a smoother second
//     * derivative.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Segment(ColorSpace quantizeColorSpace, double clusterThreshold, double smoothingThreshold);
//
//    /**
//     * Selectively blur pixels within a contrast threshold. It is similar to the unsharpen mask
//     * that sharpens everything with contrast above a certain threshold.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Gaussian, in pixels.
//     * @param threshold Only pixels within this contrast threshold are included in the blur operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SelectiveBlur(double radius, double sigma, double threshold);
//
//    /**
//     * Selectively blur pixels within a contrast threshold. It is similar to the unsharpen mask
//     * that sharpens everything with contrast above a certain threshold.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Gaussian, in pixels.
//     * @param threshold Only pixels within this contrast threshold are included in the blur operation.
//     * @param channels The channel(s) to blur.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SelectiveBlur(double radius, double sigma, double threshold, Channels channels);
//
//    /**
//     * Selectively blur pixels within a contrast threshold. It is similar to the unsharpen mask
//     * that sharpens everything with contrast above a certain threshold.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Gaussian, in pixels.
//     * @param thresholdPercentage Only pixels within this contrast threshold are included in the blur operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SelectiveBlur(double radius, double sigma, Percentage thresholdPercentage);
//
//    /**
//     * Selectively blur pixels within a contrast threshold. It is similar to the unsharpen mask
//     * that sharpens everything with contrast above a certain threshold.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Gaussian, in pixels.
//     * @param thresholdPercentage Only pixels within this contrast threshold are included in the blur operation.
//     * @param channels The channel(s) to blur.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SelectiveBlur(double radius, double sigma, Percentage thresholdPercentage, Channels channels);
//
//    /**
//     * Applies a special effect to the image, similar to the effect achieved in a photo darkroom
//     * by sepia toning.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SepiaTone();
//
//    /**
//     * Applies a special effect to the image, similar to the effect achieved in a photo darkroom
//     * by sepia toning.
//     * @param threshold The tone threshold.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SepiaTone(Percentage threshold);
//
//    /**
//     * Inserts the artifact with the specified name and value into the artifact tree of the image.
//     * @param name The name of the artifact.
//     * @param value The value of the artifact.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SetArtifact(string name, string value);
//
//    /**
//     * Inserts the artifact with the specified name and value into the artifact tree of the image.
//     * @param name The name of the artifact.
//     * @param flag The value of the artifact.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SetArtifact(string name, bool flag);
//
//    /**
//     * Lessen (or intensify) when adding noise to an image.
//     * @param attenuate The attenuate value.
//    void SetAttenuate(double attenuate);
//
//    /**
//     * Sets a named image attribute.
//     * @param name The name of the attribute.
//     * @param value The value of the attribute.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SetAttribute(string name, string value);
//
//    /**
//     * Sets a named image attribute.
//     * @param name The name of the attribute.
//     * @param flag The value of the attribute.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SetAttribute(string name, bool flag);
//
//    /**
//     * Set the bit depth (bits allocated to red/green/blue components).
//     * @param value The depth.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SetBitDepth(int value);
//
//    /**
//     * Set the bit depth (bits allocated to red/green/blue components) of the specified channel.
//     * @param value The depth.
//     * @param channels The channel to set the depth for.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SetBitDepth(int value, Channels channels);
//
//    /**
//     * Sets the default clipping path.
//     * @param value The clipping path.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SetClippingPath(string value);
//
//    /**
//     * Sets the clipping path with the specified name.
//     * @param value The clipping path.
//     * @param pathName Name of clipping path resource. If name is preceded by #, use clipping path numbered by name.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SetClippingPath(string value, string pathName);
//
//    /**
//     * Gets the compression of the image. This method should only be used when the encoder uses the compression of the image. For
//     * most usecases Setting.Compression should be used instead.
//     * @param compression The compression method.
//    void SetCompression(CompressionMethod compression);
//
//    /**
//     * Set the specified profile of the image. If a profile with the same name already exists it will be overwritten.
//     * @param profile The profile to set.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SetProfile(IImageProfile profile);
//
//    /**
//     * Set the specified profile of the image. If a profile with the same name already exists it will be overwritten.
//     * @param profile The profile to set.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SetProfile(IColorProfile profile);
//
//    /**
//     * Set the specified profile of the image. If a profile with the same name already exists it will be overwritten.
//     * @param profile The profile to set.
//     * @param mode The color transformation mode.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SetProfile(IColorProfile profile, ColorTransformMode mode);
//
//    /**
//     * Sets the associated read mask of the image. The mask must be the same dimensions as the image and
//     * only contain the colors black and white.
//     * @param image The image that contains the read mask.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SetReadMask(IMagickImage image);
//
//    /**
//     * Sets the associated write mask of the image. The mask must be the same dimensions as the image and
//     * only contains the colors black and white.
//     * @param image The image that contains the write mask.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SetWriteMask(IMagickImage image);
//
//    /**
//     * Simulate an image shadow.
//     * @param x the shadow x-offset.
//     * @param y the shadow y-offset.
//     * @param sigma The standard deviation of the Gaussian, in pixels.
//     * @param alpha Transparency percentage.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Shadow(int x, int y, double sigma, Percentage alpha);
//
//    /**
//     * Sharpen pixels in image.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Sharpen();
//
//    /**
//     * Sharpen pixels in image.
//     * @param channels The channel(s) that should be sharpened.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Sharpen(Channels channels);
//
//    /**
//     * Sharpen pixels in image.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Sharpen(double radius, double sigma);
//
//    /**
//     * Sharpen pixels in image.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @param channels The channel(s) that should be sharpened.
//    void Sharpen(double radius, double sigma, Channels channels);
//
//    /**
//     * Shave pixels from image edges.
//     * @param size The size of to shave of the image.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Shave(int size);
//
//    /**
//     * Shave pixels from image edges.
//     * @param leftRight The number of pixels to shave left and right.
//     * @param topBottom The number of pixels to shave top and bottom.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Shave(int leftRight, int topBottom);
//
//    /**
//     * Shear image (create parallelogram by sliding image by X or Y axis).
//     * @param xAngle Specifies the number of x degrees to shear the image.
//     * @param yAngle Specifies the number of y degrees to shear the image.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Shear(double xAngle, double yAngle);
//
//    /**
//     * Adjust the image contrast with a non-linear sigmoidal contrast algorithm.
//     * @param contrast The contrast.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SigmoidalContrast(double contrast);
//
//    /**
//     * Adjust the image contrast with a non-linear sigmoidal contrast algorithm.
//     * @param contrast The contrast to use.
//     * @param midpoint The midpoint to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SigmoidalContrast(double contrast, double midpoint);
//
//    /**
//     * Adjust the image contrast with a non-linear sigmoidal contrast algorithm.
//     * @param contrast The contrast to use.
//     * @param midpoint The midpoint to use.
//     * @param channels The channel(s) that should be adjusted.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SigmoidalContrast(double contrast, double midpoint, Channels channels);
//
//    /**
//     * Adjust the image contrast with a non-linear sigmoidal contrast algorithm.
//     * @param contrast The contrast to use.
//     * @param midpointPercentage The midpoint to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SigmoidalContrast(double contrast, Percentage midpointPercentage);
//
//    /**
//     * Simulates a pencil sketch.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Sketch();
//
//    /**
//     * Simulates a pencil sketch. We convolve the image with a Gaussian operator of the given
//     * radius and standard deviation (sigma). For reasonable results, radius should be larger than sigma.
//     * Use a radius of 0 and sketch selects a suitable radius for you.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @param angle Apply the effect along this angle.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Sketch(double radius, double sigma, double angle);
//
//    /**
//     * Solarize image (similar to effect seen when exposing a photographic film to light during
//     * the development process).
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Solarize();
//
//    /**
//     * Solarize image (similar to effect seen when exposing a photographic film to light during
//     * the development process).
//     * @param factor The factor to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Solarize(double factor);
//
//    /**
//     * Sort pixels within each scanline in ascending order of intensity.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void SortPixels();
//
//    /**
//     * Solarize image (similar to effect seen when exposing a photographic film to light during
//     * the development process).
//     * @param factorPercentage The factor to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Solarize(Percentage factorPercentage);
//
//    /**
//     * Splice the background color into the image.
//     * @param geometry The geometry to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Splice(IMagickGeometry geometry);
//
//    /**
//     * Spread pixels randomly within image.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Spread();
//
//    /**
//     * Spread pixels randomly within image by specified amount.
//     * @param radius Choose a random pixel in a neighborhood of this extent.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Spread(double radius);
//
//    /**
//     * Spread pixels randomly within image by specified amount.
//     * @param method Pixel interpolate method.
//     * @param radius Choose a random pixel in a neighborhood of this extent.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Spread(PixelInterpolateMethod method, double radius);
//
//    /**
//     * Makes each pixel the min / max / median / mode / etc. of the neighborhood of the specified width
//     * and height.
//     * @param type The statistic type.
//     * @param width The width of the pixel neighborhood.
//     * @param height The height of the pixel neighborhood.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Statistic(StatisticType type, int width, int height);
//
//    /**
//     * Returns the image statistics.
//     */ * @return The image statistics.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    IStatistics Statistics();
//
//    /**
//     * Returns the image statistics.
//     */ * @return The image statistics.
//     * @param channels The channel(s) that should be used.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    IStatistics Statistics(Channels channels);
//
//    /**
//     * Shade image using distant light source.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Shade();
//
//    /**
//     * Shade image using distant light source.
//     * @param azimuth The azimuth of the light source direction.
//     * @param elevation The elevation of the light source direction.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Shade(double azimuth, double elevation);
//
//    /**
//     * Shade image using distant light source.
//     * @param azimuth The azimuth of the light source direction.
//     * @param elevation The elevation of the light source direction.
//     * @param channels The channel(s) that should be shaded.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Shade(double azimuth, double elevation, Channels channels);
//
//    /**
//     * Shade image using distant light source and make it grayscale.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void ShadeGrayscale();
//
//    /**
//     * Shade image using distant light source and make it grayscale.
//     * @param azimuth The azimuth of the light source direction.
//     * @param elevation The elevation of the light source direction.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void ShadeGrayscale(double azimuth, double elevation);
//
//    /**
//     * Shade image using distant light source and make it grayscale.
//     * @param azimuth The azimuth of the light source direction.
//     * @param elevation The elevation of the light source direction.
//     * @param channels The channel(s) that should be shaded.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void ShadeGrayscale(double azimuth, double elevation, Channels channels);
//
//    /**
//     * Simulate an image shadow.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Shadow();
//
//    /**
//     * Add a digital watermark to the image (based on second image).
//     * @param watermark The image to use as a watermark.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Stegano(IMagickImage watermark);
//
//    /**
//     * Create an image which appears in stereo when viewed with red-blue glasses (Red image on
//     * left, blue on right).
//     * @param rightImage The image to use as the right part of the resulting image.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Stereo(IMagickImage rightImage);
//
//    /**
//     * Strips an image of all profiles and comments.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Strip();
//
//    /**
//     * Swirl image (image pixels are rotated by degrees).
//     * @param degrees The number of degrees.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Swirl(double degrees);
//
//    /**
//     * Swirl image (image pixels are rotated by degrees).
//     * @param method Pixel interpolate method.
//     * @param degrees The number of degrees.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Swirl(PixelInterpolateMethod method, double degrees);
//
//    /**
//     * Channel a texture on image background.
//     * @param image The image to use as a texture on the image background.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Texture(IMagickImage image);
//
//    /**
//     * Threshold image.
//     * @param percentage The threshold percentage.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Threshold(Percentage percentage);
//
//    /**
//     * Threshold image.
//     * @param percentage The threshold percentage.
//     * @param channels The channel(s) that should be thresholded.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Threshold(Percentage percentage, Channels channels);
//
//    /**
//     * Resize image to thumbnail size and remove all the image profiles except the icc/icm profile.
//     * <para />
//     * Resize will fit the image into the requested size. It does NOT fill, the requested box size.
//     * Use the [IMagickGeometry] overload for more control over the resulting size.
//     * @param width The new width.
//     * @param height The new height.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Thumbnail(int width, int height);
//
//    /**
//     * Resize image to thumbnail size and remove all the image profiles except the icc/icm profile.
//     * @param geometry The geometry to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Thumbnail(IMagickGeometry geometry);
//
//    /**
//     * Resize image to thumbnail size and remove all the image profiles except the icc/icm profile.
//     * @param percentage The percentage.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Thumbnail(Percentage percentage);
//
//    /**
//     * Resize image to thumbnail size and remove all the image profiles except the icc/icm profile.
//     * @param percentageWidth The percentage of the width.
//     * @param percentageHeight The percentage of the height.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Thumbnail(Percentage percentageWidth, Percentage percentageHeight);
//
//    /**
//     * Compose an image repeated across and down the image.
//     * @param image The image to composite with this image.
//     * @param compose The algorithm to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Tile(IMagickImage image, CompositeOperator compose);
//
//    /**
//     * Compose an image repeated across and down the image.
//     * @param image The image to composite with this image.
//     * @param compose The algorithm to use.
//     * @param args The arguments for the algorithm (compose:args).
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Tile(IMagickImage image, CompositeOperator compose, string? args);
//
//    /**
//     * Converts this instance to a base64 [string].
//     */ * @return A base64 [string].
//    string ToBase64();
//
//    /**
//     * Converts this instance to a base64 [string].
//     * @param format The format to use.
//     * @return A base64 [string].
//    string ToBase64(MagickFormat format);
//
//    /**
//     * Converts this instance to a [byte] array.
//     */ * @return A [byte] array.
//    byte[] ToByteArray();
//
//    /**
//     * Converts this instance to a [byte] array.
//     * @param defines The defines to set.
//     * @return A [byte] array.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    byte[] ToByteArray(IWriteDefines defines);
//
//    /**
//     * Converts this instance to a [byte] array.
//     * @param format The format to use.
//     * @return A [byte] array.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    byte[] ToByteArray(MagickFormat format);
//
//    /**
//     * Transforms the image from the colorspace of the source profile to the target profile. This
//     * requires the image to have a color profile. Nothing will happen if the image has no color profile.
//     * @param target The target color profile.
//     * @return True when the colorspace was transformed otherwise false.
//    bool TransformColorSpace(IColorProfile target);
//
//    /**
//     * Transforms the image from the colorspace of the source profile to the target profile. This
//     * requires the image to have a color profile. Nothing will happen if the image has no color profile.
//     * @param target The target color profile.
//     * @param mode The color transformation mode.
//     * @return True when the colorspace was transformed otherwise false.
//    bool TransformColorSpace(IColorProfile target, ColorTransformMode mode);
//
//    /**
//     * Transforms the image from the colorspace of the source profile to the target profile. The
//     * source profile will only be used if the image does not contain a color profile. Nothing
//     * will happen if the source profile has a different colorspace then that of the image.
//     * @param source The source color profile.
//     * @param target The target color profile.
//     * @return True when the colorspace was transformed otherwise false.
//    bool TransformColorSpace(IColorProfile source, IColorProfile target);
//
//    /**
//     * Transforms the image from the colorspace of the source profile to the target profile. The
//     * source profile will only be used if the image does not contain a color profile. Nothing
//     * will happen if the source profile has a different colorspace then that of the image.
//     * @param source The source color profile.
//     * @param target The target color profile.
//     * @param mode The color transformation mode.
//     * @return True when the colorspace was transformed otherwise false.
//    bool TransformColorSpace(IColorProfile source, IColorProfile target, ColorTransformMode mode);
//
//    /**
//     * Creates a horizontal mirror image by reflecting the pixels around the central y-axis while
//     * rotating them by 90 degrees.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Transpose();
//
//    /**
//     * Creates a vertical mirror image by reflecting the pixels around the central x-axis while
//     * rotating them by 270 degrees.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Transverse();
//
//    /**
//     * Trim edges that are the background color from the image.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Trim();
//
//    /**
//     * Trim the specified edges that are the background color from the image.
//     * @param edges The edges that need to be trimmed.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Trim(params Gravity[] edges);
//
//    /**
//     * Trim edges that are the background color from the image.
//     * @param percentBackground The percentage of background pixels permitted in the outer rows and columns.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Trim(Percentage percentBackground);
//
//    /**
//     * Replace image with a sharpened version of the original image using the unsharp mask algorithm.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void UnsharpMask(double radius, double sigma);
//
//    /**
//     * Replace image with a sharpened version of the original image using the unsharp mask algorithm.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @param channels The channel(s) that should be sharpened.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void UnsharpMask(double radius, double sigma, Channels channels);
//
//    /**
//     * Replace image with a sharpened version of the original image using the unsharp mask algorithm.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @param amount The percentage of the difference between the original and the blur image
//     * that is added back into the original.
//     * @param threshold The threshold in pixels needed to apply the diffence amount.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void UnsharpMask(double radius, double sigma, double amount, double threshold);
//
//    /**
//     * Replace image with a sharpened version of the original image using the unsharp mask algorithm.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @param amount The percentage of the difference between the original and the blur image
//     * that is added back into the original.
//     * @param threshold The threshold in pixels needed to apply the diffence amount.
//     * @param channels The channel(s) that should be sharpened.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void UnsharpMask(double radius, double sigma, double amount, double threshold, Channels channels);
//
//    /**
//     * Softens the edges of the image in vignette style.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Vignette();
//
//    /**
//     * Softens the edges of the image in vignette style.
//     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
//     * @param sigma The standard deviation of the Laplacian, in pixels.
//     * @param x The x ellipse offset.
//     * @param y the y ellipse offset.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Vignette(double radius, double sigma, int x, int y);
//
//    /**
//     * Map image pixels to a sine wave.
//     */ * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Wave();
//
//    /**
//     * Map image pixels to a sine wave.
//     * @param method Pixel interpolate method.
//     * @param amplitude The amplitude.
//     * @param length The length of the wave.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Wave(PixelInterpolateMethod method, double amplitude, double length);
//
//    /**
//     * Removes noise from the image using a wavelet transform.
//     * @param thresholdPercentage The threshold for smoothing.
//    void WaveletDenoise(Percentage thresholdPercentage);
//
//    /**
//     * Removes noise from the image using a wavelet transform.
//     * @param thresholdPercentage The threshold for smoothing.
//     * @param softness Attenuate the smoothing threshold.
//    void WaveletDenoise(Percentage thresholdPercentage, double softness);
//
//    /**
//     * Apply a white balancing to an image according to a grayworld assumption in the LAB colorspace.
//     */void WhiteBalance();
//
//    /**
//     * Apply a white balancing to an image according to a grayworld assumption in the LAB colorspace.
//     * @param vibrance The vibrance.
//    void WhiteBalance(Percentage vibrance);
//
//    /**
//     * Forces all pixels above the threshold into white while leaving all pixels at or below
//     * the threshold unchanged.
//     * @param threshold The threshold to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void WhiteThreshold(Percentage threshold);
//
//    /**
//     * Forces all pixels above the threshold into white while leaving all pixels at or below
//     * the threshold unchanged.
//     * @param threshold The threshold to use.
//     * @param channels The channel(s) to make black.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void WhiteThreshold(Percentage threshold, Channels channels);
//
//    /**
//     * Writes the image to the specified file.
//     * @param file The file to write the image to.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Write(FileInfo file);
//
//    /**
//     * Writes the image to the specified file.
//     * @param file The file to write the image to.
//     * @param defines The defines to set.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Write(FileInfo file, IWriteDefines defines);
//
//    /**
//     * Writes the image to the specified file.
//     * @param file The file to write the image to.
//     * @param format The format to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Write(FileInfo file, MagickFormat format);
//
//    /**
//     * Writes the image to the specified stream.
//     * @param stream The stream to write the image data to.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Write(Stream stream);
//
//    /**
//     * Writes the image to the specified stream.
//     * @param stream The stream to write the image data to.
//     * @param defines The defines to set.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Write(Stream stream, IWriteDefines defines);
//
//    /**
//     * Writes the image to the specified stream.
//     * @param stream The stream to write the image data to.
//     * @param format The format to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Write(Stream stream, MagickFormat format);
//
//    /**
//     * Writes the image to the specified file name.
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Write(string fileName);
//
//    /**
//     * Writes the image to the specified file name.
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param defines The defines to set.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Write(string fileName, IWriteDefines defines);
//
//    /**
//     * Writes the image to the specified file name.
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param format The format to use.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    void Write(string fileName, MagickFormat format);
//
//    /**
//     * Writes the image to the specified file.
//     * @param file The file to write the image to.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task WriteAsync(FileInfo file);
//
//    /**
//     * Writes the image to the specified file.
//     * @param file The file to write the image to.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task WriteAsync(FileInfo file, CancellationToken cancellationToken);
//
//    /**
//     * Writes the image to the specified file.
//     * @param file The file to write the image to.
//     * @param defines The defines to set.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task WriteAsync(FileInfo file, IWriteDefines defines);
//
//    /**
//     * Writes the image to the specified file.
//     * @param file The file to write the image to.
//     * @param defines The defines to set.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task WriteAsync(FileInfo file, IWriteDefines defines, CancellationToken cancellationToken);
//
//    /**
//     * Writes the image to the specified file.
//     * @param file The file to write the image to.
//     * @param format The format to use.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task WriteAsync(FileInfo file, MagickFormat format);
//
//    /**
//     * Writes the image to the specified file.
//     * @param file The file to write the image to.
//     * @param format The format to use.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task WriteAsync(FileInfo file, MagickFormat format, CancellationToken cancellationToken);
//
//    /**
//     * Writes the image to the specified stream.
//     * @param stream The stream to write the image data to.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task WriteAsync(Stream stream);
//
//    /**
//     * Writes the image to the specified stream.
//     * @param stream The stream to write the image data to.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task WriteAsync(Stream stream, CancellationToken cancellationToken);
//
//    /**
//     * Writes the image to the specified stream.
//     * @param stream The stream to write the image data to.
//     * @param defines The defines to set.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task WriteAsync(Stream stream, IWriteDefines defines);
//
//    /**
//     * Writes the image to the specified stream.
//     * @param stream The stream to write the image data to.
//     * @param defines The defines to set.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task WriteAsync(Stream stream, IWriteDefines defines, CancellationToken cancellationToken);
//
//    /**
//     * Writes the image to the specified stream.
//     * @param stream The stream to write the image data to.
//     * @param format The format to use.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task WriteAsync(Stream stream, MagickFormat format);
//
//    /**
//     * Writes the image to the specified stream.
//     * @param stream The stream to write the image data to.
//     * @param format The format to use.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task WriteAsync(Stream stream, MagickFormat format, CancellationToken cancellationToken);
//
//    /**
//     * Writes the image to the specified file name.
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task WriteAsync(string fileName);
//
//    /**
//     * Writes the image to the specified file name.
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task WriteAsync(string fileName, CancellationToken cancellationToken);
//
//    /**
//     * Writes the image to the specified file name.
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param defines The defines to set.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task WriteAsync(string fileName, IWriteDefines defines);
//
//    /**
//     * Writes the image to the specified file name.
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param defines The defines to set.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task WriteAsync(string fileName, IWriteDefines defines, CancellationToken cancellationToken);
//
//    /**
//     * Writes the image to the specified file name.
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param format The format to use.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task WriteAsync(string fileName, MagickFormat format);
//
//    /**
//     * Writes the image to the specified file name.
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param format The format to use.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws [MagickException] Thrown when an error is raised by ImageMagick.
//    Task WriteAsync(string fileName, MagickFormat format, CancellationToken cancellationToken);
}
