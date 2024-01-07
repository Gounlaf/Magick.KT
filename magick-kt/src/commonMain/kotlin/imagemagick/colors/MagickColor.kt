package imagemagick.colors

import imagemagick.Quantum
import imagemagick.QuantumType
import imagemagick.core.colors.MagickColorQuantum
import imagemagick.core.types.Percentage
import imagemagick.exceptions.Throw
import imagemagick.helpers.PercentageHelper
import imagemagick.native.colors.NativeMagickColor
import imagemagick.quantum
import kotlinx.cinterop.ExperimentalForeignApi
import imagemagick.core.colors.MagickColorQuantum as IMagickColor

/**
 * Class that represents a color.
 *
 * @constructor Initializes a new instance of the [MagickColor] class.
 */
@ExperimentalStdlibApi
@ExperimentalForeignApi
public data class MagickColor constructor(
    override var a: QuantumType = 0u.quantum(),
    override var b: QuantumType = 0u.quantum(),
    override var g: QuantumType = 0u.quantum(),
    override var isCmyk: Boolean = false,
    override var k: QuantumType = 0u.quantum(),
    override var r: QuantumType = 0u.quantum(),
) : IMagickColor<QuantumType> {
    /**
     * Initializes a new instance of the [MagickColor] class.
     *
     * @param color THe color to use.
     */
    public constructor(color: IMagickColor<QuantumType>) : this() {
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
    public constructor(red: QuantumType, green: QuantumType, blue: QuantumType) : this() {
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
    public constructor(red: QuantumType, green: QuantumType, blue: QuantumType, alpha: QuantumType) : this() {
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
    public constructor(
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
    public constructor(color: String) : this() {
        Throw.ifEmpty(color)

        if (color.contentEquals("transparent", ignoreCase = true)) {
            initialize(QuantumType.MAX_VALUE, QuantumType.MAX_VALUE, QuantumType.MAX_VALUE, 0.quantum())
            return
        }

        if (color.startsWith('#')) {
            parseHexColor(color)
            return
        }

        NativeMagickColor().use {
            Throw.ifFalse("color", it.initialize(color), "Invalid color specified")
            initialize(it)
        }
    }

    override fun setFromUBytes(
        red: UByte,
        green: UByte,
        blue: UByte,
        alpha: UByte,
    ) {
        r = Quantum.convert(red)
        g = Quantum.convert(green)
        b = Quantum.convert(blue)
        a = Quantum.convert(alpha)
        k = Quantum.convert(0)
        isCmyk = false
    }

    override fun toUByteArray(): UByteArray =
        if (isCmyk) {
            ubyteArrayOf(
                Quantum.scaleToUbyte(r),
                Quantum.scaleToUbyte(g),
                Quantum.scaleToUbyte(b),
                Quantum.scaleToUbyte(k),
                Quantum.scaleToUbyte(a),
            )
        } else {
            ubyteArrayOf(
                Quantum.scaleToUbyte(r),
                Quantum.scaleToUbyte(g),
                Quantum.scaleToUbyte(b),
                Quantum.scaleToUbyte(a),
            )
        }

    override fun toHexString(): String {
        if (isCmyk) {
            throw UnsupportedOperationException("This method only works for non cmyk colors.")
        }

        val red = Quantum.scaleToUbyte(r)
        val green = Quantum.scaleToUbyte(g)
        val blue = Quantum.scaleToUbyte(b)

        val f = HexFormat.UpperCase

        val rgb = "#${red.toHexString(f)}${green.toHexString(f)}${blue.toHexString(f)}"

        if (a == Quantum.max) {
            return rgb
        }

        val alpha = Quantum.scaleToUbyte(a)

        return "${rgb}${alpha.toHexString(f)}"
    }

    override fun toShortString(): String {
        if (a != Quantum.max) {
            return toString()
        }

        if (isCmyk) {
            val red = Quantum.scaleToUbyte(r)
            val green = Quantum.scaleToUbyte(g)
            val blue = Quantum.scaleToUbyte(b)
            val black = Quantum.scaleToUbyte(k)

            return "cmyk($red,$green,$blue,$black)"
        }

        // TODO Q8/Q16
        val red = r.quantum()
        val green = g.quantum()
        val blue = b.quantum()

        return HexFormat.UpperCase.let {
            "#${red.toHexString(it)}${blue.toHexString(it)}${green.toHexString(it)}"
        }
    }

    override fun compareTo(other: MagickColorQuantum<QuantumType>?): Int =
        when {
            other == null -> 1
            r < other.r -> -1
            r > other.r -> 1
            g < other.g -> -1
            g > other.g -> 1
            b < other.b -> -1
            b > other.b -> 1
            k < other.k -> -1
            k > other.k -> 1
            a < other.a -> -1
            a > other.a -> 1
            else -> 0
        }

    public operator fun times(percentage: Percentage): MagickColor {
        val red = percentage.times(r.toDouble()).quantum()
        val green = percentage.times(g.toDouble()).quantum()
        val blue = percentage.times(b.toDouble()).quantum()

        if (!isCmyk) {
            return MagickColor(red, green, blue, a)
        }

        val key = percentage.times(k.toDouble()).quantum()

        return MagickColor(red, green, blue, key, a)
    }

    override fun toString(): String {
        if (isCmyk) {
            val red = Quantum.scaleToUbyte(r)
            val green = Quantum.scaleToUbyte(g)
            val blue = Quantum.scaleToUbyte(b)
            val black = Quantum.scaleToUbyte(k)

            val alpha = a.toDouble().div(Quantum.max.toDouble()).toString()

            // C# cmyka({0},{1},{2},{3},{4:0.0###})
            return "cmyka($red,$green,$blue,$black,${alpha.take(6)})"
        }

        // TODO Q8/Q16
        val red = r.quantum()
        val green = g.quantum()
        val blue = b.quantum()
        val alpha = a.quantum()

        return HexFormat.UpperCase.let {
            "#${red.toHexString(it)}${blue.toHexString(it)}${green.toHexString(it)}${alpha.toHexString(it)}"
        }
    }

    override fun fuzzyEquals(
        other: IMagickColor<QuantumType>,
        fuzz: Percentage,
    ): Boolean {
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

    private fun initialize(
        red: QuantumType,
        green: QuantumType,
        blue: QuantumType,
        alpha: QuantumType,
    ) {
        r = red
        g = green
        b = blue
        a = alpha
        k = Quantum.convert(0)
        isCmyk = false
    }

    @ExperimentalStdlibApi
    private fun parseHexColor(color: String) {
        val (success, colors) = HexColor.tryParse(color)
        if (!success) {
            throw IllegalArgumentException("Invalid hex value (parsing failure).")
        }

        when (colors.size) {
            1 -> initialize(colors[0], colors[0], colors[0], Quantum.max)
            3 -> initialize(colors[0], colors[1], colors[2], Quantum.max)
            4 -> initialize(colors[0], colors[1], colors[2], colors[3])
            else -> throw IllegalArgumentException("Invalid hex value (incorrect number of colors).")
        }
    }

    public companion object {
        public fun createNativeInstance(color: IMagickColor<QuantumType>): NativeMagickColor =
            NativeMagickColor().also {
                it.red(color.r)
                it.blue(color.b)
                it.green(color.g)
                it.alpha(color.a)
                it.black(color.k)
                it.isCMYK(color.isCmyk)
            }

        /**
         * Creates a new [MagickColor] instance from the specified 8-bit color values (red, green, blue and alpha).
         *
         * @param red Red component value of this color.
         * @param green Green component value of this color.
         * @param blue Blue component value of this color.
         * @param alpha Alpha component value of this color.
         *
         * @return A [MagickColor] instance.
         */
        public fun fromRgba(
            red: UByte,
            green: UByte,
            blue: UByte,
            alpha: UByte,
        ): MagickColor =
            MagickColor().apply {
                setFromUBytes(red, green, blue, alpha)
            }
    }
}
