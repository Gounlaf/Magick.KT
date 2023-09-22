fun mainNoExit(args: Array<String>) {
    Platform.isMemoryLeakCheckerActive = true
    Platform.isMemoryLeakCheckerActive = true
    kotlin.native.internal.test.testLauncherEntryPoint(args)
    // DefaultDispatcher.shutdown() might also be required,
    // see https://github.com/Kotlin/kotlinx.coroutines/issues/2491
}
