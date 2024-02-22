package imagemagick.core.defines

import imagemagick.core.defines.Define as IDefine

/**
 * Interface for an object that specifies defines for an image.
 */
public interface Defines {
    public val defines: Iterable<IDefine>
}
