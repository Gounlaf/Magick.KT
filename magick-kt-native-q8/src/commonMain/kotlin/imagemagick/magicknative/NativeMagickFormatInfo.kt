package imagemagick.magicknative

import imagemagick.magicknative.exceptions.withException
import kotlin.contracts.ExperimentalContracts
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CPointerVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.alloc
import kotlinx.cinterop.convert
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toKString
import kotlinx.cinterop.usePinned
import kotlinx.cinterop.value
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

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public object NativeMagickFormatInfo {
    public data class CreateListResult(val cPointer: CPointer<CPointerVar<ByteVar>>, val length: UInt) : AutoCloseable {
        override fun close() {
            disposeList(this)
        }
    }

    @Throws(Exception::class)
    public fun createList(): CreateListResult? = withException { placement, exceptionInfo ->
        val length = placement.alloc<size_tVar>()

        MagickFormatInfo_CreateList(length.ptr, exceptionInfo.ptr)?.let {
            CreateListResult(it, length.value.convert())
        }
    }

    public fun disposeList(list: CreateListResult) {
        MagickFormatInfo_DisposeList(list.cPointer, list.length.convert())
    }

    @Throws(Exception::class)
    public fun getInfo(list: CreateListResult, index: UInt): CPointer<MagickInfo>? = withException { _, exceptionInfo ->
        MagickFormatInfo_GetInfo(list.cPointer, index.convert(), exceptionInfo.ptr)
    }

    @Throws(Exception::class)
    public fun getInfoWithBlob(data: UByteArray): CPointer<MagickInfo>? = withException { _, exceptionInfo ->
        data.usePinned {
            MagickFormatInfo_GetInfoWithBlob(it.addressOf(0), data.size.convert(), exceptionInfo.ptr)
        }
    }

    public inline fun CPointer<MagickInfo>.canReadMultithreaded(): Boolean =
        MagickFormatInfo_CanReadMultithreaded_Get(this).toPrimitive()

    public inline fun CPointer<MagickInfo>.canWriteMultithreaded(): Boolean =
        MagickFormatInfo_CanWriteMultithreaded_Get(this).toPrimitive()

    public inline fun CPointer<MagickInfo>.description(): String? =
        MagickFormatInfo_Description_Get(this)?.toKString()

    public inline fun CPointer<MagickInfo>.format(): String? = MagickFormatInfo_Format_Get(this)?.toKString()

    public inline fun CPointer<MagickInfo>.mimeType(): String? = MagickFormatInfo_MimeType_Get(this)?.toKString()

    public inline fun CPointer<MagickInfo>.moduleFormat(): String? = MagickFormatInfo_Module_Get(this)?.toKString()

    public inline fun CPointer<MagickInfo>.supportsMultipleFrames(): Boolean =
        MagickFormatInfo_SupportsMultipleFrames_Get(this).toPrimitive()

    public inline fun CPointer<MagickInfo>.supportsReading(): Boolean =
        MagickFormatInfo_SupportsReading_Get(this).toPrimitive()

    public inline fun CPointer<MagickInfo>.supportsWriting(): Boolean =
        MagickFormatInfo_SupportsWriting_Get(this).toPrimitive()
}
