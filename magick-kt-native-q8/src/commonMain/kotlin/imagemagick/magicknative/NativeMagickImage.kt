package imagemagick.magicknative

import imagemagick.QuantumType
import imagemagick.bridge.adaptiveBlur
import imagemagick.bridge.adaptiveResize
import imagemagick.bridge.adaptiveSharpen
import imagemagick.bridge.adaptiveThreshold
import imagemagick.bridge.addNoise
import imagemagick.bridge.affineTransform
import imagemagick.bridge.animationDelay
import imagemagick.bridge.animationIterations
import imagemagick.bridge.animationTicksPerSecond
import imagemagick.bridge.annotate
import imagemagick.bridge.annotateGravity
import imagemagick.bridge.autoGamma
import imagemagick.bridge.autoLevel
import imagemagick.bridge.autoOrient
import imagemagick.bridge.autoThreshold
import imagemagick.bridge.backgroundColor
import imagemagick.bridge.baseHeight
import imagemagick.bridge.baseWidth
import imagemagick.bridge.bilateralBlur
import imagemagick.bridge.blackPointCompensation
import imagemagick.bridge.blackThreshold
import imagemagick.bridge.blueShift
import imagemagick.bridge.blur
import imagemagick.bridge.border
import imagemagick.bridge.borderColor
import imagemagick.bridge.boundingBox
import imagemagick.bridge.brightnessContrast
import imagemagick.bridge.cannyEdge
import imagemagick.bridge.channelCount
import imagemagick.bridge.charcoal
import imagemagick.bridge.chop
import imagemagick.bridge.chromaBlue
import imagemagick.bridge.chromaGreen
import imagemagick.bridge.chromaRed
import imagemagick.bridge.chromaWhite
import imagemagick.bridge.clahe
import imagemagick.bridge.clamp
import imagemagick.bridge.classType
import imagemagick.bridge.clipPath
import imagemagick.bridge.clone
import imagemagick.bridge.clut
import imagemagick.bridge.colorDecisionList
import imagemagick.bridge.colorFuzz
import imagemagick.bridge.colorMatrix
import imagemagick.bridge.colorSpace
import imagemagick.bridge.colorType
import imagemagick.bridge.colormapSize
import imagemagick.bridge.compare
import imagemagick.bridge.compareDistortion
import imagemagick.bridge.compose
import imagemagick.bridge.composite
import imagemagick.bridge.compositeGravity
import imagemagick.bridge.compression
import imagemagick.bridge.contrast
import imagemagick.bridge.contrastStretch
import imagemagick.bridge.convolve
import imagemagick.bridge.copyPixels
import imagemagick.bridge.crop
import imagemagick.bridge.cycleColormap
import imagemagick.bridge.decipher
import imagemagick.bridge.depth
import imagemagick.bridge.deskew
import imagemagick.bridge.despeckle
import imagemagick.bridge.determineBitDepth
import imagemagick.bridge.determineColorType
import imagemagick.bridge.dispose
import imagemagick.bridge.distort
import imagemagick.bridge.edge
import imagemagick.bridge.emboss
import imagemagick.bridge.encipher
import imagemagick.bridge.encodingGeometry
import imagemagick.bridge.endian
import imagemagick.bridge.enhance
import imagemagick.bridge.equalize
import imagemagick.bridge.evaluateFunction
import imagemagick.bridge.evaluateGeometry
import imagemagick.bridge.evaluateOperator
import imagemagick.bridge.extent
import imagemagick.bridge.fileName
import imagemagick.bridge.filterType
import imagemagick.bridge.flip
import imagemagick.bridge.flop
import imagemagick.bridge.format
import imagemagick.bridge.frame
import imagemagick.bridge.fx
import imagemagick.bridge.gamma
import imagemagick.bridge.gammaCorrect
import imagemagick.bridge.gaussianBlur
import imagemagick.bridge.getArtifact
import imagemagick.bridge.getAttribute
import imagemagick.bridge.getNextArtifactName
import imagemagick.bridge.getNextAttributeName
import imagemagick.bridge.getNextProfileName
import imagemagick.bridge.gifDisposeMethod
import imagemagick.bridge.grayscale
import imagemagick.bridge.haldClut
import imagemagick.bridge.hasAlpha
import imagemagick.bridge.hasChannel
import imagemagick.bridge.hasProfile
import imagemagick.bridge.height
import imagemagick.bridge.houghLine
import imagemagick.bridge.implode
import imagemagick.bridge.interlace
import imagemagick.bridge.interpolate
import imagemagick.bridge.interpolativeResize
import imagemagick.bridge.isOpaque
import imagemagick.bridge.kmeans
import imagemagick.bridge.kuwahara
import imagemagick.bridge.linearStretch
import imagemagick.bridge.liquidRescale
import imagemagick.bridge.localContrast
import imagemagick.bridge.lower
import imagemagick.bridge.matteColor
import imagemagick.bridge.meanErrorPerPixel
import imagemagick.bridge.normalizedMaximumError
import imagemagick.bridge.normalizedMeanError
import imagemagick.bridge.orientation
import imagemagick.bridge.page
import imagemagick.bridge.quality
import imagemagick.bridge.raise
import imagemagick.bridge.regionMask
import imagemagick.bridge.removeArtifact
import imagemagick.bridge.removeAttribute
import imagemagick.bridge.removeProfile
import imagemagick.bridge.renderingIntent
import imagemagick.bridge.resetArtifactIterator
import imagemagick.bridge.resetAttributeIterator
import imagemagick.bridge.resetProfileIterator
import imagemagick.bridge.resize
import imagemagick.bridge.resolutionUnits
import imagemagick.bridge.resolutionX
import imagemagick.bridge.resolutionY
import imagemagick.bridge.roll
import imagemagick.bridge.rotate
import imagemagick.bridge.rotationalBlur
import imagemagick.bridge.sample
import imagemagick.bridge.scale
import imagemagick.bridge.segment
import imagemagick.bridge.selectiveBlur
import imagemagick.bridge.sepiaTone
import imagemagick.bridge.setAlpha
import imagemagick.bridge.setArtifact
import imagemagick.bridge.setAttribute
import imagemagick.bridge.setBitDepth
import imagemagick.bridge.setColorMetric
import imagemagick.bridge.setProfile
import imagemagick.bridge.setReadMask
import imagemagick.bridge.setWriteMask
import imagemagick.bridge.shade
import imagemagick.bridge.shadow
import imagemagick.bridge.sharpen
import imagemagick.bridge.shave
import imagemagick.bridge.shear
import imagemagick.bridge.sigmoidalContrast
import imagemagick.bridge.signature
import imagemagick.bridge.sketch
import imagemagick.bridge.solarize
import imagemagick.bridge.sortPixels
import imagemagick.bridge.splice
import imagemagick.bridge.spread
import imagemagick.bridge.statistic
import imagemagick.bridge.statistics
import imagemagick.bridge.totalColors
import imagemagick.bridge.virtualPixelMethod
import imagemagick.bridge.width
import imagemagick.core.enums.AlphaOption
import imagemagick.core.enums.AutoThresholdMethod
import imagemagick.core.enums.Channels
import imagemagick.core.enums.ClassType
import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.ColorType
import imagemagick.core.enums.CompositeOperator
import imagemagick.core.enums.CompressionMethod
import imagemagick.core.enums.DensityUnit
import imagemagick.core.enums.DistortMethod
import imagemagick.core.enums.Endian
import imagemagick.core.enums.ErrorMetric
import imagemagick.core.enums.EvaluateFunction
import imagemagick.core.enums.EvaluateOperator
import imagemagick.core.enums.FilterType
import imagemagick.core.enums.GifDisposeMethod
import imagemagick.core.enums.Gravity
import imagemagick.core.enums.Interlace
import imagemagick.core.enums.NoiseType
import imagemagick.core.enums.OrientationType
import imagemagick.core.enums.PixelChannel
import imagemagick.core.enums.PixelIntensityMethod
import imagemagick.core.enums.PixelInterpolateMethod
import imagemagick.core.enums.RenderingIntent
import imagemagick.core.enums.StatisticType
import imagemagick.core.enums.VirtualPixelMethod
import imagemagick.core.exceptions.MagickException
import imagemagick.core.types.Percentage
import imagemagick.magicknative.colors.NativeMagickColor
import imagemagick.magicknative.exceptions.ExceptionInfoPtrVar
import imagemagick.magicknative.exceptions.withException
import imagemagick.magicknative.matrices.NativeDoubleMatrix
import imagemagick.magicknative.settings.NativeDrawingSettings
import imagemagick.magicknative.types.NativeMagickRectangle
import imagemagick.magicknative.types.NativeOffsetInfo
import imagemagick.magicknative.types.NativePrimaryInfo
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.alloc
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.pointed
import kotlinx.cinterop.ptr
import kotlinx.cinterop.usePinned
import kotlinx.cinterop.zeroValue
import libMagickNative.ChannelStatistics
import libMagickNative.Image
import libMagickNative.MagickImage_Create
import libMagickNative.MagickImage_ReadBlob
import libMagickNative.MagickImage_ReadFile
import okio.BufferedSource
import platform.posix.size_t
import kotlin.contracts.ExperimentalContracts
import kotlin.experimental.ExperimentalNativeApi
import imagemagick.core.settings.MagickSettings as IMagickSettings
import imagemagick.core.types.MagickGeometry as IMagickGeometry

