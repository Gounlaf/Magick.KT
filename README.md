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

The easiest way to make it works is to follow the [FHS](https://en.wikipedia.org/wiki/Filesystem_Hierarchy_Standard).

If you don't follow the `FHS`:

1. you have to set `local.lib` and `local.include` properties from `local.properties.dist`
2. in order to run the tests, you will have to add the value of `local.lib` to the env var `LD_LIBRARY_PATH`
