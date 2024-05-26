@file:Suppress("KDocMissingDocumentation")

package imagemagick.bridge

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
import imagemagick.core.toMagick
import imagemagick.core.toNative
import imagemagick.magicknative.NativeChannels.toNative
import imagemagick.magicknative.NativeMagickSettings
import imagemagick.magicknative.colors.NativeMagickColor
import imagemagick.magicknative.exceptions.ExceptionInfoPtrVar
import imagemagick.magicknative.exceptions.withException
import imagemagick.magicknative.settings.NativeDrawingSettings
import imagemagick.magicknative.settings.NativeQuantizeSettings
import imagemagick.magicknative.statistics.NativeMoments
import imagemagick.magicknative.toNative
import imagemagick.magicknative.toPrimitive
import imagemagick.magicknative.types.NativeMagickRectangle
import imagemagick.magicknative.types.NativeOffsetInfo
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.DoubleVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.alloc
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toKString
import kotlinx.cinterop.usePinned
import kotlinx.cinterop.value
import libMagickNative.ChannelPerceptualHash
import libMagickNative.ChannelStatistics
import libMagickNative.CustomStreamHandler
import libMagickNative.CustomStreamSeeker
import libMagickNative.CustomStreamTeller
import libMagickNative.DrawInfo
import libMagickNative.Image
import libMagickNative.KernelInfo
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
import libMagickNative.MagickImage_BlackThreshold
import libMagickNative.MagickImage_BlueShift
import libMagickNative.MagickImage_Blur
import libMagickNative.MagickImage_Border
import libMagickNative.MagickImage_BorderColor_Get
import libMagickNative.MagickImage_BorderColor_Set
import libMagickNative.MagickImage_BoundingBox_Get
import libMagickNative.MagickImage_BrightnessContrast
import libMagickNative.MagickImage_CannyEdge
import libMagickNative.MagickImage_ChannelCount_Get
import libMagickNative.MagickImage_Charcoal
import libMagickNative.MagickImage_Chop
import libMagickNative.MagickImage_ChromaBlue_Get
import libMagickNative.MagickImage_ChromaBlue_Set
import libMagickNative.MagickImage_ChromaGreen_Get
import libMagickNative.MagickImage_ChromaGreen_Set
import libMagickNative.MagickImage_ChromaRed_Get
import libMagickNative.MagickImage_ChromaRed_Set
import libMagickNative.MagickImage_ChromaWhite_Get
import libMagickNative.MagickImage_ChromaWhite_Set
import libMagickNative.MagickImage_Clahe
import libMagickNative.MagickImage_Clamp
import libMagickNative.MagickImage_ClassType_Get
import libMagickNative.MagickImage_ClassType_Set
import libMagickNative.MagickImage_ClipPath
import libMagickNative.MagickImage_Clone
import libMagickNative.MagickImage_Clut
import libMagickNative.MagickImage_ColorDecisionList
import libMagickNative.MagickImage_ColorFuzz_Get
import libMagickNative.MagickImage_ColorFuzz_Set
import libMagickNative.MagickImage_ColorMatrix
import libMagickNative.MagickImage_ColorSpace_Get
import libMagickNative.MagickImage_ColorSpace_Set
import libMagickNative.MagickImage_ColorType_Get
import libMagickNative.MagickImage_ColorType_Set
import libMagickNative.MagickImage_ColormapSize_Get
import libMagickNative.MagickImage_ColormapSize_Set
import libMagickNative.MagickImage_Compare
import libMagickNative.MagickImage_CompareDistortion
import libMagickNative.MagickImage_Compose_Get
import libMagickNative.MagickImage_Compose_Set
import libMagickNative.MagickImage_Composite
import libMagickNative.MagickImage_CompositeGravity
import libMagickNative.MagickImage_Compression_Get
import libMagickNative.MagickImage_Compression_Set
import libMagickNative.MagickImage_Contrast
import libMagickNative.MagickImage_ContrastStretch
import libMagickNative.MagickImage_Convolve
import libMagickNative.MagickImage_CopyPixels
import libMagickNative.MagickImage_Crop
import libMagickNative.MagickImage_CycleColormap
import libMagickNative.MagickImage_Decipher
import libMagickNative.MagickImage_Depth_Get
import libMagickNative.MagickImage_Depth_Set
import libMagickNative.MagickImage_Deskew
import libMagickNative.MagickImage_Despeckle
import libMagickNative.MagickImage_DetermineBitDepth
import libMagickNative.MagickImage_DetermineColorType
import libMagickNative.MagickImage_Dispose
import libMagickNative.MagickImage_Distort
import libMagickNative.MagickImage_Edge
import libMagickNative.MagickImage_Emboss
import libMagickNative.MagickImage_Encipher
import libMagickNative.MagickImage_EncodingGeometry_Get
import libMagickNative.MagickImage_Endian_Get
import libMagickNative.MagickImage_Endian_Set
import libMagickNative.MagickImage_Enhance
import libMagickNative.MagickImage_Equalize
import libMagickNative.MagickImage_EvaluateFunction
import libMagickNative.MagickImage_EvaluateGeometry
import libMagickNative.MagickImage_EvaluateOperator
import libMagickNative.MagickImage_Extent
import libMagickNative.MagickImage_FileName_Get
import libMagickNative.MagickImage_FileName_Set
import libMagickNative.MagickImage_FilterType_Get
import libMagickNative.MagickImage_FilterType_Set
import libMagickNative.MagickImage_Flip
import libMagickNative.MagickImage_Flop
import libMagickNative.MagickImage_Format_Get
import libMagickNative.MagickImage_Format_Set
import libMagickNative.MagickImage_Frame
import libMagickNative.MagickImage_Fx
import libMagickNative.MagickImage_GammaCorrect
import libMagickNative.MagickImage_Gamma_Get
import libMagickNative.MagickImage_GaussianBlur
import libMagickNative.MagickImage_GetArtifact
import libMagickNative.MagickImage_GetAttribute
import libMagickNative.MagickImage_GetNextArtifactName
import libMagickNative.MagickImage_GetNextAttributeName
import libMagickNative.MagickImage_GetNextProfileName
import libMagickNative.MagickImage_GifDisposeMethod_Get
import libMagickNative.MagickImage_GifDisposeMethod_Set
import libMagickNative.MagickImage_Grayscale
import libMagickNative.MagickImage_HaldClut
import libMagickNative.MagickImage_HasAlpha_Get
import libMagickNative.MagickImage_HasAlpha_Set
import libMagickNative.MagickImage_HasChannel
import libMagickNative.MagickImage_HasProfile
import libMagickNative.MagickImage_Height_Get
import libMagickNative.MagickImage_HoughLine
import libMagickNative.MagickImage_Implode
import libMagickNative.MagickImage_Interlace_Get
import libMagickNative.MagickImage_Interlace_Set
import libMagickNative.MagickImage_Interpolate_Get
import libMagickNative.MagickImage_Interpolate_Set
import libMagickNative.MagickImage_InterpolativeResize
import libMagickNative.MagickImage_IsOpaque_Get
import libMagickNative.MagickImage_Kmeans
import libMagickNative.MagickImage_Kuwahara
import libMagickNative.MagickImage_LinearStretch
import libMagickNative.MagickImage_LiquidRescale
import libMagickNative.MagickImage_LocalContrast
import libMagickNative.MagickImage_Magnify
import libMagickNative.MagickImage_Map
import libMagickNative.MagickImage_MatteColor_Get
import libMagickNative.MagickImage_MatteColor_Set
import libMagickNative.MagickImage_MeanErrorPerPixel_Get
import libMagickNative.MagickImage_MeanShift
import libMagickNative.MagickImage_Minify
import libMagickNative.MagickImage_MinimumBoundingBox
import libMagickNative.MagickImage_Modulate
import libMagickNative.MagickImage_Moments
import libMagickNative.MagickImage_Morphology
import libMagickNative.MagickImage_MotionBlur
import libMagickNative.MagickImage_Negate
import libMagickNative.MagickImage_Normalize
import libMagickNative.MagickImage_NormalizedMaximumError_Get
import libMagickNative.MagickImage_NormalizedMeanError_Get
import libMagickNative.MagickImage_OilPaint
import libMagickNative.MagickImage_OrderedDither
import libMagickNative.MagickImage_Orientation_Get
import libMagickNative.MagickImage_Orientation_Set
import libMagickNative.MagickImage_Page_Get
import libMagickNative.MagickImage_Page_Set
import libMagickNative.MagickImage_Perceptible
import libMagickNative.MagickImage_PerceptualHash
import libMagickNative.MagickImage_Polaroid
import libMagickNative.MagickImage_Posterize
import libMagickNative.MagickImage_Quality_Get
import libMagickNative.MagickImage_Quality_Set
import libMagickNative.MagickImage_Quantize
import libMagickNative.MagickImage_RaiseOrLower
import libMagickNative.MagickImage_RegionMask
import libMagickNative.MagickImage_RemoveArtifact
import libMagickNative.MagickImage_RemoveAttribute
import libMagickNative.MagickImage_RemoveProfile
import libMagickNative.MagickImage_RenderingIntent_Get
import libMagickNative.MagickImage_RenderingIntent_Set
import libMagickNative.MagickImage_Resample
import libMagickNative.MagickImage_ResetArtifactIterator
import libMagickNative.MagickImage_ResetAttributeIterator
import libMagickNative.MagickImage_ResetProfileIterator
import libMagickNative.MagickImage_Resize
import libMagickNative.MagickImage_ResolutionUnits_Get
import libMagickNative.MagickImage_ResolutionUnits_Set
import libMagickNative.MagickImage_ResolutionX_Get
import libMagickNative.MagickImage_ResolutionX_Set
import libMagickNative.MagickImage_ResolutionY_Get
import libMagickNative.MagickImage_ResolutionY_Set
import libMagickNative.MagickImage_Roll
import libMagickNative.MagickImage_Rotate
import libMagickNative.MagickImage_RotationalBlur
import libMagickNative.MagickImage_Sample
import libMagickNative.MagickImage_Scale
import libMagickNative.MagickImage_Segment
import libMagickNative.MagickImage_SelectiveBlur
import libMagickNative.MagickImage_SepiaTone
import libMagickNative.MagickImage_SetAlpha
import libMagickNative.MagickImage_SetArtifact
import libMagickNative.MagickImage_SetAttribute
import libMagickNative.MagickImage_SetBitDepth
import libMagickNative.MagickImage_SetColorMetric
import libMagickNative.MagickImage_SetProfile
import libMagickNative.MagickImage_SetReadMask
import libMagickNative.MagickImage_SetWriteMask
import libMagickNative.MagickImage_Shade
import libMagickNative.MagickImage_Shadow
import libMagickNative.MagickImage_Sharpen
import libMagickNative.MagickImage_Shave
import libMagickNative.MagickImage_Shear
import libMagickNative.MagickImage_SigmoidalContrast
import libMagickNative.MagickImage_Signature_Get
import libMagickNative.MagickImage_Sketch
import libMagickNative.MagickImage_Solarize
import libMagickNative.MagickImage_SortPixels
import libMagickNative.MagickImage_Splice
import libMagickNative.MagickImage_Spread
import libMagickNative.MagickImage_Statistic
import libMagickNative.MagickImage_Statistics
import libMagickNative.MagickImage_Stegano
import libMagickNative.MagickImage_Stereo
import libMagickNative.MagickImage_Strip
import libMagickNative.MagickImage_Swirl
import libMagickNative.MagickImage_Texture
import libMagickNative.MagickImage_Threshold
import libMagickNative.MagickImage_Thumbnail
import libMagickNative.MagickImage_TotalColors_Get
import libMagickNative.MagickImage_Transpose
import libMagickNative.MagickImage_Transverse
import libMagickNative.MagickImage_Trim
import libMagickNative.MagickImage_UnsharpMask
import libMagickNative.MagickImage_Vignette
import libMagickNative.MagickImage_VirtualPixelMethod_Get
import libMagickNative.MagickImage_VirtualPixelMethod_Set
import libMagickNative.MagickImage_Wave
import libMagickNative.MagickImage_WhiteBalance
import libMagickNative.MagickImage_WhiteThreshold
import libMagickNative.MagickImage_Width_Get
import libMagickNative.MagickImage_WriteStream
import libMagickNative.PrimaryInfo
import libMagickNative.QuantizeInfo
import libMagickNative.RectangleInfo
import platform.posix.size_t
import platform.posix.size_tVar
import platform.posix.ssize_t
import kotlin.contracts.ExperimentalContracts
import kotlin.experimental.ExperimentalNativeApi

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class, RuntimeException::class)
public inline fun CPointer<Image>.clone(): Pair<CPointer<Image>, MagickException?> =
    withException({ it.dispose() }) { _, exceptionPtr ->
        MagickImage_Clone(this@clone, exceptionPtr)
    }

