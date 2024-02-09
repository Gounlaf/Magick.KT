package imagemagick.magicknative.types

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.PrimaryInfo
import libMagickNative.PrimaryInfo_Create
import libMagickNative.PrimaryInfo_Dispose
import libMagickNative.PrimaryInfo_X_Get
import libMagickNative.PrimaryInfo_X_Set
import libMagickNative.PrimaryInfo_Y_Get
import libMagickNative.PrimaryInfo_Y_Set
import libMagickNative.PrimaryInfo_Z_Get
import libMagickNative.PrimaryInfo_Z_Set

@ExperimentalStdlibApi
@ExperimentalForeignApi
public class NativePrimaryInfo : AutoCloseable {
    public val ptr: CPointer<PrimaryInfo>

    public constructor() {
        ptr = create()
    }

    public constructor(instance: CPointer<PrimaryInfo>) {
        ptr = instance
    }

    public var x: Double
        get() = ptr.x()
        set(value) = ptr.x(value)

    public var y: Double
        get() = ptr.y()
        set(value) = ptr.y(value)

    public var z: Double
        get() = ptr.z()
        set(value) = ptr.z(value)

    override fun close() {
        ptr.dispose()
    }

    public companion object {
        @Throws(IllegalStateException::class)
        public inline fun create(): CPointer<PrimaryInfo> = PrimaryInfo_Create() ?: error("Failed to instantiate native PrimaryInfo")

        public inline fun CPointer<PrimaryInfo>.dispose(): Unit = PrimaryInfo_Dispose(this)

        public inline fun CPointer<PrimaryInfo>.x(): Double = PrimaryInfo_X_Get(this)

        public inline fun CPointer<PrimaryInfo>.x(value: Double): Unit = PrimaryInfo_X_Set(this, value)

        public inline fun CPointer<PrimaryInfo>.y(): Double = PrimaryInfo_Y_Get(this)

        public inline fun CPointer<PrimaryInfo>.y(value: Double): Unit = PrimaryInfo_Y_Set(this, value)

        public inline fun CPointer<PrimaryInfo>.z(): Double = PrimaryInfo_Z_Get(this)

        public inline fun CPointer<PrimaryInfo>.z(value: Double): Unit = PrimaryInfo_Z_Set(this, value)
    }
}
