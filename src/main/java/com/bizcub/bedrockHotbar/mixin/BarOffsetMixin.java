package com.bizcub.bedrockHotbar.mixin;

//? if >=1.21.6 {
import com.bizcub.bedrockHotbar.Offset;
import net.minecraft.client.gui.hud.bar.Bar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Bar.class)
public interface BarOffsetMixin {

    @ModifyArg(method = "drawExperienceLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)V"), index = 3)
    private static int offsetExperienceLevel(int value) {
        return Offset.operation(value) - 3;
    }
}
//?} elif <=1.21.5 {
/*import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(InGameHud.class)
public interface BarOffsetMixin {}
*///?}
