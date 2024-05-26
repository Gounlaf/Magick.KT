import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    kotlin("multiplatform")
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        languageVersion.set(KotlinVersion.KOTLIN_2_0)
        apiVersion.set(KotlinVersion.KOTLIN_2_0)
    }

    explicitApi()

    linuxX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project.dependencies.platform("org.jetbrains.kotlinx:kotlinx-coroutines-bom:_"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
                implementation("org.jetbrains.kotlinx:multik-core:_")
                implementation("com.ionspin.kotlin:bignum:_")

                implementation(project(":magick-kt-common"))

                // THIS ONE SHOULD BE DYNAMIC
                implementation(project(":magick-kt-native-q8"))
            }
        }
    }
}
