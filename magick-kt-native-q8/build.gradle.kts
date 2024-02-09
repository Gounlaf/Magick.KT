import java.util.*

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
        all {
            languageSettings.apply {
                languageVersion = "1.9"
                apiVersion = "1.9"
            }
        }

        val commonMain by getting {
            dependencies {
                implementation(project(":magick-kt-common"))
            }
        }
    }
}
