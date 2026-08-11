package io.github.bizcub.bedrockHotbar.config;

import io.github.bizcub.bedrockHotbar.Main;

public interface Config {
    static Config get() {
        return Holder.INSTANCE;
    }

    static void set(final Config config) {
        if (config != null) {
            Holder.INSTANCE = config;
        }
    }

    class Holder {
        private static Config INSTANCE = new Config() { };
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
