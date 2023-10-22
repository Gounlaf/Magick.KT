# Magick.KT

## WHAT?

[Magick.NET](https://github.com/dlemstra/Magick.NET) is awesome ❤️.

Magick.KT humbly try to port it to the JVM with the power of [Kotlin Native](https://kotlinlang.org/docs/native-c-interop.html).

## TODO

Everything...

[ ] Support Quantum
[ ] Support library variations

## HOW

You need to have [Magick.Native](https://github.com/dlemstra/Magick.Native) library and headers installed,
plus headers underlying of [ImageMagick](https://github.com/ImageMagick/ImageMagick).

The easiest way to make it work is to follow the [FHS](https://en.wikipedia.org/wiki/Filesystem_Hierarchy_Standard).

If you don't follow the `FHS`:

1. you have to set `local.lib` and `local.include` properties from `local.properties.dist`
2. in order to run the tests, you will have to add the value of `local.lib` to the env var `LD_LIBRARY_PATH`

## Notes & thoughts

### Enums

All enum constants are in UPPERCASE format.

### Nullable

This project relies on kotlin nullable safety; in other words, all checks regarding nullable are done in the K Way.

### Preconditions/Checks

There is no built-in equivalent of `C# ArgumentException`.

[Kotlin's stdlib Preconditions](https://github.com/JetBrains/kotlin/blob/v1.9.10/libraries/stdlib/src/kotlin/util/Preconditions.kt) is used for the moment.

### Tests

Tests are mostly mirrored from [Magick.NET](https://github.com/dlemstra/Magick.NET), using the same resources (images).

Tests are done with [Kotest](https://kotest.io)

#### Caveats

- [it can't be configured with system properties](https://github.com/kotest/kotest/blob/402597ca4bf581f10df2f3d062a2427e0de2d005/kotest-framework/kotest-framework-engine/src/commonMain/kotlin/io/kotest/engine/config/ConfigManager.kt#L37)

## Progress

... Unable to maintain a coherent list.

Tests might show the progress.
