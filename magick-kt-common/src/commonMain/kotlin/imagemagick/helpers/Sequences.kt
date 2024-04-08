package imagemagick.helpers

/**
 * Returns a [Map] where keys are elements from the given sequence and non-null values are
 * produced by the [valueSelector] function applied to each element.
 *
 * If any two elements are equal, the last one gets added to the map.
 *
 * The returned map preserves the entry iteration order of the original sequence.
 *
 * The operation is _terminal_.
 *
 * @see associateWith
 */
public inline fun <K, V> Sequence<K>.associateWithNotNull(valueSelector: (K) -> V): Map<K, V & Any> {
    val result = LinkedHashMap<K, V & Any>()
    return associateWithToNotNull(result, valueSelector)
}

/**
 * Populates and returns the [destination] mutable map with key-value pairs for each element of the given sequence,
 * where key is the element itself and value is provided by the [valueSelector] function applied to that key.
 * Only non-null values are preserved.
 *
 * If any two elements are equal, the last one overwrites the former value in the map.
 *
 * The operation is _terminal_.
 *
 * @see associateWithTo
 */
public inline fun <K, V, M : MutableMap<in K, in V & Any>> Sequence<K>.associateWithToNotNull(
    destination: M,
    valueSelector: (K) -> V,
): M {
    for (element in this) {
        valueSelector(element)?.let { destination.put(element, it) }
    }
    return destination
}
