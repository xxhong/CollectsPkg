pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }

    //https://mp.weixin.qq.com/s/TeA2-4m0fNj0_xghs8JC0Q
    enableFeaturePreview("VERSION_CATALOGS")
    versionCatalogs {
        create("libs")
        {
            from(files("libs.versions.toml"))
        }
    }
}

rootProject.name = "CollectsPkg"
include(":app")
include(":collects")


