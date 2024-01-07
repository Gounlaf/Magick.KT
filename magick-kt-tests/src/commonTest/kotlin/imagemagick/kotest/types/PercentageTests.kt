package imagemagick.kotest.types

import imagemagick.core.types.Percentage
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.equals.shouldNotBeEqual
import io.kotest.matchers.shouldBe

class PercentageTests : ShouldSpec({
    context("Percentage") {
        context("TheConstructor") {
            should("default to zero") {
                val percentage = Percentage()
                percentage.toString() shouldBe "0%"
            }

            should("set value") {
                val percentage = Percentage(50)
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

        context("TheCompareToMethod") {
            should("should return zero when values are same") {
                val first = Percentage(100)

                first.compareTo(first) shouldBe 0
            }

            should("should return zero when values are equal") {
                val first = Percentage(100)
                val second = Percentage(100)

                first.compareTo(second) shouldBe 0
            }

            should("should return minus one when value is higher") {
                val first = Percentage(100)
                val second = Percentage(101)

                first.compareTo(second) shouldBe -1
            }

            should("should return one when value is lower") {
                val first = Percentage(100)
                val second = Percentage(50)

                first.compareTo(second) shouldBe 1
            }
        }

        context("TheEqualsMethod") {
            should("should return true when instance is equal") {
                val first = Percentage(50.0)
                val second = Percentage(50.0)

                first shouldBeEqual second
            }

            should("should return true when instance is equal (whole number)") {
                val first = Percentage(50)
                val second = Percentage(50.0)

                first shouldBeEqual second
            }

            should("should return false when instance is not equal") {
                val first = Percentage(50.0)
                val second = Percentage(50.1)

                first shouldNotBeEqual second
            }
        }

        context("TheOperators") {
            should("should return correct value when values are equal") {
                val first = Percentage(100)
                val second = Percentage(100)

                (first == second) shouldBe true
                (first != second) shouldBe false
                (first < second) shouldBe false
                (first <= second) shouldBe true
                (first > second) shouldBe false
                (first >= second) shouldBe true
            }

            should("should return correct value when values first is higher") {
                val first = Percentage(100)
                val second = Percentage(101)

                (first == second) shouldBe false
                (first != second) shouldBe true
                (first < second) shouldBe true
                (first <= second) shouldBe true
                (first > second) shouldBe false
                (first >= second) shouldBe false
            }

            should("should return correct value when values first is lower") {
                val first = Percentage(100)
                val second = Percentage(50)

                (first == second) shouldBe false
                (first != second) shouldBe true
                (first < second) shouldBe false
                (first <= second) shouldBe false
                (first > second) shouldBe true
                (first >= second) shouldBe true
            }

            should("should return correct value when multiplying") {
                Percentage().let {
                    (it * 10) shouldBe 0
                }

                Percentage(50).let {
                    (it * 10) shouldBe 5
                }

                Percentage(200).let {
                    (it * 10.0) shouldBe 20.0
                }

                Percentage(25).let {
                    (it * 10.0) shouldBe 2.5
                }
            }
        }

        context("TheToIntMethod") {
            should("should return zero for zero point four") {
                val percentage = Percentage(0.4)
                percentage.toInt() shouldBe 0
            }

            should("should return one for zero point five") {
                val percentage = Percentage(0.5)
                percentage.toInt() shouldBe 1
            }
        }
    }
})
