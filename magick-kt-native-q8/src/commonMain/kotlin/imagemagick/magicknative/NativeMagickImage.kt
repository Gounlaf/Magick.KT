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
import imagemagick.bridge.depth
import imagemagick.bridge.dispose
import imagemagick.bridge.encodingGeometry
import imagemagick.bridge.endian
import imagemagick.bridge.fileName
import imagemagick.bridge.filterType
import imagemagick.bridge.format
import imagemagick.bridge.gamma
import imagemagick.bridge.getAttribute
import imagemagick.bridge.getNextArtifactName
import imagemagick.bridge.getNextAttributeName
import imagemagick.bridge.getNextProfileName
import imagemagick.bridge.gifDisposeMethod
import imagemagick.bridge.hasAlpha
import imagemagick.bridge.hasChannel
import imagemagick.bridge.height
import imagemagick.bridge.interlace
import imagemagick.bridge.interpolate
import imagemagick.bridge.isOpaque
import imagemagick.bridge.matteColor
import imagemagick.bridge.meanErrorPerPixel
import imagemagick.bridge.normalizedMaximumError
import imagemagick.bridge.normalizedMeanError
import imagemagick.bridge.orientation
import imagemagick.bridge.page
import imagemagick.bridge.quality
import imagemagick.bridge.removeArtifact
import imagemagick.bridge.removeAttribute
import imagemagick.bridge.removeProfile
import imagemagick.bridge.renderingIntent
import imagemagick.bridge.resetArtifactIterator
import imagemagick.bridge.resetAttributeIterator
import imagemagick.bridge.resetProfileIterator
import imagemagick.bridge.resolutionUnits
import imagemagick.bridge.resolutionX
import imagemagick.bridge.resolutionY
import imagemagick.bridge.setAlpha
import imagemagick.bridge.setArtifact
import imagemagick.bridge.setAttribute
import imagemagick.bridge.setColorMetric
import imagemagick.bridge.signature
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
import imagemagick.core.enums.Endian
import imagemagick.core.enums.ErrorMetric
import imagemagick.core.enums.FilterType
import imagemagick.core.enums.GifDisposeMethod
import imagemagick.core.enums.Gravity
import imagemagick.core.enums.Interlace
import imagemagick.core.enums.NoiseType
import imagemagick.core.enums.OrientationType
import imagemagick.core.enums.PixelChannel
import imagemagick.core.enums.PixelInterpolateMethod
import imagemagick.core.enums.RenderingIntent
import imagemagick.core.enums.VirtualPixelMethod
import imagemagick.core.exceptions.MagickException
import imagemagick.core.types.Percentage
import imagemagick.magicknative.colors.NativeMagickColor
import imagemagick.magicknative.exceptions.ExceptionInfoPtrVar
import imagemagick.magicknative.exceptions.withException
import imagemagick.magicknative.matrices.NativeDoubleMatrix
import imagemagick.magicknative.settings.NativeDrawingSettings
import imagemagick.magicknative.types.NativeMagickRectangle
import imagemagick.magicknative.types.NativePrimaryInfo
import kotlin.contracts.ExperimentalContracts
import kotlin.experimental.ExperimentalNativeApi
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
import libMagickNative.Image
import libMagickNative.MagickImage_Create
import libMagickNative.MagickImage_ReadBlob
import libMagickNative.MagickImage_ReadFile
import okio.BufferedSource
import platform.posix.size_t
import imagemagick.core.settings.MagickSettings as IMagickSettings
import imagemagick.core.types.MagickGeometry as IMagickGeometry


