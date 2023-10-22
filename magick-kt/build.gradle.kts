import org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeTest
import tasks.GenerateEnumsForResources
import java.util.*

plugins {
    kotlin("multiplatform")
    id("io.kotest.multiplatform")
    id("build-logic")
    id("com.goncalossilva.resources")
    id("io.gitlab.arturbosch.detekt")
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
//        compilations.configureEach {
//            compilerOptions.configure {
//                freeCompilerArgs.add("-Xallocator=custom")
//            }
//        }

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
                // TODO Support other Quantum
                linkerOpts += "-lMagick.Native-Q8-x64.dll"
            }

            executable {
                entryPoint = "main"
            }
        }
    }

    val testOptIns = listOf(
        "kotlin.ExperimentalStdlibApi",
        "kotlin.native.runtime.NativeRuntimeApi",
        "kotlin.experimental.ExperimentalNativeApi",
        "kotlin.contracts.ExperimentalContracts",
        "kotlinx.cinterop.ExperimentalForeignApi",
        "io.kotest.common.ExperimentalKotest",
    )

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project.dependencies.platform("org.jetbrains.kotlinx:kotlinx-coroutines-bom:_"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")

                implementation(project.dependencies.platform("com.squareup.okio:okio-bom:_"))
                implementation("com.squareup.okio:okio")
                implementation("com.squareup.okio:okio-fakefilesystem")

                implementation("net.sergeych:mp_stools:_")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(project.dependencies.platform("io.kotest:kotest-bom:_"))
                implementation("io.kotest:kotest-assertions-core")
                implementation("io.kotest:kotest-framework-datatest")
                implementation("io.kotest:kotest-framework-engine")

                implementation("com.goncalossilva:resources:_")

                implementation(kotlin("test"))
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }

            testOptIns.forEach { languageSettings.optIn(it) }
        }

        val linuxX64Test by getting {
            testOptIns.forEach { languageSettings.optIn(it) }
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
