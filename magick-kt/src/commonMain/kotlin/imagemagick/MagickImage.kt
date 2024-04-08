package imagemagick

import imagemagick.bridge.dispose
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
import imagemagick.core.enums.ColorTransformMode
import imagemagick.core.enums.ColorType
import imagemagick.core.enums.CompositeOperator
import imagemagick.core.enums.CompressionMethod
import imagemagick.core.enums.DistortMethod
import imagemagick.core.enums.Endian
import imagemagick.core.enums.ErrorMetric
import imagemagick.core.enums.EvaluateFunction
import imagemagick.core.enums.EvaluateOperator
import imagemagick.core.enums.FilterType
import imagemagick.core.enums.GifDisposeMethod
import imagemagick.core.enums.Gravity
import imagemagick.core.enums.Interlace
import imagemagick.core.enums.MagickFormat
import imagemagick.core.enums.NoiseType
import imagemagick.core.enums.OrientationType
import imagemagick.core.enums.PixelChannel
import imagemagick.core.enums.PixelIntensityMethod
import imagemagick.core.enums.PixelInterpolateMethod
import imagemagick.core.enums.RenderingIntent
import imagemagick.core.enums.StatisticType
import imagemagick.core.enums.VirtualPixelMethod
import imagemagick.core.exceptions.MagickException
import imagemagick.core.profiles.ImageProfile
import imagemagick.core.profiles.color.ColorProfile
import imagemagick.core.settings.CompareSettingsQuantum
import imagemagick.core.settings.KmeansSettings
import imagemagick.core.types.Density
import imagemagick.core.types.Percentage
import imagemagick.core.types.PointD
import imagemagick.exceptions.MagickErrorException
import imagemagick.exceptions.throwIfEmpty
import imagemagick.exceptions.throwIfTrue
import imagemagick.helpers.PercentageHelper
import imagemagick.helpers.TemporaryDefines
import imagemagick.helpers.enumValueOf
import imagemagick.helpers.using
import imagemagick.magicknative.NativeMagickImage
import imagemagick.magicknative.types.NativeOffsetInfo
import imagemagick.matrices.DoubleMatrix
import imagemagick.settings.CompareSettings
import imagemagick.settings.DeskewSettings
import imagemagick.settings.DistortSettings
import imagemagick.settings.MagickReadSettings
import imagemagick.settings.MagickSettings
import imagemagick.statistics.Statistics
import imagemagick.types.ChromaticityInfo
import imagemagick.types.MagickErrorInfo
import imagemagick.types.MagickGeometry
import imagemagick.types.MagickRectangle
import imagemagick.types.MagickRectangle.Companion.toNative
import imagemagick.types.PrimaryInfo.Companion.toMagick
import imagemagick.types.PrimaryInfo.Companion.toNative
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import okio.Path
import okio.Source
import okio.buffer
import kotlin.contracts.ExperimentalContracts
import kotlin.experimental.ExperimentalNativeApi
import kotlin.math.min
import imagemagick.core.MagickImage as IMagickImage
import imagemagick.core.MagickImageQuantum as IMagickImageQ
import imagemagick.core.colors.MagickColorQuantum as IMagickColor
import imagemagick.core.matrices.ConvolveMatrix as IConvolveMatrix
import imagemagick.core.matrices.MagickColorMatrix as IMagickColorMatrix
import imagemagick.core.settings.DeskewSettings as IDeskewSettings
import imagemagick.core.settings.DistortSettings as IDistortSettings
import imagemagick.core.settings.MagickReadSettings as IMagickReadSettings
import imagemagick.core.statistics.Statistics as IStatistics
import imagemagick.core.types.ChromaticityInfo as IChromaticityInfo
import imagemagick.core.types.MagickErrorInfo as IMagickErrorInfo
import imagemagick.core.types.MagickGeometry as IMagickGeometry

