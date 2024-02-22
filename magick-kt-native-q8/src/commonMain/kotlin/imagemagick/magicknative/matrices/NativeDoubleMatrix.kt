package imagemagick.magicknative.matrices

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import libMagickNative.DoubleMatrix_Create
import libMagickNative.DoubleMatrix_Dispose
import libMagickNative.KernelInfo

@ExperimentalForeignApi
@ExperimentalStdlibApi
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

        public inline fun CPointer<KernelInfo>.dispose(): Unit = DoubleMatrix_Dispose(this)
    }
}
