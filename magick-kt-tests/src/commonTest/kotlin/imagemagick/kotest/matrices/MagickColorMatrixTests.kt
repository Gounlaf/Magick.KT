package imagemagick.kotest.matrices

import imagemagick.matrices.MagickColorMatrix
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class MagickColorMatrixTests : ShouldSpec({
    context("MagickColorMatrixTests") {
        context("TheConstructor") {
            should("throw exception when order is too low") {
                shouldThrow<IllegalArgumentException> {
                    MagickColorMatrix(0u)
                }
            }

            should("throw exception when order is too high") {
                shouldThrow<IllegalArgumentException> {
                    MagickColorMatrix(7u)
                }
            }

            should("throw exception when not enough values are provided") {
                shouldThrow<IllegalArgumentException> {
                    MagickColorMatrix(2u, 1.0)
                }
            }

            should("set the properties") {
                val matrix = MagickColorMatrix(2u, 0.0, 1.0, 0.1, 1.1)

                matrix.order shouldBe 2u
                matrix[0u, 0u] shouldBe 0.0
                matrix[1u, 0u] shouldBe 1.0
                matrix[0u, 1u] shouldBe 0.1
                matrix[1u, 1u] shouldBe 1.1
            }

            should("throw exception when order is too low and values are provided") {
                shouldThrow<IllegalArgumentException> {
                    MagickColorMatrix(0u, 1.0)
                }
            }

            should("throw exception when order is too high and values are provided") {
                shouldThrow<IllegalArgumentException> {
                    val values = DoubleArray(7 * 7) { 1.0 }

                    MagickColorMatrix(7u, *values)
                }
            }
        }

        context("TheGetValueMethod") {
            should("throw exception when x too high") {
                shouldThrow<IndexOutOfBoundsException> {
                    MagickColorMatrix(2u).getValue(2u, 1u)
                }
            }

            should("throw exception when y too high") {
                shouldThrow<IndexOutOfBoundsException> {
                    MagickColorMatrix(2u).getValue(1u, 2u)
                }
            }

            should("return value for valid indexes") {
                MagickColorMatrix(1u, 4.0).getValue(0u, 0u) shouldBe 4.0
            }
        }

        context("TheSetColumnMethod") {
            should("throw exception when x too high") {
                shouldThrow<IndexOutOfBoundsException> {
                    MagickColorMatrix(2u).setColumn(2u, 1.0, 2.0)
                }
            }

            should("throw exception when values is not set") {
                shouldThrow<IllegalArgumentException> {
                    MagickColorMatrix(2u).setColumn(0u)
                }
            }

            should("set column for correct number of values") {
                val matrix = MagickColorMatrix(2u)

                matrix.setColumn(1u, 6.0, 8.0)
                matrix.getValue(0u, 0u) shouldBe 0.0
                matrix.getValue(0u, 1u) shouldBe 0.0
                matrix.getValue(1u, 0u) shouldBe 6.0
                matrix.getValue(1u, 1u) shouldBe 8.0
            }

            should("throw exception for invalid number of values") {
                shouldThrow<IllegalArgumentException> {
                    MagickColorMatrix(2u).setColumn(0u, 1.0, 2.0, 3.0)
                }
            }
        }

        context("TheSetRowMethod") {
            should("throw exception when y too high") {
                shouldThrow<IndexOutOfBoundsException> {
                    MagickColorMatrix(2u).setRow(2u, 1.0, 2.0)
                }
            }

            should("throw exception when values is not set") {
                shouldThrow<IllegalArgumentException> {
                    MagickColorMatrix(2u).setRow(0u)
                }
            }

            should("set column for correct number of values") {
                val matrix = MagickColorMatrix(2u)

                matrix.setRow(1u, 6.0, 8.0)
                matrix.getValue(0u, 0u) shouldBe 0.0
                matrix.getValue(0u, 1u) shouldBe 6.0
                matrix.getValue(1u, 0u) shouldBe 0.0
                matrix.getValue(1u, 1u) shouldBe 8.0
            }

            should("throw exception for invalid number of values") {
                shouldThrow<IllegalArgumentException> {
                    MagickColorMatrix(2u).setRow(0u, 1.0, 2.0, 3.0)
                }
            }
        }

        context("TheSetValueMethod") {
            should("throw exception when x too high") {
                shouldThrow<IndexOutOfBoundsException> {
                    MagickColorMatrix(2u).setValue(2u, 1u, 1.0)
                }
            }

            should("throw exception when y too high") {
                shouldThrow<IndexOutOfBoundsException> {
                    MagickColorMatrix(2u).setValue(1u, 2u, 1.0)
                }
            }

            should("set the values") {
                val matrix = MagickColorMatrix(2u)

                matrix.setValue(1u, 0u, 1.5)

                matrix.getValue(0u, 0u) shouldBe 0.0
                matrix.getValue(0u, 1u) shouldBe 0.0
                matrix.getValue(1u, 0u) shouldBe 1.5
                matrix.getValue(1u, 1u) shouldBe 0.0
            }
        }

        context("TheToArrayMethod") {
            should("return array") {
                val matrix = MagickColorMatrix(1u, 6.0)

                matrix.toArray() shouldBe DoubleArray(1) {
                    6.0
                }
            }
        }
    }
})
