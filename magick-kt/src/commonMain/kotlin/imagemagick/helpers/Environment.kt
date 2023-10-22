package imagemagick.helpers

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toKString
import libMagickNative.Environment_GetEnv
import libMagickNative.Environment_Initialize
import libMagickNative.Environment_SetEnv

@ExperimentalForeignApi
internal object Environment {
    init {
        Environment_Initialize()
    }

    fun initialize() {
        // NO-OP
    }

    fun getEnv(name: String): String =
        Environment_GetEnv(name)?.toKString() ?: throw RuntimeException("The string value should never be null.")

    fun setEnv(name: String, value: String) = Environment_SetEnv(name, value)
}
