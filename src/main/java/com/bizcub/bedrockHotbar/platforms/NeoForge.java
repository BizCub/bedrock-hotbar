//? neoforge {
/*package com.bizcub.bedrockHotbar.platforms;

import com.bizcub.bedrockHotbar.BedrockHotbar;
import com.bizcub.bedrockHotbar.config.Compat;
import com.bizcub.bedrockHotbar.config.Configs;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(BedrockHotbar.MOD_ID)
public class NeoForge {

    public NeoForge() {
        if (Compat.isModLoaded(Compat.clothConfigId)) {
            Configs.init();

            ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (container, parent) -> {
                return PlatformInit.getScreen(parent);
            });
        }
    }
}*///?}