@ExperimentalForeignApi
public inline fun CPointer<Image>.dispose(): Unit = MagickImage_Dispose(this)

//region Properties

@ExperimentalForeignApi
public inline var CPointer<Image>.animationDelay: size_t
    get() = MagickImage_AnimationDelay_Get(this)
    set(value) {
        MagickImage_AnimationDelay_Set(this, value)
    }

@ExperimentalForeignApi
public inline var CPointer<Image>.animationIterations: size_t
    get() = MagickImage_AnimationIterations_Get(this)
    set(value) {
        MagickImage_AnimationIterations_Set(this, value)
    }

@ExperimentalForeignApi
public inline var CPointer<Image>.animationTicksPerSecond: ssize_t
    get() = MagickImage_AnimationTicksPerSecond_Get(this)
    set(value) {
        MagickImage_AnimationTicksPerSecond_Set(this, value)
    }

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.backgroundColor(): NativeMagickColor? =
    MagickImage_BackgroundColor_Get(this)?.let { NativeMagickColor(it) }

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.backgroundColor(value: NativeMagickColor?): Unit =
    MagickImage_BackgroundColor_Set(this, value?.ptr)

@ExperimentalForeignApi
public inline fun CPointer<Image>.baseHeight(): size_t = MagickImage_BaseHeight_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<Image>.baseWidth(): size_t = MagickImage_BaseWidth_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<Image>.blackPointCompensation(): Boolean =
    MagickImage_BlackPointCompensation_Get(this).toPrimitive()

