import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType
import org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeTest
import tasks.GenerateEnumsForResources
import java.util.*

plugins {
//    @Suppress("UnstableApiUsage")
    kotlin("multiplatform") version "1.9.10"
    id("io.kotest.multiplatform")
    id("build-logic")
    id("com.goncalossilva.resources")
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
        compilations.configureEach {
            compilerOptions.configure {
                freeCompilerArgs.add("-Xallocator=custom")
            }
        }

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
            all {
                freeCompilerArgs += "-Xruntime-logs=gc=info"

                linkerOpts += "-L${localLib.absolutePath}"
                linkerOpts += "-lMagick.Native-Q8-x64.dll"
            }

            executable {
                entryPoint = "main"

//                linkerOpts += "-L${localLib.absolutePath}"
//                linkerOpts += "-lMagick.Native-Q8-x64.dll"
            }

//            findTest(NativeBuildType.DEBUG)?.apply {
//                linkerOpts += "-L${localLib.absolutePath}"
//                linkerOpts += "-lMagick.Native-Q8-x64.dll"
//            }

//            findTest(NativeBuildType.RELEASE)?.apply {
//                linkerOpts += "-L${localLib.absolutePath}"
//                linkerOpts += "-lMagick.Native-Q8-x64.dll"
//            }
        }
    }

    val optIns = listOf(
        "kotlin.ExperimentalStdlibApi",
        "kotlin.native.runtime.NativeRuntimeApi",
        "kotlin.experimental.ExperimentalNativeApi",
        "kotlin.contracts.ExperimentalContracts",
        "io.kotest.common.ExperimentalKotest",
    )

    sourceSets {
        all {
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
        }

        val commonMain by getting {
            dependencies {
                implementation(project.dependencies.platform("org.jetbrains.kotlinx:kotlinx-coroutines-bom:_"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")

                implementation(project.dependencies.platform("com.squareup.okio:okio-bom:_"))
                implementation("com.squareup.okio:okio")
                implementation("com.squareup.okio:okio-fakefilesystem")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(project.dependencies.platform("io.kotest:kotest-bom:_"))
                implementation("io.kotest:kotest-assertions-core")
                implementation("io.kotest:kotest-framework-engine")

                implementation("com.goncalossilva:resources:_")

                implementation(kotlin("test"))
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }

            optIns.forEach { languageSettings.optIn(it) }
        }

        val linuxX64Test by getting {
            optIns.forEach { languageSettings.optIn(it) }
        }
    }
}

tasks.withType<KotlinNativeTest>().all {
    environment("LD_LIBRARY_PATH", localLib.absolutePath)
}

// Task dedicated to generate Enums from resources folder
tasks.register<GenerateEnumsForResources>(
    "generateEnumsForResources",
    project,
    File("src/commonTest"),
    "imagemagick",
    "files"
)
