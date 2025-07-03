package com.bizcub.bedrockHotbar.mixin;

import com.bizcub.bedrockHotbar.config.Offset;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class RenderBottomOfTextureMixin {

    @Shadow
    protected abstract PlayerEntity getCameraPlayer();

    @Final
    @Shadow
    private static Identifier HOTBAR_SELECTION_TEXTURE;

    @Inject(method = "renderHotbar", at = @At(value = "TAIL"))
    private void renderTexture(DrawContext context, float tickDelta, CallbackInfo ci) {
        int x = context.getScaledWindowWidth();
        int y = context.getScaledWindowHeight();
        int selectedSlot = this.getCameraPlayer().getInventory().selectedSlot;
        y = Offset.operation(y);
        context.drawGuiTexture(HOTBAR_SELECTION_TEXTURE, 24, 23, 0, 0, x / 2 - 91 - 1 + selectedSlot * 20, y, 24, 1);
    }
}