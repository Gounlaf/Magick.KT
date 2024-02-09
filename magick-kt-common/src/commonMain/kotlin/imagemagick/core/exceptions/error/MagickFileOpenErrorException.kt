package imagemagick.core.exceptions.error

/**
 * Encapsulation of the ImageMagick ileOpenError exception.
 */
public class MagickFileOpenErrorException : MagickErrorException {
    public constructor() : super()
    public constructor(message: String?) : super(message)
    public constructor(message: String?, cause: Throwable?) : super(message, cause)
    public constructor(cause: Throwable?) : super(cause)
}
