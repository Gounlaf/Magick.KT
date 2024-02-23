@file:Suppress("KDocMissingDocumentation")

package imagemagick.bridge

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.DoubleMatrix_Dispose
import libMagickNative.KernelInfo

@ExperimentalForeignApi
public inline fun CPointer<KernelInfo>.dispose(): Unit = DoubleMatrix_Dispose(this)
