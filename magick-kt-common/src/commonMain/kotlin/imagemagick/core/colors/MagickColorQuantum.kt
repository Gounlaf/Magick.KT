package imagemagick.core.colors

import imagemagick.core.types.Percentage

public interface MagickColorQuantum<TQuantumType> : Comparable<MagickColorQuantum<TQuantumType>?> where TQuantumType : Any {
    /**
     * Gets or sets the alpha component value of this color.
     */
    public var a: TQuantumType

    /**
     * Gets or sets the blue component value of this color.
     */
    public var b: TQuantumType

    /**
     * Gets or sets the green component value of this color.
     */
    public var g: TQuantumType

    /**
     * Gets a value indicating whether the color is a CMYK color.
     */
    public val isCmyk: Boolean

    /**
     * Gets or sets the key (black) component value of this color.
     */
    public var k: TQuantumType

    /**
     * Gets or sets the red component value of this color.
     */
    public var r: TQuantumType

    /**
     * Determines whether the specified color is fuzzy equal to the current color.
     *
     * @param other The color to compare this color with.
     * @param fuzz The fuzz factor.
     * @return True when the specified color is fuzzy equal to the current instance.
     */
    public fun fuzzyEquals(
        other: MagickColorQuantum<TQuantumType>,
        fuzz: Percentage,
    ): Boolean

    /**
     * Initializes the color with the specified bytes.
     *
     * @param red Red component value of this color.
     * @param green Green component value of this color.
     * @param blue Blue component value of this color.
     * @param alpha Alpha component value of this color.
     */
    public fun setFromUBytes(
        red: UByte,
        green: UByte,
        blue: UByte,
        alpha: UByte,
    )

    /**
     * Converts the value of this instance to a [UByte] array (RGBA or CMYKA).
     *
     * @return The [UByte] array.
     */
    public fun toUByteArray(): UByteArray

    /**
     * Converts the value of this instance to a hexadecimal string that will not include the alpha channel if it is opaque.
     *
     * @return The [String].
     *
     * @throws UnsupportedOperationException for CMYK colors
     */
    @Throws(UnsupportedOperationException::class)
    public fun toHexString(): String

    /**
     * Converts the value of this instance to a string representation that will not include the alpha channel if it is opaque.
     *
     * @return The [String].
     */
    public fun toShortString(): String

    /**
     * Converts the value of this instance to a string representation.
     *
     * @return The [String].
     */
    override fun toString(): String
}
