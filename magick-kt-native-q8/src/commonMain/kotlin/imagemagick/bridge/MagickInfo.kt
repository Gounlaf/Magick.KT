@file:Suppress("KDocMissingDocumentation")

package imagemagick.bridge

import imagemagick.magicknative.exceptions.withException
import imagemagick.magicknative.toPrimitive
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

@ExperimentalForeignApi
public inline fun CPointer<MagickInfo>.canReadMultithreaded(): Boolean =
    MagickFormatInfo_CanReadMultithreaded_Get(this).toPrimitive()

@ExperimentalForeignApi
public inline fun CPointer<MagickInfo>.canWriteMultithreaded(): Boolean =
    MagickFormatInfo_CanWriteMultithreaded_Get(this).toPrimitive()

@ExperimentalForeignApi
public inline fun CPointer<MagickInfo>.description(): String? =
    MagickFormatInfo_Description_Get(this)?.toKString()

@ExperimentalForeignApi
public inline fun CPointer<MagickInfo>.format(): String? = MagickFormatInfo_Format_Get(this)?.toKString()

@ExperimentalForeignApi
public inline fun CPointer<MagickInfo>.mimeType(): String? = MagickFormatInfo_MimeType_Get(this)?.toKString()

@ExperimentalForeignApi
public inline fun CPointer<MagickInfo>.moduleFormat(): String? = MagickFormatInfo_Module_Get(this)?.toKString()

@ExperimentalForeignApi
public inline fun CPointer<MagickInfo>.supportsMultipleFrames(): Boolean =
    MagickFormatInfo_SupportsMultipleFrames_Get(this).toPrimitive()

@ExperimentalForeignApi
public inline fun CPointer<MagickInfo>.supportsReading(): Boolean =
    MagickFormatInfo_SupportsReading_Get(this).toPrimitive()

@ExperimentalForeignApi
public inline fun CPointer<MagickInfo>.supportsWriting(): Boolean =
    MagickFormatInfo_SupportsWriting_Get(this).toPrimitive()

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(Exception::class)
public inline fun UByteArray.getInfoWithBlob(): CPointer<MagickInfo>? = withException { _, exceptionPtr ->
    this.usePinned {
        MagickFormatInfo_GetInfoWithBlob(it.addressOf(0), this.size.convert(), exceptionPtr)
    }
}

@ExperimentalForeignApi
@ExperimentalStdlibApi
public data class MagickFormatInfoList(val ptr: CPointer<CPointerVar<ByteVar>>, val length: UInt) : AutoCloseable {
    override fun close() {
        dispose()
    }

    public companion object {
        @ExperimentalStdlibApi
        @ExperimentalContracts
        @ExperimentalForeignApi
        public inline fun create(): MagickFormatInfoList? = withException { placement, exceptionPtr ->
            val length = placement.alloc<size_tVar>()

            MagickFormatInfo_CreateList(length.ptr, exceptionPtr)?.let {
                MagickFormatInfoList(it, length.value.toUInt())
            }
        }
    }
}

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun MagickFormatInfoList.dispose() {
    MagickFormatInfo_DisposeList(ptr, length.convert())
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun MagickFormatInfoList.getInfo(index: UInt): CPointer<MagickInfo>? = withException { _, exceptionPtr ->
    MagickFormatInfo_GetInfo(ptr, index.convert(), exceptionPtr)
}
