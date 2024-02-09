package imagemagick.magicknative.colors

import imagemagick.QuantumType
import imagemagick.magicknative.toNative
import imagemagick.magicknative.toPrimitive
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.MagickColor_Alpha_Get
import libMagickNative.MagickColor_Alpha_Set
import libMagickNative.MagickColor_Black_Get
import libMagickNative.MagickColor_Black_Set
import libMagickNative.MagickColor_Blue_Get
import libMagickNative.MagickColor_Blue_Set
import libMagickNative.MagickColor_Count_Get
import libMagickNative.MagickColor_Create
import libMagickNative.MagickColor_Dispose
import libMagickNative.MagickColor_FuzzyEquals
import libMagickNative.MagickColor_Green_Get
import libMagickNative.MagickColor_Green_Set
import libMagickNative.MagickColor_Initialize
import libMagickNative.MagickColor_IsCMYK_Get
import libMagickNative.MagickColor_IsCMYK_Set
import libMagickNative.MagickColor_Red_Get
import libMagickNative.MagickColor_Red_Set
import libMagickNative.PixelInfo

@ExperimentalStdlibApi
@ExperimentalForeignApi
public class NativeMagickColor(
    public val ptr: CPointer<PixelInfo> = create(),
) : AutoCloseable {
    public override fun close(): Unit = ptr.dispose()

    public fun count(): ULong = ptr.count()

    public var red: QuantumType
        get() = ptr.red()
        set(value) = ptr.red(value)

    public var green: QuantumType
        get() = ptr.green()
        set(value) = ptr.green(value)

    public var blue: QuantumType
        get() = ptr.blue()
        set(value) = ptr.blue(value)

    public var alpha: QuantumType
        get() = ptr.alpha()
        set(value) = ptr.alpha(value)

    public var black: QuantumType
        get() = ptr.black()
        set(value) = ptr.black(value)

    public var isCMYK: Boolean
        get() = ptr.isCMYK()
        set(value) = ptr.isCMYK(value)

    public fun fuzzyEquals(
        other: NativeMagickColor,
        fuzz: QuantumType,
    ): Boolean = ptr.fuzzyEquals(other.ptr, fuzz)

    public fun initialize(value: String): Boolean = ptr.initialize(value)

    public companion object {
        public inline fun CPointer<PixelInfo>?.toMagick(): NativeMagickColor? = this?.let { NativeMagickColor(it) }

        @Throws(IllegalStateException::class)
        public inline fun create(): CPointer<PixelInfo> = MagickColor_Create() ?: error("Failed to instantiate native MagickColor")

        public inline fun CPointer<PixelInfo>.dispose(): Unit = MagickColor_Dispose(this)

        public inline fun CPointer<PixelInfo>.count(): ULong = MagickColor_Count_Get(this)

        public inline fun CPointer<PixelInfo>.red(): QuantumType = MagickColor_Red_Get(this)

        public inline fun CPointer<PixelInfo>.red(value: QuantumType): Unit = MagickColor_Red_Set(this, value)

        public inline fun CPointer<PixelInfo>.green(): QuantumType = MagickColor_Green_Get(this)

        public inline fun CPointer<PixelInfo>.green(value: QuantumType): Unit = MagickColor_Green_Set(this, value)

        public inline fun CPointer<PixelInfo>.blue(): QuantumType = MagickColor_Blue_Get(this)

        public inline fun CPointer<PixelInfo>.blue(value: QuantumType): Unit = MagickColor_Blue_Set(this, value)

        public inline fun CPointer<PixelInfo>.alpha(): QuantumType = MagickColor_Alpha_Get(this)

        public inline fun CPointer<PixelInfo>.alpha(value: QuantumType): Unit = MagickColor_Alpha_Set(this, value)

        public inline fun CPointer<PixelInfo>.black(): QuantumType = MagickColor_Black_Get(this)

        public inline fun CPointer<PixelInfo>.black(value: QuantumType): Unit = MagickColor_Black_Set(this, value)

        public inline fun CPointer<PixelInfo>.isCMYK(): Boolean = MagickColor_IsCMYK_Get(this).toPrimitive()

        public inline fun CPointer<PixelInfo>.isCMYK(value: Boolean): Unit = MagickColor_IsCMYK_Set(this, value.toNative())

        public inline fun CPointer<PixelInfo>.fuzzyEquals(
            other: CPointer<PixelInfo>,
            fuzz: QuantumType,
        ): Boolean =
            MagickColor_FuzzyEquals(this, other, fuzz)
                .toPrimitive()

        public inline fun CPointer<PixelInfo>.initialize(value: String): Boolean = MagickColor_Initialize(this, value).toPrimitive()
    }
}
