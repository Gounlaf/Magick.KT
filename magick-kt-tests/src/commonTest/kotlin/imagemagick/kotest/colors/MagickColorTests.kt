package imagemagick.kotest.colors

import imagemagick.Quantum
import imagemagick.QuantumType
import imagemagick.asserts.ColorAssert
import imagemagick.colors.MagickColor
import imagemagick.colors.MagickColors
import imagemagick.core.types.Percentage
import imagemagick.core.types.Percentage.Companion.percent
import imagemagick.helpers.empty
import imagemagick.helpers.shouldBeIn
import imagemagick.quantum
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MagickColorTests : ShouldSpec() {
    companion object {
        private fun testColor(
            hexValue: String,
            red: Double,
            green: Double,
            blue: Double,
            isTransparent: Boolean,
        ) = testColor(hexValue, red, green, blue, isTransparent, 0.01)

        private fun testColor(
            hexValue: String,
            red: Double,
            green: Double,
            blue: Double,
            isTransparent: Boolean,
            delta: Double,
        ) {
            val color = MagickColor(hexValue)

            color.r.toDouble() shouldBeIn red - delta..red + delta
            color.g.toDouble() shouldBeIn green - delta..green + delta
            color.b.toDouble() shouldBeIn blue - delta..blue + delta

            if (isTransparent) {
                ColorAssert.transparent(color.a.toFloat())
            } else {
                ColorAssert.notTransparent(color.a.toFloat())
            }
        }
    }

    init {
        context("MagickColorTests") {
            context("TheConstructor") {
                should("throw exception when color is empty") {
                    shouldThrow<IllegalArgumentException> {
                        MagickColor(String.empty)
                    }
                }

                should("throw exception when color does not start with hash") {
                    shouldThrow<IllegalArgumentException> {
                        MagickColor("FFFFFF")
                    }
                }

                should("throw exception when color has invalid length") {
                    shouldThrow<IllegalArgumentException> {
                        MagickColor("#FFFFF")
                    }
                }

                should("throw exception when color has invalid hex value") {
                    shouldThrow<IllegalArgumentException> {
                        MagickColor("#FGF")
                    }

                    shouldThrow<IllegalArgumentException> {
                        MagickColor("#GGFFFF")
                    }

                    shouldThrow<IllegalArgumentException> {
                        MagickColor("#FFFG000000000000")
                    }
                }

                should("initialize the instance correctly") {
                    val max = Quantum.max.toDouble()

                    testColor("#FF", max, max, max, false)
                    testColor("#F00", max, 0.0, 0.0, false)
                    testColor("#0F00", 0.0, max, 0.0, true)
                    testColor("#0000FF", 0.0, 0.0, max, false)
                    testColor("#FF00FF00", max, 0.0, max, true)

                    testColor("#0000FFFF0000", 0.0, max, 0.0, false)
                    testColor("#000080000000", 0.0, (max / 2.0) + 0.5, 0.0, false)
                    testColor("#FFFf000000000000", max, 0.0, 0.0, true)

                    val half = max * 0.5f
                    testColor("gray(50%) ", half, half, half, false, 1.0)
                    testColor("rgba(100%, 0%, 0%, 0.0)", max, 0.0, 0.0, true)
                }
            }

            context("TheCompareToMethods") {
                should("return zero when values are same") {
                    val first = MagickColors.White

                    first.compareTo(first) shouldBe 0
                }

                should("return one when value is null") {
                    val first = MagickColors.White

                    first.compareTo(null) shouldBe 1
                }

                should("return zero when values are equal") {
                    val first = MagickColors.White
                    val second = MagickColor(MagickColors.White)

                    first.compareTo(second) shouldBe 0
                }

                should("return minus one when value is higher") {
                    val half: QuantumType = (Quantum.max.toDouble() / 2.0).quantum()
                    val first = MagickColor(half, half, half, half, half)

                    var second = MagickColor(half, half, Quantum.max, half, half)
                    first.compareTo(second) shouldBe -1

                    second = MagickColor(half, half, Quantum.max, half, half)
                    first.compareTo(second) shouldBe -1

                    second = MagickColor(half, half, half, Quantum.max, half)
                    first.compareTo(second) shouldBe -1

                    second = MagickColor(half, half, half, half, Quantum.max)
                    first.compareTo(second) shouldBe -1
                }

                should("return one when value is lower") {
                    val half: QuantumType = (Quantum.max.toDouble() / 2.0).quantum()
                    val first = MagickColors.White

                    var second = MagickColor(half, 0.quantum(), half, half, half)
                    first.compareTo(second) shouldBe 1

                    second = MagickColor(half, half, 0.quantum(), half, half)
                    first.compareTo(second) shouldBe 1

                    second = MagickColor(half, half, half, 0.quantum(), half)
                    first.compareTo(second) shouldBe 1

                    second = MagickColor(half, half, half, half, 0.quantum())
                    first.compareTo(second) shouldBe 1
                }
            }

            context("TheFuzzyEqualsMethod") {
                should("return true when values are same") {
                    val first = MagickColors.White

                    first.fuzzyEquals(first, 0.percent()) shouldBe true
                }

                should("return true when values are equal") {
                    val first = MagickColors.White
                    val second = MagickColor(Quantum.max, Quantum.max, Quantum.max)

                    first.fuzzyEquals(second, 0.percent()) shouldBe true
                }

                should("return the correct value") {
                    val first = MagickColor(Quantum.max, Quantum.max, Quantum.max)

                    val half = (Quantum.max.toDouble() / 2.0).quantum()
                    val second = MagickColor(Quantum.max, half, Quantum.max)

                    first.fuzzyEquals(second, 0.percent()) shouldBe false
                    first.fuzzyEquals(second, 20.percent()) shouldBe false
                    first.fuzzyEquals(second, 10.percent()) shouldBe false
                    first.fuzzyEquals(second, 30.percent()) shouldBe true
                }
            }

            context("TheOperators") {
                context("WithCompare") {
                    should("return the correct value when instance is specified") {
                        val first = MagickColors.Red
                        val second = MagickColors.Green

                        (first == second) shouldBe false
                        (first != second) shouldBe true
                        (first < second) shouldBe false
                        (first <= second) shouldBe false
                        (first > second) shouldBe true
                        (first >= second) shouldBe true
                    }

                    should("return the correct value when instance are equal") {
                        val first = MagickColors.Red
                        val second = MagickColor("red")

                        (first == second) shouldBe true
                        (first != second) shouldBe false
                        (first < second) shouldBe false
                        (first <= second) shouldBe true
                        (first > second) shouldBe false
                        (first >= second) shouldBe true
                    }
                }

                context("WithPercentage") {
                    should("not allow value above100 percent") {
                        val color = MagickColors.White
                        val percentage = Percentage(150)

                        val result = color * percentage

                        result shouldNotBe null
                        result.r shouldBe Quantum.max
                        result.g shouldBe Quantum.max
                        result.b shouldBe Quantum.max
                        result.a shouldBe Quantum.max
                    }

                    should("multiply all non alpha channels for rgb color") {
                        val color = MagickColors.White
                        val percentage = Percentage(50)

                        val result = color * percentage

                        result shouldNotBe null
                        result.r shouldBe (Quantum.max.toDouble() / 2).quantum()
                        result.g shouldBe (Quantum.max.toDouble() / 2).quantum()
                        result.b shouldBe (Quantum.max.toDouble() / 2).quantum()
                        result.a shouldBe (Quantum.max).quantum()
                    }

                    should("multiply all non alpha channels for cmyk color") {
                        val color = MagickColor("cmyka(100%,100%,100%,100%)")
                        val percentage = Percentage(50)

                        val result = color * percentage

                        result shouldNotBe null
                        result.r shouldBe (Quantum.max.toDouble() / 2).quantum()
                        result.g shouldBe (Quantum.max.toDouble() / 2).quantum()
                        result.b shouldBe (Quantum.max.toDouble() / 2).quantum()
                        result.k shouldBe (Quantum.max.toDouble() / 2).quantum()
                        result.a shouldBe (Quantum.max).quantum()
                    }
                }
            }

            context("TheToHexStringMethod") {
                should("return the correct string") {
                    val color = MagickColors.PowderBlue
                    color.toHexString() shouldBe "#B0E0E6"
                }

                should("include the alpha channel when not fully opquery") {
                    val color = MagickColor("#b0e0e680")
                    color.toHexString() shouldBe "#B0E0E680"
                }

                should("throw exception for cmyk color") {
                    val color =
                        MagickColor(
                            0.quantum(),
                            Quantum.max,
                            0.quantum(),
                            0.quantum(),
                            Quantum.max,
                        )
                    shouldThrow<UnsupportedOperationException> {
                        color.toHexString()
                    }
                }
            }

            context("TheToShortStringMethod") {
                should("return the correct string") {
                    val color = MagickColor(MagickColors.Red)
//                        #if Q8
                    color.toShortString() shouldBe "#FF0000"
//                        #else
//                        color.ToShortString() shouldBe "#FFFF00000000"
//                        #endif
                }

                should("include the alpha channel when not fully opquery") {
                    val color =
                        MagickColor(MagickColors.Red).apply {
                            a = 0.quantum()
                        }

//                        #if Q8
                    color.toShortString() shouldBe "#FF000000"
//                        #else
//                        color.ToShortString() shouldBe "#FFFF000000000000"
//                        #endif
                }

                should("return the correct string for cmyk color") {
                    val color =
                        MagickColor(
                            0.quantum(),
                            Quantum.max,
                            0.quantum(),
                            0.quantum(),
                            Quantum.max,
                        )
                    color.toShortString() shouldBe "cmyk(0,255,0,0)"
                }
            }

            context("TheToStringMethod") {
                should("return the correct string") {
                    val color = MagickColor(MagickColors.Red)
//                    #if Q8
                    color.toString() shouldBe "#FF0000FF"
//                    #else
//                    color.ToString() shouldBe "#FFFF00000000FFFF"
//                    #endif
                }

                should("return the correct string for cmyk color") {
                    var color =
                        MagickColor(
                            0.quantum(),
                            Quantum.max,
                            0.quantum(),
                            0.quantum(),
                            (Quantum.max.toDouble() / 3.0).quantum(),
                        )
                    color.toString() shouldBe "cmyka(0,255,0,0,0.3333)"

                    color =
                        MagickColor(
                            0.quantum(),
                            Quantum.max,
                            0.quantum(),
                            0.quantum(),
                            Quantum.max,
                        )
                    color.toString() shouldBe "cmyka(0,255,0,0,1.0)"
                }
            }
        }
    }
}
