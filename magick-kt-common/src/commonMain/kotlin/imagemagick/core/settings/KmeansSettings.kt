package imagemagick.core.settings

/**
 * Class that contains setting for the kmeans operation.
 */
public interface KmeansSettings {
    /**
     * Gets or sets the seed clusters from color list (e.g. red;green;blue).
     */
    public var seedColors: String?

    /**
     * Gets or sets the number of colors to use as seeds.
     */
    public var numberColors: UInt

    /**
     * Gets or sets the maximum number of iterations while converging.
     */
    public var maxIterations: UInt

    /**
     * Gets or sets the maximum tolerance.
     */
    public var tolerance: Double
}
