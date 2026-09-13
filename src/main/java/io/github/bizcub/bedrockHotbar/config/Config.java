package io.github.bizcub.bedrockHotbar.config;

import io.github.bizcub.bedrockHotbar.Main;
import io.github.bizcub.simpleConfigLib.autoconfig.ConfigProvider;

public interface Config {
    static Config get() {
        return ConfigProvider.get(Config.class);
    }
    static void set(Config instance) {
        ConfigProvider.set(Config.class, instance);
    }

    default int offset() {
        return Main.DEF_OFFSET;
    }

    default boolean renderTexture() {
        return true;
    }

    default XpLevelMode xpLevelMode() {
        return XpLevelMode.SHADOW;
    }

    default boolean chatOffset() {
        return false;
    }
}
