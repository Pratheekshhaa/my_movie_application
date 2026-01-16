buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

// Top-level build.gradle.kts
plugins {
    id("com.android.application") version "9.0.0" apply false
    id("org.jetbrains.kotlin.android") version "2.3.0" apply false
    id("org.jetbrains.kotlin.kapt") version "2.3.0" apply false // Add this
}