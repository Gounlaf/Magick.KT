plugins {
    kotlin("multiplatform") apply false
    id("org.jmailen.kotlinter") apply false
    kotlin("jvm")
}

allprojects {
    group = "name.levisflorian"
    version = "1.0-SNAPSHOT"
}

subprojects {
    apply(plugin = "org.jmailen.kotlinter")
}
