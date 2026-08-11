//? neoforge {
/*package io.github.bizcub.bedrockHotbar.platform;

import io.github.bizcub.bedrockHotbar.Main;
import io.github.bizcub.bedrockHotbar.config.Compat;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(Main.MOD_ID)
public class NeoForge {

    public NeoForge() {
        if (Compat.isClothConfigLoaded()) {
            Main.init();

            ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (container, parent) -> {
                return Compat.getScreen(parent);
            });
        }
    }
}*///?}
