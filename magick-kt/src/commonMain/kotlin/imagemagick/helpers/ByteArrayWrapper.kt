package imagemagick.helpers

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import imagemagick.magicknative.Registry
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.UByteVar
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.convert
import kotlinx.cinterop.usePinned
import libMagickNative.MagickOffsetType
import platform.posix.memcpy
import platform.posix.size_t
import platform.posix.ssize_t
import kotlin.math.max
import kotlin.math.min

@ExperimentalForeignApi
public class ByteArrayWrapper : Registry.Wrapper {
    override val uuid: Uuid = uuid4()
    override val canRead: Boolean = true
    override val canWrite: Boolean = true
    override val canTell: Boolean = true
    override val canSeek: Boolean = true

    public var bytes: UByteArray = UByteArray(8192)
        get() {
            field = bytes.copyOf(bytesLength)
            return field
        }
        private set

    private var bytesLength: Int = 0
    private var bytesOffset: Int = 0

    override fun read(
        data: CPointer<UByteVar>,
        length: size_t,
    ): ssize_t {
        val len = min(length, (bytesLength - bytesOffset).convert())
        if (len != 0uL) {
            bytes.usePinned {
                memcpy(it.addressOf(bytesOffset), data, len)
            }
            bytesOffset += len.toInt()
        }

        return len.convert()
    }

    override fun seek(
        offset: MagickOffsetType,
        whence: Int,
    ): ssize_t {
        val newOffset =
            when (whence) {
                // SeekOrigin.Begin
                0 -> offset
                // SeekOrigin.Current
                1 -> bytesOffset + offset
                // SeekOrigin.End
                2 -> bytesLength - offset
                else -> -1
            }.toInt()

        if (bytesOffset == newOffset) {
            return bytesOffset.convert()
        }

        if (newOffset < 0) {
            return -1
        }

        bytesOffset = newOffset

        return bytesOffset.convert()
    }

    override fun tell(): MagickOffsetType = bytesOffset.convert()

    override fun write(
        data: CPointer<UByteVar>,
        length: size_t,
    ): ssize_t {
        val newOffset = bytesOffset + length.toInt()

        ensureLength(newOffset)

        bytes.usePinned {
            memcpy(data, it.addressOf(bytesOffset), length)
        }

        bytesOffset = newOffset

        return length.convert()
    }

    private fun ensureLength(length: Int) {
        if (length < bytesLength) {
            return
        }

        bytesLength = length

        if (bytesLength < bytes.size) {
            return
        }

        bytes = bytes.copyOf(max(bytes.size * 2, bytesLength))
    }
}