@ExperimentalStdlibApi
@ExperimentalForeignApi
public class NativeMagickImage : NativeInstance<Image>, AutoCloseable {
    public companion object {
        internal val zero: CPointer<Image> =
            memScoped {
                zeroValue<Image>().ptr
            }
    }

    // TODO Review me: zero ptr might not be necessary
    public override var ptr: CPointer<Image> = zero
        get() {
            if (field === zero) {
                throw UnsupportedOperationException()
            }

            return field
        }
        set(value) {
            if (field !== zero) {
                field.dispose()
            }
            field = value
        }

    public constructor(instance: CPointer<Image>) {
        ptr = instance
    }

    @ExperimentalContracts
    public constructor(settings: NativeMagickSettings) {
        ptr =
            checkException {
                withException(onError = { it.dispose() }) { _, exceptionPtr ->
                    MagickImage_Create(settings.ptr, exceptionPtr)
                }
            }
    }

    override fun close() {
        dispose()
    }

    @ExperimentalContracts
    public fun clone(): NativeMagickImage =
        checkException { ptr.clone() }.let {
            NativeMagickImage(it)
        }

    public fun dispose(): Unit = ptr.dispose()

    public inline var animationDelay: UInt
        get() = ptr.animationDelay().toUInt()
        set(value) = ptr.animationDelay(value.toULong())

