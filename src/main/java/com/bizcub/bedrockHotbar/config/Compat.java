package com.bizcub.bedrockHotbar.config;

//? if fabric {
import net.fabricmc.loader.api.FabricLoader;
//?} elif neoforge {
/*import net.neoforged.fml.ModList;
*///?}

public class Compat {

    public static boolean isModLoaded(String modId) {
        //? if fabric {
        return FabricLoader.getInstance().isModLoaded(modId);
         //?} neoforge {
        /*return ModList.get().isLoaded(modId);
        *///?}
    }
}
