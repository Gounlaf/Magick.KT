package imagemagick.kotest.colors

import imagemagick.Quantum
import imagemagick.colors.ColorRGB
import imagemagick.colors.MagickColor
import imagemagick.core.types.Percentage.Companion.percent
import imagemagick.quantum
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ColorRGBTests : ShouldSpec({
    context("ColorRGBTests") {
        context("TheCompareToMethod") {
            should("return the correct value when other is null") {
                val color = ColorRGB(1.quantum(), 2.quantum(), 3.quantum())

                color.compareTo(null) shouldBe 1
            }

            should("return the correct value when other is equal") {
                val color = ColorRGB(1.quantum(), 2.quantum(), 3.quantum())

                color.compareTo(color) shouldBe 0
            }

            should("return the correct value when other is lower") {
                val color = ColorRGB(Quantum.max, 2.quantum(), 3.quantum())
                val other = ColorRGB(1.quantum(), 2.quantum(), 3.quantum())

                color.compareTo(other) shouldBe 1
            }

            should("return the correct value when other is higher") {
                val color = ColorRGB(1.quantum(), 2.quantum(), 3.quantum())
                val other = ColorRGB(Quantum.max, 2.quantum(), 3.quantum())

                color.compareTo(other) shouldBe -1
            }
        }

        context("TheEqualsMethod") {
            should("return false when other is null") {
                val color = ColorRGB(1.quantum(), 2.quantum(), 3.quantum())

                color.equals(null) shouldBe false
            }

            should("return true when other is equal") {
                val color = ColorRGB(1.quantum(), 2.quantum(), 3.quantum())
                val other = ColorRGB(1.quantum(), 2.quantum(), 3.quantum())

                (color == other) shouldBe true
            }

            should("return true when other as object is equal") {
                val color = ColorRGB(1.quantum(), 2.quantum(), 3.quantum())
                val other = ColorRGB(1.quantum(), 2.quantum(), 3.quantum())

                (color == (other as Any)) shouldBe true
            }

            should("return false when other is not equal") {
                val color = ColorRGB(3.quantum(), 2.quantum(), 1.quantum())
                val other = ColorRGB(1.quantum(), 2.quantum(), 3.quantum())

                (color == other) shouldBe false
            }

            should("return false when other as object is not equal") {
                val color = ColorRGB(3.quantum(), 2.quantum(), 1.quantum())
                val other = ColorRGB(1.quantum(), 2.quantum(), 3.quantum())

                (color == (other as Any)) shouldBe false
            }
        }

        context("TheFromMagickColorMethod") {
            should("initialize the properties") {
                val color =
                    MagickColor(
                        Quantum.max,
                        (Quantum.max.toDouble() * 0.75).quantum(),
                        (Quantum.max.toDouble() * 0.5).quantum(),
                    )
                val rgcColor = ColorRGB.fromMagickColor(color)

                rgcColor.r shouldBe Quantum.max
                rgcColor.g shouldBe (Quantum.max.toDouble() * 0.75).quantum()
                rgcColor.b shouldBe (Quantum.max.toDouble() * 0.5).quantum()
            }
        }

        context("TheFuzzyEqualsMethod") {
            should("return false when other is null") {
                val color = ColorRGB(Quantum.max, Quantum.max, Quantum.max)

                color.fuzzyEquals(null, 0.percent()) shouldBe false
            }

            should("return true when other is same") {
                val color = ColorRGB(Quantum.max, Quantum.max, Quantum.max)

                color.fuzzyEquals(color, 0.percent()) shouldBe true
            }

            should("return true when other is equal") {
                val first = ColorRGB(Quantum.max, Quantum.max, Quantum.max)
                val second = ColorRGB(MagickColor(Quantum.max, Quantum.max, Quantum.max))

                first.fuzzyEquals(second, 0.percent()) shouldBe true
            }

            should("use the percentage") {
                val first = ColorRGB(Quantum.max, Quantum.max, Quantum.max)
                val second = ColorRGB(Quantum.max, (Quantum.max.toDouble() / 2.0).quantum(), Quantum.max)

                first.fuzzyEquals(second, 0.percent()) shouldBe false
                first.fuzzyEquals(second, 10.percent()) shouldBe false
                first.fuzzyEquals(second, 20.percent()) shouldBe false
                first.fuzzyEquals(second, 30.percent()) shouldBe true
            }
        }

        context("TheGetHashCodeMethod") {
            should("return different value when channel changed") {
                val first = ColorRGB(0.quantum(), 0.quantum(), 0.quantum())
                val hashCode = first.hashCode()

                first.b = Quantum.max
                first.hashCode() shouldNotBe hashCode
            }
        }

        context("TheOperators") {
            should("return the correct value when instance is null") {
                val color = ColorRGB(0.quantum(), 0.quantum(), 0.quantum())

                (color.equals(null)) shouldBe false
                (color < null) shouldBe false
                (color <= null) shouldBe false
                (color > null) shouldBe true
                (color >= null) shouldBe true
            }

            should("return the correct value when instances are equal") {
                val first = ColorRGB(0.quantum(), 0.quantum(), Quantum.max)
                val second = ColorRGB(0.quantum(), 0.quantum(), Quantum.max)

                (first == second) shouldBe true
                (first != second) shouldBe false
                (first < second) shouldBe false
                (first <= second) shouldBe true
                (first > second) shouldBe false
                (first >= second) shouldBe true
            }

            should("return the correct value when instances are not equal") {
                val first = ColorRGB(Quantum.max, 0.quantum(), 0.quantum())
                val second = ColorRGB(0.quantum(), Quantum.max, 0.quantum())

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
                val color = ColorRGB(0.quantum(), 0.quantum(), 0.quantum())

                color.r = 1.quantum()
                color.r shouldBe 1.quantum()
                color.g shouldBe 0.quantum()
                color.b shouldBe 0.quantum()

                color.g = 2.quantum()
                color.r shouldBe 1.quantum()
                color.g shouldBe 2.quantum()
                color.b shouldBe 0.quantum()

                color.b = 3.quantum()
                color.r shouldBe 1.quantum()
                color.g shouldBe 2.quantum()
                color.b shouldBe 3.quantum()
            }
        }
    }
})
