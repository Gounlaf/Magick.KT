package imagemagick.types

import imagemagick.core.types.Percentage
import imagemagick.enums.GeometryFlags
import imagemagick.exceptions.throwIfEmpty
import imagemagick.exceptions.throwIfNegative
import imagemagick.magicknative.types.NativeMagickGeometry
import imagemagick.magicknative.types.NativeMagickRectangle
import kotlinx.cinterop.ExperimentalForeignApi
import imagemagick.core.types.MagickGeometry as IMagickGeometry

/**
 * Encapsulation of the ImageMagick geometry object.
 *
 * @constructor Initializes a new instance of the [MagickGeometry] class.
 */
@ExperimentalStdlibApi
@ExperimentalForeignApi
public data class MagickGeometry(
    override var aspectRatio: Boolean = false,
    override var fillArea: Boolean = false,
    override var greater: Boolean = false,
    override var height: UInt = 0u,
    override var ignoreAspectRatio: Boolean = false,
    override var isPercentage: Boolean = false,
    override var less: Boolean = false,
    override var width: UInt = 0u,
    override var limitPixels: Boolean = false,
    override var x: Int = 0,
    override var y: Int = 0,
) : IMagickGeometry {
    /**
     * Initializes a new instance of the [MagickGeometry] class using the specified width and height.
     *
     * @param widthAndHeight The width and height
     */
    public constructor(widthAndHeight: UInt) : this(0, 0, widthAndHeight, widthAndHeight)

    /**
     * Initializes a new instance of the [MagickGeometry] class using the specified width and height.
     *
     * @param width The width.
     * @param height The height.
     */
    public constructor(width: UInt, height: UInt) : this(0, 0, width, height)

    /**
     * Initializes a new instance of the [MagickGeometry] class using the specified offsets, width and height.
     *
     * @param x The X offset from origin.
     * @param y The Y offset from origin.
     * @param width The width.
     * @param height The height.
     */
    public constructor(x: Int, y: Int, width: UInt, height: UInt) : this() {
        initialize(x, y, width, height)
    }

    /**
     * Initializes a new instance of the [MagickGeometry] class using the specified width and height.
     *
     * @param percentageWith The percentage of the width.
     * @param percentageHeight The percentage of the height.
     */
    public constructor(percentageWith: Percentage, percentageHeight: Percentage) : this(
        0,
        0,
        percentageWith,
        percentageHeight
    )

    /**
     * Initializes a new instance of the [MagickGeometry] class using the specified offsets, width and height.
     *
     * @param x The X offset from origin.
     * @param y The Y offset from origin.
     * @param percentageWith The percentage of the width.
     * @param percentageHeight The percentage of the height.
     */
    public constructor(x: Int, y: Int, percentageWith: Percentage, percentageHeight: Percentage) : this() {
        throwIfNegative("percentageWith", percentageWith)
        throwIfNegative("percentageHeight", percentageHeight)

        initializeFromPercentage(x, y, percentageWith.toUInt(), percentageHeight.toUInt())
    }

    /**
     * Initializes a new instance of the [MagickGeometry] class using the specified geometry.
     *
     * @param value Geometry specifications in the form:
     *  &lt;width&gt;x&lt;height&gt;{+-}&lt;xoffset&gt;{+-}&lt;yoffset&gt;
     *  (where width, height, xoffset, and yoffset are numbers).
     */
    @ExperimentalStdlibApi
    public constructor(value: String) : this() {
        throwIfEmpty(value)

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
    override fun initialize(
        x: Int,
        y: Int,
        width: UInt,
        height: UInt,
    ) {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
    }

    override fun compareTo(other: IMagickGeometry?): Int {
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

    override fun toString(): String = buildString {
        if (aspectRatio) {
            append("$width:$height")
            return@buildString
        }

        if (width > 0u) append(width)

        if (height > 0u) {
            append("x$height")
        } else if (!isPercentage) {
            append('x')
        }

        if (x != 0 || y != 0) {
            if (x >= 0) append('+')

            append(x)

            if (y >= 0) append('+')

            append(y)
        }

        if (isPercentage) append('%')
        if (ignoreAspectRatio) append('!')
        if (greater) append('>')
        if (less) append('<')
        if (fillArea) append('^')
        if (limitPixels) append('@')
    }

    private fun initializeFromPercentage(
        x: Int,
        y: Int,
        width: UInt,
        height: UInt,
    ) {
        initialize(x, y, width, height)
        isPercentage = true
    }

    private fun initialize(instance: NativeMagickGeometry) {
        x = instance.x.toInt()
        y = instance.y.toInt()
        width = instance.width.toUInt()
        height = instance.height.toUInt()
    }

    private fun initialize(
        instance: NativeMagickGeometry,
        flags: List<GeometryFlags>,
    ) {
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

    private fun initializeFromAspectRation(
        instance: NativeMagickGeometry,
        value: String,
    ) {
        aspectRatio = true

        val ratio = value.split(':', limit = 2)

        // /!\ The original CSharp implementation uses a custom "ParseInt" method for theses two values
        width = parseInt(ratio[0])
        height = parseInt(ratio[1])

        x = instance.x.toInt()
        y = instance.y.toInt()
    }

    public companion object {
        public inline fun String.toMagickGeometry(): MagickGeometry? = fromString(this)

        public inline fun fromString(value: String?): MagickGeometry? =
            value?.let {
                MagickGeometry(it)
            }

        @Throws(IllegalStateException::class)
        public fun fromPageSize(pageSize: String): MagickGeometry {
            throwIfEmpty(pageSize)

            val rectangle = MagickRectangle.fromPageSize(pageSize) ?: error("Invalid page size specified.")

            return fromRectangle(rectangle)
        }

        internal fun clone(value: IMagickGeometry?): MagickGeometry? =
            value?.let {
                MagickGeometry().apply {
                    aspectRatio = it.aspectRatio
                    fillArea = it.fillArea
                    greater = it.greater
                    height = it.height
                    isPercentage = it.isPercentage
                    less = it.less
                    limitPixels = it.limitPixels
                    width = it.width
                    x = it.x
                    y = it.y
                }
            }

        internal inline fun fromRectangle(rectangle: MagickRectangle): MagickGeometry =
            MagickGeometry(rectangle.x, rectangle.y, rectangle.width, rectangle.height)

        internal inline fun fromRectangle(rectangle: NativeMagickRectangle): MagickGeometry =
            MagickGeometry(rectangle.x, rectangle.y, rectangle.width, rectangle.height)

        private fun parseInt(value: String): UInt {
            val start = value.indexOfFirst { c -> c.isDigit() }
            var end = start

            while (end < value.length && value[end].isDigit())
                end++

            return value.substring(start, end).toUInt()
        }
    }
}
