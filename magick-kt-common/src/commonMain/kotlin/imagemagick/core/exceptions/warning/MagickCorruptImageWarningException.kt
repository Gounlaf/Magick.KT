package imagemagick.core.exceptions.warning

/**
 * Encapsulation of the ImageMagick CorruptImageWarning exception.
 */
public class MagickCorruptImageWarningException : MagickWarningException {
    public constructor() : super()
    public constructor(message: String?) : super(message)
    public constructor(message: String?, cause: Throwable?) : super(message, cause)
    public constructor(cause: Throwable?) : super(cause)
}
