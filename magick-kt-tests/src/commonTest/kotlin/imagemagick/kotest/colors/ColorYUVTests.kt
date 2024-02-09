package imagemagick.kotest.colors

import imagemagick.Quantum
import imagemagick.colors.ColorYUV
import imagemagick.colors.MagickColor
import imagemagick.helpers.shouldBeIn
import imagemagick.quantum
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ColorYUVTests : ShouldSpec({
    context("ColorYUVTests") {
        context("TheCompareToMethods") {
            should("return the correct value when other is null") {
                val color = ColorYUV(1.0, 2.0, 3.0)

                color.compareTo(null) shouldBe 1
            }

            should("return the correct value when other is equal") {
                val color = ColorYUV(1.0, 2.0, 3.0)

                color.compareTo(color) shouldBe 0
            }

            should("return the correct value when other is lower") {
                val color = ColorYUV(1.0, 1.0, 1.0)
                val other = ColorYUV(0.5, 0.5, 0.5)

                color.compareTo(other) shouldBe 1
            }

            should("return the correct value when other is higher") {
                val color = ColorYUV(0.5, 0.5, 0.5)
                val other = ColorYUV(1.0, 1.0, 1.0)

                color.compareTo(other) shouldBe -1
            }
        }

        context("TheEqualsMethods") {
            should("return false when other is null") {
                val color = ColorYUV(1.0, 1.0, 1.0)

                color.equals(null) shouldBe false
            }

            should("return true when other is equal") {
                val color = ColorYUV(1.0, 1.0, 1.0)
                val other = ColorYUV(1.0, 1.0, 1.0)

                (color == other) shouldBe true
            }

            should("return true when other as object is equal") {
                val color = ColorYUV(1.0, 1.0, 1.0)
                val other = ColorYUV(1.0, 1.0, 1.0)

                (color == (other as Any)) shouldBe true
            }

            should("return false when other is not equal") {
                val color = ColorYUV(1.0, 1.0, 1.0)
                val other = ColorYUV(0.5, 0.5, 0.5)

                (color == other) shouldBe false
            }

            should("return false when other as object is not equal") {
                val color = ColorYUV(1.0, 1.0, 1.0)
                val other = ColorYUV(0.5, 0.5, 0.5)

                (color == (other as Any)) shouldBe false
            }
        }

        context("TheFromMagickColorMethod") {
            should("initialize the properties") {
                val color =
                    MagickColor(
                        Quantum.max,
                        Quantum.max,
                        (Quantum.maxd * 0.02).quantum(),
                    )
                val hslColor = ColorYUV.fromMagickColor(color)

                hslColor.y shouldBeIn 0.88..0.89
                hslColor.u shouldBeIn 0.07..0.08
                hslColor.v shouldBeIn 0.59..0.60
            }
        }

        context("TheGetHashCodeMethod") {
            should("return different value when channel changed") {
                val first = ColorYUV(0.0, 0.0, 0.0)
                val hashCode = first.hashCode()

                first.apply {
                    y = 1.0
                    u = 1.0
                    v = 1.0
                }

                first.hashCode() shouldNotBe hashCode
            }
        }

        context("TheOperators") {
            should("return the correct value when instance is null") {
                val color = ColorYUV(0.0, 0.0, 0.0)

                (color < null)shouldBe false
                (color <= null) shouldBe false
                (color > null)shouldBe true
                (color >= null) shouldBe true
            }

            should("return the correct value when instances are equal") {
                val first = ColorYUV(0.0, 0.0, 0.0)
                val second = ColorYUV(0.0, 0.0, 0.0)

                (first == second) shouldBe true
                (first != second) shouldBe false
                (first < second)shouldBe false
                (first <= second) shouldBe true
                (first > second)shouldBe false
                (first >= second) shouldBe true
            }

            should("return the correct value when instances are not equal") {
                val first = ColorYUV(1.0, 0.5, 0.5)
                val second = ColorYUV(0.5, 0.5, 0.5)

                (first == second) shouldBe false
                (first != second) shouldBe true
                (first < second)shouldBe false
                (first <= second) shouldBe false
                (first > second)shouldBe true
                (first >= second) shouldBe true
            }
        }

        context("TheProperties") {
            should("set the correct value") {
                val color = ColorYUV(0.0, 0.0, 0.0)

                color.y = 1.0
                color.y shouldBe 1.0
                color.u shouldBe 0.0
                color.v shouldBe 0.0

                color.u = 2.0
                color.y shouldBe 1.0
                color.u shouldBe 2.0
                color.v shouldBe 0.0

                color.v = 3.0
                color.y shouldBe 1.0
                color.u shouldBe 2.0
                color.v shouldBe 3.0
            }
        }
    }
})
