package com.bizcub.bedrockHotbar.mixin;

import com.bizcub.bedrockHotbar.config.Offset;
import net.minecraft.client.gui.hud.bar.Bar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Bar.class)
public interface BarOffsetMixin {

    @ModifyArg(method = "drawExperienceLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)V"), index = 3)
    private static int offset1(int value) {
        return Offset.operation(value);
    }

}
