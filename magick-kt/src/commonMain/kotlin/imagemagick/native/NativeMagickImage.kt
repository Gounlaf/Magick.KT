package imagemagick.native

import imagemagick.QuantumType
import imagemagick.settings.MagickSettings
import imagemagick.toSizeT
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.UByteVar
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.alloc
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.nativeHeap
import kotlinx.cinterop.objcPtr
import kotlinx.cinterop.pointed
import kotlinx.cinterop.ptr
import kotlinx.cinterop.readBits
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.usePinned
import kotlinx.cinterop.writeBits
import libMagickNative.CustomStreamHandler
import libMagickNative.Image
import libMagickNative.MagickImage_Create
import libMagickNative.MagickImage_Dispose
import libMagickNative.MagickImage_ReadBlob
import libMagickNative.MagickImage_ReadFile
import libMagickNative.MagickImage_ReadStream
import okio.Buffer
import okio.BufferedSource
import okio.Source
import okio.buffer
import platform.posix.memcpy
import platform.posix.size_t
import kotlin.math.min
import imagemagick.core.settings.MagickSettings as IMagickSettings

@ExperimentalStdlibApi
@ExperimentalForeignApi
internal class NativeMagickImage internal constructor(settings: NativeMagickSettings) : AutoCloseable {
    internal var ptr: CPointer<Image> = memScoped {
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

    fun readBlob(settings: IMagickSettings<QuantumType>?, data: UByteArray, offset: UInt, length: UInt) = memScoped {
        val exceptionInfo = alloc<ExceptionInfoPtrVar>()

        val image = data.usePinned { pinnedData ->
            MagickSettings.createNativeInstance(settings).use { nativeSettings ->
                MagickImage_ReadBlob(
                    nativeSettings.ptr, pinnedData.addressOf(0), offset.convert(), length.convert(), exceptionInfo.ptr
                )
            }
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

    fun readFile(settings: IMagickSettings<QuantumType>?) = memScoped {
        val exceptionInfo = alloc<ExceptionInfoPtrVar>()

        val image = MagickSettings.createNativeInstance(settings).use { nativeSettings ->
            MagickImage_ReadFile(
                nativeSettings.ptr, exceptionInfo.ptr
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

    fun readStream(stream: BufferedSource, settings: IMagickSettings<QuantumType>?) {
        TODO()
    }
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

    companion object {
        internal inline fun CPointer<Image>.dispose() = MagickImage_Dispose(this)
    }
}
