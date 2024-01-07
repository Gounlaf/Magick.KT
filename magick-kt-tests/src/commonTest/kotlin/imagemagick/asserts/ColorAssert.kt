package imagemagick.asserts

import imagemagick.Quantum
import io.kotest.matchers.shouldBe

object ColorAssert {
    fun transparent(alpha: Float) {
        alpha shouldBe 0.0
    }

    fun notTransparent(alpha: Float) {
        alpha shouldBe Quantum.max.toFloat()
    }
}
