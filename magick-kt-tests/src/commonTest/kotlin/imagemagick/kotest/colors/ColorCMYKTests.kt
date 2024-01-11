package imagemagick.kotest.colors

import imagemagick.Quantum
import imagemagick.colors.ColorCMYK
import imagemagick.colors.MagickColor
import imagemagick.core.types.Percentage.Companion.percent
import imagemagick.helpers.empty
import imagemagick.helpers.shouldBeIn
import imagemagick.quantum
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ColorCMYKTests : ShouldSpec({
    context("ColorCMYKTests") {
        context("TheConstructor") {
            should("throw exception when color is empty") {
                shouldThrow<IllegalArgumentException> {
                    ColorCMYK(String.empty)
                }
            }

            should("throw exception when color does not start with hash") {
                shouldThrow<IllegalArgumentException> {
                    ColorCMYK("FFFFFF")
                }
            }

            should("throw exception when color has invalid length") {
                shouldThrow<IllegalArgumentException> {
                    ColorCMYK("#FFFFF")
                }
            }

            should("throw exception when color has invalid hex value") {
                shouldThrow<IllegalArgumentException> {
                    ColorCMYK("#FGF")
                }

                shouldThrow<IllegalArgumentException> {
                    ColorCMYK("#GGFFFF")
                }

                shouldThrow<IllegalArgumentException> {
                    ColorCMYK("#FFFG000000000000")
                }
            }

            should("convert percentage to color") {
                var color = ColorCMYK(0.percent(), 100.percent(), 0.percent(), 100.percent())
                color.c shouldBe 0.quantum()
                color.m shouldBe Quantum.max
                color.y shouldBe 0.quantum()
                color.k shouldBe Quantum.max
                color.a shouldBe Quantum.max

                color = ColorCMYK(100.percent(), 0.percent(), 100.percent(), 0.percent(), 100.percent())
                color.c shouldBe Quantum.max
                color.m shouldBe 0.quantum()
                color.y shouldBe Quantum.max
                color.k shouldBe 0.quantum()
                color.a shouldBe Quantum.max
            }

            should("convert hex value to color") {
                var color = ColorCMYK("#0ff0")
                color.c shouldBe 0.quantum()
                color.m shouldBe Quantum.max
                color.y shouldBe Quantum.max
                color.k shouldBe 0.quantum()
                color.a shouldBe Quantum.max

                color = ColorCMYK("#ff00ff00")
                color.c shouldBe Quantum.max
                color.m shouldBe 0.quantum()
                color.y shouldBe Quantum.max
                color.k shouldBe 0.quantum()
                color.a shouldBe Quantum.max

                color = ColorCMYK("#0000ffff0000ffff")
                color.c shouldBe 0.quantum()
                color.m shouldBe Quantum.max
                color.y shouldBe 0.quantum()
                color.k shouldBe Quantum.max
                color.a shouldBe Quantum.max
            }
        }

        context("TheCompareToMethod") {
            should("return the correct value when other is null") {
                val color = ColorCMYK(1.quantum(), 2.quantum(), 3.quantum(), 4.quantum())

                color.compareTo(null) shouldBe 1
            }

            should("return the correct value when other is equal") {
                val color = ColorCMYK(1.quantum(), 2.quantum(), 3.quantum(), 4.quantum())

                color.compareTo(color) shouldBe 0
            }

            should("return the correct value when other is lower") {
                val color = ColorCMYK(Quantum.max, 2.quantum(), 3.quantum(), 4.quantum())
                val other = ColorCMYK(1.quantum(), 2.quantum(), 3.quantum(), 4.quantum())

                color.compareTo(other) shouldBe 1
            }

            should("return the correct value when other is higher") {
                val color = ColorCMYK(1.quantum(), 2.quantum(), 3.quantum(), 4.quantum())
                val other = ColorCMYK(Quantum.max, 2.quantum(), 3.quantum(), 4.quantum())

                color.compareTo(other) shouldBe -1
            }
        }

        context("TheEqualsMethod") {
            should("return false when other is null") {
                val color = ColorCMYK(1.quantum(), 2.quantum(), 3.quantum(), 4.quantum())

                color.equals(null) shouldBe false
            }

            should("return true when other is equal") {
                val color = ColorCMYK(1.quantum(), 2.quantum(), 3.quantum(), 4.quantum())
                val other = ColorCMYK(1.quantum(), 2.quantum(), 3.quantum(), 4.quantum())

                color.equals(other) shouldBe true
            }

            should("return true when other as object is equal") {
                val color = ColorCMYK(1.quantum(), 2.quantum(), 3.quantum(), 4.quantum())
                val other = ColorCMYK(1.quantum(), 2.quantum(), 3.quantum(), 4.quantum())

                color.equals(other as Any) shouldBe true
            }

            should("return false when other is not equal") {
                val color = ColorCMYK(4.quantum(), 3.quantum(), 2.quantum(), 1.quantum())
                val other = ColorCMYK(1.quantum(), 2.quantum(), 3.quantum(), 4.quantum())

                color.equals(other) shouldBe false
            }

            should("return false when other as object is not equal") {
                val color = ColorCMYK(4.quantum(), 3.quantum(), 2.quantum(), 1.quantum())
                val other = ColorCMYK(1.quantum(), 2.quantum(), 3.quantum(), 4.quantum())

                color.equals(other as Any) shouldBe false
            }
        }

        context("TheFromMagickColorMethod") {
            should("initialize the properties") {
                val color =
                    MagickColor(
                        Quantum.max,
                        (Quantum.max.toDouble() * 0.75).quantum(),
                        (Quantum.max.toDouble() * 0.5).quantum(),
                        (Quantum.max.toDouble() * 0.25).quantum(),
                    )
                val cmykColor = ColorCMYK.fromMagickColor(color)

                Quantum.scaleToDouble(cmykColor.c) shouldBeIn 0.99..1.0
                Quantum.scaleToDouble(cmykColor.m) shouldBeIn 0.74..0.75
                Quantum.scaleToDouble(cmykColor.y) shouldBeIn 0.49..0.5
                Quantum.scaleToDouble(cmykColor.k) shouldBeIn 0.0..0.01
                Quantum.scaleToDouble(cmykColor.a) shouldBeIn 0.24..0.25
            }
        }

        context("TheGetHashCodeMethod") {
            should("return different value when channel changed") {
                val first = ColorCMYK(0.quantum(), 0.quantum(), 0.quantum(), 0.quantum())
                val hashCode = first.hashCode()

                first.k = Quantum.max
                first.hashCode() shouldNotBe hashCode
            }
        }

        // TODO finish test
//        context("TheNativeInstance") {
//            should("have the correct colorspace") {
//                MagickImage(MagickColors.Black, 1u, 1u).use {
//                    it.colorSpace = ColorSpace.CMYK
//
//                }
// //                using val image = MagickImage(MagickColors.Black, 1, 1)
// //                image.ColorSpace = ColorSpace.CMYK
// //                image.Opaque(MagickColors.Black, MagickColor("cmyk(128,23,250,156)"))
// //
// //                Color0 shouldBe MagickColor("cmyka(128,23,250,156,1.0)"), image, 0
//            }
//        }

        context("TheOperators") {
            should("return the correct value when instance is null") {
                val color = ColorCMYK(0.quantum(), 0.quantum(), 0.quantum(), 0.quantum())

                (color < null) shouldBe false
                (color <= null) shouldBe false
                (color > null) shouldBe true
                (color >= null) shouldBe true
            }

            should("return the correct value when instances are equal") {
                val first = ColorCMYK(0.quantum(), 0.quantum(), Quantum.max, 0.quantum())
                val second = ColorCMYK("#0000ff00")

                (first == second) shouldBe true
                (first != second) shouldBe false
                (first < second) shouldBe false
                (first <= second) shouldBe true
                (first > second) shouldBe false
                (first >= second) shouldBe true
            }

            should("return the correct value when instances are not equal") {
                val first = ColorCMYK(Quantum.max, 0.quantum(), 0.quantum(), 0.quantum())
                val second = ColorCMYK(0.quantum(), Quantum.max, 0.quantum(), 0.quantum())

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
                val color = ColorCMYK(0.quantum(), 0.quantum(), 0.quantum(), 0.quantum())

                color.c = 1.quantum()
                color.c shouldBe 1.quantum()
                color.m shouldBe 0.quantum()
                color.y shouldBe 0.quantum()
                color.k shouldBe 0.quantum()
                color.a shouldBe Quantum.max

                color.m = 2.quantum()
                color.c shouldBe 1.quantum()
                color.m shouldBe 2.quantum()
                color.y shouldBe 0.quantum()
                color.k shouldBe 0.quantum()
                color.a shouldBe Quantum.max

                color.y = 3.quantum()
                color.c shouldBe 1.quantum()
                color.m shouldBe 2.quantum()
                color.y shouldBe 3.quantum()
                color.k shouldBe 0.quantum()
                color.a shouldBe Quantum.max

                color.k = 4.quantum()
                color.c shouldBe 1.quantum()
                color.m shouldBe 2.quantum()
                color.y shouldBe 3.quantum()
                color.k shouldBe 4.quantum()
                color.a shouldBe Quantum.max

                color.a = 5.quantum()
                color.c shouldBe 1.quantum()
                color.m shouldBe 2.quantum()
                color.y shouldBe 3.quantum()
                color.k shouldBe 4.quantum()
                color.a shouldBe 5.quantum()
            }
        }
    }
})
