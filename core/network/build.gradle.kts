plugins {
    alias(libs.plugins.base.android.library)
    alias(libs.plugins.base.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.sb.multimodule.core.network"
}

dependencies {

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