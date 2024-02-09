package imagemagick.magicknative

import imagemagick.QuantumType
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
import imagemagick.core.toMagick
import imagemagick.core.toNative
import imagemagick.magicknative.NativeChannels.Companion.toNative
import imagemagick.magicknative.colors.NativeMagickColor
import imagemagick.magicknative.exceptions.ExceptionInfoPtrVar
import imagemagick.magicknative.exceptions.withException
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
import kotlinx.cinterop.cstr
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.pointed
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toKString
import kotlinx.cinterop.usePinned
import kotlinx.cinterop.zeroValue
import libMagickNative.Image
import libMagickNative.MagickImage_AdaptiveBlur
import libMagickNative.MagickImage_AdaptiveResize
import libMagickNative.MagickImage_AdaptiveSharpen
import libMagickNative.MagickImage_AdaptiveThreshold
import libMagickNative.MagickImage_AddNoise
import libMagickNative.MagickImage_AffineTransform
import libMagickNative.MagickImage_AnimationDelay_Get
import libMagickNative.MagickImage_AnimationDelay_Set
import libMagickNative.MagickImage_AnimationIterations_Get
import libMagickNative.MagickImage_AnimationIterations_Set
import libMagickNative.MagickImage_AnimationTicksPerSecond_Get
import libMagickNative.MagickImage_AnimationTicksPerSecond_Set
import libMagickNative.MagickImage_Annotate
import libMagickNative.MagickImage_AnnotateGravity
import libMagickNative.MagickImage_AutoGamma
import libMagickNative.MagickImage_AutoLevel
import libMagickNative.MagickImage_AutoOrient
import libMagickNative.MagickImage_AutoThreshold
import libMagickNative.MagickImage_BackgroundColor_Get
import libMagickNative.MagickImage_BackgroundColor_Set
import libMagickNative.MagickImage_BaseHeight_Get
import libMagickNative.MagickImage_BaseWidth_Get
import libMagickNative.MagickImage_BilateralBlur
import libMagickNative.MagickImage_BlackPointCompensation_Get
import libMagickNative.MagickImage_BlackPointCompensation_Set
import libMagickNative.MagickImage_BorderColor_Get
import libMagickNative.MagickImage_BorderColor_Set
import libMagickNative.MagickImage_BoundingBox_Get
import libMagickNative.MagickImage_ChannelCount_Get
import libMagickNative.MagickImage_ChromaBlue_Get
import libMagickNative.MagickImage_ChromaBlue_Set
import libMagickNative.MagickImage_ChromaGreen_Get
import libMagickNative.MagickImage_ChromaGreen_Set
import libMagickNative.MagickImage_ChromaRed_Get
import libMagickNative.MagickImage_ChromaRed_Set
import libMagickNative.MagickImage_ChromaWhite_Get
import libMagickNative.MagickImage_ChromaWhite_Set
import libMagickNative.MagickImage_ClassType_Get
import libMagickNative.MagickImage_ClassType_Set
import libMagickNative.MagickImage_Clone
import libMagickNative.MagickImage_ColorFuzz_Get
import libMagickNative.MagickImage_ColorFuzz_Set
import libMagickNative.MagickImage_ColorSpace_Get
import libMagickNative.MagickImage_ColorSpace_Set
import libMagickNative.MagickImage_ColorType_Get
import libMagickNative.MagickImage_ColorType_Set
import libMagickNative.MagickImage_ColormapSize_Get
import libMagickNative.MagickImage_ColormapSize_Set
import libMagickNative.MagickImage_Compose_Get
import libMagickNative.MagickImage_Compose_Set
import libMagickNative.MagickImage_Compression_Get
import libMagickNative.MagickImage_Compression_Set
import libMagickNative.MagickImage_Create
import libMagickNative.MagickImage_Depth_Get
import libMagickNative.MagickImage_Depth_Set
import libMagickNative.MagickImage_Dispose
import libMagickNative.MagickImage_EncodingGeometry_Get
import libMagickNative.MagickImage_Endian_Get
import libMagickNative.MagickImage_Endian_Set
import libMagickNative.MagickImage_FileName_Get
import libMagickNative.MagickImage_FileName_Set
import libMagickNative.MagickImage_FilterType_Get
import libMagickNative.MagickImage_FilterType_Set
import libMagickNative.MagickImage_Format_Get
import libMagickNative.MagickImage_Format_Set
import libMagickNative.MagickImage_Gamma_Get
import libMagickNative.MagickImage_GetAttribute
import libMagickNative.MagickImage_GetNextArtifactName
import libMagickNative.MagickImage_GetNextAttributeName
import libMagickNative.MagickImage_GetNextProfileName
import libMagickNative.MagickImage_GifDisposeMethod_Get
import libMagickNative.MagickImage_GifDisposeMethod_Set
import libMagickNative.MagickImage_HasAlpha_Get
import libMagickNative.MagickImage_HasAlpha_Set
import libMagickNative.MagickImage_HasChannel
import libMagickNative.MagickImage_Height_Get
import libMagickNative.MagickImage_Interlace_Get
import libMagickNative.MagickImage_Interlace_Set
import libMagickNative.MagickImage_Interpolate_Get
import libMagickNative.MagickImage_Interpolate_Set
import libMagickNative.MagickImage_IsOpaque_Get
import libMagickNative.MagickImage_MatteColor_Get
import libMagickNative.MagickImage_MatteColor_Set
import libMagickNative.MagickImage_MeanErrorPerPixel_Get
import libMagickNative.MagickImage_NormalizedMaximumError_Get
import libMagickNative.MagickImage_NormalizedMeanError_Get
import libMagickNative.MagickImage_Orientation_Get
import libMagickNative.MagickImage_Orientation_Set
import libMagickNative.MagickImage_Page_Get
import libMagickNative.MagickImage_Page_Set
import libMagickNative.MagickImage_Quality_Get
import libMagickNative.MagickImage_Quality_Set
import libMagickNative.MagickImage_ReadBlob
import libMagickNative.MagickImage_ReadFile
import libMagickNative.MagickImage_RemoveArtifact
import libMagickNative.MagickImage_RemoveAttribute
import libMagickNative.MagickImage_RemoveProfile
import libMagickNative.MagickImage_RenderingIntent_Get
import libMagickNative.MagickImage_RenderingIntent_Set
import libMagickNative.MagickImage_ResetArtifactIterator
import libMagickNative.MagickImage_ResetAttributeIterator
import libMagickNative.MagickImage_ResetProfileIterator
import libMagickNative.MagickImage_ResolutionUnits_Get
import libMagickNative.MagickImage_ResolutionUnits_Set
import libMagickNative.MagickImage_ResolutionX_Get
import libMagickNative.MagickImage_ResolutionX_Set
import libMagickNative.MagickImage_ResolutionY_Get
import libMagickNative.MagickImage_ResolutionY_Set
import libMagickNative.MagickImage_SetAlpha
import libMagickNative.MagickImage_SetAttribute
import libMagickNative.MagickImage_Signature_Get
import libMagickNative.MagickImage_TotalColors_Get
import libMagickNative.MagickImage_VirtualPixelMethod_Get
import libMagickNative.MagickImage_VirtualPixelMethod_Set
import libMagickNative.MagickImage_Width_Get
import libMagickNative.RectangleInfo
import okio.BufferedSource
import platform.posix.size_t
import platform.posix.ssize_t
import imagemagick.core.settings.MagickSettings as IMagickSettings
import imagemagick.core.types.MagickGeometry as IMagickGeometry


