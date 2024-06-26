import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.base.android.library)
    alias(libs.plugins.base.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.sb.multimodule.core.network"

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "NEWS_API_KEY", getProperties("NEWS_API_KEY"))
        }
    }
}

fun getProperties(propertyKey: String): String =
    gradleLocalProperties(rootDir, providers).getProperty(propertyKey)

dependencies {
    api(projects.core.common)
    api(projects.core.model)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.sandwich)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.android)

    testImplementation(libs.kotlinx.coroutines.test)
}