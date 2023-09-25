package imagemagick.core.enums

//
// /!\ do not modify order: it's directly linked to the underlying C library enum/order /!\
//

/**
 * Specifies the different file formats that are supported by ImageMagick.
 */
enum class MagickFormat {
    /** Unknown. */
    UNKNOWN,
    /** Hasselblad CFV/H3D39II. */
    THREEFR,
    /** Media Container. */
    THREEG2,
    /** Media Container. */
    THREEGP,
    /** Raw alpha samples. */
    A,
    /** AAI Dune image. */
    AAI,
    /** Adobe Illustrator CS2. */
    AI,
    /** Animated Portable Network Graphics. */
    APNG,
    /** PFS: 1st Publisher Clip Art. */
    ART,
    /** Sony Alpha Raw Image Format. */
    ARW,
    /** Image sequence laid out in continuous irregular courses (Unknown). */
    ASHLAR,
    /** Microsoft Audio/Visual Interleaved. */
    AVI,
    /** AV1 Image File Format (Heic). */
    AVIF,
    /** AVS X image. */
    AVS,
    /** Raw blue samples. */
    B,
    /** Raw mosaiced samples. */
    BAYER,
    /** Raw mosaiced and alpha samples. */
    BAYERA,
    /** Raw blue, green, and red samples. */
    BGR,
    /** Raw blue, green, red, and alpha samples. */
    BGRA,
    /** Raw blue, green, red, and opacity samples. */
    BGRO,
    /** Microsoft Windows bitmap image. */
    BMP,
    /** Microsoft Windows bitmap image (V2). */
    BMP2,
    /** Microsoft Windows bitmap image (V3). */
    BMP3,
    /** BRF ASCII Braille format. */
    BRF,
    /** Raw cyan samples. */
    C,
    /** Continuous Acquisition and Life-cycle Support Type 1. */
    CAL,
    /** Continuous Acquisition and Life-cycle Support Type 1. */
    CALS,
    /** Constant image uniform color. */
    CANVAS,
    /** Caption. */
    CAPTION,
    /** Cineon Image File. */
    CIN,
    /**Cisco IP phone image format. */
    CIP,
    /** Image Clip Mask. */
    CLIP,
    /** The system clipboard. */
    CLIPBOARD,
    /** Raw cyan, magenta, yellow, and black samples. */
    CMYK,
    /** Raw cyan, magenta, yellow, black, and alpha samples. */
    CMYKA,
    /** Canon Digital Camera Raw Image Format. */
    CR2,
    /** Canon Digital Camera Raw Image Format. */
    CR3,
    /** Canon Digital Camera Raw Image Format. */
    CRW,
    /** Cube color lookup table image. */
    CUBE,
    /** Microsoft icon. */
    CUR,
    /** DR Halo. */
    CUT,
    /** Base64-encoded inline images. */
    DATA,
    /** Digital Imaging and Communications in Medicine image. */
    DCM,
    /** Kodak Digital Camera Raw Image File. */
    DCR,
    /** Raw Photo Decoder (dcraw) (Dng). */
    DCRAW,
    /** ZSoft IBM PC multi-page Paintbrush. */
    DCX,
    /** Microsoft DirectDraw Surface. */
    DDS,
    /** Multi-face font package. */
    DFONT,
    /** Microsoft Windows 3.X Packed Device-Independent Bitmap. */
    DIB,
    /** Digital Negative. */
    DNG,
    /** SMPTE 268M-2003 (DPX 2.0). */
    DPX,
    /** Microsoft DirectDraw Surface. */
    DXT1,
    /** Microsoft DirectDraw Surface. */
    DXT5,
    /** Windows Enhanced Meta File. */
    EMF,
    /** Encapsulated Portable Document Format. */
    EPDF,
    /** Encapsulated PostScript Interchange format. */
    EPI,
    /** Encapsulated PostScript. */
    EPS,
    /** Level II Encapsulated PostScript. */
    EPS2,
    /** Level III Encapsulated PostScript. */
    EPS3,
    /** Encapsulated PostScript. */
    EPSF,
    /** Encapsulated PostScript Interchange format. */
    EPSI,
    /** Encapsulated PostScript with TIFF preview. */
    EPT,
    /** Encapsulated PostScript Level II with TIFF preview. */
    EPT2,
    /** Encapsulated PostScript Level III with TIFF preview. */
    EPT3,
    /** Epson RAW Format. */
    ERF,
    /** High Dynamic-range (HDR). */
    EXR,
    /** Farbfeld. */
    FARBFELD,
    /** Group 3 FAX. */
    FAX,
    /** Farbfeld. */
    FF,
    /** Uniform Resource Locator (file://). */
    FILE,
    /** Flexible Image Transport System. */
    FITS,
    /** FilmLight. */
    FL32,
    /** Flash Video Stream. */
    FLV,
    /** Plasma fractal image. */
    FRACTAL,
    /** Uniform Resource Locator (ftp://). */
    FTP,
    /** Flexible Image Transport System. */
    FTS,
    /** Formatted text image. */
    FTXT,
    /** Raw green samples. */
    G,
    /** Group 3 FAX. */
    G3,
    /** Group 4 FAX. */
    G4,
    /** CompuServe graphics interchange format. */
    GIF,
    /** CompuServe graphics interchange format. */
    GIF87,
    /** Gradual linear passing from one shade to another. */
    GRADIENT,
    /** Raw gray samples. */
    GRAY,
    /** Raw gray and alpha samples. */
    GRAYA,
    /** Raw CCITT Group4. */
    GROUP4,
    /** Identity Hald color lookup table image. */
    HALD,
    /** Radiance RGBE image format. */
    HDR,
    /** High Efficiency Image Format. */
    HEIC,
    /** High Efficiency Image Format. */
    HEIF,
    /** Histogram of the image. */
    HISTOGRAM,
    /** Slow Scan TeleVision. */
    HRZ,
    /** Hypertext Markup Language and a client-side image map. */
    HTM,
    /** Hypertext Markup Language and a client-side image map. */
    HTML,
    /** Uniform Resource Locator (http://). */
    HTTP,
    /** Uniform Resource Locator (https://). */
    HTTPS,
    /** Truevision Targa image. */
    ICB,
    /** Microsoft icon. */
    ICO,
    /** Microsoft icon. */
    ICON,
    /** Phase One Raw Image Format. */
    IIQ,
    /** The image format and characteristics. */
    INFO,
    /** Base64-encoded inline images. */
    INLINE,
    /** IPL Image Sequence. */
    IPL,
    /** ISO/TR 11548-1 format. */
    ISOBRL,
    /** ISO/TR 11548-1 format 6dot. */
    ISOBRL6,
    /** JPEG-2000 Code Stream Syntax. */
    J2C,
    /** JPEG-2000 Code Stream Syntax. */
    J2K,
    /** JPEG Network Graphics. */
    JNG,
    /** Garmin tile format. */
    JNX,
    /** JPEG-2000 File Format Syntax. */
    JP2,
    /** JPEG-2000 Code Stream Syntax. */
    JPC,
    /** Joint Photographic Experts Group JFIF format. */
    JPE,
    /** Joint Photographic Experts Group JFIF format. */
    JPEG,
    /** Joint Photographic Experts Group JFIF format. */
    JPG,
    /** JPEG-2000 File Format Syntax. */
    JPM,
    /** Joint Photographic Experts Group JFIF format. */
    JPS,
    /** JPEG-2000 File Format Syntax. */
    JPT,
    /** The image format and characteristics. */
    JSON,
    /** JPEG XL Lossless JPEG1 Recompression. */
    JXL,
    /** Raw black samples. */
    K,
    /** Kodak Digital Camera Raw Image Format. */
    K25,
    /** Kodak Digital Camera Raw Image Format. */
    KDC,
    /** Image label. */
    LABEL,
    /** Raw magenta samples. */
    M,
    /** MPEG Video Stream. */
    M2V,
    /** Raw MPEG-4 Video. */
    M4V,
    /** MAC Paint. */
    MAC,
    /** Colormap intensities and indices. */
    MAP,
    /** Image Clip Mask. */
    MASK,
    /** MATLAB level 5 image format. */
    MAT,
    /** MATTE format. */
    MATTE,
    /** Mamiya Raw Image File. */
    MEF,
    /** Magick Image File Format. */
    MIFF,
    /** Multimedia Container. */
    MKV,
    /** Multiple-image Network Graphics. */
    MNG,
    /** Raw bi-level bitmap. */
    MONO,
    /** MPEG Video Stream. */
    MOV,
    /** MPEG-4 Video Stream. */
    MP4,
    /** Magick Persistent Cache image format. */
    MPC,
    /** MPEG Video Stream. */
    MPEG,
    /** MPEG Video Stream. */
    MPG,
    /** Joint Photographic Experts Group JFIF format (Jpeg). */
    MPO,
    /** Sony (Minolta) Raw Image File. */
    MRW,
    /** Magick Scripting Language. */
    MSL,
    /** ImageMagick's own SVG internal renderer. */
    MSVG,
    /** MTV Raytracing image format. */
    MTV,
    /** Magick Vector Graphics. */
    MVG,
    /** Nikon Digital SLR Camera Raw Image File. */
    NEF,
    /** Nikon Digital SLR Camera Raw Image File. */
    NRW,
    /** Constant image of uniform color. */
    NULL,
    /** Raw opacity samples. */
    O,
    /** OpenRaster format. */
    ORA,
    /** Olympus Digital Camera Raw Image File. */
    ORF,
    /** On-the-air bitmap. */
    OTB,
    /** Open Type font. */
    OTF,
    /** 16bit/pixel interleaved YUV. */
    PAL,
    /** Palm pixmap. */
    PALM,
    /** Common 2-dimensional bitmap format. */
    PAM,
    /** Pango Markup Language. */
    PANGO,
    /** Predefined pattern. */
    PATTERN,
    /** Portable bitmap format (black and white). */
    PBM,
    /** Photo CD. */
    PCD,
    /** Photo CD. */
    PCDS,
    /** Printer Control Language. */
    PCL,
    /** Apple Macintosh QuickDraw/PICT. */
    PCT,
    /** ZSoft IBM PC Paintbrush. */
    PCX,
    /** Palm Database ImageViewer Format. */
    PDB,
    /** Portable Document Format. */
    PDF,
    /** Portable Document Archive Format. */
    PDFA,
    /** Pentax Electronic File. */
    PEF,
    /** Embrid Embroidery Format. */
    PES,
    /** Postscript Type 1 font (ASCII). */
    PFA,
    /** Postscript Type 1 font (binary). */
    PFB,
    /** Portable float format. */
    PFM,
    /** Portable graymap format (gray scale). */
    PGM,
    /** Portable half float format. */
    PHM,
    /** JPEG 2000 uncompressed format. */
    PGX,
    /** Personal Icon. */
    PICON,
    /** Apple Macintosh QuickDraw/PICT. */
    PICT,
    /** Alias/Wavefront RLE image format. */
    PIX,
    /** Joint Photographic Experts Group JFIF format. */
    PJPEG,
    /** Plasma fractal image. */
    PLASMA,
    /** Portable Network Graphics. */
    PNG,
    /** PNG inheriting bit-depth and color-type from original. */
    PNG00,
    /** opaque or binary transparent 24-bit RGB. */
    PNG24,
    /** OPAQUE OR TRANSPARENT 32-BIT RGBA. */
    PNG32,
    /** opaque or binary transparent 48-bit RGB. */
    PNG48,
    /** opaque or transparent 64-bit RGBA. */
    PNG64,
    /** 8-bit indexed with optional binary transparency. */
    PNG8,
    /** Portable anymap. */
    PNM,
    /** Pocketmod Personal Organizer (Pdf). */
    POCKETMOD,
    /** Portable pixmap format (color). */
    PPM,
    /** PostScript. */
    PS,
    /** Level II PostScript. */
    PS2,
    /** Level III PostScript. */
    PS3,
    /** Adobe Large Document Format. */
    PSB,
    /** Adobe Photoshop bitmap. */
    PSD,
    /** Pyramid encoded TIFF. */
    PTIF,
    /** Seattle Film Works. */
    PWP,
    /** Quite OK image format. */
    QOI,
    /** Raw red samples. */
    R,
    /** Gradual radial passing from one shade to another. */
    RADIALGRADIENT,
    /** Fuji CCD-RAW Graphic File. */
    RAF,
    /** SUN Rasterfile. */
    RAS,
    /** Raw. */
    RAW,
    /** Raw red, green, and blue samples. */
    RGB,
    /** Raw red, green, blue samples in 565 format. */
    RGB565,
    /** Raw red, green, blue, and alpha samples. */
    RGBA,
    /** Raw red, green, blue, and opacity samples. */
    RGBO,
    /** LEGO Mindstorms EV3 Robot Graphic Format (black and white). */
    RGF,
    /** Alias/Wavefront image. */
    RLA,
    /** Utah Run length encoded image. */
    RLE,
    /** Raw Media Format. */
    RMF,
    /** Rsvg. */
    RSVG,
    /** Panasonic Lumix Raw Image. */
    RW2,
    /** ZX-Spectrum SCREEN$. */
    SCR,
    /** Screen shot. */
    SCREENSHOT,
    /** Scitex HandShake. */
    SCT,
    /** Seattle Film Works. */
    SFW,
    /** Irix RGB image. */
    SGI,
    /** Hypertext Markup Language and a client-side image map. */
    SHTML,
    /** DEC SIXEL Graphics Format. */
    SIX,
    /** DEC SIXEL Graphics Format. */
    SIXEL,
    /** Sparse Color. */
    SPARSECOLOR,
    /** Sony Raw Format 2. */
    SR2,
    /** Sony Raw Format. */
    SRF,
    /** Steganographic image. */
    STEGANO,
    /** String to image and back. */
    STRIMG,
    /** SUN Rasterfile. */
    SUN,
    /** Scalable Vector Graphics. */
    SVG,
    /** Compressed Scalable Vector Graphics. */
    SVGZ,
    /** Text. */
    TEXT,
    /** Truevision Targa image. */
    TGA,
    /** EXIF Profile Thumbnail. */
    THUMBNAIL,
    /** Tagged Image File Format. */
    TIF,
    /** Tagged Image File Format. */
    TIFF,
    /** Tagged Image File Format (64-bit). */
    TIFF64,
    /** Tile image with a texture. */
    TILE,
    /** PSX TIM. */
    TIM,
    /** PS2 TIM2. */
    TM2,
    /** TrueType font collection. */
    TTC,
    /** TrueType font. */
    TTF,
    /** Text. */
    TXT,
    /** Unicode Text format. */
    UBRL,
    /** Unicode Text format 6dot. */
    UBRL6,
    /** X-Motif UIL table. */
    UIL,
    /** 16bit/pixel interleaved YUV. */
    UYVY,
    /** Truevision Targa image. */
    VDA,
    /** VICAR rasterfile format. */
    VICAR,
    /** Visual Image Directory. */
    VID,
    /** Open Web Media. */
    WEBM,
    /** Khoros Visualization image. */
    VIFF,
    /** VIPS image. */
    VIPS,
    /** Truevision Targa image. */
    VST,
    /** WebP Image Format. */
    WEBP,
    /** Wireless Bitmap (level 0) image. */
    WBMP,
    /** Windows Meta File. */
    WMF,
    /** Windows Media Video. */
    WMV,
    /** Word Perfect Graphics. */
    WPG,
    /** Sigma Camera RAW Picture File. */
    X3F,
    /** X Windows system bitmap (black and white). */
    XBM,
    /** Constant image uniform color. */
    XC,
    /** GIMP image. */
    XCF,
    /** X Windows system pixmap (color). */
    XPM,
    /** Microsoft XML Paper Specification. */
    XPS,
    /** Khoros Visualization image. */
    XV,
    /** Raw yellow samples. */
    Y,
    /** The image format and characteristics. */
    YAML,
    /** Raw Y, Cb, and Cr samples. */
    YCBCR,
    /** Raw Y, Cb, Cr, and alpha samples. */
    YCBCRA,
    /**CCIR 601 4:1:1 or 4:2:2. */
    YUV,
}