@ExperimentalNativeApi
@ExperimentalStdlibApi
@ExperimentalForeignApi
@ExperimentalContracts
public class NativeMagickImage : NativeInstance<Image>, AutoCloseable {
    public var ptr: CPointer<Image> = zero
        get() {
            if (field === zero) {
                throw UnsupportedOperationException()
            }

            return field
        }
        private set(value) {
            if (field !== zero) {
                field.dispose()
            }
            field = value
        }

    public constructor(instance: CPointer<Image>) {
        ptr = instance
    }

    public constructor(settings: NativeMagickSettings) {
        ptr = memScoped {
            val exception = alloc<ExceptionInfoPtrVar>()

            val instance = MagickImage_Create(settings.ptr, exception.ptr)

            // TODO Check exception

            instance
        } ?: error("Failed to instantiate native MagickImage")
    }

    override fun close() {
        dispose()
    }

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

    public inline var classType: ClassType
        get() = ptr.classType()
        set(value) = ptr.classType(value)

    public inline var colorFuzz: Double
        get() = ptr.colorFuzz()
        set(value) = ptr.colorFuzz(value)

    public inline var colormapSize: UInt
        get() = ptr.colormapSize().toUInt()
        set(value) = ptr.colormapSize(value.toLong())

    public inline var colorSpace: ColorSpace
        get() = ptr.colorSpace()
        set(value) = ptr.colorSpace(value)

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

