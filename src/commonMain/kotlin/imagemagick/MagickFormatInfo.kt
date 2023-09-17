package imagemagick

import imagemagick.core.enums.MagickFormat
import imagemagick.helpers.Environment
import imagemagick.native.NativeMagickFormatInfo
import imagemagick.native.NativeMagickFormatInfo.canReadMultithreaded
import imagemagick.native.NativeMagickFormatInfo.canWriteMultithreaded
import imagemagick.native.NativeMagickFormatInfo.description
import imagemagick.native.NativeMagickFormatInfo.format
import imagemagick.native.NativeMagickFormatInfo.mimeType
import imagemagick.native.NativeMagickFormatInfo.moduleFormat
import imagemagick.native.NativeMagickFormatInfo.supportsMultipleFrames
import imagemagick.native.NativeMagickFormatInfo.supportsReading
import imagemagick.native.NativeMagickFormatInfo.supportsWriting
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import libMagickNative.MagickInfo
import platform.posix.free
import platform.posix.size_t
import imagemagick.core.MagickFormatInfo as Interface

class MagickFormatInfo private constructor(
    override val canReadMultithreaded: Boolean,
    override val canWriteMultithreaded: Boolean,
    override val description: String?,
    override val format: MagickFormat,
    override val mimeType: String?,
    override val moduleFormat: MagickFormat,
    override val supportsMultipleFrames: Boolean,
    override val supportsReading: Boolean,
    override val supportsWriting: Boolean,
) : Interface {
    companion object {
        internal val allFormats: Map<MagickFormat, Interface>

        init {
            println("Environment_Initialize")
            Environment.initialize()

            allFormats = loadFormats()
        }

        private fun loadFormats(): Map<MagickFormat, Interface> {
            val formats = mutableMapOf<MagickFormat, Interface>()

            try {
                val (list, length) = NativeMagickFormatInfo.createList()

                for (i in 0.convert<size_t>()..length) {
                    memScoped {
                        NativeMagickFormatInfo.getInfo(memScope, list, i).let { ptr ->
                            try {
                                ctor(ptr).also {
                                    formats[it.format] = it
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                }

                free(list)
            } catch (e: Exception) {
                println()
            }

            return formats
        }

        private fun formatCleaner(format: String?): MagickFormat =
            try {
                enumValueOf<MagickFormat>(format?.filterNot { it == '-' }?.let {
                    when (it) {
                        "3FR" -> "THREEFR"
                        "3G2" -> "THREEG2"
                        "3GP" -> "THREEGP"
                        else -> it
                    }
                } ?: "UNKNOWN")
            } catch (e: Exception) {
                MagickFormat.UNKNOWN
            }

        private fun ctor(ptr: CPointer<MagickInfo>): MagickFormatInfo = MagickFormatInfo(
            canReadMultithreaded = ptr.canReadMultithreaded(),
            canWriteMultithreaded = ptr.canWriteMultithreaded(),
            description = ptr.description(),
            format = formatCleaner(ptr.format()),
            mimeType = ptr.mimeType(),
            moduleFormat = formatCleaner(ptr.moduleFormat()),
            supportsMultipleFrames = ptr.supportsMultipleFrames(),
            supportsReading = ptr.supportsReading(),
            supportsWriting = ptr.supportsWriting()
        )
    }
}
