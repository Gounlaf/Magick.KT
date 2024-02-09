package imagemagick.core.enums

import imagemagick.helpers.Flags
import imagemagick.helpers.or

/**
 * Specifies channel types.
 */
public enum class Channels(override val bit: ULong) : Flags {
    /** Undefined. */
    UNDEFINED(0b0.toULong()),

    /** Red. */
    RED(0b1.toULong()),

    /** Gray. */
    GRAY(RED.bit),

    /** Cyan. */
    CYAN(RED.bit),

    /** Green. */
    GREEN(0b10.toULong()),

    /** Magenta. */
    MAGENTA(GREEN.bit),

    /** Blue. */
    BLUE(0b100.toULong()),

    /** Yellow. */
    YELLOW(BLUE.bit),

    /** Black. */
    BLACK(0b1000.toULong()),

    /** Alpha. */
    ALPHA(0b10000.toULong()),

    /** Opacity. */
    OPACITY(ALPHA.bit),

    /** Index. */
    INDEX(0b100000.toULong()),

    /** Composite. */
    COMPOSITE(0b11111.toULong()),

    /** TrueAlpha. */
    TRUE_ALPHA(0b100000000.toULong()),

    /** RGB. */
    RGB(RED.or(GREEN).or(BLUE).value),

    /** RGBA. */
    RGBA(RED.or(GREEN).or(BLUE).or(ALPHA).value),

    /** CMYK. */
    CMYK(CYAN.or(MAGENTA).or(YELLOW).or(BLACK).value),

    /** CMYKA. */
    CMYKA(CYAN.or(MAGENTA).or(YELLOW).or(BLACK).or(ALPHA).value),

    /** Meta 0. */
    META0(1uL shl PixelChannel.META0.channel),

    /** Meta 1. */
    META1(1uL shl PixelChannel.META1.channel),

    /** Meta  2. */
    META2(1uL shl PixelChannel.META2.channel),

    /** Meta 3. */
    META3(1uL shl PixelChannel.META3.channel),

    /** Meta 4. */
    META4(1uL shl PixelChannel.META4.channel),

    /** Meta 5. */
    META5(1uL shl PixelChannel.META5.channel),

    /** Meta 6. */
    META6(1uL shl PixelChannel.META6.channel),

    /** Meta 7. */
    META7(1uL shl PixelChannel.META7.channel),

    /** Meta 8. */
    META8(1uL shl PixelChannel.META8.channel),

    /** Meta 9. */
    META9(1uL shl PixelChannel.META9.channel),

    /** Meta 10. */
    META10(1uL shl PixelChannel.META10.channel),

    /** Meta 11. */
    META11(1uL shl PixelChannel.META11.channel),

    /** Meta 12. */
    META12(1uL shl PixelChannel.META12.channel),

    /** Meta 13. */
    META13(1uL shl PixelChannel.META13.channel),

    /** Meta 14. */
    META14(1uL shl PixelChannel.META14.channel),

    /** Meta 15. */
    META15(1uL shl PixelChannel.META15.channel),

    /** Meta 16. */
    META16(1uL shl PixelChannel.META16.channel),

    /** Meta 17. */
    META17(1uL shl PixelChannel.META17.channel),

    /** Meta 18. */
    META18(1uL shl PixelChannel.META18.channel),

    /** Meta 19. */
    META19(1uL shl PixelChannel.META19.channel),

    /** Meta 20. */
    META20(1uL shl PixelChannel.META20.channel),

    /** Meta 21. */
    META21(1uL shl PixelChannel.META21.channel),

    /** Meta 22. */
    META22(1uL shl PixelChannel.META22.channel),

    /** Meta 23. */
    META23(1uL shl PixelChannel.META23.channel),

    /** Meta 24. */
    META24(1uL shl PixelChannel.META24.channel),

    /** Meta 25. */
    META25(1uL shl PixelChannel.META25.channel),

    /** Meta 26. */
    META26(1uL shl PixelChannel.META26.channel),

    /** Meta 27. */
    META27(1uL shl PixelChannel.META27.channel),

    /** Meta 28. */
    META28(1uL shl PixelChannel.META28.channel),

    /** Meta 29. */
    META29(1uL shl PixelChannel.META29.channel),

    /** Meta 30. */
    META30(1uL shl PixelChannel.META30.channel),

    /** Meta 31. */
    META31(1uL shl PixelChannel.META31.channel),

    /** Meta 32. */
    META32(1uL shl PixelChannel.META32.channel),

    /** Meta 33. */
    META33(1uL shl PixelChannel.META33.channel),

    /** Meta 34. */
    META34(1uL shl PixelChannel.META34.channel),

    /** Meta 35. */
    META35(1uL shl PixelChannel.META35.channel),

    /** Meta 36. */
    META36(1uL shl PixelChannel.META36.channel),

    /** Meta 37. */
    META37(1uL shl PixelChannel.META37.channel),

    /** Meta 38. */
    META38(1uL shl PixelChannel.META38.channel),

    /** Meta 39. */
    META39(1uL shl PixelChannel.META39.channel),

    /** Meta 40. */
    META40(1uL shl PixelChannel.META40.channel),

    /** Meta 41. */
    META41(1uL shl PixelChannel.META41.channel),

    /** Meta 42. */
    META42(1uL shl PixelChannel.META42.channel),

    /** Meta 43. */
    META43(1uL shl PixelChannel.META43.channel),

    /** Meta 44. */
    META44(1uL shl PixelChannel.META44.channel),

    /** Meta 45. */
    META45(1uL shl PixelChannel.META45.channel),

    /** Meta 46. */
    META46(1uL shl PixelChannel.META46.channel),

    /** Meta 47. */
    META47(1uL shl PixelChannel.META47.channel),

    /** Meta 48. */
    META48(1uL shl PixelChannel.META48.channel),

    /** Meta 49. */
    META49(1uL shl PixelChannel.META49.channel),

    /** Meta 50. */
    META50(1uL shl PixelChannel.META50.channel),

    /** Meta 51. */
    META51(1uL shl PixelChannel.META51.channel),

    /** Meta 52. */
    META52(1uL shl PixelChannel.META52.channel),

    /** All. */
    ALL(0b0111111111111111111111111111111111111111111111111111111111111111.toULong()),
}
