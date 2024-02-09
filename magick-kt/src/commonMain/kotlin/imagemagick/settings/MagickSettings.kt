package imagemagick.settings

import imagemagick.MagickFormatInfo
import imagemagick.QuantumType
import imagemagick.colors.MagickColor
import imagemagick.colors.MagickColor.Companion.toMagick
import imagemagick.core.MagickImageQuantum
import imagemagick.core.colors.MagickColorQuantum
import imagemagick.core.defines.Defines
import imagemagick.core.enums.ColorSpace
import imagemagick.core.enums.ColorType
import imagemagick.core.enums.CompressionMethod
import imagemagick.core.enums.DensityUnit
import imagemagick.core.enums.Endian
import imagemagick.core.enums.FillRule
import imagemagick.core.enums.FontStyleType
import imagemagick.core.enums.FontWeight
import imagemagick.core.enums.Gravity
import imagemagick.core.enums.Interlace
import imagemagick.core.enums.LineCap
import imagemagick.core.enums.LineJoin
import imagemagick.core.enums.MagickFormat
import imagemagick.core.enums.TextDirection
import imagemagick.core.types.Density
import imagemagick.exceptions.Throw
import imagemagick.helpers.enumValueOf
import imagemagick.helpers.isNotNullOrEmpty
import imagemagick.magicknative.NativeMagickSettings
import imagemagick.magicknative.colors.NativeMagickColor
import imagemagick.types.MagickGeometry
import imagemagick.types.MagickGeometry.Companion.toMagickGeometry
import kotlin.contracts.ExperimentalContracts
import kotlin.experimental.ExperimentalNativeApi
import kotlinx.cinterop.ExperimentalForeignApi
import imagemagick.core.drawables.DrawableAffine as IDrawableAffine
import imagemagick.core.settings.MagickSettings as IMagickSettings
import imagemagick.core.types.MagickGeometry as IMagickGeometry

@ExperimentalContracts
@ExperimentalForeignApi
@ExperimentalNativeApi
@ExperimentalStdlibApi
public open class MagickSettings : IMagickSettings<QuantumType> {
    private val options: MutableMap<String, String?> = mutableMapOf()

    // TODO Take care of events

    override var affine: IDrawableAffine?
        get() = drawing.affine
        set(value) {
            drawing.affine = value
        }
    override var antiAlias: Boolean = false
    override var backgroundColor: MagickColorQuantum<QuantumType>? = null
    override var borderColor: MagickColorQuantum<QuantumType>?
        get() = drawing.borderColor
        set(value) {
            drawing.borderColor = value
        }
    override var colorSpace: ColorSpace = ColorSpace.UNDEFINED
    override var colorType: ColorType = ColorType.UNDEFINED
    override var compression: CompressionMethod = CompressionMethod.UNDEFINED
    override var debug: Boolean = false
    override var density: Density? = null
    override var depth: UInt = 0u
    override var endian: Endian = Endian.UNDEFINED
    override var fillColor: MagickColorQuantum<QuantumType>?
        get() = drawing.fillColor
        set(value) {
            setOptionAndArtifact("fill", value?.toString())
            drawing.fillColor = value
        }
    override var fillPattern: MagickImageQuantum<QuantumType>?
        get() = drawing.fillPattern
        set(value) {
            drawing.fillPattern = value
        }
    override var fillRule: FillRule
        get() = drawing.fillRule
        set(value) {
            drawing.fillRule = value
        }

