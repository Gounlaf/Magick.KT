package imagemagick.settings

import imagemagick.core.types.MagickGeometry
import kotlinx.cinterop.ExperimentalForeignApi
import imagemagick.core.settings.MagickReadSettings as Interface

@ExperimentalForeignApi
@ExperimentalStdlibApi
class MagickReadSettings() : MagickSettings(), Interface {
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

    internal constructor(settings: Interface) : this() {
        copy(settings)

        applyDefines()
        applyDimensions()
        applyFrame()
    }

    private fun applyDefines() {
        // TODO
    }

    private fun applyDimensions() {
        // TODO
    }

    private fun applyFrame() {
        // TODO
    }

    private fun copy(settings: Interface) {
        copyFrom(settings as MagickSettings)
//        Defines = settings.Defines
        frameIndex = settings.frameIndex
        frameCount = settings.frameCount
        height = settings.height
        width = settings.width
    }
}
