package imagemagick.magicknative

import imagemagick.QuantumType
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.Quantum
import libMagickNative.Quantum_Depth_Get
import libMagickNative.Quantum_Max_Get
import libMagickNative.Quantum_ScaleToByte
import platform.posix.size_t

@ExperimentalForeignApi
public object NativeQuantum {
    public val depth: size_t = Quantum_Depth_Get()
    public val max: Quantum = Quantum_Max_Get()

    public fun scaleToUByte(value: QuantumType): Quantum = Quantum_ScaleToByte(value)
}
