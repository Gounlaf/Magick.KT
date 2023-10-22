package imagemagick.settings

import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.ColorType
import imagemagick.core.enums.CompressionMethod
import imagemagick.core.enums.DensityUnit
import imagemagick.core.enums.Endian
import imagemagick.core.enums.Interlace
import imagemagick.core.enums.MagickFormat
import imagemagick.core.types.Density
import imagemagick.core.types.MagickGeometry
import imagemagick.helpers.isNotNullOrEmpty
import imagemagick.native.NativeMagickSettings
import imagemagick.native.NativeMagickSettings.Companion.antiAlias
import imagemagick.native.NativeMagickSettings.Companion.colorFuzz
import imagemagick.native.NativeMagickSettings.Companion.colorSpace
import imagemagick.native.NativeMagickSettings.Companion.colorType
import imagemagick.native.NativeMagickSettings.Companion.compression
import imagemagick.native.NativeMagickSettings.Companion.debug
import imagemagick.native.NativeMagickSettings.Companion.density
import imagemagick.native.NativeMagickSettings.Companion.depth
import imagemagick.native.NativeMagickSettings.Companion.endian
import imagemagick.native.NativeMagickSettings.Companion.fileName
import imagemagick.native.NativeMagickSettings.Companion.format
import imagemagick.native.NativeMagickSettings.Companion.interlace
import imagemagick.native.NativeMagickSettings.Companion.monochrome
import imagemagick.native.NativeMagickSettings.Companion.numberScenes
import imagemagick.native.NativeMagickSettings.Companion.option
import imagemagick.native.NativeMagickSettings.Companion.ping
import imagemagick.native.NativeMagickSettings.Companion.quality
import imagemagick.native.NativeMagickSettings.Companion.scene
import imagemagick.native.NativeMagickSettings.Companion.scenes
import imagemagick.native.NativeMagickSettings.Companion.size
import imagemagick.native.NativeMagickSettings.Companion.verbose
import kotlinx.cinterop.ExperimentalForeignApi
import imagemagick.core.settings.MagickSettings as Interface

@ExperimentalForeignApi
@ExperimentalStdlibApi
open class MagickSettings internal constructor() : Interface {
    private val options = mutableMapOf<String, String>()

    override var antiAlias: Boolean = false
    override var colorSpace: ColorSpace = ColorSpace.UNDEFINED
    override var colorType: ColorType = ColorType.UNDEFINED
    override var compression: CompressionMethod = CompressionMethod.UNDEFINED
    override var debug: Boolean = false
    override var density: Density? = null
    override var depth: UInt = 0u
    override var endian: Endian = Endian.UNDEFINED
    override var format: MagickFormat = MagickFormat.UNKNOWN
    override var verbose: Boolean = false

    internal var colorFuzz: Double = 0.0
    internal var fileName: String? = null
    internal var interlace: Interlace
    internal var ping: Boolean = false
    internal var quality: UInt = 0u

    protected var extract: MagickGeometry? = null

    /**
     * Gets or sets the number of scenes.
     */
    protected var numberScenes: UInt = 0u

    /**
     * Gets or sets a value indicating whether a monochrome reader should be used.
     */
    protected var monochrome: Boolean

    /**
     * Gets or sets the size of the image.
     */
    protected var size: String? = null

    /**
     * Gets or sets the active scene.
     */
    protected var scene: UInt = 0u

    /**
     * Gets or sets scenes of the image.
     */
    protected var scenes: String? = null

    init {
        NativeMagickSettings().use { settings ->
            settings.native.let {
                antiAlias = it.antiAlias()
                colorSpace = it.colorSpace()
                colorType = it.colorType()
                compression = it.compression()
                debug = it.debug()
                density = it.density()?.takeUnless { str -> str.isBlank() }?.let { str -> Density.create(str) }
                depth = it.depth()
                endian = it.endian()
                format = it.format()?.let { format ->
                    try {
                        enumValueOf<MagickFormat>(format.uppercase())
                    } catch (e: Exception) {
                        null
                    }
                } ?: MagickFormat.UNKNOWN
                verbose = it.verbose()

                interlace = it.interlace()
                monochrome = it.monochrome()
            }
        }
    }

    private fun format(): String? = when (format) {
        MagickFormat.UNKNOWN -> null
        MagickFormat.THREEFR -> "3FR"
        MagickFormat.THREEG2 -> "3G2"
        MagickFormat.THREEGP -> "3GP"
        MagickFormat.RADIALGRADIENT -> "RADIAL-GRADIENT"
        MagickFormat.SPARSECOLOR -> "SPARSE-COLOR"
        else -> format.toString()
    }

    protected fun copyFrom(settings: MagickSettings?) {
        settings?.let {
            antiAlias = it.antiAlias
//            backgroundColor = MagickColor.Clone(it.BackgroundColor);
            colorSpace = it.colorSpace
            colorType = it.colorType
            compression = it.compression
            debug = it.debug
            density = it.density?.copy()
            depth = it.depth
            endian = it.endian
            extract = it.extract?.copy()
//            _font = it._font;
//            _fontPointsize = it._fontPointsize;
            format = it.format
            monochrome = it.monochrome
//            page = MagickGeometry.Clone(it.Page);
            verbose = it.verbose

            colorFuzz = it.colorFuzz
            interlace = it.interlace
            ping = it.ping
            quality = it.quality
            size = it.size

            it.options.forEach { (k, v) ->
                options[k] = v
            }

//            Drawing = it.Drawing.Clone();
        }
    }

    companion object {
        internal fun createNativeInstance(instance: Interface): NativeMagickSettings =
            NativeMagickSettings().also { native ->
                native.native.let {
                    val settings = instance as MagickSettings

                    val format = settings.format()?.uppercase()

                    it.antiAlias(settings.antiAlias)
                    //it.backgroundColor(Any()) TODO
                    it.colorSpace(settings.colorSpace)
                    it.colorType(settings.colorType)
                    it.compression(settings.compression)
                    it.debug(settings.debug)
                    it.density(settings.density?.toString(DensityUnit.UNDEFINED))
                    it.depth(settings.depth)
                    it.endian(settings.endian)
                    //it.extract() TODO
                    it.format(format)
                    it.interlace(settings.interlace)
                    it.monochrome(settings.monochrome)

                    it.colorFuzz(settings.colorFuzz)
                    it.fileName(with(settings.fileName) {
                        if (isNotNullOrEmpty() && format.isNotNullOrEmpty()) {
                            "$format:$this"
                        } else {
                            this
                        }
                    })
                    it.numberScenes(settings.numberScenes)
                    //it.page() TODO
                    it.ping(settings.ping)
                    it.quality(settings.quality)
                    it.scene(settings.scene)
                    it.scenes(settings.scenes)
                    it.size(settings.size)

                    settings.options.forEach { (k, v) -> it.option(k, v) }
                }
            }
    }
}
