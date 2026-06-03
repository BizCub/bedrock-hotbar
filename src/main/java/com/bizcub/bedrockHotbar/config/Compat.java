package com.bizcub.bedrockHotbar.config;

//~ auto_config
import me.shedaniel.autoconfig.AutoConfigClient;
import net.minecraft.client.gui.screens.Screen;
/*? fabric*/ import net.fabricmc.loader.api.FabricLoader;
/*? forge*/ //import net.minecraftforge.fml.ModList;
/*? neoforge*/ //import net.neoforged.fml.ModList;

public class Compat {
    public static final String clothConfigId =
            /*? (fabric && >=1.18) || (forge && <1.17)*/ "cloth-config";
            /*? (forge && >=1.17) || neoforge*/ //"cloth_config";
            /*? fabric && <1.18*/ //"cloth-config2";

    public static boolean isModLoaded(String modId) {
        /*? fabric*/ return FabricLoader.getInstance().isModLoaded(modId);
        /*? (forge && <26.1) || neoforge*/ //return ModList.get().isLoaded(modId);
        /*? forge && >=26.1*/ //return ModList.isLoaded(modId);
    }

    public static boolean isClothConfigLoaded() {
        return isModLoaded(clothConfigId);
    }

    //? is_cloth_config_available {
    public static Screen getScreen(Screen parent) {
        return AutoConfigClient.getConfigScreen(ModClothConfig.class, parent).get();
    }//?}
}