@ExperimentalStdlibApi
@ExperimentalForeignApi
public class NativeMagickImage : NativeInstance<Image>, AutoCloseable {
    public var ptr: CPointer<Image> = zero
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
        ptr = withException { _, exceptionInfo ->
            MagickImage_Create(settings.ptr, exceptionInfo.ptr)
        } ?: error("Failed to instantiate native MagickImage")
    }

    override fun close() {
        dispose()
    }

    @ExperimentalContracts
    public fun clone(): NativeMagickImage = NativeMagickImage(ptr.clone())

    override fun dispose(result: CPointer<Image>) {
        result.dispose()
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
    public inline val boundingBox: NativeMagickRectangle
        get() = NativeMagickRectangle(ptr.boundingBox())

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
    public inline var classType: ClassType
        get() = ptr.classType()
        set(value) = ptr.classType(value)

    public inline var colorFuzz: Double
        get() = ptr.colorFuzz()
        set(value) = ptr.colorFuzz(value)

    @ExperimentalContracts
    public inline var colormapSize: UInt
        get() = ptr.colormapSize().toUInt()
        set(value) = ptr.colormapSize(value.toLong())

    @ExperimentalContracts
    public inline var colorSpace: ColorSpace
        get() = ptr.colorSpace()
        set(value) = ptr.colorSpace(value)

    @ExperimentalContracts
    public inline var colorType: ColorType
        get() = ptr.colorType()
        set(value) = ptr.colorType(value)

    public inline val compression: CompressionMethod
        get() = ptr.compression()

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
    public inline var hasAlpha: Boolean
        get() = ptr.hasAlpha()
        set(value) = ptr.hasAlpha(value)

    public inline val height: UInt
        get() = ptr.height().toUInt()

    public inline var interlace: Interlace
        get() = ptr.interlace()
        set(value) = ptr.interlace(value)

    public inline var interpolate: PixelInterpolateMethod
        get() = ptr.interpolate()
        set(value) = ptr.interpolate(value)

    @ExperimentalContracts
    public inline val isOpaque: Boolean
        get() = ptr.isOpaque()

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
    public inline val signature: String
        get() = ptr.signature()

    @ExperimentalContracts
    public inline val totalColors: UInt
        get() = ptr.totalColors().toUInt()

    @ExperimentalContracts
    public inline var virtualPixelMethod: VirtualPixelMethod
        get() = ptr.virtualPixelMethod()
        set(value) = ptr.virtualPixelMethod(value)

    public inline val width: UInt
        get() = ptr.width().toUInt()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun adaptiveBlur(radius: Double, sigma: Double): Unit {
        ptr.adaptiveBlur(radius, sigma)?.let {
            ptr = it
        }
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun adaptiveResize(geometry: IMagickGeometry): Unit {
        ptr.adaptiveResize(geometry.toString())?.let {
            ptr = it
        }
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun adaptiveSharpen(radius: Double, sigma: Double, channels: Channels): Unit {
        ptr.adaptiveSharpen(radius, sigma, channels)?.let {
            ptr = it
        }
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun adaptiveThreshold(with: UInt, height: UInt, bias: Double, channels: Channels): Unit {
        ptr.adaptiveThreshold(with.toULong(), height.toULong(), bias, channels)?.let {
            ptr = it
        }
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun addNoise(noiseType: NoiseType, attenuate: Double, channels: Channels): Unit {
        ptr.addNoise(noiseType, attenuate, channels)?.let {
            ptr = it
        }
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
    ): Unit {
        ptr.affineTransform(scaleX, scaleY, shearX, shearY, translateX, translateY)?.let {
            ptr = it
        }
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public inline fun annotate(
        settings: NativeDrawingSettings,
        text: String,
        boundingArea: String,
        gravity: Gravity,
        degrees: Double,
    ): Unit = ptr.annotate(settings, text, boundingArea, gravity, degrees)

    @ExperimentalContracts
    @Throws(MagickException::class)
    public inline fun annotateGravity(settings: NativeDrawingSettings, text: String, gravity: Gravity): Unit =
        ptr.annotateGravity(settings, text, gravity)

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public inline fun autoGamma(channels: Channels): Unit = ptr.autoGamma(channels)

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public inline fun autoLevel(channels: Channels): Unit = ptr.autoLevel(channels)

    @ExperimentalContracts
    @Throws(MagickException::class)
    public inline fun autoOrient(): Unit = ptr.autoOrient()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public inline fun autoThreshold(method: AutoThresholdMethod): Unit = ptr.autoThreshold(method)

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun bilateralBlur(width: UInt, height: UInt, intensitySigma: Double, spatialSigma: Double) {
        ptr.bilateralBlur(width.toULong(), height.toULong(), intensitySigma, spatialSigma)?.let { ptr = it }
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public inline fun blackThreshold(threshold: String, channels: Channels): Unit =
        ptr.blackThreshold(threshold, channels)

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun blueShift(factor: Double): Unit {
        ptr.blueShift(factor)?.let { ptr = it }
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public fun blur(radius: Double, sigma: Double, channels: Channels): Unit {
        ptr.blur(radius, sigma, channels)?.let { ptr = it }
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun border(value: NativeMagickRectangle): Unit {
        ptr.border(value)?.let { ptr = it }
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public inline fun brightnessContrast(brigthness: Double, contrast: Double, channels: Channels): Unit =
        ptr.brightnessContrast(brigthness, contrast, channels)

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun cannyEdge(radius: Double, sigma: Double, lower: Percentage, upper: Percentage): Unit {
        ptr.cannyEdge(radius, sigma, lower.toDouble() / 100, upper.toDouble() / 100)?.let { ptr = it }
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun charcoal(radius: Double, sigma: Double): Unit {
        ptr.charcoal(radius, sigma)?.let { ptr = it }
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun chop(geometry: NativeMagickRectangle): Unit {
        ptr.chop(geometry)?.let { ptr = it }
    }

    @ExperimentalContracts
    @Throws(MagickException::class)
    public inline fun clahe(xTiles: ULong, yTiles: ULong, numberBins: ULong, clipLimit: Double): Unit =
        ptr.clahe(xTiles, yTiles, numberBins, clipLimit)

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public inline fun clamp(channels: Channels): Unit = ptr.clamp(channels)

    @ExperimentalContracts
    @Throws(MagickException::class)
    public inline fun clipPath(pathName: String, inside: Boolean): Unit = ptr.clipPath(pathName, inside)

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public inline fun clut(image: NativeMagickImage, method: PixelInterpolateMethod, channels: Channels): Unit =
        ptr.clut(image, method, channels)

    @ExperimentalContracts
    @Throws(MagickException::class)
    public inline fun colorDecisionList(fileName: String): Unit = ptr.colorDecisionList(fileName)

    @ExperimentalContracts
    @Throws(MagickException::class)
    public fun colorMatrix(matrix: NativeDoubleMatrix): Unit {
        ptr.colorMatrix(matrix)?.let { ptr = it }
    }

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public inline fun compare(image: NativeMagickImage, metric: ErrorMetric, channels: Channels): Pair<Double, CPointer<Image>?> =
        ptr.compare(image, metric, channels)

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public inline fun compareDistortion(
        image: NativeMagickImage,
        metric: ErrorMetric,
        channels: Channels,
    ): Double = ptr.compareDistortion(image, metric, channels)

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public inline fun composite(
        image: NativeMagickImage,
        x: Long,
        y: Long,
        compose: CompositeOperator,
        channels: Channels,
    ): Unit = ptr.composite(image, x, y, compose, channels)

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public inline fun compositeGravity(
        image: NativeMagickImage,
        gravity: Gravity,
        x: Long,
        y: Long,
        compose: CompositeOperator,
        channels: Channels,
    ): Unit = ptr.compositeGravity(image, gravity, x, y, compose, channels)

    @ExperimentalContracts
    @Throws(MagickException::class)
    public inline fun contrast(enhance: Boolean): Unit = ptr.contrast(enhance)

    @ExperimentalContracts
    @ExperimentalNativeApi
    @Throws(MagickException::class)
    public inline fun contrastStretch(blackPoint: Double, whitePoint: Double, channels: Channels): Unit =
        ptr.contrastStretch(blackPoint, whitePoint, channels)

    @ExperimentalContracts
    @Throws(MagickException::class)
    public inline fun getAttribute(name: String): String? = ptr.getAttribute(name)

    public inline fun hasChannel(channel: PixelChannel): Boolean = ptr.hasChannel(channel)

    public inline fun getNextArtifactName(): String? = ptr.getNextArtifactName()

    public inline fun getNextAttributeName(): String? = ptr.getNextAttributeName()

    public inline fun getNextProfileName(): String? = ptr.getNextProfileName()

    // Diff from Magick.NET: a native version required in parameters
    public fun readBlob(
        settings: NativeMagickSettings?,
        data: UByteArray,
        offset: UInt,
        length: UInt,
    ): Unit = memScoped {
        val exceptionInfo = alloc<ExceptionInfoPtrVar>()

        val image = data.usePinned { pinnedData ->
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
    public fun readFile(settings: NativeMagickSettings?): Unit = memScoped {
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

    public inline fun removeArtifact(name: String): Unit = ptr.removeArtifact(name)

    public inline fun removeAttribute(name: String): Unit = ptr.removeAttribute(name)

    public inline fun removeProfile(name: String): Unit = ptr.removeProfile(name)

    public inline fun resetArtifactIterator(): Unit = ptr.resetArtifactIterator()

    public inline fun resetAttributeIterator(): Unit = ptr.resetAttributeIterator()

    public inline fun resetProfileIterator(): Unit = ptr.resetProfileIterator()

    @ExperimentalContracts
    @Throws(MagickException::class)
    public inline fun setAlpha(value: AlphaOption): Unit = ptr.setAlpha(value)

    public inline fun setArtifact(name: String, value: String): Unit = ptr.setArtifact(name, value)

    @ExperimentalContracts
    @Throws(MagickException::class)
    public inline fun setAttribute(name: String, value: String): Unit = ptr.setAttribute(name, value)

    @ExperimentalContracts
    @Throws(MagickException::class)
    public inline fun setColorMetric(image: NativeMagickImage): Boolean = ptr.setColorMetric(image)

    public companion object {
        public data class CompareResult(
            /** Difference image instance */
            public val result: CPointer<Image>?,
            public val distortion: Double,
        )

        internal val zero: CPointer<Image> = memScoped {
            zeroValue<Image>().ptr
        }
    }
}
