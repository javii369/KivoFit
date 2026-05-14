pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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
            from(files("mobile-app/gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "KivoFit"

// El módulo :app apunta a mobile-app/app (la app Compose real)
include(":app")
project(":app").projectDir = file("mobile-app/app")