    public inline val isOpaque: Boolean
        get() = ptr.isOpaque()

    public inline var matteColor: NativeMagickColor?
        get() = ptr.matteColor()
        set(value) = ptr.matteColor(value)

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

    public inline val signature: String
        get() = ptr.signature()

    public inline val totalColors: UInt
        get() = ptr.totalColors().toUInt()

    public inline var virtualPixelMethod: VirtualPixelMethod
        get() = ptr.virtualPixelMethod()
        set(value) = ptr.virtualPixelMethod(value)

    public inline val width: UInt
        get() = ptr.width().toUInt()

    @Throws(MagickException::class)
    public fun adaptiveBlur(radius: Double, sigma: Double): Unit {
        ptr.adaptiveBlur(radius, sigma)?.let {
            ptr = it
        }
    }

    @Throws(MagickException::class)
    public fun adaptiveResize(geometry: IMagickGeometry): Unit {
        ptr.adaptiveResize(geometry.toString())?.let {
            ptr = it
        }
    }

    @Throws(MagickException::class)
    public fun adaptiveSharpen(radius: Double, sigma: Double, channels: Channels): Unit {
        ptr.adaptiveSharpen(radius, sigma, channels.toNative())?.let {
            ptr = it
        }
    }

    @Throws(MagickException::class)
    public fun adaptiveThreshold(with: UInt, height: UInt, bias: Double, channels: Channels): Unit {
        ptr.adaptiveThreshold(with.toULong(), height.toULong(), bias, channels.toNative())?.let {
            ptr = it
        }
    }

    @Throws(MagickException::class)
    public fun addNoise(noiseType: NoiseType, attenuate: Double, channels: Channels): Unit {
        ptr.addNoise(noiseType, attenuate, channels.toNative())?.let {
            ptr = it
        }
    }

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

    @Throws(MagickException::class)
    public inline fun annotate(
        settings: NativeDrawingSettings,
        text: String,
        boundingArea: String,
        gravity: Gravity,
        degrees: Double,
    ): Unit = ptr.annotate(settings, text, boundingArea, gravity, degrees)

    @Throws(MagickException::class)
    public inline fun annotateGravity(settings: NativeDrawingSettings, text: String, gravity: Gravity): Unit =
        ptr.annotateGravity(settings, text, gravity)

    @Throws(MagickException::class)
    public inline fun autoGamma(channels: Channels): Unit = ptr.autoGamma(channels.toNative())

    @Throws(MagickException::class)
    public inline fun autoLevel(channels: Channels): Unit = ptr.autoLevel(channels.toNative())

    @Throws(MagickException::class)
    public inline fun autoOrient(): Unit = ptr.autoOrient()

    @Throws(MagickException::class)
    public inline fun autoThreshold(method: AutoThresholdMethod): Unit = ptr.autoThreshold(method)

    @Throws(MagickException::class)
    public fun bilateralBlur(width: UInt, height: UInt, intensitySigma: Double, spatialSigma: Double) {
        ptr.bilateralBlur(width.toULong(), height.toULong(), intensitySigma, spatialSigma)?.let {
            ptr = it
        }
    }

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

    @Throws(MagickException::class)
    public inline fun setAlpha(value: AlphaOption): Unit = ptr.setAlpha(value)

