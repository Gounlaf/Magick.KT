package imagemagick.magicknative.settings

import imagemagick.core.enums.FillRule
import imagemagick.core.enums.FontStyleType
import imagemagick.core.enums.FontWeight
import imagemagick.core.enums.Gravity
import imagemagick.core.enums.LineCap
import imagemagick.core.enums.LineJoin
import imagemagick.core.enums.TextDirection
import imagemagick.core.toMagick
import imagemagick.core.toNative
import imagemagick.magicknative.NativeMagickImage
import imagemagick.magicknative.colors.NativeMagickColor
import imagemagick.magicknative.colors.NativeMagickColor.Companion.toMagick
import imagemagick.magicknative.exceptions.ExceptionInfoPtrVar
import imagemagick.magicknative.toNative
import imagemagick.magicknative.toPrimitive
import kotlin.contracts.ExperimentalContracts
import kotlin.experimental.ExperimentalNativeApi
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.pointed
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toCValues
import kotlinx.cinterop.toKString
import libMagickNative.DrawInfo
import libMagickNative.DrawingSettings_BorderColor_Get
import libMagickNative.DrawingSettings_BorderColor_Set
import libMagickNative.DrawingSettings_Create
import libMagickNative.DrawingSettings_Dispose
import libMagickNative.DrawingSettings_FillColor_Get
import libMagickNative.DrawingSettings_FillColor_Set
import libMagickNative.DrawingSettings_FillRule_Get
import libMagickNative.DrawingSettings_FillRule_Set
import libMagickNative.DrawingSettings_FontFamily_Get
import libMagickNative.DrawingSettings_FontFamily_Set
import libMagickNative.DrawingSettings_FontPointsize_Get
import libMagickNative.DrawingSettings_FontPointsize_Set
import libMagickNative.DrawingSettings_FontStyle_Get
import libMagickNative.DrawingSettings_FontStyle_Set
import libMagickNative.DrawingSettings_FontWeight_Get
import libMagickNative.DrawingSettings_FontWeight_Set
import libMagickNative.DrawingSettings_Font_Get
import libMagickNative.DrawingSettings_Font_Set
import libMagickNative.DrawingSettings_SetAffine
import libMagickNative.DrawingSettings_SetFillPattern
import libMagickNative.DrawingSettings_SetStrokeDashArray
import libMagickNative.DrawingSettings_SetStrokePattern
import libMagickNative.DrawingSettings_SetText
import libMagickNative.DrawingSettings_StrokeAntiAlias_Get
import libMagickNative.DrawingSettings_StrokeAntiAlias_Set
import libMagickNative.DrawingSettings_StrokeColor_Get
import libMagickNative.DrawingSettings_StrokeColor_Set
import libMagickNative.DrawingSettings_StrokeDashOffset_Get
import libMagickNative.DrawingSettings_StrokeDashOffset_Set
import libMagickNative.DrawingSettings_StrokeLineCap_Get
import libMagickNative.DrawingSettings_StrokeLineCap_Set
import libMagickNative.DrawingSettings_StrokeLineJoin_Get
import libMagickNative.DrawingSettings_StrokeLineJoin_Set
import libMagickNative.DrawingSettings_StrokeMiterLimit_Get
import libMagickNative.DrawingSettings_StrokeMiterLimit_Set
import libMagickNative.DrawingSettings_StrokeWidth_Get
import libMagickNative.DrawingSettings_StrokeWidth_Set
import libMagickNative.DrawingSettings_TextAntiAlias_Get
import libMagickNative.DrawingSettings_TextAntiAlias_Set
import libMagickNative.DrawingSettings_TextDirection_Get
import libMagickNative.DrawingSettings_TextDirection_Set
import libMagickNative.DrawingSettings_TextEncoding_Get
import libMagickNative.DrawingSettings_TextEncoding_Set
import libMagickNative.DrawingSettings_TextGravity_Get
import libMagickNative.DrawingSettings_TextGravity_Set
import libMagickNative.DrawingSettings_TextInterlineSpacing_Get
import libMagickNative.DrawingSettings_TextInterlineSpacing_Set
import libMagickNative.DrawingSettings_TextInterwordSpacing_Get
import libMagickNative.DrawingSettings_TextInterwordSpacing_Set
import libMagickNative.DrawingSettings_TextKerning_Get
import libMagickNative.DrawingSettings_TextKerning_Set
import libMagickNative.DrawingSettings_TextUnderColor_Get
import libMagickNative.DrawingSettings_TextUnderColor_Set

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@ExperimentalStdlibApi
public class NativeDrawingSettings : AutoCloseable {
    public val ptr: CPointer<DrawInfo> = create()

    override fun close() {
        ptr.dispose()
    }

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

    public fun setFillPattern(value: NativeMagickImage): Unit = ptr.setFillPattern(value)

