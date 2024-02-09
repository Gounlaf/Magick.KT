package imagemagick.core.enums

public enum class FontWeight(public val weight: Int) {
    /** Undefined. */
    UNDEFINED(0),

    /** Thin (100). */
    THIN(100),

    /** Extra light (200). */
    EXTRA_LIGHT(200),

    /** Ultra light (200). */
    ULTRA_LIGHT(EXTRA_LIGHT.weight),

    /** Light (300). */
    LIGHT(300),

    /** Normal (400). */
    NORMAL(400),

    /** Regular (400). */
    REGULAR(NORMAL.weight),

    /** Medium (500). */
    MEDIUM(500),

    /** Demi bold (600). */
    DEMI_BOLD(600),

    /** Semi bold (600). */
    SEMI_BOLD(DEMI_BOLD.weight),

    /** Bold (700). */
    BOLD(700),

    /** Extra bold (800). */
    EXTRA_BOLD(800),

    /** Ultra bold (800). */
    ULTRA_BOLD(EXTRA_BOLD.weight),

    /** Heavy (900). */
    HEAVY(900),

    /** Black (900). */
    BLACK(HEAVY.weight),
}
