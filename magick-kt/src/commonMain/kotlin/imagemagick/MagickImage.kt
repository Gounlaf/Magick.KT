package imagemagick

import imagemagick.core.enums.MagickFormat
import imagemagick.exceptions.Throw
import imagemagick.native.NativeMagickImage
import imagemagick.settings.MagickReadSettings
import imagemagick.settings.MagickSettings
import kotlinx.cinterop.ExperimentalForeignApi
import imagemagick.core.MagickImage as Interface
import imagemagick.core.settings.MagickReadSettings as MagickReadSettingsInterface

/**
 * Class that represents an ImageMagick image.
 *
 * @constructor Initializes a new instance of the [MagickImage] class.
 */
@ExperimentalStdlibApi
@ExperimentalForeignApi
class MagickImage() : Interface {
    private var settings: MagickSettings = MagickSettings()

    @ExperimentalStdlibApi
    private var native: NativeMagickImage = MagickSettings
        .createNativeInstance(settings).use { NativeMagickImage(it) }
        set(value) {
            field.dispose()
            field = value
        }

    constructor(data: ByteArray) : this() {
        read(data)
    }


    override val baseHeight: UInt
        get() = TODO("Not yet implemented")
    override val baseWidth: UInt
        get() = TODO("Not yet implemented")
    override val blackPointCompensation: Boolean
        get() = TODO("Not yet implemented")

    override fun read(data: ByteArray) {
        read(data, null)
    }

    // TODO put in "quantumized" interface
    @Throws(IllegalArgumentException::class)
    fun read(data: ByteArray, readSettings: MagickReadSettingsInterface?) {
        Throw.ifEmpty(data)
        read(data, 0u, data.size.toUInt(), readSettings, false)
    }

    private fun read(
        data: ByteArray,
        offset: UInt,
        lengt: UInt,
        readSettings: MagickReadSettingsInterface?,
        ping: Boolean,
        fileName: String? = null,
    ) {
//        var newReadSettings = CreateReadSettings(readSettings);
//        SetSettings(newReadSettings);

        settings.ping = ping;
        settings.fileName = fileName;

//
//        _nativeInstance.ReadBlob(Settings, data, offset, length);

        resetSettings()
    }

    private fun createReadSettings(readSettings: MagickReadSettingsInterface?)
    {
        readSettings?.frameCount?.let {
            require(it == 1u) {
                "The frame count can only be set to 1 when a single image is being read."
            }
        }

        val newReadSettings = readSettings?.let { MagickReadSettings(it) } ?: MagickReadSettings(settings)
    }

    private fun resetSettings() {
        settings.format = MagickFormat.UNKNOWN
    }
}