    public fun setStrokeDashArray(value: Iterable<Double>): Unit = ptr.setStrokeDashArray(value)

    public fun setStrokePattern(value: NativeMagickImage): Unit = ptr.setStrokePattern(value)

    public fun setText(value: String?): Unit = ptr.setText(value)

    public companion object {
        @Throws(IllegalStateException::class)
        public inline fun create(): CPointer<DrawInfo> =
            DrawingSettings_Create()
                ?: error("Failed to instantiate native DrawingSettings")

        public inline fun CPointer<DrawInfo>.dispose(): Unit = DrawingSettings_Dispose(this)

        public inline fun CPointer<DrawInfo>.borderColor(): NativeMagickColor? =
            DrawingSettings_BorderColor_Get(this).toMagick()

        public inline fun CPointer<DrawInfo>.borderColor(value: NativeMagickColor?): Unit =
            DrawingSettings_BorderColor_Set(this, value?.ptr)

        public inline fun CPointer<DrawInfo>.fillColor(): NativeMagickColor? =
            DrawingSettings_FillColor_Get(this).toMagick()

        public inline fun CPointer<DrawInfo>.fillColor(value: NativeMagickColor?): Unit =
            DrawingSettings_FillColor_Set(this, value?.ptr)

        public inline fun CPointer<DrawInfo>.fillRule(): FillRule = DrawingSettings_FillRule_Get(this).toMagick()

        public inline fun CPointer<DrawInfo>.fillRule(value: FillRule): Unit =
            DrawingSettings_FillRule_Set(this, value.toNative())

        public inline fun CPointer<DrawInfo>.font(): String? = DrawingSettings_Font_Get(this)?.toKString()

        public inline fun CPointer<DrawInfo>.font(value: String?): Unit = DrawingSettings_Font_Set(this, value)

        public inline fun CPointer<DrawInfo>.fontFamily(): String? = DrawingSettings_FontFamily_Get(this)?.toKString()

        public inline fun CPointer<DrawInfo>.fontFamily(value: String?): Unit =
            DrawingSettings_FontFamily_Set(this, value)

        public inline fun CPointer<DrawInfo>.fontPointsize(): Double = DrawingSettings_FontPointsize_Get(this)

        public inline fun CPointer<DrawInfo>.fontPointsize(value: Double): Unit =
            DrawingSettings_FontPointsize_Set(this, value)

        public inline fun CPointer<DrawInfo>.fontStyle(): FontStyleType = DrawingSettings_FontStyle_Get(this).toMagick()

        public inline fun CPointer<DrawInfo>.fontStyle(value: FontStyleType): Unit =
            DrawingSettings_FontStyle_Set(this, value.toNative())

        public inline fun CPointer<DrawInfo>.fontWeight(): FontWeight = DrawingSettings_FontWeight_Get(this).toMagick()

        public inline fun CPointer<DrawInfo>.fontWeight(value: FontWeight): Unit =
            DrawingSettings_FontWeight_Set(this, value.weight.convert())

        public inline fun CPointer<DrawInfo>.strokeAntiAlias(): Boolean =
            DrawingSettings_StrokeAntiAlias_Get(this).toPrimitive()

        public inline fun CPointer<DrawInfo>.strokeAntiAlias(value: Boolean): Unit =
            DrawingSettings_StrokeAntiAlias_Set(this, value.toNative())

        public inline fun CPointer<DrawInfo>.strokeColor(): NativeMagickColor? =
            DrawingSettings_StrokeColor_Get(this).toMagick()

        public inline fun CPointer<DrawInfo>.strokeColor(value: NativeMagickColor?): Unit =
            DrawingSettings_StrokeColor_Set(this, value?.ptr)

        public inline fun CPointer<DrawInfo>.strokeDashOffset(): Double = DrawingSettings_StrokeDashOffset_Get(this)

        public inline fun CPointer<DrawInfo>.strokeDashOffset(value: Double): Unit =
            DrawingSettings_StrokeDashOffset_Set(this, value)

        public inline fun CPointer<DrawInfo>.strokeLineCap(): LineCap =
            DrawingSettings_StrokeLineCap_Get(this).toMagick()

        public inline fun CPointer<DrawInfo>.strokeLineCap(value: LineCap): Unit =
            DrawingSettings_StrokeLineCap_Set(this, value.toNative())

        public inline fun CPointer<DrawInfo>.strokeLineJoin(): LineJoin =
            DrawingSettings_StrokeLineJoin_Get(this).toMagick()

        public inline fun CPointer<DrawInfo>.strokeLineJoin(value: LineJoin): Unit =
            DrawingSettings_StrokeLineJoin_Set(
                this,
                value.toNative(),
            )

        public inline fun CPointer<DrawInfo>.strokeMiterLimit(): UInt =
            DrawingSettings_StrokeMiterLimit_Get(this).convert()

        public inline fun CPointer<DrawInfo>.strokeMiterLimit(value: UInt): Unit =
            DrawingSettings_StrokeMiterLimit_Set(
                this,
                value.convert(),
            )

        public inline fun CPointer<DrawInfo>.strokeWidth(): Double = DrawingSettings_StrokeWidth_Get(this)

        public inline fun CPointer<DrawInfo>.strokeWidth(value: Double): Unit =
            DrawingSettings_StrokeWidth_Set(this, value)

        public inline fun CPointer<DrawInfo>.textAntiAlias(): Boolean =
            DrawingSettings_TextAntiAlias_Get(this).toPrimitive()

        public inline fun CPointer<DrawInfo>.textAntiAlias(value: Boolean): Unit =
            DrawingSettings_TextAntiAlias_Set(this, value.toNative())

        public inline fun CPointer<DrawInfo>.textDirection(): TextDirection =
            DrawingSettings_TextDirection_Get(this).toMagick()

        public inline fun CPointer<DrawInfo>.textDirection(value: TextDirection): Unit =
            DrawingSettings_TextDirection_Set(
                this,
                value.toNative(),
            )

        public inline fun CPointer<DrawInfo>.textEncoding(): String? =
            DrawingSettings_TextEncoding_Get(this)?.toKString()

        public inline fun CPointer<DrawInfo>.textEncoding(value: String?): Unit =
            DrawingSettings_TextEncoding_Set(this, value)

        public inline fun CPointer<DrawInfo>.textGravity(): Gravity = DrawingSettings_TextGravity_Get(this).toMagick()

        public inline fun CPointer<DrawInfo>.textGravity(value: Gravity): Unit =
            DrawingSettings_TextGravity_Set(this, value.toNative())

        public inline fun CPointer<DrawInfo>.textInterlineSpacing(): Double =
            DrawingSettings_TextInterlineSpacing_Get(this)

        public inline fun CPointer<DrawInfo>.textInterlineSpacing(value: Double): Unit =
            DrawingSettings_TextInterlineSpacing_Set(this, value)

        public inline fun CPointer<DrawInfo>.textInterwordSpacing(): Double =
            DrawingSettings_TextInterwordSpacing_Get(this)

        public inline fun CPointer<DrawInfo>.textInterwordSpacing(value: Double): Unit =
            DrawingSettings_TextInterwordSpacing_Set(this, value)

        public inline fun CPointer<DrawInfo>.textKerning(): Double = DrawingSettings_TextKerning_Get(this)

        public inline fun CPointer<DrawInfo>.textKerning(value: Double): Unit =
            DrawingSettings_TextKerning_Set(this, value)

        public inline fun CPointer<DrawInfo>.textUnderColor(): NativeMagickColor? =
            DrawingSettings_TextUnderColor_Get(this).toMagick()

        public inline fun CPointer<DrawInfo>.textUnderColor(value: NativeMagickColor?): Unit =
            DrawingSettings_TextUnderColor_Set(this, value?.ptr)

        public inline fun CPointer<DrawInfo>.setAffine(
            scaleX: Double,
            scaleY: Double,
            shearX: Double,
            shearY: Double,
            translateX: Double,
            translateY: Double,
        ): Unit =
            DrawingSettings_SetAffine(
                this,
                scaleX,
                scaleY,
                shearX,
                shearY,
                translateX,
                translateY,
            )

        public inline fun CPointer<DrawInfo>.setFillPattern(value: NativeMagickImage) {
            val ptr = this

            memScoped {
                val exceptionInfo = alloc<ExceptionInfoPtrVar>()

                DrawingSettings_SetFillPattern(ptr, value.ptr, exceptionInfo.ptr)

                // TODO manage exceptions
                exceptionInfo.pointed?.let { exception ->
                    println("got exception")
                    println(exception.error_number)
                    println(exception.description)
                }
            }
        }

        public inline fun CPointer<DrawInfo>.setStrokeDashArray(value: Iterable<Double>): Unit =
            value.toList().toDoubleArray().toCValues()
                .let { DrawingSettings_SetStrokeDashArray(this, it, it.size.convert()) }

        public inline fun CPointer<DrawInfo>.setStrokePattern(value: NativeMagickImage) {
            val ptr = this

            memScoped {
                val exceptionInfo = alloc<ExceptionInfoPtrVar>()

                DrawingSettings_SetStrokePattern(ptr, value.ptr, exceptionInfo.ptr)

                // TODO manage exceptions
                exceptionInfo.pointed?.let { exception ->
                    println("got exception")
                    println(exception.error_number)
                    println(exception.description)
                }
            }
        }

        public inline fun CPointer<DrawInfo>.setText(value: String?): Unit = DrawingSettings_SetText(this, value)
    }
}