    override var font: String?
        get() = drawing.font
        set(value) {
            drawing.font = value
        }
    override var fontFamily: String?
        get() = drawing.fontFamily
        set(value) {
            setOptionAndArtifact("family", value)
            drawing.fontFamily = value
        }
    private var backingFontPointSize: Double = 0.0
    override var fontPointsize: Double
        get() = backingFontPointSize
        set(value) {
            backingFontPointSize = value
            drawing.fontPointsize = value
        }
    override var fontStyle: FontStyleType
        get() = drawing.fontStyle
        set(value) {
            setOptionAndArtifact("style", value)
            drawing.fontStyle = value
        }
    override var fontWeight: FontWeight
        get() = drawing.fontWeight
        set(value) {
            setOptionAndArtifact("weight", value.weight.toString())
            drawing.fontWeight = value
        }
    override var format: MagickFormat = MagickFormat.UNKNOWN
    override var interlace: Interlace = Interlace.UNDEFINED
    override var page: IMagickGeometry? = null
    override var strokeAntiAlias: Boolean
        get() = drawing.strokeAntiAlias
        set(value) {
            drawing.strokeAntiAlias = value
        }
    override var strokeColor: MagickColorQuantum<QuantumType>?
        get() = drawing.strokeColor
        set(value) {
            setOptionAndArtifact("stroke", value?.toString())
            drawing.strokeColor = value
        }
    override var strokeDashArray: Iterable<Double>?
        get() = drawing.strokeDashArray
        set(value) {
            drawing.strokeDashArray = value
        }
    override var strokeDashOffset: Double
        get() = drawing.strokeDashOffset
        set(value) {
            drawing.strokeDashOffset = value
        }
    override var strokeLineCap: LineCap
        get() = drawing.strokeLineCap
        set(value) {
            drawing.strokeLineCap = value
        }
    override var strokeLineJoin: LineJoin
        get() = drawing.strokeLineJoin
        set(value) {
            drawing.strokeLineJoin = value
        }
    override var strokeMiterLimit: UInt
        get() = drawing.strokeMiterLimit
        set(value) {
            drawing.strokeMiterLimit = value
        }
    override var strokePattern: MagickImageQuantum<QuantumType>?
        get() = drawing.strokePattern
        set(value) {
            drawing.strokePattern = value
        }
    override var strokeWidth: Double
        get() = drawing.strokeWidth
        set(value) {
            setOptionAndArtifact("strokewidth", value)
            drawing.strokeWidth = value
        }
    override var textAntiAlias: Boolean
        get() = drawing.textAntiAlias
        set(value) {
            drawing.textAntiAlias = value
        }
    override var textDirection: TextDirection
        get() = drawing.textDirection
        set(value) {
            drawing.textDirection = value
        }
    override var textEncoding: String?
        get() = drawing.textEncoding
        set(value) {
            drawing.textEncoding = value
        }
    override var textGravity: Gravity
        get() = drawing.textGravity
        set(value) {
            setOptionAndArtifact("gravity", value)
            drawing.textGravity = value
        }
    override var textInterlineSpacing: Double
        get() = drawing.textInterlineSpacing
        set(value) {
            setOptionAndArtifact("interline-spacing", value)
            drawing.textInterlineSpacing = value
        }
    override var textInterwordSpacing: Double
        get() = drawing.textInterwordSpacing
        set(value) {
            setOptionAndArtifact("interword-spacing", value)
            drawing.textInterwordSpacing = value
        }
    override var textKerning: Double
        get() = drawing.textKerning
        set(value) {
            setOptionAndArtifact("kerning", value)
            drawing.textKerning = value
        }
    override var textUnderColor: MagickColorQuantum<QuantumType>?
        get() = drawing.textUnderColor
        set(value) {
            setOptionAndArtifact("undercolor", value?.toString())
            drawing.textUnderColor = value
        }

    override var verbose: Boolean = false

    internal var drawing: DrawingSettings
        private set
    internal var colorFuzz: Double = 0.0
    internal var fileName: String? = null
    internal var ping: Boolean = false
    internal var quality: UInt = 0u

    /** Gets or sets the specified area to extract from the image. */
    protected var extract: IMagickGeometry? = null

    /** Gets or sets the number of scenes. */
    protected var numberScenes: UInt = 0u

    /** Gets or sets a value indicating whether a monochrome reader should be used. */
    protected var monochrome: Boolean = false

    /** Gets or sets the size of the image. */
    protected var size: String? = null

    /** Gets or sets the active scene. */
    protected var scene: UInt = 0u

    /** Gets or sets scenes of the image. */
    protected var scenes: String? = null

    init {
        NativeMagickSettings().use { settings ->
            antiAlias = settings.antiAlias
            backgroundColor = settings.backgroundColor?.toMagick()
            colorSpace = settings.colorSpace
            colorType = settings.colorType
            compression = settings.compression
            debug = settings.debug
            density = settings.density.takeUnless { str -> str.isNullOrBlank() }?.let { str -> Density.create(str) }
            depth = settings.depth
            endian = settings.endian
            extract = settings.extract?.toMagickGeometry()
            format = enumValueOf<MagickFormat>(settings.format?.uppercase(), MagickFormat.UNKNOWN)
            interlace = settings.interlace
            monochrome = settings.monochrome
            verbose = settings.verbose
        }

        drawing = DrawingSettings()
    }

    override fun getDefine(
        format: MagickFormat,
        name: String,
    ): String? {
        Throw.ifEmpty("name", name)
        return getOption(parseDefine(format, name))
    }

    override fun getDefine(name: String): String? {
        Throw.ifEmpty("name", name)
        return getOption(name)
    }

    override fun removeDefine(
        format: MagickFormat,
        name: String,
    ) {
        Throw.ifEmpty("name", name)
        val key = parseDefine(format, name)
        options.remove(key)
    }

