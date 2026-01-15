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

if (isForge) {
    if (!isClothConfigAvailable) {
        project.extra["mod.cloth_config"] = "17.0.144"
    }
}

if (isNeoForge) {
    val neoVers = mod.mc.substring(2)
    val neoLoader = mod.dep("neoforge_loader")
    val neoForge = if (neoVers.contains(".")) "$neoVers.$neoLoader" else "$neoVers.0.$neoLoader"
    project.setProperty("deps.neoforge_loader", neoForge)

    when (mod.mc) {
        "1.21.1" -> project.extra["mod.appleskin"] = "mc1.21-3.0.7"
        "1.21.3" -> project.extra["mod.appleskin"] = "mc1.21.3-3.0.7"
        "1.21.5" -> project.extra["mod.appleskin"] = "mc1.21.5-3.0.7"
        "1.21.8" -> project.extra["mod.appleskin"] = "mc1.21.6-3.0.7"
    }
}
