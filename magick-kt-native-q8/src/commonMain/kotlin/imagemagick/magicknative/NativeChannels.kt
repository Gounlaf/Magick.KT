package imagemagick.magicknative

import imagemagick.core.enums.Channels
import kotlin.experimental.ExperimentalNativeApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import platform.posix.size_t

@ExperimentalNativeApi
@ExperimentalForeignApi
public class NativeChannels private constructor(channels: Channels) {
    public val value: size_t = channels.bit

    public companion object {
        public inline fun Channels.toNative(): NativeChannels = fromMagick(this)

        public fun fromMagick(channels: Channels): NativeChannels = NativeChannels(channels)

        public fun convertValue(channels: Channels): size_t {
            if (Platform.cpuArchitecture.bitness == 64) {
                return channels.bit.convert()
            }

            if (channels == Channels.ALL) {
                return 0b0111111111111111111111111111u
            }

            if (channels.bit > 0b1111111111111111111111111111u) throw IllegalArgumentException(
                "There is no support for setting more than 32 bits of the Channels on a 32-bit platform",
            )

            return channels.bit.convert()
        }
    }
}
