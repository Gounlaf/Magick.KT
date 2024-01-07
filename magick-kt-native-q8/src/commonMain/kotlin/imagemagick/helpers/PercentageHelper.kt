package imagemagick.helpers

import imagemagick.Quantum
import imagemagick.QuantumType
import imagemagick.core.types.Percentage
import kotlinx.cinterop.ExperimentalForeignApi

@ExperimentalForeignApi
object PercentageHelper {
    // TODO Check this nightmare
    fun fromQuantum(value: Double): Percentage = Percentage(value.div(Quantum.max.toDouble()).times(100))

    // TODO Check this nightmare
    fun toQuantum(value: Percentage): Double = Quantum.max.toDouble().times(value.toDouble().div(100))

    // TODO Check this nightmare
    fun toQuantumType(value: Percentage): QuantumType =
        Quantum.max.toDouble().times(value.toDouble().div(100)).toUInt().toUByte()
}
