plugins {
    alias(libs.plugins.base.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.sb.multimodulebase.core.domain"
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)

    implementation(libs.javax.inject)
}