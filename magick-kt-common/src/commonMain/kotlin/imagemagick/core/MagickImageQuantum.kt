package imagemagick.core

import imagemagick.core.enums.Channels
import imagemagick.core.exceptions.MagickException
import kotlinx.io.Source
import kotlinx.io.files.Path
import imagemagick.core.MagickImage as IMagickImage
import imagemagick.core.colors.MagickColorQuantum as IMagickColorQuantum
import imagemagick.core.settings.CompareSettingsQuantum as ICompareSettingsQuantum
import imagemagick.core.settings.MagickReadSettings as IMagickReadSettings
import imagemagick.core.settings.MagickSettings as IMagickSettings

/**
 * Interface that represents an ImageMagick image.
 *
 * @param TQuantumType The quantum type.
 */
public interface MagickImageQuantum<TQuantumType> : IMagickImage where TQuantumType : Any {
    /**
     * Gets or sets the background color of the image.
     */
    public var backgroundColor: IMagickColorQuantum<TQuantumType>?

    /**
     * Gets or sets the border color of the image.
     */
    public var borderColor: IMagickColorQuantum<TQuantumType>?

    /**
     * Gets or sets the matte color.
     */
    public var matteColor: IMagickColorQuantum<TQuantumType>?

    /**
     * Gets the settings for this instance.
     */
    public val settings: IMagickSettings<TQuantumType>

    /**
     * Creates a clone of the current image.
     *
     * @return A clone of the current image.
     */
    public fun clone(): MagickImageQuantum<TQuantumType>

//
//    /**
//     * Creates a clone of the current image with the specified geometry.
//     *
//     * @param geometry The area to clone.
//     * @return A clone of the current image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    MagickImageQuantum<TQuantumType> Clone(IMagickGeometry geometry);
//
//    /**
//     * Creates a clone of the current image.
//     *
//     * @param width The width of the area to clone.
//     * @param height The height of the area to clone.
//     * @return A clone of the current image.
//     */
//    MagickImageQuantum<TQuantumType> Clone(int width, int height);
//
//    /**
//     * Creates a clone of the current image.
//     *
//     * @param x The X offset from origin.
//     * @param y The Y offset from origin.
//     * @param width The width of the area to clone.
//     * @param height The height of the area to clone.
//     * @return A clone of the current image.
//     */
//    MagickImageQuantum<TQuantumType> Clone(int x, int y, int width, int height);
//
//    /**
//     * Sets the alpha channel to the specified color.
//     *
//     * @param color The color to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun colorAlpha(color: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Colorize image with the specified color, using specified percent alpha.
//     *
//     * @param color The color to use.
//     * @param alpha The alpha percentage.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun colorize(color: IMagickColorQuantum<TQuantumType>, alpha: Percentage)
//
//    /**
//     * Colorize image with the specified color, using specified percent alpha for red, green,
//     * and blue quantums.
//     *
//     * @param color The color to use.
//     * @param alphaRed The alpha percentage for red.
//     * @param alphaGreen The alpha percentage for green.
//     * @param alphaBlue The alpha percentage for blue.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun colorize(color: IMagickColorQuantum<TQuantumType>, alphaRed: Percentage, alphaGreen: Percentage, alphaBlue: Percentage)
//
//    /**
//     * Forces all pixels in the color range to white otherwise black.
//     *
//     * @param startColor The start color of the color range.
//     * @param stopColor The stop color of the color range.
//     */
//    public fun colorThreshold(startColor: IMagickColorQuantum<TQuantumType>, stopColor: IMagickColorQuantum<TQuantumType>)

