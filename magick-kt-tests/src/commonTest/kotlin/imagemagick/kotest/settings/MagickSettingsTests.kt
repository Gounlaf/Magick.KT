package imagemagick.kotest.settings

import imagemagick.MagickImage
import imagemagick.colors.MagickColors
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class MagickSettingsTests : ShouldSpec({
    context("MagickSettingsTests") {
        context("TheAffineProperty") {
            should("default to null") {
                MagickImage().use {
                    it.settings.affine shouldBe null
                }
            }

            should("be used when annotating") {
                MagickImage(MagickColors.White, 300u, 300u).use {
                }

//                using
//                val image = MagickImage(MagickColors.White, 300, 300)
//                image.Annotate("Magick.NET", Gravity.Center)
//
//                Color200 shouldBe MagickColors.White, image, 200
//
//                using
//                val imageWithAffine = MagickImage(MagickColors.White, 300, 300)
//                imageWithAffine.Settings.Affine = DrawableAffine(10, 20, 30, 40, 50, 60)
//                imageWithAffine.Annotate("Magick.NET", Gravity.Center)
//
//                Color200 shouldBe MagickColors.Black, imageWithAffine, 200
            }
        }
    }
})
