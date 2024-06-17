plugins {
    alias(libs.plugins.base.android.library)
    alias(libs.plugins.base.android.library.compose)
}

android {
    namespace = "com.sb.multimodulebase.core.designsystem"
}

dependencies {
    api(libs.androidx.core.ktx)

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)

    debugApi(libs.androidx.compose.ui.tooling)

    implementation(libs.coil.kt.compose)
    implementation(libs.coil.kt.gif)

    testImplementation(libs.androidx.compose.ui.test)
    testImplementation(libs.hilt.android.testing)

    androidTestImplementation(libs.androidx.compose.ui.test)
}