    /**
     * Returns the distortion based on the specified metric.
     *
     * @param image The other image to compare with this image.
     * @param settings The settings to use.
     * @param difference The image that will contain the difference.
     * @return The distortion based on the specified metric.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun compare(
        image: IMagickImage,
        settings: ICompareSettingsQuantum<TQuantumType>,
        difference: IMagickImage,
    ): Double = compare(image, settings, difference, Channels.UNDEFINED)

    /**
     * Returns the distortion based on the specified metric.
     *
     * @param image The other image to compare with this image.
     * @param settings The settings to use.
     * @param difference The image that will contain the difference.
     * @param channels The channel(s) to compare.
     * @return The distortion based on the specified metric.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public fun compare(
        image: IMagickImage,
        settings: ICompareSettingsQuantum<TQuantumType>,
        difference: IMagickImage,
        channels: Channels,
    ): Double

//    /**
//     * Determines the connected-components of the image.
//     *
//     * @param connectivity How many neighbors to visit, choose from 4 or 8.
//     * @return The connected-components of the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IReadOnlyCollection<IConnectedComponent<TQuantumType>> ConnectedComponents(int connectivity);
//
//    /**
//     * Determines the connected-components of the image.
//     *
//     * @param settings The settings for this operation.
//     * @return The connected-components of the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IReadOnlyCollection<IConnectedComponent<TQuantumType>> ConnectedComponents(IConnectedComponentsSettings settings);
//
//    /**
//     * Creates tiles of the current image in the specified dimension.
//     *
//     * @param width The width of the tile.
//     * @param height The height of the tile.
//     * @return New title of the current image.
//     */
//    IReadOnlyCollection<MagickImageQuantum<TQuantumType>> CropToTiles(int width, int height);
//
//    /**
//     * Creates tiles of the current image in the specified dimension.
//     *
//     * @param geometry The size of the tile.
//     * @return New title of the current image.
//     */
//    IReadOnlyCollection<MagickImageQuantum<TQuantumType>> CropToTiles(IMagickGeometry geometry);
//
//    /**
//     * Draw on image using one or more drawables.
//     *
//     * @param drawables The drawable(s) to draw on the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun draw(drawables: IDrawables<TQuantumType>)
//
//    /**
//     * Extend the image as defined by the width and height.
//     *
//     * @param width The width to extend the image to.
//     * @param height The height to extend the image to.
//     * @param backgroundColor The background color to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun extent(width: Int, height: Int, backgroundColor: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Extend the image as defined by the width and height.
//     *
//     * @param width The width to extend the image to.
//     * @param height The height to extend the image to.
//     * @param gravity The placement gravity.
//     * @param backgroundColor The background color to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun extent(width: Int, height: Int, gravity: Gravity, backgroundColor: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Extend the image as defined by the geometry.
//     *
//     * @param geometry The geometry to extend the image to.
//     * @param backgroundColor The background color to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun extent(geometry: IMagickGeometry, backgroundColor: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Extend the image as defined by the geometry.
//     *
//     * @param geometry The geometry to extend the image to.
//     * @param gravity The placement gravity.
//     * @param backgroundColor The background color to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun extent(geometry: IMagickGeometry, gravity: Gravity, backgroundColor: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Floodfill pixels matching color (within fuzz factor) of target pixel(x,y) with replacement
//     * alpha value using method.
//     *
//     * @param alpha The alpha to use.
//     * @param x The X coordinate.
//     * @param y The Y coordinate.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun floodFill(alpha: TQuantumType, x: Int, y: Int)
//
//    /**
//     * Flood-fill color across pixels that match the color of the target pixel and are neighbors
//     * of the target pixel. Uses current fuzz setting when determining color match.
//     *
//     * @param color The color to use.
//     * @param x The X coordinate.
//     * @param y The Y coordinate.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun floodFill(color: IMagickColorQuantum<TQuantumType>, x: Int, y: Int)
//
//    /**
//     * Flood-fill color across pixels that match the color of the target pixel and are neighbors
//     * of the target pixel. Uses current fuzz setting when determining color match.
//     *
//     * @param color The color to use.
//     * @param x The X coordinate.
//     * @param y The Y coordinate.
//     * @param target The target color.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun floodFill(color: IMagickColorQuantum<TQuantumType>, x: Int, y: Int, target: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Flood-fill texture across pixels that match the color of the target pixel and are neighbors
//     * of the target pixel. Uses current fuzz setting when determining color match.
//     *
//     * @param image The image to use.
//     * @param x The X coordinate.
//     * @param y The Y coordinate.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun floodFill(image: MagickImageQuantum<TQuantumType>, x: Int, y: Int)
//
//    /**
//     * Flood-fill texture across pixels that match the color of the target pixel and are neighbors
//     * of the target pixel. Uses current fuzz setting when determining color match.
//     *
//     * @param image The image to use.
//     * @param x The X coordinate.
//     * @param y The Y coordinate.
//     * @param target The target color.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun floodFill(image: MagickImageQuantum<TQuantumType>, x: Int, y: Int, target: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Returns the color at colormap position index.
//     *
//     * @param index The position index.
//     * @return The color at colormap position index.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IMagickColorQuantum<TQuantumType>? GetColormapColor(int index);
//
//    /**
//     * Returns a pixel collection that can be used to read or modify the pixels of this image.
//     *
//     * @return A pixel collection that can be used to read or modify the pixels of this image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IPixelCollection<TQuantumType> GetPixels();
//
//    /**
//     * Returns a pixel collection that can be used to read or modify the pixels of this image. This instance
//     * will not do any bounds checking and directly call ImageMagick.
//     *
//     * @return A pixel collection that can be used to read or modify the pixels of this image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IUnsafePixelCollection<TQuantumType> GetPixelsUnsafe();
//
//    /**
//     * Gets the associated read mask of the image.
//     *
//     * @return The associated read mask of the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    MagickImageQuantum<TQuantumType>? GetReadMask();
//
//    /**
//     * Gets the associated write mask of the image.
//     *
//     * @return The associated write mask of the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    MagickImageQuantum<TQuantumType>? GetWriteMask();
//
//    /**
//     * Creates a color histogram.
//     *
//     * @return A color histogram.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IReadOnlyDictionary<IMagickColorQuantum<TQuantumType>, int> Histogram();
//
//    #if !Q8
//
//    /**
//     * Import pixels from the specified quantum array into the current image.
//     *
//     * @param data The quantum array to read the pixels from.
//     * @param settings The import settings to use when importing the pixels.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun importPixels(data: TQuantumType[], settings: IPixelImportSettings)
//
//    /**
//     * Import pixels from the specified quantum array into the current image.
//     *
//     * @param data The quantum array to read the pixels from.
//     * @param offset The offset at which to begin reading data.
//     * @param settings The import settings to use when importing the pixels.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun importPixels(data: TQuantumType[], offset: Int, settings: IPixelImportSettings)
//    #endif
//
//    /**
//     * Returns the sum of values (pixel values) in the image.
//     *
//     * @return The sum of values (pixel values) in the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    MagickImageQuantum<TQuantumType>? Integral();
//
//    /**
//     * Floodfill pixels not matching color (within fuzz factor) of target pixel(x,y) with
//     * replacement alpha value using method.
//     *
//     * @param alpha The alpha to use.
//     * @param x The X coordinate.
//     * @param y The Y coordinate.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseFloodFill(alpha: TQuantumType, x: Int, y: Int)
//
//    /**
//     * Flood-fill texture across pixels that do not match the color of the target pixel and are
//     * neighbors of the target pixel. Uses current fuzz setting when determining color match.
//     *
//     * @param color The color to use.
//     * @param x The X coordinate.
//     * @param y The Y coordinate.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseFloodFill(color: IMagickColorQuantum<TQuantumType>, x: Int, y: Int)
//
//    /**
//     * Flood-fill texture across pixels that do not match the color of the target pixel and are
//     * neighbors of the target pixel. Uses current fuzz setting when determining color match.
//     *
//     * @param color The color to use.
//     * @param x The X coordinate.
//     * @param y The Y coordinate.
//     * @param target The target color.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseFloodFill(color: IMagickColorQuantum<TQuantumType>, x: Int, y: Int, target: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Flood-fill texture across pixels that do not match the color of the target pixel and are
//     * neighbors of the target pixel. Uses current fuzz setting when determining color match.
//     *
//     * @param image The image to use.
//     * @param x The X coordinate.
//     * @param y The Y coordinate.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseFloodFill(image: MagickImageQuantum<TQuantumType>, x: Int, y: Int)
//
//    /**
//     * Flood-fill texture across pixels that match the color of the target pixel and are neighbors
//     * of the target pixel. Uses current fuzz setting when determining color match.
//     *
//     * @param image The image to use.
//     * @param x The X coordinate.
//     * @param y The Y coordinate.
//     * @param target The target color.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseFloodFill(image: MagickImageQuantum<TQuantumType>, x: Int, y: Int, target: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Applies the reversed level operation to just the specific channels specified. It compresses
//     * the full range of color values, so that they lie between the given black and white points.
//     * Gamma is applied before the values are mapped. Uses a midpoint of 1.0.
//     *
//     * @param blackPoint The darkest color in the image. Colors darker are set to zero.
//     * @param whitePoint The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseLevel(blackPoint: TQuantumType, whitePoint: TQuantumType)
//
//    /**
//     * Applies the reversed level operation to just the specific channels specified. It compresses
//     * the full range of color values, so that they lie between the given black and white points.
//     * Gamma is applied before the values are mapped. Uses a midpoint of 1.0.
//     *
//     * @param blackPoint The darkest color in the image. Colors darker are set to zero.
//     * @param whitePoint The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @param channels The channel(s) to level.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseLevel(blackPoint: TQuantumType, whitePoint: TQuantumType, channels: Channels)
//
//    /**
//     * Applies the reversed level operation to just the specific channels specified. It compresses
//     * the full range of color values, so that they lie between the given black and white points.
//     * Gamma is applied before the values are mapped.
//     *
//     * @param blackPoint The darkest color in the image. Colors darker are set to zero.
//     * @param whitePoint The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @param midpoint The gamma correction to apply to the image. (Useful range of 0 to 10).
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseLevel(blackPoint: TQuantumType, whitePoint: TQuantumType, midpoint: Double)
//
//    /**
//     * Applies the reversed level operation to just the specific channels specified. It compresses
//     * the full range of color values, so that they lie between the given black and white points.
//     * Gamma is applied before the values are mapped.
//     *
//     * @param blackPoint The darkest color in the image. Colors darker are set to zero.
//     * @param whitePoint The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @param midpoint The gamma correction to apply to the image. (Useful range of 0 to 10).
//     * @param channels The channel(s) to level.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseLevel(blackPoint: TQuantumType, whitePoint: TQuantumType, midpoint: Double, channels: Channels)
//
//    /**
//     * Maps the given color to "black" and "white" values, linearly spreading out the colors, and
//     * level values on a channel by channel bases, as per level(). The given colors allows you to
//     * specify different level ranges for each of the color channels separately.
//     *
//     * @param blackColor The color to map black to/from.
//     * @param whiteColor The color to map white to/from.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseLevelColors(blackColor: IMagickColorQuantum<TQuantumType>, whiteColor: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Maps the given color to "black" and "white" values, linearly spreading out the colors, and
//     * level values on a channel by channel bases, as per level(). The given colors allows you to
//     * specify different level ranges for each of the color channels separately.
//     *
//     * @param blackColor The color to map black to/from.
//     * @param whiteColor The color to map white to/from.
//     * @param channels The channel(s) to level.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseLevelColors(blackColor: IMagickColorQuantum<TQuantumType>, whiteColor: IMagickColorQuantum<TQuantumType>, channels: Channels)
//
//    /**
//     * Changes any pixel that does not match the target with the color defined by fill.
//     *
//     * @param target The color to replace.
//     * @param fill The color to replace opaque color with.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseOpaque(target: IMagickColorQuantum<TQuantumType>, fill: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Add alpha channel to image, setting pixels that don't match the specified color to transparent.
//     *
//     * @param color The color that should not be made transparent.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseTransparent(color: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Add alpha channel to image, setting pixels that don't lie in between the given two colors to
//     * transparent.
//     *
//     * @param colorLow The low target color.
//     * @param colorHigh The high target color.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun inverseTransparentChroma(colorLow: IMagickColorQuantum<TQuantumType>, colorHigh: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Adjust the levels of the image by scaling the colors falling between specified white and
//     * black points to the full available quantum range. Uses a midpoint of 1.0.
//     *
//     * @param blackPoint The darkest color in the image. Colors darker are set to zero.
//     * @param whitePoint The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun level(blackPoint: TQuantumType, whitePoint: TQuantumType)
//
//    /**
//     * Adjust the levels of the image by scaling the colors falling between specified white and
//     * black points to the full available quantum range. Uses a midpoint of 1.0.
//     *
//     * @param blackPoint The darkest color in the image. Colors darker are set to zero.
//     * @param whitePoint The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @param channels The channel(s) to level.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun level(blackPoint: TQuantumType, whitePoint: TQuantumType, channels: Channels)
//
//    /**
//     * Adjust the levels of the image by scaling the colors falling between specified white and
//     * black points to the full available quantum range.
//     *
//     * @param blackPoint The darkest color in the image. Colors darker are set to zero.
//     * @param whitePoint The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @param gamma The gamma correction to apply to the image. (Useful range of 0 to 10).
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun level(blackPoint: TQuantumType, whitePoint: TQuantumType, gamma: Double)
//
//    /**
//     * Adjust the levels of the image by scaling the colors falling between specified white and
//     * black points to the full available quantum range.
//     *
//     * @param blackPoint The darkest color in the image. Colors darker are set to zero.
//     * @param whitePoint The lightest color in the image. Colors brighter are set to the maximum quantum value.
//     * @param gamma The gamma correction to apply to the image. (Useful range of 0 to 10).
//     * @param channels The channel(s) to level.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun level(blackPoint: TQuantumType, whitePoint: TQuantumType, gamma: Double, channels: Channels)
//
//    /**
//     * Maps the given color to "black" and "white" values, linearly spreading out the colors, and
//     * level values on a channel by channel bases, as per level(). The given colors allows you to
//     * specify different level ranges for each of the color channels separately.
//     *
//     * @param blackColor The color to map black to/from.
//     * @param whiteColor The color to map white to/from.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun levelColors(blackColor: IMagickColorQuantum<TQuantumType>, whiteColor: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Maps the given color to "black" and "white" values, linearly spreading out the colors, and
//     * level values on a channel by channel bases, as per level(). The given colors allows you to
//     * specify different level ranges for each of the color channels separately.
//     *
//     * @param blackColor The color to map black to/from.
//     * @param whiteColor The color to map white to/from.
//     * @param channels The channel(s) to level.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun levelColors(blackColor: IMagickColorQuantum<TQuantumType>, whiteColor: IMagickColorQuantum<TQuantumType>, channels: Channels)
//
//    /**
//     * Remap image colors with closest color from the specified colors.
//     *
//     * @param colors The colors to use.
//     * @return The error informaton.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun map(colors: IEnumerable<IMagickColorQuantum<TQuantumType>>): IMagickErrorInfo
//
//    /**
//     * Remap image colors with closest color from the specified colors.
//     *
//     * @param colors The colors to use.
//     * @param settings Quantize settings.
//     * @return The error informaton.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun map(colors: IEnumerable<IMagickColorQuantum<TQuantumType>>, settings: IQuantizeSettings): IMagickErrorInfo
//
//    /**
//     * Changes any pixel that matches target with the color defined by fill.
//     *
//     * @param target The color to replace.
//     * @param fill The color to replace opaque color with.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun opaque(target: IMagickColorQuantum<TQuantumType>, fill: IMagickColorQuantum<TQuantumType>)
//

