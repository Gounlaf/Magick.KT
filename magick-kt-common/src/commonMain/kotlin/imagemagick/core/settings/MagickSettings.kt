package imagemagick.core.settings

import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.ColorType
import imagemagick.core.enums.CompressionMethod
import imagemagick.core.enums.Endian
import imagemagick.core.enums.MagickFormat
import imagemagick.core.types.Density

/**
 * Class that contains various settings.
 */
interface MagickSettings<T> where T : Any {
//    /** Gets or sets the affine to use when annotating with text or drawing. */
//    IDrawableAffine? Affine { get; set; }
//
    /** Gets or sets a value indicating whether anti-aliasing should be enabled (default true). */
    var antiAlias: Boolean

    //    /** Gets or sets the background color. */
//    IMagickColor<TQuantumType>? BackgroundColor { get; set; }
//
//    /** Gets or sets the border color. */
//    IMagickColor<TQuantumType>? BorderColor { get; set; }
//
    /* Gets or sets the color space.*/
    var colorSpace: ColorSpace

    /** Gets or sets the color type of the image. */
    var colorType: ColorType

    /** Gets or sets the compression method to use. */
    var compression: CompressionMethod

    /**
     * Gets or sets a value indicating whether printing of debug messages from ImageMagick is enabled
     * when a debuggeris attached.
     */
    var debug: Boolean

    /** Gets or sets the vertical and horizontal resolution in pixels. */
    var density: Density?

    /** Gets or sets the depth (bits allocated to red/green/blue components). */
    var depth: UInt

    /**
     * Gets or sets the endianness (little like Intel or big like SPARC) for image formats which
     * support endian-specific options.
     */
    var endian: Endian
//
//    /** Gets or sets the fill color. */
//    IMagickColor<TQuantumType>? FillColor { get; set; }
//
//    /** Gets or sets the fill pattern. */
//    IMagickImage<TQuantumType>? FillPattern { get; set; }
//
//    /** Gets or sets the rule to use when filling drawn objects. */
//    FillRule FillRule { get; set; }
//
//    /** Gets or sets the text rendering font. */
//    string? Font { get; set; }
//
//    /** Gets or sets the text font family. */
//    string? FontFamily { get; set; }
//
//    /** Gets or sets the font point size. */
//    double FontPointsize { get; set; }
//
//    /** Gets or sets the font style. */
//    FontStyleType FontStyle { get; set; }
//
//    /** Gets or sets the font weight. */
//    FontWeight FontWeight { get; set; }
//
    /** Gets or sets the the format of the image. */
    var format: MagickFormat
//
//    /** Gets or sets the preferred size and location of an image canvas. */
//    IMagickGeometry? Page { get; set; }
//
//    /** Gets or sets a value indicating whether stroke anti-aliasing is enabled or disabled. */
//    bool StrokeAntiAlias { get; set; }
//
//    /** Gets or sets the color to use when drawing object outlines. */
//    IMagickColor<TQuantumType>? StrokeColor { get; set; }
//
//    /// <summary>
//    /// Gets or sets the pattern of dashes and gaps used to stroke paths. This represents a
//    /// zero-terminated array of numbers that specify the lengths of alternating dashes and gaps
//    /// in pixels. If a zero value is not found it will be added. If an odd number of values is
//    /// provided, then the list of values is repeated to yield an even number of values.
//    /// </summary>
//    IEnumerable<double>? StrokeDashArray { get; set; }
//
//    /// <summary>
//    /// Gets or sets the distance into the dash pattern to start the dash (default 0) while
//    /// drawing using a dash pattern,.
//    /// </summary>
//    double StrokeDashOffset { get; set; }
//
//    /** Gets or sets the shape to be used at the end of open subpaths when they are stroked. */
//    LineCap StrokeLineCap { get; set; }
//
//    /// <summary>
//    /// Gets or sets the shape to be used at the corners of paths (or other vector shapes) when they
//    /// are stroked.
//    /// </summary>
//    LineJoin StrokeLineJoin { get; set; }
//
//    /// <summary>
//    /// Gets or sets the miter limit. When two line segments meet at a sharp angle and miter joins have
//    /// been specified for 'lineJoin', it is possible for the miter to extend far beyond the thickness
//    /// of the line stroking the path. The miterLimit' imposes a limit on the ratio of the miter
//    /// length to the 'lineWidth'. The default value is 4.
//    /// </summary>
//    int StrokeMiterLimit { get; set; }
//
//    /** Gets or sets the pattern image to use while stroking object outlines. */
//    IMagickImage<TQuantumType>? StrokePattern { get; set; }
//
//    /** Gets or sets the stroke width for drawing lines, circles, ellipses, etc. */
//    double StrokeWidth { get; set; }
//
//    /** Gets or sets a value indicating whether Postscript and TrueType fonts should be anti-aliased (default true). */
//    bool TextAntiAlias { get; set; }
//
//    /** Gets or sets text direction (right-to-left or left-to-right). */
//    TextDirection TextDirection { get; set; }
//
//    /** Gets or sets the text annotation encoding (e.g. "UTF-16"). */
//    Encoding? TextEncoding { get; set; }
//
//    /** Gets or sets the text annotation gravity. */
//    Gravity TextGravity { get; set; }
//
//    /** Gets or sets the text inter-line spacing. */
//    double TextInterlineSpacing { get; set; }
//
//    /** Gets or sets the text inter-word spacing. */
//    double TextInterwordSpacing { get; set; }
//
//    /** Gets or sets the text inter-character kerning. */
//    double TextKerning { get; set; }
//
//    /** Gets or sets the text undercolor box. */
//    IMagickColor<TQuantumType>? TextUnderColor { get; set; }

