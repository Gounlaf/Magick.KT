package imagemagick.types

import imagemagick.magicknative.types.NativePrimaryInfo
import kotlinx.cinterop.ExperimentalForeignApi
import imagemagick.core.types.PrimaryInfo as IPrimaryInfo

@ExperimentalForeignApi
@ExperimentalStdlibApi
public data class PrimaryInfo(override val x: Double, override val y: Double, override val z: Double) : IPrimaryInfo {
    public constructor(native: NativePrimaryInfo) : this(native.x, native.y, native.z)

    public companion object {
        public inline fun IPrimaryInfo.toNative(): NativePrimaryInfo = createNativeInstance(this)

        public inline fun NativePrimaryInfo.toMagick(): IPrimaryInfo = PrimaryInfo(this)

        public inline fun createNativeInstance(value: IPrimaryInfo): NativePrimaryInfo =
            NativePrimaryInfo().apply {
                x = value.x
                y = value.y
                z = value.z
            }
    }
}
