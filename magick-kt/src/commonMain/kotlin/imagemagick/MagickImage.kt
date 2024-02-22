package imagemagick

import imagemagick.colors.MagickColor.Companion.toMagick
import imagemagick.colors.MagickColor.Companion.toNative
import imagemagick.core.MagickImageQuantum
import imagemagick.core.colors.MagickColorQuantum
import imagemagick.core.drawables.DrawableAffine
import imagemagick.core.enums.AlphaOption
import imagemagick.core.enums.AutoThresholdMethod
import imagemagick.core.enums.Channels
import imagemagick.core.enums.ClassType
import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.ColorType
import imagemagick.core.enums.CompositeOperator
import imagemagick.core.enums.CompressionMethod
import imagemagick.core.enums.Endian
import imagemagick.core.enums.ErrorMetric
import imagemagick.core.enums.FilterType
import imagemagick.core.enums.GifDisposeMethod
import imagemagick.core.enums.Gravity
import imagemagick.core.enums.Interlace
import imagemagick.core.enums.MagickFormat
import imagemagick.core.enums.NoiseType
import imagemagick.core.enums.OrientationType
import imagemagick.core.enums.PixelChannel
import imagemagick.core.enums.PixelInterpolateMethod
import imagemagick.core.enums.RenderingIntent
import imagemagick.core.enums.VirtualPixelMethod
import imagemagick.core.exceptions.MagickException
import imagemagick.core.settings.CompareSettingsQuantum
import imagemagick.core.types.Density
import imagemagick.core.types.Percentage
import imagemagick.exceptions.MagickErrorException
import imagemagick.exceptions.throwIfEmpty
import imagemagick.exceptions.throwIfNegative
import imagemagick.exceptions.throwIfTrue
import imagemagick.helpers.PercentageHelper
import imagemagick.helpers.TemporaryDefines
import imagemagick.helpers.enumValueOf
import imagemagick.magicknative.NativeMagickImage
import imagemagick.matrices.DoubleMatrix
import imagemagick.settings.CompareSettings
import imagemagick.settings.MagickReadSettings
import imagemagick.settings.MagickSettings
import imagemagick.types.ChromaticityInfo
import imagemagick.types.MagickErrorInfo
import imagemagick.types.MagickGeometry
import imagemagick.types.MagickRectangle
import imagemagick.types.MagickRectangle.Companion.toNative
import imagemagick.types.PrimaryInfo.Companion.toMagick
import imagemagick.types.PrimaryInfo.Companion.toNative
import kotlin.contracts.ExperimentalContracts
import kotlin.experimental.ExperimentalNativeApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import okio.Path
import okio.Source
import okio.buffer
import imagemagick.core.MagickImage as IMagickImage
import imagemagick.core.MagickImageQuantum as IMagickImageQ
import imagemagick.core.colors.MagickColorQuantum as IMagickColor
import imagemagick.core.matrices.MagickColorMatrix as IMagickColorMatrix
import imagemagick.core.settings.MagickReadSettings as IMagickReadSettings
import imagemagick.core.types.ChromaticityInfo as IChromaticityInfo
import imagemagick.core.types.MagickErrorInfo as IMagickErrorInfo
import imagemagick.core.types.MagickGeometry as IMagickGeometry

/**
 * Class that represents an ImageMagick image.
 *
 * @constructor Initializes a new instance of the [MagickImage] class.
 */
@ExperimentalNativeApi
@ExperimentalContracts
@ExperimentalStdlibApi
@ExperimentalForeignApi
public class MagickImage : IMagickImageQ<QuantumType>, AutoCloseable {
    public override var settings: MagickSettings
        private set

    private var nativeInstance: NativeMagickImage

    public constructor() {
        settings = MagickSettings()
        nativeInstance = MagickSettings.createNativeInstance(settings).use { NativeMagickImage(it) }
    }

