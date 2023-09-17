package imagemagick

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlin.native.runtime.GC

fun getUsage(): Long {
    GC.collect()
    return GC.lastGCInfo!!.memoryUsageAfter["heap"]!!.totalObjectsSizeBytes
}

class MagickKTTest : FunSpec({
    test("Features detected correctly") {
        val before = getUsage()

        // TODO define me with options from native build
        MagickKT.features shouldNotBe "Unknown"

        val after = getUsage()
        before shouldBe after
    }

    test("Formats supported") {
        val before = getUsage()

        MagickKT.supportedFormats.should {
            it.size shouldBeGreaterThan 0
        }

        val after = getUsage()
        before shouldBe after
    }
})
