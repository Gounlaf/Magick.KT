@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative

import imagemagick.bridge.canReadMultithreaded
import imagemagick.bridge.canWriteMultithreaded
import imagemagick.bridge.description
import imagemagick.bridge.format
import imagemagick.bridge.mimeType
import imagemagick.bridge.moduleFormat
import imagemagick.bridge.supportsMultipleFrames
import imagemagick.bridge.supportsReading
import imagemagick.bridge.supportsWriting
import imagemagick.core.exceptions.MagickException
import imagemagick.magicknative.exceptions.withException
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CPointerVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.alloc
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.usePinned
import kotlinx.cinterop.value
import kotlinx.cinterop.zeroValue
import libMagickNative.MagickFormatInfo_CreateList
import libMagickNative.MagickFormatInfo_DisposeList
import libMagickNative.MagickFormatInfo_GetInfo
import libMagickNative.MagickFormatInfo_GetInfoByName
import libMagickNative.MagickFormatInfo_GetInfoWithBlob
import libMagickNative.MagickInfo
import platform.posix.size_tVar
import kotlin.contracts.ExperimentalContracts

@ExperimentalStdlibApi
@ExperimentalForeignApi
public class NativeMagickFormatInfo() : ConstNativeInstance<MagickInfo>() {
    override val hasInstance: Boolean
        get() =
            try {
                ptr != zero
            } catch (e: UnsupportedOperationException) {
                false
            }

    override var ptr: CPointer<MagickInfo> = zero
        get() {
            if (field === zero) {
                throw UnsupportedOperationException()
            }

            return field
        }

    public constructor(cPointer: CPointer<MagickInfo>) : this() {
        ptr = cPointer
    }

    public inline val canReadMultithreaded: Boolean
        get() = ptr.canReadMultithreaded()

    public inline val canWriteMultithreaded: Boolean
        get() = ptr.canWriteMultithreaded()

    public inline val description: String
        get() = ptr.description()

    public inline val format: String
        get() = ptr.format()

    public inline val mimeType: String?
        get() = ptr.mimeType()

    public inline val module: String
        get() = ptr.moduleFormat()

    public inline val supportsMultipleFrames: Boolean
        get() = ptr.supportsMultipleFrames()

    public inline val supportsReading: Boolean
        get() = ptr.supportsReading()

    public inline val supportsWriting: Boolean
        get() = ptr.supportsWriting()

    @ExperimentalContracts
    public fun getInfoWithBlob(data: UByteArray) {
        checkException {
            withException { _, exceptionPtr ->
                data.usePinned {
                    MagickFormatInfo_GetInfoWithBlob(it.addressOf(0), data.size.convert(), exceptionPtr)
                }
            }
        }.selfUpdate()
    }

    @ExperimentalContracts
    public fun getInfoByName(name: String) {
        checkException {
            withException { _, exceptionPtr ->
                MagickFormatInfo_GetInfoByName(name, exceptionPtr)
            }
        }.selfUpdate()
    }

    @ExperimentalContracts
    public fun createList(): NativeMagickFormatInfoList =
        checkException {
            NativeMagickFormatInfoList.create()
        }

    private inline fun CPointer<MagickInfo>?.selfUpdate(): Unit? = this?.let { ptr = it }

    public companion object {
        internal val zero: CPointer<MagickInfo> =
            memScoped {
                zeroValue<MagickInfo>().ptr
            }
    }
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public data class NativeMagickFormatInfoList(val ptr: CPointer<CPointerVar<ByteVar>>, val length: UInt) :
    AutoCloseable {
    override fun close() {
        dispose()
    }

    public inline fun dispose(): Unit = MagickFormatInfo_DisposeList(ptr, length.convert())

    public inline fun getInfo(index: UInt): Pair<CPointer<MagickInfo>, MagickException?> =
        withException { _, exceptionPtr ->
            MagickFormatInfo_GetInfo(ptr, index.convert(), exceptionPtr)
        }

    public companion object {
        @ExperimentalStdlibApi
        @ExperimentalContracts
        @ExperimentalForeignApi
        public fun create(): Pair<NativeMagickFormatInfoList, MagickException?> =
            withException(onError = { it.dispose() }) { placement, exceptionPtr ->
                val length = placement.alloc<size_tVar>()

                MagickFormatInfo_CreateList(length.ptr, exceptionPtr)?.let {
                    NativeMagickFormatInfoList(it, length.value.toUInt())
                }
            }
    }
}
