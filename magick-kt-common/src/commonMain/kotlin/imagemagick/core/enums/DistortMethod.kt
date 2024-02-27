package imagemagick.core.enums

//
// /!\ do not modify order: it's directly linked to the underlying C library enum/order /!\
//

/**
 * Specifies distortion methods.
 */
public enum class DistortMethod {
    /** Undefined. */
    UNDEFINED,

    /** Affine. */
    AFFINE,

    /** Affine projection. */
    AFFINE_PROJECTION,

    /** Scale rotate translate. */
    SCALE_ROTATE_TRANSLATE,

    /** Perspective. */
    PERSPECTIVE,

    /** Perspective projection. */
    PERSPECTIVE_PROJECTION,

    /** Bilinear forward. */
    BILINEAR_FORWARD,

    /** Bilinear reverse. */
    BILINEAR_REVERSE,

    /** Polynomial. */
    POLYNOMIAL,

    /** Arc. */
    ARC,

    /** Polar. */
    POLAR,

    /** De-polar. */
    DE_POLAR,

    /** Cylinder 2 plane. */
    CYLINDER2_PLANE,

    /** Plane 2 cylinder. */
    PLANE2_CYLINDER,

    /** Barrel. */
    BARREL,

    /** Barrel inverse. */
    BARREL_INVERSE,

    /** Shepards. */
    SHEPARDS,

    /** Resize. */
    RESIZE,

    /** Sentinel. */
    SENTINEL,

    /** Rigid affine. */
    RIGID_AFFINE,
}
