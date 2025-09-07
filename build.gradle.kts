plugins {
    id("dev.architectury.loom") version "1.11-SNAPSHOT"
    id("architectury-plugin") version "3.4-SNAPSHOT"
    id("me.modmuss50.mod-publish-plugin") version "0.8.4"
}

val minecraft = stonecutter.current.version
val loader = loom.platform.get().name.lowercase()
val mixinId = mod.id.replace("_", "-")

var mcStart = findProperty("mod.mc_start").toString()
if (mcStart == "null") mcStart = minecraft
var mcEnd = findProperty("mod.mc_end").toString()
if (mcEnd == "null") mcEnd = minecraft
var neoPatch = findProperty("deps.neoforge_patch").toString()
if (neoPatch == "null") neoPatch = "1.21+build.4"

base.archivesName.set("$mixinId-$loader")
version = "${mod.version}+$mcStart"

architectury.common(stonecutter.tree.branches.mapNotNull {
    if (stonecutter.current.project !in it) null
    else prop("loom.platform")
})

stonecutter {
    swaps["mod_id"] = "\"${prop("mod.id")}\""
    constants.match(loader, "fabric", "forge", "neoforge")
    java {
        val java = if (stonecutter.eval(minecraft, ">=1.20.5")) JavaVersion.VERSION_21
        else if (stonecutter.eval(minecraft, ">=1.18")) JavaVersion.VERSION_17
        else if (stonecutter.eval(minecraft, ">=1.17")) JavaVersion.VERSION_16
        else JavaVersion.VERSION_1_8
        targetCompatibility = java
        sourceCompatibility = java
    }
}

loom {
    decompilers {
        get("vineflower").apply { // Adds names to lambdas - useful for mixins
            options.put("mark-corresponding-synthetics", "1")
        }
    }
    if (loader == "forge") forge.mixinConfigs("$mixinId.mixins.json")
}

repositories {
    maven("https://maven.neoforged.net/releases/")
    maven("https://maven.shedaniel.me/")
    maven("https://maven.terraformersmc.com/releases/")
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraft")

    if (loader == "fabric") {
        modImplementation("net.fabricmc:fabric-loader:0.17.2")
        mappings("net.fabricmc:yarn:$minecraft+build.${mod.dep("yarn_build")}:v2")
        modApi("me.shedaniel.cloth:cloth-config-fabric:${mod.cloth_config}")
        modApi("com.terraformersmc:modmenu:${mod.modmenu}")
    }
    if (loader == "neoforge") {
        val neoVers = minecraft.substring(2)
        val neoLoader = mod.dep("neoforge_loader")
        "neoForge"("net.neoforged:neoforge:${if (neoVers.contains(".")) "$neoVers.$neoLoader" else "$neoVers.0.$neoLoader"}")
        mappings(loom.layered {
            mappings("net.fabricmc:yarn:$minecraft+build.${mod.dep("yarn_build")}:v2")
            mappings("dev.architectury:yarn-mappings-patch-neoforge:$neoPatch")
        })
        modApi("me.shedaniel.cloth:cloth-config-neoforge:${mod.cloth_config}")
    }
}

tasks.processResources {
    properties(
        listOf("fabric.mod.json", "META-INF/mods.toml", "META-INF/neoforge.mods.toml", "pack.mcmeta"),
        "mixin" to mixinId,
        "id" to mod.id,
        "name" to mod.name,
        "description" to mod.description,
        "version" to mod.version,
        "modrinth" to mod.modrinth,
        "github" to mod.github,
        "author" to "Bizarre Cube",
        "license" to "MIT"
    )
}

val buildAndCollect = tasks.register<Copy>("buildAndCollect") {
    group = "build"
    from(tasks.remapJar.get().archiveFile)
    into(rootProject.layout.buildDirectory.file("libs/${mod.version}"))
    dependsOn("build")
}

if (stonecutter.current.isActive) {
    rootProject.tasks.register("buildActive") {
        group = "project"
        dependsOn(buildAndCollect)
    }

    rootProject.tasks.register("runActive") {
        group = "project"
        dependsOn(tasks.named("runClient"))
    }
}

publishMods {
    file = tasks.remapJar.get().archiveFile
    displayName = "${mod.name} ${loader.upperCaseFirst()} $mcStart v${mod.version}"
    changelog = rootProject.file("CHANGELOG.md").readText()
    version = mod.version
    type = STABLE
    modLoaders.add(loader)
    if (loader == "fabric") modLoaders.add("quilt")

    modrinth {
        projectId = mod.modrinth
        accessToken = file("C:\\Tokens\\modrinth.txt").readText()
        if (loader == "fabric") optional("modmenu")
        optional("cloth-config")
        minecraftVersionRange {
            start = mcStart
            end = mcEnd
            includeSnapshots = true
        }
    }

    curseforge {
        projectId = mod.curseforge
        accessToken = file("C:\\Tokens\\curseforge.txt").readText()
        if (loader == "fabric") optional("modmenu")
        optional("cloth-config")
        minecraftVersionRange {
            start = mcStart
            end = mcEnd
        }
    }

    github {
        accessToken = file("C:\\Tokens\\github.txt").readText()
        repository = "BizCub/${mod.github}"
        commitish = "master"
        tagName = "v${mod.version}-$loader+$mcStart"
    }
}
