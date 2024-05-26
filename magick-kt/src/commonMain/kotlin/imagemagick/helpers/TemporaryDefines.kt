package imagemagick.helpers

import imagemagick.MagickImage
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.contracts.ExperimentalContracts
import kotlin.experimental.ExperimentalNativeApi

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@ExperimentalStdlibApi
internal class TemporaryDefines(private val image: MagickImage) : AutoCloseable {
    private val names: MutableList<String> = mutableListOf()

    override fun close() {
        for (name in names) {
            image.removeArtifact(name)
        }
    }

    fun setArtifact(
        name: String,
        value: String,
    ) {
        if (value.isEmpty()) {
            return
        }

        names.add(name)
        image.setArtifact(name, value)
    }

    fun setArtifact(
        name: String,
        value: Boolean,
    ) {
        names.add(name)
        image.setArtifact(name, value)
    }

    fun <TValue> setArtifact(
        name: String,
        value: TValue?,
    ) {
        if (value == null) {
            return
        }

        names.add(name)
        image.setArtifact(name, value.toString())
    }
}
