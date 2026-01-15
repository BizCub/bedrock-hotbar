pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.kikugie.dev/snapshots")
        maven("https://maven.architectury.dev")
        maven("https://maven.fabricmc.net")
        maven("https://maven.minecraftforge.net")
        maven("https://maven.neoforged.net/releases")
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.8.2"
}

stonecutter {
    create(rootProject) {
        val fb = "fabric"; val fr = "forge"; val nf = "neoforge"
        fun match(version: String, vararg loaders: String) = loaders
            .forEach { version("$version-$it", version) }
        match("1.21.11", fb, nf)
        match("1.21.8", fb, nf)
        match("1.21.5", fb, nf)
        match("1.21.3", fb, nf)
        match("1.21.1", fb, nf)
        match("1.20.5", fb)
        match("1.20.2", fb)
        match("1.20",   fb)
        match("1.19.4", fb)
        match("1.16.5", fb)
    }
}

rootProject.name = extra["mod.name"] as String
