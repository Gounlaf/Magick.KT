package imagemagick.kotest

import com.goncalossilva.resources.Resource
import imagemagick.MagickFormatInfo
import imagemagick.Resources
import imagemagick.core.enums.MagickFormat
import imagemagick.helpers.checkGcUsage
import imagemagick.helpers.empty
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.engine.test.logging.error
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import okio.Path.Companion.toPath

class MagickFormatInfoTests : ShouldSpec() {
    companion object {
        fun shouldReport(format: MagickFormat): Boolean {
            if (isDisabledThroughPolicy(format))
                return false

            return when (format) {
                MagickFormat.CLIPBOARD, MagickFormat.EMF, MagickFormat.WMF -> false//!Runtime.IsWindows
                else -> true
            }
        }

        private fun isDisabledThroughPolicy(format: MagickFormat): Boolean =
            format == MagickFormat.SUN || format == MagickFormat.RAS
    }

    init {
        context("MagickFormatInfo TheConstructor") {
            context("with ByteArray") {
                should("throw exception when array is empty") {
                    checkGcUsage {
                        shouldThrow<IllegalArgumentException> {
                            MagickFormatInfo.create(ubyteArrayOf())
                        }
                    }
                }

                should("return null when format cannot be determined") {
                    checkGcUsage {
                        val formatInfo = MagickFormatInfo.create(ubyteArrayOf(42u))

                        formatInfo.shouldBeNull()
                    }
                }

                should("return the correct info for the jpg format") {
                    checkGcUsage {
                        val bytes = Resource(Resources.Images.ImageMagickJPG.path).readBytes()

                        bytes.size shouldBeGreaterThan 0

                        val formatInfo = MagickFormatInfo.create(bytes.toUByteArray())
                        formatInfo.shouldNotBeNull()
                        formatInfo.format shouldBeEqual MagickFormat.JPEG
                    }
                }
            }

            context("with Path") {
                should("return the correct info for the png format") {
                    checkGcUsage {
                        // it doesn't have to be a real file; it guesses only the format from the extension
                        val formatInfo = MagickFormatInfo.create(Resources.Images.Magick_NET_iconPNG.path.toPath())

                        formatInfo.shouldNotBeNull()

                        formatInfo.canReadMultithreaded shouldBe true
                        formatInfo.canWriteMultithreaded shouldBe true
                        formatInfo.description shouldBe "Portable Network Graphics"
                        formatInfo.format shouldBe MagickFormat.PNG
                        formatInfo.mimeType shouldBe "image/png"
                        formatInfo.moduleFormat shouldBe MagickFormat.PNG
                        formatInfo.supportsMultipleFrames shouldBe false
                        formatInfo.supportsReading shouldBe true
                        formatInfo.supportsWriting shouldBe true
                    }
                }
            }

            context("with FileName") {
                should("throw exception when filename is empty") {
                    checkGcUsage {
                        shouldThrow<IllegalArgumentException> {
                            MagickFormatInfo.create(String.empty)
                        }
                    }
                }

                should("should return null for unknown extension") {
                    checkGcUsage {
                        val formatInfo = MagickFormatInfo.create("foo.bar")
                        formatInfo shouldBe null
                    }
                }

                should("return the correct info for the jpg format") {
                    checkGcUsage {
                        // it doesn't have to be a real file; it guesses only the format from the extension
                        val formatInfo = MagickFormatInfo.create(Resources.Images.ImageMagickJPG.path)

                        formatInfo.shouldNotBeNull()

                        formatInfo.canReadMultithreaded shouldBe true
                        formatInfo.canWriteMultithreaded shouldBe true
                        formatInfo.description shouldBe "Joint Photographic Experts Group JFIF format"
                        formatInfo.format shouldBe MagickFormat.JPG
                        formatInfo.mimeType shouldBe "image/jpeg"
                        formatInfo.moduleFormat shouldBe MagickFormat.JPEG
                        formatInfo.supportsMultipleFrames shouldBe false
                        formatInfo.supportsReading shouldBe true
                        formatInfo.supportsWriting shouldBe true
                    }
                }
            }

            context("with MagickFormat") {
                should("return the correct info for the gradient format") {
                    checkGcUsage {
                        val formatInfo = MagickFormatInfo.create(MagickFormat.GRADIENT)

                        formatInfo.shouldNotBeNull()

                        formatInfo.format shouldBe MagickFormat.GRADIENT

                        formatInfo.canReadMultithreaded shouldBe true
                        formatInfo.canWriteMultithreaded shouldBe true
                        formatInfo.description shouldBe "Gradual linear passing from one shade to another"
                        formatInfo.mimeType shouldBe null
                        formatInfo.supportsMultipleFrames shouldBe false
                        formatInfo.supportsReading shouldBe true
                        formatInfo.supportsWriting shouldBe false
                    }
                }

                should("return the correct info for the jp2 format") {
                    checkGcUsage {
                        val formatInfo = MagickFormatInfo.create(MagickFormat.JP2)

                        formatInfo.shouldNotBeNull()

                        formatInfo.format shouldBe MagickFormat.JP2

                        formatInfo.canReadMultithreaded shouldBe true
                        formatInfo.canWriteMultithreaded shouldBe true
                        formatInfo.description shouldBe "JPEG-2000 File Format Syntax"
                        formatInfo.mimeType shouldBe "image/jp2"
                        formatInfo.supportsMultipleFrames shouldBe false
                        formatInfo.supportsReading shouldBe true
                        formatInfo.supportsWriting shouldBe true
                    }
                }

                should("return the correct info for the pango format") {
                    checkGcUsage {
                        val formatInfo = MagickFormatInfo.create(MagickFormat.PANGO)

                        formatInfo.shouldNotBeNull()

                        formatInfo.canReadMultithreaded shouldBe false
                        formatInfo.canWriteMultithreaded shouldBe false
                        formatInfo.description shouldBe "Pango Markup Language"
                        formatInfo.format shouldBe MagickFormat.PANGO
                        formatInfo.mimeType shouldBe null
                        formatInfo.moduleFormat shouldBe MagickFormat.PANGO
                        formatInfo.supportsMultipleFrames shouldBe false
                        formatInfo.supportsReading shouldBe true
                        formatInfo.supportsWriting shouldBe false
                    }
                }

                should("return format info for all formats") {
                    checkGcUsage {
                        val missingFormats = MagickFormat.entries.filter {
                            if (it == MagickFormat.UNKNOWN) {
                                return@filter false
                            }

                            MagickFormatInfo.create(it)?.let { false } ?: shouldReport(it)
                        }

                        if (missingFormats.isNotEmpty()) {
                            error {
                                "Cannot find MagickFormatInfo for ${missingFormats.joinToString { it.name }}"
                            }
                        }
                    }
                }
            }
        }

        context("MagickFormatInfo TheEqualsMethod") {
            should("return true when the objects are equal") {
                checkGcUsage {
                    val first = MagickFormatInfo.create(MagickFormat.PNG)
                    val second = MagickFormatInfo.create(Resources.Images.SnakewarePNG.path)

                    first.shouldNotBeNull()
                    second.shouldNotBeNull()

                    first shouldBeEqual second
                }
            }
        }
    }
}
