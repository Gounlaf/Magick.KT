package imagemagick.settings

import imagemagick.QuantumType
import imagemagick.core.types.MagickGeometry
import kotlinx.cinterop.ExperimentalForeignApi
import net.sergeych.sprintf.format
import imagemagick.core.settings.MagickReadSettings as IMagickReadSettings

@ExperimentalForeignApi
@ExperimentalStdlibApi
class MagickReadSettings() : MagickSettings(), IMagickReadSettings<QuantumType> {
    override var extractArea: MagickGeometry = MagickGeometry()
    override var frameIndex: UInt? = null
    override var frameCount: UInt? = null
    override var height: UInt? = null
    override var syncImageWithExifProfile: Boolean = false
    override var useMonochrome: Boolean = false
    override var width: UInt? = null

    internal constructor(settings: MagickSettings) : this() {
        copyFrom(settings)
    }

    internal constructor(settings: IMagickReadSettings<QuantumType>) : this() {
        copy(settings)

        applyDefines()
        applyDimensions()
        applyFrame()
    }

    internal fun forceSingleFrame() {
        frameCount = 1u
        applyFrame()
    }

    private fun calculateScenes(): String? {
        if (frameIndex == null && (frameCount == null || frameCount == 1u)) {
            return null
        }

        frameIndex?.let {
            if (frameCount == numberScenes || frameCount == 1u) {
                return it.toString(radix = 10)
            }
        }

        val frame = frameIndex ?: 0u
        val count = frameCount ?: 1u

        return "${frame}-${frame + count}"
    }

    private fun applyDefines() {
        // TODO
    }

    private fun applyDimensions() {
        // TODO
    }

    private fun applyFrame() {
        if (frameIndex == null && frameCount == null) {
            return
        }

        scenes = calculateScenes()
        scene = frameIndex ?: 0u
        numberScenes = frameCount ?: 1u
    }

    private fun copy(settings: IMagickReadSettings<QuantumType>) {
        copyFrom(settings as MagickSettings)
//        Defines = settings.Defines
        frameIndex = settings.frameIndex
        frameCount = settings.frameCount
        height = settings.height
        width = settings.width
    }
}
