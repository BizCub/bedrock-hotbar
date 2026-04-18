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
    addDependency("api", "me.shedaniel.cloth:cloth-config-${mod.loader}:${getDep("cloth-config")?.split("+")?.first()}")
    val appleskin = getDep("appleskin").split("+")
    addDependency("implementation", "squeek.appleskin:appleskin-${mod.loader}:${appleskin[1]}-${appleskin[0]}")

    if (isFabric) {
        addRepository("https://maven.terraformersmc.com/releases")

        addDependency("implementation", "net.fabricmc:fabric-loader:${getDep("fabric")}")
        addDependency("implementation", "net.fabricmc.fabric-api:fabric-api:${getDep("fabric-api")}")
        addDependency("api", "com.terraformersmc:modmenu:${getDep("modmenu")}")
    }

    if (isNeoForge) {
        addRepository("https://maven.neoforged.net/releases")
    }

    project.extensions.configure<ModPublishExtension>("publishMods") {
        modrinth {
            if (isClothConfigAvailable) optional("cloth-config")
            if (isAppleSkinAvailable) optional("appleskin")
            if (isFabric) requires("fabric-api")
            if (isFabric) optional("modmenu")
        }
        curseforge {
            if (isClothConfigAvailable) optional("cloth-config")
            if (isAppleSkinAvailable) optional("appleskin")
            if (isFabric) requires("fabric-api")
            if (isFabric) optional("modmenu")
        }
    }
}