    override fun removeDefine(name: String) {
        Throw.ifEmpty("name", name)
        options.remove(name)
    }

    override fun setDefine(
        format: MagickFormat,
        name: String,
        flag: Boolean,
    ): Unit = setDefine(format, name, flag.toString())

    override fun setDefine(
        format: MagickFormat,
        name: String,
        value: Int,
    ): Unit = setDefine(format, name, value.toString())

    override fun setDefine(
        format: MagickFormat,
        name: String,
        value: String,
    ) {
        Throw.ifEmpty("name", name)
        setOption(parseDefine(format, name), value)
    }

    override fun setDefine(
        name: String,
        value: String,
    ) {
        Throw.ifEmpty("name", name)
        setOption(name, value)
    }

    override fun setDefines(defines: Defines) {
        defines.defines.iterator().forEach {
            setDefine(it.format, it.name, it.value)
        }
    }

    internal fun clone(): MagickSettings =
        MagickSettings().also {
            it.copyFrom(this)
        }

    private fun format(): String? =
        when (format) {
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
            drawing = settings.drawing.clone()

            antiAlias = it.antiAlias
            backgroundColor = MagickColor.clone(it.backgroundColor)
            colorSpace = it.colorSpace
            colorType = it.colorType
            compression = it.compression
            debug = it.debug
            density = it.density?.copy()
            depth = it.depth
            endian = it.endian
            extract = MagickGeometry.clone(it.extract)
            font = it.font
            fontPointsize = it.fontPointsize
            format = it.format
            monochrome = it.monochrome
            page = MagickGeometry.clone(it.page)
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

    /**
     * Gets an image option.
     *
     * @param key The key of the option.
     *
     * @return The value of the option.
     */
    protected fun getOption(key: String): String? = options[key]

    /**
     * Sets an image option.
     *
     * @param key The key of the option.
     * @param value The value of the option.
     */
    protected fun setOption(
        key: String,
        value: String?,
    ) {
        options[key] = value
    }

    public fun createNativeInstance(): NativeMagickSettings = Companion.createNativeInstance(this)

    private fun setOptionAndArtifact(
        key: String,
        value: Double,
    ) = setOptionAndArtifact(key, value.toString())

    private inline fun <reified T : Enum<T>> setOptionAndArtifact(
        key: String,
        value: Enum<T>,
    ) = setOptionAndArtifact(key, value.toString())

    private fun setOptionAndArtifact(
        key: String,
        value: String?,
    ) {
    }

    public companion object {
        public fun MagickSettings.toNative(): NativeMagickSettings = createNativeInstance(this)

        public fun createNativeInstance(instance: IMagickSettings<QuantumType>): NativeMagickSettings =
            (instance as MagickSettings).let { settings ->
                val format = settings.format()?.uppercase()

                NativeMagickSettings().apply {
                    antiAlias = settings.antiAlias

                    if (settings.backgroundColor != null) {
                        settings.backgroundColor?.let {
                            MagickColor.createNativeInstance(it).use { nativeColor: NativeMagickColor ->
                                backgroundColor = nativeColor
                            }
                        }
                    } else {
                        backgroundColor = null
                    }

                    colorSpace = settings.colorSpace
                    colorType = settings.colorType
                    compression = settings.compression
                    debug = settings.debug
                    density = settings.density?.toString(DensityUnit.UNDEFINED)
                    depth = settings.depth
                    endian = settings.endian
                    extract = settings.extract?.toString()
                    this.format = format
                    fontPointsize = settings.fontPointsize
                    interlace = settings.interlace
                    monochrome = settings.monochrome

                    colorFuzz(settings.colorFuzz)
                    fileName(
                        with(settings.fileName) {
                            if (isNotNullOrEmpty() && format.isNotNullOrEmpty()) {
                                "$format:$this"
                            } else {
                                this
                            }
                        },
                    )
                    numberScenes(settings.numberScenes)
                    page(settings.page?.toString())
                    ping(settings.ping)
                    quality(settings.quality)
                    scene(settings.scene)
                    scenes(settings.scenes)
                    size(settings.size)

                    settings.options.forEach { (k, v) -> option(k, v) }
                }
            }

        private fun parseDefine(
            format: MagickFormat,
            name: String,
        ): String {
            if (format == MagickFormat.UNKNOWN) {
                return name
            }

            val module = getModule(format)

            return "${module.name}:$name"
        }

        private fun getModule(format: MagickFormat): MagickFormat =
            MagickFormatInfo.create(format)?.moduleFormat ?: format
    }
}
