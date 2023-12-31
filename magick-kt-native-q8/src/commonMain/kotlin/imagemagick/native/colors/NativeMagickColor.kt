package imagemagick.native.colors

import imagemagick.QuantumType
import imagemagick.native.toNative
import imagemagick.native.toPrimitive
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
class NativeMagickColor : AutoCloseable {
    private val ptr: CPointer<PixelInfo> = create()

    override fun close() = ptr.dispose()

    fun count(): ULong = ptr.count()

    fun red(): QuantumType = ptr.red()
    fun red(value: QuantumType) = ptr.red(value)

    fun green(): QuantumType = ptr.green()
    fun green(value: QuantumType) = ptr.green(value)

    fun blue(): QuantumType = ptr.blue()
    fun blue(value: QuantumType) = ptr.blue(value)

    fun alpha(): QuantumType = ptr.alpha()
    fun alpha(value: QuantumType) = ptr.alpha(value)

    fun black(): QuantumType = ptr.black()
    fun black(value: QuantumType) = ptr.black(value)

    fun isCMYK(): Boolean = ptr.isCMYK()
    fun isCMYK(value: Boolean) = ptr.isCMYK(value)

    fun fuzzyEquals(other: NativeMagickColor, fuzz: QuantumType): Boolean = ptr.fuzzyEquals(other.ptr, fuzz)

    fun initialize(value: String): Boolean = ptr.initialize(value)

    companion object {
        @Throws(IllegalStateException::class)
        inline fun create(): CPointer<PixelInfo> =
            MagickColor_Create() ?: error("Failed to instantiate native MagickColor")

        inline fun CPointer<PixelInfo>.dispose() = MagickColor_Dispose(this)

        inline fun CPointer<PixelInfo>.count(): ULong = MagickColor_Count_Get(this)

        inline fun CPointer<PixelInfo>.red(): QuantumType = MagickColor_Red_Get(this)
        inline fun CPointer<PixelInfo>.red(value: QuantumType) = MagickColor_Red_Set(this, value)

        inline fun CPointer<PixelInfo>.green(): QuantumType = MagickColor_Green_Get(this)
        inline fun CPointer<PixelInfo>.green(value: QuantumType) = MagickColor_Green_Set(this, value)

        inline fun CPointer<PixelInfo>.blue(): QuantumType = MagickColor_Blue_Get(this)
        inline fun CPointer<PixelInfo>.blue(value: QuantumType) = MagickColor_Blue_Set(this, value)

        inline fun CPointer<PixelInfo>.alpha(): QuantumType = MagickColor_Alpha_Get(this)
        inline fun CPointer<PixelInfo>.alpha(value: QuantumType) = MagickColor_Alpha_Set(this, value)

        inline fun CPointer<PixelInfo>.black(): QuantumType = MagickColor_Black_Get(this)
        inline fun CPointer<PixelInfo>.black(value: QuantumType) = MagickColor_Black_Set(this, value)

        inline fun CPointer<PixelInfo>.isCMYK(): Boolean = MagickColor_IsCMYK_Get(this).toPrimitive()
        inline fun CPointer<PixelInfo>.isCMYK(value: Boolean) = MagickColor_IsCMYK_Set(this, value.toNative())

        inline fun CPointer<PixelInfo>.fuzzyEquals(other: CPointer<PixelInfo>, fuzz: QuantumType): Boolean =
            MagickColor_FuzzyEquals(this, other, fuzz)
                .toPrimitive()

        inline fun CPointer<PixelInfo>.initialize(value: String): Boolean =
            MagickColor_Initialize(this, value).toPrimitive()
    }
}
