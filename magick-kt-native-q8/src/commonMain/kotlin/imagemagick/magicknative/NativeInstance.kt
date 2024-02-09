package imagemagick.magicknative

import imagemagick.core.exceptions.MagickException
import imagemagick.core.exceptions.error.MagickErrorException
import imagemagick.core.exceptions.warning.MagickWarningException
import imagemagick.magicknative.exceptions.MagickExceptionHelper
import kotlinx.cinterop.CPointed
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.ExceptionInfo
import platform.posix.warn

@ExperimentalForeignApi
public abstract class NativeInstance<T : CPointed> {
    protected abstract fun dispose(result: CPointer<T>)

    protected fun checkException(exception: CPointer<ExceptionInfo>?, result: CPointer<T>?) {
        MagickExceptionHelper.create(exception).let { magickException ->
            if (magickException is MagickErrorException) {
                result?.let { dispose(result) }

                throw magickException
            }

            raiseWarning(magickException)

            if (null == result) {
                throw MagickErrorException("The operation returned null but did not raise an exception.")
            }
        }
    }

    protected fun raiseWarning(exception: MagickException?) {
//        if (_warningEvent is null)
//            return;

        if (exception is MagickWarningException) {
//            _warningEvent.Invoke(this, new WarningEventArgs(warning));
            warn(exception.message)
        }
    }
}
