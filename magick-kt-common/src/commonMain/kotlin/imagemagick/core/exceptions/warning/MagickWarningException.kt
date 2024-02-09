package imagemagick.core.exceptions.warning

import imagemagick.core.exceptions.MagickException

/**
 * Encapsulation of the ImageMagick Warning exception.
 */
public open class MagickWarningException : MagickException {
    public constructor() : super()
    public constructor(message: String?) : super(message)
    public constructor(message: String?, cause: Throwable?) : super(message, cause)
    public constructor(cause: Throwable?) : super(cause)
}
