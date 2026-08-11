package io.github.bizcub.bedrockHotbar.config;

import io.github.bizcub.bedrockHotbar.Main;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

@me.shedaniel.autoconfig.annotation.Config(name = Main.MOD_ID)
public class ClothConfig implements Config, ConfigData {

    public static ClothConfig getInstance() {
        return AutoConfig.getConfigHolder(ClothConfig.class).getConfig();
    }

    public static void init() {
        AutoConfig.register(ClothConfig.class, GsonConfigSerializer::new);
    }

    @ConfigEntry.BoundedDiscrete(min = Main.MIN_OFFSET, max = Main.MAX_OFFSET)
    public int offset = Config.super.offset();

    //? >=1.20.2 {
    @ConfigEntry.Gui.Tooltip
    public boolean renderTexture = Config.super.renderTexture();//?}

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public XpLevelMode xpLevelMode = Config.super.xpLevelMode();

    public boolean chatOffset = Config.super.chatOffset();

    @Override
    public int offset() {
        return this.offset;
    }

    //? >=1.20.2 {
    @Override
    public boolean renderTexture() {
        return this.renderTexture;
    }//?}

    @Override
    public XpLevelMode xpLevelMode() {
        return this.xpLevelMode;
    }

    @Override
    public boolean chatOffset() {
        return this.chatOffset;
    }
}
