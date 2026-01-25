plugins {
    id("multiloader-common")
}

configurations.all {
    resolutionStrategy {
        force("net.fabricmc:fabric-loader:latest.release")
    }
}

if (isForge || isNeoForge) {
    project.extra["mc.snapshot"] = null
}

if (isNeoForge) {
    val neoVers = mod.mc.substring(2)
    val neoLoader = dep("neoforge_loader")
    val neoForge = if (neoVers.contains(".")) "$neoVers.$neoLoader" else "$neoVers.0.$neoLoader"
    project.extra["dep.neoforge_loader"] = neoForge

    when (mod.mc) {
        "1.21.1" -> project.extra["mod.appleskin"] = "mc1.21-3.0.7"
        "1.21.3" -> project.extra["mod.appleskin"] = "mc1.21.3-3.0.7"
        "1.21.5" -> project.extra["mod.appleskin"] = "mc1.21.5-3.0.7"
        "1.21.8" -> project.extra["mod.appleskin"] = "mc1.21.6-3.0.7"
    }
}

if (isForge) {
    if (!isClothConfigAvailable) project.extra["mod.cloth_config"] = "17.0.144"
    if (!isAppleSkinAvailable) project.extra["mod.appleskin"] = "mc1.20.2-2.5.1"

    when (mod.mc) {
        "1.20.6" -> project.extra["pub.start"] = "1.20.6"
        "1.16.5" -> project.extra["mod.appleskin"] = "mc1.16.4-2.5.1"
        "1.20.1" -> project.extra["mod.appleskin"] = "mc1.20.1-2.5.1"
    }
}

base.archivesName.set("${mod.mixin}-${mod.loader}")
version = "${mod.version}+${mod.pub_start}"
