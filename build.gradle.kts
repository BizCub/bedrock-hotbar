import java.util.*

plugins {
    id("dev.architectury.loom")
    id("architectury-plugin")
    id("me.modmuss50.mod-publish-plugin")
    id("com.github.johnrengelman.shadow")
}

val minecraft = stonecutter.current.version
val loader = loom.platform.get().name.lowercase()
val incorrectVersions = arrayOf("1.21.6")

version = "${mod.version}+${mod.mc_start}"
group = mod.group
base {
    archivesName.set("${mod.id}-$loader")
}

architectury.common(stonecutter.tree.branches.mapNotNull {
    if (stonecutter.current.project !in it) null
    else it.prop("loom.platform")
})

repositories {
    maven("https://maven.neoforged.net/releases/")
    maven("https://maven.shedaniel.me/")
    maven("https://maven.terraformersmc.com/releases/")
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraft")
    //mappings(loom.officialMojangMappings())

    if (loader == "fabric") {
        modImplementation("net.fabricmc:fabric-loader:0.16.14")
        mappings("net.fabricmc:yarn:$minecraft+build.${mod.dep("yarn_build")}:v2")
        modApi("me.shedaniel.cloth:cloth-config-fabric:${mod.cloth_config}")
        modApi("com.terraformersmc:modmenu:${mod.modmenu}")
    }
    if (loader == "neoforge") {
        "neoForge"("net.neoforged:neoforge:${mod.dep("neoforge_loader")}")
        mappings(loom.layered {
            mappings("net.fabricmc:yarn:$minecraft+build.${mod.dep("yarn_build")}:v2")
            mod.dep("neoforge_patch").takeUnless { it.startsWith('[') }?.let {
                mappings("dev.architectury:yarn-mappings-patch-neoforge:$it")
            }
        })
        modApi("me.shedaniel.cloth:cloth-config-neoforge:${mod.cloth_config}")
    }
}

loom {
    decompilers {
        get("vineflower").apply { // Adds names to lambdas - useful for mixins
            options.put("mark-corresponding-synthetics", "1")
        }
    }
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}

publishMods {
    file = rootProject.file("build/libs/${mod.version}/" + loader + "/${mod.id}-" + loader + "-${mod.version}+${mod.mc_start}.jar")
    //file = project.tasks.remapJar.get().archiveFile
    displayName = "${mod.name} ${loader.replaceFirstChar { it.uppercase() }} ${property("mod.mc_start")} v${mod.version}"
    changelog = rootProject.file("CHANGELOG.md").readText()
    type = STABLE
    modLoaders.add(loader)
    if (loader == "fabric") modLoaders.add("quilt")

    modrinth {
        projectId = property("mod.modrinth").toString()
        accessToken = providers.environmentVariable("MODRINTH_TOKEN")
        if (loader == "fabric") optional("modmenu", "cloth-config")
        if (loader == "neoforge") optional("cloth-config")
        minecraftVersionRange {
            start = mod.mc_start
            end = mod.mc_end
            includeSnapshots = true
        }
    }

    curseforge {
        projectId = property("mod.curseforge").toString()
        accessToken = providers.environmentVariable("CURSEFORGE_TOKEN")
        if (loader == "fabric") optional("modmenu", "cloth-config")
        if (loader == "neoforge") optional("cloth-config")
        minecraftVersionRange {
            start = mod.mc_start
            end = mod.mc_end
        }
    }

    github {
        accessToken = providers.environmentVariable("GITHUB_TOKEN")
        repository = "BizCub/${mod.github}"
        commitish = "master"
        tagName = "v${mod.version}-" + loader + "+${minecraft}"
    }
}

java {
    withSourcesJar()
    val java = if (stonecutter.eval(minecraft, ">=1.20.5")) JavaVersion.VERSION_21 else JavaVersion.VERSION_17
    targetCompatibility = java
    sourceCompatibility = java
}

val shadowBundle: Configuration by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
}

tasks.shadowJar {
    configurations = listOf(shadowBundle)
    archiveClassifier = "dev-shadow"
}

tasks.remapJar {
    input = tasks.shadowJar.get().archiveFile
    archiveClassifier = null
    dependsOn(tasks.shadowJar)
}

tasks.jar {
    archiveClassifier = "dev"
}

val buildAndCollect = tasks.register<Copy>("buildAndCollect") {
    group = "versioned"
    description = "Must run through 'chiseledBuild'"
    from(tasks.remapJar.get().archiveFile, tasks.remapSourcesJar.get().archiveFile)
    into(rootProject.layout.buildDirectory.file("libs/${mod.version}/$loader"))
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

tasks.processResources {
    properties(
        listOf("fabric.mod.json"),
        "id" to mod.id,
        "name" to mod.name,
        "description" to mod.description,
        "version" to mod.version,
        "modrinth" to mod.modrinth,
        "github" to mod.github
    )
    properties(
        listOf("META-INF/mods.toml", "pack.mcmeta"),
        "id" to mod.id,
        "name" to mod.name,
        "description" to mod.description,
        "version" to mod.version,
        //"minecraft" to mod.prop("mc_dep_forgelike"),
        "modrinth" to mod.modrinth,
        "github" to mod.github
    )
    properties(
        listOf("META-INF/neoforge.mods.toml", "pack.mcmeta"),
        "id" to mod.id,
        "name" to mod.name,
        "description" to mod.description,
        "version" to mod.version,
        //"minecraft" to mod.prop("mc_dep_forgelike"),
        "modrinth" to mod.modrinth,
        "github" to mod.github
    )
}

tasks.build {
    group = "versioned"
    description = "Must run through 'chiseledBuild'"
}
