package imagemagick.kotest.types

import imagemagick.core.types.PointD
import imagemagick.helpers.empty
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class PointDTests : ShouldSpec({
    context("PointDTests") {
        context("TheConstructor") {
            should("throw exception when value is empty") {
                shouldThrow<IllegalArgumentException> {
                    PointD.create(String.empty)
                }
            }

            should("throw exception when value is invalid") {
                shouldThrow<IllegalArgumentException> {
                    PointD.create("1.0x")
                }

                shouldThrow<IllegalArgumentException> {
                    PointD.create("x1.0")
                }

                shouldThrow<IllegalArgumentException> {
                    PointD.create("ax1.0")
                }

                shouldThrow<IllegalArgumentException> {
                    PointD.create("1.0xb")
                }

                shouldThrow<IllegalArgumentException> {
                    PointD.create("1.0x6 magick")
                }
            }

            should("set the x and y to zero by default") {
                val point = PointD()
                point.x shouldBe 0.0
                point.y shouldBe 0.0
            }

            should("set the x and y value") {
                val point = PointD(5, 10)
                point.x shouldBe 5.0
                point.y shouldBe 10.0
            }

            should("use the x value when t value is not set") {
                val point = PointD(5.0)
                point.x shouldBe 5.0
                point.y shouldBe 5.0
            }

            should("set the values from string") {
                val point = PointD.create("1.0x2.5")
                point.x shouldBe 1.0
                point.y shouldBe 2.5
                point.toString() shouldBe "1x2.5"
            }
        }

        context("TheEqualsMethod") {
            should("return false when instance is null") {
                val point = PointD(50.0)

                point.equals(null) shouldBe false
            }

            should("return true when instance is equal") {
                val first = PointD(50.0)
                val second = PointD(50.0)

                (first == second) shouldBe true
            }

            should("return false when instance is not equal") {
                val first = PointD(50)
                val second = PointD(42)

                (first == second) shouldBe false
            }
        }
    }
})
