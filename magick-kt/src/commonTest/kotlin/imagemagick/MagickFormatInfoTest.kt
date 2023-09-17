package imagemagick

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class MagickFormatInfoTest : ShouldSpec({
    context("with ByteArray") {
        should("throw exception when array is empty") {
            checkGccUsage {
                shouldThrow<IllegalArgumentException> {
                    MagickFormatInfo.create(byteArrayOf())
                }
            }
        }

        should("return null when format cannot be determined") {
            checkGccUsage {
                val result = MagickFormatInfo.create(byteArrayOf(42))

                result shouldBe null
            }
        }

        should("return the correct info for the jpg format") {
            checkGccUsage {

            }
        }
    }
})
