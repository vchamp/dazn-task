buildscript {
    extra.apply {
        set("lifecycle_version", "2.6.1")
        set("compose_version", "1.3.3")
        set("hilt_version", "2.44.2")
        set("ktor_version", "2.2.3")
        set("coil_version", "2.2.2")
        set("exoplayer_version", "1.0.0")
    }
}
plugins {
    id("com.android.application") version "7.4.2" apply false
    id("com.android.library") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10" apply false
    id("com.google.dagger.hilt.android") version "2.44.2" apply false
}
