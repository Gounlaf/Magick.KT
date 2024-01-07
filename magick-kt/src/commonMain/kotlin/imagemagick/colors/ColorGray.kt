package imagemagick.colors

import imagemagick.Quantum
import imagemagick.QuantumType
import imagemagick.exceptions.Throw
import kotlinx.cinterop.ExperimentalForeignApi
import imagemagick.core.colors.MagickColorQuantum as IMagickColor

/**
 * Class that represents a gray color.
 */
@ExperimentalForeignApi
@ExperimentalStdlibApi
public class ColorGray : ColorBase {
    /** Gets or sets the shade of this color (value between 0.0 - 1.0). */
    public var shade: Double = 0.0
        set(value) {
            if (value < 0.0 || value > 1.0) {
                return
            }

            field = value
        }

    public constructor(shade: Double) : super(MagickColor(0u, 0u, 0u)) {
        Throw.ifTrue("shade", shade < 0.0 || shade > 1.0, "Invalid shade specified")

        this.shade = shade
    }

    private constructor(color: IMagickColor<QuantumType>) : super(color) {
        shade = (0.212656 * Quantum.scaleToDouble(color.r)) +
            (0.715158 * Quantum.scaleToDouble(color.g)) +
            (0.072186 * Quantum.scaleToDouble(color.b))
    }

    override fun updateColor() {
        Quantum.scaleToQuantum(shade).let {
            color.r = it
            color.g = it
            color.b = it
        }
    }
}
