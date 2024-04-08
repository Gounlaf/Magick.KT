package imagemagick.magicknative

import imagemagick.core.exceptions.MagickException
import imagemagick.core.exceptions.warning.MagickWarningException
import imagemagick.events.EventHolder
import imagemagick.events.WarningEventArgs
import platform.posix.warn

@ExperimentalStdlibApi
public abstract class NativeHelper {
    public val warning: EventHolder<WarningEventArgs> = EventHolder()

    protected fun raiseWarning(exception: MagickException?) {
        if (exception is MagickWarningException) {
            warning.raiseEvent(WarningEventArgs(exception))
            warn(exception.message)
        }
    }

    protected inline fun <R> checkException(body: () -> Pair<R, MagickException?>): R {
        val (result, exception) = body()

        raiseWarning(exception)

        return result
    }
}
