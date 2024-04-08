@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CVariable
import kotlinx.cinterop.ExperimentalForeignApi

@ExperimentalStdlibApi
@ExperimentalForeignApi
public abstract class NativeInstance<T : CVariable> : NativeHelper() {
    public abstract var ptr: CPointer<T>
}
