@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative.types

import imagemagick.bridge.dispose
import imagemagick.bridge.height
import imagemagick.bridge.width
import imagemagick.bridge.x
import imagemagick.bridge.y
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import libMagickNative.MagickRectangle_Create
import libMagickNative.MagickRectangle_FromPageSize
import libMagickNative.RectangleInfo

@ExperimentalForeignApi
public class NativeMagickRectangle(public val ptr: CPointer<RectangleInfo>) : AutoCloseable {
    public constructor() : this(create())

    override fun close() {
        ptr.dispose()
    }

    public var x: Int
        get() = ptr.x().toInt()
        set(value) = ptr.x(value.convert())

    public var y: Int
        get() = ptr.y().toInt()
        set(value) = ptr.y(value.convert())

    public var width: UInt
        get() = ptr.width().toUInt()
        set(value) = ptr.width(value.convert())

    public var height: UInt
        get() = ptr.height().toUInt()
        set(value) = ptr.height(value.convert())

    public companion object {
        @Throws(IllegalStateException::class)
        public inline fun create(): CPointer<RectangleInfo> =
            MagickRectangle_Create() ?: error("Failed to instantiate native MagickRectangle")

        public inline fun fromPageSize(value: String): NativeMagickRectangle? =
            MagickRectangle_FromPageSize(value)?.let {
                NativeMagickRectangle(it)
            }
    }
}
