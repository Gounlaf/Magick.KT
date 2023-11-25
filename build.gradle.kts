plugins {
    kotlin("multiplatform") apply false
    id("org.jmailen.kotlinter") version "4.1.0" apply false
}

allprojects {
    group = "name.levisflorian"
    version = "1.0-SNAPSHOT"
}

subprojects {
    apply(plugin = "org.jmailen.kotlinter")
}
