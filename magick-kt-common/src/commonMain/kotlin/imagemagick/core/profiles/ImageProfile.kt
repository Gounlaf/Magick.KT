package imagemagick.core.profiles

/**
 * Interface that describes an image profile.
 */
public interface ImageProfile {
    /**
     * Gets the name of the profile.
     */
    public val name: String

    /**
     * Returns the [Byte] array of this profile.
     *
     * @return A [Byte] array.
     */
    public fun getData(): UByteArray?

    /**
     * Converts this instance to a byte array.
     *
     * @return A [Byte] array.
     */
    public fun toByteArray(): UByteArray?

    public override fun equals(other: Any?): Boolean
}
