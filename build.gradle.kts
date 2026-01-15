plugins {
    `multiloader-loader`
    id("dev.architectury.loom") version "1.+"
    id("architectury-plugin") version "3.+"
    id("me.modmuss50.mod-publish-plugin") version "1.+"
}

stonecutter {
    swaps["mod_id"] = "\"${prop("mod.id")}\";"
    constants.match(loader, "fabric", "forge", "neoforge")
    constants["is_cloth_config_available"] = isClothConfigAvailable
}

repositories {
    maven("https://maven.neoforged.net/releases")
    maven("https://maven.terraformersmc.com/releases")
}

dependencies {
    minecraft("com.mojang:minecraft:${mod.propIfExist("mc.snapshot", mod.mc)}")
    mappings(loom.officialMojangMappings())
    modApi("me.shedaniel.cloth:cloth-config-$loader:${mod.cloth_config}")

    if (isFabric) {
        modImplementation("net.fabricmc:fabric-loader:latest.release")
        modImplementation("com.terraformersmc:modmenu:${mod.modmenu}")
    }
    if (isForge) {
        "forge"("net.minecraftforge:forge:${mod.mc}-${mod.dep("forge_loader")}")
    }
    if (isNeoForge) {
        "neoForge"("net.neoforged:neoforge:${mod.dep("neoforge_loader")}")
    }
}

loom {
    decompilers {
        get("vineflower").apply {
            options.put("mark-corresponding-synthetics", "1")
        }
    }
    if (isForge) forge.mixinConfigs("${mod.mixin}.mixins.json")

    runConfigs.all {
        runDir = "../../run"
    }
}

publishMods {
    fun tokenDir(tokenName: String) = file("C:\\Tokens\\$tokenName.txt").readText()
    file.set(tasks.remapJar.get().archiveFile)
    displayName = "${mod.name} ${loader.upperCaseFirst()} ${mod.pub_start} v${mod.version}"
    changelog = rootProject.file("CHANGELOG.md").readText()
    version = mod.version
    type = STABLE
    modLoaders.add(loader)
    if (isFabric) modLoaders.add("quilt")

    modrinth {
        projectId = mod.modrinth
        accessToken = tokenDir("modrinth")
        minecraftVersionRange {
            start = mod.pub_start
            end = mod.pub_end
            includeSnapshots = true
        }
    }
    curseforge {
        projectId = mod.curseforge
        accessToken = tokenDir("curseforge")
        minecraftVersionRange {
            start = mod.pub_start
            end = mod.pub_end
        }
    }
    github {
        accessToken = tokenDir("github")
        repository = "BizCub/${mod.github}"
        commitish = "master"
        tagName = "v${mod.version}-$loader+${mod.pub_start}"
    }
}

val buildAndCollect = tasks.register<Copy>("buildAndCollect") {
    group = "build"
    from(tasks.remapJar.get().archiveFile)
    into(rootProject.layout.buildDirectory.file("libs/${mod.version}"))
    dependsOn("build")
}

if (stonecutter.current.isActive) {
    rootProject.tasks.register("buildActive") {
        dependsOn(buildAndCollect)
    }
    rootProject.tasks.register("runActive") {
        dependsOn(tasks.named("runClient"))
    }
}
