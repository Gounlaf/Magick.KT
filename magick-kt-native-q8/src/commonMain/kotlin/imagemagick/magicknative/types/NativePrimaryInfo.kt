@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative.types

import imagemagick.bridge.dispose
import imagemagick.bridge.x
import imagemagick.bridge.y
import imagemagick.bridge.z
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.PrimaryInfo
import libMagickNative.PrimaryInfo_Create

@ExperimentalStdlibApi
@ExperimentalForeignApi
public class NativePrimaryInfo(public val ptr: CPointer<PrimaryInfo>) : AutoCloseable {
    public constructor() : this(create())

    public var x: Double
        get() = ptr.x()
        set(value) = ptr.x(value)

    public var y: Double
        get() = ptr.y()
        set(value) = ptr.y(value)

    public var z: Double
        get() = ptr.z()
        set(value) = ptr.z(value)

    override fun close() {
        ptr.dispose()
    }

    public companion object {
        @Throws(IllegalStateException::class)
        public inline fun create(): CPointer<PrimaryInfo> = PrimaryInfo_Create() ?: error("Failed to instantiate native PrimaryInfo")
    }
}
