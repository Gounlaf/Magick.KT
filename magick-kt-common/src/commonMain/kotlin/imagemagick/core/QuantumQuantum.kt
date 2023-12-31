package imagemagick.core

/**
 * Interface that represents the quantum information of ImageMagick.
 */
interface QuantumQuantum<TQuantumType> : Quantum where TQuantumType : Any {
    /**
     * Gets the maximum value of the quantum.
     */
    val max: TQuantumType
}
