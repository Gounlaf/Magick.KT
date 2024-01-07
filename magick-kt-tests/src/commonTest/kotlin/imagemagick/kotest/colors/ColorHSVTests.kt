package imagemagick.kotest.colors

import imagemagick.Quantum
import imagemagick.colors.ColorHSV
import imagemagick.colors.MagickColor
import imagemagick.helpers.shouldBeIn
import imagemagick.quantum
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ColorHSVTests : ShouldSpec({
    context("ColorHSVTests") {
        context("TheCompareToMethod") {
            should("return the correct value when other is null") {
                val color = ColorHSV(1.0, 2.0, 3.0)

                color.compareTo(null) shouldBe 1
            }

            should("return the correct value when other is equal") {
                val color = ColorHSV(1.0, 2.0, 3.0)

                color.compareTo(color) shouldBe 0
            }

            should("return the correct value when other is lower") {
                val color = ColorHSV(1.0, 1.0, 1.0)
                val other = ColorHSV(0.5, 0.5, 0.5)

                color.compareTo(other) shouldBe 1
            }

            should("return the correct value when other is higher") {
                val color = ColorHSV(0.5, 0.5, 0.5)
                val other = ColorHSV(1.0, 1.0, 1.0)

                color.compareTo(other) shouldBe -1
            }
        }

        context("TheEqualsMethod") {
            should("return false when other is null") {
                val color = ColorHSV(1.0, 1.0, 1.0)
                color.equals(null) shouldBe false
            }

            should("return true when other is equal") {
                val color = ColorHSV(1.0, 1.0, 1.0)
                val other = ColorHSV(1.0, 1.0, 1.0)

                color.equals(other) shouldBe true
            }

            should("return true when other as object is equal") {
                val color = ColorHSV(1.0, 1.0, 1.0)
                val other = ColorHSV(1.0, 1.0, 1.0)

                color.equals(other as Any) shouldBe true
            }

            should("return false when other is not equal") {
                val color = ColorHSV(1.0, 1.0, 1.0)
                val other = ColorHSV(0.5, 0.5, 0.5)

                color.equals(other) shouldBe false
            }

            should("return false when other as object is not equal") {
                val color = ColorHSV(1.0, 1.0, 1.0)
                val other = ColorHSV(0.5, 0.5, 0.5)

                color.equals(other as Any) shouldBe false
            }
        }

        context("ThefromMagickColorMethod") {
            should("initialize the properties") {
                val color = MagickColor(Quantum.max, Quantum.max, (Quantum.max.toDouble() * 0.02).quantum())
                val hslColor = ColorHSV.fromMagickColor(color)

                hslColor.hue shouldBeIn 0.16..0.17
                hslColor.saturation shouldBeIn 0.98..0.99
                hslColor.value shouldBeIn 1.0..1.01
            }
        }

        context("TheGetHashCodeMethod") {
            should("return different value when channel changed") {
                val first = ColorHSV(0.0, 0.0, 0.0)
                val hashCode = first.hashCode()

                first.hue = 1.0
                first.saturation = 1.0
                first.value = 1.0
                first.hashCode() shouldNotBe hashCode
            }
        }

        context("TheOperators") {
            should("return the correct value when instance is null") {
                val color = ColorHSV(0.0, 0.0, 0.0)

                (color < null) shouldBe false
                (color <= null) shouldBe false
                (color > null) shouldBe true
                (color >= null) shouldBe true
            }

            should("return the correct value when instances are equal") {
                val first = ColorHSV(0.0, 0.0, 0.0)
                val second = ColorHSV(0.0, 0.0, 0.0)

                (first == second) shouldBe true
                (first != second) shouldBe false
                (first < second) shouldBe false
                (first <= second) shouldBe true
                (first > second) shouldBe false
                (first >= second) shouldBe true
            }

            should("return the correct value when instances are not equal") {
                val first = ColorHSV(1.0, 0.5, 0.5)
                val second = ColorHSV(0.5, 0.5, 0.5)

                (first == second) shouldBe false
                (first != second) shouldBe true
                (first < second) shouldBe false
                (first <= second) shouldBe false
                (first > second) shouldBe true
                (first >= second) shouldBe true
            }

            should("return the correct value when casted from magick color") {
                val actual = ColorHSV.fromMagickColor(MagickColor("#BFFFDFFF9FFFFFFF"))
                actual.hue shouldBeIn 0.24..0.26
                actual.saturation shouldBeIn 0.28..0.29
                actual.value shouldBeIn 0.87..0.88
            }
        }

        context("TheProperties") {
            should("set the correct value") {
                val color = ColorHSV(0.0, 0.0, 0.0)

                color.hue = 1.0
                color.hue shouldBe 1.0
                color.saturation shouldBe 0.0
                color.value shouldBe 0.0

                color.saturation = 2.0
                color.hue shouldBe 1.0
                color.saturation shouldBe 2.0
                color.value shouldBe 0.0

                color.value = 3.0
                color.hue shouldBe 1.0
                color.saturation shouldBe 2.0
                color.value shouldBe 3.0
            }
        }
    }
})
