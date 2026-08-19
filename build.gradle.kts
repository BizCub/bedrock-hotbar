plugins {
    id("io.github.bizcub.multiloader")
}

multiloader {
    sc.replacements {
        string(scp >= "26.2", "contextual_bar") {
            replace("ContextualBarRenderer", "ContextualBar")
        }
        string(scp >= "26.2") {
            replace("net.minecraft.client.gui.Gui;", "net.minecraft.client.gui.Hud;")
            replace("Gui.class", "Hud.class")
        }
        string(scp >= "26.1") {
            replace("GuiGraphics", "GuiGraphicsExtractor")
        }
        string(scp >= "26.1", "hotbar") {
            replace("renderItemHotbar", "extractItemHotbar")
        }
        string(scp >= "26.1", "render") {
            replace("render", "extract")
        }
        string(scp >= "1.21.11") {
            replace("ResourceLocation", "Identifier")
        }
        string(scp >= "1.21.11" && !isForge, "auto_config") {
            replace("AutoConfig", "AutoConfigClient")
        }
        string(scp >= "1.21.5") {
            replace(".selected", ".getSelectedSlot()")
        }
        string(scp >= "1.20.5", "hotbar") {
            replace("renderHotbar", "renderItemHotbar")
        }
    }

    setMREnvironment(mrEnvs.clientOnly)
    setCFEnvironment(cfEnvs.client)

    versionRange("26.2", to = "latest")
    versionRange("1.21.8", to = "1.21.10")
    versionRange("1.21.3", to = "1.21.4")
    versionRange("1.20.2", to = "1.20.4")

    addDependency(
        dependency = "io.github.bizcub:simple-config-lib:1.0-${mod.loader}+${mod.mc}"
    )
    if (isFabric) addDependency(
        dependency = "maven.modrinth:smooth-scroll:${getDep("smooth-scroll")}"
    )
    val isClothConfigAvailable = !isForge || scp <= "1.21.3"
    addDependency(
        dependency = "me.shedaniel.cloth:cloth-config-${mod.loader}:${getDep("cloth-config").split("+").first()}",
        configuration = if (isClothConfigAvailable) "implementation" else "compileOnly",
        repository = "maven.shedaniel.me",
        isPublishDepEnabled = isClothConfigAvailable,
        publishProjectId = "cloth-config"
    )
    if (!isForge || scp <= "1.20.4") {
        val appleskin = getDep("appleskin").split("+")
        addDependency(
            dependency = "squeek.appleskin:appleskin-${mod.loader}:${appleskin[1]}-${appleskin[0]}",
            repository = "maven.ryanliptak.com",
        )
    }

    if (isFabric) {
        addDependency(
            dependency = "net.fabricmc:fabric-loader:${getDep("fabric")}"
        )
        addDependency(
            dependency = "net.fabricmc.fabric-api:fabric-api:${getDep("fabric-api")}",
            isPublishDepEnabled = true,
            isPublishDepRequired = true
        )
        addDependency(
            dependency = "com.terraformersmc:modmenu:${getDep("modmenu")}",
            repository = "maven.terraformersmc.com/releases",
            isPublishDepEnabled = true
        )
    }
}
