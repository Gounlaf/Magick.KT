rootProject.name = "magick-kt"

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://maven.universablockchain.com/")
        }
        gradlePluginPortal()
    }
}

plugins {
    // See https://splitties.github.io/refreshVersions/
    id("de.fayard.refreshVersions") version "0.60.3"
}

includeBuild("build-logic")

include("magick-kt")