    /**
     * Initializes a new instance of the [MagickImage] class.
     *
     * @param data The byte array to read the image data from.
     *
     * @throws MagickException Thrown when an error is raised by ImageMagick.
     */
    @Throws(MagickException::class)
    public constructor(data: UByteArray) : this() {
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
    public constructor(data: UByteArray, offset: UInt, count: UInt) : this() {
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
    public constructor(data: UByteArray, offset: UInt, count: UInt, format: MagickFormat) : this() {
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
    public constructor(
        data: UByteArray,
        offset: UInt,
        count: UInt,
        readSettings: IMagickReadSettings<QuantumType>,
    ) : this() {
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
    public constructor(data: UByteArray, format: MagickFormat) : this() {
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
    public constructor(data: UByteArray, readSettings: IMagickReadSettings<QuantumType>) : this() {
        read(data, readSettings)
    }

    public constructor(color: IMagickColor<QuantumType>, width: UInt, height: UInt) : this() {
        read(color, width, height)
    }

    public constructor(image: IMagickImageQ<QuantumType>) {
        if (image is MagickImage) {
            settings = image.settings.clone()
            nativeInstance = image.nativeInstance.clone()
        } else {
            throw UnsupportedOperationException()
        }
    }

    override fun close() {
        nativeInstance.dispose()
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

    override val artifactNames: Sequence<String>
        get() = sequence {
            nativeInstance.resetArtifactIterator()
            var name = nativeInstance.getNextArtifactName()
            while (name != null) {
                yield(name)
                name = nativeInstance.getNextArtifactName()
            }
        }

    override val attributeNames: Sequence<String>
        get() = sequence {
            nativeInstance.resetAttributeIterator()
            var name = nativeInstance.getNextAttributeName()
            while (name != null) {
                yield(name)
                name = nativeInstance.getNextAttributeName()
            }
        }

    override var animationDelay: UInt
        get() = nativeInstance.animationDelay
        set(value) {
            nativeInstance.animationDelay = value
        }

    override var animationIterations: UInt
        get() = nativeInstance.animationIterations
        set(value) {
            nativeInstance.animationIterations = value
        }

    override var animationTicksPerSecond: UInt
        get() = nativeInstance.animationTicksPerSecond
        set(value) {
            nativeInstance.animationTicksPerSecond = value
        }

    override var backgroundColor: IMagickColor<QuantumType>?
        get() = nativeInstance.backgroundColor.toMagick()
        set(value) {
            nativeInstance.backgroundColor = value.toNative()
            settings.backgroundColor = value
        }

    override val baseHeight: UInt
        get() = nativeInstance.baseHeight

    override val baseWidth: UInt
        get() = nativeInstance.baseWidth

    override var blackPointCompensation: Boolean
        get() = nativeInstance.blackPointCompensation
        set(value) {
            nativeInstance.blackPointCompensation = value
        }

    override var borderColor: MagickColorQuantum<QuantumType>?
        get() = nativeInstance.borderColor.toMagick()
        set(value) {
            nativeInstance.borderColor = value.toNative()
        }

    override val boundingBox: IMagickGeometry?
        get() {
            val boundingBox = nativeInstance.boundingBox

            return if (boundingBox.width == 0u || boundingBox.height == 0u) {
                null
            } else {
                MagickGeometry.fromRectangle(boundingBox)
            }
        }

    override val channelCount: UInt
        get() = nativeInstance.channelCount

    override val channels: Sequence<PixelChannel>
        get() =
            sequence {
                listOf(
                    PixelChannel.RED,
                    PixelChannel.GREEN,
                    PixelChannel.BLUE,
                    PixelChannel.BLACK,
                    PixelChannel.ALPHA,
                    PixelChannel.INDEX,
                ).forEach {
                    if (nativeInstance.hasChannel(it)) {
                        yield(it)
                    }
                }

                enumValues<PixelChannel>()
                    .filter { it.channel >= PixelChannel.META10.channel && it.channel <= PixelChannel.META52.channel }
                    .forEach {
                        if (nativeInstance.hasChannel(it)) {
                            yield(it)
                        } else {
                            return@sequence
                        }
                    }
            }

    override var chromaticity: IChromaticityInfo
        get() {
            val red = nativeInstance.chromaRed ?: throw MagickErrorException("Unable to allocate primary info")
            val green = nativeInstance.chromaGreen ?: throw MagickErrorException("Unable to allocate primary info")
            val blue = nativeInstance.chromaBlue ?: throw MagickErrorException("Unable to allocate primary info")
            val white = nativeInstance.chromaWhite ?: throw MagickErrorException("Unable to allocate primary info")

            return ChromaticityInfo(red.toMagick(), green.toMagick(), blue.toMagick(), white.toMagick())
        }
        set(value) {
            nativeInstance.chromaRed = value.red.toNative()
            nativeInstance.chromaGreen = value.green.toNative()
            nativeInstance.chromaBlue = value.blue.toNative()
            nativeInstance.chromaWhite = value.white.toNative()
        }

    override var classType: ClassType
        get() = nativeInstance.classType
        set(value) {
            nativeInstance.classType = value
        }

    override var colorFuzz: Percentage
        get() = PercentageHelper.fromQuantum(nativeInstance.colorFuzz)
        set(value) {
            val quantumValue = PercentageHelper.toQuantum(value)
            nativeInstance.colorFuzz = quantumValue
            settings.colorFuzz = quantumValue
        }

    override var colormapSize: UInt
        get() = nativeInstance.colormapSize
        set(value) {
            nativeInstance.colormapSize = value
        }

    override var colorSpace: ColorSpace
        get() = nativeInstance.colorSpace
        set(value) {
            nativeInstance.colorSpace = value
        }

    override var colorType: ColorType
        get() {
            if (settings.colorType != ColorType.UNDEFINED) {
                return settings.colorType
            }

            return nativeInstance.colorType
        }
        set(value) {
            // https://github.com/dlemstra/Magick.NET/discussions/1543#discussioncomment-8381106
            nativeInstance.colorType = value
        }

    override var comment: String?
        get() = getAttribute("comment")
        set(value) {
            if (value != null) {
                setAttribute("comment", value)
            } else {
                removeAttribute("comment")
            }
        }

    override var compose: CompositeOperator
        get() = nativeInstance.compose
        set(value) {
            nativeInstance.compose = value
        }

    override val compression: CompressionMethod
        get() = nativeInstance.compression

    override var density: Density
        get() = Density(nativeInstance.resolutionX, nativeInstance.resolutionY, nativeInstance.resolutionUnits)
        set(value) {
            nativeInstance.resolutionX = value.x
            nativeInstance.resolutionY = value.y
            nativeInstance.resolutionUnits = value.units
        }

    override var depth: UInt
        get() = nativeInstance.depth.toUInt()
        set(value) {
            nativeInstance.depth = value.convert()
        }

    override val encodingGeometry: IMagickGeometry?
        get() = MagickGeometry.fromString(nativeInstance.encodingGeometry)

    override var endian: Endian
        get() = nativeInstance.endian
        set(value) {
            nativeInstance.endian = value
        }

    override val fileName: String?
        get() = nativeInstance.fileName

    override var filterType: FilterType
        get() = nativeInstance.filterType
        set(value) {
            nativeInstance.filterType = value
        }

    override var format: MagickFormat
        get() = enumValueOf(nativeInstance.format?.uppercase(), MagickFormat.UNKNOWN)
        set(value) {
            nativeInstance.format = value.name
            settings.format = value
        }

    override val gamma: Double
        get() = nativeInstance.gamma

    override var gifDisposeMethod: GifDisposeMethod
        get() = nativeInstance.gifDisposeMethod
        set(value) {
            nativeInstance.gifDisposeMethod = value
        }

    override var hasAlpha: Boolean
        get() = nativeInstance.hasAlpha
        set(value) {
            if (nativeInstance.hasAlpha != value) {
                if (value) {
                    TODO("Alpha(AlphaOption.Opaque)")
                }
                nativeInstance.hasAlpha = value
            }
        }

    override val height: UInt
        get() = nativeInstance.height

    override val interlace: Interlace
        get() = nativeInstance.interlace

    override var interpolate: PixelInterpolateMethod
        get() = nativeInstance.interpolate
        set(value) {
            nativeInstance.interpolate = value
        }

    override val isOpaque: Boolean
        get() = nativeInstance.isOpaque

    override var label: String?
        get() = getAttribute("label")
        set(value) {
            if (value != null) {
                setAttribute("label", value)
            } else {
                removeAttribute("label")
            }
        }

    override var matteColor: MagickColorQuantum<QuantumType>?
        get() = nativeInstance.matteColor.toMagick()
        set(value) {
            nativeInstance.matteColor = value.toNative()
        }

    override var orientation: OrientationType
        get() = nativeInstance.orientation
        set(value) {
            nativeInstance.orientation = value
        }

    override var page: IMagickGeometry
        get() {
            return nativeInstance.page?.let {
                MagickGeometry.fromRectangle(it)
            } ?: throw MagickErrorException("Unable to allocate rectangle")
        }
        set(value) {
            nativeInstance.page = MagickRectangle.fromGeometry(value, this).toNative()
        }

    override val profileNames: Sequence<String>
        get() = sequence {
            nativeInstance.resetProfileIterator()
            var name = nativeInstance.getNextProfileName()
            while (name != null) {
                yield(name)
                name = nativeInstance.getNextProfileName()
            }
        }

    override var quality: UInt
        get() = nativeInstance.quality.toUInt()
        set(value) {
            var bounded = if (value < 1u) 1u else value
            bounded = if (bounded > 100u) 100u else bounded

            nativeInstance.quality = bounded.convert()
            settings.quality = bounded.convert()
        }

    override var renderingIntent: RenderingIntent
        get() = nativeInstance.renderingIntent
        set(value) {
            nativeInstance.renderingIntent = value
        }

    override val signature: String
        get() = nativeInstance.signature

    override val totalColors: UInt
        get() = nativeInstance.totalColors

    override var virtualPixelMethod: VirtualPixelMethod
        get() = nativeInstance.virtualPixelMethod
        set(value) {
            nativeInstance.virtualPixelMethod = value
        }

    override val width: UInt
        get() = nativeInstance.width


    override fun adaptiveBlur(radius: Double, sigma: Double): Unit = nativeInstance.adaptiveBlur(radius, sigma)

    override fun adaptiveResize(width: UInt, height: UInt): Unit =
        nativeInstance.adaptiveResize(MagickGeometry(width, height))

    override fun adaptiveResize(geometry: IMagickGeometry): Unit = nativeInstance.adaptiveResize(geometry)

    override fun adaptiveSharpen(radius: Double, sigma: Double, channels: Channels): Unit =
        nativeInstance.adaptiveSharpen(radius, sigma, channels)

    override fun adaptiveThreshold(width: UInt, height: UInt, bias: Double, channels: Channels): Unit =
        nativeInstance.adaptiveThreshold(width, height, bias, channels)

    override fun adaptiveThreshold(width: UInt, height: UInt, biasPercentage: Percentage, channels: Channels): Unit =
        adaptiveThreshold(width, height, PercentageHelper.toQuantum(biasPercentage), channels)

    override fun addNoise(noiseType: NoiseType, attenuate: Double, channels: Channels): Unit =
        nativeInstance.addNoise(noiseType, attenuate, channels)

    override fun affineTransform(affineMatrix: DrawableAffine): Unit = nativeInstance.affineTransform(
        affineMatrix.scaleX,
        affineMatrix.scaleY,
        affineMatrix.shearX,
        affineMatrix.shearY,
        affineMatrix.translateX,
        affineMatrix.translateY
    )

    override fun alpha(value: AlphaOption): Unit = nativeInstance.setAlpha(value)

    override fun annotate(
        text: String,
        boundingArea: IMagickGeometry,
        gravity: Gravity,
        angle: Double,
    ) {
        throwIfEmpty(text)

        settings.drawing.createNativeInstance().use { drawingSettings ->
            nativeInstance.annotate(drawingSettings, text, boundingArea.toString(), gravity, angle)
        }
    }

    override fun annotate(text: String, gravity: Gravity) {
        throwIfEmpty(text)
        settings.drawing.createNativeInstance().use { drawingSettings ->
            nativeInstance.annotateGravity(drawingSettings, text, gravity)
        }
    }

    override fun autoGamma(channels: Channels): Unit = nativeInstance.autoGamma(channels)

    override fun autoLevel(channels: Channels): Unit = nativeInstance.autoLevel(channels)

    override fun autoOrient(): Unit = nativeInstance.autoOrient()

    override fun autoThreshold(method: AutoThresholdMethod): Unit = nativeInstance.autoThreshold(method)

    override fun bilateralBlur(width: UInt, height: UInt, intensitySigma: Double, spatialSigma: Double): Unit =
        nativeInstance.bilateralBlur(width, height, intensitySigma, spatialSigma)

    override fun blackThreshold(threshold: Percentage, channels: Channels): Unit =
        nativeInstance.blackThreshold(threshold.toString(), channels)

    override fun blueShift(factor: Double): Unit = nativeInstance.blueShift(factor)

    override fun blur(radius: Double, sigma: Double, channels: Channels): Unit =
        nativeInstance.blur(radius, sigma, channels)

    override fun border(width: UInt, height: UInt): Unit = MagickRectangle(0, 0, width, height).toNative().use {
        nativeInstance.border(it)
    }

    override fun brightnessContrast(brightness: Percentage, contrast: Percentage, channels: Channels): Unit =
        nativeInstance.brightnessContrast(brightness.toDouble(), contrast.toDouble(), channels)

    override fun cannyEdge(radius: Double, sigma: Double, lower: Percentage, upper: Percentage) {
        // The implementation slightly differs from Magick.NET: Percentage -> double conversion done in Native* classes
        nativeInstance.cannyEdge(radius, sigma, lower, upper)
    }

    override fun charcoal(radius: Double, sigma: Double) {
        nativeInstance.charcoal(radius, sigma)
    }

    override fun chop(geometry: IMagickGeometry): Unit = MagickRectangle.fromGeometry(geometry, this).toNative().use {
        nativeInstance.chop(it)
    }

    override fun chopHorizontal(offset: Int, width: UInt): Unit = chop(MagickGeometry(offset, 0, width, 0u))

    override fun chopVertical(offset: Int, height: UInt): Unit = chop(MagickGeometry(0, offset, 0u, height))

    override fun clahe(xTiles: UInt, yTiles: UInt, numberBins: UInt, clipLimit: Double): Unit =
        nativeInstance.clahe(xTiles.toULong(), yTiles.toULong(), numberBins.toULong(), clipLimit)

    override fun clamp(channels: Channels): Unit = nativeInstance.clamp(channels)

    override fun clip(pathName: String) {
        throwIfEmpty(pathName)
        nativeInstance.clipPath(pathName, true)
    }

    override fun clipOutside(pathName: String): Unit {
        throwIfEmpty(pathName)
        nativeInstance.clipPath(pathName, false)
    }

    override fun clut(image: IMagickImage, method: PixelInterpolateMethod, channels: Channels): Unit {
        nativeInstance(image).let { nativeInstance.clut(it, method, channels) }
    }

    override fun colorDecisionList(fileName: String): Unit {
        nativeInstance.colorDecisionList(fileName)
    }

    override fun colorMatrix(matrix: IMagickColorMatrix): Unit {
        DoubleMatrix.nativeInstance(matrix).use {
            nativeInstance.colorMatrix(it)
        }
    }

    override fun compare(image: IMagickImage): IMagickErrorInfo {
        val other = nativeInstance(image)
        if (nativeInstance.setColorMetric(other)) {
            return MagickErrorInfo()
        }

        return createErrorInfo(this)
    }

    override fun compare(image: IMagickImage, metric: ErrorMetric, channels: Channels): Double =
        nativeInstance(image).let {
            nativeInstance.compareDistortion(it, metric, channels)
        }

    override fun compare(
        image: IMagickImage,
        metric: ErrorMetric,
        difference: IMagickImage,
        channels: Channels,
    ): Double = compare(image, CompareSettings(metric = metric), difference, channels)

    override fun compare(
        image: IMagickImage,
        settings: CompareSettingsQuantum<QuantumType>,
        difference: IMagickImage,
        channels: Channels,
    ): Double {
        if (difference !is MagickImage) {
            throw UnsupportedOperationException()
        }

        val compareResult = TemporaryDefines(this).use {
            it.setArtifact("compare:highlight-color", settings.highlightColor)
            it.setArtifact("compare:lowlight-color", settings.lowlightColor)
            it.setArtifact("compare:masklight-color", settings.masklightColor)

            nativeInstance(image).let { otherNativeInstance ->
                nativeInstance.compare(otherNativeInstance, settings.metric, channels)
            }
        }

        compareResult.result?.let {
            difference.nativeInstance.ptr = it
        }

        return compareResult.distortion
    }

    override fun composite(
        image: imagemagick.core.MagickImage,
        x: Int,
        y: Int,
        compose: CompositeOperator,
        args: String?,
        channels: Channels,
    ): Unit {
        TemporaryDefines(this).use {
            it.setArtifact("compose:args", args)

            nativeInstance(image).let { otherNativeInstance ->
                nativeInstance.composite(otherNativeInstance, x.toLong(), y.toLong(), compose, channels)
            }
        }
    }

    override fun composite(
        image: imagemagick.core.MagickImage,
        gravity: Gravity,
        x: Int,
        y: Int,
        compose: CompositeOperator,
        args: String?,
        channels: Channels,
    ): Unit  {
        TemporaryDefines(this).use {
            it.setArtifact("compose:args", args)

            nativeInstance(image).let { otherNativeInstance ->
                nativeInstance.compositeGravity(otherNativeInstance, gravity, x.toLong(), y.toLong(), compose, channels)
            }
        }
    }

    override fun contrast(): Unit = nativeInstance.contrast(true)

    override fun contrastStretch(blackPoint: Percentage, whitePoint: Percentage, channels: Channels) {
        throwIfNegative("blackPoint", blackPoint)
        throwIfNegative("whitePoint", whitePoint)
    }

    override fun clone(): MagickImageQuantum<QuantumType> = MagickImage(this)

    override fun getAttribute(name: String): String? = nativeInstance.getAttribute(name)

    override fun ping(data: UByteArray): Unit = ping(data, null)

    override fun ping(
        data: UByteArray,
        offset: UInt,
        count: UInt,
    ): Unit = ping(data, offset, count, null)

    override fun ping(
        data: UByteArray,
        offset: UInt,
        count: UInt,
        readSettings: IMagickReadSettings<QuantumType>?,
    ) {
        throwIfEmpty(data)
        throwIfTrue("count", count < 1u, "The number of bytes should be at least 1.")
        throwIfTrue("offset", offset.toInt() >= data.size, "The offset should not exceed the length of the array.")
        throwIfTrue(
            "count",
            (offset + count).toInt() > data.size,
            "The number of bytes should not exceed the length of the array.",
        )

        read(data, offset, count, readSettings, true)
    }

    override fun ping(
        data: UByteArray,
        readSettings: IMagickReadSettings<QuantumType>?,
    ) {
        throwIfEmpty(data)

        ping(data, 0u, data.size.toUInt(), readSettings)
    }

    override fun ping(file: Path): Unit = ping(file, null)

    override fun ping(
        file: Path,
        readSettings: IMagickReadSettings<QuantumType>?,
    ): Unit = read(file.toString(), readSettings, true)

    override fun ping(stream: Source): Unit = ping(stream, null)

    override fun ping(
        stream: Source,
        readSettings: IMagickReadSettings<QuantumType>?,
    ) {
        read(stream, readSettings, true)
    }

    override fun ping(fileName: String): Unit = ping(fileName, null)

    override fun ping(
        fileName: String,
        readSettings: IMagickReadSettings<QuantumType>?,
    ): Unit = read(fileName, readSettings, true)

    // read UByteArray

    override fun read(data: UByteArray): Unit = read(data, null)

    override fun read(
        data: UByteArray,
        offset: UInt,
        count: UInt,
    ): Unit = read(data, offset, count, null)

    override fun read(
        data: UByteArray,
        offset: UInt,
        count: UInt,
        format: MagickFormat,
    ): Unit =
        read(
            data,
            offset,
            count,
            MagickReadSettings(settings).also {
                it.format = format
            },
        )

    override fun read(
        data: UByteArray,
        offset: UInt,
        count: UInt,
        readSettings: IMagickReadSettings<QuantumType>?,
    ) {
        throwIfEmpty(data)
        throwIfTrue("count", count < 1u, "The number of bytes should be at least 1.")
        throwIfTrue("offset", offset.toInt() >= data.size, "The offset should not exceed the length of the array.")
        throwIfTrue(
            "count",
            (offset + count).toInt() > data.size,
            "The number of bytes should not exceed the length of the array.",
        )
    }

    override fun read(
        data: UByteArray,
        format: MagickFormat,
    ) {
        throwIfEmpty(data)

        read(
            data,
            0u,
            data.size.toUInt(),
            MagickReadSettings(settings).also {
                it.format = format
            },
        )
    }

    override fun read(
        data: UByteArray,
        readSettings: IMagickReadSettings<QuantumType>?,
    ) {
        throwIfEmpty(data)
        read(data, 0u, data.size.toUInt(), readSettings, false)
    }

    // /read UByteArray

    // read Path

    override fun read(file: Path): Unit = read(file, null)

    override fun read(
        file: Path,
        width: UInt,
        height: UInt,
    ): Unit = read(file.name, width, height)

    override fun read(
        file: Path,
        format: MagickFormat,
    ): Unit =
        read(
            file.name,
            MagickReadSettings(settings).also {
                it.format = format
            },
        )

    override fun read(
        file: Path,
        readSettings: IMagickReadSettings<QuantumType>?,
    ): Unit = read(file.name, readSettings)

    // /read Path

    override fun read(
        color: IMagickColor<QuantumType>,
        width: UInt,
        height: UInt,
    ) {
        read("xc:${color.toShortString()}", width, height)
        backgroundColor = color
    }

    // read Stream

    override fun read(stream: Source): Unit = read(stream, MagickFormat.UNKNOWN)

    override fun read(
        stream: Source,
        format: MagickFormat,
    ): Unit =
        read(
            stream,
            MagickReadSettings(settings).also {
                it.format = format
            },
        )

    override fun read(
        stream: Source,
        readSettings: IMagickReadSettings<QuantumType>?,
    ): Unit = read(stream, readSettings, false)

    // read /Stream

    // read FileName

    override fun read(fileName: String): Unit = read(fileName, null)

    override fun read(
        fileName: String,
        width: UInt,
        height: UInt,
    ): Unit =
        read(
            fileName,
            MagickReadSettings(settings).also {
                it.width = width
                it.height = height
            },
        )

    override fun read(
        fileName: String,
        format: MagickFormat,
    ): Unit =
        read(
            fileName,
            MagickReadSettings(settings).also {
                it.format = format
            },
        )

    override fun read(
        fileName: String,
        readSettings: IMagickReadSettings<QuantumType>?,
    ): Unit = read(fileName, readSettings, false)

    // /read FileName

    private fun read(
        data: UByteArray,
        offset: UInt,
        length: UInt,
        readSettings: IMagickReadSettings<QuantumType>?,
        ping: Boolean,
        fileName: String? = null,
    ) {
        val newReadSettings = createReadSettings(readSettings)
        settings = newReadSettings

        settings.ping = ping
        settings.fileName = fileName

        settings.createNativeInstance().use {
            nativeInstance.readBlob(it, data, offset = offset, length = length)
        }

        resetSettings()
    }

    private fun read(
        stream: Source,
        readSettings: IMagickReadSettings<QuantumType>?,
        ping: Boolean,
    ) {
//        Throw.IfNullOrEmpty(nameof(stream), stream);
//
//        var bytes = Bytes.FromStreamBuffer(stream);
//        if (bytes is not null)
//        {
//            Read(bytes.GetData(), 0, bytes.Length, readSettings, ping);
//            return;
//        }

        val newReadSettings = createReadSettings(readSettings)
        settings = newReadSettings

        settings.ping = ping
        settings.fileName = null

        nativeInstance.readStream(stream.buffer(), settings)
    }

    private fun read(
        fileName: String,
        readSettings: IMagickReadSettings<QuantumType>?,
        ping: Boolean,
    ) {
        val newReadSettings = createReadSettings(readSettings)
        settings = newReadSettings

        settings.fileName = fileName
        settings.ping = ping

        settings.createNativeInstance().use {
            nativeInstance.readFile(it)
        }

        resetSettings()
    }

    override fun removeArtifact(name: String) {
        throwIfEmpty("name", name)
        nativeInstance.removeArtifact(name)
    }

    override fun removeAttribute(name: String) {
        throwIfEmpty("name", name)
        nativeInstance.removeAttribute(name)
    }

    override fun setArtifact(name: String, value: String) {
        throwIfEmpty("name", name)
        nativeInstance.setArtifact(name, value)
    }

    override fun setArtifact(name: String, flag: Boolean) {
        throwIfEmpty("name", name)
        nativeInstance.setArtifact(name, if (flag) "true" else "false")
    }

    @Throws(MagickException::class)
    override fun setAttribute(name: String, value: String) {
        throwIfEmpty("name", name)
        nativeInstance.setAttribute(name, value)
    }

    public companion object {
        internal fun clone(image: IMagickImageQ<QuantumType>?): IMagickImageQ<QuantumType>? = image?.clone()

        @Throws(UnsupportedOperationException::class)
        internal fun nativeInstance(image: IMagickImage): NativeMagickImage {
            if (image !is MagickImage) {
                throw UnsupportedOperationException()
            }

            return image.nativeInstance
        }

        internal fun createErrorInfo(image: MagickImage): IMagickErrorInfo = image.nativeInstance.let {
            MagickErrorInfo(
                it.meanErrorPerPixel,
                it.normalizedMaximumError,
                it.normalizedMeanError
            )
        }
    }
}
