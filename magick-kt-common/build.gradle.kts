plugins {
    kotlin("multiplatform")
}

kotlin {
    explicitApiWarning()

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

                implementation(project.dependencies.platform("com.squareup.okio:okio-bom:_"))
                implementation("com.squareup.okio:okio")
                implementation("com.squareup.okio:okio-fakefilesystem")
            }
        }
    }
}
