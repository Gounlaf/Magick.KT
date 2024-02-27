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
import imagemagick.core.enums.VirtualPixelMethod
import imagemagick.core.exceptions.MagickException
import imagemagick.core.toMagick
import imagemagick.core.toNative
import imagemagick.core.types.Percentage
import imagemagick.magicknative.NativeChannels.toNative
import imagemagick.magicknative.NativeMagickImage
import imagemagick.magicknative.colors.NativeMagickColor
import imagemagick.magicknative.exceptions.ExceptionInfoPtrVar
import imagemagick.magicknative.exceptions.withException
import imagemagick.magicknative.matrices.NativeDoubleMatrix
import imagemagick.magicknative.settings.NativeDrawingSettings
import imagemagick.magicknative.toNative
import imagemagick.magicknative.toPrimitive
import imagemagick.magicknative.types.NativeMagickRectangle
import imagemagick.magicknative.types.NativeOffsetInfo
import imagemagick.magicknative.types.NativePrimaryInfo
import kotlin.contracts.ExperimentalContracts
import kotlin.experimental.ExperimentalNativeApi
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.DoubleVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.alloc
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toKString
import kotlinx.cinterop.usePinned
import kotlinx.cinterop.value
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
import libMagickNative.MagickImage_SetArtifact
import libMagickNative.MagickImage_SetAttribute
import libMagickNative.MagickImage_SetColorMetric
import libMagickNative.MagickImage_SigmoidalContrast
import libMagickNative.MagickImage_Signature_Get
import libMagickNative.MagickImage_TotalColors_Get
import libMagickNative.MagickImage_VirtualPixelMethod_Get
import libMagickNative.MagickImage_VirtualPixelMethod_Set
import libMagickNative.MagickImage_Width_Get
import libMagickNative.RectangleInfo
import platform.posix.size_t
import platform.posix.ssize_t

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class, RuntimeException::class)
public inline fun CPointer<Image>.clone(): CPointer<Image> = withException { _, exceptionPtr ->
    MagickImage_Clone(this@clone, exceptionPtr)
} ?: throw RuntimeException()

@ExperimentalForeignApi
public inline fun CPointer<Image>.dispose(): Unit = MagickImage_Dispose(this)

//region Properties

@ExperimentalForeignApi
public inline fun CPointer<Image>.animationDelay(): size_t = MagickImage_AnimationDelay_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<Image>.animationDelay(value: size_t): Unit = MagickImage_AnimationDelay_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<Image>.animationIterations(): size_t = MagickImage_AnimationIterations_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<Image>.animationIterations(value: size_t): Unit =
    MagickImage_AnimationIterations_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<Image>.animationTicksPerSecond(): ssize_t = MagickImage_AnimationTicksPerSecond_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<Image>.animationTicksPerSecond(value: ssize_t): Unit =
    MagickImage_AnimationTicksPerSecond_Set(this, value)

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.backgroundColor(): NativeMagickColor? = MagickImage_BackgroundColor_Get(this)?.let {
    NativeMagickColor(it)
}

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
public inline fun CPointer<Image>.borderColor(): NativeMagickColor? = MagickImage_BorderColor_Get(this)?.let {
    NativeMagickColor(it)
}

@ExperimentalStdlibApi
@ExperimentalForeignApi
public inline fun CPointer<Image>.borderColor(value: NativeMagickColor?): Unit =
    MagickImage_BorderColor_Set(this, value?.ptr)

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class, RuntimeException::class)
public inline fun CPointer<Image>.boundingBox(): CPointer<RectangleInfo> = withException { _, exceptionPtr ->
    MagickImage_BoundingBox_Get(this@boundingBox, exceptionPtr)
} ?: throw RuntimeException()

