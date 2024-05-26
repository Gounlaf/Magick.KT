package imagemagick.helpers

import imagemagick.MagickImage
import imagemagick.QuantumType
import imagemagick.core.MagickImageQuantum
import imagemagick.core.enums.MagickFormat
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.contracts.ExperimentalContracts
import kotlin.experimental.ExperimentalNativeApi

@ExperimentalForeignApi
internal class TemporaryMagickFormat : AutoCloseable {
    private val formatInfos: MutableList<MagickFormatData> = mutableListOf()

    @ExperimentalStdlibApi
    @ExperimentalContracts
    @ExperimentalNativeApi
    constructor(image: MagickImage, format: MagickFormat) {
        addImage(image, format)
    }

//    constructor(images: MagickImageCollection, format: MagickFormat) {
//        images.forEach {
//            addImage(it, format)
//        }
//    }

    private fun addImage(
        image: MagickImageQuantum<QuantumType>,
        format: MagickFormat,
    ) {
        formatInfos.add(MagickFormatData(image))
        image.format = format
    }

    override fun close() {
        formatInfos.forEach { it.restoreOriginalFormat() }
    }

    @ExperimentalForeignApi
    private class MagickFormatData {
        val image: MagickImageQuantum<QuantumType>
        val originalImageFormat: MagickFormat
        val originalSettingsFormat: MagickFormat

        constructor(image: MagickImageQuantum<QuantumType>) {
            this.image = image
            originalImageFormat = image.format
            originalSettingsFormat = image.settings.format
        }

        fun restoreOriginalFormat() {
            image.format = originalImageFormat
            // We need to set the format of the settings because it is possible that this was different
            // from the format of the image. And the Format property of the image also changes the settings.
            image.settings.format = originalSettingsFormat
        }
    }
}