    public inline var animationIterations: UInt
        get() = ptr.animationIterations().toUInt()
        set(value) = ptr.animationIterations(value.toULong())

    public inline var animationTicksPerSecond: UInt
        get() = ptr.animationTicksPerSecond().toUInt()
        set(value) = ptr.animationTicksPerSecond(value.toLong())

    public inline var backgroundColor: NativeMagickColor?
        get() = ptr.backgroundColor()
        set(value) = ptr.backgroundColor(value)

    public inline val baseHeight: UInt
        get() = ptr.baseHeight().toUInt()

    public inline val baseWidth: UInt
        get() = ptr.baseWidth().toUInt()

    public inline var blackPointCompensation: Boolean
        get() = ptr.blackPointCompensation()
        set(value) = ptr.blackPointCompensation(value)

    public inline var borderColor: NativeMagickColor?
        get() = ptr.borderColor()
        set(value) = ptr.borderColor(value)

    @ExperimentalContracts
    public val boundingBox: NativeMagickRectangle
        get() =
            checkException { ptr.boundingBox() }.let {
                NativeMagickRectangle(it)
            }

    public inline val channelCount: UInt
        get() = ptr.channelCount().toUInt()

    public inline var chromaBlue: NativePrimaryInfo?
        get() = ptr.chromaBlue()
        set(value) = ptr.chromaBlue(value)

    public inline var chromaGreen: NativePrimaryInfo?
        get() = ptr.chromaGreen()
        set(value) = ptr.chromaGreen(value)

    public inline var chromaRed: NativePrimaryInfo?
        get() = ptr.chromaRed()
        set(value) = ptr.chromaRed(value)

    public inline var chromaWhite: NativePrimaryInfo?
        get() = ptr.chromaWhite()
        set(value) = ptr.chromaWhite(value)

    @ExperimentalContracts
    public var classType: ClassType
        get() = ptr.classType()
        set(value) =
            checkException {
                ptr.classType(value)
            }

    public inline var colorFuzz: Double
        get() = ptr.colorFuzz()
        set(value) = ptr.colorFuzz(value)

    @ExperimentalContracts
    public var colormapSize: UInt
        get() = ptr.colormapSize().toUInt()
        set(value) =
            checkException {
                ptr.colormapSize(value.toLong())
            }

    @ExperimentalContracts
    public var colorSpace: ColorSpace
        get() = ptr.colorSpace()
        set(value) =
            checkException {
                ptr.colorSpace(value)
            }

    @ExperimentalContracts
    public var colorType: ColorType
        get() = ptr.colorType()
        set(value) =
            checkException {
                ptr.colorType(value)
            }

