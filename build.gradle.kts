plugins {
    kotlin("multiplatform") apply false
    id("org.jmailen.kotlinter") version "4.1.1" apply false
    kotlin("jvm")
}

allprojects {
    group = "name.levisflorian"
    version = "1.0-SNAPSHOT"
}

subprojects {
    apply(plugin = "org.jmailen.kotlinter")
}
