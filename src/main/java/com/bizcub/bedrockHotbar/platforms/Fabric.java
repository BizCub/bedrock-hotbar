//? if fabric {
package com.bizcub.bedrockHotbar.platforms;

import com.bizcub.bedrockHotbar.Constants;
import com.bizcub.bedrockHotbar.config.Compat;
import com.bizcub.bedrockHotbar.config.Configs;
import net.fabricmc.api.ClientModInitializer;

public class Fabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        if (Compat.isModLoaded(Constants.MOD_MENU_ID)) {
            Configs.init();
        }
    }
}
//?}
