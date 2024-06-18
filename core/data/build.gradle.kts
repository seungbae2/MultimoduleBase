plugins {
    alias(libs.plugins.base.android.library)
    alias(libs.plugins.base.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.sb.multimodulebase.core.data"
}

dependencies {
    api(projects.core.common)
    api(projects.core.datastore)
    api(projects.core.model)
    api(projects.core.network)

    implementation(libs.sandwich)
    implementation(libs.kotlinx.serialization.json)

    api(libs.androidx.paging.runtime)
    api(libs.androidx.paging.compose)
}