@ExperimentalForeignApi
public inline fun CPointer<Image>.blackPointCompensation(value: Boolean): Unit =
    MagickImage_BlackPointCompensation_Set(this, value.toNative())

@ExperimentalStdlibApi
@ExperimentalForeignApi
public inline fun CPointer<Image>.borderColor(): NativeMagickColor? =
    MagickImage_BorderColor_Get(this)?.let {
        NativeMagickColor(it)
    }

@ExperimentalStdlibApi
@ExperimentalForeignApi
public inline fun CPointer<Image>.borderColor(value: NativeMagickColor?): Unit =
    MagickImage_BorderColor_Set(this, value?.ptr)

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class, RuntimeException::class)
public inline fun CPointer<Image>.boundingBox(): Pair<CPointer<RectangleInfo>, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_BoundingBox_Get(this@boundingBox, exceptionPtr)
    }

@ExperimentalForeignApi
public inline fun CPointer<Image>.channelCount(): size_t = MagickImage_ChannelCount_Get(this)

@ExperimentalForeignApi
public inline var CPointer<Image>.chromaBlue: CPointer<PrimaryInfo>?
    get() = MagickImage_ChromaBlue_Get(this)
    set(value) {
        MagickImage_ChromaBlue_Set(this, value)
    }

@ExperimentalForeignApi
public inline var CPointer<Image>.chromaGreen: CPointer<PrimaryInfo>?
    get() = MagickImage_ChromaGreen_Get(this)
    set(value) {
        MagickImage_ChromaGreen_Set(this, value)
    }

@ExperimentalForeignApi
public inline var CPointer<Image>.chromaRed: CPointer<PrimaryInfo>?
    get() = MagickImage_ChromaRed_Get(this)
    set(value) {
        MagickImage_ChromaRed_Set(this, value)
    }

@ExperimentalForeignApi
public inline var CPointer<Image>.chromaWhite: CPointer<PrimaryInfo>?
    get() = MagickImage_ChromaWhite_Get(this)
    set(value) {
        MagickImage_ChromaWhite_Set(this, value)
    }

@ExperimentalForeignApi
public inline fun CPointer<Image>.classType(): ClassType =
    memScoped {
        val exceptionInfo = alloc<ExceptionInfoPtrVar>()

        // the exception is required by the underlying API, but it never used
        // https://github.com/dlemstra/Magick.Native/discussions/38

        MagickImage_ClassType_Get(this@classType, exceptionInfo.ptr).toMagick()
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.classType(value: ClassType): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_ClassType_Set(this@classType, value.toNative(), exceptionPtr)
    }

@ExperimentalForeignApi
public inline var CPointer<Image>.colorFuzz: Double
    get() = MagickImage_ColorFuzz_Get(this)
    set(value) {
        MagickImage_ColorFuzz_Set(this, value)
    }

