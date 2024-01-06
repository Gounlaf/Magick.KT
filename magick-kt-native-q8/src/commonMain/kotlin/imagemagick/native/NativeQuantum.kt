package imagemagick.native

import imagemagick.QuantumType
import kotlinx.cinterop.ExperimentalForeignApi
import libMagickNative.Quantum
import libMagickNative.Quantum_Depth_Get
import libMagickNative.Quantum_Max_Get
import libMagickNative.Quantum_ScaleToByte

@ExperimentalForeignApi
object NativeQuantum {
    val depth = Quantum_Depth_Get()
    val max = Quantum_Max_Get()

    fun scaleToUByte(value: QuantumType): Quantum = Quantum_ScaleToByte(value)
}
