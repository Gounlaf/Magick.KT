@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative.types

import imagemagick.bridge.dispose
import imagemagick.bridge.x
import imagemagick.bridge.y
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.OffsetInfo
import libMagickNative.OffsetInfo_Create

@ExperimentalStdlibApi
@ExperimentalForeignApi
public class NativeOffsetInfo(public val ptr: CPointer<OffsetInfo>) : AutoCloseable {
    public constructor() : this(create())

    override fun close() {
        ptr.dispose()
    }

    public inline fun x(value: Long): Unit = ptr.x(value)

    public inline fun y(value: Long): Unit = ptr.y(value)

    public companion object {
        @Throws(IllegalStateException::class)
        public inline fun create(): CPointer<OffsetInfo> =
            OffsetInfo_Create() ?: error("Failed to instantiate native OffsetInfo")
    }
}
