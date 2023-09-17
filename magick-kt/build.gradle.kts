import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType
import org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeTest
import java.util.*

plugins {
    @Suppress("UnstableApiUsage")
    embeddedKotlin("multiplatform")
    id("io.kotest.multiplatform")
}

val localProperties = Properties().apply {
    file("${rootProject.projectDir}/local.properties").takeIf { it.isFile }?.let {
        it.inputStream().use { stream -> load(stream) }
    }
}

val localLib = file(localProperties.getProperty("local.lib", "/usr/lib"))
val localInclude = file(localProperties.getProperty("local.include", "/usr/include"))

kotlin {
    linuxX64 {
        compilations.getByName("main") {
            cinterops {
                val libMagickNative by creating {
                    compilerOpts += "-DMAGICKCORE_HDRI_ENABLE=false"

                    compilerOpts += "-I${localInclude.absolutePath}"
                    compilerOpts += "-I${localInclude.absolutePath}/ImageMagick-7"
                }
            }
        }

        binaries {
            executable {
                entryPoint = "main"

                linkerOpts += "-L${localLib.absolutePath}"
                linkerOpts += "-lMagick.Native-Q8-x64.dll"
            }

            findTest(NativeBuildType.DEBUG)?.apply {
                linkerOpts += "-L${localLib.absolutePath}"
                linkerOpts += "-lMagick.Native-Q8-x64.dll"
            }

            findTest(NativeBuildType.RELEASE)?.apply {
                linkerOpts += "-L${localLib.absolutePath}"
                linkerOpts += "-lMagick.Native-Q8-x64.dll"
            }
        }
    }

    sourceSets {
        all {
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
        }

        val commonMain by getting {
            dependencies {
                implementation(platform("org.jetbrains.kotlinx:kotlinx-coroutines-bom:_"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")

                implementation(platform("com.squareup.okio:okio-bom:_"))
                implementation("com.squareup.okio:okio")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(platform("io.kotest:kotest-bom:_"))
                implementation("io.kotest:kotest-assertions-core")
                implementation("io.kotest:kotest-framework-engine")

                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }

            languageSettings.optIn("kotlin.ExperimentalStdlibApi")
            languageSettings.optIn("kotlin.native.runtime.NativeRuntimeApi")
            languageSettings.optIn("kotlin.contracts.ExperimentalContracts")
        }

        val linuxX64Test by getting {
            languageSettings.optIn("kotlin.ExperimentalStdlibApi")
            languageSettings.optIn("kotlin.native.runtime.NativeRuntimeApi")
            languageSettings.optIn("kotlin.contracts.ExperimentalContracts")
        }
    }
}

tasks.withType<KotlinNativeTest>().all {
    environment("LD_LIBRARY_PATH", localLib.absolutePath)
}
