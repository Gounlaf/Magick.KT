package imagemagick.kotest.types

import imagemagick.core.types.MagickGeometry
import imagemagick.helpers.empty
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe

class MagickGeometryTests : ShouldSpec({
    context("MagickGeometry TheConstructor") {
        should("throw exception when value is empty") {
            shouldThrow<IllegalArgumentException> {
                MagickGeometry(String.empty)
            }
        }

        should("set ignore aspect ratio") {
            val geometry = MagickGeometry("5x10!")

            geometry.x shouldBe 0
            geometry.y shouldBe 0
            geometry.width shouldBe 5u
            geometry.height shouldBe 10u
            geometry.ignoreAspectRatio shouldBe true
        }

        should("set less") {
            val geometry = MagickGeometry("10x5+2+1<")

            geometry.x shouldBe 2
            geometry.y shouldBe 1
            geometry.width shouldBe 10u
            geometry.height shouldBe 5u
            geometry.less shouldBe true
        }

        should("set greater") {
            val geometry = MagickGeometry("5x10>")

            geometry.x shouldBe 0
            geometry.y shouldBe 0
            geometry.width shouldBe 5u
            geometry.height shouldBe 10u
            geometry.greater shouldBe true
        }

        should("set fill area") {
            val geometry = MagickGeometry("10x15^")

            geometry.x shouldBe 0
            geometry.y shouldBe 0
            geometry.width shouldBe 10u
            geometry.height shouldBe 15u
            geometry.fillArea shouldBe true
        }

        should("should set limit pixels") {
            val geometry = MagickGeometry("10@")

            geometry.x shouldBe 0
            geometry.y shouldBe 0
            geometry.width shouldBe 10u
            geometry.height shouldBe 0
            geometry.limitPixels shouldBe true
        }
    }
})
