package imagemagick.native

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import libMagickNative.Image
import libMagickNative.MagickImage_Create
import libMagickNative.MagickImage_Dispose

@ExperimentalStdlibApi
@ExperimentalForeignApi
internal class NativeMagickImage internal constructor(settings: NativeMagickSettings) : AutoCloseable {
    internal val instance: CPointer<Image> = memScoped {
        val exception = alloc<ExceptionInfoPtrVar>()

        MagickImage_Create(settings.native, exception.ptr)

        // TODO Check exception
    }  ?: error("Failed to instantiate native MagickImage")

    override fun close() {
        dispose()
    }

    fun dispose() = instance.dispose()

    companion object {
        internal inline fun CPointer<Image>.dispose() = MagickImage_Dispose(this)
    }
}
