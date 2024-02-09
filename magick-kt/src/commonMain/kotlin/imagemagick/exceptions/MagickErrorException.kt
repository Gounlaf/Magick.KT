package imagemagick.exceptions

/**
 * Encapsulation of the ImageMagick Error exception.
 */
public class MagickErrorException : MagickException {
    public constructor() : super()
    public constructor(message: String?) : super(message)
    public constructor(message: String?, cause: Throwable?) : super(message, cause)
    public constructor(cause: Throwable?) : super(cause)
}
