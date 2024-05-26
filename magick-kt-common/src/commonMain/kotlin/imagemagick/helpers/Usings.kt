package imagemagick.helpers

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

// Original idea from https://github.com/FelixEngl/KotlinUsings/blob/b8e6ef031feb36785ede76e0fa60a2e7a50d9a46/Usings.kt

/**
 * Executes the given [block] function on the given 2 resources and closes them down correctly afterwards whether an exception
 * is thrown or not.
 *
 * @param block is a function to process the 2 [AutoCloseable] resources.
 * @return the result of the [block] function invoked on the 2 resources.
 */
@ExperimentalContracts
public inline fun <A : AutoCloseable?, B : AutoCloseable?, C> using(
    argA: A,
    argB: B,
    block: (A, B) -> C,
): C {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    var exception: Throwable? = null
    try {
        return block(argA, argB)
    } catch (e: Exception) {
        exception = e
        throw e
    } finally {
        try {
            argA?.close()
        } catch (closeException: Exception) {
            exception?.addSuppressed(closeException)
        }

        try {
            argB?.close()
        } catch (closeException: Exception) {
            exception?.addSuppressed(closeException)
        }
    }
}
