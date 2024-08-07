plugins {
    id("com.android.library")
    id("maven-publish")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 34
    namespace = "com.particle.baseutils"
    defaultConfig {
        minSdk = 23
        targetSdk = 34
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    implementation(libs.appcompat)
    implementation(libs.androidx.recyclerview.v121)

    implementation(libs.gson)
    compileOnly(libs.material.v1120)
}


afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            val release by publications.registering(MavenPublication::class) {
                from(components["release"])
                artifactId = "base-service"
                groupId = "network.particle"
                version = "1.0.1"
            }
        }
    }
}
