package imagemagick.helpers

import imagemagick.MagickImage
import kotlin.contracts.ExperimentalContracts
import kotlin.experimental.ExperimentalNativeApi
import kotlinx.cinterop.ExperimentalForeignApi

@ExperimentalForeignApi
@ExperimentalContracts
@ExperimentalNativeApi
@ExperimentalStdlibApi
internal class TemporaryDefines(private val image: MagickImage) : AutoCloseable {
    private val names: MutableList<String> = mutableListOf()

    override fun close() {
        for (name in names) {
            image.removeArtifact(name)
        }
    }

    fun setArtifact(name: String, value: String): Unit {
        if (value.isEmpty()) {
            return
        }

        names.add(name)
        image.setArtifact(name, value)
    }

    fun setArtifact(name: String, value: Boolean): Unit {
        names.add(name)
        image.setArtifact(name, value)
    }

    fun <TValue> setArtifact(name: String, value: TValue?): Unit {
        if (value == null) {
            return
        }

        names.add(name)
        image.setArtifact(name, value.toString())
    }
}
