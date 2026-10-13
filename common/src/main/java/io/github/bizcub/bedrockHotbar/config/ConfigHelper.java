package io.github.bizcub.bedrockHotbar.config;

import io.github.bizcub.bedrockHotbar.Main;
import io.github.bizcub.simpleConfigLib.autoconfig.gui.ConfigScreens;
import net.minecraft.client.gui.screens.Screen;
/*? fabric*/ import net.fabricmc.loader.api.FabricLoader;
/*? forge*/ //import net.minecraftforge.fml.ModList;
/*? neoforge*/ //import net.neoforged.fml.ModList;

public class ConfigHelper {
    public static boolean isModLoaded(String modId) {
        /*? fabric*/ return FabricLoader.getInstance().isModLoaded(modId);
        /*? (forge && <26.1) || neoforge*/ //return ModList.get().isLoaded(modId);
        /*? forge && >=26.1*/ //return ModList.isLoaded(modId);
    }

    public static boolean isConfigLoaded() {
        return isModLoaded("simple_config_lib");
    }

    public static Screen getScreen(Screen parent) {
        return isConfigLoaded() ? ConfigScreens.open(Main.MOD_ID, parent) : parent;
    }
}