    @Throws(MagickException::class)
    public inline fun setAttribute(name: String, value: String): Unit = ptr.setAttribute(name, value)

    public companion object {
        internal val zero: CPointer<Image> = memScoped {
            zeroValue<Image>().ptr
        }

        @Throws(MagickException::class, RuntimeException::class)
        public inline fun CPointer<Image>.clone(): CPointer<Image> = withException { _, exceptionInfo ->
            MagickImage_Clone(this@clone, exceptionInfo.ptr)
        } ?: throw RuntimeException()

        public inline fun CPointer<Image>.dispose() {
            MagickImage_Dispose(this)
        }

        //region Properties

        public inline fun CPointer<Image>.animationDelay(): size_t = MagickImage_AnimationDelay_Get(this)

        public inline fun CPointer<Image>.animationDelay(value: size_t) {
            MagickImage_AnimationDelay_Set(this, value)
        }

        public inline fun CPointer<Image>.animationIterations(): size_t = MagickImage_AnimationIterations_Get(this)

        public inline fun CPointer<Image>.animationIterations(value: size_t) {
            MagickImage_AnimationIterations_Set(this, value)
        }

        public inline fun CPointer<Image>.animationTicksPerSecond(): ssize_t =
            MagickImage_AnimationTicksPerSecond_Get(this)

        public inline fun CPointer<Image>.animationTicksPerSecond(value: ssize_t) {
            MagickImage_AnimationTicksPerSecond_Set(this, value)
        }

        public inline fun CPointer<Image>.backgroundColor(): NativeMagickColor? =
            MagickImage_BackgroundColor_Get(this)?.let {
                NativeMagickColor(it)
            }

        public inline fun CPointer<Image>.backgroundColor(value: NativeMagickColor?) {
            MagickImage_BackgroundColor_Set(this, value?.ptr)
        }

        public inline fun CPointer<Image>.baseHeight(): size_t = MagickImage_BaseHeight_Get(this)

        public inline fun CPointer<Image>.baseWidth(): size_t = MagickImage_BaseWidth_Get(this)

        public inline fun CPointer<Image>.blackPointCompensation(): Boolean =
            MagickImage_BlackPointCompensation_Get(this).toPrimitive()

        public inline fun CPointer<Image>.blackPointCompensation(value: Boolean): Unit =
            MagickImage_BlackPointCompensation_Set(this, value.toNative())

        public inline fun CPointer<Image>.borderColor(): NativeMagickColor? = MagickImage_BorderColor_Get(this)?.let {
            NativeMagickColor(it)
        }

        public inline fun CPointer<Image>.borderColor(value: NativeMagickColor?) {
            MagickImage_BorderColor_Set(this, value?.ptr)
        }

        @Throws(MagickException::class, RuntimeException::class)
        public inline fun CPointer<Image>.boundingBox(): CPointer<RectangleInfo> = withException { _, exceptionInfo ->
            MagickImage_BoundingBox_Get(this@boundingBox, exceptionInfo.ptr)
        } ?: throw RuntimeException()

        public inline fun CPointer<Image>.channelCount(): size_t = MagickImage_ChannelCount_Get(this)

        public inline fun CPointer<Image>.chromaBlue(): NativePrimaryInfo? = MagickImage_ChromaBlue_Get(this)?.let {
            NativePrimaryInfo(it)
        }

        public inline fun CPointer<Image>.chromaBlue(value: NativePrimaryInfo?): Unit =
            MagickImage_ChromaBlue_Set(this, value?.ptr)

        public inline fun CPointer<Image>.chromaGreen(): NativePrimaryInfo? = MagickImage_ChromaGreen_Get(this)?.let {
            NativePrimaryInfo(it)
        }

        public inline fun CPointer<Image>.chromaGreen(value: NativePrimaryInfo?): Unit =
            MagickImage_ChromaGreen_Set(this, value?.ptr)

        public inline fun CPointer<Image>.chromaRed(): NativePrimaryInfo? = MagickImage_ChromaRed_Get(this)?.let {
            NativePrimaryInfo(it)
        }

        public inline fun CPointer<Image>.chromaRed(value: NativePrimaryInfo?): Unit =
            MagickImage_ChromaRed_Set(this, value?.ptr)

        public inline fun CPointer<Image>.chromaWhite(): NativePrimaryInfo? = MagickImage_ChromaWhite_Get(this)?.let {
            NativePrimaryInfo(it)
        }

        public inline fun CPointer<Image>.chromaWhite(value: NativePrimaryInfo?): Unit =
            MagickImage_ChromaWhite_Set(this, value?.ptr)

        public inline fun CPointer<Image>.classType(): ClassType = memScoped {
            val exceptionInfo = alloc<ExceptionInfoPtrVar>()

            // the exception is required by the underlying API, but it never used
            // https://github.com/dlemstra/Magick.Native/discussions/38

            MagickImage_ClassType_Get(this@classType, exceptionInfo.ptr).toMagick()
        }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.classType(value: ClassType): Unit = withException { _, exceptionInfo ->
            MagickImage_ClassType_Set(this@classType, value.toNative(), exceptionInfo.ptr)
        }

        public inline fun CPointer<Image>.colorFuzz(): Double = MagickImage_ColorFuzz_Get(this)

        public inline fun CPointer<Image>.colorFuzz(value: Double): Unit = MagickImage_ColorFuzz_Set(this, value)

        public inline fun CPointer<Image>.colormapSize(): Long = memScoped {
            val exceptionInfo = alloc<ExceptionInfoPtrVar>()

            // the exception is required by the underlying API, but it never used
            // https://github.com/dlemstra/Magick.Native/discussions/38

            MagickImage_ColormapSize_Get(this@colormapSize, exceptionInfo.ptr)
        }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.colormapSize(value: Long): Unit = withException { _, exceptionInfo ->
            MagickImage_ColormapSize_Set(this@colormapSize, value, exceptionInfo.ptr)
        }

        public inline fun CPointer<Image>.colorSpace(): ColorSpace = memScoped {
            val exceptionInfo = alloc<ExceptionInfoPtrVar>()

            // the exception is required by the underlying API, but it never used
            // https://github.com/dlemstra/Magick.Native/discussions/38

            MagickImage_ColorSpace_Get(this@colorSpace, exceptionInfo.ptr).toMagick()
        }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.colorSpace(value: ColorSpace): Unit = withException { _, exceptionInfo ->
            MagickImage_ColorSpace_Set(this@colorSpace, value.toNative(), exceptionInfo.ptr)
        }

        public inline fun CPointer<Image>.colorType(): ColorType = memScoped {
            val exceptionInfo = alloc<ExceptionInfoPtrVar>()

            val value = MagickImage_ColorType_Get(this@colorType, exceptionInfo.ptr)

            // the exception is required by the underlying API, but it never used
            // https://github.com/dlemstra/Magick.Native/discussions/38

            value.toMagick()
        }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.colorType(value: ColorType): Unit = withException { _, exceptionInfo ->
            MagickImage_ColorType_Set(this@colorType, value.toNative(), exceptionInfo.ptr)
        }

        public inline fun CPointer<Image>.compose(): CompositeOperator = MagickImage_Compose_Get(this).toMagick()

        public inline fun CPointer<Image>.compose(value: CompositeOperator): Unit =
            MagickImage_Compose_Set(this, value.toNative())

        public inline fun CPointer<Image>.compression(): CompressionMethod =
            MagickImage_Compression_Get(this).toMagick()

        public inline fun CPointer<Image>.compression(value: CompressionMethod): Unit =
            MagickImage_Compression_Set(this, value.toNative())

        public inline fun CPointer<Image>.depth(): size_t = MagickImage_Depth_Get(this)

        public inline fun CPointer<Image>.depth(value: size_t): Unit = MagickImage_Depth_Set(this, value)

        public inline fun CPointer<Image>.endian(): Endian = MagickImage_Endian_Get(this).toMagick()

        public inline fun CPointer<Image>.endian(value: Endian): Unit = MagickImage_Endian_Set(this, value.toNative())

        public inline fun CPointer<Image>.encodingGeometry(): String? =
            MagickImage_EncodingGeometry_Get(this)?.toKString()

        public inline fun CPointer<Image>.fileName(): String? = MagickImage_FileName_Get(this)?.toKString()

        public inline fun CPointer<Image>.fileName(value: String?): Unit = MagickImage_FileName_Set(this, value)

        public inline fun CPointer<Image>.filterType(): FilterType = MagickImage_FilterType_Get(this).toMagick()

        public inline fun CPointer<Image>.filterType(value: FilterType): Unit =
            MagickImage_FilterType_Set(this, value.toNative())

        public inline fun CPointer<Image>.format(): String? = MagickImage_Format_Get(this)?.toKString()

        public inline fun CPointer<Image>.format(value: String?): Unit = MagickImage_Format_Set(this, value)

        public inline fun CPointer<Image>.gamma(): Double = MagickImage_Gamma_Get(this)

        public inline fun CPointer<Image>.gifDisposeMethod(): GifDisposeMethod =
            MagickImage_GifDisposeMethod_Get(this).toMagick()

        public inline fun CPointer<Image>.gifDisposeMethod(value: GifDisposeMethod): Unit =
            MagickImage_GifDisposeMethod_Set(this, value.toNative())

        public inline fun CPointer<Image>.height(): size_t = MagickImage_Height_Get(this)

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.hasAlpha(): Boolean = withException { _, exceptionInfo ->
            MagickImage_HasAlpha_Get(this@hasAlpha, exceptionInfo.ptr).toPrimitive()
        }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.hasAlpha(value: Boolean): Unit = withException { _, exceptionInfo ->
            MagickImage_HasAlpha_Set(this@hasAlpha, value.toNative(), exceptionInfo.ptr)
        }

        public inline fun CPointer<Image>.hasChannel(channel: PixelChannel): Boolean = MagickImage_HasChannel(
            this,
            channel.channel.convert(),
        ).toPrimitive()

        public inline fun CPointer<Image>.interlace(): Interlace = MagickImage_Interlace_Get(this).toMagick()

        public inline fun CPointer<Image>.interlace(value: Interlace): Unit =
            MagickImage_Interlace_Set(this, value.toNative())

        public inline fun CPointer<Image>.interpolate(): PixelInterpolateMethod =
            MagickImage_Interpolate_Get(this).toMagick()

        public inline fun CPointer<Image>.interpolate(value: PixelInterpolateMethod): Unit =
            MagickImage_Interpolate_Set(this, value.toNative())

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.isOpaque(): Boolean = withException { _, exceptionInfo ->
            MagickImage_IsOpaque_Get(this@isOpaque, exceptionInfo.ptr).toPrimitive()
        }

        public inline fun CPointer<Image>.matteColor(): NativeMagickColor? =
            MagickImage_MatteColor_Get(this)?.let { NativeMagickColor(it) }

        public inline fun CPointer<Image>.matteColor(value: NativeMagickColor?): Unit =
            MagickImage_MatteColor_Set(this, value?.ptr)

        public inline fun CPointer<Image>.meanErrorPerPixel(): Double = MagickImage_MeanErrorPerPixel_Get(this)

        public inline fun CPointer<Image>.normalizedMaximumError(): Double =
            MagickImage_NormalizedMaximumError_Get(this)

        public inline fun CPointer<Image>.normalizedMeanError(): Double = MagickImage_NormalizedMeanError_Get(this)

        public inline fun CPointer<Image>.orientation(): OrientationType = MagickImage_Orientation_Get(this).toMagick()

        public inline fun CPointer<Image>.orientation(value: OrientationType): Unit =
            MagickImage_Orientation_Set(this, value.toNative())

        public inline fun CPointer<Image>.page(): NativeMagickRectangle? =
            MagickImage_Page_Get(this)?.let { NativeMagickRectangle(it) }

        public inline fun CPointer<Image>.page(value: NativeMagickRectangle?): Unit =
            MagickImage_Page_Set(this, value?.ptr)

        public inline fun CPointer<Image>.quality(): size_t = MagickImage_Quality_Get(this)

        public inline fun CPointer<Image>.quality(value: size_t): Unit = MagickImage_Quality_Set(this, value)

        public inline fun CPointer<Image>.renderingIntent(): RenderingIntent =
            MagickImage_RenderingIntent_Get(this).toMagick()

        public inline fun CPointer<Image>.renderingIntent(value: RenderingIntent): Unit =
            MagickImage_RenderingIntent_Set(this, value.toNative())

        public inline fun CPointer<Image>.resolutionUnits(): DensityUnit =
            MagickImage_ResolutionUnits_Get(this).toMagick()

        public inline fun CPointer<Image>.resolutionUnits(value: DensityUnit): Unit =
            MagickImage_ResolutionUnits_Set(this, value.toNative())

        public inline fun CPointer<Image>.resolutionX(): Double = MagickImage_ResolutionX_Get(this)

        public inline fun CPointer<Image>.resolutionX(value: Double): Unit = MagickImage_ResolutionX_Set(this, value)

        public inline fun CPointer<Image>.resolutionY(): Double = MagickImage_ResolutionY_Get(this)

        public inline fun CPointer<Image>.resolutionY(value: Double): Unit = MagickImage_ResolutionY_Set(this, value)

        @Throws(MagickException::class, RuntimeException::class)
        public inline fun CPointer<Image>.signature(): String = withException { _, exceptionInfo ->
            MagickImage_Signature_Get(this@signature, exceptionInfo.ptr)?.toKString()
        } ?: throw RuntimeException("The string value should never be null.")

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.totalColors(): size_t = withException { _, exceptionInfo ->
            MagickImage_TotalColors_Get(this@totalColors, exceptionInfo.ptr)
        }

        public inline fun CPointer<Image>.virtualPixelMethod(): VirtualPixelMethod = memScoped {
            val exceptionInfo = alloc<ExceptionInfoPtrVar>()

            val value = MagickImage_VirtualPixelMethod_Get(this@virtualPixelMethod, exceptionInfo.ptr)

            // the exception is required by the underlying API, but it never used
            // https://github.com/dlemstra/Magick.Native/discussions/38

            value.toMagick()
        }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.virtualPixelMethod(value: VirtualPixelMethod): Unit =
            withException { _, exceptionInfo ->
                MagickImage_VirtualPixelMethod_Set(this@virtualPixelMethod, value.toNative(), exceptionInfo.ptr)
            }

        public inline fun CPointer<Image>.width(): size_t = MagickImage_Width_Get(this)

        //endregion

        //region Methods

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.adaptiveBlur(radius: Double, sigma: Double): CPointer<Image>? =
            withException { _, exceptionInfo ->
                MagickImage_AdaptiveBlur(this@adaptiveBlur, radius, sigma, exceptionInfo.ptr)
            }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.adaptiveResize(geometry: String): CPointer<Image>? =
            withException { _, exceptionInfo ->
                MagickImage_AdaptiveResize(this@adaptiveResize, geometry, exceptionInfo.ptr)
            }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.adaptiveSharpen(
            radius: Double,
            sigma: Double,
            channels: NativeChannels,
        ): CPointer<Image>? = withException { _, exceptionInfo ->
            MagickImage_AdaptiveSharpen(this@adaptiveSharpen, radius, sigma, channels.value, exceptionInfo.ptr)
        }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.adaptiveThreshold(
            with: ULong,
            height: ULong,
            bias: Double,
            channels: NativeChannels,
        ): CPointer<Image>? = withException { _, exceptionInfo ->
            MagickImage_AdaptiveThreshold(this@adaptiveThreshold, with, height, bias, channels.value, exceptionInfo.ptr)
        }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.addNoise(
            noiseType: NoiseType,
            attenuate: Double,
            channels: NativeChannels,
        ): CPointer<Image>? = withException { _, exceptionInfo ->
            MagickImage_AddNoise(this@addNoise, noiseType.toNative(), attenuate, channels.value, exceptionInfo.ptr)
        }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.affineTransform(
            scaleX: Double,
            scaleY: Double,
            shearX: Double,
            shearY: Double,
            translateX: Double,
            translateY: Double,
        ): CPointer<Image>? = withException { _, exceptionInfo ->
            MagickImage_AffineTransform(
                this@affineTransform, scaleX, scaleY, shearX, shearY, translateX, translateY, exceptionInfo.ptr
            )
        }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.annotate(
            settings: NativeDrawingSettings,
            text: String,
            boundingArea: String,
            gravity: Gravity,
            degrees: Double,
        ): Unit = withException { _, exceptionInfo ->
            MagickImage_Annotate(
                this@annotate,
                settings.ptr,
                text.cstr,
                boundingArea.cstr,
                gravity.toNative(),
                degrees,
                exceptionInfo.ptr
            )
        }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.annotateGravity(
            settings: NativeDrawingSettings,
            text: String,
            gravity: Gravity,
        ): Unit = withException { _, exceptionInfo ->
            MagickImage_AnnotateGravity(
                this@annotateGravity, settings.ptr, text.cstr, gravity.toNative(), exceptionInfo.ptr
            )
        }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.autoGamma(channels: NativeChannels): Unit =
            withException { _, exceptionInfo ->
                MagickImage_AutoGamma(this@autoGamma, channels.value, exceptionInfo.ptr)
            }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.autoLevel(channels: NativeChannels): Unit =
            withException { _, exceptionInfo ->
                MagickImage_AutoLevel(this@autoLevel, channels.value, exceptionInfo.ptr)
            }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.autoOrient(): Unit = withException { _, exceptionInfo ->
            MagickImage_AutoOrient(this@autoOrient, exceptionInfo.ptr)
        }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.autoThreshold(method: AutoThresholdMethod): Unit =
            withException { _, exceptionInfo ->
                MagickImage_AutoThreshold(this@autoThreshold, method.toNativeEnum(), exceptionInfo.ptr)
            }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.bilateralBlur(
            width: ULong,
            height: ULong,
            intensitySigma: Double,
            spatialSigma: Double,
        ): CPointer<Image>? = withException { _, exceptionInfo ->
            MagickImage_BilateralBlur(
                this@bilateralBlur,
                width,
                height,
                intensitySigma,
                spatialSigma,
                exceptionInfo.ptr
            )
        }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.getAttribute(name: String): String? = withException { _, exceptionInfo ->
            MagickImage_GetAttribute(this@getAttribute, name, exceptionInfo.ptr)?.toKString()
        }

        public inline fun CPointer<Image>.getNextArtifactName(): String? =
            MagickImage_GetNextArtifactName(this)?.toKString()

        public inline fun CPointer<Image>.getNextAttributeName(): String? =
            MagickImage_GetNextAttributeName(this)?.toKString()

        public inline fun CPointer<Image>.getNextProfileName(): String? =
            MagickImage_GetNextProfileName(this)?.toKString()

        public inline fun CPointer<Image>.removeArtifact(name: String): Unit =
            MagickImage_RemoveArtifact(this@removeArtifact, name)

        public inline fun CPointer<Image>.removeAttribute(name: String): Unit =
            MagickImage_RemoveAttribute(this@removeAttribute, name)

        public inline fun CPointer<Image>.removeProfile(name: String): Unit =
            MagickImage_RemoveProfile(this@removeProfile, name)

        public inline fun CPointer<Image>.resetArtifactIterator(): Unit = MagickImage_ResetArtifactIterator(this)

        public inline fun CPointer<Image>.resetAttributeIterator(): Unit = MagickImage_ResetAttributeIterator(this)

        public inline fun CPointer<Image>.resetProfileIterator(): Unit = MagickImage_ResetProfileIterator(this)

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.setAlpha(value: AlphaOption): Unit = withException { _, exceptionInfo ->
            MagickImage_SetAlpha(this@setAlpha, value.toNative(), exceptionInfo.ptr)
        }

        @Throws(MagickException::class)
        public inline fun CPointer<Image>.setAttribute(name: String, value: String): Unit =
            withException { _, exceptionInfo ->
                MagickImage_SetAttribute(this@setAttribute, name, value, exceptionInfo.ptr)
            }

        //endregion
    }
}
