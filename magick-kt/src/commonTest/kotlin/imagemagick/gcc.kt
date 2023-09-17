package imagemagick

import io.kotest.matchers.shouldBe
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.native.runtime.GC

fun getUsage(): Long {
    GC.collect()
    return GC.lastGCInfo!!.memoryUsageAfter["heap"]!!.totalObjectsSizeBytes
}

inline fun <reified T> checkGccUsage(block: () -> T) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }

    val before = getUsage()

    block()

    val after = getUsage()

    before shouldBe after
}