    public inline var compression: CompressionMethod
        get() = ptr.compression()
        set(value) = ptr.compression(value)

    public inline var compose: CompositeOperator
        get() = ptr.compose()
        set(value) = ptr.compose(value)

    public inline var depth: size_t
        get() = ptr.depth()
        set(value) = ptr.depth(value)

    public inline val encodingGeometry: String?
        get() = ptr.encodingGeometry()

    public inline var endian: Endian
        get() = ptr.endian()
        set(value) = ptr.endian(value)

    public inline var fileName: String?
        get() = ptr.fileName()
        set(value) = ptr.fileName(value)

    public inline var filterType: FilterType
        get() = ptr.filterType()
        set(value) = ptr.filterType(value)

    public inline var format: String?
        get() = ptr.format()
        set(value) = ptr.format(value)

    public inline val gamma: Double
        get() = ptr.gamma()

    public inline var gifDisposeMethod: GifDisposeMethod
        get() = ptr.gifDisposeMethod()
        set(value) = ptr.gifDisposeMethod(value)

    @ExperimentalContracts
    public var hasAlpha: Boolean
        get() = ptr.hasAlpha()
        set(value) =
            checkException {
                ptr.hasAlpha(value)
            }

    public inline val height: UInt
        get() = ptr.height().toUInt()

    public inline var interlace: Interlace
        get() = ptr.interlace()
        set(value) = ptr.interlace(value)

    public inline var interpolate: PixelInterpolateMethod
        get() = ptr.interpolate()
        set(value) = ptr.interpolate(value)

    @ExperimentalContracts
    public val isOpaque: Boolean
        get() =
            checkException {
                ptr.isOpaque()
            }

    public inline var matteColor: NativeMagickColor?
        get() = ptr.matteColor()
        set(value) = ptr.matteColor(value)

    public inline val meanErrorPerPixel: Double
        get() = ptr.meanErrorPerPixel()

    public inline val normalizedMaximumError: Double
        get() = ptr.normalizedMaximumError()

    public inline val normalizedMeanError: Double
        get() = ptr.normalizedMeanError()

    public inline var orientation: OrientationType
        get() = ptr.orientation()
        set(value) = ptr.orientation(value)

    public inline var page: NativeMagickRectangle?
        get() = ptr.page()
        set(value) = ptr.page(value)

    public inline var quality: size_t
        get() = ptr.quality()
        set(value) = ptr.quality(value)

    public inline var renderingIntent: RenderingIntent
        get() = ptr.renderingIntent()
        set(value) = ptr.renderingIntent(value)

    public inline var resolutionX: Double
        get() = ptr.resolutionX()
        set(value) = ptr.resolutionX(value)

    public inline var resolutionY: Double
        get() = ptr.resolutionY()
        set(value) = ptr.resolutionY(value)

    public inline var resolutionUnits: DensityUnit
        get() = ptr.resolutionUnits()
        set(value) = ptr.resolutionUnits(value)

    @ExperimentalContracts
    public val signature: String
        get() =
            checkException {
                ptr.signature()
            }

    @ExperimentalContracts
    public val totalColors: UInt
        get() = checkException { ptr.totalColors() }.toUInt()

    @ExperimentalContracts
    public var virtualPixelMethod: VirtualPixelMethod
        get() = ptr.virtualPixelMethod()
        set(value) = checkException { ptr.virtualPixelMethod(value) }

