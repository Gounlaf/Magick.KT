plugins {
    kotlin("multiplatform") apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.1"
}

buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

allprojects {
    group = "name.levisflorian"
    version = "1.0-SNAPSHOT"

    apply(plugin = "io.gitlab.arturbosch.detekt")

    detekt {
        buildUponDefaultConfig = true
        source.setFrom(
            "src/commonMain/kotlin",
            "src/commonTest/kotlin",
        )
    }

    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://maven.universablockchain.com/")
        }
    }
}
