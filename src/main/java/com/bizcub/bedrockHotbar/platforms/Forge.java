//? forge {
/*package com.bizcub.bedrockHotbar.platforms;

import com.bizcub.bedrockHotbar.Constants;
import com.bizcub.bedrockHotbar.config.Compat;
import com.bizcub.bedrockHotbar.config.Configs;
/^? >=1.19^/ import net.minecraftforge.client.ConfigScreenHandler;
/^? >=1.18 && <=1.18.2^/ /^import net.minecraftforge.client.ConfigGuiHandler;^/
/^? >=1.17 && <=1.17.1^/ /^import net.minecraftforge.fmlclient.ConfigGuiHandler;^/
/^? <=1.16.5^/ /^import net.minecraftforge.fml.ExtensionPoint;^/
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class Forge {

    public Forge() {
        if (Compat.isModLoaded(Compat.clothConfigId)) {
            Configs.init();

            //? >=1.19 && <=1.21.3 {
            ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> {
                return new ConfigScreenHandler.ConfigScreenFactory((minecraft, screen) -> {
                    return PlatformInit.getScreen(screen);
                });
            });

            //?} >=1.17 && <=1.18.2 {
            /^ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class,
                    () -> new ConfigGuiHandler.ConfigGuiFactory((client, parent) ->
                            PlatformInit.getScreen(parent)
            );

            ^///?} <=1.16.5 {
            /^ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY,
                    () -> (mc, screen) -> PlatformInit.getScreen(screen)
            );^///?}
        }
    }
}*///?}
