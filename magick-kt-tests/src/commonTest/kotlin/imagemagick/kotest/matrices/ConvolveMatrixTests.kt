package imagemagick.kotest.matrices

import imagemagick.matrices.ConvolveMatrix
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class ConvolveMatrixTests : ShouldSpec({
    context("ConvolveMatrixTests") {
        context("TheConstructor") {
            should("throw exception when order is too low") {
                shouldThrow<IllegalArgumentException> {
                    ConvolveMatrix(0u)
                }
            }

            should("throw exception when order is not an odd number") {
                shouldThrow<IllegalArgumentException> {
                    ConvolveMatrix(2u)
                }
            }

            should("throw exception when not enough values are provided") {
                shouldThrow<IllegalArgumentException> {
                    ConvolveMatrix(3u, 1.0)
                }
            }

            should("set the properties") {
                val matrix = ConvolveMatrix(3u, 0.0, 1.0, 2.0, 0.1, 1.1, 2.1, 0.2, 1.2, 2.2)

                matrix.order shouldBe 3u
                matrix.getValue(0u, 0u) shouldBe 0.0
                matrix.getValue(1u, 0u) shouldBe 1.0
                matrix.getValue(2u, 0u) shouldBe 2.0
                matrix.getValue(0u, 1u) shouldBe 0.1
                matrix.getValue(1u, 1u) shouldBe 1.1
                matrix.getValue(2u, 1u) shouldBe 2.1
                matrix.getValue(0u, 2u) shouldBe 0.2
                matrix.getValue(1u, 2u) shouldBe 1.2
                matrix.getValue(2u, 2u) shouldBe 2.2
            }

            should("throw exception when order is too low and values are provided") {
                shouldThrow<IllegalArgumentException> {
                    ConvolveMatrix(0u, 1.0)
                }
            }

            should("throw exception when order is not an odd number and values are provided") {
                shouldThrow<IllegalArgumentException> {
                    ConvolveMatrix(2u, 1.0, 2.0, 3.0, 4.0)
                }
            }
        }

        context("TheGetValueMethod") {
            should("throw exception when x too high") {
                shouldThrow<IndexOutOfBoundsException> {
                    ConvolveMatrix(1u).getValue(1u, 0u)
                }
            }

            should("throw exception when y too high") {
                shouldThrow<IndexOutOfBoundsException> {
                    ConvolveMatrix(1u).getValue(0u, 1u)
                }
            }

            should("return value for valid indexes") {
                ConvolveMatrix(1u, 4.0).getValue(0u, 0u) shouldBe 4
            }
        }

        context("TheSetColumnMethod") {
            should("throw exception when x too high") {
                shouldThrow<IllegalArgumentException> {
                    ConvolveMatrix(1u).setColumn(2u, 1.0, 2.0)
                }
            }

            should("throw exception when values is not set") {
                shouldThrow<IllegalArgumentException> {
                    ConvolveMatrix(1u).setColumn(0u)
                }
            }

            should("set column for correct number of values") {
                val matrix = ConvolveMatrix(3u)

                matrix.setColumn(1u, 6.0, 8.0, 10.0)
                matrix.getValue(0u, 0u) shouldBe 0.0
                matrix.getValue(0u, 1u) shouldBe 0.0
                matrix.getValue(0u, 2u) shouldBe 0.0
                matrix.getValue(1u, 0u) shouldBe 6.0
                matrix.getValue(1u, 1u) shouldBe 8.0
                matrix.getValue(1u, 2u) shouldBe 10.0
                matrix.getValue(2u, 0u) shouldBe 0.0
                matrix.getValue(2u, 1u) shouldBe 0.0
                matrix.getValue(2u, 2u) shouldBe 0.0
            }

            should("throw exception for invalid number of values") {
                shouldThrow<IllegalArgumentException> {
                    ConvolveMatrix(1u).setColumn(0u, 1.0, 2.0, 3.0)
                }
            }
        }

        context("TheSetRowMethod") {
            should("throw exception when y too high") {
                shouldThrow<IllegalArgumentException> {
                    ConvolveMatrix(1u).setRow(2u, 1.0, 2.0)
                }
            }

            should("throw exception when values is not set") {
                shouldThrow<IllegalArgumentException> {
                    ConvolveMatrix(1u).setRow(0u)
                }
            }

            should("set column for correct number of values") {
                val matrix = ConvolveMatrix(3u)

                matrix.setRow(1u, 6.0, 8.0, 10.0)
                matrix.getValue(0u, 0u) shouldBe 0.0
                matrix.getValue(0u, 1u) shouldBe 6.0
                matrix.getValue(0u, 2u) shouldBe 0.0
                matrix.getValue(1u, 0u) shouldBe 0.0
                matrix.getValue(1u, 1u) shouldBe 8.0
                matrix.getValue(1u, 2u) shouldBe 0.0
                matrix.getValue(2u, 0u) shouldBe 0.0
                matrix.getValue(2u, 1u) shouldBe 10.0
                matrix.getValue(2u, 2u) shouldBe 0.0
            }

            should("throw exception for invalid number of values") {
                shouldThrow<IllegalArgumentException> {
                    ConvolveMatrix(2u).setRow(0u, 1.0, 2.0, 3.0)
                }
            }
        }

        context("TheSetValueMethod") {
            should("throw exception when x too high") {
                shouldThrow<IndexOutOfBoundsException> {
                    ConvolveMatrix(1u).setValue(1u, 0u, 1.0)
                }
            }

            should("throw exception when y too high") {
                shouldThrow<IndexOutOfBoundsException> {
                    ConvolveMatrix(1u).setValue(0u, 1u, 1.0)
                }
            }

            should("set the values") {
                val matrix = ConvolveMatrix(3u)

                matrix.setValue(1u, 2u, 1.5)

                matrix.getValue(0u, 0u) shouldBe 0.0
                matrix.getValue(0u, 1u) shouldBe 0.0
                matrix.getValue(0u, 2u) shouldBe 0.0
                matrix.getValue(1u, 0u) shouldBe 0.0
                matrix.getValue(1u, 1u) shouldBe 0.0
                matrix.getValue(1u, 2u) shouldBe 1.5
                matrix.getValue(2u, 0u) shouldBe 0.0
                matrix.getValue(2u, 1u) shouldBe 0.0
                matrix.getValue(2u, 2u) shouldBe 0.0
            }
        }

        context("TheToArrayMethod") {
            should("return array") {
                val matrix = ConvolveMatrix(1u, 6.0)

                matrix.toArray() shouldBe DoubleArray(1) {
                    6.0
                }
            }
        }
    }
})