/**
 * Class that represents an ImageMagick image.
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
        get() =
            sequence {
                nativeInstance.resetArtifactIterator()
                var name = nativeInstance.getNextArtifactName()
                while (name != null) {
                    yield(name)
                    name = nativeInstance.getNextArtifactName()
                }
            }

    override val attributeNames: Sequence<String>
        get() =
            sequence {
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
        get() =
            sequence {
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

    override fun adaptiveBlur(
        radius: Double,
        sigma: Double,
    ): Unit = nativeInstance.adaptiveBlur(radius, sigma)

    override fun adaptiveResize(
        width: UInt,
        height: UInt,
    ): Unit = nativeInstance.adaptiveResize(MagickGeometry(width, height))

    override fun adaptiveResize(geometry: IMagickGeometry): Unit = nativeInstance.adaptiveResize(geometry)

    override fun adaptiveSharpen(
        radius: Double,
        sigma: Double,
        channels: Channels,
    ): Unit = nativeInstance.adaptiveSharpen(radius, sigma, channels)

    override fun adaptiveThreshold(
        width: UInt,
        height: UInt,
        bias: Double,
        channels: Channels,
    ): Unit = nativeInstance.adaptiveThreshold(width, height, bias, channels)

    override fun adaptiveThreshold(
        width: UInt,
        height: UInt,
        biasPercentage: Percentage,
        channels: Channels,
    ): Unit = adaptiveThreshold(width, height, PercentageHelper.toQuantum(biasPercentage), channels)

    override fun addNoise(
        noiseType: NoiseType,
        attenuate: Double,
        channels: Channels,
    ): Unit = nativeInstance.addNoise(noiseType, attenuate, channels)

    override fun affineTransform(affineMatrix: DrawableAffine): Unit =
        nativeInstance.affineTransform(
            affineMatrix.scaleX,
            affineMatrix.scaleY,
            affineMatrix.shearX,
            affineMatrix.shearY,
            affineMatrix.translateX,
            affineMatrix.translateY,
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

    override fun annotate(
        text: String,
        gravity: Gravity,
    ) {
        throwIfEmpty(text)
        settings.drawing.createNativeInstance().use { drawingSettings ->
            nativeInstance.annotateGravity(drawingSettings, text, gravity)
        }
    }

    override fun autoGamma(channels: Channels): Unit = nativeInstance.autoGamma(channels)

    override fun autoLevel(channels: Channels): Unit = nativeInstance.autoLevel(channels)

    override fun autoOrient(): Unit = nativeInstance.autoOrient()

    override fun autoThreshold(method: AutoThresholdMethod): Unit = nativeInstance.autoThreshold(method)

    override fun bilateralBlur(
        width: UInt,
        height: UInt,
        intensitySigma: Double,
        spatialSigma: Double,
    ): Unit = nativeInstance.bilateralBlur(width, height, intensitySigma, spatialSigma)

    override fun blackThreshold(
        threshold: Percentage,
        channels: Channels,
    ): Unit = nativeInstance.blackThreshold(threshold.toString(), channels)

    override fun blueShift(factor: Double): Unit = nativeInstance.blueShift(factor)

    override fun blur(
        radius: Double,
        sigma: Double,
        channels: Channels,
    ): Unit = nativeInstance.blur(radius, sigma, channels)

    override fun border(
        width: UInt,
        height: UInt,
    ): Unit =
        MagickRectangle(x = 0, y = 0, width, height).toNative().use {
            nativeInstance.border(it)
        }

    override fun brightnessContrast(
        brightness: Percentage,
        contrast: Percentage,
        channels: Channels,
    ): Unit = nativeInstance.brightnessContrast(brightness.toDouble(), contrast.toDouble(), channels)

    override fun cannyEdge(
        radius: Double,
        sigma: Double,
        lower: Percentage,
        upper: Percentage,
    ) {
        // The implementation slightly differs from Magick.NET: Percentage -> double conversion done in Native* classes
        nativeInstance.cannyEdge(radius, sigma, lower, upper)
    }

    override fun charcoal(
        radius: Double,
        sigma: Double,
    ) {
        nativeInstance.charcoal(radius, sigma)
    }

    override fun chop(geometry: IMagickGeometry): Unit =
        MagickRectangle.fromGeometry(geometry, this).toNative().use {
            nativeInstance.chop(it)
        }

    override fun chopHorizontal(
        offset: Int,
        width: UInt,
    ): Unit = chop(MagickGeometry(offset, y = 0, width, height = 0u))

    override fun chopVertical(
        offset: Int,
        height: UInt,
    ): Unit = chop(MagickGeometry(x = 0, offset, width = 0u, height))

    override fun clahe(
        xTiles: UInt,
        yTiles: UInt,
        numberBins: UInt,
        clipLimit: Double,
    ): Unit = nativeInstance.clahe(xTiles.toULong(), yTiles.toULong(), numberBins.toULong(), clipLimit)

    override fun clamp(channels: Channels): Unit = nativeInstance.clamp(channels)

    override fun clip(pathName: String) {
        throwIfEmpty(pathName)
        nativeInstance.clipPath(pathName, true)
    }

    override fun clipOutside(pathName: String) {
        throwIfEmpty(pathName)
        nativeInstance.clipPath(pathName, false)
    }

    override fun clone(): MagickImageQuantum<QuantumType> = MagickImage(this)

    override fun clut(
        image: IMagickImage,
        method: PixelInterpolateMethod,
        channels: Channels,
    ): Unit = nativeInstance.clut(image.nativeInstance(), method, channels)

    override fun colorDecisionList(fileName: String) {
        nativeInstance.colorDecisionList(fileName)
    }

    override fun colorMatrix(matrix: IMagickColorMatrix) {
        DoubleMatrix.nativeInstance(matrix).use {
            nativeInstance.colorMatrix(it)
        }
    }

    override fun compare(image: IMagickImage): IMagickErrorInfo {
        if (nativeInstance.setColorMetric(image.nativeInstance())) {
            return MagickErrorInfo()
        }

        return createErrorInfo(this)
    }

    override fun compare(
        image: IMagickImage,
        metric: ErrorMetric,
        channels: Channels,
    ): Double = nativeInstance.compareDistortion(image.nativeInstance(), metric, channels)

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

        val (distortion, result) =
            TemporaryDefines(this).use {
                it.setArtifact("compare:highlight-color", settings.highlightColor)
                it.setArtifact("compare:lowlight-color", settings.lowlightColor)
                it.setArtifact("compare:masklight-color", settings.masklightColor)

                nativeInstance.compare(image.nativeInstance(), settings.metric, channels)
            }

        result?.let { difference.nativeInstance.ptr = it }

        return distortion
    }

    override fun composite(
        image: IMagickImage,
        x: Int,
        y: Int,
        compose: CompositeOperator,
        args: String?,
        channels: Channels,
    ): Unit =
        TemporaryDefines(this).use {
            it.setArtifact("compose:args", args)

            nativeInstance.composite(image.nativeInstance(), x.toLong(), y.toLong(), compose, channels)
        }

    override fun composite(
        image: IMagickImage,
        gravity: Gravity,
        x: Int,
        y: Int,
        compose: CompositeOperator,
        args: String?,
        channels: Channels,
    ): Unit =
        TemporaryDefines(this).use {
            it.setArtifact("compose:args", args)

            nativeInstance.compositeGravity(image.nativeInstance(), gravity, x.toLong(), y.toLong(), compose, channels)
        }

    override fun contrast(): Unit = nativeInstance.contrast(true)

    override fun contrastStretch(
        blackPoint: Percentage,
        whitePoint: Percentage,
        channels: Channels,
    ): Unit =
        calculateContrastStretch(blackPoint, whitePoint).let { contrast ->
            nativeInstance.contrastStretch(contrast.x, contrast.y, channels)
        }

    override fun convolve(matrix: IConvolveMatrix) {
        DoubleMatrix.nativeInstance(matrix).use {
            nativeInstance.convolve(it)
        }
    }

    override fun copyPixels(
        source: IMagickImage,
        channels: Channels,
    ) {
        val geometry = MagickGeometry(x = 0, y = 0, min(source.width, width), min(source.height, height))

        copyPixels(source, geometry, x = 0, y = 0, channels)
    }

    override fun copyPixels(
        source: IMagickImage,
        geometry: IMagickGeometry,
        x: Int,
        y: Int,
        channels: Channels,
    ) {
        using(
            MagickRectangle.fromGeometry(geometry, this).toNative(),
            NativeOffsetInfo(),
        ) { nativeGeometry, offsetInfo ->
            offsetInfo.x(x.toLong())
            offsetInfo.y(y.toLong())

            nativeInstance.copyPixels(source.nativeInstance(), nativeGeometry, offsetInfo, channels)
        }
    }

    override fun crop(
        width: UInt,
        height: UInt,
        gravity: Gravity,
    ): Unit = crop(MagickGeometry(x = 0, y = 0, width = width, height = height), gravity)

    override fun crop(
        geometry: IMagickGeometry,
        gravity: Gravity,
    ): Unit = nativeInstance.crop(geometry, gravity)

    override fun cycleColormap(amount: Int): Unit = nativeInstance.cycleColormap(amount.toLong())

    override fun decipher(passphrase: String): Unit = nativeInstance.decipher(passphrase)

    override fun deskew(threshold: Percentage): Double =
        deskew(
            DeskewSettings().apply {
                this.threshold = threshold
            },
        )

    override fun deskew(settings: IDeskewSettings): Double {
        TemporaryDefines(this).use {
            it.setArtifact("deskew:auto-crop", settings.autoCrop)
            nativeInstance.deskew(PercentageHelper.toQuantum(settings.threshold))
        }

        return getAttribute("deskew:angle")?.toDoubleOrNull() ?: 0.0
    }

    override fun despeckle(): Unit = nativeInstance.despeckle()

    override fun determineColorType(): ColorType = nativeInstance.determineColorType()

    override fun determineBitDepth(channels: Channels): UInt = nativeInstance.determineBitDepth(channels).toUInt()

    override fun distort(
        method: DistortMethod,
        vararg arguments: Double,
    ): Unit = distort(method, DistortSettings(), *arguments)

    override fun distort(
        method: DistortMethod,
        settings: IDistortSettings,
        vararg arguments: Double,
    ) {
        throwIfEmpty("arguments", arguments)

        TemporaryDefines(this).use {
            it.setArtifact("distort:scale", settings.scale)
            it.setArtifact("distort:viewport", settings.viewport)

            nativeInstance.distort(method, settings.bestfit, arguments)
        }
    }

    override fun edge(radius: Double): Unit = nativeInstance.edge(radius)

    override fun emboss(
        radius: Double,
        sigma: Double,
    ): Unit = nativeInstance.emboss(radius, sigma)

    override fun encipher(passphrase: String) {
        throwIfEmpty("passphrase", passphrase)
        nativeInstance.encipher(passphrase)
    }

    override fun enhance(): Unit = nativeInstance.enhance()

    override fun equalize(channels: Channels): Unit = nativeInstance.equalize(channels)

    override fun evaluate(
        channels: Channels,
        evaluateFunction: EvaluateFunction,
        vararg arguments: Double,
    ) {
        throwIfEmpty("arguments", arguments)
        nativeInstance.evaluateFunction(channels, evaluateFunction, arguments)
    }

    override fun evaluate(
        channels: Channels,
        evaluateOperator: EvaluateOperator,
        value: Double,
    ): Unit = nativeInstance.evaluateOperator(channels, evaluateOperator, value)

    override fun evaluate(
        channels: Channels,
        evaluateOperator: EvaluateOperator,
        percentage: Percentage,
    ): Unit = evaluate(channels, evaluateOperator, PercentageHelper.toQuantum(percentage))

    override fun evaluate(
        channels: Channels,
        geometry: IMagickGeometry,
        evaluateOperator: EvaluateOperator,
        value: Double,
    ): Unit =
        MagickRectangle.fromGeometry(geometry, this).toNative().use {
            nativeInstance.evaluateGeometry(channels, it, evaluateOperator, value)
        }

    override fun evaluate(
        channels: Channels,
        geometry: IMagickGeometry,
        evaluateOperator: EvaluateOperator,
        percentage: Percentage,
    ): Unit = evaluate(channels, geometry, evaluateOperator, PercentageHelper.toQuantum(percentage))

    override fun extent(
        width: UInt,
        height: UInt,
    ): Unit = extent(MagickGeometry(width, height), Gravity.UNDEFINED)

    override fun extent(
        x: Int,
        y: Int,
        width: UInt,
        height: UInt,
    ): Unit = extent(MagickGeometry(x, y, width, height), Gravity.UNDEFINED)

    override fun extent(
        width: UInt,
        height: UInt,
        gravity: Gravity,
    ): Unit = extent(MagickGeometry(width, height), gravity)

    override fun extent(geometry: IMagickGeometry): Unit = extent(geometry, Gravity.UNDEFINED)

    override fun extent(
        geometry: IMagickGeometry,
        gravity: Gravity,
    ): Unit = nativeInstance.extent(geometry, gravity)

    override fun flip(): Unit = nativeInstance.flip()

    override fun flop(): Unit = nativeInstance.flop()

    override fun frame(): Unit = frame(MagickGeometry(x = 6, y = 6, width = 25u, height = 25u))

    override fun frame(geometry: IMagickGeometry): Unit =
        MagickRectangle.fromGeometry(geometry, this).toNative()
            .use { nativeInstance.frame(it) }

    override fun frame(
        width: UInt,
        height: UInt,
    ): Unit = frame(MagickGeometry(x = 6, y = 6, width, height))

    override fun frame(
        width: UInt,
        height: UInt,
        innerBevel: Int,
        outerBevel: Int,
    ): Unit = frame(MagickGeometry(innerBevel, outerBevel, width, height))

    override fun fx(
        expression: String,
        channels: Channels,
    ) {
        throwIfEmpty(expression)
        nativeInstance.fx(expression, channels)
    }

    override fun gammaCorrect(
        gamma: Double,
        channels: Channels,
    ): Unit = nativeInstance.gammaCorrect(gamma, channels)

    override fun gaussianBlur(
        radius: Double,
        sigma: Double,
        channels: Channels,
    ): Unit = nativeInstance.gaussianBlur(radius, sigma, channels)

    override fun getAttribute(name: String): String? {
        throwIfEmpty("name", name)
        return nativeInstance.getAttribute(name)
    }

    override fun getArtifact(name: String): String? {
        throwIfEmpty("name", name)
        return nativeInstance.getArtifact(name)
    }

    override fun grayscale(method: PixelIntensityMethod): Unit = nativeInstance.grayscale(method)

    override fun haldClut(image: IMagickImage): Unit = nativeInstance.haldClut(image.nativeInstance())

    override fun hasProfile(name: String): Boolean = nativeInstance.hasProfile(name)

    override fun houghLine(
        width: UInt,
        height: UInt,
        threshold: UInt,
    ): Unit = nativeInstance.houghLine(width.toULong(), height.toULong(), threshold.toULong())

    override fun implode(
        amount: Double,
        method: PixelInterpolateMethod,
    ): Unit = nativeInstance.implode(amount, method)

    override fun interpolativeResize(
        width: UInt,
        height: UInt,
        method: PixelInterpolateMethod,
    ): Unit = interpolativeResize(MagickGeometry(width, height), method)

    override fun interpolativeResize(
        geometry: IMagickGeometry,
        method: PixelInterpolateMethod,
    ): Unit = nativeInstance.interpolativeResize(geometry, method)

    override fun interpolativeResize(
        percentage: Percentage,
        method: PixelInterpolateMethod,
    ): Unit = interpolativeResize(MagickGeometry(percentage, percentage), method)

    override fun interpolativeResize(
        percentageWidth: Percentage,
        percentageHeight: Percentage,
        method: PixelInterpolateMethod,
    ): Unit = interpolativeResize(MagickGeometry(percentageWidth, percentageHeight), method)

    override fun inverseContrast(): Unit = nativeInstance.contrast(enhance = false)

    override fun inverseSigmoidalContrast(contrast: Double): Unit = inverseSigmoidalContrast(contrast, Quantum.maxd * 0.5)

    override fun inverseSigmoidalContrast(
        contrast: Double,
        midpoint: Double,
        channels: Channels,
    ): Unit = nativeInstance.sigmoidalContrast(sharpen = false, contrast, midpoint, channels)

    override fun inverseSigmoidalContrast(
        contrast: Double,
        midpointPercentage: Percentage,
    ): Unit = inverseSigmoidalContrast(contrast, PercentageHelper.toQuantum(midpointPercentage))

    override fun kmeans(settings: KmeansSettings): Unit =
        TemporaryDefines(this).use {
            it.setArtifact("kmeans:seed-colors", settings.seedColors)
            nativeInstance.kmeans(settings.numberColors.toULong(), settings.maxIterations.toULong(), settings.tolerance)
        }

    override fun kuwahara(
        radius: Double,
        sigma: Double,
    ): Unit = nativeInstance.kuwahara(radius, sigma)

    override fun linearStretch(
        blackPoint: Percentage,
        whitePoint: Percentage,
    ): Unit = nativeInstance.linearStretch(PercentageHelper.toQuantum(blackPoint), PercentageHelper.toQuantum(whitePoint))

    override fun liquidRescale(
        width: UInt,
        height: UInt,
    ): Unit = liquidRescale(MagickGeometry(width, height))

    override fun liquidRescale(
        width: UInt,
        height: UInt,
        deltaX: Double,
        rigidity: Double,
    ): Unit = nativeInstance.liquidRescale(MagickGeometry(width, height), deltaX, rigidity)

    override fun liquidRescale(geometry: IMagickGeometry): Unit =
        nativeInstance.liquidRescale(geometry, geometry.x.toDouble(), geometry.y.toDouble())

    override fun liquidRescale(percentage: Percentage): Unit = liquidRescale(MagickGeometry(percentage, percentage))

    override fun liquidRescale(
        percentageWidth: Percentage,
        percentageHeight: Percentage,
    ): Unit = liquidRescale(MagickGeometry(percentageWidth, percentageHeight))

    override fun liquidRescale(
        percentageWidth: Percentage,
        percentageHeight: Percentage,
        deltaX: Double,
        rigidity: Double,
    ): Unit = nativeInstance.liquidRescale(MagickGeometry(percentageWidth, percentageHeight), deltaX, rigidity)

    override fun lower(size: UInt): Unit = nativeInstance.lower(size.toULong())

    override fun magnify() {
        TODO("Not yet implemented")
    }

    override fun localContrast(
        radius: Double,
        strength: Percentage,
        channels: Channels,
    ): Unit = nativeInstance.localContrast(radius, strength.toDouble(), channels)

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

    override fun raise(size: UInt): Unit = nativeInstance.raise(size.toULong())

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

    override fun removeRegionMask(): Unit = nativeInstance.regionMask(null)

    override fun removeProfile(profile: ImageProfile): Unit = removeProfile(profile.name)

    override fun removeProfile(name: String): Unit = nativeInstance.removeProfile(name)

    override fun removeReadMask(): Unit = nativeInstance.setReadMask(null)

    override fun removeWriteMask(): Unit = nativeInstance.setWriteMask(null)

    override fun resize(
        width: UInt,
        height: UInt,
    ): Unit = nativeInstance.resize(MagickGeometry(width, height).toString())

    override fun resize(geometry: IMagickGeometry): Unit = nativeInstance.resize(geometry.toString())

    override fun resize(percentage: Percentage): Unit = nativeInstance.resize(MagickGeometry(percentage, percentage).toString())

    override fun resize(
        percentageWidth: Percentage,
        percentageHeight: Percentage,
    ): Unit = nativeInstance.resize(MagickGeometry(percentageWidth, percentageHeight).toString())

    override fun roll(
        x: Int,
        y: Int,
    ): Unit = nativeInstance.roll(x.toLong(), y.toLong())

    override fun rotate(degrees: Double): Unit = nativeInstance.rotate(degrees)

    override fun rotationalBlur(
        angle: Double,
        channels: Channels,
    ): Unit = nativeInstance.rotationalBlur(angle, channels)

    override fun sample(
        width: UInt,
        height: UInt,
    ): Unit = sample(MagickGeometry(width, height))

    override fun sample(geometry: IMagickGeometry): Unit = nativeInstance.sample(geometry.toString())

    override fun sample(
        percentageWidth: Percentage,
        percentageHeight: Percentage,
    ): Unit = sample(MagickGeometry(percentageWidth, percentageHeight))

    override fun scale(
        width: UInt,
        height: UInt,
    ): Unit = scale(MagickGeometry(width, height))

    override fun scale(geometry: IMagickGeometry): Unit = nativeInstance.scale(geometry.toString())

    override fun scale(
        percentageWidth: Percentage,
        percentageHeight: Percentage,
    ): Unit = scale(MagickGeometry(percentageWidth, percentageHeight))

    override fun segment(
        quantizeColorSpace: ColorSpace,
        clusterThreshold: Double,
        smoothingThreshold: Double,
    ): Unit = nativeInstance.segment(quantizeColorSpace, clusterThreshold, smoothingThreshold)

    override fun selectiveBlur(
        radius: Double,
        sigma: Double,
        threshold: Double,
        channels: Channels,
    ): Unit = nativeInstance.selectiveBlur(radius, sigma, threshold, channels)

    override fun selectiveBlur(
        radius: Double,
        sigma: Double,
        thresholdPercentage: Percentage,
        channels: Channels,
    ): Unit = nativeInstance.selectiveBlur(radius, sigma, PercentageHelper.toQuantum(thresholdPercentage), channels)

    override fun sepiaTone(threshold: Percentage): Unit = nativeInstance.sepiaTone(PercentageHelper.toQuantum(threshold))

    override fun setArtifact(
        name: String,
        value: String,
    ) {
        throwIfEmpty("name", name)
        nativeInstance.setArtifact(name, value)
    }

    override fun setArtifact(
        name: String,
        flag: Boolean,
    ) {
        throwIfEmpty("name", name)
        nativeInstance.setArtifact(name, if (flag) "true" else "false")
    }

    override fun setAttribute(
        name: String,
        value: String,
    ) {
        throwIfEmpty("name", name)
        nativeInstance.setAttribute(name, value)
    }

    override fun setAttribute(
        name: String,
        flag: Boolean,
    ) {
        throwIfEmpty("name", name)
        nativeInstance.setAttribute(name, if (flag) "true" else "false")
    }

    override fun setAttenuate(attenuate: Double): Unit = setArtifact("attenuate", attenuate.toString())

    override fun setBitDepth(
        value: UInt,
        channels: Channels,
    ): Unit = nativeInstance.setBitDepth(value.toULong(), channels)

    override fun setClippingPath(
        value: String,
        pathName: String,
    ): Unit = setAttribute("8BIM:1999,2998:$value", pathName)

    override fun setCompression(compression: CompressionMethod) {
        nativeInstance.compression = compression
    }

    override fun setProfile(profile: ImageProfile) {
        profile.toByteArray()
            ?.takeUnless { it.isEmpty() }
            ?.let { datum ->
                nativeInstance.setProfile(profile.name, datum)
            }
    }

    override fun setProfile(
        profile: ColorProfile,
        mode: ColorTransformMode,
    ) {
        profile.toByteArray()
            ?.takeUnless { it.isEmpty() }
            ?.let { datum ->
                TemporaryDefines(this).use {
                    if (mode == ColorTransformMode.QUANTUM) {
                        it.setArtifact("profile:highres-transform", false)
                    }

                    nativeInstance.setProfile(profile.name, datum)
                }
            }
    }

    override fun setReadMask(image: IMagickImage): Unit = nativeInstance.setReadMask(image.nativeInstance())

    override fun setWriteMask(image: IMagickImage): Unit = nativeInstance.setWriteMask(image.nativeInstance())

    override fun shade(
        azimuth: Double,
        elevation: Double,
        channels: Channels,
    ): Unit = nativeInstance.shade(azimuth, elevation, colorShading = false, channels)

    override fun shadeGrayscale(
        azimuth: Double,
        elevation: Double,
        channels: Channels,
    ): Unit = nativeInstance.shade(azimuth, elevation, colorShading = true, channels)

    override fun shadow(
        x: Int,
        y: Int,
        sigma: Double,
        alpha: Percentage,
    ): Unit = nativeInstance.shadow(x.toLong(), y.toLong(), sigma, alpha.toDouble())

    override fun sharpen(
        radius: Double,
        sigma: Double,
        channels: Channels,
    ): Unit = nativeInstance.sharpen(radius, sigma, channels)

    override fun shave(
        leftRight: UInt,
        topBottom: UInt,
    ): Unit = nativeInstance.shave(leftRight.toULong(), topBottom.toULong())

    override fun shear(
        xAngle: Double,
        yAngle: Double,
    ): Unit = nativeInstance.shear(xAngle, yAngle)

    override fun sigmoidalContrast(contrast: Double): Unit = sigmoidalContrast(contrast, Quantum.maxd * 0.5)

    override fun sigmoidalContrast(
        contrast: Double,
        midpoint: Double,
        channels: Channels,
    ): Unit = nativeInstance.sigmoidalContrast(sharpen = true, contrast, midpoint, channels)

    override fun sigmoidalContrast(
        contrast: Double,
        midpointPercentage: Percentage,
    ): Unit = sigmoidalContrast(contrast, PercentageHelper.toQuantum(midpointPercentage))

    override fun sketch(
        radius: Double,
        sigma: Double,
        angle: Double,
    ): Unit = nativeInstance.sketch(radius, sigma, angle)

    override fun solarize(factor: Double): Unit = nativeInstance.solarize(factor)

    override fun solarize(factorPercentage: Percentage): Unit = nativeInstance.solarize(PercentageHelper.toQuantum(factorPercentage))

    override fun sortPixels(): Unit = nativeInstance.sortPixels()

    override fun splice(geometry: IMagickGeometry): Unit =
        MagickRectangle.fromGeometry(geometry, this).toNative().use {
            nativeInstance.splice(it)
        }

    override fun spread(
        method: PixelInterpolateMethod,
        radius: Double,
    ): Unit = nativeInstance.spread(method, radius)

    override fun statistic(
        type: StatisticType,
        width: UInt,
        height: UInt,
    ): Unit = nativeInstance.statistic(type, width.toULong(), height.toULong())

    override fun statistics(channels: Channels): IStatistics {
        val list = nativeInstance.statistics(channels)

        return try {
            Statistics(this, list, channels)
        } finally {
            list.dispose()
        }
    }

    private fun calculateContrastStretch(
        blackPoint: Percentage,
        whitePoint: Percentage,
    ): PointD {
        val pixels = (width * height).toDouble()

        var x = blackPoint.toDouble()
        var y = whitePoint.toDouble()

        x *= pixels / 100.0
        y *= pixels / 100.0
        y = pixels - y

        return PointD(x, y)
    }

    public companion object {
        internal fun clone(image: IMagickImageQ<QuantumType>?): IMagickImageQ<QuantumType>? = image?.clone()

        @Throws(UnsupportedOperationException::class)
        internal fun IMagickImage.nativeInstance(): NativeMagickImage =
            (this as? MagickImage)?.nativeInstance ?: throw UnsupportedOperationException()

        internal fun createErrorInfo(image: MagickImage): IMagickErrorInfo =
            image.nativeInstance.let {
                MagickErrorInfo(
                    it.meanErrorPerPixel,
                    it.normalizedMaximumError,
                    it.normalizedMeanError,
                )
            }
    }
}
