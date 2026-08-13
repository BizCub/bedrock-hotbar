package io.github.bizcub.bedrockHotbar.config;

import io.github.bizcub.bedrockHotbar.Main;
import io.github.bizcub.simpleConfigLib.autoconfig.ConfigHolder;
import io.github.bizcub.simpleConfigLib.autoconfig.annotation.*;

@AutoConfig(name = Main.MOD_ID, translate = true)
public class SimpleConfig implements Config {

    public static ConfigHolder<SimpleConfig> getInstance() {
        return ConfigHolder.register(SimpleConfig.class);
    }

    @Slider(min = Main.MIN_OFFSET, max = Main.MAX_OFFSET)
    public int offset = Config.super.offset();

    //? >=1.20.2 {
    @Tooltip
    public boolean renderTexture = Config.super.renderTexture();//?}

    @Tooltip
    @EnumConfig(translate = true)
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
