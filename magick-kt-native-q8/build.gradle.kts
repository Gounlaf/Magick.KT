import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import java.util.Properties

plugins {
    kotlin("multiplatform")
}

val localProperties =
    Properties().apply {
        file("${rootProject.projectDir}/local.properties").takeIf { it.isFile }?.let {
            it.inputStream().use { stream -> load(stream) }
        }
    }

val localInclude = file(localProperties.getProperty("local.include", "/usr/include"))

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        languageVersion.set(KotlinVersion.KOTLIN_2_0)
        apiVersion.set(KotlinVersion.KOTLIN_2_0)
    }

    explicitApiWarning()

    linuxX64 {
        compilations.getByName("main") {
            cinterops {
                val libMagickNative by creating {
                    compilerOpts += "-DMAGICKCORE_HDRI_ENABLE=0"
                    compilerOpts += "-DMAGICKCORE_QUANTUM_DEPTH=8"
                    compilerOpts += "-DMAGICKCORE_CHANNEL_MASK_DEPTH=64"

                    compilerOpts += "-I${localInclude.absolutePath}"
                    compilerOpts += "-I${localInclude.absolutePath}/ImageMagick-7"
                }
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":magick-kt-common"))
                implementation("com.benasher44:uuid:_")
            }
        }
    }
}
