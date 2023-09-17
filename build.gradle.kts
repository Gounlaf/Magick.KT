import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType
import java.util.*

plugins {
    embeddedKotlin("multiplatform")
    id("io.kotest.multiplatform")
}

repositories {
    mavenCentral()
}

val localProperties = Properties().apply {
    file("local.properties").takeIf { it.isFile }?.let {
        it.inputStream().use { stream -> load(stream) }
    }
}

kotlin {
    group = "name.levisflorian"
    version = "1.0-SNAPSHOT"

    val localLib = file(localProperties.getProperty("local.lib", "/usr/lib"))
    val localInclude = file(localProperties.getProperty("local.include", "/usr/include"))

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
        }

        val linuxX64Test by getting {
            languageSettings.optIn("kotlin.ExperimentalStdlibApi")
            languageSettings.optIn("kotlin.native.runtime.NativeRuntimeApi")
        }
    }
}
