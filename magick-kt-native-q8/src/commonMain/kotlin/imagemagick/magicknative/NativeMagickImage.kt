package imagemagick.magicknative

import imagemagick.bridge.PointInfoCollection
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
import imagemagick.bridge.magnify
import imagemagick.bridge.map
import imagemagick.bridge.matteColor
import imagemagick.bridge.meanErrorPerPixel
import imagemagick.bridge.meanShift
import imagemagick.bridge.minify
import imagemagick.bridge.minimumBoundingBox
import imagemagick.bridge.modulate
import imagemagick.bridge.moments
import imagemagick.bridge.morphology
import imagemagick.bridge.motionBlur
import imagemagick.bridge.negate
import imagemagick.bridge.normalize
import imagemagick.bridge.normalizedMaximumError
import imagemagick.bridge.normalizedMeanError
import imagemagick.bridge.oilPaint
import imagemagick.bridge.orderedDither
import imagemagick.bridge.orientation
import imagemagick.bridge.page
import imagemagick.bridge.perceptible
import imagemagick.bridge.perceptualHash
import imagemagick.bridge.polaroid
import imagemagick.bridge.posterize
import imagemagick.bridge.quality
import imagemagick.bridge.quantize
import imagemagick.bridge.raise
import imagemagick.bridge.readBlob
import imagemagick.bridge.readFile
import imagemagick.bridge.regionMask
import imagemagick.bridge.removeArtifact
import imagemagick.bridge.removeAttribute
import imagemagick.bridge.removeProfile
import imagemagick.bridge.renderingIntent
import imagemagick.bridge.resample
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
import imagemagick.bridge.stegano
import imagemagick.bridge.stereo
import imagemagick.bridge.strip
import imagemagick.bridge.swirl
import imagemagick.bridge.texture
import imagemagick.bridge.threshold
import imagemagick.bridge.thumbnail
import imagemagick.bridge.totalColors
import imagemagick.bridge.transpose
import imagemagick.bridge.transverse
import imagemagick.bridge.trim
import imagemagick.bridge.unsharpMask
import imagemagick.bridge.vignette
import imagemagick.bridge.virtualPixelMethod
import imagemagick.bridge.wave
import imagemagick.bridge.whiteBalance
import imagemagick.bridge.width
import imagemagick.bridge.writeStream
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
import imagemagick.core.enums.DitherMethod
import imagemagick.core.enums.Endian
import imagemagick.core.enums.ErrorMetric
import imagemagick.core.enums.EvaluateFunction
import imagemagick.core.enums.EvaluateOperator
import imagemagick.core.enums.FilterType
import imagemagick.core.enums.GifDisposeMethod
import imagemagick.core.enums.Gravity
import imagemagick.core.enums.Interlace
import imagemagick.core.enums.MorphologyMethod
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
import imagemagick.helpers.PercentageHelper
import imagemagick.magicknative.colors.NativeMagickColor
import imagemagick.magicknative.exceptions.withException
import imagemagick.magicknative.matrices.NativeDoubleMatrix
import imagemagick.magicknative.settings.NativeDrawingSettings
import imagemagick.magicknative.settings.NativeQuantizeSettings
import imagemagick.magicknative.statistics.NativeMoments
import imagemagick.magicknative.statistics.NativePerceptualHashList
import imagemagick.magicknative.types.NativeMagickRectangle
import imagemagick.magicknative.types.NativeOffsetInfo
import imagemagick.magicknative.types.NativePrimaryInfo
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.zeroValue
import libMagickNative.ChannelStatistics
import libMagickNative.CustomStreamHandler
import libMagickNative.CustomStreamSeeker
import libMagickNative.CustomStreamTeller
import libMagickNative.Image
import libMagickNative.MagickImage_Create
import platform.posix.size_t
import kotlin.contracts.ExperimentalContracts
import kotlin.experimental.ExperimentalNativeApi
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
        get() = ptr.animationDelay.toUInt()
        set(value) {
            ptr.animationDelay = value.toULong()
        }

    public inline var animationIterations: UInt
        get() = ptr.animationIterations.toUInt()
        set(value) {
            ptr.animationIterations = value.toULong()
        }

    public inline var animationTicksPerSecond: UInt
        get() = ptr.animationTicksPerSecond.toUInt()
        set(value) {
            ptr.animationTicksPerSecond = value.toLong()
        }

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
        get() = ptr.chromaBlue?.let { NativePrimaryInfo(it) }
        set(value) {
            ptr.chromaBlue = value?.ptr
        }

    public inline var chromaGreen: NativePrimaryInfo?
        get() = ptr.chromaGreen?.let { NativePrimaryInfo(it) }
        set(value) {
            ptr.chromaGreen = value?.ptr
        }

    public inline var chromaRed: NativePrimaryInfo?
        get() = ptr.chromaRed?.let { NativePrimaryInfo(it) }
        set(value) {
            ptr.chromaRed = value?.ptr
        }

    public inline var chromaWhite: NativePrimaryInfo?
        get() = ptr.chromaWhite?.let { NativePrimaryInfo(it) }
        set(value) {
            ptr.chromaWhite = value?.ptr
        }

    @ExperimentalContracts
    public var classType: ClassType
        get() = ptr.classType()
        set(value) =
            checkException {
                ptr.classType(value)
            }

    public inline var colorFuzz: Double
        get() = ptr.colorFuzz
        set(value) {
            ptr.colorFuzz = value
        }

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
        get() = ptr.compression
        set(value) {
            ptr.compression = value
        }

    public inline var compose: CompositeOperator
        get() = ptr.compose
        set(value) {
            ptr.compose = value
        }

    public inline var depth: size_t
        get() = ptr.depth
        set(value) {
            ptr.depth = value
        }

    public inline val encodingGeometry: String?
        get() = ptr.encodingGeometry()

    public inline var endian: Endian
        get() = ptr.endian
        set(value) {
            ptr.endian = value
        }

    public inline var fileName: String?
        get() = ptr.fileName
        set(value) {
            ptr.fileName = value
        }

    public inline var filterType: FilterType
        get() = ptr.filterType
        set(value) {
            ptr.filterType = value
        }

    public inline var format: String?
        get() = ptr.format
        set(value) {
            ptr.format = value
        }

    public inline val gamma: Double
        get() = ptr.gamma()

    public inline var gifDisposeMethod: GifDisposeMethod
        get() = ptr.gifDisposeMethod
        set(value) {
            ptr.gifDisposeMethod = value
        }

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
        get() = ptr.interlace
        set(value) {
            ptr.interlace = value
        }

    public inline var interpolate: PixelInterpolateMethod
        get() = ptr.interpolate
        set(value) {
            ptr.interpolate = value
        }

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
        get() = ptr.orientation
        set(value) {
            ptr.orientation = value
        }

    public inline var page: NativeMagickRectangle?
        get() = ptr.page()
        set(value) = ptr.page(value)

    public inline var quality: size_t
        get() = ptr.quality
        set(value) {
            ptr.quality = value
        }

    public inline var renderingIntent: RenderingIntent
        get() = ptr.renderingIntent
        set(value) {
            ptr.renderingIntent = value
        }

    public inline var resolutionX: Double
        get() = ptr.resolutionX
        set(value) {
            ptr.resolutionX = value
        }

    public inline var resolutionY: Double
        get() = ptr.resolutionY
        set(value) {
            ptr.resolutionY = value
        }

    public inline var resolutionUnits: DensityUnit
        get() = ptr.resolutionUnits
        set(value) {
            ptr.resolutionUnits = value
        }

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
    ): Unit = checkException { ptr.adaptiveBlur(radius, sigma) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun adaptiveResize(geometry: IMagickGeometry): Unit =
        checkException {
            ptr.adaptiveResize(geometry.toString())
        }.selfUpdate()

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun adaptiveSharpen(
        radius: Double,
        sigma: Double,
        channels: Channels,
    ): Unit = checkException { ptr.adaptiveSharpen(radius, sigma, channels) }.selfUpdate()

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun adaptiveThreshold(
        with: UInt,
        height: UInt,
        bias: Double,
        channels: Channels,
    ): Unit = checkException { ptr.adaptiveThreshold(with.toULong(), height.toULong(), bias, channels) }.selfUpdate()

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun addNoise(
        noiseType: NoiseType,
        attenuate: Double,
        channels: Channels,
    ): Unit = checkException { ptr.addNoise(noiseType, attenuate, channels) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun affineTransform(
        scaleX: Double,
        scaleY: Double,
        shearX: Double,
        shearY: Double,
        translateX: Double,
        translateY: Double,
    ): Unit = checkException { ptr.affineTransform(scaleX, scaleY, shearX, shearY, translateX, translateY) }.selfUpdate()

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
    public fun autoOrient(): Unit = checkException { ptr.autoOrient() }.selfUpdate()

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
    public fun blueShift(factor: Double): Unit = checkException { ptr.blueShift(factor) }.selfUpdate()

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun blur(
        radius: Double,
        sigma: Double,
        channels: Channels,
    ): Unit = checkException { ptr.blur(radius, sigma, channels) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun border(value: NativeMagickRectangle): Unit = checkException { ptr.border(value) }.selfUpdate()

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
    ): Unit = checkException { ptr.cannyEdge(radius, sigma, lower.toDouble() / 100.0, upper.toDouble() / 100.0) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun charcoal(
        radius: Double,
        sigma: Double,
    ): Unit = checkException { ptr.charcoal(radius, sigma) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun chop(geometry: NativeMagickRectangle): Unit = checkException { ptr.chop(geometry) }.selfUpdate()

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
    ): Unit = checkException { ptr.clut(image.ptr, method, channels) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun colorDecisionList(fileName: String): Unit = checkException { ptr.colorDecisionList(fileName) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun colorMatrix(matrix: NativeDoubleMatrix): Unit = checkException { ptr.colorMatrix(matrix.ptr) }.selfUpdate()

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun compare(
        image: NativeMagickImage,
        metric: ErrorMetric,
        channels: Channels,
    ): Pair<Double, CPointer<Image>?> = checkException { ptr.compare(image.ptr, metric, channels) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun compareDistortion(
        image: NativeMagickImage,
        metric: ErrorMetric,
        channels: Channels,
    ): Double = checkException { ptr.compareDistortion(image.ptr, metric, channels) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun composite(
        image: NativeMagickImage,
        x: Long,
        y: Long,
        compose: CompositeOperator,
        channels: Channels,
    ): Unit = checkException { ptr.composite(image.ptr, x.convert(), y.convert(), compose, channels) }

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
    ): Unit = checkException { ptr.compositeGravity(image.ptr, gravity, x.convert(), y.convert(), compose, channels) }

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
    public fun convolve(matrix: NativeDoubleMatrix): Unit = checkException { ptr.convolve(matrix.ptr) }.selfUpdate()

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun copyPixels(
        source: NativeMagickImage,
        geometry: NativeMagickRectangle,
        offset: NativeOffsetInfo,
        channels: Channels,
    ): Unit = checkException { ptr.copyPixels(source.ptr, geometry, offset, channels) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun crop(
        geometry: IMagickGeometry,
        gravity: Gravity,
    ): Unit = checkException { ptr.crop(geometry.toString(), gravity) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun cycleColormap(amount: Long): Unit = checkException { ptr.cycleColormap(amount) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun decipher(passphrase: String): Unit = checkException { ptr.decipher(passphrase) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun deskew(threshold: Double): Unit = checkException { ptr.deskew(threshold) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun despeckle(): Unit = checkException { ptr.despeckle() }.selfUpdate()

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
    ): Unit = checkException { ptr.distort(method, bestfit, arguments) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun edge(radius: Double): Unit = checkException { ptr.edge(radius) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun emboss(
        radius: Double,
        sigma: Double,
    ): Unit = checkException { ptr.emboss(radius, sigma) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun encipher(passphrase: String): Unit = checkException { ptr.encipher(passphrase) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun enhance(): Unit = checkException { ptr.enhance() }.selfUpdate()

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
    ): Unit = checkException { ptr.extent(geometry.toString(), gravity) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun flip(): Unit = checkException { ptr.flip() }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun flop(): Unit = checkException { ptr.flop() }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun frame(geometry: NativeMagickRectangle): Unit = checkException { ptr.frame(geometry) }.selfUpdate()

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun fx(
        expression: String,
        channels: Channels,
    ): Unit = checkException { ptr.fx(expression, channels) }.selfUpdate()

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
    ): Unit = checkException { ptr.gaussianBlur(radius, sigma, channels) }.selfUpdate()

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
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun haldClut(
        image: NativeMagickImage,
        channels: Channels,
    ): Unit = checkException { ptr.haldClut(image.ptr, channels) }

    public inline fun hasChannel(channel: PixelChannel): Boolean = ptr.hasChannel(channel)

    public inline fun hasProfile(name: String): Boolean = ptr.hasProfile(name)

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun houghLine(
        width: ULong,
        height: ULong,
        threshold: ULong,
    ): Unit = checkException { ptr.houghLine(width, height, threshold) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun implode(
        amount: Double,
        method: PixelInterpolateMethod,
    ): Unit = checkException { ptr.implode(amount, method) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun interpolativeResize(
        geometry: IMagickGeometry,
        method: PixelInterpolateMethod,
    ): Unit = checkException { ptr.interpolativeResize(geometry.toString(), method) }.selfUpdate()

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
    ): Unit = checkException { ptr.kuwahara(radius, sigma) }.selfUpdate()

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
    ): Unit = checkException { ptr.liquidRescale(geometry.toString(), deltaX, rigidity) }.selfUpdate()

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun localContrast(
        radius: Double,
        strength: Double,
        channels: Channels,
    ): Unit = checkException { ptr.localContrast(radius, strength, channels) }.selfUpdate()

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun lower(size: ULong): Unit = checkException { ptr.lower(size) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun magnify(): Unit = checkException { ptr.magnify() }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun map(
        image: NativeMagickImage,
        settings: NativeQuantizeSettings,
    ): Boolean = checkException { ptr.map(image.ptr, settings) }

    @ExperimentalContracts
    public fun meanShift(
        width: UInt,
        height: UInt,
        colorDistance: Percentage,
    ) {
        checkException {
            ptr.meanShift(
                width.convert(),
                height.convert(),
                PercentageHelper.toQuantum(colorDistance),
            )
        }.selfUpdate()
    }

    @ExperimentalContracts
    public fun minify(): Unit = checkException { ptr.minify() }.selfUpdate()

    @ExperimentalContracts
    public fun minimumBoundingBox(): PointInfoCollection =
        checkException {
            ptr.minimumBoundingBox()
        }

    @ExperimentalContracts
    public fun modulate(modulate: String) {
        checkException { ptr.modulate(modulate) }
    }

    @ExperimentalNativeApi
    @ExperimentalContracts
    public fun morphology(
        method: MorphologyMethod,
        userKernel: String,
        channels: Channels,
        iterations: Int,
    ): Unit = checkException { ptr.morphology(method, userKernel, channels, iterations) }.selfUpdate()

    @ExperimentalContracts
    public fun moments(): NativeMoments = checkException { ptr.moments() }

    @ExperimentalContracts
    public fun motionBlur(
        radius: Double,
        sigma: Double,
        angle: Double,
    ): Unit = checkException { ptr.motionBlur(radius, sigma, angle) }.selfUpdate()

    @ExperimentalContracts
    @ExperimentalNativeApi
    public fun negate(
        onlyGrayscale: Boolean,
        channels: Channels,
    ): Unit = checkException { ptr.negate(onlyGrayscale, channels) }

    @ExperimentalContracts
    public fun normalize(): Unit = checkException { ptr.normalize() }

    @ExperimentalContracts
    public fun oilPaint(
        radius: Double,
        sigma: Double,
    ): Unit = checkException { ptr.oilPaint(radius, sigma) }.selfUpdate()

    @ExperimentalContracts
    @ExperimentalNativeApi
    public fun orderedDither(
        thresholdMap: String,
        channels: Channels,
    ): Unit = checkException { ptr.orderedDither(thresholdMap, channels) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    public fun perceptible(
        epsilon: Double,
        channels: Channels,
    ): Unit = checkException { ptr.perceptible(epsilon, channels) }

    @ExperimentalContracts
    public fun perceptualHash(): NativePerceptualHashList = checkException { ptr.perceptualHash() }.let { NativePerceptualHashList(it) }

    @ExperimentalContracts
    public fun polaroid(
        settings: NativeDrawingSettings,
        caption: String,
        angle: Double,
        method: PixelInterpolateMethod,
    ) {
        checkException { ptr.polaroid(settings.ptr, caption, angle, method) }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    public fun posterize(
        levels: UInt,
        method: DitherMethod,
        channels: Channels,
    ): Unit = checkException { ptr.posterize(levels, method, channels) }

    @ExperimentalContracts
    public fun quantize(settings: NativeQuantizeSettings): Unit = checkException { ptr.quantize(settings.ptr) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun raise(size: ULong): Unit = checkException { ptr.raise(size) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun readBlob(
        settings: NativeMagickSettings,
        data: UByteArray,
        offset: UInt,
        length: UInt,
    ): Unit = checkException { settings.ptr.readBlob(data, offset.convert(), length.convert()) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun readFile(settings: NativeMagickSettings): Unit = checkException { settings.ptr.readFile() }.selfUpdate()

//    @ExperimentalContracts
//    public fun readStream(
//        settings: NativeMagickSettings,
//        wrapper: Wrapper,
// //        reader: Reader?,
// //        seeker: CustomStreamSeeker?,
// //        teller: CustomStreamTeller?,
//    ) {
//        wrapper.userData()
//        wrapper.reader
//
//        checkException {
//            settings.ptr.readStream(userData, reader, seeker, teller)
//        }.selfUpdate()
//    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun regionMask(region: NativeMagickRectangle?): Unit = checkException { ptr.regionMask(region) }

    public inline fun removeArtifact(name: String): Unit = ptr.removeArtifact(name)

    public inline fun removeAttribute(name: String): Unit = ptr.removeAttribute(name)

    public inline fun removeProfile(name: String): Unit = ptr.removeProfile(name)

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun resample(
        resolutionX: Double,
        resolutionY: Double,
    ) {
        checkException { ptr.resample(resolutionX, resolutionY) }.selfUpdate()
    }

    public inline fun resetArtifactIterator(): Unit = ptr.resetArtifactIterator()

    public inline fun resetAttributeIterator(): Unit = ptr.resetAttributeIterator()

    public inline fun resetProfileIterator(): Unit = ptr.resetProfileIterator()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun resize(geometry: String): Unit = checkException { ptr.resize(geometry) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun roll(
        x: Long,
        y: Long,
    ): Unit = checkException { ptr.roll(x.convert(), y.convert()) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun rotate(degrees: Double): Unit = checkException { ptr.rotate(degrees) }.selfUpdate()

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun rotationalBlur(
        angle: Double,
        channels: Channels,
    ): Unit = checkException { ptr.rotationalBlur(angle, channels) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun sample(geometry: String): Unit = checkException { ptr.sample(geometry) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun scale(geometry: String): Unit = checkException { ptr.scale(geometry) }.selfUpdate()

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
    ): Unit = checkException { ptr.selectiveBlur(radius, sigma, threshold, channels) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun sepiaTone(threshold: Double): Unit = checkException { ptr.sepiaTone(threshold) }.selfUpdate()

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
    public fun setColorMetric(image: NativeMagickImage): Boolean = checkException { ptr.setColorMetric(image.ptr) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun setProfile(
        name: String,
        datum: UByteArray,
    ): Unit = checkException { ptr.setProfile(name, datum) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun setReadMask(image: NativeMagickImage?): Unit = checkException { ptr.setReadMask(image?.ptr) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun setWriteMask(image: NativeMagickImage?): Unit = checkException { ptr.setWriteMask(image?.ptr) }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun shade(
        azimuth: Double,
        elevation: Double,
        colorShading: Boolean,
        channels: Channels,
    ): Unit = checkException { ptr.shade(azimuth, elevation, colorShading, channels) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun shadow(
        x: Long,
        y: Long,
        sigma: Double,
        alphaPercentage: Double,
    ): Unit = checkException { ptr.shadow(x.convert(), y.convert(), sigma, alphaPercentage) }.selfUpdate()

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun sharpen(
        radius: Double,
        sigma: Double,
        channels: Channels,
    ): Unit = checkException { ptr.sharpen(radius, sigma, channels) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun shave(
        leftRight: ULong,
        bottomLeft: ULong,
    ): Unit = checkException { ptr.shave(leftRight, bottomLeft) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun shear(
        xAngle: Double,
        yAngle: Double,
    ): Unit = checkException { ptr.shear(xAngle, yAngle) }.selfUpdate()

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
    ): Unit = checkException { ptr.sketch(radius, sigma, angle) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun solarize(factor: Double): Unit = checkException { ptr.solarize(factor) }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun sortPixels(): Unit = checkException { ptr.sortPixels() }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun splice(geometry: NativeMagickRectangle): Unit = checkException { ptr.splice(geometry) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun spread(
        method: PixelInterpolateMethod,
        radius: Double,
    ): Unit {
        checkException { ptr.spread(method, radius) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun statistic(
        type: StatisticType,
        width: ULong,
        height: ULong,
    ): Unit {
        checkException { ptr.statistic(type, width, height) }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun statistics(channels: Channels): CPointer<ChannelStatistics> {
        return checkException { ptr.statistics(channels) }
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun stegano(watermark: NativeMagickImage): Unit {
        checkException { ptr.stegano(watermark.ptr) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun stereo(rightImage: NativeMagickImage): Unit {
        checkException { ptr.stereo(rightImage.ptr) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun strip(): Unit {
        checkException { ptr.strip() }
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun swirl(
        method: PixelInterpolateMethod,
        degrees: Double,
    ): Unit {
        checkException { ptr.swirl(method, degrees) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun texture(image: NativeMagickImage): Unit {
        checkException { ptr.texture(image.ptr) }
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun threshold(
        percentage: Percentage,
        channels: Channels,
    ): Unit {
        checkException { ptr.threshold(PercentageHelper.toQuantum(percentage), channels) }
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun thumbnail(geometry: String): Unit = checkException { ptr.thumbnail(geometry) }.selfUpdate()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun transpose() {
        checkException { ptr.transpose() }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun transverse() {
        checkException { ptr.transverse() }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun trim() {
        checkException { ptr.trim() }.selfUpdate()
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun unsharpMask(
        radius: Double,
        sigma: Double,
        amount: Double,
        threshold: Double,
        channels: Channels,
    ) {
        checkException { ptr.unsharpMask(radius, sigma, amount, threshold, channels) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun vignette(
        radius: Double,
        sigma: Double,
        x: Long,
        y: Long,
    ) {
        checkException { ptr.vignette(radius, sigma, x.convert(), y.convert()) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun wave(method: PixelInterpolateMethod, amplitude: Double, length: Double): Unit {
        checkException { ptr.wave(method, amplitude, length) }.selfUpdate()
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun whiteBalance(): Unit {
        checkException { ptr.whiteBalance() }
    }

    public fun whiteThreshold(threshold: String, channels: Channels): Unit {
        checkException { ptr.whiteBalance() }
    }

    @ExperimentalContracts
    public fun writeStream(
        settings: NativeMagickSettings,
        userData: StableRef<Any>,
        writer: CustomStreamHandler?,
        seeker: CustomStreamSeeker?,
        teller: CustomStreamTeller?,
        reader: CustomStreamHandler?,
    ) {
        checkException {
            ptr.writeStream(settings, userData, writer, seeker, teller, reader)
        }
    }

    private inline fun CPointer<Image>?.selfUpdate() {
        this?.let { ptr = it }
    }
}
