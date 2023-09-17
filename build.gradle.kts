plugins {
//    embeddedKotlin("multiplatform")
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

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
