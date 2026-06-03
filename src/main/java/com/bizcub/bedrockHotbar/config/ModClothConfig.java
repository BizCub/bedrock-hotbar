package com.bizcub.bedrockHotbar.config;

import com.bizcub.bedrockHotbar.Main;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

@Config(name = Main.MOD_ID)
public class ModClothConfig implements ModConfig, ConfigData {

    @ConfigEntry.BoundedDiscrete(min = Main.MIN_OFFSET, max = Main.MAX_OFFSET)
    public int offset = ModConfig.super.offset();

    //? >=1.20.2 {
    @ConfigEntry.Gui.Tooltip
    public boolean renderTexture = ModConfig.super.renderTexture();//?}

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public XpLevelMode xpLevelMode = ModConfig.super.xpLevelMode();

    public boolean chatOffset = ModConfig.super.chatOffset();

    public enum XpLevelMode {
        Shadow("text.bedrock_hotbar.option.xpLevelMode.shadow"),
        Outline("text.bedrock_hotbar.option.xpLevelMode.outline");

        private final String key;

        XpLevelMode(String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return this.key;
        }
    }

    public static ModClothConfig getInstance() {
        return AutoConfig.register(ModClothConfig.class, GsonConfigSerializer::new).getConfig();
    }
}
