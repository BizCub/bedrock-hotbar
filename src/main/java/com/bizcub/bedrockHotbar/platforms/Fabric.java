//? fabric {
package com.bizcub.bedrockHotbar.platforms;

import com.bizcub.bedrockHotbar.config.Compat;
import com.bizcub.bedrockHotbar.config.Configs;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.ClientModInitializer;

public class Fabric implements ClientModInitializer, ModMenuApi {

    @Override
    public void onInitializeClient() {
        if (Compat.isModLoaded(Compat.clothConfigId)) Configs.init();
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return PlatformInit::getScreen;
    }
}//?}
