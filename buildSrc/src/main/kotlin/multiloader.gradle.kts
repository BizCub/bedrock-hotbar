plugins {
    id("me.modmuss50.mod-publish-plugin")
    id("dev.kikugie.fletching-table")
    id("com.bizcub.multiloader")
}

multiloader {
    val isClothConfigAvailable = !(isForge && scp > "1.21.3")

    sc.constants["is_cloth_config_available"] = isClothConfigAvailable

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

    addDependency(
        dependency = "me.shedaniel.cloth:cloth-config-${mod.loader}:${getDep("cloth-config").split("+").first()}",
        repository = "maven.shedaniel.me",
        isPublishDepEnabled = true,
        publishProjectId = "cloth-config"
    )
    val appleskin = getDep("appleskin").split("+")
    addDependency(
        dependency = "squeek.appleskin:appleskin-${mod.loader}:${appleskin[1]}-${appleskin[0]}",
        repository = "maven.ryanliptak.com",
        isPublishDepEnabled = true,
        publishProjectId = "appleskin"
    )

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
