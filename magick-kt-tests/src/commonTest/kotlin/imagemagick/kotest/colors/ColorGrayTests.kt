package imagemagick.kotest.colors

import imagemagick.Quantum
import imagemagick.colors.ColorGray
import imagemagick.colors.MagickColor
import imagemagick.helpers.shouldBeIn
import imagemagick.quantum
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ColorGrayTests : ShouldSpec({
    context("ColorGrayTests") {
        context("TheConstructor") {
            should("throw exception when shade is too low") {
                shouldThrow<IllegalArgumentException> {
                    ColorGray(-0.99)
                }
            }

            should("throw exception when shade is too high") {
                shouldThrow<IllegalArgumentException> {
                    ColorGray(1.01)
                }
            }
        }

        context("TheCompareToMethod") {
            should("return the correct value when other is null") {
                val color = ColorGray(1.0)

                color.compareTo(null) shouldBe 1
            }

            should("return the correct value when other is equal") {
                val color = ColorGray(1.0)

                color.compareTo(color) shouldBe 0
            }

            should("return the correct value when other is lower") {
                val color = ColorGray(1.0)
                val other = ColorGray(0.5)

                color.compareTo(other) shouldBe 1
            }

            should("return the correct value when other is higher") {
                val color = ColorGray(0.25)
                val other = ColorGray(0.5)

                color.compareTo(other) shouldBe -1
            }
        }

        context("TheEqualsMethod") {
            should("return false when other is null") {
                val color = ColorGray(1.0)

                color.equals(null) shouldBe false
            }

            should("return true when other is equal") {
                val color = ColorGray(1.0)
                val other = ColorGray(1.0)

                color.equals(other) shouldBe true
            }

            should("return true when other as object is equal") {
                val color = ColorGray(1.0)
                val other = ColorGray(1.0)

                color.equals(other as Any) shouldBe true
            }

            should("return false when other is not equal") {
                val color = ColorGray(1.0)
                val other = ColorGray(0.5)

                color.equals(other) shouldBe false
            }

            should("return false when other as object is not equal") {
                val color = ColorGray(1.0)
                val other = ColorGray(0.5)

                color.equals(other as Any) shouldBe false
            }
        }

        context("TheFromMagickColorMethod") {
            should("initialize the properties") {
                val color =
                    MagickColor(
                        Quantum.max,
                        (Quantum.max.toDouble() * 0.25).quantum(),
                        (Quantum.max.toDouble() * 0.5).quantum(),
                    )
                val grayColor = ColorGray.fromMagickColor(color)

                grayColor.shade shouldBeIn 0.41..0.43
            }
        }

        context("TheGetHashCodeMethod") {
            should("return different value when channel changed") {
                val first = ColorGray(0.0)
                val hashCode = first.hashCode()

                first.shade = 1.0
                first.hashCode() shouldNotBe hashCode
            }
        }

        context("TheOperators") {
            should("return the correct value when instance is null") {
                val color = ColorGray(0.0)

                (color < null) shouldBe false
                (color <= null) shouldBe false
                (color > null) shouldBe true
                (color >= null) shouldBe true
            }

            should("return the correct value when instances are equal") {
                val first = ColorGray(0.0)
                val second = ColorGray(0.0)

                (first == second) shouldBe true
                (first != second) shouldBe false
                (first < second) shouldBe false
                (first <= second) shouldBe true
                (first > second) shouldBe false
                (first >= second) shouldBe true
            }

            should("return the correct value when instances are not equal") {
                val first = ColorGray(0.5)
                val second = ColorGray(0.0)

                (first == second) shouldBe false
                (first != second) shouldBe true
                (first < second) shouldBe false
                (first <= second) shouldBe false
                (first > second) shouldBe true
                (first >= second) shouldBe true
            }
        }

        context("TheProperties") {
            should("set the correct value") {
                val color = ColorGray(0.0)

                color.shade = 1.0
                color.shade shouldBe 1.0

                color.shade = -0.99
                color.shade shouldBe 1.0

                color.shade = 1.01
                color.shade shouldBe 1.0
            }
        }
    }
})
