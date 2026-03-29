import com.bizcub.multiloader.MultiLoader
import dev.kikugie.stonecutter.build.StonecutterBuildExtension
import me.modmuss50.mpp.ModPublishExtension

val stonecutter = project.extensions.getByType(StonecutterBuildExtension::class.java)

project.extensions.configure<MultiLoader>("multiloader") {
    project.afterEvaluate {
        stonecutter.let { sc ->
            sc.replacements {
                string(scp >= "26.1") {
                    replace("GuiGraphics", "GuiGraphicsExtractor")
                }
                string(scp >= "26.1", "hotbar") {
                    replace("renderItemHotbar", "extractItemHotbar")
                }
                string(scp >= "1.21.11") {
                    replace("ResourceLocation", "Identifier")
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

    addRepository("https://maven.shedaniel.me")
    addRepository("https://maven.ryanliptak.com")
    addDependency("me.shedaniel.cloth:cloth-config-${mod.loader}:${getProp("cloth_config")}", "api")
    if (scp < "26.1") addDependency("squeek.appleskin:appleskin-${mod.loader}:${getProp("appleskin")}", "implementation")

    if (isFabric) {
        addRepository("https://maven.terraformersmc.com/releases")

        addDependency("net.fabricmc:fabric-loader:latest.release", "implementation")
        addDependency("com.terraformersmc:modmenu:${getProp("modmenu")}", "api")
    }

    if (isNeoForge) {
        addRepository("https://maven.neoforged.net/releases")
    }

    project.extensions.configure<ModPublishExtension>("publishMods") {
        modrinth {
            if (isClothConfigAvailable) optional("cloth-config")
            if (isAppleSkinAvailable) optional("appleskin")
            if (isFabric) optional("modmenu")
        }
        curseforge {
            if (isClothConfigAvailable) optional("cloth-config")
            if (isAppleSkinAvailable) optional("appleskin")
            if (isFabric) optional("modmenu")
        }
    }
}
