plugins {
    kotlin("multiplatform") apply false
    id("org.jmailen.kotlinter") apply false
    id("org.jetbrains.kotlinx.binary-compatibility-validator")
    kotlin("jvm")
//    id("org.jetbrains.dokka")
}

allprojects {
    group = "name.levisflorian"
    version = "1.0-SNAPSHOT"
}

subprojects {
    apply(plugin = "org.jmailen.kotlinter")
//    apply(plugin = "org.jetbrains.dokka")
}


apiValidation {
    @OptIn(kotlinx.validation.ExperimentalBCVApi::class)
    klib {
        enabled = true
    }
}
