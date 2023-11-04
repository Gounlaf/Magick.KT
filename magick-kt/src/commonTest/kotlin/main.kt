import imagemagick.helpers.Environment

fun mainNoExit(args: Array<String>) {
    Environment.initialize()

    kotlin.native.internal.test.testLauncherEntryPoint(args)
}
