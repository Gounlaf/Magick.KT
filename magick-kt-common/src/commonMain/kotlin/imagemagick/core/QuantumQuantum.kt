package imagemagick.core

/**
 * Interface that represents the quantum information of ImageMagick.
 */
public interface QuantumQuantum<TQuantumType> : QuantumContract where TQuantumType : Any {
    /**
     * Gets the maximum value of the quantum.
     */
    public val max: TQuantumType
}
