package imagemagick

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.should
import io.kotest.matchers.shouldNotBe


class MagickKTTest : FunSpec({
    test("Features detected correctly") {
        checkGccUsage {
            // TODO define me with options from native build
            MagickKT.features shouldNotBe "Unknown"
        }
    }

    test("Formats supported") {
        checkGccUsage {
            MagickKT.supportedFormats.should {
                it.size shouldBeGreaterThan 0
            }
        }
    }
})
