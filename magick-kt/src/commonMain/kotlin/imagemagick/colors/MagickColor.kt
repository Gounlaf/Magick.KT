package imagemagick.colors

import imagemagick.QuantumImpl
import imagemagick.QuantumType
import imagemagick.core.types.Percentage
import imagemagick.exceptions.Throw
import imagemagick.helpers.PercentageHelper
import imagemagick.native.colors.NativeMagickColor
import kotlinx.cinterop.ExperimentalForeignApi
import imagemagick.core.colors.MagickColorQuantum as IMagickColor

/**
 * Class that represents a color.
 *
 * @constructor Initializes a new instance of the [MagickColor] class.
 */
@ExperimentalStdlibApi
@ExperimentalForeignApi
data class MagickColor constructor(
    override var a: QuantumType = QuantumImpl.convert(0u),
    override var b: QuantumType = QuantumImpl.convert(0u),
    override var g: QuantumType = QuantumImpl.convert(0u),
    override var isCmyk: Boolean = false,
    override var k: QuantumType = QuantumImpl.convert(0u),
    override var r: QuantumType = QuantumImpl.convert(0u),
) : IMagickColor<QuantumType> {

    /**
     * Initializes a new instance of the [MagickColor] class.
     *
     * @param color THe color to use.
     */
    constructor(color: IMagickColor<QuantumType>) : this() {
        r = color.r
        g = color.g
        b = color.b
        a = color.a
        k = color.k
        isCmyk = color.isCmyk
    }

    /**
     * Initializes a new instance of the [MagickColor] class.
     *
     * @param red Red component value of this color (Q8: 0-255, Q16/Q16HDRI 0-65535).
     * @param green Green component value of this color (Q8: 0-255, Q16/Q16HDRI 0-65535).
     * @param blue Blue component value of this color (Q8: 0-255, Q16/Q16HDRI 0-65535).
     */
    constructor(red: QuantumType, green: QuantumType, blue: QuantumType) : this() {
        initialize(red, green, blue, QuantumType.MAX_VALUE)
    }

    /**
     * Initializes a new instance of the [MagickColor] class.
     *
     * @param red Red component value of this color (Q8: 0-255, Q16/Q16HDRI 0-65535).
     * @param green Green component value of this color (Q8: 0-255, Q16/Q16HDRI 0-65535).
     * @param blue Blue component value of this color (Q8: 0-255, Q16/Q16HDRI 0-65535).
     * @param alpha Alpha component value of this color (Q8: 0-255, Q16/Q16HDRI 0-65535).
     */
    constructor(red: QuantumType, green: QuantumType, blue: QuantumType, alpha: QuantumType) : this() {
        initialize(red, green, blue, alpha)
    }

    /**
     * Initializes a new instance of the [MagickColor] class.
     *
     * @param cyan Cyan component value of this color (Q8: 0-255, Q16/Q16HDRI 0-65535).
     * @param magenta Magenta component value of this color (Q8: 0-255, Q16/Q16HDRI 0-65535).
     * @param yellow Yellow component value of this color (Q8: 0-255, Q16/Q16HDRI 0-65535).
     * @param black Black component value of this color (Q8: 0-255, Q16/Q16HDRI 0-65535).
     * @param alpha Alpha component value of this color (Q8: 0-255, Q16/Q16HDRI 0-65535).
     */
    constructor(
        cyan: QuantumType,
        magenta: QuantumType,
        yellow: QuantumType,
        black: QuantumType,
        alpha: QuantumType,
    ) : this() {
        initialize(cyan, magenta, yellow, alpha)
        k = black
        isCmyk = true
    }

    /**
     * Initializes a new instance of the [MagickColor] class.
     *
     * @param color The RGBA/CMYK hex string or name of the color (http://www.imagemagick.org/script/color.php).
     *  For example: #F000, #FF000000, #FFFF000000000000.
     */
    constructor(color: String) : this() {
        Throw.ifEmpty(color)

        if (color.contentEquals("transparent", ignoreCase = true)) {
            initialize(QuantumType.MAX_VALUE, QuantumType.MAX_VALUE, QuantumType.MAX_VALUE, QuantumImpl.convert(0))
            return
        }

        if (color.startsWith('#')) {
            NativeMagickColor().use {
                Throw.ifFalse("color", it.initialize(color), "Invalid color specified")
                initialize(it)
            }
        }
    }

    override fun setFromUBytes(red: UByte, green: UByte, blue: UByte, alpha: UByte) {
        r = QuantumImpl.convert(red);
        g = QuantumImpl.convert(green);
        b = QuantumImpl.convert(blue);
        a = QuantumImpl.convert(alpha);
        k = QuantumImpl.convert(0)
        isCmyk = false
    }

    override fun toUByteArray(): UByteArray = if (isCmyk) {
        ubyteArrayOf(
            QuantumImpl.scaleToUbyte(r),
            QuantumImpl.scaleToUbyte(g),
            QuantumImpl.scaleToUbyte(b),
            QuantumImpl.scaleToUbyte(k),
            QuantumImpl.scaleToUbyte(a),
        )
    } else {
        ubyteArrayOf(
            QuantumImpl.scaleToUbyte(r),
            QuantumImpl.scaleToUbyte(g),
            QuantumImpl.scaleToUbyte(b),
            QuantumImpl.scaleToUbyte(a),
        )
    }

    override fun toHexString(): String {
        if (isCmyk) {
            throw UnsupportedOperationException("This method only works for non cmyk colors.")
        }

        val red = QuantumImpl.scaleToUbyte(r)
        val green = QuantumImpl.scaleToUbyte(g)
        val blue = QuantumImpl.scaleToUbyte(b)

        val rgb = "#${red.toHexString()}${green.toHexString()}${blue.toHexString()}"

        if (a == QuantumImpl.max) {
            return rgb
        }

        val alpha = QuantumImpl.scaleToUbyte(a)

        return "${rgb}${alpha.toHexString()}"
    }

    override fun toShortString(): String {
        if (a != QuantumImpl.max) {
            return toString()
        }

        if (isCmyk) {
            val red = QuantumImpl.scaleToUbyte(r)
            val green = QuantumImpl.scaleToUbyte(g)
            val blue = QuantumImpl.scaleToUbyte(b)
            val black = QuantumImpl.scaleToUbyte(k)

            return "cmyk($red,$green,$blue,$black)"
        }

        // TODO Q8/Q16
        val red = QuantumImpl.convert(r)
        val green = QuantumImpl.convert(g)
        val blue = QuantumImpl.convert(b)

        return "#${red.toHexString()}${blue.toHexString()}${green.toHexString()}"
    }

    override fun toString(): String {
        if (isCmyk) {
            val red = QuantumImpl.scaleToUbyte(r)
            val green = QuantumImpl.scaleToUbyte(g)
            val blue = QuantumImpl.scaleToUbyte(b)
            val black = QuantumImpl.scaleToUbyte(k)

            val alpha = a.toDouble().div(QuantumImpl.max.toDouble())

            return "cmyk($red,$green,$blue,$black,$alpha)"
        }

        // TODO Q8/Q16
        val red = QuantumImpl.convert(r)
        val green = QuantumImpl.convert(g)
        val blue = QuantumImpl.convert(b)
        val alpha = QuantumImpl.convert(a)

//        when (Q) {
//            BitDepth.Q8 -> TODO()
//            BitDepth.Q16 -> TODO()
//            BitDepth.Q16HDRI -> TODO()
//        }

        return "#${red.toHexString()}${blue.toHexString()}${green.toHexString()}${alpha.toHexString()}"
    }

    override fun fuzzyEquals(other: IMagickColor<QuantumType>, fuzz: Percentage): Boolean {
        if (this === other) {
            return true
        }

        return createNativeInstance(this).use { one ->
            createNativeInstance(other).use {
                one.fuzzyEquals(it, PercentageHelper.toQuantumType(fuzz))
            }
        }
    }

    private fun initialize(native: NativeMagickColor) {
        r = native.red()
        g = native.green()
        b = native.blue()
        a = native.alpha()
        k = native.black()
        isCmyk = native.isCMYK()
    }

    private fun initialize(red: QuantumType, green: QuantumType, blue: QuantumType, alpha: QuantumType) {
        r = red
        g = green
        b = blue
        a = alpha
        k = QuantumImpl.convert(0)
        isCmyk = false
    }

    @ExperimentalStdlibApi
    private fun parseHexColor(color: String) {
        val (success, colors) = HexColor.tryParse(color)
        if (!success) {
            throw IllegalArgumentException("Invalid hex value.")
        }

        when (colors.size) {
            1 -> initialize(colors[0], colors[0], colors[0], QuantumImpl.max)
            3 -> initialize(colors[0], colors[1], colors[2], QuantumImpl.max)
            4 -> initialize(colors[0], colors[1], colors[2], colors[3])
            else -> throw IllegalArgumentException("Invalid hex value.")
        }
    }

    companion object {
        fun createNativeInstance(color: IMagickColor<QuantumType>): NativeMagickColor = NativeMagickColor().also {
            it.red(color.r)
            it.blue(color.b)
            it.green(color.g)
            it.alpha(color.a)
            it.black(color.k)
            it.isCMYK(color.isCmyk)
        }
    }
}
