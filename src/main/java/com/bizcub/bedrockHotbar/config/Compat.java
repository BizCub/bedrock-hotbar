package com.bizcub.bedrockHotbar.config;

/*? fabric*/ import net.fabricmc.loader.api.FabricLoader;
/*? forge*/ //import net.minecraftforge.fml.ModList;
/*? neoforge*/ //import net.neoforged.fml.ModList;

public class Compat {
    /*? (fabric && >=1.18) || (forge && <1.17)*/ public static final String clothConfigId = "cloth-config";
    /*? (forge && >=1.17) || neoforge*/ //public static final String clothConfigId = "cloth_config";
    /*? fabric && <1.18*/ //public static final String clothConfigId = "cloth-config2";

    public static boolean isModLoaded(String modId) {
        /*? fabric*/ return FabricLoader.getInstance().isModLoaded(modId);
        /*? forge || neoforge*/ //return ModList.get().isLoaded(modId);
    }
}