    /**
     * Reads only metadata and not the pixel data.
     *
     * @param data The byte array to read the image data from.
     * @param offset The offset at which to begin reading data.
     * @param count The maximum number of bytes to read.
     * @param readSettings The settings to use when reading the image.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     * @throws IllegalArgumentException Thrown when offset or count
     */
    @Throws(MagickException::class, IllegalArgumentException::class)
    public fun ping(
        data: UByteArray,
        offset: UInt,
        count: UInt,
        readSettings: IMagickReadSettings<TQuantumType>?,
    )

    /**
     * Reads only metadata and not the pixel data.
     *
     * @param data The byte array to read the information from.
     * @param readSettings The settings to use when reading the image.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun ping(
        data: UByteArray,
        readSettings: IMagickReadSettings<TQuantumType>?,
    )

    /**
     * Reads only metadata and not the pixel data.
     *
     * @param file The file to read the image from.
     * @param readSettings The settings to use when reading the image.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun ping(
        file: Path,
        readSettings: IMagickReadSettings<TQuantumType>?,
    )

    /**
     * Reads only metadata and not the pixel data.
     *
     * @param stream The stream to read the image data from.
     * @param readSettings The settings to use when reading the image.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun ping(
        stream: Source,
        readSettings: IMagickReadSettings<TQuantumType>?,
    )

    /**
     * Reads only metadata and not the pixel data.
     *
     * @param fileName The fully qualified name of the image file, or the relative image file name.
     * @param readSettings The settings to use when reading the image.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun ping(
        fileName: String,
        readSettings: IMagickReadSettings<TQuantumType>?,
    )
//
//    /**
//     * Changes the value of individual pixels based on the intensity of each pixel compared to a
//     * random threshold. The result is a low-contrast, two color image.
//     *
//     * @param low The low threshold.
//     * @param high The high threshold.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun randomThreshold(low: TQuantumType, high: TQuantumType)
//
//    /**
//     * Changes the value of individual pixels based on the intensity of each pixel compared to a
//     * random threshold. The result is a low-contrast, two color image.
//     *
//     * @param low The low threshold.
//     * @param high The high threshold.
//     * @param channels The channel(s) to use.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun randomThreshold(low: TQuantumType, high: TQuantumType, channels: Channels)
//
//    /**
//     * Applies soft and hard thresholding.
//     *
//     * @param lowBlack Defines the minimum black threshold value.
//     * @param lowWhite Defines the minimum white threshold value.
//     * @param highWhite Defines the maximum white threshold value.
//     * @param highBlack Defines the maximum black threshold value.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun rangeThreshold(lowBlack: TQuantumType, lowWhite: TQuantumType, highWhite: TQuantumType, highBlack: TQuantumType)
//

    /**
     * Read single image frame.
     *
     * @param data The byte array to read the image data from.
     * @param offset The offset at which to begin reading data.
     * @param count The maximum number of bytes to read.
     * @param readSettings The settings to use when reading the image.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun read(
        data: UByteArray,
        offset: UInt,
        count: UInt,
        readSettings: IMagickReadSettings<TQuantumType>?,
    )

    /**
     * Read single image frame.
     *
     * @param data The byte array to read the image data from.
     * @param readSettings The settings to use when reading the image.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     * @throws IllegalArgumentException Thrown when [data] is empty
     */
    @Throws(MagickException::class, IllegalArgumentException::class)
    public fun read(
        data: UByteArray,
        readSettings: IMagickReadSettings<TQuantumType>?,
    )

