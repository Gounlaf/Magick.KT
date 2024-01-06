package imagemagick.colors

import imagemagick.QuantumImpl
import imagemagick.QuantumType
import kotlinx.cinterop.ExperimentalForeignApi

@ExperimentalForeignApi
public typealias HexColorParsingResult = Pair<Boolean, List<QuantumType>>

@ExperimentalForeignApi
@ExperimentalStdlibApi
public object HexColor {
    /**
     * See [Color Model Specification](https://www.imagemagick.org/script/color.php)
     *
     * @param value a color in hexadecimal representation; examples:
     *  #f00 -> #rgb;
     *  #ff0000 -> #rrggbb;
     *  #ff0000ff -> #rrggbbaa;
     *  #ffff00000000 -> #rrrrggggbbbb;
     *  #ffff00000000ffff -> #rrrrggggbbbbaaaa
     */
    public fun tryParse(value: String): HexColorParsingResult {
        if (value.startsWith('#')) {
            return tryParse(value.substring(1))
        }

        if (value.length < 12) {
            return tryParseQ8(value)
        }

        return tryParseQ16(value)
    }

    private fun tryParseQ8(value: String): HexColorParsingResult =
        try {
            when (value.length) {
                // 3: rgb
                // 4: rgba
                3, 4 ->
                    HexColorParsingResult(
                        true,
                        value.chunked(1) {
                            QuantumImpl.convert(it.toString().repeat(2).hexToUByte())
                        }.toList(),
                    )

                // 2: same color for all channels
                // 6: rrggbb
                // 8: rrggbbaa
                2, 6, 8 ->
                    HexColorParsingResult(
                        true,
                        value.chunked(2) {
                            QuantumImpl.convert(it.toString().hexToUByte())
                        }.toList(),
                    )

                else -> HexColorParsingResult(false, emptyList())
            }
        } catch (e: IllegalArgumentException) {
            HexColorParsingResult(false, emptyList())
        }

    private fun tryParseQ16(value: String): HexColorParsingResult =
        try {
            when (value.length) {
                // 12: rrrrggggbbbb
                // 16: rrrrggggbbbbaaaa
                12, 16 ->
                    HexColorParsingResult(
                        true,
                        value.chunked(4) {
                            QuantumImpl.convert(it.toString().hexToUShort())
                        }.toList(),
                    )

                else -> HexColorParsingResult(false, emptyList())
            }
        } catch (e: IllegalArgumentException) {
            HexColorParsingResult(false, emptyList())
        }
}
