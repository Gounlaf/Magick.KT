@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CVariable
import kotlinx.cinterop.ExperimentalForeignApi

@ExperimentalStdlibApi
@ExperimentalForeignApi
public abstract class ConstNativeInstance<T : CVariable> : NativeHelper() {
    public abstract var ptr: CPointer<T>

    public abstract val hasInstance: Boolean
}
