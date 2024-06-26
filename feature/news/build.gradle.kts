plugins {
    alias(libs.plugins.base.android.feature)
    alias(libs.plugins.base.android.library.compose)
    alias(libs.plugins.base.android.hilt)
}

android {
    namespace = "com.sb.multimodulebase.feature.news"

}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.designsystem)
    implementation(projects.core.domain)
    implementation(libs.coil.kt.compose)
}