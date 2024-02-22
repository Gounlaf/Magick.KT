package imagemagick.core.enums

/**
 * Specifies the pixel channels.
 */
public enum class PixelChannel(public val channel: Int) {
    /** Red. */
    RED(0),

    /** Cyan. */
    CYAN(RED.channel),

    /** Gray. */
    GRAY(RED.channel),

    /** Green. */
    GREEN(1),

    /** Magenta. */
    MAGENTA(GREEN.channel),

    /** Blue. */
    BLUE(2),

    /** Yellow. */
    YELLOW(BLUE.channel),

    /** Black. */
    BLACK(3),

    /** Alpha. */
    ALPHA(4),

    /** Index. */
    INDEX(5),

    /** Meta 0. */
    META0(10),

    /** Meta 1. */
    META1(11),

    /** Meta 2. */
    META2(12),

    /** Meta 3. */
    META3(13),

    /** Meta 4. */
    META4(14),

    /** Meta 5. */
    META5(15),

    /** Meta 6. */
    META6(16),

    /** Meta 7. */
    META7(17),

    /** Meta 8. */
    META8(18),

    /** Meta 9. */
    META9(19),

    /** Meta 10. */
    META10(20),

    /** Meta 11. */
    META11(21),

    /** Meta 12. */
    META12(22),

    /** Meta 13. */
    META13(23),

    /** Meta 14. */
    META14(24),

    /** Meta 15. */
    META15(25),

    /** Meta 16. */
    META16(26),

    /** Meta 17. */
    META17(27),

    /** Meta 18. */
    META18(28),

    /** Meta 19. */
    META19(29),

    /** Meta 20. */
    META20(30),

    /** Meta 21. */
    META21(31),

    /** Meta 22. */
    META22(32),

    /** Meta 23. */
    META23(33),

    /** Meta 24. */
    META24(34),

    /** Meta 25. */
    META25(35),

    /** Meta 26. */
    META26(36),

    /** Meta 27. */
    META27(37),

    /** Meta 28. */
    META28(38),

    /** Meta 29. */
    META29(39),

    /** Meta 30. */
    META30(40),

    /** Meta 31. */
    META31(41),

    /** Meta 32. */
    META32(42),

    /** Meta 33. */
    META33(43),

    /** Meta 34. */
    META34(44),

    /** Meta 35. */
    META35(45),

    /** Meta 36. */
    META36(46),

    /** Meta 37. */
    META37(47),

    /** Meta 38. */
    META38(48),

    /** Meta39. */
    META39(49),

    /** Meta 40. */
    META40(50),

    /** Meta 41. */
    META41(51),

    /** Meta 42. */
    META42(52),

    /** Meta 43. */
    META43(53),

    /** Meta 44. */
    META44(54),

    /** Meta 45. */
    META45(55),

    /** Meta 46. */
    META46(56),

    /** Meta 47. */
    META47(57),

    /** Meta 48. */
    META48(58),

    /** Meta 49. */
    META49(59),

    /** Meta 50. */
    META50(60),

    /** Meta 51. */
    META51(61),

    /** Meta 52. */
    META52(62),

    /** Composite. */
    COMPOSITE(64),
}
