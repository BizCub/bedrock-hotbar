package com.bizcub.bedrockHotbar.config;

import com.bizcub.bedrockHotbar.Constants;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

@Config(name = Constants.MOD_ID)
public class Configs implements ConfigData {

    @ConfigEntry.BoundedDiscrete(min = Constants.MIN_OFFSET, max = Constants.MAX_OFFSET)
    public int offset = Constants.DEF_OFFSET;

    @ConfigEntry.Gui.Tooltip
    public boolean renderTexture = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public XpLevelMode xpLevelMode = XpLevelMode.Shadow;

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

    public static Configs getInstance() {
        return AutoConfig.getConfigHolder(Configs.class).getConfig();
    }

    public static void init() {
        AutoConfig.register(Configs.class, GsonConfigSerializer::new);
    }
}
