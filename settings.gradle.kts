pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.architectury.dev")
        maven("https://maven.minecraftforge.net")
        maven("https://maven.neoforged.net/releases/")
        maven("https://maven.kikugie.dev/snapshots")
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.7.10"
}

stonecutter {
    kotlinController = true
    centralScript = "build.gradle.kts"
    create(rootProject) {
        fun mc(loader: String, vararg versions: String) {
            for (version in versions) version("$version-$loader", version)
        }
        mc("fabric", "1.19.4", "1.16.5", "1.20", "1.20.2", "1.20.5", "1.21", "1.21.2", "1.21.5", "1.21.6")
        mc("neoforge", "1.21", "1.21.2", "1.21.5", "1.21.6")
    }
}

rootProject.name = extra["mod.name"] as String