    public inline val width: UInt
        get() = ptr.width().toUInt()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun adaptiveBlur(
        radius: Double,
        sigma: Double,
    ) {
        checkException { ptr.adaptiveBlur(radius, sigma) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun adaptiveResize(geometry: IMagickGeometry) {
        checkException {
            ptr.adaptiveResize(geometry.toString())
        }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun adaptiveSharpen(
        radius: Double,
        sigma: Double,
        channels: Channels,
    ) {
        checkException { ptr.adaptiveSharpen(radius, sigma, channels) }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun adaptiveThreshold(
        with: UInt,
        height: UInt,
        bias: Double,
        channels: Channels,
    ) {
        checkException { ptr.adaptiveThreshold(with.toULong(), height.toULong(), bias, channels) }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun addNoise(
        noiseType: NoiseType,
        attenuate: Double,
        channels: Channels,
    ) {
        checkException { ptr.addNoise(noiseType, attenuate, channels) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun affineTransform(
        scaleX: Double,
        scaleY: Double,
        shearX: Double,
        shearY: Double,
        translateX: Double,
        translateY: Double,
    ) {
        checkException { ptr.affineTransform(scaleX, scaleY, shearX, shearY, translateX, translateY) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun annotate(
        settings: NativeDrawingSettings,
        text: String,
        boundingArea: String,
        gravity: Gravity,
        degrees: Double,
    ): Unit =
        checkException {
            ptr.annotate(settings, text, boundingArea, gravity, degrees)
        }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun annotateGravity(
        settings: NativeDrawingSettings,
        text: String,
        gravity: Gravity,
    ): Unit = checkException { ptr.annotateGravity(settings, text, gravity) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun autoGamma(channels: Channels): Unit = checkException { ptr.autoGamma(channels) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun autoLevel(channels: Channels): Unit = checkException { ptr.autoLevel(channels) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun autoOrient() {
        checkException { ptr.autoOrient() }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun autoThreshold(method: AutoThresholdMethod): Unit = checkException { ptr.autoThreshold(method) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun bilateralBlur(
        width: UInt,
        height: UInt,
        intensitySigma: Double,
        spatialSigma: Double,
    ) {
        checkException {
            ptr.bilateralBlur(
                width.toULong(),
                height.toULong(),
                intensitySigma,
                spatialSigma,
            )
        }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun blackThreshold(
        threshold: String,
        channels: Channels,
    ): Unit = checkException { ptr.blackThreshold(threshold, channels) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun blueShift(factor: Double) {
        checkException { ptr.blueShift(factor) }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun blur(
        radius: Double,
        sigma: Double,
        channels: Channels,
    ) {
        checkException { ptr.blur(radius, sigma, channels) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun border(value: NativeMagickRectangle) {
        checkException { ptr.border(value) }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun brightnessContrast(
        brigthness: Double,
        contrast: Double,
        channels: Channels,
    ): Unit = checkException { ptr.brightnessContrast(brigthness, contrast, channels) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun cannyEdge(
        radius: Double,
        sigma: Double,
        lower: Percentage,
        upper: Percentage,
    ) {
        checkException { ptr.cannyEdge(radius, sigma, lower.toDouble() / 100.0, upper.toDouble() / 100.0) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun charcoal(
        radius: Double,
        sigma: Double,
    ) {
        checkException { ptr.charcoal(radius, sigma) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun chop(geometry: NativeMagickRectangle) {
        checkException { ptr.chop(geometry) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun clahe(
        xTiles: ULong,
        yTiles: ULong,
        numberBins: ULong,
        clipLimit: Double,
    ): Unit = checkException { ptr.clahe(xTiles, yTiles, numberBins, clipLimit) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun clamp(channels: Channels): Unit = checkException { ptr.clamp(channels) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun clipPath(
        pathName: String,
        inside: Boolean,
    ): Unit = checkException { ptr.clipPath(pathName, inside) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun clut(
        image: NativeMagickImage,
        method: PixelInterpolateMethod,
        channels: Channels,
    ): Unit = checkException { ptr.clut(image, method, channels) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun colorDecisionList(fileName: String): Unit = checkException { ptr.colorDecisionList(fileName) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun colorMatrix(matrix: NativeDoubleMatrix) {
        checkException { ptr.colorMatrix(matrix) }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun compare(
        image: NativeMagickImage,
        metric: ErrorMetric,
        channels: Channels,
    ): Pair<Double, CPointer<Image>?> = checkException { ptr.compare(image, metric, channels) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun compareDistortion(
        image: NativeMagickImage,
        metric: ErrorMetric,
        channels: Channels,
    ): Double = checkException { ptr.compareDistortion(image, metric, channels) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun composite(
        image: NativeMagickImage,
        x: Long,
        y: Long,
        compose: CompositeOperator,
        channels: Channels,
    ): Unit = checkException { ptr.composite(image, x, y, compose, channels) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun compositeGravity(
        image: NativeMagickImage,
        gravity: Gravity,
        x: Long,
        y: Long,
        compose: CompositeOperator,
        channels: Channels,
    ): Unit = checkException { ptr.compositeGravity(image, gravity, x, y, compose, channels) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun contrast(enhance: Boolean): Unit = checkException { ptr.contrast(enhance) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun contrastStretch(
        blackPoint: Double,
        whitePoint: Double,
        channels: Channels,
    ): Unit = checkException { ptr.contrastStretch(blackPoint, whitePoint, channels) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun convolve(matrix: NativeDoubleMatrix) {
        checkException { ptr.convolve(matrix) }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun copyPixels(
        source: NativeMagickImage,
        geometry: NativeMagickRectangle,
        offset: NativeOffsetInfo,
        channels: Channels,
    ): Unit = checkException { ptr.copyPixels(source, geometry, offset, channels) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun crop(
        geometry: IMagickGeometry,
        gravity: Gravity,
    ) {
        checkException { ptr.crop(geometry.toString(), gravity) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun cycleColormap(amount: Long): Unit = checkException { ptr.cycleColormap(amount) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun decipher(passphrase: String): Unit = checkException { ptr.decipher(passphrase) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun deskew(threshold: Double) {
        checkException { ptr.deskew(threshold) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun despeckle() {
        checkException { ptr.despeckle() }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun determineBitDepth(channels: Channels): ULong = checkException { ptr.determineBitDepth(channels) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun determineColorType(): ColorType = checkException { ptr.determineColorType() }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun distort(
        method: DistortMethod,
        bestfit: Boolean,
        arguments: DoubleArray,
    ) {
        checkException { ptr.distort(method, bestfit, arguments) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun edge(radius: Double) {
        checkException { ptr.edge(radius) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun emboss(
        radius: Double,
        sigma: Double,
    ) {
        checkException { ptr.emboss(radius, sigma) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun encipher(passphrase: String): Unit = checkException { ptr.encipher(passphrase) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun enhance() {
        checkException { ptr.enhance() }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun equalize(channels: Channels): Unit = checkException { ptr.equalize(channels) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun evaluateFunction(
        channels: Channels,
        evaluateFunction: EvaluateFunction,
        values: DoubleArray,
    ): Unit = checkException { ptr.evaluateFunction(channels, evaluateFunction, values) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun evaluateGeometry(
        channels: Channels,
        geometry: NativeMagickRectangle,
        evaluateOperator: EvaluateOperator,
        value: Double,
    ): Unit = checkException { ptr.evaluateGeometry(channels, geometry, evaluateOperator, value) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun evaluateOperator(
        channels: Channels,
        evaluateOperator: EvaluateOperator,
        value: Double,
    ): Unit = checkException { ptr.evaluateOperator(channels, evaluateOperator, value) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun extent(
        geometry: IMagickGeometry,
        gravity: Gravity,
    ) {
        checkException { ptr.extent(geometry.toString(), gravity) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun flip() {
        checkException { ptr.flip() }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun flop() {
        checkException { ptr.flop() }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun frame(geometry: NativeMagickRectangle) {
        checkException { ptr.frame(geometry) }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun fx(
        expression: String,
        channels: Channels,
    ) {
        checkException { ptr.fx(expression, channels) }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun gammaCorrect(
        gamma: Double,
        channels: Channels,
    ): Unit = checkException { ptr.gammaCorrect(gamma, channels) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun gaussianBlur(
        radius: Double,
        sigma: Double,
        channels: Channels,
    ) {
        checkException { ptr.gaussianBlur(radius, sigma, channels) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun getAttribute(name: String): String? =
        checkException {
            ptr.getAttribute(name)
        }

    public inline fun getArtifact(name: String): String? = ptr.getArtifact(name)

    public inline fun getNextArtifactName(): String? = ptr.getNextArtifactName()

    public inline fun getNextAttributeName(): String? = ptr.getNextAttributeName()

    public inline fun getNextProfileName(): String? = ptr.getNextProfileName()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun grayscale(method: PixelIntensityMethod): Unit = checkException { ptr.grayscale(method) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun haldClut(image: NativeMagickImage): Unit = checkException { ptr.haldClut(image) }

    public inline fun hasChannel(channel: PixelChannel): Boolean = ptr.hasChannel(channel)

    public inline fun hasProfile(name: String): Boolean = ptr.hasProfile(name)

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun houghLine(
        width: ULong,
        height: ULong,
        threshold: ULong,
    ) {
        checkException { ptr.houghLine(width, height, threshold) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun implode(
        amount: Double,
        method: PixelInterpolateMethod,
    ) {
        checkException { ptr.implode(amount, method) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun interpolativeResize(
        geometry: IMagickGeometry,
        method: PixelInterpolateMethod,
    ) {
        checkException { ptr.interpolativeResize(geometry.toString(), method) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun kmeans(
        numberColors: ULong,
        maxIterations: ULong,
        tolerance: Double,
    ): Unit = checkException { ptr.kmeans(numberColors, maxIterations, tolerance) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun kuwahara(
        radius: Double,
        sigma: Double,
    ) {
        checkException { ptr.kuwahara(radius, sigma) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun linearStretch(
        blackPoint: Double,
        whitePoint: Double,
    ): Unit = checkException { ptr.linearStretch(blackPoint, whitePoint) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun liquidRescale(
        geometry: IMagickGeometry,
        deltaX: Double,
        rigidity: Double,
    ) {
        checkException { ptr.liquidRescale(geometry.toString(), deltaX, rigidity) }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun localContrast(
        radius: Double,
        strength: Double,
        channels: Channels,
    ) {
        checkException { ptr.localContrast(radius, strength, channels) }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun lower(size: ULong): Unit = checkException { ptr.lower(size) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun raise(size: ULong): Unit = checkException { ptr.raise(size) }

    // Diff from Magick.NET: a native version required in parameters
    public fun readBlob(
        settings: NativeMagickSettings?,
        data: UByteArray,
        offset: UInt,
        length: UInt,
    ): Unit =
        memScoped {
            val exceptionInfo = alloc<ExceptionInfoPtrVar>()

            val image =
                data.usePinned { pinnedData ->
                    MagickImage_ReadBlob(
                        settings?.ptr,
                        pinnedData.addressOf(0),
                        offset.convert(),
                        length.convert(),
                        exceptionInfo.ptr,
                    )
                }

            exceptionInfo.pointed?.let { exception ->
                println("got exception")
                println(exception.error_number)
                println(exception.description)
            }

            if (null != image) {
                ptr = image
            }
        }

    // Diff from Magick.NET: a native version required in parameters
    public fun readFile(settings: NativeMagickSettings?): Unit =
        memScoped {
            val exceptionInfo = alloc<ExceptionInfoPtrVar>()

            val image = MagickImage_ReadFile(settings?.ptr, exceptionInfo.ptr)

            exceptionInfo.pointed?.let { exception ->
                println("got exception")
                println(exception.error_number)
                println(exception.description)
            }

            if (null != image) {
                ptr = image
            }
        }

    public fun readStream(
        stream: BufferedSource,
        settings: IMagickSettings<QuantumType>?,
    ) {
        TODO()
        // memScoped {
//        val exceptionInfo = alloc<ExceptionInfoPtrVar>()
//
//        val image = MagickSettings.createNativeInstance(settings).use { nativeSettings ->
//            MagickImage_ReadStream(
//                nativeSettings.ptr,
//                // read
//                staticCFunction { data: CPointer<UByteVar>?, count: size_t, _: COpaquePointer?, ->
//                    if (null == data) {
//                        return@staticCFunction 0
//                    }
//
//                    if (0.toSizeT() == count) {
//                        return@staticCFunction 0
//                    }
//
//
//                    val bufferSize = 8192
//                    val sink = ByteArray(bufferSize)
//
//                    var total: Long = count.convert()
//
//                    var destination: CPointer<UByteVar> = data.getPointer(this)
//                    var bytesRead: Long = 0
//
//                    while (total > 0) {
//                        var length = min(total, bufferSize.toLong())
//
//                        if (stream.exhausted()) {
//                            break
//                        }
//
//                        try {
//                            length = stream.read(sink, 0, length.toInt()).toLong()
//                        } catch(e: Exception) {
//                            return@staticCFunction -1
//                        }
//
//                        if (length < 0) {
//                            break
//                        }
//
//                        sink.usePinned {
//                            memcpy(destination, it.addressOf(0), length.convert())?.let { dstUpdated ->
//                                destination = dstUpdated.reinterpret()
//                            }
//                        }
//
//                        bytesRead += length
//
//                        total -= length
//                    }
//
//                    bytesRead.convert()
//                },
//                // seek
//                null,
// //                staticCFunction { offset: libMagickNative.MagickOffsetType, whence: Int, _: COpaquePointer?, ->
// //                    try {
// //                        0
// //                    } catch (e: Exception) {
// //                        -1
// //                    }
// //                },
//                // tell
// //                staticCFunction { _: COpaquePointer? -> 0 },
//                null,
//                exceptionInfo.ptr
//            )
//        }
//
//        exceptionInfo.pointed?.let { exception ->
//            println("got exception")
//            println(exception.error_number)
//            println(exception.description)
//        }
//
//        if (null != image) {
//            ptr = image
//        }
//    }
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun regionMask(region: NativeMagickRectangle?): Unit = checkException { ptr.regionMask(region) }

    public inline fun removeArtifact(name: String): Unit = ptr.removeArtifact(name)

    public inline fun removeAttribute(name: String): Unit = ptr.removeAttribute(name)

    public inline fun removeProfile(name: String): Unit = ptr.removeProfile(name)

    public inline fun resetArtifactIterator(): Unit = ptr.resetArtifactIterator()

    public inline fun resetAttributeIterator(): Unit = ptr.resetAttributeIterator()

    public inline fun resetProfileIterator(): Unit = ptr.resetProfileIterator()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun resize(geometry: String) {
        checkException { ptr.resize(geometry) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun roll(
        x: Long,
        y: Long,
    ) {
        checkException { ptr.roll(x, y) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun rotate(degrees: Double) {
        checkException { ptr.rotate(degrees) }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun rotationalBlur(
        angle: Double,
        channels: Channels,
    ) {
        checkException { ptr.rotationalBlur(angle, channels) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun sample(geometry: String) {
        checkException { ptr.sample(geometry) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun scale(geometry: String) {
        checkException { ptr.scale(geometry) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun segment(
        colorSpace: ColorSpace,
        clusterThreshold: Double,
        smoothingThreshold: Double,
    ): Unit = checkException { ptr.segment(colorSpace, clusterThreshold, smoothingThreshold) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun selectiveBlur(
        radius: Double,
        sigma: Double,
        threshold: Double,
        channels: Channels,
    ) {
        checkException { ptr.selectiveBlur(radius, sigma, threshold, channels) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun sepiaTone(threshold: Double) {
        checkException { ptr.sepiaTone(threshold) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun setAlpha(value: AlphaOption): Unit = checkException { ptr.setAlpha(value) }

    public inline fun setArtifact(
        name: String,
        value: String,
    ): Unit = ptr.setArtifact(name, value)

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun setAttribute(
        name: String,
        value: String,
    ): Unit = checkException { ptr.setAttribute(name, value) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun setBitDepth(
        value: ULong,
        channels: Channels,
    ): Unit = checkException { ptr.setBitDepth(value, channels) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun setColorMetric(image: NativeMagickImage): Boolean = checkException { ptr.setColorMetric(image) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun setProfile(
        name: String,
        datum: UByteArray,
    ): Unit = checkException { ptr.setProfile(name, datum) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun setReadMask(image: NativeMagickImage?): Unit = checkException { ptr.setReadMask(image) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun setWriteMask(image: NativeMagickImage?): Unit = checkException { ptr.setWriteMask(image) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun shade(
        azimuth: Double,
        elevation: Double,
        colorShading: Boolean,
        channels: Channels,
    ) {
        checkException { ptr.shade(azimuth, elevation, colorShading, channels) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun shadow(
        x: Long,
        y: Long,
        sigma: Double,
        alphaPercentage: Double,
    ) {
        checkException { ptr.shadow(x, y, sigma, alphaPercentage) }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun sharpen(
        radius: Double,
        sigma: Double,
        channels: Channels,
    ) {
        checkException { ptr.sharpen(radius, sigma, channels) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun shave(
        leftRight: ULong,
        bottomLeft: ULong,
    ) {
        checkException { ptr.shave(leftRight, bottomLeft) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun shear(
        xAngle: Double,
        yAngle: Double,
    ) {
        checkException { ptr.shear(xAngle, yAngle) }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun sigmoidalContrast(
        sharpen: Boolean,
        contrast: Double,
        midpoint: Double,
        channels: Channels,
    ): Unit = checkException { ptr.sigmoidalContrast(sharpen, contrast, midpoint, channels) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun sketch(
        radius: Double,
        sigma: Double,
        angle: Double,
    ) {
        checkException { ptr.sketch(radius, sigma, angle) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun solarize(factor: Double): Unit = checkException { ptr.solarize(factor) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun sortPixels(): Unit = checkException { ptr.sortPixels() }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun splice(geometry: NativeMagickRectangle) {
        checkException { ptr.splice(geometry) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun spread(
        method: PixelInterpolateMethod,
        radius: Double,
    ) {
        checkException { ptr.spread(method, radius) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun statistic(
        type: StatisticType,
        width: ULong,
        height: ULong,
    ) {
        checkException { ptr.statistic(type, width, height) }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun statistics(channels: Channels): CPointer<ChannelStatistics> =
        checkException {
            ptr.statistics(channels)
        }

    private inline fun CPointer<Image>?.selfUpdate(): CPointer<Image> {
        this?.let { ptr = it }
        return ptr
    }
}
