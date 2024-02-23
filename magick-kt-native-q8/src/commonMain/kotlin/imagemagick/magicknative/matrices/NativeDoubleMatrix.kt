@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative.matrices

import imagemagick.bridge.dispose
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import libMagickNative.DoubleMatrix_Create
import libMagickNative.KernelInfo

@ExperimentalStdlibApi
@ExperimentalForeignApi
public class NativeDoubleMatrix(public val ptr: CPointer<KernelInfo>) : AutoCloseable {
    override fun close(): Unit = ptr.dispose()

    public companion object {
        @Throws(IllegalStateException::class)
        public inline fun create(order: ULong, values: DoubleArray): NativeDoubleMatrix =
            values.usePinned { valuesFixed ->
                DoubleMatrix_Create(valuesFixed.addressOf(0), order)
            }?.let {
                NativeDoubleMatrix(it)
            } ?: error("Failed to instantiate native DoubleMatrix")
    }
}
