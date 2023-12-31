package imagemagick.helpers

import imagemagick.QuantumImpl
import imagemagick.QuantumType
import imagemagick.core.types.Percentage
import kotlinx.cinterop.ExperimentalForeignApi

@ExperimentalForeignApi
object PercentageHelper {
    // TODO Check this nightmare
    fun fromQuantum(value: Double): Percentage = Percentage(value.div(QuantumImpl.max.toDouble()).times(100))

    // TODO Check this nightmare
    fun toQuantum(value: Percentage): Double = QuantumImpl.max.toDouble().times(value.toDouble().div(100))

    // TODO Check this nightmare
    fun toQuantumType(value: Percentage): QuantumType =
        QuantumImpl.max.toDouble().times(value.toDouble().div(100)).toFloat().toUInt().toUByte()
}
