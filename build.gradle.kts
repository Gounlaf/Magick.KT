plugins {
    kotlin("multiplatform") apply false
    id("build-logic") apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
}

allprojects {
    group = "name.levisflorian"
    version = "1.0-SNAPSHOT"
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}
