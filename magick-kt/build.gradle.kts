plugins {
    kotlin("multiplatform")
}

kotlin {
    explicitApi()

    linuxX64()

    sourceSets {
        all {
            languageSettings.apply {
                languageVersion = "1.9"
                apiVersion = "1.9"
            }
        }

        val commonMain by getting {
            dependencies {
                implementation(project.dependencies.platform("org.jetbrains.kotlinx:kotlinx-coroutines-bom:_"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")

                implementation("com.ionspin.kotlin:bignum:_")

                implementation(project(":magick-kt-common"))

                // THIS ONE SHOULD BE DYNAMIC
                implementation(project(":magick-kt-native-q8"))
            }
        }
    }
}
