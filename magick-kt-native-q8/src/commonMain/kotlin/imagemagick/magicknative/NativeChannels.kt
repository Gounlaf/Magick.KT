@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative

import imagemagick.core.enums.Channels
import kotlin.experimental.ExperimentalNativeApi

@ExperimentalNativeApi
public object NativeChannels {
    public inline fun Channels.toNative(): ULong = convertValue(this)

    public inline fun convertValue(channels: Channels): ULong {
        if (Platform.cpuArchitecture.bitness == 64) {
            return channels.bit
        }

        if (channels == Channels.ALL) {
            return 0b0111111111111111111111111111u
        }

        if (channels.bit > 0b1111111111111111111111111111u) {
            throw IllegalArgumentException(
                "There is no support for setting more than 32 bits of the Channels on a 32-bit platform",
            )
        }

        return channels.bit
    }
}
