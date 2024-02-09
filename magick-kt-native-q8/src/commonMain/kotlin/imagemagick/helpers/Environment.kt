package imagemagick.helpers

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toKString
import libMagickNative.Environment_GetEnv
import libMagickNative.Environment_Initialize
import libMagickNative.Environment_SetEnv

@ExperimentalForeignApi
public object Environment {
    init {
        Environment_Initialize()
    }

    public fun initialize() {
        // NO-OP
    }

    public fun getEnv(name: String): String =
        Environment_GetEnv(name)?.toKString() ?: throw RuntimeException("The string value should never be null.")

    public fun setEnv(
        name: String,
        value: String,
    ) = Environment_SetEnv(name, value)
}
