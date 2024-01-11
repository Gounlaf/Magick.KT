package imagemagick.kotest.colors

import imagemagick.colors.ColorMono
import imagemagick.colors.MagickColors
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ColorMonoTests : ShouldSpec({
    context("ColorMonoTests") {
        context("TheCompareToMethod") {
            should("return the correct value when other is null") {
                val color = ColorMono.black

                color.compareTo(null) shouldBe 1
            }

            should("return the correct value when other is equal") {
                val color = ColorMono.black

                color.compareTo(color) shouldBe 0
            }

            should("return the correct value when other is lower") {
                val color = ColorMono.white
                val other = ColorMono.black

                color.compareTo(other) shouldBe 1
            }

            should("return the correct value when other is higher") {
                val color = ColorMono.black
                val other = ColorMono.white

                color.compareTo(other) shouldBe -1
            }
        }

        context("TheEqualsMethod") {
            should("return false when other is null") {
                val color = ColorMono.black

                color.equals(null) shouldBe false
            }

            should("return true when other is equal") {
                val color = ColorMono.white
                val other = ColorMono.white

                color.equals(other) shouldBe true
            }

            should("return true when other as object is equal") {
                val color = ColorMono.white
                val other = ColorMono.white

                color.equals(other as Any) shouldBe true
            }

            should("return false when other is not equal") {
                val color = ColorMono.black
                val other = ColorMono.white

                color.equals(other) shouldBe false
            }

            should("return false when other as object is not equal") {
                val color = ColorMono.black
                val other = ColorMono.white

                color.equals(other as Any) shouldBe false
            }
        }

        context("TheFromMagickColorMethod") {
            should("initialize the properties") {
                val color = MagickColors.Black
                val grayColor = ColorMono.fromMagickColor(color)

                ColorMono.fromMagickColor(color) shouldBe grayColor
            }
        }

        context("TheGetHashCodeMethod") {
            should("return different value when channel changed") {
                val first = ColorMono.black
                val hashCode = first.hashCode()

                first.isBlack = false
                first.hashCode() shouldNotBe hashCode
            }
        }

        context("TheOperators") {
            should("return the correct value when instance is null") {
                val color = ColorMono.white

                (color < null) shouldBe false
                (color <= null) shouldBe false
                (color > null) shouldBe true
                (color >= null) shouldBe true
            }

            should("return the correct value when instances are equal") {
                val first = ColorMono.black
                val second = ColorMono.black

                (first == second) shouldBe true
                (first != second) shouldBe false
                (first < second) shouldBe false
                (first <= second) shouldBe true
                (first > second) shouldBe false
                (first >= second) shouldBe true
            }

            should("return the correct value when instances are not equal") {
                val first = ColorMono.white
                val second = ColorMono.black

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
                val color = ColorMono.white

                color.isBlack = true
                color.isBlack shouldBe true
            }
        }
    }
})
