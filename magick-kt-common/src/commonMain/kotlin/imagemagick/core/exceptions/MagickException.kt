package imagemagick.core.exceptions

/**
 * Encapsulation of the ImageMagick exception object.
 */
public open class MagickException : Exception {
    public var relatedExceptions: List<MagickException>? = null
        private set

    public constructor() : super()
    public constructor(message: String?) : super(message)
    public constructor(message: String?, cause: Throwable?) : super(message, cause)
    public constructor(cause: Throwable?) : super(cause)

    /**
     * Sets the related exceptions of this exception.
     *
     * @param relatedExceptions The related exceptions.
     */
    public fun setRelatedException(relatedExceptions: List<MagickException>) {
        this.relatedExceptions = relatedExceptions
    }
}
