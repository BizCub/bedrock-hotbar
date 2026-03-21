plugins {
    id("java")
}

sc.replacements {
    string(scp >= "26.1") {
        replace("GuiGraphics", "GuiGraphicsExtractor")
    }
    string(scp >= "1.21.11") {
        replace("ResourceLocation", "Identifier")
    }
    string(scp >= "1.21.5") {
        replace(".selected", ".getSelectedSlot()")
    }
    string(scp >= "1.20.5") {
        replace("renderHotbar", "renderItemHotbar")
    }
}

if (isNeoForge) {
    val neoVersion = mod.mc.substring(2)
    val neoLoader = getProp("neoforge")
    setProp("neoforge", if (neoVersion.contains(".")) "$neoVersion.$neoLoader" else "$neoVersion.0.$neoLoader")
}

configurations.all {
    resolutionStrategy {
        force("net.fabricmc:fabric-loader:latest.release")
    }
}

base.archivesName.set("${mod.mixin}-${mod.loader}")
version = "${mod.version}+${mod.pub_start}"

project.extra["loom.platform"] = mod.loader

tasks.processResources {
    properties(
        listOf("fabric.mod.json", "META-INF/*.toml"),
        "ModMenu"       to $$"$ModMenu",
        "id"            to mod.id,
        "mixin"         to mod.mixin,
        "name"          to mod.name,
        "description"   to mod.description,
        "version"       to mod.version,
        "modrinth"      to mod.modrinth,
        "github"        to mod.github,
        "author"        to "Bizarre Cube",
        "license"       to "MIT"
    )
}

java {
    val java = when {
        scp >= "26.1"   -> JavaVersion.VERSION_25
        scp >= "1.20.5" -> JavaVersion.VERSION_21
        scp >= "1.18"   -> JavaVersion.VERSION_17
        scp >= "1.17"   -> JavaVersion.VERSION_16
        else            -> JavaVersion.VERSION_1_8
    }
    targetCompatibility = java
    sourceCompatibility = java
}
