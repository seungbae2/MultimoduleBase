plugins {
    alias(libs.plugins.base.android.feature)
    alias(libs.plugins.base.android.library.compose)
    alias(libs.plugins.base.android.hilt)
}

android {
    namespace = "sb.multimodulebase.feature.search"
}

dependencies {

    implementation(projects.core.data)
    implementation(projects.core.designsystem)
    implementation(projects.core.domain)
    implementation(libs.coil.kt.compose)
    implementation(libs.androidx.browser)
}