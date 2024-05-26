@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative.settings

import imagemagick.bridge.dispose
import imagemagick.bridge.setColorSpace
import imagemagick.bridge.setColors
import imagemagick.bridge.setDitherMethod
import imagemagick.bridge.setMeasureError
import imagemagick.bridge.setTreeDepth
import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.DitherMethod
import imagemagick.magicknative.NativeInstance
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import libMagickNative.QuantizeInfo
import libMagickNative.QuantizeSettings_Create

@ExperimentalStdlibApi
@ExperimentalForeignApi
public class NativeQuantizeSettings : NativeInstance<QuantizeInfo>(), AutoCloseable {
    public override var ptr: CPointer<QuantizeInfo> = create()

    override fun close(): Unit = ptr.dispose()

    public fun setColors(value: UInt): Unit = ptr.setColors(value.convert())

    public fun setColorSpace(value: ColorSpace): Unit = ptr.setColorSpace(value)

    public fun setDitherMethod(value: DitherMethod): Unit = ptr.setDitherMethod(value)

    public fun setMeasureError(value: Boolean): Unit = ptr.setMeasureError(value)

    public fun setTreeDepth(value: UInt): Unit = ptr.setTreeDepth(value.convert())

    public companion object {
        @Throws(IllegalStateException::class)
        public inline fun create(): CPointer<QuantizeInfo> =
            QuantizeSettings_Create() ?: error("Failed to instantiate native DrawingSettings")
    }
}
