package imagemagick.kotest.colors

import imagemagick.Quantum
import imagemagick.colors.ColorHSL
import imagemagick.colors.MagickColor
import imagemagick.helpers.shouldBeIn
import imagemagick.quantum
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ColorHSLTests : ShouldSpec({
    context("ColorHSLTests") {
        context("TheCompareToMethod") {
            should("return the correct value when other is null") {
                val color = ColorHSL(1.0, 2.0, 3.0)

                color.compareTo(null) shouldBe 1
            }

            should("return the correct value when other is equal") {
                val color = ColorHSL(1.0, 2.0, 3.0)

                color.compareTo(color) shouldBe 0
            }

            should("return the correct value when other is lower") {
                val color = ColorHSL(1.0, 1.0, 1.0)
                val other = ColorHSL(0.5, 0.5, 0.5)

                color.compareTo(other) shouldBe 1
            }

            should("return the correct value when other is higher") {
                val color = ColorHSL(0.5, 0.5, 0.5)
                val other = ColorHSL(1.0, 1.0, 1.0)

                color.compareTo(other) shouldBe -1
            }
        }

        context("TheEqualsMethod") {
            should("return false when other is null") {
                val color = ColorHSL(1.0, 2.0, 3.0)

                color.equals(null) shouldBe false
            }

            should("return true when other is equal") {
                val color = ColorHSL(1.0, 2.0, 3.0)
                val other = ColorHSL(1.0, 2.0, 3.0)

                color.equals(other) shouldBe true
            }

            should("return true when other as object is equal") {
                val color = ColorHSL(1.0, 2.0, 3.0)
                val other = ColorHSL(1.0, 2.0, 3.0)

                color.equals(other as Any) shouldBe true
            }

            should("return false when other is not equal") {
                val color = ColorHSL(1.0, 2.0, 3.0)
                val other = ColorHSL(3.0, 2.0, 1.0)

                color.equals(other) shouldBe false
            }

            should("return false when other as object is not equal") {
                val color = ColorHSL(1.0, 2.0, 3.0)
                val other = ColorHSL(3.0, 2.0, 1.0)

                color.equals(other as Any) shouldBe false
            }
        }

        context("TheFromMagickColorMethod") {
            should("initialize the properties") {
                val color = MagickColor(Quantum.max, Quantum.max, (Quantum.max.toDouble() * 0.02).quantum())
                val hslColor = ColorHSL.fromMagickColor(color)

                hslColor.hue shouldBeIn 0.16..0.17
                hslColor.lightness shouldBeIn 0.5..0.6
                hslColor.saturation shouldBeIn 0.99..1.01
            }
        }

        context("TheGetHashCodeMethod") {
            should("return different value when channel changed") {
                val first = ColorHSL(0.0, 0.0, 0.0)
                val hashCode = first.hashCode()

                first.hue = 1.0
                first.saturation = 1.0
                first.lightness = 1.0
                first.hashCode() shouldNotBe hashCode
            }
        }

        context("TheOperators") {
            should("return the correct value when instance is null") {
                val color = ColorHSL(0.0, 0.0, 0.0)

                (color < null) shouldBe false
                (color <= null) shouldBe false
                (color > null) shouldBe true
                (color >= null) shouldBe true
            }

            should("return the correct value when instances are equal") {
                val first = ColorHSL(0.0, 0.0, 0.0)
                val second = ColorHSL(0.0, 0.0, 0.0)

                (first == second) shouldBe true
                (first != second) shouldBe false
                (first < second) shouldBe false
                (first <= second) shouldBe true
                (first > second) shouldBe false
                (first >= second) shouldBe true
            }

            should("return the correct value when instances are not equal") {
                val first = ColorHSL(1.0, 0.5, 0.5)
                val second = ColorHSL(0.5, 0.5, 0.5)

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
                val color = ColorHSL(0.0, 0.0, 0.0)

                color.hue = 1.0
                color.hue shouldBe 1.0
                color.saturation shouldBe 0.0
                color.lightness shouldBe 0.0

                color.saturation = 2.0
                color.hue shouldBe 1.0
                color.saturation shouldBe 2.0
                color.lightness shouldBe 0.0

                color.lightness = 3.0
                color.hue shouldBe 1.0
                color.saturation shouldBe 2.0
                color.lightness shouldBe 3.0
            }
        }
    }
})
