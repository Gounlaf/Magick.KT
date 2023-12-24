package imagemagick

typealias QuantumType = Byte
//typealias QuantumType = UShort
//typealias QuantumType = Float


fun Int.toSizeT() = this.toULong()
