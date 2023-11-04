package imagemagick.core.types

import imagemagick.enums.GeometryFlags
import imagemagick.exceptions.Throw
import imagemagick.native.NativeMagickGeometry
import kotlinx.cinterop.ExperimentalForeignApi

//
// /!\ Contrary to Magick.NET impl, this one doesn't use interface (yet?)
//

/**
 * Encapsulation of the ImageMagick geometry object.
 *
 * @constructor Initializes a new instance of the [MagickGeometry] class.
 */
@ExperimentalStdlibApi
@ExperimentalForeignApi
data class MagickGeometry(
    /** Gets a value indicating whether the value is an aspect ratio. */
    var aspectRatio: Boolean = false,
    /** Gets or sets a value indicating whether the image is resized based on the smallest fitting dimension (^). */
    var fillArea: Boolean = false,
    /** Gets or sets a value indicating whether the image is resized if image is greater than size (&gt;). */
    var greater: Boolean = false,
    /**Gets or sets the height of the geometry.*/
    var height: UInt = 0u,
    /** Gets or sets a value indicating whether the image is resized without preserving aspect ratio (!). */
    var ignoreAspectRatio: Boolean = false,
    /** Gets or sets a value indicating whether the width and height are expressed as percentages. */
    var isPercentage: Boolean = false,
    /** Gets or sets a value indicating whether the image is resized if the image is less than size (&lt;). */
    var less: Boolean = false,
    /** Gets or sets the width of the geometry. */
    var width: UInt = 0u,
    /** Gets or sets a value indicating whether the image is resized using a pixel area count limit (@). */
    var limitPixels: Boolean = false,
    /** Gets or sets the X offset from origin. */
    var x: Int = 0,
    /** Gets or sets the Y offset from origin. */
    var y: Int = 0
) : Comparable<MagickGeometry?> {
    companion object {
        fun String.toMagickGeometry(): MagickGeometry = MagickGeometry()

        fun fromRectangle(rectangle: MagickRectangle): MagickGeometry =
            MagickGeometry(rectangle.x, rectangle.y, rectangle.width, rectangle.height)

        fun fromString(value: String?): MagickGeometry? = value?.let {
            MagickGeometry(it)
        }

        fun fromPageSize(pageSize: String) {
//            MagickRectangle.f
        }

        private fun parseInt(value: String): UInt {
            // substring in Kotlin have a different API than C#

            val start = value.indexOfFirst { c -> c.isDigit() }
            var end = start

            while (end < value.length && value[end].isDigit())
                end++

            return value.substring(start, end).toUInt()
        }
    }

    /**
     * Initializes a new instance of the [MagickGeometry] class using the specified width and height.
     *
     * @param widthAndHeight The width and height
     */
    constructor(widthAndHeight: UInt) : this(0, 0, widthAndHeight, widthAndHeight)

    /**
     * Initializes a new instance of the [MagickGeometry] class using the specified width and height.
     *
     * @param width The width.
     * @param height The height.
     */
    constructor(width: UInt, height: UInt) : this(0, 0, width, height)

    /**
     * Initializes a new instance of the [MagickGeometry] class using the specified offsets, width and height.
     *
     * @param x The X offset from origin.
     * @param y The Y offset from origin.
     * @param width The width.
     * @param height The height.
     */
    constructor(x: Int, y: Int, width: UInt, height: UInt) : this() {
        initialize(x, y, width, height)
    }

    /**
     * Initializes a new instance of the [MagickGeometry] class using the specified width and height.
     *
     * @param percentageWith The percentage of the width.
     * @param percentageHeight The percentage of the height.
     */
    constructor(percentageWith: Percentage, percentageHeight: Percentage) : this(0, 0, percentageWith, percentageHeight)

    /**
     * Initializes a new instance of the [MagickGeometry] class using the specified offsets, width and height.
     *
     * @param x The X offset from origin.
     * @param y The Y offset from origin.
     * @param percentageWith The percentage of the width.
     * @param percentageHeight The percentage of the height.
     */
    constructor(x: Int, y: Int, percentageWith: Percentage, percentageHeight: Percentage) : this() {
        Throw.ifNegative("percentageWith", percentageWith)
        Throw.ifNegative("percentageHeight", percentageHeight)

        initializeFromPercentage(x, y, percentageWith.toUInt(), percentageHeight.toUInt())
    }

    /**
     * Initializes a new instance of the [MagickGeometry] class using the specified geometry.
     *
     * @param value Geometry specifications in the form:
     *  &lt;width&gt;x&lt;height&gt;{+-}&lt;xoffset&gt;{+-}&lt;yoffset&gt;
     *  (where width, height, xoffset, and yoffset are numbers).
     */
    constructor(value: String) : this() {
        Throw.ifEmpty(value)

        NativeMagickGeometry().use {
            val flags = it.initialize(value)

            if (!flags.contains(GeometryFlags.ASPECT_RATIO)) {
                initialize(it, flags)
            } else {
                initializeFromAspectRation(it, value)
            }
        }
    }

    /**
     * Initializes the geometry using the specified value.
     *
     * @param x The X offset from origin.
     * @param y The Y offset from origin.
     * @param width The width.
     * @param height The height.
     */
    fun initialize(x: Int, y: Int, width: UInt, height: UInt) {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
    }

    override fun compareTo(other: MagickGeometry?): Int {
        if (null == other) {
            return 1
        }

        val left = width * height
        val right = other.width * other.height

        if (left == right) {
            return 0
        }

        return if (left < right) -1 else 1
    }

    override fun toString(): String {
        if (aspectRatio) return "$width:$height"

        var result = ""

        if (width > 0u) result += width

        if (height > 0u) {
            result += "x$height"
        } else if (!isPercentage) {
            result += "x"
        }

        if (x != 0 || y != 0) {
            if (x >= 0) result += '+'

            result += x

            if (y >= 0) result += '+'

            result += y
        }

        if (isPercentage) result += '%'
        if (ignoreAspectRatio) result += '!'
        if (greater) result += '>'
        if (less) result += '<'
        if (fillArea) result += '^'
        if (limitPixels) result += '@'

        return result
    }

    private fun initializeFromPercentage(x: Int, y: Int, width: UInt, height: UInt) {
        initialize(x, y, width, height)
        isPercentage = true
    }

    private fun initialize(instance: NativeMagickGeometry) {
        x = instance.x.toInt()
        y = instance.y.toInt()
        width = instance.width.toUInt()
        height = instance.height.toUInt()
    }

    private fun initialize(instance: NativeMagickGeometry, flags: List<GeometryFlags>) {
        if (flags.size == 1) {
            require(!flags.contains(GeometryFlags.NO_VALUE)) {
                "Invalid geometry specified."
            }
        }

        initialize(instance)

        isPercentage = flags.contains(GeometryFlags.PERCENT_VALUE)
        ignoreAspectRatio = flags.contains(GeometryFlags.IGNORE_ASPECT_RATIO)
        fillArea = flags.contains(GeometryFlags.FILL_AREA)
        greater = flags.contains(GeometryFlags.GREATER)
        less = flags.contains(GeometryFlags.LESS)
        limitPixels = flags.contains(GeometryFlags.LIMIT_PIXELS)
    }

    private fun initializeFromAspectRation(instance: NativeMagickGeometry, value: String) {
        aspectRatio = true

        val ratio = value.split(':', limit = 2)

        // /!\ The original CSharp implementation uses a custom "ParseInt" method for theses two values
        width = parseInt(ratio[0])
        height = parseInt(ratio[1])

        x = instance.x.toInt()
        y = instance.y.toInt()
    }
}
