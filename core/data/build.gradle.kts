plugins {
    alias(libs.plugins.base.android.library)
    alias(libs.plugins.base.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.sb.multimodulebase.core.data"
}

dependencies {
}