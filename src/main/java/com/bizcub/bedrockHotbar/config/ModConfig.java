package com.bizcub.bedrockHotbar.config;

import com.bizcub.bedrockHotbar.Main;

public interface ModConfig {
    ModConfig CONFIG = Compat.isClothConfigLoaded() ? ModClothConfig.getInstance() : new ModConfig() { };

    default int offset() {
        return Main.DEF_OFFSET;
    }

    default boolean renderTexture() {
        return true;
    }

    default ModClothConfig.XpLevelMode xpLevelMode() {
        return ModClothConfig.XpLevelMode.Shadow;
    }

    default boolean chatOffset() {
        return false;
    }
}
