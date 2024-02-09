package imagemagick.settings

import imagemagick.MagickImage
import imagemagick.QuantumType
import imagemagick.colors.MagickColor
import imagemagick.core.drawables.DrawableAffine
import imagemagick.core.enums.FillRule
import imagemagick.core.enums.FontStyleType
import imagemagick.core.enums.FontWeight
import imagemagick.core.enums.Gravity
import imagemagick.core.enums.LineCap
import imagemagick.core.enums.LineJoin
import imagemagick.core.enums.TextDirection
import imagemagick.helpers.isNotNullOrEmpty
import imagemagick.magicknative.settings.NativeDrawingSettings
import kotlin.contracts.ExperimentalContracts
import kotlin.experimental.ExperimentalNativeApi
import kotlinx.cinterop.ExperimentalForeignApi
import imagemagick.core.MagickImageQuantum as IMagickImage
import imagemagick.core.colors.MagickColorQuantum as IMagickColor

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@ExperimentalStdlibApi
public class DrawingSettings internal constructor() {
    public var affine: DrawableAffine? = null
    public var borderColor: IMagickColor<QuantumType>?
    public var fillColor: IMagickColor<QuantumType>?
    public var fillPattern: IMagickImage<QuantumType>? = null
    public var fillRule: FillRule
    public var font: String?
    public var fontFamily: String?
    public var fontPointsize: Double
    public var fontStyle: FontStyleType
    public var fontWeight: FontWeight
    public var strokeAntiAlias: Boolean
    public var strokeColor: IMagickColor<QuantumType>?
    public var strokeDashArray: Iterable<Double>? = null
    public var strokeDashOffset: Double
    public var strokeLineCap: LineCap
    public var strokeLineJoin: LineJoin
    public var strokeMiterLimit: UInt
    public var strokePattern: IMagickImage<QuantumType>? = null
    public var strokeWidth: Double
    public var text: String? = null
    public var textAntiAlias: Boolean
    public var textDirection: TextDirection
    public var textEncoding: String?
    public var textGravity: Gravity
    public var textInterlineSpacing: Double
    public var textInterwordSpacing: Double
    public var textKerning: Double
    public var textUnderColor: IMagickColor<QuantumType>?

    init {
        NativeDrawingSettings().use { settings ->
            borderColor = settings.borderColor?.use { MagickColor.createInstance(it) }
            fillColor = settings.fillColor?.use { MagickColor.createInstance(it) }
            fillRule = settings.fillRule
            font = settings.font
            fontFamily = settings.fontFamily
            fontPointsize = settings.fontPointsize
            fontStyle = settings.fontStyle
            fontWeight = settings.fontWeight
            strokeAntiAlias = settings.strokeAntiAlias
            strokeColor = settings.strokeColor?.use { MagickColor.createInstance(it) }
            strokeDashOffset = settings.strokeDashOffset
            strokeLineCap = settings.strokeLineCap
            strokeLineJoin = settings.strokeLineJoin
            strokeMiterLimit = settings.strokeMiterLimit
            strokeWidth = settings.strokeWidth
            textAntiAlias = settings.textAntiAlias
            textDirection = settings.textDirection
            textEncoding = settings.textEncoding
            textGravity = settings.textGravity
            textInterlineSpacing = settings.textInterlineSpacing
            textInterwordSpacing = settings.textInterwordSpacing
            textKerning = settings.textKerning
            textUnderColor = settings.textUnderColor?.use { MagickColor.createInstance(it) }
        }
    }

    internal fun clone(): DrawingSettings =
        DrawingSettings().also {
            it.borderColor = MagickColor.clone(borderColor)
            it.fillColor = MagickColor.clone(fillColor)
            it.fillRule = fillRule
            it.font = font
            it.fontFamily = fontFamily
            it.fontPointsize = fontPointsize
            it.fontStyle = fontStyle
            it.fontWeight = fontWeight
            it.strokeAntiAlias = strokeAntiAlias
            it.strokeColor = MagickColor.clone(strokeColor)
            it.strokeDashOffset = strokeDashOffset
            it.strokeLineCap = strokeLineCap
            it.strokeLineJoin = strokeLineJoin
            it.strokeMiterLimit = strokeMiterLimit
            it.strokeWidth = strokeWidth
            it.textAntiAlias = textAntiAlias
            it.textDirection = textDirection
            it.textEncoding = textEncoding
            it.textGravity = textGravity
            it.textInterlineSpacing = textInterlineSpacing
            it.textInterwordSpacing = textInterwordSpacing
            it.textKerning = textKerning
            it.textUnderColor = MagickColor.clone(textUnderColor)

            it.affine = affine
            it.fillPattern = MagickImage.clone(it.fillPattern)
            it.strokeDashArray = strokeDashArray?.toList()
        }

    public fun createNativeInstance(): NativeDrawingSettings =
        NativeDrawingSettings().also { instance: NativeDrawingSettings ->
            borderColor?.let { color ->
                MagickColor.createNativeInstance(color).use { native ->
                    instance.borderColor = native
                }
            } ?: run { instance.borderColor = null }

            fillColor?.let { color ->
                MagickColor.createNativeInstance(color).use { native ->
                    instance.fillColor = native
                }
            } ?: run { instance.fillColor = null }

            instance.fillRule = fillRule
            instance.font = font
            instance.fontFamily = fontFamily
            instance.fontPointsize = fontPointsize
            instance.fontStyle = fontStyle
            instance.fontWeight = fontWeight
            instance.strokeAntiAlias = strokeAntiAlias
            strokeColor?.let { color ->
                MagickColor.createNativeInstance(color).use { native ->
                    instance.strokeColor = native
                }
            } ?: run { instance.strokeColor = null }
            instance.strokeDashOffset = strokeDashOffset
            instance.strokeLineCap = strokeLineCap
            instance.strokeLineJoin = strokeLineJoin
            instance.strokeMiterLimit = strokeMiterLimit
            instance.strokeWidth = strokeWidth
            instance.textAntiAlias = textAntiAlias
            instance.textDirection = textDirection
            instance.textEncoding = textEncoding

            textUnderColor?.let { color ->
                MagickColor.createNativeInstance(color).use { native ->
                    instance.textUnderColor = native
                }
            } ?: run { instance.textUnderColor = null }

            affine?.let {
                instance.setAffine(it.scaleX, it.scaleY, it.shearX, it.shearY, it.translateX, it.translateY)
            }

            fillPattern?.let { MagickImage.nativeInstance(it) }?.let { instance.setFillPattern(it) }

            strokeDashArray?.let { instance.setStrokeDashArray(it) }

            strokePattern?.let { MagickImage.nativeInstance(it) }?.let { instance.setStrokePattern(it) }

            text.takeIf { it.isNotNullOrEmpty() }?.let { instance.setText(it) }
        }
}
