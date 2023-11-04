package imagemagick

import imagemagick.core.enums.MagickFormat
import imagemagick.helpers.toString
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
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.MagickInfo
import okio.Path
import platform.posix.free
import imagemagick.core.MagickFormatInfo as Interface

data class MagickFormatInfo private constructor(
    override val canReadMultithreaded: Boolean,
    override val canWriteMultithreaded: Boolean,
    override val description: String?,
    override val format: MagickFormat,
    override val mimeType: String?,
    override val moduleFormat: MagickFormat,
    override val supportsMultipleFrames: Boolean,
    override val supportsReading: Boolean,
    override val supportsWriting: Boolean
) : Interface {
    @ExperimentalStdlibApi
    @ExperimentalForeignApi
    companion object {
        internal val allFormats: Map<MagickFormat, Interface> by lazy {
            loadFormats()
        }

        private fun loadFormats(): Map<MagickFormat, Interface> {
            val formats = mutableMapOf<MagickFormat, Interface>()

            NativeMagickFormatInfo.createList()?.use { list ->
                for (i in 0u..list.length) {
                    NativeMagickFormatInfo.getInfo(list, i)?.let { ptr ->
                        ptr.toMagickFormatInfo().also {
                            formats[it.format] = it
                        }
                    }
                }

                // TODO AddStealthCoders(instance, formats)
            }

            return formats
        }

        private fun formatCleaner(format: String?): MagickFormat =
            try {
                enumValueOf<MagickFormat>(
                    format?.filterNot { it == '-' }?.uppercase()?.let {
                        when (it) {
                            // Rename some format with text equivalent because property can't begin with a number
                            "3FR" -> "THREEFR"
                            "3G2" -> "THREEG2"
                            "3GP" -> "THREEGP"
                            else -> it
                        }
                    } ?: "UNKNOWN"
                )
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
         * @param format The image format.
         * @return The format information.
         */
        fun create(format: MagickFormat): Interface? = when (format) {
            MagickFormat.UNKNOWN -> null
            else -> allFormats[format]
        }

        @Throws(IllegalArgumentException::class)
        fun create(data: UByteArray): Interface? {
            require(data.isNotEmpty())

            return NativeMagickFormatInfo.getInfoWithBlob(data)?.let {
                val info = it.toMagickFormatInfo()

                free(it)

                info
            }
        }

        /**
         * Returns the format information. The extension of the supplied file name is used to determine the format.
         *
         * @param fileName The name of the file to check.
         * @return The format information.
         */
        fun create(fileName: String): Interface? {
            require(fileName.isNotBlank()) // TODO Set error message
            return create(formatCleaner(fileName.substringAfterLast('.')))
        }
    }

    // "{Format}: {Description} ({SupportReading}R{SupportWriting}W{SupportMultipleFrames}M)"
    override fun toString(): String = "$format: $description ({${supportsReading.toString("+", "-")}}R{${
    supportsWriting.toString(
        "+",
        "-"
    )
    }W{${supportsMultipleFrames.toString("+", "-")}}M)"

    override fun unregister(): Boolean {
        TODO("Not yet implemented")
    }
}
