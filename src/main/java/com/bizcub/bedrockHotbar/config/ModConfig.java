package com.bizcub.bedrockHotbar.config;

import com.bizcub.bedrockHotbar.BedrockHotbar;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

@Config(name = BedrockHotbar.MOD_ID)
public class ModConfig implements ConfigData {

    @ConfigEntry.BoundedDiscrete(min = -50, max = 250)
    public int offset = 4;

    public static ModConfig getInstance() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }

    public static void init() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
    }
}