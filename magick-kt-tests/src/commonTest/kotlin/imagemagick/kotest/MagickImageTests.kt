package imagemagick.kotest

import imagemagick.MagickImage
import imagemagick.Resources
import imagemagick.core.enums.MagickFormat
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem

class MagickImageTests : ShouldSpec({
    context("MagickImage") {
        context("TheReadMethod") {
            context("WithStream") {
//                should("throw exception when stream is empty")
//                {
//                    MagickImage().use { image ->
//                        shouldThrow<ArgumentNullException> {
//                            image.read(/* TODO */)
//                        }
//                    }
//                }
//
//
//                should("throw exception when stream is not readable")
//                {
//                    using
//                    val testStream = TestStream.ThatCannotRead()
//                    using
//                    val image = MagickImage()
//
//                    shouldThrow<IllegalArgumentException> {
//                        image.Read(testStream)
//                    }
//                }

                should("read image") {
                    MagickImage().use { image ->
                        val fileStream = SystemFileSystem.source(Path(Resources.Images.SnakewarePNG.path)).buffered()

                        image.read(fileStream)
                        image.width shouldBe 286
                        image.height shouldBe 67
                        image.format shouldBe MagickFormat.PNG
                    }
                }

//                should("read image from seekable partial stream")
//                {
//                    using
//                    val image = MagickImage()
//                    using
//                    val fileStream = File.OpenRead(Files.ImageMagickJPG)
//                    image.Read(fileStream)
//
//                    fileStream.Position = 0
//                    using
//                    val partialStream = PartialStream(fileStream, true)
//                    using
//                    val testImage = MagickImage()
//                    testImage.Read(partialStream)
//
//                    testImage.width shouldBe image.Width
//                    testImage.height shouldBe image.Height
//                    testImage.format shouldBe image.Format
//                    image.compare(testImage, ErrorMetric.rootMeanSquared) shouldBe 0.0
//                }
//
//
//                should("read image from non seekable partial stream")
//                {
//                    using
//                    val image = MagickImage()
//                    using
//                    val fileStream = File.OpenRead(Files.ImageMagickJPG)
//                    image.Read(fileStream)
//
//                    fileStream.Position = 0
//                    using
//                    val partialStream = PartialStream(fileStream, false)
//                    using
//                    val testImage = MagickImage()
//                    testImage.Read(partialStream)
//
//                    testImage.width shouldBe image.Width
//                    testImage.height shouldBe image.Height
//                    testImage.format shouldBe image.Format
//                    image.compare(testImage, ErrorMetric.rootMeanSquared) shouldBe 0.0
//                }
//
//
//                should("read image from memory stream where buffer is not publicly visible")
//                {
//                    val data = File.ReadAllBytes(Files.CirclePNG)
//                    val testBuffer = byte[data.Length + 10]
//                    data.CopyTo(testBuffer, index: 10)
//
//                    using
//                    val stream = MemoryStream(testBuffer, index: 10, count: testBuffer.Length-10)
//                    using
//                    val image = MagickImage()
//                    image.Read(stream)
//                }
            }
        }
    }
})
