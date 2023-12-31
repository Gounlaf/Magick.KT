package imagemagick.kotest.types

import imagemagick.core.types.Percentage
import imagemagick.core.types.Percentage.Companion.toPercentage
import imagemagick.helpers.checkGcUsage
import imagemagick.helpers.empty
import imagemagick.types.MagickGeometry
import imagemagick.types.MagickGeometry.Companion.toMagickGeometry
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class MagickGeometryTests : ShouldSpec({
    context("MagickGeometry TheConstructor") {
        should("throw exception when value is empty") {
            checkGcUsage {
                shouldThrow<IllegalArgumentException> {
                    MagickGeometry(String.empty)
                }
            }
        }

        should("set ignore aspect ratio") {
            checkGcUsage {
                val geometry = "5x10!".toMagickGeometry()

                geometry.shouldNotBeNull()

                geometry.x shouldBe 0
                geometry.y shouldBe 0
                geometry.width shouldBe 5u
                geometry.height shouldBe 10u
                geometry.ignoreAspectRatio shouldBe true
            }
        }

        should("set less") {
            checkGcUsage {
                val geometry = MagickGeometry("10x5+2+1<")

                geometry.x shouldBe 2
                geometry.y shouldBe 1
                geometry.width shouldBe 10u
                geometry.height shouldBe 5u
                geometry.less shouldBe true
            }
        }

        should("set greater") {
            checkGcUsage {
                val geometry = MagickGeometry("5x10>")

                geometry.x shouldBe 0
                geometry.y shouldBe 0
                geometry.width shouldBe 5u
                geometry.height shouldBe 10u
                geometry.greater shouldBe true
            }
        }

        should("set fill area") {
            checkGcUsage {
                val geometry = MagickGeometry("10x15^")

                geometry.x shouldBe 0
                geometry.y shouldBe 0
                geometry.width shouldBe 10u
                geometry.height shouldBe 15u
                geometry.fillArea shouldBe true
            }
        }

        should("set limit pixels") {
            checkGcUsage {
                val geometry = MagickGeometry("10@")

                geometry.x shouldBe 0
                geometry.y shouldBe 0
                geometry.width shouldBe 10u
                geometry.height shouldBe 0u
                geometry.limitPixels shouldBe true
            }
        }

        should("set greater and is percentage") {
            checkGcUsage {
                val geometry = MagickGeometry("50%x0>")

                geometry.x shouldBe 0
                geometry.y shouldBe 0
                geometry.width shouldBe 50u
                geometry.height shouldBe 0u
                geometry.isPercentage shouldBe true
                geometry.greater shouldBe true
            }
        }

        should("set aspect ratio") {
            checkGcUsage {
                val geometry = MagickGeometry("3:2")

                geometry.x shouldBe 0
                geometry.y shouldBe 0
                geometry.width shouldBe 3u
                geometry.height shouldBe 2u
                geometry.aspectRatio shouldBe true
            }
        }

        should("set aspect ratio with only x offset") {
            checkGcUsage {
                val geometry = MagickGeometry("4:3+2")

                geometry.x shouldBe 2
                geometry.y shouldBe 0
                geometry.width shouldBe 4u
                geometry.height shouldBe 3u
                geometry.aspectRatio shouldBe true
            }
        }

        should("set aspect ratio with offset") {
            checkGcUsage {
                val geometry = MagickGeometry("4:3+2+1")

                geometry.x shouldBe 2
                geometry.y shouldBe 1
                geometry.width shouldBe 4u
                geometry.height shouldBe 3u
                geometry.aspectRatio shouldBe true
            }
        }

        should("set aspect ratio with negative offset") {
            checkGcUsage {
                val geometry = MagickGeometry("4:3-2+1")

                geometry.x shouldBe -2
                geometry.y shouldBe 1
                geometry.width shouldBe 4u
                geometry.height shouldBe 3u
                geometry.aspectRatio shouldBe true
            }
        }

        should("set width and height when size is supplied") {
            checkGcUsage {
                val geometry = MagickGeometry(5u)

                geometry.x shouldBe 0
                geometry.y shouldBe 0
                geometry.width shouldBe 5u
                geometry.height shouldBe 5u
            }
        }

        should("set width and height") {
            checkGcUsage {
                val geometry = MagickGeometry(5u, 10u)

                geometry.x shouldBe 0
                geometry.y shouldBe 0
                geometry.width shouldBe 5u
                geometry.height shouldBe 10u
            }
        }

        should("set x and y") {
            checkGcUsage {
                val geometry = MagickGeometry(5, 10, 15u, 20u)

                geometry.x shouldBe 5
                geometry.y shouldBe 10
                geometry.width shouldBe 15u
                geometry.height shouldBe 20u
            }
        }

        should("set width and height and is percentage") {
            checkGcUsage {
                val geometry = MagickGeometry(Percentage(50.0), Percentage(10.0))

                geometry.x shouldBe 0
                geometry.y shouldBe 0
                geometry.width shouldBe 50u
                geometry.height shouldBe 10u
                geometry.isPercentage shouldBe true
            }
        }

        should("set x and y and is percentage") {
            checkGcUsage {
                val geometry = MagickGeometry(5, 10, 15.0.toPercentage(), 20.0.toPercentage())

                geometry.x shouldBe 5
                geometry.y shouldBe 10
                geometry.width shouldBe 15u
                geometry.height shouldBe 20u
                geometry.isPercentage shouldBe true
            }
        }
    }

    context("MagickGeometry TheCompareToMethod") {
        should("return zero when instances are the same") {
            checkGcUsage {
                val first = MagickGeometry(10u, 5u)

                first.compareTo(first) shouldBe 0
            }
        }

        should("return one when instances is null") {
            checkGcUsage {
                val first = MagickGeometry(10u, 5u)

                first.compareTo(null) shouldBe 1
            }
        }

        should("return zero when instances are equal") {
            checkGcUsage {
                val first = MagickGeometry(10u, 5u)
                val second = MagickGeometry(10u, 5u)

                first.compareTo(second) shouldBe 0
            }
        }

        should("return one when instances are not equal") {
            checkGcUsage {
                val first = MagickGeometry(10u, 5u)
                val second = MagickGeometry(5u, 5u)

                first.compareTo(second) shouldBe 1
            }
        }
    }

    context("MagickGeometry TheEqualsMethod") {
        should("return false when instance is null") {
            checkGcUsage {
                val geometry = MagickGeometry(10u, 5u)

                (geometry.equals(null)) shouldBe false
            }
        }

        should("return true when instance is the same") {
            checkGcUsage {
                val geometry = MagickGeometry(10u, 5u)

                (geometry == geometry) shouldBe true
            }
        }

        should("return true when object is the same") {
            checkGcUsage {
                val geometry = MagickGeometry(10u, 5u)

                (geometry == (geometry as Any)) shouldBe true
            }
        }

        should("return true when instance is equal") {
            checkGcUsage {
                val first = MagickGeometry(10u, 5u)
                val second = MagickGeometry(10u, 5u)

                (first == second) shouldBe true
            }
        }

        should("return true when object is equal") {
            checkGcUsage {
                val first = MagickGeometry(10u, 5u)
                val second = MagickGeometry(10u, 5u)

                (first == (second as Any)) shouldBe true
            }
        }

        should("return false when instance is not equal") {
            checkGcUsage {
                val first = MagickGeometry(10u, 5u)
                val second = MagickGeometry(5u, 10u)

                (first == second) shouldBe false
            }
        }

        should("return false when object is not equal") {
            checkGcUsage {
                val first = MagickGeometry(10u, 5u)
                val second = MagickGeometry(5u, 10u)

                (first == (second as Any)) shouldBe false
            }
        }
    }

    context("MagickGeometry TheFromPageSizeMethod") {
        should("throw exception when page size is empty") {
            checkGcUsage {
                shouldThrow<IllegalArgumentException> {
                    MagickGeometry.fromPageSize(String.empty)
                }
            }
        }

        should("throw exception when page size is invalid") {
            checkGcUsage {
                val exception = shouldThrow<IllegalStateException> {
                    MagickGeometry.fromPageSize("invalid")
                }

                exception.message shouldBe "Invalid page size specified."
            }
        }

        should("return the correct geometry") {
            checkGcUsage {
                val geometry = MagickGeometry.fromPageSize("a4")
                geometry.width shouldBe 595u
                geometry.height shouldBe 842u
                geometry.x shouldBe 0
                geometry.y shouldBe 0
            }
        }

        should("set the x and y position") {
            checkGcUsage {
                val geometry = MagickGeometry.fromPageSize("a4+3+2")
                geometry.width shouldBe 595u
                geometry.height shouldBe 842u
                geometry.x shouldBe 3
                geometry.y shouldBe 2
            }
        }
    }

    context("MagickGeometry TheOperators") {
        should("return the correct value when instance is null") {
            val geometry = MagickGeometry(10u, 5u)

            @Suppress("SENSELESS_COMPARISON")
            (geometry == null) shouldBe false
            @Suppress("SENSELESS_COMPARISON")
            (geometry != null) shouldBe true
            (geometry < null) shouldBe false
            (geometry <= null) shouldBe false
            (geometry > null) shouldBe true
            (geometry >= null) shouldBe true
            @Suppress("SENSELESS_COMPARISON")
            (null == geometry) shouldBe false
            @Suppress("SENSELESS_COMPARISON")
            (null != geometry) shouldBe true
            // the following can't happens in Kotlin
//            (null < geometry) shouldBe true
//            (null <= geometry) shouldBe true
//            (null > geometry) shouldBe false
//            (null >= geometry) shouldBe false
        }

        should("return the correct value when instance is specified") {
            val first = MagickGeometry(10u, 5u)
            val second = MagickGeometry(5u, 5u)

            (first == second) shouldBe false
            (first != second) shouldBe true
            (first < second) shouldBe false
            (first <= second) shouldBe false
            (first > second) shouldBe true
            (first >= second) shouldBe true
        }

        should("return the correct value when instance has same size") {
            val first = MagickGeometry(10u, 5u)
            val second = MagickGeometry(5u, 10u)

            (first == second) shouldBe false
            (first != second) shouldBe true
            (first < second) shouldBe false
            (first <= second) shouldBe true
            (first > second) shouldBe false
            (first >= second) shouldBe true
        }

        should("return the correct value when instance are equal") {
            val first = MagickGeometry(10u, 5u)
            val second = MagickGeometry(10u, 5u)

            (first == second) shouldBe true
            (first != second) shouldBe false
            (first < second) shouldBe false
            (first <= second) shouldBe true
            (first > second) shouldBe false
            (first >= second) shouldBe true
        }
    }

    context("MagickGeometry TheToStringMethod") {
        should("only return width and height") {
            checkGcUsage {
                val geometry = MagickGeometry(10u, 5u)

                geometry.toString() shouldBe "10x5"
            }
        }

        should("return correct value for positive values") {
            checkGcUsage {
                val geometry = MagickGeometry(1, 2, 10u, 20u)

                geometry.toString() shouldBe "10x20+1+2"
            }
        }

        should("return correct value for negative values") {
            checkGcUsage {
                val geometry = MagickGeometry(-1, -2, 20u, 10u)

                geometry.toString() shouldBe "20x10-1-2"
            }
        }

        should("return correct value for ignore aspect ratio") {
            checkGcUsage {
                val geometry = MagickGeometry(5u, 10u).apply {
                    ignoreAspectRatio = true
                }

                geometry.toString() shouldBe "5x10!"
            }
        }

        should("return correct value for less") {
            checkGcUsage {
                val geometry = MagickGeometry(2, 1, 10u, 5u).apply {
                    less = true
                }

                geometry.toString() shouldBe "10x5+2+1<"
            }
        }

        should("return correct value for greater") {
            checkGcUsage {
                val geometry = MagickGeometry(0u, 10u).apply {
                    greater = true
                }

                geometry.toString() shouldBe "x10>"
            }
        }

        should("return correct value for fill area") {
            checkGcUsage {
                val geometry = MagickGeometry(10u, 15u).apply {
                    fillArea = true
                }

                geometry.toString() shouldBe "10x15^"
            }
        }

        should("return correct value for limit pixels") {
            checkGcUsage {
                val geometry = MagickGeometry(10u, 0u).apply {
                    limitPixels = true
                }

                geometry.toString() shouldBe "10x@"
            }
        }

        should("return correct value for aspect ration") {
            checkGcUsage {
                val geometry = MagickGeometry(3u, 2u).apply {
                    aspectRatio = true
                }

                geometry.toString() shouldBe "3:2"
            }
        }

        should("set greater and is percentage") {
            checkGcUsage {
                val geometry = MagickGeometry(Percentage(50), Percentage(0)).apply {
                    greater = true
                }

                geometry.toString() shouldBe "50%>"
            }
        }
    }
})
