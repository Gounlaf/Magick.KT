package imagemagick.kotest.types

import imagemagick.core.types.Percentage
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class PercentageTests : ShouldSpec({
    context("Percentage TheConstructor") {
        should("default to zero") {
            val percentage = Percentage()
            percentage.toString() shouldBe "0%"
        }

        should("set value") {
            val percentage = Percentage(50u)
            percentage.toString() shouldBe "50%"
        }

        should("handle value above 100") {
            val percentage = Percentage(200.0)
            percentage.toString() shouldBe "200%"
        }

        should("handle negative value") {
            val percentage = Percentage(-25)
            percentage.toString() shouldBe "-25%"
        }
    }
})
