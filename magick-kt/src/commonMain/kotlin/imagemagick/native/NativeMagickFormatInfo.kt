package imagemagick.native

import imagemagick.core.toPrimitive
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CPointerVar
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.alloc
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
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

internal typealias ExceptionInfoPtrVar = CPointerVar<ExceptionInfo>

internal object NativeMagickFormatInfo {
    data class CreateListResult(val cPointer: CPointer<CPointerVar<ByteVar>>, val length: UInt) : AutoCloseable {
        override fun close() = disposeList(this)
    }

    @Throws(Exception::class)
    internal fun createList(): CreateListResult = memScoped {
        val length = alloc<size_tVar>()
        val exception = alloc<ExceptionInfoPtrVar>()

        val result = MagickFormatInfo_CreateList(length.ptr, exception.ptr)

        // TODO Check exception

        return CreateListResult(result!!, length.value.convert())
    }

    internal fun disposeList(list: CreateListResult) {
        MagickFormatInfo_DisposeList(list.cPointer, list.length.convert())
    }

    @Throws(Exception::class)
    internal fun getInfo(memScope: MemScope, list: CreateListResult, index: UInt): CPointer<MagickInfo> {
        val exception = memScope.alloc<ExceptionInfoPtrVar>()

        val result = MagickFormatInfo_GetInfo(list.cPointer, index.convert(), exception.ptr)

        // TODO Check exception

        return result!!
    }

    @Throws(Exception::class)
    internal fun getInfoWithBlob(data: UByteArray): CPointer<MagickInfo> = memScoped {
        val exception = alloc<ExceptionInfoPtrVar>()

//        MagickFormatInfo_GetInfoWithBlob(data.toCValues(), data.size.convert(), exception.ptr)
        // version below seems to be the equivalent of fixed in C#
        val result = data.usePinned {
            val cPt = it.addressOf(0)

            MagickFormatInfo_GetInfoWithBlob(cPt, data.size.convert(), exception.ptr)
        }

        // TODO Check exception

        return result!!
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
