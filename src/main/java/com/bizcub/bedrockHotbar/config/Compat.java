package com.bizcub.bedrockHotbar.config;

/*? fabric*/ import net.fabricmc.loader.api.FabricLoader;
/*? neoforge*/ /*import net.neoforged.fml.ModList;*/

public class Compat {

    public static boolean isModLoaded(String modId) {
        /*? fabric*/ return FabricLoader.getInstance().isModLoaded(modId);
        /*? neoforge*/ /*return ModList.get().isLoaded(modId);*/
    }
}
