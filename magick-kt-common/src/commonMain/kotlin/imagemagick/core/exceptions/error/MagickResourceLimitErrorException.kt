package imagemagick.core.exceptions.error

/**
 * Encapsulation of the ImageMagick ResourceLimitError exception.
 */
public class MagickResourceLimitErrorException : MagickErrorException {
    public constructor() : super()
    public constructor(message: String?) : super(message)
    public constructor(message: String?, cause: Throwable?) : super(message, cause)
    public constructor(cause: Throwable?) : super(cause)
}
