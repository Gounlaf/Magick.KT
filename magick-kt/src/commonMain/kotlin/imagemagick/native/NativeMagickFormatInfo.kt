package imagemagick.native

import imagemagick.core.toPrimitive
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CPointerVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.alloc
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.pointed
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toKString
import kotlinx.cinterop.usePinned
import kotlinx.cinterop.value
import libMagickNative.ExceptionInfo
import libMagickNative.MagickFormatInfo_CanReadMultithreaded_Get
import libMagickNative.MagickFormatInfo_CanWriteMultithreaded_Get
import libMagickNative.MagickFormatInfo_CreateList
import libMagickNative.MagickFormatInfo_Description_Get
import libMagickNative.MagickFormatInfo_DisposeList
import libMagickNative.MagickFormatInfo_Format_Get
import libMagickNative.MagickFormatInfo_GetInfo
import libMagickNative.MagickFormatInfo_GetInfoWithBlob
import libMagickNative.MagickFormatInfo_MimeType_Get
import libMagickNative.MagickFormatInfo_Module_Get
import libMagickNative.MagickFormatInfo_SupportsMultipleFrames_Get
import libMagickNative.MagickFormatInfo_SupportsReading_Get
import libMagickNative.MagickFormatInfo_SupportsWriting_Get
import libMagickNative.MagickInfo
import platform.posix.size_tVar

@ExperimentalForeignApi
internal typealias ExceptionInfoPtrVar = CPointerVar<ExceptionInfo>

@ExperimentalForeignApi
@ExperimentalStdlibApi
internal object NativeMagickFormatInfo {
    data class CreateListResult(val cPointer: CPointer<CPointerVar<ByteVar>>, val length: UInt) : AutoCloseable {
        override fun close() {
            disposeList(this)
        }
    }

    @Throws(Exception::class)
    internal fun createList(): CreateListResult? = memScoped {
        val length = alloc<size_tVar>()
        val exception = alloc<ExceptionInfoPtrVar>()

        // TODO Check exception

        return MagickFormatInfo_CreateList(length.ptr, exception.ptr)?.let {
            CreateListResult(it, length.value.convert())
        }
    }

    internal fun disposeList(list: CreateListResult) {
        MagickFormatInfo_DisposeList(list.cPointer, list.length.convert())
    }

    @Throws(Exception::class)
    internal fun getInfo(list: CreateListResult, index: UInt): CPointer<MagickInfo>? = memScoped {
        val exception = alloc<ExceptionInfoPtrVar>()

        val result = MagickFormatInfo_GetInfo(list.cPointer, index.convert(), exception.ptr)

        exception.pointed?.let {
            println("got exception")
            println(it.error_number)
            println(it.description)
        }

        // TODO Check exception

        return result
    }

    @Throws(Exception::class)
    internal fun getInfoWithBlob(data: UByteArray): CPointer<MagickInfo>? = memScoped {
        val exceptionInfo = alloc<ExceptionInfoPtrVar>()

        val result = data.usePinned {
            MagickFormatInfo_GetInfoWithBlob(it.addressOf(0), data.size.convert(), exceptionInfo.ptr)
        }

        // TODO Check exception
        exceptionInfo.pointed?.let { exception ->
            println("got exception")
            println(exception.error_number)
            println(exception.description)
        }

        return result
    }

    internal inline fun CPointer<MagickInfo>.canReadMultithreaded(): Boolean =
        MagickFormatInfo_CanReadMultithreaded_Get(this).toPrimitive()

    internal inline fun CPointer<MagickInfo>.canWriteMultithreaded(): Boolean =
        MagickFormatInfo_CanWriteMultithreaded_Get(this).toPrimitive()

    internal inline fun CPointer<MagickInfo>.description(): String? =
        MagickFormatInfo_Description_Get(this)?.toKString()

    internal inline fun CPointer<MagickInfo>.format(): String? = MagickFormatInfo_Format_Get(this)?.toKString()

    internal inline fun CPointer<MagickInfo>.mimeType(): String? = MagickFormatInfo_MimeType_Get(this)?.toKString()

    internal inline fun CPointer<MagickInfo>.moduleFormat(): String? = MagickFormatInfo_Module_Get(this)?.toKString()

    internal inline fun CPointer<MagickInfo>.supportsMultipleFrames(): Boolean =
        MagickFormatInfo_SupportsMultipleFrames_Get(this).toPrimitive()

    internal inline fun CPointer<MagickInfo>.supportsReading(): Boolean =
        MagickFormatInfo_SupportsReading_Get(this).toPrimitive()

    internal inline fun CPointer<MagickInfo>.supportsWriting(): Boolean =
        MagickFormatInfo_SupportsWriting_Get(this).toPrimitive()
}
