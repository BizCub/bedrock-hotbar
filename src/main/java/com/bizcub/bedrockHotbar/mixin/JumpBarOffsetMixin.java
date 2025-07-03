package com.bizcub.bedrockHotbar.mixin;

import com.bizcub.bedrockHotbar.config.Offset;
import net.minecraft.client.gui.hud.bar.JumpBar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(JumpBar.class)
public class JumpBarOffsetMixin {

    @ModifyVariable(method = "renderBar", at = @At(value = "STORE"), ordinal = 1)
    public int offset1(int value) {
        return Offset.operation(value);
    }

}