@ExperimentalForeignApi
public inline fun CPointer<Image>.colormapSize(): Long =
    memScoped {
        val exceptionInfo = alloc<ExceptionInfoPtrVar>()

        // the exception is required by the underlying API, but it never used
        // https://github.com/dlemstra/Magick.Native/discussions/38

        MagickImage_ColormapSize_Get(this@colormapSize, exceptionInfo.ptr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.colormapSize(value: Long): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_ColormapSize_Set(this@colormapSize, value, exceptionPtr)
    }

@ExperimentalForeignApi
public inline fun CPointer<Image>.colorSpace(): ColorSpace =
    memScoped {
        val exceptionInfo = alloc<ExceptionInfoPtrVar>()

        // the exception is required by the underlying API, but it never used
        // https://github.com/dlemstra/Magick.Native/discussions/38

        MagickImage_ColorSpace_Get(this@colorSpace, exceptionInfo.ptr).toMagick()
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.colorSpace(value: ColorSpace): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_ColorSpace_Set(this@colorSpace, value.toNative(), exceptionPtr)
    }

@ExperimentalForeignApi
public inline fun CPointer<Image>.colorType(): ColorType =
    memScoped {
        val exceptionInfo = alloc<ExceptionInfoPtrVar>()

        val value = MagickImage_ColorType_Get(this@colorType, exceptionInfo.ptr)

        // the exception is required by the underlying API, but it never used
        // https://github.com/dlemstra/Magick.Native/discussions/38

        value.toMagick()
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.colorType(value: ColorType): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_ColorType_Set(this@colorType, value.toNative(), exceptionPtr)
    }

@ExperimentalForeignApi
public inline var CPointer<Image>.compose: CompositeOperator
    get() = MagickImage_Compose_Get(this).toMagick()
    set(value) {
        MagickImage_Compose_Set(this, value.toNative())
    }

@ExperimentalForeignApi
public inline var CPointer<Image>.compression: CompressionMethod
    get() = MagickImage_Compression_Get(this).toMagick()
    set(value) {
        MagickImage_Compression_Set(this, value.toNative())
    }

@ExperimentalForeignApi
public inline var CPointer<Image>.depth: size_t
    get() = MagickImage_Depth_Get(this)
    set(value) {
        MagickImage_Depth_Set(this, value)
    }

@ExperimentalForeignApi
public inline var CPointer<Image>.endian: Endian
    get() = MagickImage_Endian_Get(this).toMagick()
    set(value) {
        MagickImage_Endian_Set(this, value.toNative())
    }

@ExperimentalForeignApi
public inline fun CPointer<Image>.encodingGeometry(): String? = MagickImage_EncodingGeometry_Get(this)?.toKString()

@ExperimentalForeignApi
public inline var CPointer<Image>.fileName: String?
    get() = MagickImage_FileName_Get(this)?.toKString()
    set(value) {
        MagickImage_FileName_Set(this, value)
    }

@ExperimentalForeignApi
public inline var CPointer<Image>.filterType: FilterType
    get() = MagickImage_FilterType_Get(this).toMagick()
    set(value) {
        MagickImage_FilterType_Set(this, value.toNative())
    }

@ExperimentalForeignApi
public inline var CPointer<Image>.format: String?
    get() = MagickImage_Format_Get(this)?.toKString()
    set(value) {
        MagickImage_Format_Set(this, value)
    }

@ExperimentalForeignApi
public inline fun CPointer<Image>.gamma(): Double = MagickImage_Gamma_Get(this)

@ExperimentalForeignApi
public inline var CPointer<Image>.gifDisposeMethod: GifDisposeMethod
    get() = MagickImage_GifDisposeMethod_Get(this).toMagick()
    set(value) {
        MagickImage_GifDisposeMethod_Set(this, value.toNative())
    }

@ExperimentalForeignApi
public inline fun CPointer<Image>.height(): size_t = MagickImage_Height_Get(this)

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.hasAlpha(): Boolean =
    memScoped {
        val exceptionInfo = alloc<ExceptionInfoPtrVar>()

        val value = MagickImage_HasAlpha_Get(this@hasAlpha, exceptionInfo.ptr)

        // the exception is required by the underlying API, but it never used
        // https://github.com/dlemstra/Magick.Native/discussions/38

        value.toPrimitive()
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.hasAlpha(value: Boolean): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_HasAlpha_Set(this@hasAlpha, value.toNative(), exceptionPtr)
    }

@ExperimentalForeignApi
public inline fun CPointer<Image>.hasChannel(channel: PixelChannel): Boolean =
    MagickImage_HasChannel(
        this,
        channel.channel.convert(),
    ).toPrimitive()

@ExperimentalForeignApi
public inline fun CPointer<Image>.hasProfile(name: String): Boolean = MagickImage_HasProfile(this, name).toPrimitive()

@ExperimentalForeignApi
public inline var CPointer<Image>.interlace: Interlace
    get() = MagickImage_Interlace_Get(this).toMagick()
    set(value) {
        MagickImage_Interlace_Set(this, value.toNative())
    }

@ExperimentalForeignApi
public inline var CPointer<Image>.interpolate: PixelInterpolateMethod
    get() = MagickImage_Interpolate_Get(this).toMagick()
    set(value) {
        MagickImage_Interpolate_Set(this, value.toNative())
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.isOpaque(): Pair<Boolean, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_IsOpaque_Get(this@isOpaque, exceptionPtr).toPrimitive()
    }

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.matteColor(): NativeMagickColor? =
    MagickImage_MatteColor_Get(this)?.let { NativeMagickColor(it) }

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.matteColor(value: NativeMagickColor?): Unit =
    MagickImage_MatteColor_Set(this, value?.ptr)

@ExperimentalForeignApi
public inline fun CPointer<Image>.meanErrorPerPixel(): Double = MagickImage_MeanErrorPerPixel_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<Image>.normalizedMaximumError(): Double = MagickImage_NormalizedMaximumError_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<Image>.normalizedMeanError(): Double = MagickImage_NormalizedMeanError_Get(this)

@ExperimentalForeignApi
public inline var CPointer<Image>.orientation: OrientationType
    get() = MagickImage_Orientation_Get(this).toMagick()
    set(value) {
        MagickImage_Orientation_Set(this, value.toNative())
    }

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.page(): NativeMagickRectangle? =
    MagickImage_Page_Get(this)?.let { NativeMagickRectangle(it) }

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.page(value: NativeMagickRectangle?): Unit = MagickImage_Page_Set(this, value?.ptr)

@ExperimentalForeignApi
public inline var CPointer<Image>.quality: size_t
    get() = MagickImage_Quality_Get(this)
    set(value) {
        MagickImage_Quality_Set(this, value)
    }

@ExperimentalForeignApi
public inline var CPointer<Image>.renderingIntent: RenderingIntent
    get() = MagickImage_RenderingIntent_Get(this).toMagick()
    set(value) {
        MagickImage_RenderingIntent_Set(this, value.toNative())
    }

@ExperimentalForeignApi
public inline var CPointer<Image>.resolutionUnits: DensityUnit
    get() = MagickImage_ResolutionUnits_Get(this).toMagick()
    set(value) {
        MagickImage_ResolutionUnits_Set(this, value.toNative())
    }

@ExperimentalForeignApi
public inline var CPointer<Image>.resolutionX: Double
    get() = MagickImage_ResolutionX_Get(this)
    set(value) {
        MagickImage_ResolutionX_Set(this, value)
    }

@ExperimentalForeignApi
public inline var CPointer<Image>.resolutionY: Double
    get() = MagickImage_ResolutionY_Get(this)
    set(value) {
        MagickImage_ResolutionY_Set(this, value)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class, RuntimeException::class)
public inline fun CPointer<Image>.signature(): Pair<String, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Signature_Get(this@signature, exceptionPtr)?.toKString()
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.totalColors(): Pair<size_t, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_TotalColors_Get(this@totalColors, exceptionPtr)
    }

