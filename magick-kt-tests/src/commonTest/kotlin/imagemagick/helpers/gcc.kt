package imagemagick.helpers

import io.kotest.core.test.TestScope
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.native.runtime.GC

fun getUsage(): Long {
    GC.collect()
    return GC.lastGCInfo!!.memoryUsageAfter["heap"]!!.totalObjectsSizeBytes
}

inline fun <reified R> TestScope.checkGcUsage(block: () -> R) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }

    checkGcUsage(this) {
        block()
    }
}

inline fun <reified T : TestScope, reified R> checkGcUsage(
    receiver: T,
    block: T.() -> R,
) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }

//    val before = getUsage()
    GC.collect()

    receiver.block()

    GC.collect()

//    val after = getUsage()
//
//    if (after != before) {
//        receiver.warn { "⚠ you may have a memory leak; before: $before - after: $after; ${receiver.testCase.name}" }
//    }
}

inline fun <R> checkGcUsage(block: () -> R) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }

    val before = getUsage()

    block()

    val after = getUsage()

    if (after != before) {
        println("⚠ you may have a memory leak; before: $before - after: $after")
    }
}
