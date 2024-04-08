package imagemagick

import imagemagick.core.enums.MagickFormat
import imagemagick.exceptions.throwIfEmpty
import imagemagick.helpers.toString
import imagemagick.magicknative.NativeMagickFormatInfo
import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path
import platform.posix.warn
import kotlin.contracts.ExperimentalContracts
import imagemagick.core.MagickFormatInfo as IMagickFormatInfo

/**
 * Class that contains information about an image format.
 */
public data class MagickFormatInfo private constructor(
    override val canReadMultithreaded: Boolean,
    override val canWriteMultithreaded: Boolean,
    override val description: String?,
    override val format: MagickFormat,
    override val mimeType: String?,
    override val moduleFormat: MagickFormat,
    override val supportsMultipleFrames: Boolean,
    override val supportsReading: Boolean,
    override val supportsWriting: Boolean,
) : IMagickFormatInfo {
    @ExperimentalStdlibApi
    @ExperimentalForeignApi
    @ExperimentalContracts
    public companion object {
        @Suppress("KDocMissingDocumentation")
        public val allFormats: Map<MagickFormat, IMagickFormatInfo> by lazy {
            loadFormats()
        }

        private fun loadFormats(): Map<MagickFormat, IMagickFormatInfo> {
            val formats = mutableMapOf<MagickFormat, IMagickFormatInfo>()

            NativeMagickFormatInfo().let {
                it.createList().use { list ->
                    for (i in 0u..list.length) {
                        val (ptr, warning) = list.getInfo(i)
                        warning?.message?.let { message -> warn(message) }

                        NativeMagickFormatInfo(ptr).toMagickFormatInfo()?.let { magicFormatInfo ->
                            formats[magicFormatInfo.format] = magicFormatInfo
                        }
                    }
                }

                // AddStealthCoders(instance, formats)
                it.getInfoByName("DIB")
                it.toMagickFormatInfo()?.let { magicFormatInfo ->
                    formats[magicFormatInfo.format] = magicFormatInfo
                }

                it.getInfoByName("TIF")
                it.toMagickFormatInfo()?.let { magicFormatInfo ->
                    formats[magicFormatInfo.format] = magicFormatInfo
                }
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
                    } ?: "UNKNOWN",
                )
            } catch (e: Exception) {
                MagickFormat.UNKNOWN
            }

        private inline fun NativeMagickFormatInfo.toMagickFormatInfo(): MagickFormatInfo? =
            if (!this.hasInstance) {
                null
            } else {
                MagickFormatInfo(
                    this.canReadMultithreaded,
                    this.canWriteMultithreaded,
                    this.description,
                    formatCleaner(this.format),
                    this.mimeType,
                    formatCleaner(this.module),
                    this.supportsMultipleFrames,
                    this.supportsReading,
                    this.supportsWriting,
                )
            }

        /**
         * Returns the format information. The extension of the supplied [file] is used to determine the format.
         *
         * @param file The file to check.
         * @return The format information.
         */
        public fun create(file: Path): IMagickFormatInfo? = create(file.normalized().toString())

        /**
         * Returns the format information of the specified [format].
         *
         * @param format The image format.
         * @return The format information.
         */
        public fun create(format: MagickFormat): IMagickFormatInfo? =
            when (format) {
                MagickFormat.UNKNOWN -> null
                else -> allFormats[format]
            }

        /**
         * Returns the format information. The header of the image in the array of bytes is used to
         * determine the format. Null will be returned when the format could not be determined.
         *
         * @param data The array of bytes to read the image header from.
         *
         * @return The format information.
         */
        public fun create(data: UByteArray): IMagickFormatInfo? {
            throwIfEmpty(data)

            return NativeMagickFormatInfo().apply {
                getInfoWithBlob(data)
            }.toMagickFormatInfo()
        }

        /**
         * Returns the format information. The extension of the supplied file name is used to determine the format.
         *
         * @param fileName The name of the file to check.
         * @return The format information.
         */
        public fun create(fileName: String): IMagickFormatInfo? {
            throwIfEmpty(fileName)

            return create(formatCleaner(fileName.substringAfterLast('.')))
        }
    }

    // "{Format}: {Description} ({SupportReading}R{SupportWriting}W{SupportMultipleFrames}M)"
    override fun toString(): String =
        "$format: $description ({${supportsReading.toString("+", "-")}}R{${
            supportsWriting.toString(
                "+",
                "-",
            )
        }W{${supportsMultipleFrames.toString("+", "-")}}M)"

    override fun unregister(): Boolean {
        TODO("Not yet implemented")
    }
}
