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

    public static Configs getInstance() {
        return AutoConfig.getConfigHolder(Configs.class).getConfig();
    }

    public static void init() {
        AutoConfig.register(Configs.class, GsonConfigSerializer::new);
    }
}
