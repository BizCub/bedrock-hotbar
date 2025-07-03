package com.bizcub.bedrockHotbar;

import com.bizcub.bedrockHotbar.config.ModConfig;
import net.fabricmc.api.ClientModInitializer;

public class BedrockHotbar implements ClientModInitializer {

    public static final String MOD_ID = "bedrock_hotbar";

    @Override
    public void onInitializeClient() {
        ModConfig.init();
    }
}