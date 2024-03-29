plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    compileSdk = 33

    defaultConfig {
        applicationId = "es.dmoral.toastysample"
        minSdk = 21
        targetSdk = 33
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    implementation(libs.appcompat)
    implementation("com.github.xxhong:Toasty:2.0.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(project(mapOf("path" to ":collects")))
//    implementation("com.github.xxhong:CollectsPkg:1.1.5")
}
