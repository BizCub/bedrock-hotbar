import java.util.Properties

plugins {
    id("java")
}

base.archivesName.set("${mod.id}-$loader")
version = "${mod.version}+${mod.pub_start}"

java {
    val java = if (scb.eval(mod.mc, ">=26.1")) JavaVersion.VERSION_25
    else if (scb.eval(mod.mc, ">=1.20.5")) JavaVersion.VERSION_21
    else if (scb.eval(mod.mc, ">=1.18")) JavaVersion.VERSION_17
    else if (scb.eval(mod.mc, ">=1.17")) JavaVersion.VERSION_16
    else JavaVersion.VERSION_1_8
    targetCompatibility = java
    sourceCompatibility = java
}

tasks.processResources {
    properties(
        listOf("fabric.mod.json", "META-INF/mods.toml", "META-INF/neoforge.mods.toml", "pack.mcmeta"),
        "id" to mod.id,
        "mixin" to mod.mixin,
        "name" to mod.name,
        "description" to mod.description,
        "version" to mod.version,
        "modrinth" to mod.modrinth,
        "github" to mod.github,
        "author" to "Bizarre Cube",
        "license" to "MIT"
    )
}

project.extra["loom.platform"] = loader

fun loadExtraDependencies(project: Project, mcVers: String) {
    val customPropsFile = project.rootProject.file("vers/$mcVers.properties")

    if (customPropsFile.exists()) {
        val customProps = Properties().apply {
            customPropsFile.inputStream().use { load(it) }
        }
        customProps.forEach { (key, value) ->
            project.extra[key.toString()] = value
        }
    }
}

loadExtraDependencies(project,mod.mc)
