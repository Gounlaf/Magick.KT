package imagemagick.core.exceptions.warning

/**
 * Encapsulation of the ImageMagick ImageWarning exception.
 */
public class MagickImageWarningException : MagickWarningException {
    public constructor() : super()
    public constructor(message: String?) : super(message)
    public constructor(message: String?, cause: Throwable?) : super(message, cause)
    public constructor(cause: Throwable?) : super(cause)
}
