pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            from(files("./gradle/libraries.versions.toml"))
        }
    }
}
rootProject.name = "GifApp"
enableFeaturePreview("VERSION_CATALOGS")

include(":app")
include(":data")
