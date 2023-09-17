package imagemagick

import imagemagick.core.MagickFormatInfo
import imagemagick.core.MagickKT as Interface
import kotlinx.cinterop.toKString
import libMagickNative.Magick_Features_Get

object MagickKT : Interface {
    override val features: String by lazy { Magick_Features_Get()?.toKString() ?: "Unknown" }

    override val supportedFormats: List<MagickFormatInfo> by lazy {
        imagemagick.MagickFormatInfo.allFormats.values.toList()
    }
}