    /**
     * Read single image frame.
     *
     * @param file The file to read the image from.
     * @param readSettings The settings to use when reading the image.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun read(
        file: Path,
        readSettings: IMagickReadSettings<TQuantumType>?,
    )

    /**
     * Read single image frame.
     *
     * @param color The color to fill the image with.
     * @param width The width.
     * @param height The height.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun read(
        color: IMagickColorQuantum<TQuantumType>,
        width: UInt,
        height: UInt,
    )

    /**
     * Read single image frame.
     *
     * @param stream The stream to read the image data from.
     * @param readSettings The settings to use when reading the image.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun read(
        stream: Source,
        readSettings: IMagickReadSettings<TQuantumType>?,
    )

    /**
     * Read single image frame.
     *
     * @param fileName The fully qualified name of the image file, or the relative image file name.
     * @param readSettings The settings to use when reading the image.
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    public fun read(
        fileName: String,
        readSettings: IMagickReadSettings<TQuantumType>?,
    )

//    /**
//     * Read single image frame.
//     *
//     * @param file The file to read the image from.
//     * @param readSettings The settings to use when reading the image.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun readAsync(file: Path, readSettings: IMagickReadSettings<TQuantumType>?): Task
//
//    /**
//     * Read single image frame.
//     *
//     * @param file The file to read the image from.
//     * @param readSettings The settings to use when reading the image.
//     * @return A [Task] representing the asynchronous operation.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun readAsync(file: Path, readSettings: IMagickReadSettings<TQuantumType>?, cancellationToken: CancellationToken): Task
//
//    /**
//     * Read single image frame.
//     *
//     * @param stream The stream to read the image data from.
//     * @param readSettings The settings to use when reading the image.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun readAsync(stream: Source, readSettings: IMagickReadSettings<TQuantumType>?): Task
//
//    /**
//     * Read single image frame.
//     *
//     * @param stream The stream to read the image data from.
//     * @param readSettings The settings to use when reading the image.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun readAsync(stream: Source, readSettings: IMagickReadSettings<TQuantumType>?, cancellationToken: CancellationToken): Task
//
//    /**
//     * Read single image frame.
//     *
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param readSettings The settings to use when reading the image.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun readAsync(fileName: String, readSettings: IMagickReadSettings<TQuantumType>?): Task
//
//    /**
//     * Read single image frame.
//     *
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param readSettings The settings to use when reading the image.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun readAsync(fileName: String, readSettings: IMagickReadSettings<TQuantumType>?, cancellationToken: CancellationToken): Task

//    /**
//     * Read single image frame from pixel data.
//     *
//     * @param data The byte array to read the image data from.
//     * @param settings The pixel settings to use when reading the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun readPixels(data: UByteArray, settings: IPixelReadSettings<TQuantumType>)
//
//    /**
//     * Read single image frame from pixel data.
//     *
//     * @param data The byte array to read the image data from.
//     * @param offset The offset at which to begin reading data.
//     * @param count The maximum number of bytes to read.
//     * @param settings The pixel settings to use when reading the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun readPixels(data: UByteArray, offset: Int, count: Int, settings: IPixelReadSettings<TQuantumType>)

//    #if !Q8
//
//    /**
//     * Read single image frame.
//     *
//     * @param data The quantum array to read the image data from.
//     * @param settings The pixel settings to use when reading the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun readPixels(data: TQuantumType[], settings: IPixelReadSettings<TQuantumType>)
//
//    /**
//     * Read single image frame from pixel data.
//     *
//     * @param data The quantum array to read the image data from.
//     * @param offset The offset at which to begin reading data.
//     * @param count The maximum number of items to read.
//     * @param settings The pixel settings to use when reading the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun readPixels(data: TQuantumType[], offset: Int, count: Int, settings: IPixelReadSettings<TQuantumType>)
//    #endif

//    /**
//     * Read single image frame from pixel data.
//     *
//     * @param file The file to read the image from.
//     * @param settings The pixel settings to use when reading the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun readPixels(file: Path, settings: IPixelReadSettings<TQuantumType>)
//
//    /**
//     * Read single image frame from pixel data.
//     *
//     * @param stream The stream to read the image data from.
//     * @param settings The pixel settings to use when reading the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun readPixels(stream: Source, settings: IPixelReadSettings<TQuantumType>)
//
//    /**
//     * Read single image frame from pixel data.
//     *
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param settings The pixel settings to use when reading the image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun readPixels(fileName: String, settings: IPixelReadSettings<TQuantumType>)

//    /**
//     * Read single image frame from pixel data.
//     *
//     * @param file The file to read the image from.
//     * @param settings The pixel settings to use when reading the image.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun readPixelsAsync(file: Path, settings: IPixelReadSettings<TQuantumType>): Task
//
//    /**
//     * Read single image frame from pixel data.
//     *
//     * @param file The file to read the image from.
//     * @param settings The pixel settings to use when reading the image.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun readPixelsAsync(file: Path, settings: IPixelReadSettings<TQuantumType>, cancellationToken: CancellationToken): Task
//
//    /**
//     * Read single image frame from pixel data.
//     *
//     * @param stream The stream to read the image data from.
//     * @param settings The pixel settings to use when reading the image.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun readPixelsAsync(stream: Source, settings: IPixelReadSettings<TQuantumType>): Task
//
//    /**
//     * Read single image frame from pixel data.
//     *
//     * @param stream The stream to read the image data from.
//     * @param settings The pixel settings to use when reading the image.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun readPixelsAsync(stream: Source, settings: IPixelReadSettings<TQuantumType>, cancellationToken: CancellationToken): Task
//
//    /**
//     * Read single image frame from pixel data.
//     *
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param settings The pixel settings to use when reading the image.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun readPixelsAsync(fileName: String, settings: IPixelReadSettings<TQuantumType>): Task
//
//    /**
//     * Read single image frame from pixel data.
//     *
//     * @param fileName The fully qualified name of the image file, or the relative image file name.
//     * @param settings The pixel settings to use when reading the image.
//     * @param cancellationToken The token to monitor for cancellation requests.
//     * @return A [Task] representing the asynchronous operation.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun readPixelsAsync(fileName: String, settings: IPixelReadSettings<TQuantumType>, cancellationToken: CancellationToken): Task

//    /**
//     * Separates the channels from the image and returns it as grayscale images.
//     *
//     * @return The channels from the image as grayscale images.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IReadOnlyCollection<MagickImageQuantum<TQuantumType>> Separate();
//
//    /**
//     * Separates the specified channels from the image and returns it as grayscale images.
//     *
//     * @param channels The channel(s) to separates.
//     * @return The channels from the image as grayscale images.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IReadOnlyCollection<MagickImageQuantum<TQuantumType>> Separate(Channels channels);
//
//    /**
//     * Set color at colormap position index.
//     *
//     * @param index The position index.
//     * @param color The color.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun setColormapColor(index: Int, color: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Simulate an image shadow.
//     *
//     * @param color The color of the shadow.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun shadow(color: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Simulate an image shadow.
//     *
//     * @param x  the shadow x-offset.
//     * @param y the shadow y-offset.
//     * @param sigma The standard deviation of the Gaussian, in pixels.
//     * @param alpha Transparency percentage.
//     * @param color The color of the shadow.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun shadow(x: Int, y: Int, sigma: Double, alpha: Percentage, color: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Sparse color image, given a set of coordinates, interpolates the colors found at those
//     * coordinates, across the whole image, using various methods.
//     *
//     * @param method The sparse color method to use.
//     * @param args The sparse color arguments.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sparseColor(method: SparseColorMethod, args: IEnumerable<ISparseColorArg<TQuantumType>>)
//
//    /**
//     * Sparse color image, given a set of coordinates, interpolates the colors found at those
//     * coordinates, across the whole image, using various methods.
//     *
//     * @param method The sparse color method to use.
//     * @param args The sparse color arguments.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sparseColor(method: SparseColorMethod, vararg args: ISparseColorArg<TQuantumType>)
//
//    /**
//     * Sparse color image, given a set of coordinates, interpolates the colors found at those
//     * coordinates, across the whole image, using various methods.
//     *
//     * @param channels The channel(s) to use.
//     * @param method The sparse color method to use.
//     * @param args The sparse color arguments.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sparseColor(channels: Channels, method: SparseColorMethod, args: IEnumerable<ISparseColorArg<TQuantumType>>)
//
//    /**
//     * Sparse color image, given a set of coordinates, interpolates the colors found at those
//     * coordinates, across the whole image, using various methods.
//     *
//     * @param channels The channel(s) to use.
//     * @param method The sparse color method to use.
//     * @param args The sparse color arguments.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun sparseColor(channels: Channels, method: SparseColorMethod, vararg args: ISparseColorArg<TQuantumType>)
//
//    /**
//     * Search for the specified image at EVERY possible location in this image. This is slow!
//     * very very slow.. It returns a similarity image such that an exact match location is
//     * completely white and if none of the pixels match, black, otherwise some gray level in-between.
//     *
//     * @param image The image to search for.
//     * @return The result of the search action.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IMagickSearchResult<TQuantumType> SubImageSearch(MagickImageQuantum<TQuantumType> image);
//
//    /**
//     * Search for the specified image at EVERY possible location in this image. This is slow!
//     * very very slow.. It returns a similarity image such that an exact match location is
//     * completely white and if none of the pixels match, black, otherwise some gray level in-between.
//     *
//     * @param image The image to search for.
//     * @param metric The metric to use.
//     * @return The result of the search action.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IMagickSearchResult<TQuantumType> SubImageSearch(MagickImageQuantum<TQuantumType> image, ErrorMetric metric);
//
//    /**
//     * Search for the specified image at EVERY possible location in this image. This is slow!
//     * very very slow.. It returns a similarity image such that an exact match location is
//     * completely white and if none of the pixels match, black, otherwise some gray level in-between.
//     *
//     * @param image The image to search for.
//     * @param metric The metric to use.
//     * @param similarityThreshold Minimum distortion for (sub)image match.
//     * @return The result of the search action.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    IMagickSearchResult<TQuantumType> SubImageSearch(MagickImageQuantum<TQuantumType> image, ErrorMetric metric, double similarityThreshold);
//
//    /**
//     * Applies a color vector to each pixel in the image. The length of the vector is 0 for black
//     * and white and at its maximum for the midtones. The vector weighting function is
//     * f(x)=(1-(4.0*((x-0.5)*(x-0.5)))).
//     *
//     * @param opacity An opacity value used for tinting.
//     * @param color A color value used for tinting.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun tint(opacity: IMagickGeometry, color: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Add alpha channel to image, setting pixels matching color to transparent.
//     *
//     * @param color The color to make transparent.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun transparent(color: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Add alpha channel to image, setting pixels that lie in between the given two colors to
//     * transparent.
//     *
//     * @param colorLow The low target color.
//     * @param colorHigh The high target color.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    public fun transparentChroma(colorLow: IMagickColorQuantum<TQuantumType>, colorHigh: IMagickColorQuantum<TQuantumType>)
//
//    /**
//     * Returns the unique colors of an image.
//     *
//     * @return The unique colors of an image.
//     * @throws MagickException Thrown when an error is raised by ImageMagick.
//     */
//    @Throws(MagickException::class)
//    MagickImageQuantum<TQuantumType>? UniqueColors();
//
//    /**
//     * Removes noise from the image using a wavelet transform.
//     *
//     * @param threshold The threshold for smoothing.
//     */
//    public fun waveletDenoise(threshold: TQuantumType)
//
//    /**
//     * Removes noise from the image using a wavelet transform.
//     *
//     * @param threshold The threshold for smoothing.
//     * @param softness Attenuate the smoothing threshold.
//     */
//    public fun waveletDenoise(threshold: TQuantumType, softness: Double)
}