@ExperimentalForeignApi
public inline fun CPointer<Image>.channelCount(): size_t = MagickImage_ChannelCount_Get(this)

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.chromaBlue(): NativePrimaryInfo? = MagickImage_ChromaBlue_Get(this)?.let {
    NativePrimaryInfo(it)
}

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.chromaBlue(value: NativePrimaryInfo?): Unit =
    MagickImage_ChromaBlue_Set(this, value?.ptr)

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.chromaGreen(): NativePrimaryInfo? = MagickImage_ChromaGreen_Get(this)?.let {
    NativePrimaryInfo(it)
}

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.chromaGreen(value: NativePrimaryInfo?): Unit =
    MagickImage_ChromaGreen_Set(this, value?.ptr)

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.chromaRed(): NativePrimaryInfo? = MagickImage_ChromaRed_Get(this)?.let {
    NativePrimaryInfo(it)
}

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.chromaRed(value: NativePrimaryInfo?): Unit =
    MagickImage_ChromaRed_Set(this, value?.ptr)

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.chromaWhite(): NativePrimaryInfo? = MagickImage_ChromaWhite_Get(this)?.let {
    NativePrimaryInfo(it)
}

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.chromaWhite(value: NativePrimaryInfo?): Unit =
    MagickImage_ChromaWhite_Set(this, value?.ptr)

