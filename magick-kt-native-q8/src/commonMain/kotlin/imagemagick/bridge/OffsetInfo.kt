@file:Suppress("KDocMissingDocumentation")

package imagemagick.bridge

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.OffsetInfo
import libMagickNative.OffsetInfo_Dispose
import libMagickNative.OffsetInfo_SetX
import libMagickNative.OffsetInfo_SetY
import platform.posix.ssize_t

@ExperimentalForeignApi
public inline fun CPointer<OffsetInfo>.dispose(): Unit = OffsetInfo_Dispose(this)

@ExperimentalForeignApi
public inline fun CPointer<OffsetInfo>.x(value: ssize_t): Unit = OffsetInfo_SetX(this@x, value)

@ExperimentalForeignApi
public inline fun CPointer<OffsetInfo>.y(value: ssize_t): Unit = OffsetInfo_SetY(this@y, value)
