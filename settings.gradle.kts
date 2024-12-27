pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        jcenter()
        mavenLocal()
        maven {
            setUrl("https://jitpack.io")
        }
    }
}

rootProject.name = "SplitBill"
include(":app")
