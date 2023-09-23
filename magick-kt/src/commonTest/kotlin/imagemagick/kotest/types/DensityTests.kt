package imagemagick.kotest.types

import imagemagick.core.enums.DensityUnit
import imagemagick.core.types.Density
import imagemagick.helpers.empty
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class DensityTests : ShouldSpec({
    context("Density TheConstructor") {
        should("throw exception when value is empty") {
            shouldThrow<IllegalArgumentException> {
                Density.create(String.empty)
            }
        }

        should("throw exception when value is invalid") {
            val values = listOf(
                "1.0x",
                "x1.0",
                "ax1.0",
                "1.0xb",
                "1.0x6 magick",
            )

            forAll(*values.map { row(it) }.toTypedArray()) {
                shouldThrow<IllegalArgumentException> {
                    Density.create(it)
                }
            }
        }

        should("use pixels per inch as the default units") {
            val density = Density(5)

            density.x shouldBe 5.0
            density.y shouldBe 5.0
            density.units shouldBe DensityUnit.PIXELS_PER_INCH
        }

        should("set the units") {
            val density = Density(8.5, DensityUnit.PIXELS_PER_CENTIMETER)

            density.x shouldBe 8.5
            density.y shouldBe 8.5
            density.units shouldBe DensityUnit.PIXELS_PER_CENTIMETER
        }

        should("set the x and y density") {
            val density = Density(2, 3)

            density.x shouldBe 2.0
            density.y shouldBe 3.0
            density.units shouldBe DensityUnit.PIXELS_PER_INCH
        }

        should("set the x and y density and units") {
            val density = Density(2.2, 3.3, DensityUnit.UNDEFINED)

            density.x shouldBe 2.2
            density.y shouldBe 3.3
            density.units shouldBe DensityUnit.UNDEFINED
        }

        should("set the x and y density from the value") {
            val density = Density.create("1.0x2.5")

            density.x shouldBe 1.0
            density.y shouldBe 2.5
            density.units shouldBe DensityUnit.UNDEFINED
            density.toString() shouldBe "1x2.5"
        }

        should("set the correct units for cm") {
            val density = Density.create("2.5x1.0 cm")

            density.x shouldBe 2.5
            density.y shouldBe 1.0
            density.units shouldBe DensityUnit.PIXELS_PER_CENTIMETER
            density.toString() shouldBe "2.5x1 cm"
        }

        should("set the correct units for inch") {
            val density = Density.create("2.5x1.0 inch")

            density.x shouldBe 2.5
            density.y shouldBe 1.0
            density.units shouldBe DensityUnit.PIXELS_PER_INCH
            density.toString() shouldBe "2.5x1 inch"
        }
    }

    context("Density TheToStringMethod") {
        should("return the correct value for pixels per centimeter units") {
            val density = Density(1, 2, DensityUnit.PIXELS_PER_CENTIMETER)

            density.toString() shouldBe "1x2 cm"
        }

        should("return the correct value for pixels per inch units") {
            val density = Density(1, 2, DensityUnit.PIXELS_PER_INCH)

            density.toString() shouldBe "1x2 inch"
        }

        should("return the correct value for undefined units") {
            val density = Density(1, 2, DensityUnit.UNDEFINED)

            density.toString() shouldBe "1x2"
        }

        should("not convert the value when units match") {
            val density = Density(1, 2, DensityUnit.PIXELS_PER_CENTIMETER)

            density.toString(DensityUnit.PIXELS_PER_CENTIMETER) shouldBe "1x2 cm"
        }

        should("not add units when undefined is specified") {
            val density = Density(1, 2, DensityUnit.PIXELS_PER_CENTIMETER)

            density.toString(DensityUnit.UNDEFINED) shouldBe "1x2"
        }

        should("convert pixels per inch to pixels per centimeter") {
            val density = Density(2.54, 5.08, DensityUnit.PIXELS_PER_INCH)

            density.toString(DensityUnit.PIXELS_PER_CENTIMETER) shouldBe "1x2 cm"
        }

        should("convert pixels per centimeter to pixels per inch") {
            val density = Density(1, 2, DensityUnit.PIXELS_PER_CENTIMETER)

            density.toString(DensityUnit.PIXELS_PER_INCH) shouldBe "2.54x5.08 inch"
        }
    }

    context("Density TheChangeUnitsMethod") {

    }
})
