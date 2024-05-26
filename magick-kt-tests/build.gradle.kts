import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeTest
import java.util.Properties

plugins {
    kotlin("multiplatform")
    id("io.kotest.multiplatform")
    id("com.goncalossilva.resources")
}

val localProperties =
    Properties().apply {
        file("${rootProject.projectDir}/local.properties").takeIf { it.isFile }?.let {
            it.inputStream().use { stream -> load(stream) }
        }
    }

val localLib = file(localProperties.getProperty("local.lib", "/usr/lib"))
val localInclude = file(localProperties.getProperty("local.include", "/usr/include"))

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        languageVersion.set(KotlinVersion.KOTLIN_2_0)
        apiVersion.set(KotlinVersion.KOTLIN_2_0)
    }

    linuxX64 {
        binaries {
            all {
//                freeCompilerArgs += "-Xruntime-logs=gc=info"

                linkerOpts += "-L${localLib.absolutePath}"
                // THIS ONE SHOULD BE DYNAMIC
                linkerOpts += "-lMagick.Native-Q8-x64.dll"
            }
        }
    }

    sourceSets {
        all {
            listOf(
                "kotlin.ExperimentalStdlibApi",
                "kotlin.native.runtime.NativeRuntimeApi",
                "kotlin.experimental.ExperimentalNativeApi",
                "kotlin.contracts.ExperimentalContracts",
                "kotlinx.cinterop.ExperimentalForeignApi",
                "io.kotest.common.ExperimentalKotest",
            ).forEach { languageSettings.optIn(it) }
        }

        val commonTest by getting {
            dependencies {
                // THIS ONE SHOULD BE DYNAMIC
                implementation(project(":magick-kt-native-q8"))
                implementation(project(":magick-kt"))

                implementation(project.dependencies.platform("io.kotest:kotest-bom:_"))
                implementation("io.kotest:kotest-assertions-core")
                implementation("io.kotest:kotest-framework-datatest")
                implementation("io.kotest:kotest-framework-engine")

                implementation("com.goncalossilva:resources:_")

                implementation(kotlin("test"))
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}

tasks.withType<KotlinNativeTest>().all {
    environment("LD_LIBRARY_PATH", localLib.absolutePath)
}
