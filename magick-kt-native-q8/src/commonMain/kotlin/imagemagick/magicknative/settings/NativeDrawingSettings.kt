@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative.settings

import imagemagick.bridge.borderColor
import imagemagick.bridge.dispose
import imagemagick.bridge.fillColor
import imagemagick.bridge.fillRule
import imagemagick.bridge.font
import imagemagick.bridge.fontFamily
import imagemagick.bridge.fontPointsize
import imagemagick.bridge.fontStyle
import imagemagick.bridge.fontWeight
import imagemagick.bridge.setAffine
import imagemagick.bridge.setFillPattern
import imagemagick.bridge.setStrokeDashArray
import imagemagick.bridge.setStrokePattern
import imagemagick.bridge.setText
import imagemagick.bridge.strokeAntiAlias
import imagemagick.bridge.strokeColor
import imagemagick.bridge.strokeDashOffset
import imagemagick.bridge.strokeLineCap
import imagemagick.bridge.strokeLineJoin
import imagemagick.bridge.strokeMiterLimit
import imagemagick.bridge.strokeWidth
import imagemagick.bridge.textAntiAlias
import imagemagick.bridge.textDirection
import imagemagick.bridge.textEncoding
import imagemagick.bridge.textGravity
import imagemagick.bridge.textInterlineSpacing
import imagemagick.bridge.textInterwordSpacing
import imagemagick.bridge.textKerning
import imagemagick.bridge.textUnderColor
import imagemagick.core.enums.FillRule
import imagemagick.core.enums.FontStyleType
import imagemagick.core.enums.FontWeight
import imagemagick.core.enums.Gravity
import imagemagick.core.enums.LineCap
import imagemagick.core.enums.LineJoin
import imagemagick.core.enums.TextDirection
import imagemagick.magicknative.NativeInstance
import imagemagick.magicknative.NativeMagickImage
import imagemagick.magicknative.colors.NativeMagickColor
import imagemagick.magicknative.exceptions.MagickExceptionHelper.checkException
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.DrawInfo
import libMagickNative.DrawingSettings_Create
import kotlin.contracts.ExperimentalContracts
import kotlin.experimental.ExperimentalNativeApi

@ExperimentalForeignApi
@ExperimentalStdlibApi
public class NativeDrawingSettings : NativeInstance<DrawInfo>(), AutoCloseable {
    public override var ptr: CPointer<DrawInfo> = create()

    override fun close(): Unit = ptr.dispose()

    public var borderColor: NativeMagickColor?
        get() = ptr.borderColor()
        set(value) = ptr.borderColor(value)

    public var fillColor: NativeMagickColor?
        get() = ptr.fillColor()
        set(value) = ptr.fillColor(value)

    public var fillRule: FillRule
        get() = ptr.fillRule()
        set(value) = ptr.fillRule(value)

    public var font: String?
        get() = ptr.font()
        set(value) = ptr.font(value)

    public var fontFamily: String?
        get() = ptr.fontFamily()
        set(value) = ptr.fontFamily(value)

    public var fontPointsize: Double
        get() = ptr.fontPointsize()
        set(value) = ptr.fontPointsize(value)

    public var fontStyle: FontStyleType
        get() = ptr.fontStyle()
        set(value) = ptr.fontStyle(value)

    public var fontWeight: FontWeight
        get() = ptr.fontWeight()
        set(value) = ptr.fontWeight(value)

    public var strokeAntiAlias: Boolean
        get() = ptr.strokeAntiAlias()
        set(value) = ptr.strokeAntiAlias(value)

    public var strokeColor: NativeMagickColor?
        get() = ptr.strokeColor()
        set(value) = ptr.strokeColor(value)

    public var strokeDashOffset: Double
        get() = ptr.strokeDashOffset()
        set(value) = ptr.strokeDashOffset(value)

    public var strokeLineCap: LineCap
        get() = ptr.strokeLineCap()
        set(value) = ptr.strokeLineCap(value)

    public var strokeLineJoin: LineJoin
        get() = ptr.strokeLineJoin()
        set(value) = ptr.strokeLineJoin(value)

    public var strokeMiterLimit: UInt
        get() = ptr.strokeMiterLimit()
        set(value) = ptr.strokeMiterLimit(value)

    public var strokeWidth: Double
        get() = ptr.strokeWidth()
        set(value) = ptr.strokeWidth(value)

    public var textAntiAlias: Boolean
        get() = ptr.textAntiAlias()
        set(value) = ptr.textAntiAlias(value)

    public var textDirection: TextDirection
        get() = ptr.textDirection()
        set(value) = ptr.textDirection(value)

    public var textEncoding: String?
        get() = ptr.textEncoding()
        set(value) = ptr.textEncoding(value)

    public var textGravity: Gravity
        get() = ptr.textGravity()
        set(value) = ptr.textGravity(value)

    public var textInterlineSpacing: Double
        get() = ptr.textInterlineSpacing()
        set(value) = ptr.textInterlineSpacing(value)

    public var textInterwordSpacing: Double
        get() = ptr.textInterwordSpacing()
        set(value) = ptr.textInterwordSpacing(value)

    public var textKerning: Double
        get() = ptr.textKerning()
        set(value) = ptr.textKerning(value)

    public var textUnderColor: NativeMagickColor?
        get() = ptr.textUnderColor()
        set(value) = ptr.textUnderColor(value)

    public fun setAffine(
        scaleX: Double,
        scaleY: Double,
        shearX: Double,
        shearY: Double,
        translateX: Double,
        translateY: Double,
    ): Unit = ptr.setAffine(scaleX, scaleY, shearX, shearY, translateX, translateY)

    @ExperimentalContracts
    @ExperimentalNativeApi
    public fun setFillPattern(value: NativeMagickImage) {
        checkException {
            ptr.setFillPattern(value)
        }
    }

    public fun setStrokeDashArray(value: Iterable<Double>): Unit = ptr.setStrokeDashArray(value.toList().toDoubleArray())

    @ExperimentalContracts
    @ExperimentalNativeApi
    public fun setStrokePattern(value: NativeMagickImage) {
        checkException {
            ptr.setStrokePattern(value)
        }
    }

    public fun setText(value: String?): Unit = ptr.setText(value)

    public companion object {
        @Throws(IllegalStateException::class)
        public inline fun create(): CPointer<DrawInfo> = DrawingSettings_Create() ?: error("Failed to instantiate native DrawingSettings")
    }
}
