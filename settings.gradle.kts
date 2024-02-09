pluginManagement {
    plugins {
        kotlin("jvm") version "1.9.22"
    }
}

rootProject.name = "magick-kt"

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    // See https://splitties.github.io/refreshVersions/
    id("de.fayard.refreshVersions") version "0.60.4"
}

include(":magick-kt-common")
include(":magick-kt")
include(":magick-kt-native-q8")

include(":magick-kt-tests")
