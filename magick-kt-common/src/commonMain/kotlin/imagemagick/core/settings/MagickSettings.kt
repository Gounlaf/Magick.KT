package imagemagick.core.settings

import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.ColorType
import imagemagick.core.enums.CompressionMethod
import imagemagick.core.enums.Endian
import imagemagick.core.enums.FillRule
import imagemagick.core.enums.FontStyleType
import imagemagick.core.enums.FontWeight
import imagemagick.core.enums.Gravity
import imagemagick.core.enums.Interlace
import imagemagick.core.enums.LineCap
import imagemagick.core.enums.LineJoin
import imagemagick.core.enums.MagickFormat
import imagemagick.core.enums.TextDirection
import imagemagick.core.types.Density
import imagemagick.core.types.MagickGeometry
import imagemagick.core.MagickImageQuantum as IMagickImage
import imagemagick.core.colors.MagickColorQuantum as IMagickColor
import imagemagick.core.defines.Defines as IDefines
import imagemagick.core.drawables.DrawableAffine as IDrawableAffine

/**
 * Class that contains various settings.
 */
public interface MagickSettings<TQuantumType> where TQuantumType : Any {
    /**
     * Gets or sets the affine to use when annotating with text or drawing.
     */
    public var affine: IDrawableAffine?

    /**
     * Gets or sets a value indicating whether anti-aliasing should be enabled (default true).
     */
    public var antiAlias: Boolean

    /**
     * Gets or sets the background color.
     */
    public var backgroundColor: IMagickColor<TQuantumType>?

    /**
     * Gets or sets the border color.
     */
    public var borderColor: IMagickColor<TQuantumType>?

    /**
     * Gets or sets the color space.
     */
    public var colorSpace: ColorSpace

    /**
     * Gets or sets the color type of the image.
     */
    public var colorType: ColorType

    /**
     * Gets or sets the compression method to use.
     */
    public var compression: CompressionMethod

    /**
     * Gets or sets a value indicating whether printing of debug messages from ImageMagick is enabled when a debugger is attached.
     */
    public var debug: Boolean

    /**
     * Gets or sets the vertical and horizontal resolution in pixels.
     */
    public var density: Density?

    /**
     * Gets or sets the depth (bits allocated to red/green/blue components).
     */
    public var depth: UInt

    /**
     * Gets or sets the endianness (little like Intel or big like SPARC) for image formats which support
     * endian-specific options.
     *
     */
    public var endian: Endian

    /**
     * Gets or sets the fill color.
     */
    public var fillColor: IMagickColor<TQuantumType>?

    /**
     * Gets or sets the fill pattern.
     */
    public var fillPattern: IMagickImage<TQuantumType>?

    /**
     * Gets or sets the rule to use when filling drawn objects.
     */
    public var fillRule: FillRule

    /**
     * Gets or sets the text rendering font.
     */
    public var font: String?

    /**
     * Gets or sets the text font family.
     */
    public var fontFamily: String?

    /**
     * Gets or sets the font point size.
     */
    public var fontPointsize: Double

    /**
     * Gets or sets the font style.
     */
    public var fontStyle: FontStyleType

    /**
     * Gets or sets the font weight.
     */
    public var fontWeight: FontWeight

    /**
     * Gets or sets the the format of the image.
     */
    public var format: MagickFormat

    /**
     * Gets or sets the interlace method.
     */
    public var interlace: Interlace

    /**
     * Gets or sets the preferred size and location of an image canvas.
     */
    public var page: MagickGeometry?

    /**
     * Gets or sets a value indicating whether stroke anti-aliasing is enabled or disabled.
     */
    public var strokeAntiAlias: Boolean

    /**
     * Gets or sets the color to use when drawing object outlines.
     */
    public var strokeColor: IMagickColor<TQuantumType>?

    /**
     * Gets or sets the pattern of dashes and gaps used to stroke paths. This represents a
     * zero-terminated array of numbers that specify the lengths of alternating dashes and gaps
     * in pixels. If a zero value is not found it will be added. If an odd number of values is
     * provided, then the list of values is repeated to yield an even number of values.
     */
    public var strokeDashArray: Iterable<Double>?

