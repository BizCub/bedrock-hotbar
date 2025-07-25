package com.bizcub.bedrockHotbar.mixin;

//? if >=1.21.6 {
import com.bizcub.bedrockHotbar.Offset;
import net.minecraft.client.gui.hud.bar.LocatorBar;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LocatorBar.class)
public class LocatorBarOffsetMixin {

    @ModifyArg(method = "renderBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIII)V"), index = 3)
    public int offsetLocatorBar(int value) {
        return Offset.operation(value);
    }

    @ModifyVariable(method = "renderAddons", at = @At(value = "STORE"))
    public int offsetLocatorAddons(int value) {
        return Offset.operation(value);
    }
}

//?} elif <=1.21.5 {
/*import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(InGameHud.class)
public class LocatorBarOffsetMixin {}
*///?}
