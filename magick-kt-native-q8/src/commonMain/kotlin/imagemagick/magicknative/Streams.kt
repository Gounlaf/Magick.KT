package imagemagick.magicknative

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.UByteVar
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.convert
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.usePinned
import kotlinx.io.Sink
import kotlinx.io.Source
import libMagickNative.CustomStreamHandler
import libMagickNative.CustomStreamSeeker
import libMagickNative.CustomStreamTeller
import libMagickNative.MagickOffsetType
import platform.posix.memcpy
import platform.posix.size_t
import platform.posix.ssize_t

@ExperimentalForeignApi
public object Registry {
    private val wrappers = mutableMapOf<Uuid, Wrapper>()
    private val zero = 0.convert<size_t>()

    public fun register(wrapper: Wrapper): Wrap {
        val uuid = uuid4()

        wrappers[uuid] = wrapper

        return Wrap(
            StableRef.create(uuid),
            if (wrapper.canRead) staticCFunction(Registry::read) else null,
            if (wrapper.canTell) staticCFunction(Registry::tell) else null,
            if (wrapper.canWrite) staticCFunction(Registry::seek) else null,
            if (wrapper.canWrite) staticCFunction(Registry::write) else null,
        )
    }

    private fun read(
        data: CPointer<UByteVar>?,
        length: size_t,
        refPtr: COpaquePointer?,
    ): ssize_t =
        when {
            refPtr == null -> 0
            data == null -> 0
            length == zero -> 0
            else -> wrappers[refPtr.asStableRef<Uuid>().get()]?.read(data, length) ?: 0
        }

    private fun write(
        data: CPointer<UByteVar>?,
        length: size_t,
        refPtr: COpaquePointer?,
    ): ssize_t =
        when {
            refPtr == null -> 0
            data == null -> 0
            length == zero -> 0
            else -> wrappers[refPtr.asStableRef<Uuid>().get()]?.write(data, length) ?: 0
        }

    private fun tell(refPtr: COpaquePointer?): MagickOffsetType =
        when (refPtr) {
            null -> 0
            else -> wrappers[refPtr.asStableRef<Uuid>().get()]?.tell() ?: 0
        }

    private fun seek(
        offset: MagickOffsetType,
        whence: Int,
        refPtr: COpaquePointer?,
    ): ssize_t =
        when (refPtr) {
            null -> 0
            else -> wrappers[refPtr.asStableRef<Uuid>().get()]?.seek(offset, whence) ?: 0
        }

    public fun unregister(wrap: Wrap): Unit =
        wrap.ref.let {
            wrappers.remove(it.get())
            it.dispose()
        }

    public data class Wrap(
        val ref: StableRef<Uuid>,
        val reader: CustomStreamHandler?,
        val teller: CustomStreamTeller?,
        val seeker: CustomStreamSeeker?,
        val writer: CustomStreamHandler?,
    ) : AutoCloseable {
        override fun close() {
            ref.dispose()
        }
    }

    @ExperimentalForeignApi
    public interface Wrapper {
        public val uuid: Uuid

        public val canRead: Boolean
        public val canWrite: Boolean
        public val canTell: Boolean
        public val canSeek: Boolean

        public fun read(
            data: CPointer<UByteVar>,
            length: size_t,
        ): ssize_t

        public fun seek(
            offset: MagickOffsetType,
            whence: Int,
        ): ssize_t

        public fun tell(): MagickOffsetType

        public fun write(
            data: CPointer<UByteVar>,
            length: size_t,
        ): ssize_t
    }
}

@ExperimentalForeignApi
@Deprecated("to be replaced asap")
public object Streams {
    private val bufferSize = (8 * 1024).convert<size_t>()
    private val sources = mutableMapOf<Uuid, Source>()
    private val sinks = mutableMapOf<Uuid, Sink>()

    public fun wrap(stream: Source): StableRef<Uuid> {
        val uuid = uuid4()
        val ref = StableRef.create(uuid)

        sources[uuid] = stream

        return ref
    }

    public fun wrap(stream: Sink): StableRef<Uuid> {
        val uuid = uuid4()
        val ref = StableRef.create(uuid)

        sinks[uuid] = stream

        return ref
    }

    public fun read(
        data: CPointer<UByteVar>,
        count: size_t,
        userData: COpaquePointer,
    ): Long {
        val zeroSizeT = 0.convert<size_t>()

        if (count == zeroSizeT) {
            return 0
        }

        val stream = sources[userData.asStableRef<Uuid>().get()]
        if (null == stream) {
            return 0
        }

        var total = count
        var bytesRead = 0L

        val sink = ByteArray(bufferSize.toInt())
        var destination = data

        while (total > zeroSizeT) {
            var length = minOf(total, bufferSize).toInt()

            length =
                try {
                    stream.readAtMostTo(sink, startIndex = 0, endIndex = length)
                } catch (e: Throwable) {
                    bytesRead = -1
                    break
                }

            if (length <= 0) {
                break
            }

            bytesRead += length

            val lengthSizeT = length.convert<size_t>()

            destination = readBuffer(destination, sink.toUByteArray(), lengthSizeT)

            total -= lengthSizeT
        }

        return bytesRead
    }

    private fun readBuffer(
        destination: CPointer<UByteVar>,
        sink: UByteArray,
        length: size_t,
    ): CPointer<UByteVar> {
        var newDestination = destination
        sink.usePinned {
            memcpy(destination, it.addressOf(0), length)?.let { dstUpdated ->
                newDestination = dstUpdated.reinterpret()
            }
        }
        return newDestination
    }

    public fun write(
        data: CPointer<UByteVar>,
        count: size_t,
        userData: COpaquePointer,
    ): Long {
        val zeroSizeT = 0.convert<size_t>()

        if (count == zeroSizeT) {
            return 0
        }

        val stream = sinks[userData.asStableRef<Uuid>().get()]
        if (null == stream) {
            return 0
        }

        return 0
    }
}
