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
import okio.Path
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
                                ptr.toMagickFormatInfo().also {
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
                enumValueOf<MagickFormat>(format?.filterNot { it == '-' }?.uppercase()?.let {
                    when (it) {
                        // Rename some format with text equivalent because property can't begin with a number
                        "3FR" -> "THREEFR"
                        "3G2" -> "THREEG2"
                        "3GP" -> "THREEGP"
                        else -> it
                    }
                } ?: "UNKNOWN")
            } catch (e: Exception) {
                MagickFormat.UNKNOWN
            }

        private inline fun CPointer<MagickInfo>.toMagickFormatInfo(): MagickFormatInfo = MagickFormatInfo(
            canReadMultithreaded = this.canReadMultithreaded(),
            canWriteMultithreaded = this.canWriteMultithreaded(),
            description = this.description(),
            format = formatCleaner(this.format()),
            mimeType = this.mimeType(),
            moduleFormat = formatCleaner(this.moduleFormat()),
            supportsMultipleFrames = this.supportsMultipleFrames(),
            supportsReading = this.supportsReading(),
            supportsWriting = this.supportsWriting()
        )

        /**
         * Returns the format information. The extension of the supplied [file] is used to determine the format.
         *
         * @param file The file to check.
         * @return The format information.
         */
        fun create(file: Path): Interface? = create(file.normalized().toString())

        /**
         * Returns the format information of the specified [format].
         *
         *
         * @param format The image format.
         * @return The format information.
         */
        fun create(format: MagickFormat): Interface? = when (format) {
            MagickFormat.UNKNOWN -> null
            else -> allFormats[format]
        }

        @Throws(IllegalArgumentException::class)
        fun create(data: ByteArray): Interface? {
            require(data.isNotEmpty())

            val uData = data.toUByteArray()

            return memScoped {
                try {
                    NativeMagickFormatInfo.GetInfoWithBlob(memScope, uData, uData.size.convert()).toMagickFormatInfo()
                } catch (e: Exception) {
                    null
                }
            }
        }

        /**
         * Returns the format information. The extension of the supplied file name is used to determine the format.
         *
         * @param fileName The name of the file to check.
         * @return The format information.
         */
        fun create(fileName: String): Interface? = create(formatCleaner(fileName.substringAfterLast('.')))
    }
}
