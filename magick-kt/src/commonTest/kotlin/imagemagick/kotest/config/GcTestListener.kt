package imagemagick.kotest.config

import imagemagick.helpers.getUsage
import io.kotest.core.extensions.Extension
import io.kotest.core.listeners.AfterEachListener
import io.kotest.core.listeners.BeforeEachListener
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult

class GcTestListener : BeforeEachListener, AfterEachListener, Extension {
    private val memConsumed = mutableMapOf<TestCase, Long>()

    override suspend fun beforeEach(testCase: TestCase) {
        memConsumed[testCase] = getUsage()
    }

    override suspend fun afterEach(testCase: TestCase, result: TestResult) {
        val after = getUsage()
        val before = memConsumed.remove(testCase) ?: 0

        if (after != before) {
            println("⚠ you may have a memory leak for test ${testCase.name} before: $before - after: $after")
        }
    }
}
