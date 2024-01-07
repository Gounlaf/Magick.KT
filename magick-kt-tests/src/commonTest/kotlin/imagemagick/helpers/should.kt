package imagemagick.helpers

import io.kotest.matchers.shouldBe

infix fun <T : Comparable<T>> T.shouldBeIn(range: ClosedRange<T>): T {
    range.contains(this).shouldBe(true)
    return this
}

infix fun <T : Comparable<T>> T.shouldBeIn(range: OpenEndRange<T>): T {
    range.contains(this).shouldBe(true)
    return this
}
