package com.bizcub.bedrockHotbar;

import com.bizcub.bedrockHotbar.config.Compat;
import com.bizcub.bedrockHotbar.config.Configs;

public class BedrockHotbar {
    public static final String MOD_ID = /*$ mod_id*/ "bedrock_hotbar";

    public static final int DEF_OFFSET = 4;
    public static final int MIN_OFFSET = 0;
    public static final int MAX_OFFSET = 100;

    public static int operation(int x) {
        if (Compat.isModLoaded(Compat.clothConfigId))
            return x -= Configs.getInstance().offset;
        return x -= DEF_OFFSET;
    }
}
