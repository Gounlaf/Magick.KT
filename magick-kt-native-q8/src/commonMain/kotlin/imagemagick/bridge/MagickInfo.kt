@file:Suppress("KDocMissingDocumentation")

package imagemagick.bridge

import imagemagick.magicknative.toNonNullKString
import imagemagick.magicknative.toPrimitive
import kotlinx.cinterop.ByteVarOf
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CPointerVarOf
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toKString
import libMagickNative.MagickFormatInfo_CanReadMultithreaded_Get
import libMagickNative.MagickFormatInfo_CanWriteMultithreaded_Get
import libMagickNative.MagickFormatInfo_Description_Get
import libMagickNative.MagickFormatInfo_Format_Get
import libMagickNative.MagickFormatInfo_MimeType_Get
import libMagickNative.MagickFormatInfo_Module_Get
import libMagickNative.MagickFormatInfo_SupportsMultipleFrames_Get
import libMagickNative.MagickFormatInfo_SupportsReading_Get
import libMagickNative.MagickFormatInfo_SupportsWriting_Get
import libMagickNative.MagickInfo
import platform.posix.size_t

@ExperimentalForeignApi
public inline val CPointer<MagickInfo>.canReadMultithreaded: Boolean get() = MagickFormatInfo_CanReadMultithreaded_Get(this).toPrimitive()

@ExperimentalForeignApi
public inline val CPointer<MagickInfo>.canWriteMultithreaded: Boolean get() = MagickFormatInfo_CanWriteMultithreaded_Get(this).toPrimitive()

@ExperimentalForeignApi
public inline val CPointer<MagickInfo>.description: String get() = MagickFormatInfo_Description_Get(this).toNonNullKString()

@ExperimentalForeignApi
public inline val CPointer<MagickInfo>.format: String get() = MagickFormatInfo_Format_Get(this).toNonNullKString()

@ExperimentalForeignApi
public inline val CPointer<MagickInfo>.mimeType: String? get() = MagickFormatInfo_MimeType_Get(this)?.toKString()

@ExperimentalForeignApi
public inline val CPointer<MagickInfo>.moduleFormat: String get() = MagickFormatInfo_Module_Get(this).toNonNullKString()

@ExperimentalForeignApi
public inline val CPointer<MagickInfo>.supportsMultipleFrames: Boolean get() =
    MagickFormatInfo_SupportsMultipleFrames_Get(
        this,
    ).toPrimitive()

@ExperimentalForeignApi
public inline val CPointer<MagickInfo>.supportsReading: Boolean get() = MagickFormatInfo_SupportsReading_Get(this).toPrimitive()

@ExperimentalForeignApi
public inline val CPointer<MagickInfo>.supportsWriting: Boolean get() = MagickFormatInfo_SupportsWriting_Get(this).toPrimitive()

@ExperimentalForeignApi
public typealias MagickFormatInfo_CreateList_Result = CPointer<CPointerVarOf<CPointer<ByteVarOf<Byte>>>>

@ExperimentalForeignApi
public data class MagickFormatInfoCreateListResult(
    val ptr: CPointer<CPointerVarOf<CPointer<ByteVarOf<Byte>>>>?,
    val size: size_t,
)
