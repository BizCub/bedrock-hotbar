plugins {
    id("multiloader-common")
    id("me.modmuss50.mod-publish-plugin")
}

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

reps.clear()
reps.add(Repository("https://maven.shedaniel.me"))
reps.add(Repository("https://maven.ryanliptak.com"))

deps.clear()
deps.add(Dependency("me.shedaniel.cloth:cloth-config-${mod.loader}:${getProp("cloth_config")}", "api"))
if (scp < "26.1") deps.add(Dependency("squeek.appleskin:appleskin-${mod.loader}:${getProp("appleskin")}", "implementation"))

if (isFabric) {
    reps.add(Repository("https://maven.terraformersmc.com/releases"))

    deps.add(Dependency("net.fabricmc:fabric-loader:latest.release", "implementation"))
    deps.add(Dependency("com.terraformersmc:modmenu:${getProp("modmenu")}", "api"))
}

if (isNeoForge) {
    reps.add(Repository("https://maven.neoforged.net/releases"))
}

publishMods {
    modrinth {
        //if (isClothConfigAvailable) optional("cloth-config")
        //if (isFabric) optional("modmenu")
    }
    curseforge {
        //if (isClothConfigAvailable) optional("cloth-config")
        //if (isFabric) optional("modmenu")
    }
}
