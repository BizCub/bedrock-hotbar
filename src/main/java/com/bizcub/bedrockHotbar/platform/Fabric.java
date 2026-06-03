//? fabric {
package com.bizcub.bedrockHotbar.platform;

import com.bizcub.bedrockHotbar.Main;
import com.bizcub.bedrockHotbar.config.Compat;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.ClientModInitializer;

public class Fabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        if (Compat.isClothConfigLoaded()) Main.init();
    }

    public static class ModMenu implements ModMenuApi {

        @Override
        public ConfigScreenFactory<?> getModConfigScreenFactory() {
            return Compat::getScreen;
        }
    }
}//?}
