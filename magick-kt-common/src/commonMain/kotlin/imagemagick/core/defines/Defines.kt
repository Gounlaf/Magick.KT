package imagemagick.core.defines

import imagemagick.core.defines.Define as IDefine

/**
 * Interface for an object that specifies defines for an image.
 */
public interface Defines {
    /**
     * Gets the defines that should be set as a define on an image.
     */
    public val defines: Iterable<IDefine>
}
