//? fabric {
package com.bizcub.bedrockHotbar.platforms;

import com.bizcub.bedrockHotbar.config.Compat;
import com.bizcub.bedrockHotbar.config.Configs;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.ClientModInitializer;

public class Fabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        if (Compat.isModLoaded(Compat.clothConfigId)) Configs.init();
    }

    public static class ModMenu implements ModMenuApi {

        @Override
        public ConfigScreenFactory<?> getModConfigScreenFactory() {
            return PlatformInit::getScreen;
        }
    }
}//?}
