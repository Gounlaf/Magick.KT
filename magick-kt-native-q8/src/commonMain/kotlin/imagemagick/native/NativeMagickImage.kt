package imagemagick.native

import imagemagick.QuantumType
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CValuesRef
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.alloc
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.pointed
import kotlinx.cinterop.ptr
import kotlinx.cinterop.usePinned
import libMagickNative.Image
import libMagickNative.MagickImage_AnimationDelay_Get
import libMagickNative.MagickImage_AnimationDelay_Set
import libMagickNative.MagickImage_AnimationIterations_Get
import libMagickNative.MagickImage_AnimationIterations_Set
import libMagickNative.MagickImage_AnimationTicksPerSecond_Get
import libMagickNative.MagickImage_AnimationTicksPerSecond_Set
import libMagickNative.MagickImage_BackgroundColor_Get
import libMagickNative.MagickImage_BackgroundColor_Set
import libMagickNative.MagickImage_Create
import libMagickNative.MagickImage_Dispose
import libMagickNative.MagickImage_ReadBlob
import libMagickNative.MagickImage_ReadFile
import libMagickNative.PixelInfo
import okio.BufferedSource
import platform.posix.size_t
import platform.posix.ssize_t
import imagemagick.core.settings.MagickSettings as IMagickSettings

@ExperimentalStdlibApi
@ExperimentalForeignApi
class NativeMagickImage constructor(settings: NativeMagickSettings) : AutoCloseable {
    var ptr: CPointer<Image> = memScoped {
        val exception = alloc<ExceptionInfoPtrVar>()

        MagickImage_Create(settings.ptr, exception.ptr)

        // TODO Check exception
    } ?: error("Failed to instantiate native MagickImage")
        set(value) {
            field.dispose()
            field = value
        }

    override fun close() {
        dispose()
    }

    fun dispose() = ptr.dispose()

    fun animationDelay(): size_t = ptr.animationDelay()
    fun animationDelay(value: size_t) = ptr.animationDelay(value)

    fun animationIterations(): size_t = ptr.animationIterations()
    fun animationIterations(value: size_t) = ptr.animationIterations(value)

    fun animationTicksPerSecond(): ssize_t = ptr.animationTicksPerSecond()
    fun animationTicksPerSecond(value: ssize_t) = ptr.animationTicksPerSecond(value)

    fun backgroundColor(): CPointer<PixelInfo>? = ptr.backgroundColor()
    fun backgroundColor(value: CValuesRef<PixelInfo>?) = ptr.backgroundColor(value)

    // Diff from Magick.NET: a native version required in parameters
    fun readBlob(settings: NativeMagickSettings?, data: UByteArray, offset: UInt, length: UInt) = memScoped {
        val exceptionInfo = alloc<ExceptionInfoPtrVar>()

        val image = data.usePinned { pinnedData ->
            MagickImage_ReadBlob(
                settings?.ptr, pinnedData.addressOf(0), offset.convert(), length.convert(), exceptionInfo.ptr
            )
        }

        exceptionInfo.pointed?.let { exception ->
            println("got exception")
            println(exception.error_number)
            println(exception.description)
        }

        if (null != image) {
            ptr = image
        }
    }

    // Diff from Magick.NET: a native version required in parameters
    fun readFile(settings: NativeMagickSettings?) = memScoped {
        val exceptionInfo = alloc<ExceptionInfoPtrVar>()

        val image = MagickImage_ReadFile(settings?.ptr, exceptionInfo.ptr)

        exceptionInfo.pointed?.let { exception ->
            println("got exception")
            println(exception.error_number)
            println(exception.description)
        }

        if (null != image) {
            ptr = image
        }
    }

    fun readStream(stream: BufferedSource, settings: IMagickSettings<QuantumType>?) {
        TODO()
        // memScoped {
//        val exceptionInfo = alloc<ExceptionInfoPtrVar>()
//
//        val image = MagickSettings.createNativeInstance(settings).use { nativeSettings ->
//            MagickImage_ReadStream(
//                nativeSettings.ptr,
//                // read
//                staticCFunction { data: CPointer<UByteVar>?, count: size_t, _: COpaquePointer?, ->
//                    if (null == data) {
//                        return@staticCFunction 0
//                    }
//
//                    if (0.toSizeT() == count) {
//                        return@staticCFunction 0
//                    }
//
//
//                    val bufferSize = 8192
//                    val sink = ByteArray(bufferSize)
//
//                    var total: Long = count.convert()
//
//                    var destination: CPointer<UByteVar> = data.getPointer(this)
//                    var bytesRead: Long = 0
//
//                    while (total > 0) {
//                        var length = min(total, bufferSize.toLong())
//
//                        if (stream.exhausted()) {
//                            break
//                        }
//
//                        try {
//                            length = stream.read(sink, 0, length.toInt()).toLong()
//                        } catch(e: Exception) {
//                            return@staticCFunction -1
//                        }
//
//                        if (length < 0) {
//                            break
//                        }
//
//                        sink.usePinned {
//                            memcpy(destination, it.addressOf(0), length.convert())?.let { dstUpdated ->
//                                destination = dstUpdated.reinterpret()
//                            }
//                        }
//
//                        bytesRead += length
//
//                        total -= length
//                    }
//
//                    bytesRead.convert()
//                },
//                // seek
//                null,
////                staticCFunction { offset: libMagickNative.MagickOffsetType, whence: Int, _: COpaquePointer?, ->
////                    try {
////                        0
////                    } catch (e: Exception) {
////                        -1
////                    }
////                },
//                // tell
////                staticCFunction { _: COpaquePointer? -> 0 },
//                null,
//                exceptionInfo.ptr
//            )
//        }
//
//        exceptionInfo.pointed?.let { exception ->
//            println("got exception")
//            println(exception.error_number)
//            println(exception.description)
//        }
//
//        if (null != image) {
//            ptr = image
//        }
//    }
    }

    companion object {
        inline fun CPointer<Image>.dispose() = MagickImage_Dispose(this)

        inline fun CPointer<Image>.animationDelay(): size_t = MagickImage_AnimationDelay_Get(this)
        inline fun CPointer<Image>.animationDelay(value: size_t) = MagickImage_AnimationDelay_Set(this, value)

        inline fun CPointer<Image>.animationIterations(): size_t = MagickImage_AnimationIterations_Get(this)
        inline fun CPointer<Image>.animationIterations(value: size_t) = MagickImage_AnimationIterations_Set(this, value)

        inline fun CPointer<Image>.animationTicksPerSecond(): ssize_t = MagickImage_AnimationTicksPerSecond_Get(this)
        inline fun CPointer<Image>.animationTicksPerSecond(value: ssize_t) = MagickImage_AnimationTicksPerSecond_Set(this, value)

        inline fun CPointer<Image>.backgroundColor(): CPointer<PixelInfo>? = MagickImage_BackgroundColor_Get(this)
        inline fun CPointer<Image>.backgroundColor(value: CValuesRef<PixelInfo>?) = MagickImage_BackgroundColor_Set(this, value)
    }
}
