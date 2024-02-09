package imagemagick.core.enums

/**
 * Specifies alpha options.
 */
public enum class AlphaOption {
    /** Undefined. */
    UNDEFINED,

    /**
     * Enable the image's transparency channel. Note that normally Set should be used instead of
     * this, unless you specifically need to preserve the existing (but specifically turned Off)
     * transparency channel.
     */
    ACTIVATE,

    /** Associate the alpha channel with the image. */
    ASSOCIATE,

    /**
     * Set any fully-transparent pixel to the background color, while leaving it fully-transparent.
     * This can make some image file formats, such as PNG, smaller as the RGB values of transparent
     * pixels are more uniform, and thus can compress better.
     */
    BACKGROUND,

    /**
     * Turns 'On' the alpha/matte channel, then copies the grayscale intensity of the image, into
     * the alpha channel, converting a grayscale mask into a transparent shaped mask ready to be
     * colored appropriately. The color channels are not modified.
     */
    COPY,

    /**
     * _DISABLES THE IMAGE'S TRANSPARENCY CHANNEL. _THIS DOES NOT DELETE OR CHANGE THE EXISTING DATA,    * it just turns off the use of that data.
     */
    DEACTIVATE,

    /** Discrete. */
    DISCRETE,

    /** Disassociate the alpha channel from the image. */
    DISASSOCIATE,

    /**
     * Copies the alpha channel values into all the color channels and turns 'Off' the image's
     * transparency, so as to generate a grayscale mask of the image's shape. The alpha channel
     * data is left intact just deactivated. This is the inverse of 'Copy'.
     */
    EXTRACT,

    /** Off. */
    OFF,

    /** On. */
    ON,

    /** Enables the alpha/matte channel and forces it to be fully opaque. */
    OPAQUE,

    /** Composite the image over the background color. */
    REMOVE,

    /**
     * Activates the alpha/matte channel. If it was previously turned off then it also
     * RESETS THE CHANNEL TO OPAQUE. _IF THE IMAGE ALREADY HAD THE ALPHA CHANNEL TURNED ON,    * it will have no effect.
     */
    SET,

    /**
     * As per 'Copy' but also colors the resulting shape mask with the current background color.
     * That is the RGB color channels is replaced, with appropriate alpha shape.
     */
    SHAPE,

    /**
     * Activates the alpha/matte channel and forces it to be fully transparent. This effectively
     * creates a fully transparent image the same size as the original and with all its original
     * RGB data still intact, but fully transparent.
     */
    TRANSPARENT,
}