@ExperimentalForeignApi
public inline fun CPointer<Image>.virtualPixelMethod(): VirtualPixelMethod =
    memScoped {
        val exceptionInfo = alloc<ExceptionInfoPtrVar>()

        val value = MagickImage_VirtualPixelMethod_Get(this@virtualPixelMethod, exceptionInfo.ptr)

        // the exception is required by the underlying API, but it never used
        // https://github.com/dlemstra/Magick.Native/discussions/38

        value.toMagick()
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.virtualPixelMethod(value: VirtualPixelMethod): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_VirtualPixelMethod_Set(this@virtualPixelMethod, value.toNative(), exceptionPtr)
    }

@ExperimentalForeignApi
public inline fun CPointer<Image>.width(): size_t = MagickImage_Width_Get(this)

//endregion

//region Methods

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.adaptiveBlur(
    radius: Double,
    sigma: Double,
): Pair<CPointer<Image>, MagickException?> =
    withException({ it.dispose() }) { _, exceptionPtr ->
        MagickImage_AdaptiveBlur(this@adaptiveBlur, radius, sigma, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.adaptiveResize(geometry: String): Pair<CPointer<Image>, MagickException?> =
    withException({ it.dispose() }) { _, exceptionPtr ->
        MagickImage_AdaptiveResize(this@adaptiveResize, geometry, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.adaptiveSharpen(
    radius: Double,
    sigma: Double,
    channels: Channels,
): Pair<CPointer<Image>, MagickException?> =
    withException({ it.dispose() }) { _, exceptionPtr ->
        MagickImage_AdaptiveSharpen(this@adaptiveSharpen, radius, sigma, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.adaptiveThreshold(
    with: ULong,
    height: ULong,
    bias: Double,
    channels: Channels,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_AdaptiveThreshold(
            this@adaptiveThreshold,
            with,
            height,
            bias,
            channels.toNative(),
            exceptionPtr,
        )
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.addNoise(
    noiseType: NoiseType,
    attenuate: Double,
    channels: Channels,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_AddNoise(this@addNoise, noiseType.toNative(), attenuate, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.affineTransform(
    scaleX: Double,
    scaleY: Double,
    shearX: Double,
    shearY: Double,
    translateX: Double,
    translateY: Double,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_AffineTransform(
            this@affineTransform,
            scaleX,
            scaleY,
            shearX,
            shearY,
            translateX,
            translateY,
            exceptionPtr,
        )
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.annotate(
    settings: NativeDrawingSettings,
    text: String,
    boundingArea: String,
    gravity: Gravity,
    degrees: Double,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Annotate(
            this@annotate,
            settings.ptr,
            text,
            boundingArea,
            gravity.toNative(),
            degrees,
            exceptionPtr,
        )
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.annotateGravity(
    settings: NativeDrawingSettings,
    text: String,
    gravity: Gravity,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_AnnotateGravity(
            this@annotateGravity,
            settings.ptr,
            text,
            gravity.toNative(),
            exceptionPtr,
        )
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.autoGamma(channels: Channels): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_AutoGamma(this@autoGamma, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.autoLevel(channels: Channels): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_AutoLevel(this@autoLevel, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.autoOrient(): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_AutoOrient(this@autoOrient, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.autoThreshold(method: AutoThresholdMethod): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_AutoThreshold(this@autoThreshold, method.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.bilateralBlur(
    width: ULong,
    height: ULong,
    intensitySigma: Double,
    spatialSigma: Double,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_BilateralBlur(
            this@bilateralBlur,
            width,
            height,
            intensitySigma,
            spatialSigma,
            exceptionPtr,
        )
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.blackThreshold(
    threshold: String,
    channels: Channels,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_BlackThreshold(this@blackThreshold, threshold, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.blueShift(factor: Double): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_BlueShift(this@blueShift, factor, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.blur(
    radius: Double,
    sigma: Double,
    channels: Channels,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Blur(this@blur, radius, sigma, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.border(value: NativeMagickRectangle): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Border(this@border, value.ptr, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.brightnessContrast(
    brigthness: Double,
    contrast: Double,
    channels: Channels,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_BrightnessContrast(
            this@brightnessContrast,
            brigthness,
            contrast,
            channels.toNative(),
            exceptionPtr,
        )
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.cannyEdge(
    radius: Double,
    sigma: Double,
    lower: Double,
    upper: Double,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_CannyEdge(this@cannyEdge, radius, sigma, lower, upper, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.charcoal(
    radius: Double,
    sigma: Double,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Charcoal(this@charcoal, radius, sigma, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.chop(geometry: NativeMagickRectangle): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Chop(this@chop, geometry.ptr, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.clahe(
    xTiles: size_t,
    yTiles: size_t,
    numberBins: size_t,
    clipLimit: Double,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Clahe(this@clahe, xTiles, yTiles, numberBins, clipLimit, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.clamp(channels: Channels): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Clamp(this@clamp, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.clipPath(
    pathName: String,
    inside: Boolean,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_ClipPath(this@clipPath, pathName, inside.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.clut(
    image: CPointer<Image>,
    method: PixelInterpolateMethod,
    channels: Channels,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Clut(this@clut, image, method.toNative(), channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.colorDecisionList(fileName: String): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_ColorDecisionList(this@colorDecisionList, fileName, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.colorMatrix(matrix: CPointer<KernelInfo>): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_ColorMatrix(this@colorMatrix, matrix, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.compare(
    image: CPointer<Image>,
    metric: ErrorMetric,
    channels: Channels,
): Pair<Pair<Double, CPointer<Image>?>, MagickException?> =
    withException(onError = { it.second?.dispose() }) { placement, exceptionPtr ->
        val distortion = placement.alloc<DoubleVar>()

        val differenceImage =
            MagickImage_Compare(
                this,
                image,
                metric.toNative(),
                channels.toNative(),
                distortion.ptr,
                exceptionPtr,
            )

        Pair(distortion.value, differenceImage)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.compareDistortion(
    image: CPointer<Image>,
    metric: ErrorMetric,
    channels: Channels,
): Pair<Double, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_CompareDistortion(this, image, metric.toNative(), channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.composite(
    image: CPointer<Image>,
    x: Long,
    y: Long,
    compose: CompositeOperator,
    channels: Channels,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Composite(
            this@composite,
            image,
            x,
            y,
            compose.toNative(),
            channels.toNative(),
            exceptionPtr,
        )
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.compositeGravity(
    image: CPointer<Image>,
    gravity: Gravity,
    x: Long,
    y: Long,
    compose: CompositeOperator,
    channels: Channels,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_CompositeGravity(
            this@compositeGravity,
            image,
            gravity.toNative(),
            x,
            y,
            compose.toNative(),
            channels.toNative(),
            exceptionPtr,
        )
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.contrast(enhance: Boolean): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Contrast(this@contrast, enhance.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.contrastStretch(
    blackPoint: Double,
    whitePoint: Double,
    channels: Channels,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_ContrastStretch(
            this@contrastStretch,
            blackPoint,
            whitePoint,
            channels.toNative(),
            exceptionPtr,
        )
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.convolve(matrix: CPointer<KernelInfo>): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Convolve(this@convolve, matrix, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.copyPixels(
    image: CPointer<Image>,
    geometry: NativeMagickRectangle,
    offset: NativeOffsetInfo,
    channels: Channels,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_CopyPixels(this@copyPixels, image, geometry.ptr, offset.ptr, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.crop(
    geometry: String,
    gravity: Gravity,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Crop(this@crop, geometry, gravity.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.cycleColormap(amount: Long): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_CycleColormap(this@cycleColormap, amount, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.decipher(passphrase: String): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Decipher(this@decipher, passphrase, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.deskew(threshold: Double): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Deskew(this@deskew, threshold, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.despeckle(): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Despeckle(this@despeckle, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.determineBitDepth(channels: Channels): Pair<size_t, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_DetermineBitDepth(this@determineBitDepth, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.determineColorType(): Pair<ColorType, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_DetermineColorType(this@determineColorType, exceptionPtr).toMagick<ColorType>()
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.distort(
    method: DistortMethod,
    bestfit: Boolean,
    arguments: DoubleArray,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        arguments.usePinned {
            MagickImage_Distort(
                this@distort,
                method.toNative(),
                bestfit.toNative(),
                it.addressOf(0),
                arguments.size.toULong(),
                exceptionPtr,
            )
        }
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.emboss(
    radius: Double,
    sigma: Double,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Emboss(this@emboss, radius, sigma, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.edge(radius: Double): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Edge(this@edge, radius, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.encipher(passphrase: String): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Encipher(this@encipher, passphrase, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.enhance(): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Enhance(this@enhance, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.equalize(channels: Channels): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Equalize(this@equalize, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.evaluateFunction(
    channels: Channels,
    evaluateFunction: EvaluateFunction,
    values: DoubleArray,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        val length = values.size
        values.usePinned {
            MagickImage_EvaluateFunction(
                this@evaluateFunction,
                channels.toNative(),
                evaluateFunction.toNative(),
                it.addressOf(0),
                length.toULong(),
                exceptionPtr,
            )
        }
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.evaluateGeometry(
    channels: Channels,
    geometry: NativeMagickRectangle,
    evaluateOperator: EvaluateOperator,
    value: Double,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_EvaluateGeometry(
            this@evaluateGeometry,
            channels.toNative(),
            geometry.ptr,
            evaluateOperator.toNative(),
            value,
            exceptionPtr,
        )
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.evaluateOperator(
    channels: Channels,
    evaluateOperator: EvaluateOperator,
    value: Double,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_EvaluateOperator(
            this@evaluateOperator,
            channels.toNative(),
            evaluateOperator.toNative(),
            value,
            exceptionPtr,
        )
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.extent(
    geometry: String,
    gravity: Gravity,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Extent(this@extent, geometry, gravity.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.flip(): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Flip(this@flip, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.flop(): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Flop(this@flop, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.frame(geometry: NativeMagickRectangle): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Frame(this@frame, geometry.ptr, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.fx(
    expression: String,
    channels: Channels,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Fx(this@fx, expression, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.gammaCorrect(
    gamma: Double,
    channels: Channels,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_GammaCorrect(this@gammaCorrect, gamma, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.gaussianBlur(
    radius: Double,
    sigma: Double,
    channels: Channels,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_GaussianBlur(this@gaussianBlur, radius, sigma, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.getAttribute(name: String): Pair<String, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_GetAttribute(this@getAttribute, name, exceptionPtr)?.toKString()
    }

@ExperimentalForeignApi
public inline fun CPointer<Image>.getArtifact(name: String): String? =
    MagickImage_GetArtifact(this@getArtifact, name)?.toKString()

@ExperimentalForeignApi
public inline fun CPointer<Image>.getNextArtifactName(): String? = MagickImage_GetNextArtifactName(this)?.toKString()

@ExperimentalForeignApi
public inline fun CPointer<Image>.getNextAttributeName(): String? = MagickImage_GetNextAttributeName(this)?.toKString()

@ExperimentalForeignApi
public inline fun CPointer<Image>.getNextProfileName(): String? = MagickImage_GetNextProfileName(this)?.toKString()

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.grayscale(method: PixelIntensityMethod): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Grayscale(this@grayscale, method.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.haldClut(
    image: CPointer<Image>,
    channels: Channels,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_HaldClut(this@haldClut, image, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.houghLine(
    width: ULong,
    height: ULong,
    threshold: ULong,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_HoughLine(this@houghLine, width, height, threshold, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.implode(
    amount: Double,
    method: PixelInterpolateMethod,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Implode(this@implode, amount, method.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.interpolativeResize(
    geometry: String,
    method: PixelInterpolateMethod,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_InterpolativeResize(this@interpolativeResize, geometry, method.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.kmeans(
    numberColors: ULong,
    maxIterations: ULong,
    tolerance: Double,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Kmeans(this@kmeans, numberColors, maxIterations, tolerance, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.kuwahara(
    radius: Double,
    sigma: Double,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Kuwahara(this@kuwahara, radius, sigma, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.linearStretch(
    blackPoint: Double,
    whitePoint: Double,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_LinearStretch(this@linearStretch, blackPoint, whitePoint, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.liquidRescale(
    geometry: String,
    deltaX: Double,
    rigidity: Double,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_LiquidRescale(this@liquidRescale, geometry, deltaX, rigidity, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.localContrast(
    radius: Double,
    strength: Double,
    channels: Channels,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_LocalContrast(this@localContrast, radius, strength, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.lower(size: ULong): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_RaiseOrLower(this@lower, size, false.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.magnify(): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Magnify(this@magnify, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.map(
    image: CPointer<Image>,
    settings: NativeQuantizeSettings,
): Pair<Boolean, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Map(this@map, image, settings.ptr, exceptionPtr).toPrimitive()
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.meanShift(
    width: size_t,
    height: size_t,
    colorDistance: Double,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_MeanShift(this@meanShift, width, height, colorDistance, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.minify(): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Minify(this@minify, exceptionPtr)
    }

@ExperimentalStdlibApi
@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.minimumBoundingBox(): Pair<PointInfoCollection, MagickException?> =
    withException(onError = { it.dispose() }) { placement, exceptionPtr ->
        val length = placement.alloc<size_tVar>()

        MagickImage_MinimumBoundingBox(this@minimumBoundingBox, length.ptr, exceptionPtr)?.let {
            PointInfoCollection(it, length.value)
        }
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.modulate(modulate: String): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Modulate(this@modulate, modulate, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.morphology(
    method: MorphologyMethod,
    userKernel: String,
    channels: Channels,
    iterations: Int,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Morphology(
            this@morphology,
            method.toNative(),
            userKernel,
            channels.toNative(),
            iterations.convert(),
            exceptionPtr,
        )
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.moments(): Pair<NativeMoments, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Moments(this@moments, exceptionPtr)?.let {
            NativeMoments(it)
        }
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.motionBlur(
    radius: Double,
    sigma: Double,
    angle: Double,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_MotionBlur(this@motionBlur, radius, sigma, angle, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.negate(
    onlyGrayscale: Boolean,
    channels: Channels,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Negate(this@negate, onlyGrayscale.toNative(), channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.normalize(): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Normalize(this@normalize, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.oilPaint(
    radius: Double,
    sigma: Double,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_OilPaint(this@oilPaint, radius, sigma, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.orderedDither(
    thresholdMap: String,
    channels: Channels,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_OrderedDither(this@orderedDither, thresholdMap, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.perceptible(
    epsilon: Double,
    channels: Channels,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Perceptible(this@perceptible, epsilon, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.perceptualHash(): Pair<CPointer<ChannelPerceptualHash>, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_PerceptualHash(this@perceptualHash, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.polaroid(
    settings: CPointer<DrawInfo>,
    caption: String,
    angle: Double,
    method: PixelInterpolateMethod,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Polaroid(this@polaroid, settings, caption, angle, method.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.posterize(
    levels: UInt,
    method: DitherMethod,
    channels: Channels,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Posterize(this@posterize, levels.convert(), method.toNative(), channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.quantize(settings: CPointer<QuantizeInfo>): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Quantize(this@quantize, settings, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.raise(size: size_t): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_RaiseOrLower(this@raise, size, true.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.regionMask(region: NativeMagickRectangle?): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_RegionMask(this@regionMask, region?.ptr, exceptionPtr)
    }

@ExperimentalForeignApi
public inline fun CPointer<Image>.removeArtifact(name: String): Unit =
    MagickImage_RemoveArtifact(this@removeArtifact, name)

@ExperimentalForeignApi
public inline fun CPointer<Image>.removeAttribute(name: String): Unit =
    MagickImage_RemoveAttribute(this@removeAttribute, name)

@ExperimentalForeignApi
public inline fun CPointer<Image>.removeProfile(name: String): Unit =
    MagickImage_RemoveProfile(this@removeProfile, name)

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.resample(
    resolutionX: Double,
    resolutionY: Double,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Resample(this@resample, resolutionX, resolutionY, exceptionPtr)
    }

@ExperimentalForeignApi
public inline fun CPointer<Image>.resetArtifactIterator(): Unit = MagickImage_ResetArtifactIterator(this)

@ExperimentalForeignApi
public inline fun CPointer<Image>.resetAttributeIterator(): Unit = MagickImage_ResetAttributeIterator(this)

@ExperimentalForeignApi
public inline fun CPointer<Image>.resetProfileIterator(): Unit = MagickImage_ResetProfileIterator(this)

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.resize(geometry: String): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Resize(this@resize, geometry, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.roll(
    x: Long,
    y: Long,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Roll(this@roll, x, y, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.rotate(degrees: Double): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Rotate(this@rotate, degrees, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.rotationalBlur(
    angle: Double,
    channels: Channels,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_RotationalBlur(this@rotationalBlur, angle, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.sample(geometry: String): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Sample(this@sample, geometry, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.scale(geometry: String): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Scale(this@scale, geometry, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.segment(
    colorSpace: ColorSpace,
    clusterThreshold: Double,
    smoothingThreshold: Double,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Segment(this@segment, colorSpace.toNative(), clusterThreshold, smoothingThreshold, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.selectiveBlur(
    radius: Double,
    sigma: Double,
    threshold: Double,
    channels: Channels,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_SelectiveBlur(this@selectiveBlur, radius, sigma, threshold, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.sepiaTone(threshold: Double): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_SepiaTone(this@sepiaTone, threshold, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.setColorMetric(image: CPointer<Image>): Pair<Boolean, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_SetColorMetric(this@setColorMetric, image, exceptionPtr).toPrimitive()
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.setAlpha(value: AlphaOption): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_SetAlpha(this@setAlpha, value.toNative(), exceptionPtr)
    }

@ExperimentalForeignApi
public inline fun CPointer<Image>.setArtifact(
    name: String,
    value: String,
): Unit = MagickImage_SetArtifact(this@setArtifact, name, value)

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.setAttribute(
    name: String,
    value: String,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_SetAttribute(this@setAttribute, name, value, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.setBitDepth(
    value: ULong,
    channels: Channels,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_SetBitDepth(this@setBitDepth, value, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.setProfile(
    name: String,
    datum: UByteArray,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        datum.usePinned {
            MagickImage_SetProfile(
                this@setProfile,
                name,
                it.addressOf(0),
                datum.size.toULong(),
                exceptionPtr,
            )
        }
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.setReadMask(image: CPointer<Image>?): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_SetReadMask(this@setReadMask, image, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.setWriteMask(image: CPointer<Image>?): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_SetWriteMask(this@setWriteMask, image, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.shade(
    azimuth: Double,
    elevation: Double,
    colorShading: Boolean,
    channels: Channels,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Shade(this@shade, azimuth, elevation, colorShading.toNative(), channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.shadow(
    x: Long,
    y: Long,
    sigma: Double,
    alphaPercentage: Double,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Shadow(this@shadow, x, y, sigma, alphaPercentage, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.sharpen(
    radius: Double,
    sigma: Double,
    channels: Channels,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Sharpen(this@sharpen, radius, sigma, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.shave(
    leftRight: ULong,
    bottomLeft: ULong,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Shave(this@shave, leftRight, bottomLeft, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.shear(
    xAngle: Double,
    yAngle: Double,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Shear(this@shear, xAngle, yAngle, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.sigmoidalContrast(
    sharpen: Boolean,
    contrast: Double,
    midpoint: Double,
    channels: Channels,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_SigmoidalContrast(
            this@sigmoidalContrast,
            sharpen.toNative(),
            contrast,
            midpoint,
            channels.toNative(),
            exceptionPtr,
        )
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.sketch(
    radius: Double,
    sigma: Double,
    angle: Double,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Sketch(this@sketch, radius, sigma, angle, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.solarize(factor: Double): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Solarize(this@solarize, factor, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.sortPixels(): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_SortPixels(this@sortPixels, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.splice(geometry: NativeMagickRectangle): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Splice(this@splice, geometry.ptr, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.spread(
    method: PixelInterpolateMethod,
    radius: Double,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Spread(this@spread, method.toNative(), radius, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.statistic(
    type: StatisticType,
    width: ULong,
    height: ULong,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Statistic(this@statistic, type.toNative(), width, height, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.statistics(channels: Channels): Pair<CPointer<ChannelStatistics>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Statistics(this@statistics, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.stegano(watermark: CPointer<Image>): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Stegano(this@stegano, watermark, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.stereo(rightImage: CPointer<Image>): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Stereo(this@stereo, rightImage, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.strip(): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Strip(this@strip, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.swirl(
    method: PixelInterpolateMethod,
    degrees: Double,
): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Swirl(this@swirl, method.toNative(), degrees, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.texture(image: CPointer<Image>): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Texture(this@texture, image, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.threshold(
    percentage: Double,
    channels: Channels,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Threshold(this@threshold, percentage, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.thumbnail(geometry: String): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Thumbnail(this@thumbnail, geometry, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.transpose(): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Transpose(this@transpose, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.transverse(): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Transverse(this@transverse, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.trim(): Pair<CPointer<Image>, MagickException?> =
    withException(onError = { it.dispose() }) { _, exceptionPtr ->
        MagickImage_Trim(this@trim, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
public inline fun CPointer<Image>.unsharpMask(
    radius: Double,
    sigma: Double,
    amount: Double,
    threshold: Double,
    channels: Channels,
): Pair<CPointer<Image>, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_UnsharpMask(this@unsharpMask, radius, sigma, amount, threshold, channels.toNative(), exceptionPtr)
    }


@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.vignette(
    radius: Double,
    sigma: Double,
    x: ssize_t,
    y: ssize_t,
): Pair<CPointer<Image>, MagickException?> = withException { _, exceptionPtr ->
    MagickImage_Vignette(this@vignette, radius, sigma, x, y, exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.wave(method: PixelInterpolateMethod, amplitude: Double, length: Double): Pair<CPointer<Image>, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_Wave(this@wave, method.toNative(), amplitude, length, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.whiteBalance(): Pair<Unit, MagickException?> = withException { _, exceptionPtr ->
    MagickImage_WhiteBalance(this@whiteBalance, exceptionPtr)
}

@ExperimentalNativeApi
@ExperimentalContracts
@ExperimentalForeignApi
public inline fun CPointer<Image>.whiteThreshold(threshold: String, channels: Channels): Pair<Unit, MagickException?> = withException { _, exceptionPtr ->
    MagickImage_WhiteThreshold(this@whiteThreshold, threshold, channels.toNative(), exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.writeStream(
    settings: NativeMagickSettings,
    userData: StableRef<Any>,
    writer: CustomStreamHandler?,
    seeker: CustomStreamSeeker?,
    teller: CustomStreamTeller?,
    reader: CustomStreamHandler?,
): Pair<Unit, MagickException?> =
    withException { _, exceptionPtr ->
        MagickImage_WriteStream(
            this@writeStream,
            settings.ptr,
            writer,
            seeker,
            teller,
            reader,
            userData.asCPointer(),
            exceptionPtr,
        )
    }

//endregion
