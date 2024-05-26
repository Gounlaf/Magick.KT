import de.fayard.refreshVersions.core.StabilityLevel

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
    id("de.fayard.refreshVersions") version "0.60.5"
}

refreshVersions {
    rejectVersionIf {
        candidate.stabilityLevel.isLessStableThan(StabilityLevel.Beta)
    }
}

include(":magick-kt-common")
include(":magick-kt")

include(":magick-kt-native-q8")
include(":magick-kt-tests")
