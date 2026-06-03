import com.bizcub.multiloader.MultiLoader
import dev.kikugie.stonecutter.build.StonecutterBuildExtension

apply(plugin = "dev.kikugie.fletching-table")

val stonecutter = project.extensions.getByType(StonecutterBuildExtension::class.java)

project.extensions.configure<MultiLoader>("multiloader") {
    access()

    project.afterEvaluate {
        stonecutter.let { sc ->
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
        }
    }

    addDependency("maven.shedaniel.me", "api", "me.shedaniel.cloth:cloth-config-${mod.loader}:${getDep("cloth-config")?.split("+")?.first()}")
    val appleskin = getDep("appleskin").split("+")
    addDependency("maven.ryanliptak.com", "implementation", "squeek.appleskin:appleskin-${mod.loader}:${appleskin[1]}-${appleskin[0]}")

    if (isFabric) {
        addDependency("implementation", "net.fabricmc:fabric-loader:${getDep("fabric")}")
        addDependency("implementation", "net.fabricmc.fabric-api:fabric-api:${getDep("fabric-api")}")
        addDependency("maven.terraformersmc.com/releases", "api", "com.terraformersmc:modmenu:${getDep("modmenu")}")
    }

    if (isClothConfigAvailable) addPublishDep("optional", "cloth-config")
    if (isAppleSkinAvailable) addPublishDep("optional", "appleskin")
    if (isFabric) addPublishDep("requires", "fabric-api")
    if (isFabric) addPublishDep("optional", "modmenu")
}
