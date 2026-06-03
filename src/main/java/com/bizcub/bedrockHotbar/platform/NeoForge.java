//? neoforge {
/*package com.bizcub.bedrockHotbar.platform;

import com.bizcub.bedrockHotbar.Main;
import com.bizcub.bedrockHotbar.config.Compat;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(Main.MOD_ID)
public class NeoForge {

    public NeoForge() {
        if (Compat.isModLoaded(Compat.clothConfigId)) {
            Main.init();

            ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (container, parent) -> {
                return Compat.getScreen(parent);
            });
        }
    }
}*///?}
