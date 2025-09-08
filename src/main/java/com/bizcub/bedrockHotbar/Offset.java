package com.bizcub.bedrockHotbar;

import com.bizcub.bedrockHotbar.config.Compat;
import com.bizcub.bedrockHotbar.config.Configs;

public class Offset {

    public static int operation(int x) {
        if (Compat.isModLoaded(Constants.CLOTH_CONFIG_ID)) {
            return x -= Configs.getInstance().offset;
        }
        return x -= Constants.DEF_OFFSET;
    }
}
