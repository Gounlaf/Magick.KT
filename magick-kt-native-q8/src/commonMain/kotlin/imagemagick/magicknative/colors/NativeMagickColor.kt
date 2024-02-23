@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative.colors

import imagemagick.QuantumType
import imagemagick.bridge.alpha
import imagemagick.bridge.black
import imagemagick.bridge.blue
import imagemagick.bridge.count
import imagemagick.bridge.dispose
import imagemagick.bridge.fuzzyEquals
import imagemagick.bridge.green
import imagemagick.bridge.initialize
import imagemagick.bridge.isCMYK
import imagemagick.bridge.red
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.MagickColor_Create
import libMagickNative.PixelInfo

@ExperimentalStdlibApi
@ExperimentalForeignApi
public class NativeMagickColor(public val ptr: CPointer<PixelInfo> = create()) : AutoCloseable {
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

    public fun fuzzyEquals(other: NativeMagickColor, fuzz: QuantumType): Boolean = ptr.fuzzyEquals(other.ptr, fuzz)

    public fun initialize(value: String): Boolean = ptr.initialize(value)

    public companion object {
        @ExperimentalForeignApi
        @Throws(IllegalStateException::class)
        public inline fun create(): CPointer<PixelInfo> =
            MagickColor_Create() ?: error("Failed to instantiate native MagickColor")
    }
}