@ExperimentalForeignApi
public inline fun CPointer<Image>.classType(): ClassType = memScoped {
    val exceptionInfo = alloc<ExceptionInfoPtrVar>()

    // the exception is required by the underlying API, but it never used
    // https://github.com/dlemstra/Magick.Native/discussions/38

    MagickImage_ClassType_Get(this@classType, exceptionInfo.ptr).toMagick()
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.classType(value: ClassType): Unit = withException { _, exceptionPtr ->
    MagickImage_ClassType_Set(this@classType, value.toNative(), exceptionPtr)
}

@ExperimentalForeignApi
public inline fun CPointer<Image>.colorFuzz(): Double = MagickImage_ColorFuzz_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<Image>.colorFuzz(value: Double): Unit = MagickImage_ColorFuzz_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<Image>.colormapSize(): Long = memScoped {
    val exceptionInfo = alloc<ExceptionInfoPtrVar>()

    // the exception is required by the underlying API, but it never used
    // https://github.com/dlemstra/Magick.Native/discussions/38

    MagickImage_ColormapSize_Get(this@colormapSize, exceptionInfo.ptr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.colormapSize(value: Long): Unit = withException { _, exceptionPtr ->
    MagickImage_ColormapSize_Set(this@colormapSize, value, exceptionPtr)
}

@ExperimentalForeignApi
public inline fun CPointer<Image>.colorSpace(): ColorSpace = memScoped {
    val exceptionInfo = alloc<ExceptionInfoPtrVar>()

    // the exception is required by the underlying API, but it never used
    // https://github.com/dlemstra/Magick.Native/discussions/38

    MagickImage_ColorSpace_Get(this@colorSpace, exceptionInfo.ptr).toMagick()
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.colorSpace(value: ColorSpace): Unit = withException { _, exceptionPtr ->
    MagickImage_ColorSpace_Set(this@colorSpace, value.toNative(), exceptionPtr)
}

@ExperimentalForeignApi
public inline fun CPointer<Image>.colorType(): ColorType = memScoped {
    val exceptionInfo = alloc<ExceptionInfoPtrVar>()

    val value = MagickImage_ColorType_Get(this@colorType, exceptionInfo.ptr)

    // the exception is required by the underlying API, but it never used
    // https://github.com/dlemstra/Magick.Native/discussions/38

    value.toMagick()
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.colorType(value: ColorType): Unit = withException { _, exceptionPtr ->
    MagickImage_ColorType_Set(this@colorType, value.toNative(), exceptionPtr)
}

@ExperimentalForeignApi
public inline fun CPointer<Image>.compose(): CompositeOperator = MagickImage_Compose_Get(this).toMagick()

@ExperimentalForeignApi
public inline fun CPointer<Image>.compose(value: CompositeOperator): Unit =
    MagickImage_Compose_Set(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<Image>.compression(): CompressionMethod = MagickImage_Compression_Get(this).toMagick()

@ExperimentalForeignApi
public inline fun CPointer<Image>.compression(value: CompressionMethod): Unit =
    MagickImage_Compression_Set(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<Image>.depth(): size_t = MagickImage_Depth_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<Image>.depth(value: size_t): Unit = MagickImage_Depth_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<Image>.endian(): Endian = MagickImage_Endian_Get(this).toMagick()

@ExperimentalForeignApi
public inline fun CPointer<Image>.endian(value: Endian): Unit = MagickImage_Endian_Set(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<Image>.encodingGeometry(): String? = MagickImage_EncodingGeometry_Get(this)?.toKString()

@ExperimentalForeignApi
public inline fun CPointer<Image>.fileName(): String? = MagickImage_FileName_Get(this)?.toKString()

@ExperimentalForeignApi
public inline fun CPointer<Image>.fileName(value: String?): Unit = MagickImage_FileName_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<Image>.filterType(): FilterType = MagickImage_FilterType_Get(this).toMagick()

@ExperimentalForeignApi
public inline fun CPointer<Image>.filterType(value: FilterType): Unit =
    MagickImage_FilterType_Set(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<Image>.format(): String? = MagickImage_Format_Get(this)?.toKString()

@ExperimentalForeignApi
public inline fun CPointer<Image>.format(value: String?): Unit = MagickImage_Format_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<Image>.gamma(): Double = MagickImage_Gamma_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<Image>.gifDisposeMethod(): GifDisposeMethod =
    MagickImage_GifDisposeMethod_Get(this).toMagick()

@ExperimentalForeignApi
public inline fun CPointer<Image>.gifDisposeMethod(value: GifDisposeMethod): Unit =
    MagickImage_GifDisposeMethod_Set(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<Image>.height(): size_t = MagickImage_Height_Get(this)

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.hasAlpha(): Boolean = withException { _, exceptionPtr ->
    MagickImage_HasAlpha_Get(this@hasAlpha, exceptionPtr).toPrimitive()
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.hasAlpha(value: Boolean): Unit = withException { _, exceptionPtr ->
    MagickImage_HasAlpha_Set(this@hasAlpha, value.toNative(), exceptionPtr)
}

@ExperimentalForeignApi
public inline fun CPointer<Image>.hasChannel(channel: PixelChannel): Boolean = MagickImage_HasChannel(
    this,
    channel.channel.convert(),
).toPrimitive()

@ExperimentalForeignApi
public inline fun CPointer<Image>.hasProfile(name: String): Boolean = MagickImage_HasProfile(this, name).toPrimitive()

@ExperimentalForeignApi
public inline fun CPointer<Image>.interlace(): Interlace = MagickImage_Interlace_Get(this).toMagick()

@ExperimentalForeignApi
public inline fun CPointer<Image>.interlace(value: Interlace): Unit =
    MagickImage_Interlace_Set(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<Image>.interpolate(): PixelInterpolateMethod =
    MagickImage_Interpolate_Get(this).toMagick()

@ExperimentalForeignApi
public inline fun CPointer<Image>.interpolate(value: PixelInterpolateMethod): Unit =
    MagickImage_Interpolate_Set(this, value.toNative())

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.isOpaque(): Boolean = withException { _, exceptionPtr ->
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
public inline fun CPointer<Image>.orientation(): OrientationType = MagickImage_Orientation_Get(this).toMagick()

@ExperimentalForeignApi
public inline fun CPointer<Image>.orientation(value: OrientationType): Unit =
    MagickImage_Orientation_Set(this, value.toNative())

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.page(): NativeMagickRectangle? =
    MagickImage_Page_Get(this)?.let { NativeMagickRectangle(it) }

@ExperimentalForeignApi
@ExperimentalStdlibApi
public inline fun CPointer<Image>.page(value: NativeMagickRectangle?): Unit = MagickImage_Page_Set(this, value?.ptr)

@ExperimentalForeignApi
public inline fun CPointer<Image>.quality(): size_t = MagickImage_Quality_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<Image>.quality(value: size_t): Unit = MagickImage_Quality_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<Image>.renderingIntent(): RenderingIntent = MagickImage_RenderingIntent_Get(this).toMagick()

@ExperimentalForeignApi
public inline fun CPointer<Image>.renderingIntent(value: RenderingIntent): Unit =
    MagickImage_RenderingIntent_Set(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<Image>.resolutionUnits(): DensityUnit = MagickImage_ResolutionUnits_Get(this).toMagick()

@ExperimentalForeignApi
public inline fun CPointer<Image>.resolutionUnits(value: DensityUnit): Unit =
    MagickImage_ResolutionUnits_Set(this, value.toNative())

@ExperimentalForeignApi
public inline fun CPointer<Image>.resolutionX(): Double = MagickImage_ResolutionX_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<Image>.resolutionX(value: Double): Unit = MagickImage_ResolutionX_Set(this, value)

@ExperimentalForeignApi
public inline fun CPointer<Image>.resolutionY(): Double = MagickImage_ResolutionY_Get(this)

@ExperimentalForeignApi
public inline fun CPointer<Image>.resolutionY(value: Double): Unit = MagickImage_ResolutionY_Set(this, value)

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class, RuntimeException::class)
public inline fun CPointer<Image>.signature(): String = withException { _, exceptionPtr ->
    MagickImage_Signature_Get(this@signature, exceptionPtr)?.toKString()
} ?: throw RuntimeException("The string value should never be null.")

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.totalColors(): size_t = withException { _, exceptionPtr ->
    MagickImage_TotalColors_Get(this@totalColors, exceptionPtr)
}

@ExperimentalForeignApi
public inline fun CPointer<Image>.virtualPixelMethod(): VirtualPixelMethod = memScoped {
    val exceptionInfo = alloc<ExceptionInfoPtrVar>()

    val value = MagickImage_VirtualPixelMethod_Get(this@virtualPixelMethod, exceptionInfo.ptr)

    // the exception is required by the underlying API, but it never used
    // https://github.com/dlemstra/Magick.Native/discussions/38

    value.toMagick()
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.virtualPixelMethod(value: VirtualPixelMethod): Unit =
    withException { _, exceptionPtr ->
        MagickImage_VirtualPixelMethod_Set(this@virtualPixelMethod, value.toNative(), exceptionPtr)
    }

@ExperimentalForeignApi
public inline fun CPointer<Image>.width(): size_t = MagickImage_Width_Get(this)

//endregion

//region Methods

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.adaptiveBlur(radius: Double, sigma: Double): CPointer<Image>? =
    withException { _, exceptionPtr ->
        MagickImage_AdaptiveBlur(this@adaptiveBlur, radius, sigma, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.adaptiveResize(geometry: String): CPointer<Image>? =
    withException { _, exceptionPtr ->
        MagickImage_AdaptiveResize(this@adaptiveResize, geometry, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.adaptiveSharpen(
    radius: Double,
    sigma: Double,
    channels: Channels,
): CPointer<Image>? = withException { _, exceptionPtr ->
    MagickImage_AdaptiveSharpen(this@adaptiveSharpen, radius, sigma, channels.toNative(), exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.adaptiveThreshold(
    with: ULong,
    height: ULong,
    bias: Double,
    channels: Channels,
): CPointer<Image>? = withException { _, exceptionPtr ->
    MagickImage_AdaptiveThreshold(
        this@adaptiveThreshold,
        with,
        height,
        bias,
        channels.toNative(),
        exceptionPtr
    )
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.addNoise(
    noiseType: NoiseType,
    attenuate: Double,
    channels: Channels,
): CPointer<Image>? = withException { _, exceptionPtr ->
    MagickImage_AddNoise(this@addNoise, noiseType.toNative(), attenuate, channels.toNative(), exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.affineTransform(
    scaleX: Double,
    scaleY: Double,
    shearX: Double,
    shearY: Double,
    translateX: Double,
    translateY: Double,
): CPointer<Image>? = withException { _, exceptionPtr ->
    MagickImage_AffineTransform(
        this@affineTransform, scaleX, scaleY, shearX, shearY, translateX, translateY, exceptionPtr
    )
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.annotate(
    settings: NativeDrawingSettings,
    text: String,
    boundingArea: String,
    gravity: Gravity,
    degrees: Double,
): Unit = withException { _, exceptionPtr ->
    MagickImage_Annotate(
        this@annotate,
        settings.ptr,
        text,
        boundingArea,
        gravity.toNative(),
        degrees,
        exceptionPtr
    )
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.annotateGravity(
    settings: NativeDrawingSettings,
    text: String,
    gravity: Gravity,
): Unit = withException { _, exceptionPtr ->
    MagickImage_AnnotateGravity(
        this@annotateGravity, settings.ptr, text, gravity.toNative(), exceptionPtr
    )
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.autoGamma(channels: Channels): Unit = withException { _, exceptionPtr ->
    MagickImage_AutoGamma(this@autoGamma, channels.toNative(), exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.autoLevel(channels: Channels): Unit = withException { _, exceptionPtr ->
    MagickImage_AutoLevel(this@autoLevel, channels.toNative(), exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.autoOrient(): Unit = withException { _, exceptionPtr ->
    MagickImage_AutoOrient(this@autoOrient, exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.autoThreshold(method: AutoThresholdMethod): Unit = withException { _, exceptionPtr ->
    MagickImage_AutoThreshold(this@autoThreshold, method.toNative(), exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.bilateralBlur(
    width: ULong,
    height: ULong,
    intensitySigma: Double,
    spatialSigma: Double,
): CPointer<Image>? = withException { _, exceptionPtr ->
    MagickImage_BilateralBlur(
        this@bilateralBlur,
        width,
        height,
        intensitySigma,
        spatialSigma,
        exceptionPtr
    )
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.blackThreshold(threshold: String, channels: Channels): Unit =
    withException { _, exceptionPtr ->
        MagickImage_BlackThreshold(this@blackThreshold, threshold, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.blueShift(factor: Double): CPointer<Image>? =
    withException { _, exceptionPtr ->
        MagickImage_BlueShift(this@blueShift, factor, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.blur(radius: Double, sigma: Double, channels: Channels): CPointer<Image>? =
    withException { _, exceptionPtr ->
        MagickImage_Blur(this@blur, radius, sigma, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.border(value: NativeMagickRectangle): CPointer<Image>? =
    withException { _, exceptionPtr ->
        MagickImage_Border(this@border, value.ptr, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.brightnessContrast(brigthness: Double, contrast: Double, channels: Channels): Unit =
    withException { _, exceptionPtr ->
        MagickImage_BrightnessContrast(
            this@brightnessContrast,
            brigthness,
            contrast,
            channels.toNative(),
            exceptionPtr
        )
    }

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.cannyEdge(
    radius: Double,
    sigma: Double,
    lower: Double,
    upper: Double,
): CPointer<Image>? = withException { _, exceptionPtr ->
    MagickImage_CannyEdge(this@cannyEdge, radius, sigma, lower, upper, exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.charcoal(radius: Double, sigma: Double): CPointer<Image>? =
    withException { _, exceptionPtr ->
        MagickImage_Charcoal(this@charcoal, radius, sigma, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.chop(geometry: NativeMagickRectangle): CPointer<Image>? =
    withException { _, exceptionPtr ->
        MagickImage_Chop(this@chop, geometry.ptr, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.clahe(
    xTiles: size_t,
    yTiles: size_t,
    numberBins: size_t,
    clipLimit: Double,
): Unit = withException { _, exceptionPtr ->
    MagickImage_Clahe(this@clahe, xTiles, yTiles, numberBins, clipLimit, exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.clamp(channels: Channels): Unit = withException { _, exceptionPtr ->
    MagickImage_Clamp(this@clamp, channels.toNative(), exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.clipPath(pathName: String, inside: Boolean): Unit =
    withException { _, exceptionPtr ->
        MagickImage_ClipPath(this@clipPath, pathName, inside.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@ExperimentalStdlibApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.clut(
    image: NativeMagickImage,
    method: PixelInterpolateMethod,
    channels: Channels,
): Unit = withException { _, exceptionPtr ->
    MagickImage_Clut(this@clut, image.ptr, method.toNative(), channels.toNative(), exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.colorDecisionList(fileName: String): Unit = withException { _, exceptionPtr ->
    MagickImage_ColorDecisionList(this@colorDecisionList, fileName, exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.colorMatrix(matrix: NativeDoubleMatrix): CPointer<Image>? =
    withException { _, exceptionPtr ->
        MagickImage_ColorMatrix(this@colorMatrix, matrix.ptr, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@ExperimentalStdlibApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.compare(
    image: NativeMagickImage,
    metric: ErrorMetric,
    channels: Channels,
): Pair<Double, CPointer<Image>?> = withException { placement, exceptionPtr ->
    val distortion = placement.alloc<DoubleVar>()

    val differenceImage = MagickImage_Compare(
        this,
        image.ptr,
        metric.toNative(),
        channels.toNative(),
        distortion.ptr,
        exceptionPtr
    )

    Pair(distortion.value, differenceImage)
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@ExperimentalStdlibApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.compareDistortion(
    image: NativeMagickImage,
    metric: ErrorMetric,
    channels: Channels,
): Double = withException { _, exceptionPtr ->
    MagickImage_CompareDistortion(this, image.ptr, metric.toNative(), channels.toNative(), exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@ExperimentalStdlibApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.composite(
    image: NativeMagickImage,
    x: Long,
    y: Long,
    compose: CompositeOperator,
    channels: Channels,
): Unit = withException { _, exceptionPtr ->
    MagickImage_Composite(
        this@composite,
        image.ptr,
        x,
        y,
        compose.toNative(),
        channels.toNative(),
        exceptionPtr
    )
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@ExperimentalStdlibApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.compositeGravity(
    image: NativeMagickImage,
    gravity: Gravity,
    x: Long,
    y: Long,
    compose: CompositeOperator,
    channels: Channels,
): Unit = withException { _, exceptionPtr ->
    MagickImage_CompositeGravity(
        this@compositeGravity,
        image.ptr,
        gravity.toNative(),
        x,
        y,
        compose.toNative(),
        channels.toNative(),
        exceptionPtr
    )
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.contrast(enhance: Boolean): Unit = withException { _, exceptionPtr ->
    MagickImage_Contrast(this@contrast, enhance.toNative(), exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.contrastStretch(
    blackPoint: Double,
    whitePoint: Double,
    channels: Channels,
): Unit = withException { _, exceptionPtr ->
    MagickImage_ContrastStretch(
        this@contrastStretch,
        blackPoint,
        whitePoint,
        channels.toNative(),
        exceptionPtr
    )
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.convolve(matrix: NativeDoubleMatrix): Unit = withException { _, exceptionPtr ->
    MagickImage_Convolve(this@convolve, matrix.ptr, exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@ExperimentalStdlibApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.copyPixels(
    image: NativeMagickImage,
    geometry: NativeMagickRectangle,
    offset: NativeOffsetInfo,
    channels: Channels,
): Unit = withException { _, exceptionPtr ->
    MagickImage_CopyPixels(this@copyPixels, image.ptr, geometry.ptr, offset.ptr, channels.toNative(), exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.crop(geometry: String, gravity: Gravity): CPointer<Image>? =
    withException { _, exceptionPtr ->
        MagickImage_Crop(this@crop, geometry, gravity.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.cycleColormap(amount: Long): Unit = withException { _, exceptionPtr ->
    MagickImage_CycleColormap(this@cycleColormap, amount, exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.decipher(passphrase: String): Unit = withException { _, exceptionPtr ->
    MagickImage_Decipher(this@decipher, passphrase, exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.deskew(threshold: Double): CPointer<Image>? = withException { _, exceptionPtr ->
    MagickImage_Deskew(this@deskew, threshold, exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.despeckle(): CPointer<Image>? = withException { _, exceptionPtr ->
    MagickImage_Despeckle(this@despeckle, exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.determineBitDepth(channels: Channels): size_t = withException { _, exceptionPtr ->
    MagickImage_DetermineBitDepth(this@determineBitDepth, channels.toNative(), exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.determineColorType(): ColorType = withException { _, exceptionPtr ->
    MagickImage_DetermineColorType(this@determineColorType, exceptionPtr).toMagick()
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.distort(
    method: DistortMethod,
    bestfit: Boolean,
    arguments: DoubleArray,
): CPointer<Image>? = withException { _, exceptionPtr ->
    val length = arguments.size
    arguments.usePinned {
        MagickImage_Distort(
            this@distort,
            method.toNative(),
            bestfit.toNative(),
            it.addressOf(0),
            length.toULong(),
            exceptionPtr
        )
    }
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.emboss(radius: Double, sigma: Double): CPointer<Image>? =
    withException { _, exceptionPtr ->
        MagickImage_Emboss(this@emboss, radius, sigma, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.edge(radius: Double): CPointer<Image>? = withException { _, exceptionPtr ->
    MagickImage_Edge(this@edge, radius, exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.encipher(passphrase: String): Unit = withException { _, exceptionPtr ->
    MagickImage_Encipher(this@encipher, passphrase, exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.enhance(): CPointer<Image>? = withException { _, exceptionPtr ->
    MagickImage_Enhance(this@enhance, exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.equalize(channels: Channels): Unit = withException { _, exceptionPtr ->
    MagickImage_Equalize(this@equalize, channels.toNative(), exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.evaluateFunction(
    channels: Channels,
    evaluateFunction: EvaluateFunction,
    values: DoubleArray,
): Unit = withException { _, exceptionPtr ->
    val length = values.size
    values.usePinned {
        MagickImage_EvaluateFunction(
            this@evaluateFunction,
            channels.toNative(),
            evaluateFunction.toNative(),
            it.addressOf(0),
            length.toULong(),
            exceptionPtr
        )
    }
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@ExperimentalStdlibApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.evaluateGeometry(
    channels: Channels,
    geometry: NativeMagickRectangle,
    evaluateOperator: EvaluateOperator,
    value: Double,
): Unit = withException { _, exceptionPtr ->
    MagickImage_EvaluateGeometry(
        this@evaluateGeometry,
        channels.toNative(),
        geometry.ptr,
        evaluateOperator.toNative(),
        value,
        exceptionPtr
    )
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.evaluateOperator(
    channels: Channels,
    evaluateOperator: EvaluateOperator,
    value: Double,
): Unit = withException { _, exceptionPtr ->
    MagickImage_EvaluateOperator(
        this@evaluateOperator,
        channels.toNative(),
        evaluateOperator.toNative(),
        value,
        exceptionPtr
    )
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.extent(geometry: String, gravity: Gravity): CPointer<Image>? =
    withException { _, exceptionPtr ->
        MagickImage_Extent(this@extent, geometry, gravity.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.flip(): CPointer<Image>? = withException { _, exceptionPtr ->
    MagickImage_Flip(this@flip, exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.flop(): CPointer<Image>? = withException { _, exceptionPtr ->
    MagickImage_Flop(this@flop, exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.frame(geometry: NativeMagickRectangle): CPointer<Image>? =
    withException { _, exceptionPtr ->
        MagickImage_Frame(this@frame, geometry.ptr, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.fx(expression: String, channels: Channels): CPointer<Image>? =
    withException { _, exceptionPtr ->
        MagickImage_Fx(this@fx, expression, channels.toNative(), exceptionPtr)
    }


@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.gammaCorrect(gamma: Double, channels: Channels): Unit =
    withException { _, exceptionPtr ->
        MagickImage_GammaCorrect(this@gammaCorrect, gamma, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.gaussianBlur(radius: Double, sigma: Double, channels: Channels): CPointer<Image>? =
    withException { _, exceptionPtr ->
        MagickImage_GaussianBlur(this@gaussianBlur, radius, sigma, channels.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.getAttribute(name: String): String? = withException { _, exceptionPtr ->
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
@Throws(MagickException::class)
public inline fun CPointer<Image>.grayscale(method: PixelIntensityMethod): Unit = withException { _, exceptionPtr ->
    MagickImage_Grayscale(this@grayscale, method.toNative(), exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.haldClut(image: NativeMagickImage): Unit = withException { _, exceptionPtr ->
    MagickImage_HaldClut(this@haldClut, image.ptr, exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.houghLine(width: ULong, height: ULong, threshold: ULong): Unit =
    withException { _, exceptionPtr ->
        MagickImage_HoughLine(this@houghLine, width, height, threshold, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.implode(amount: Double, method: PixelInterpolateMethod): CPointer<Image>? =
    withException { _, exceptionPtr ->
        MagickImage_Implode(this@implode, amount, method.toNative(), exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.interpolativeResize(
    geometry: String,
    method: PixelInterpolateMethod,
): CPointer<Image>? = withException { _, exceptionPtr ->
    MagickImage_InterpolativeResize(this@interpolativeResize, geometry, method.toNative(), exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.kmeans(numberColors: ULong, maxIterations: ULong, tolerance: Double): Unit =
    withException { _, exceptionPtr ->
        MagickImage_Kmeans(this@kmeans, numberColors, maxIterations, tolerance, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.kuwahara(radius: Double, sigma: Double): CPointer<Image>? =
    withException { _, exceptionPtr ->
        MagickImage_Kuwahara(this@kuwahara, radius, sigma, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.linearStretch(blackPoint: Double, whitePoint: Double): Unit =
    withException { _, exceptionPtr ->
        MagickImage_LinearStretch(this@linearStretch, blackPoint, whitePoint, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.liquidRescale(geometry: String, deltaX: Double, rigidity: Double): CPointer<Image>? =
    withException { _, exceptionPtr ->
        MagickImage_LiquidRescale(this@liquidRescale, geometry, deltaX, rigidity, exceptionPtr)
    }

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.localContrast(radius: Double, strength: Double, channels: Channels): CPointer<Image>? =
    withException { _, exceptionPtr ->
        MagickImage_LocalContrast(this@localContrast, radius, strength, channels.toNative(), exceptionPtr)
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

@ExperimentalForeignApi
public inline fun CPointer<Image>.resetArtifactIterator(): Unit = MagickImage_ResetArtifactIterator(this)

@ExperimentalForeignApi
public inline fun CPointer<Image>.resetAttributeIterator(): Unit = MagickImage_ResetAttributeIterator(this)

@ExperimentalForeignApi
public inline fun CPointer<Image>.resetProfileIterator(): Unit = MagickImage_ResetProfileIterator(this)


@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalStdlibApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.setColorMetric(image: NativeMagickImage): Boolean =
    withException { _, exceptionPtr ->
        MagickImage_SetColorMetric(this@setColorMetric, image.ptr, exceptionPtr).toPrimitive()
    }

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.setAlpha(value: AlphaOption): Unit = withException { _, exceptionPtr ->
    MagickImage_SetAlpha(this@setAlpha, value.toNative(), exceptionPtr)
}

@ExperimentalForeignApi
public inline fun CPointer<Image>.setArtifact(name: String, value: String): Unit =
    MagickImage_SetArtifact(this@setArtifact, name, value)

@ExperimentalContracts
@ExperimentalForeignApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.setAttribute(name: String, value: String): Unit = withException { _, exceptionPtr ->
    MagickImage_SetAttribute(this@setAttribute, name, value, exceptionPtr)
}

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@Throws(MagickException::class)
public inline fun CPointer<Image>.sigmoidalContrast(
    sharpen: Boolean,
    contrast: Double,
    midpoint: Double,
    channels: Channels,
): Unit = withException { _, exceptionPtr ->
    MagickImage_SigmoidalContrast(
        this@sigmoidalContrast,
        sharpen.toNative(),
        contrast,
        midpoint,
        channels.toNative(),
        exceptionPtr
    )
}

//endregion
