package imagemagick.helpers

import imagemagick.Quantum
import imagemagick.QuantumType
import imagemagick.core.types.Percentage
import kotlinx.cinterop.ExperimentalForeignApi

@ExperimentalForeignApi
public object PercentageHelper {
    public val quantumMax: Double = Quantum.max.toDouble()

    // TODO Check this nightmare
    public fun fromQuantum(value: Double): Percentage = Percentage(value.div(quantumMax).times(100))

    // TODO Check this nightmare
    public fun toQuantum(value: Percentage): Double = quantumMax.times(value.toDouble().div(100))

    // TODO Check this nightmare
    public fun toQuantumType(value: Percentage): QuantumType = quantumMax.times(value.toDouble().div(100)).toUInt().toUByte()
}
