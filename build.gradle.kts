plugins {
    `multiloader-loader`
    id("architectury-plugin") version "3.+"
    id("dev.architectury.loom") version "1.+"
    id("me.modmuss50.mod-publish-plugin") version "1.+"
}

stonecutter {
    swaps["mod_id"] = "\"${prop("mod.id")}\";"
    constants.match(mod.loader, "fabric", "forge", "neoforge")
}

repositories {
    maven("https://maven.neoforged.net/releases")
    maven("https://maven.terraformersmc.com/releases")
    maven("https://maven.ryanliptak.com")
}

dependencies {
    minecraft("com.mojang:minecraft:${mod.propIfExist("mc.snapshot", mod.mc)}")
    mappings(loom.officialMojangMappings())
    modApi("me.shedaniel.cloth:cloth-config-${mod.loader}:${mod.cloth_config}")
    modImplementation("squeek.appleskin:appleskin-${mod.loader}:${mod.appleskin}")

    if (isFabric) {
        modImplementation("net.fabricmc:fabric-loader:latest.release")
        modImplementation("com.terraformersmc:modmenu:${mod.modmenu}")
    }
    if (isForge) {
        "forge"("net.minecraftforge:forge:${mod.mc}-${dep("forge_loader")}")
    }
    if (isNeoForge) {
        "neoForge"("net.neoforged:neoforge:${dep("neoforge_loader")}")
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
    fun tokenDir(token: String) = file("C:\\Tokens\\$token.txt").readText()
    file.set(tasks.remapJar.get().archiveFile)
    displayName = "${mod.name} ${mod.loader.upperCaseFirst()} ${mod.pub_start} v${mod.version}"
    changelog = rootProject.file("CHANGELOG.md").readText()
    version = mod.version
    type = STABLE
    modLoaders.add(mod.loader)
    if (isFabric) modLoaders.add("quilt")

    modrinth {
        projectId = mod.modrinth
        accessToken = tokenDir("modrinth")
        if (isClothConfigAvailable) optional("cloth-config")
        if (isFabric) optional("modmenu")
        optional("appleskin")
        minecraftVersionRange {
            start = mod.pub_start
            end = mod.pub_end
            includeSnapshots = true
        }
    }
    curseforge {
        projectId = mod.curseforge
        accessToken = tokenDir("curseforge")
        if (isClothConfigAvailable) optional("cloth-config")
        if (isFabric) optional("modmenu")
        optional("appleskin")
        minecraftVersionRange {
            start = mod.pub_start
            end = mod.pub_end
        }
    }
    github {
        accessToken = tokenDir("github")
        repository = "BizCub/${mod.github}"
        commitish = "master"
        tagName = "v${mod.version}-${mod.loader}+${mod.pub_start}"
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
