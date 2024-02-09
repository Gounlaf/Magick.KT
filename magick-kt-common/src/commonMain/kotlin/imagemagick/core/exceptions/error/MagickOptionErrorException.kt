package imagemagick.core.exceptions.error

/**
 * Encapsulation of the ImageMagick OptionError exception.
 */
public class MagickOptionErrorException : MagickErrorException {
    public constructor() : super()
    public constructor(message: String?) : super(message)
    public constructor(message: String?, cause: Throwable?) : super(message, cause)
    public constructor(cause: Throwable?) : super(cause)
}
