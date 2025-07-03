package com.bizcub.bedrockHotbar.mixin;

import com.bizcub.bedrockHotbar.config.Offset;
import net.minecraft.client.gui.hud.bar.ExperienceBar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ExperienceBar.class)
public class ExperienceBarOffsetMixin {

    @ModifyVariable(method = "renderBar", at = @At(value = "STORE"), ordinal = 1)
    public int offsetExperienceBar(int value) {
        return Offset.operation(value);
    }
}