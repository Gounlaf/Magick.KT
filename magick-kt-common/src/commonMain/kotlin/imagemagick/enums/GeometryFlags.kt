package imagemagick.enums

import imagemagick.helpers.Flags

@Suppress("KDocMissingDocumentation")
public enum class GeometryFlags(override val bit: ULong) : Flags {
    NO_VALUE(0u),

    /** '%' percentage of something */
    PERCENT_VALUE(0x1000u),

    /** '!' resize no-aspect - special use flag */
    IGNORE_ASPECT_RATIO(0x2000u),

    /** '<' resize smaller - special use flag */
    LESS(0x4000u),

    /** '>' resize larger - spacial use flag */
    GREATER(0x8000u),

    /** '^' special handling needed */
    FILL_AREA(0x10000u),

    /** '@' resize to area - special use flag */
    LIMIT_PIXELS(0x20000u),

    /** '~' special handling needed */
    ASPECT_RATIO(0x100000u),
}
