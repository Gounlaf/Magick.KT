package imagemagick.native

import imagemagick.core.toBoolean
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CPointerVar
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toCValues
import kotlinx.cinterop.toKString
import kotlinx.cinterop.value
import libMagickNative.ExceptionInfo
import libMagickNative.MagickFormatInfo_CanReadMultithreaded_Get
import libMagickNative.MagickFormatInfo_CanWriteMultithreaded_Get
import libMagickNative.MagickFormatInfo_CreateList
import libMagickNative.MagickFormatInfo_Description_Get
import libMagickNative.MagickFormatInfo_Format_Get
import libMagickNative.MagickFormatInfo_GetInfo
import libMagickNative.MagickFormatInfo_GetInfoWithBlob
import libMagickNative.MagickFormatInfo_MimeType_Get
import libMagickNative.MagickFormatInfo_Module_Get
import libMagickNative.MagickFormatInfo_SupportsMultipleFrames_Get
import libMagickNative.MagickFormatInfo_SupportsReading_Get
import libMagickNative.MagickFormatInfo_SupportsWriting_Get
import libMagickNative.MagickInfo
import platform.posix.size_t
import platform.posix.size_tVar

internal typealias CreateListResult = CPointer<CPointerVar<ByteVar>>
internal typealias ExceptionInfoPtrVar = CPointerVar<ExceptionInfo>

internal object NativeMagickFormatInfo {
    @Throws(Exception::class)
    fun createList(): Pair<CreateListResult, size_t> = memScoped {
        val length = alloc<size_tVar>()
        val exception = alloc<ExceptionInfoPtrVar>()

        val result = MagickFormatInfo_CreateList(length.ptr, exception.ptr)

        // TODO Check exception

        return Pair(result!!, length.value)
    }

    @Throws(Exception::class)
    fun getInfo(memScope: MemScope, list: CreateListResult, index: size_t): CPointer<MagickInfo> {
        val exception = memScope.alloc<ExceptionInfoPtrVar>()

        val result = MagickFormatInfo_GetInfo(list, index, exception.ptr)

        // TODO Check exception

        return result!!
    }

    @Throws(Exception::class)
    fun GetInfoWithBlob(memScope: MemScope, data: UByteArray, length: size_t): CPointer<MagickInfo> {
        val exception = memScope.alloc<ExceptionInfoPtrVar>()
        val result = MagickFormatInfo_GetInfoWithBlob(data.toCValues(), length, exception.ptr)

        // TODO Check exception

        return result!!
    }

    inline fun CPointer<MagickInfo>.canReadMultithreaded(): Boolean =
        MagickFormatInfo_CanReadMultithreaded_Get(this).toBoolean()

    inline fun CPointer<MagickInfo>.canWriteMultithreaded(): Boolean =
        MagickFormatInfo_CanWriteMultithreaded_Get(this).toBoolean()

    inline fun CPointer<MagickInfo>.description(): String? =
        MagickFormatInfo_Description_Get(this)?.toKString()

    inline fun CPointer<MagickInfo>.format(): String? = MagickFormatInfo_Format_Get(this)?.toKString()

    inline fun CPointer<MagickInfo>.mimeType(): String? = MagickFormatInfo_MimeType_Get(this)?.toKString()

    inline fun CPointer<MagickInfo>.moduleFormat(): String? = MagickFormatInfo_Module_Get(this)?.toKString()

    inline fun CPointer<MagickInfo>.supportsMultipleFrames(): Boolean =
        MagickFormatInfo_SupportsMultipleFrames_Get(this).toBoolean()

    inline fun CPointer<MagickInfo>.supportsReading(): Boolean =
        MagickFormatInfo_SupportsReading_Get(this).toBoolean()

    inline fun CPointer<MagickInfo>.supportsWriting(): Boolean =
        MagickFormatInfo_SupportsWriting_Get(this).toBoolean()
}
