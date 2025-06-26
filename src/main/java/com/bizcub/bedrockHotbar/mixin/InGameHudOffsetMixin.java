package com.bizcub.bedrockHotbar.mixin;

import com.bizcub.bedrockHotbar.Offset;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(InGameHud.class)
public class InGameHudOffsetMixin {

    @ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 22, ordinal = 4))
    public int resizeSelection(int value) {
        return 24;
    }

    @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"), index = 2)
    public int offsetHotbar(int value) {
        return Offset.operation(value);
    }

    @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbarItem(IIFLnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)V"), index = 1)
    public int offsetItem(int value) {
        return Offset.operation(value);
    }

    @ModifyArg(method = "renderStatusBars", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"), index = 2)
    public int offsetStatus(int value) {
        return Offset.operation(value);
    }

    @ModifyArg(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"), index = 2)
    public int offsetExperienceBarBackground(int value) {
        return Offset.operation(value);
    }

    @ModifyArg(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I"), index = 3)
    public float offsetExperienceLevel(float value) {
        return Offset.operation((int) value);
    }

    @ModifyArg(method = "renderMountHealth", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"), index = 2)
    public int offsetMountHealth(int value) {
        return Offset.operation(value);
    }

    @ModifyArg(method = "renderMountJumpBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"), index = 2)
    public int offsetMountJumpBarBackground(int value) {
        return Offset.operation(value);
    }

}