@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative.types

import imagemagick.bridge.dispose
import imagemagick.bridge.height
import imagemagick.bridge.initialize
import imagemagick.bridge.width
import imagemagick.bridge.x
import imagemagick.bridge.y
import imagemagick.enums.GeometryFlags
import imagemagick.helpers.BitMask
import imagemagick.helpers.enabledValues
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.GeometryInfo
import libMagickNative.MagickGeometry_Create

@ExperimentalForeignApi
public class NativeMagickGeometry(private val ptr: CPointer<GeometryInfo>) : AutoCloseable {
    public constructor() : this(create())

    override fun close() {
        ptr.dispose()
    }

    public fun initialize(value: String): List<GeometryFlags> = enabledValues<GeometryFlags>(BitMask(ptr.initialize(value).toULong()))

    public val width: Double get() = ptr.width()

    public val height: Double get() = ptr.height()

    public val x: Double get() = ptr.x()

    public val y: Double get() = ptr.y()

    public companion object {
        @Throws(IllegalStateException::class)
        public inline fun create(): CPointer<GeometryInfo> = MagickGeometry_Create() ?: error("Failed to instantiate native MagickGeometry")
    }
}
