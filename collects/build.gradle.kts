plugins {
    id("com.android.library")
//    id("com.github.dcendents.android-maven")
    id("maven-publish")
}

//group = 'com.github.GrenderG'

android {
    compileSdk = libs.versions.targetSdk.get().toInt()

    defaultConfig {
        minSdk = 23
        targetSdk = 33
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    implementation(libs.appcompat)
    implementation("androidx.recyclerview:recyclerview:1.2.1")
}


// Because the components are created only during the afterEvaluate phase, you must
// configure your publications using the afterEvaluate() lifecycle method.
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