    /**
     * Gets or sets the distance into the dash pattern to start the dash (default 0) while
     * drawing using a dash pattern.
     */
    public var strokeDashOffset: Double

    /**
     * Gets or sets the shape to be used at the end of open subpaths when they are stroked.
     */
    public var strokeLineCap: LineCap

    /**
     * Gets or sets the shape to be used at the corners of paths (or other vector shapes) when they
     * are stroked.
     */
    public var strokeLineJoin: LineJoin

    /**
     * Gets or sets the miter limit. When two line segments meet at a sharp angle and miter joins have
     * been specified for 'lineJoin', it is possible for the miter to extend far beyond the thickness
     * of the line stroking the path. The miterLimit' imposes a limit on the ratio of the miter
     * length to the 'lineWidth'. The default value is 4.
     */
    public var strokeMiterLimit: UInt

    /**
     * Gets or sets the pattern image to use while stroking object outlines.
     */
    public var strokePattern: IMagickImage<TQuantumType>?

    /**
     * Gets or sets the stroke width for drawing lines, circles, ellipses, etc.
     */
    public var strokeWidth: Double

    /**
     * Gets or sets a value indicating whether Postscript and TrueType fonts should be anti-aliased (default true).
     */
    public var textAntiAlias: Boolean

    /**
     * Gets or sets text direction (right-to-left or left-to-right).
     */
    public var textDirection: TextDirection

    /**
     * Gets or sets the text annotation encoding (e.g. "UTF-16").
     */
    public var textEncoding: String?
    // TODO Create Encoding equivalent

    /**
     * Gets or sets the text annotation gravity.
     */
    public var textGravity: Gravity

    /**
     * Gets or sets the text inter-line spacing.
     */
    public var textInterlineSpacing: Double

    /**
     * Gets or sets the text inter-word spacing.
     */
    public var textInterwordSpacing: Double

    /**
     * Gets or sets the text inter-character kerning.
     */
    public var textKerning: Double

    /**
     * Gets or sets the text undercolor box.
     */
    public var textUnderColor: IMagickColor<TQuantumType>?

    /**
     * Gets or sets a value indicating whether verbose output os turned on or off.
     */
    public var verbose: Boolean

    /**
     * Returns the value of a format-specific option.
     *
     * @param format The format to get the option for.
     * @param name The name of the option.
     *
     * @return The value of a format-specific option.
     */
    public fun getDefine(
        format: MagickFormat,
        name: String,
    ): String?

    /**
     * Returns the value of a format-specific option.
     *
     * @param name The name of the option.
     *
     * @return The value of a format-specific option.
     */
    public fun getDefine(name: String): String?

    /**
     * Removes the define with the specified name.
     *
     * @param format The format to set the define for.
     * @param name The name of the define.
     */
    public fun removeDefine(
        format: MagickFormat,
        name: String,
    )

    /**
     * Removes the define with the specified name.
     *
     * @param name The name of the define.
     */
    public fun removeDefine(name: String)

    /**
     * Sets a format-specific option.
     *
     * @param format The format to set the define for.
     * @param name The name of the define.
     * @param flag The value of the define.
     */
    public fun setDefine(
        format: MagickFormat,
        name: String,
        flag: Boolean,
    )

    /**
     * Sets a format-specific option.
     *
     * @param format The format to set the option for.
     * @param name The name of the option.
     * @param value The value of the option.
     */
    public fun setDefine(
        format: MagickFormat,
        name: String,
        value: Int,
    )

    /**
     * Sets a format-specific option.
     *
     * @param format The format to set the option for.
     * @param name The name of the option.
     * @param value The value of the option.
     */
    public fun setDefine(
        format: MagickFormat,
        name: String,
        value: String,
    )

    /**
     * Sets a format-specific option.
     *
     * @param name The name of the option.
     * @param value The value of the option.
     */
    public fun setDefine(
        name: String,
        value: String,
    )

    /**
     * Sets format-specific options with the specified defines.
     *
     * @param defines The defines to set.
     */
    public fun setDefines(defines: IDefines)
}
