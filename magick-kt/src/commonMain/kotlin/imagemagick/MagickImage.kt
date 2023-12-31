package imagemagick

import imagemagick.core.colors.MagickColorQuantum
import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.CompressionMethod
import imagemagick.core.enums.Interlace
import imagemagick.core.enums.MagickFormat
import imagemagick.core.types.Density
import imagemagick.core.exceptions.MagickException
import imagemagick.exceptions.Throw
import imagemagick.native.NativeMagickImage
import imagemagick.settings.MagickReadSettings
import imagemagick.settings.MagickSettings
import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path
import okio.Source
import okio.buffer
import imagemagick.core.MagickImageQuantum as IMagickImage
import imagemagick.core.settings.MagickReadSettings as IMagickReadSettings

/**
 * Class that represents an ImageMagick image.
 *
 * @constructor Initializes a new instance of the [MagickImage] class.
 */
@ExperimentalStdlibApi
@ExperimentalForeignApi
class MagickImage() : IMagickImage<QuantumType>, AutoCloseable {
    private var settings: MagickSettings = MagickSettings()

    @ExperimentalStdlibApi
    private var native: NativeMagickImage = MagickSettings.createNativeInstance(settings).use { NativeMagickImage(it) }


    /**
     * Initializes a new instance of the [MagickImage] class.
     *
     * @param data The byte array to read the image data from.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    constructor(data: UByteArray) : this() {
        read(data, null)
    }

    /**
     * Initializes a new instance of the [MagickImage] class.
     *
     * @param data The byte array to read the image data from.
     * @param offset The offset at which to begin reading data.
     * @param count The maximum number of bytes to read.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    constructor(data: UByteArray, offset: UInt, count: UInt) : this() {
        read(data, offset, count)
    }

    /**
     * Initializes a new instance of the [MagickImage] class.
     *
     * @param data The byte array to read the image data from.
     * @param offset The offset at which to begin reading data.
     * @param count The maximum number of bytes to read.
     * @param format The format to use.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    constructor(data: UByteArray, offset: UInt, count: UInt, format: MagickFormat) : this() {
        read(data, offset, count, format)
    }

    /**
     * Initializes a new instance of the [MagickImage] class.
     *
     * @param data The byte array to read the image data from.
     * @param offset The offset at which to begin reading data.
     * @param count The maximum number of bytes to read.
     * @param readSettings The settings to use when reading the image.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    constructor(data: UByteArray, offset: UInt, count: UInt, readSettings: IMagickReadSettings<QuantumType>) : this() {
        read(data, offset, count, readSettings)
    }

    /**
     * Initializes a new instance of the [MagickImage] class.
     *
     * @param data The byte array to read the image data from.
     * @param format The format to use.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    constructor(data: UByteArray, format: MagickFormat) : this() {
        read(data, format)
    }

    /**
     * Initializes a new instance of the [MagickImage] class.
     *
     * @param data The byte array to read the image data from.
     * @param readSettings The settings to use when reading the image.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    constructor(data: UByteArray, readSettings: IMagickReadSettings<QuantumType>) : this() {
        read(data, readSettings)
    }


    override fun close() {
        native.dispose()
    }

    private fun createReadSettings(readSettings: IMagickReadSettings<QuantumType>?): MagickReadSettings {
        readSettings?.frameCount?.let {
            require(it == 1u) {
                "The frame count can only be set to 1 when a single image is being read."
            }
        }

        return (readSettings?.let { MagickReadSettings(it) } ?: MagickReadSettings(settings)).apply {
            forceSingleFrame()
        }
    }

    private fun resetSettings() {
        settings.format = MagickFormat.UNKNOWN
    }

    override var backgroundColor: MagickColorQuantum<QuantumType>?
        get() = TODO("Not yet implemented")
        set(value) {}

    override var colorSpace: ColorSpace
        get() = TODO("Not yet implemented")
        set(value) {}

    override val compression: CompressionMethod
        get() = TODO("Not yet implemented")

    override var density: Density
        get() = TODO("Not yet implemented")
        set(value) {}

    override val fileName: String?
        get() = TODO("Not yet implemented")

    override var format: MagickFormat
        get() = TODO("Not yet implemented")
        set(value) {}

    override val height: UInt
        get() = TODO("Not yet implemented")

    override var interlace: Interlace
        get() = TODO("Not yet implemented")
        set(value) {}

    override var quality: UInt
        get() = TODO("Not yet implemented")
        set(value) {}

    override val width: UInt
        get() = TODO("Not yet implemented")

    override fun ping(data: UByteArray) = ping(data, null)

    override fun ping(data: UByteArray, offset: UInt, count: UInt) = ping(data, offset, count, null)

    override fun ping(data: UByteArray, offset: UInt, count: UInt, readSettings: IMagickReadSettings<QuantumType>?) {
        Throw.ifEmpty(data)
        Throw.ifTrue("count", count < 1u, "The number of bytes should be at least 1.")
        Throw.ifTrue("offset", offset.toInt() >= data.size, "The offset should not exceed the length of the array.")
        Throw.ifTrue(
            "count",
            (offset + count).toInt() > data.size,
            "The number of bytes should not exceed the length of the array."
        )

        read(data, offset, count, readSettings, true)
    }

    override fun ping(data: UByteArray, readSettings: IMagickReadSettings<QuantumType>?) {
        Throw.ifEmpty(data)

        ping(data, 0u, data.size.toUInt(), readSettings)
    }

    override fun ping(file: Path) = ping(file, null)

    override fun ping(file: Path, readSettings: IMagickReadSettings<QuantumType>?) =
        read(file.toString(), readSettings, true)

    override fun ping(stream: Source) = ping(stream, null)

    override fun ping(stream: Source, readSettings: IMagickReadSettings<QuantumType>?) {
        read(stream, readSettings, true)
    }

    override fun ping(fileName: String) = ping(fileName, null)

    override fun ping(fileName: String, readSettings: IMagickReadSettings<QuantumType>?) =
        read(fileName, readSettings, true)

    // read UByteArray

    override fun read(data: UByteArray) = read(data, null)

    override fun read(data: UByteArray, offset: UInt, count: UInt) = read(data, offset, count, null)

    override fun read(data: UByteArray, offset: UInt, count: UInt, format: MagickFormat) =
        read(data, offset, count, MagickReadSettings(settings).also {
            it.format = format
        })

    override fun read(data: UByteArray, offset: UInt, count: UInt, readSettings: IMagickReadSettings<QuantumType>?) {
        Throw.ifEmpty(data)
        Throw.ifTrue("count", count < 1u, "The number of bytes should be at least 1.")
        Throw.ifTrue("offset", offset.toInt() >= data.size, "The offset should not exceed the length of the array.")
        Throw.ifTrue(
            "count",
            (offset + count).toInt() > data.size,
            "The number of bytes should not exceed the length of the array."
        )
    }

    override fun read(data: UByteArray, format: MagickFormat) {
        Throw.ifEmpty(data)

        read(data, 0u, data.size.toUInt(), MagickReadSettings(settings).also {
            it.format = format
        })
    }

    override fun read(data: UByteArray, readSettings: IMagickReadSettings<QuantumType>?) {
        Throw.ifEmpty(data)
        read(data, 0u, data.size.toUInt(), readSettings, false)
    }

    // /read UByteArray

    // read Path

    override fun read(file: Path) = read(file, null)

    override fun read(file: Path, width: UInt, height: UInt) = read(file.name, width, height)

    override fun read(file: Path, format: MagickFormat) = read(file.name, MagickReadSettings(settings).also {
        it.format = format
    })

    override fun read(file: Path, readSettings: IMagickReadSettings<QuantumType>?) = read(file.name, readSettings)

    // /read Path

    override fun read(color: MagickColorQuantum<QuantumType>, width: UInt, height: UInt) {
        read("xc:${color.toShortString()}", width, height)
        backgroundColor = color
    }

    // read Stream

    override fun read(stream: Source) = read(stream, MagickFormat.UNKNOWN)

    override fun read(stream: Source, format: MagickFormat) = read(stream, MagickReadSettings(settings).also {
        it.format = format
    })

    override fun read(stream: Source, readSettings: IMagickReadSettings<QuantumType>?) =
        read(stream, readSettings, false)

    // read /Stream

    // read FileName

    override fun read(fileName: String) = read(fileName, null)

    override fun read(fileName: String, width: UInt, height: UInt) = read(fileName, MagickReadSettings(settings).also {
        it.width = width
        it.height = height
    })

    override fun read(fileName: String, format: MagickFormat) = read(fileName, MagickReadSettings(settings).also {
        it.format = format
    })

    override fun read(fileName: String, readSettings: IMagickReadSettings<QuantumType>?) =
        read(fileName, readSettings, false)

    // /read FileName

    private fun read(
        data: UByteArray,
        offset: UInt,
        length: UInt,
        readSettings: IMagickReadSettings<QuantumType>?,
        ping: Boolean,
        fileName: String? = null,
    ) {
        val newReadSettings = createReadSettings(readSettings);
        settings = newReadSettings

        settings.ping = ping
        settings.fileName = fileName


        settings.createNativeInstance().use {
            native.readBlob(it, data, offset = offset, length = length)
        }

        resetSettings()
    }

    private fun read(stream: Source, readSettings: IMagickReadSettings<QuantumType>?, ping: Boolean) {
//        Throw.IfNullOrEmpty(nameof(stream), stream);
//
//        var bytes = Bytes.FromStreamBuffer(stream);
//        if (bytes is not null)
//        {
//            Read(bytes.GetData(), 0, bytes.Length, readSettings, ping);
//            return;
//        }

        val newReadSettings = createReadSettings(readSettings);
        settings = newReadSettings

        settings.ping = ping
        settings.fileName = null

        native.readStream(stream.buffer(), settings)
    }

    private fun read(fileName: String, readSettings: IMagickReadSettings<QuantumType>?, ping: Boolean) {
        val newReadSettings = createReadSettings(readSettings);
        settings = newReadSettings

        settings.fileName = fileName
        settings.ping = ping

        settings.createNativeInstance().use {
            native.readFile(it)
        }

        resetSettings()
    }
}