    /** Gets or sets a value indicating whether verbose output os turned on or off. */
    var verbose: Boolean

//    /** Returns the value of a format-specific option. */
//    /// <param name="format">The format to get the option for.</param>
//    /// <param name="name">The name of the option.</param>
//    /// <returns>The value of a format-specific option.</returns>
//    string? GetDefine(MagickFormat format, string name);
//
//    /** Returns the value of a format-specific option. */
//    /// <param name="name">The name of the option.</param>
//    /// <returns>The value of a format-specific option.</returns>
//    string? GetDefine(string name);
//
//    /** Removes the define with the specified name. */
//    /// <param name="format">The format to set the define for.</param>
//    /// <param name="name">The name of the define.</param>
//    void RemoveDefine(MagickFormat format, string name);
//
//    /** Removes the define with the specified name. */
//    /// <param name="name">The name of the define.</param>
//    void RemoveDefine(string name);
//
//    /** Sets a format-specific option. */
//    /// <param name="format">The format to set the define for.</param>
//    /// <param name="name">The name of the define.</param>
//    /// <param name="flag">The value of the define.</param>
//    void SetDefine(MagickFormat format, string name, bool flag);
//
//    /** Sets a format-specific option. */
//    /// <param name="format">The format to set the option for.</param>
//    /// <param name="name">The name of the option.</param>
//    /// <param name="value">The value of the option.</param>
//    void SetDefine(MagickFormat format, string name, int value);
//
//    /** Sets a format-specific option. */
//    /// <param name="format">The format to set the option for.</param>
//    /// <param name="name">The name of the option.</param>
//    /// <param name="value">The value of the option.</param>
//    void SetDefine(MagickFormat format, string name, string value);
//
//    /** Sets a format-specific option. */
//    /// <param name="name">The name of the option.</param>
//    /// <param name="value">The value of the option.</param>
//    void SetDefine(string name, string value);
//
//    /** Sets format-specific options with the specified defines. */
//    /// <param name="defines">The defines to set.</param>
//    void SetDefines(IDefines defines);
}
