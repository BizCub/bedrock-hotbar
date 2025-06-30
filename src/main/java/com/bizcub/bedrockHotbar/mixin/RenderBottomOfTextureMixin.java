package com.bizcub.bedrockHotbar.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.client.gui.widget.ClickableWidget.WIDGETS_TEXTURE;

@Mixin(InGameHud.class)
public abstract class RenderBottomOfTextureMixin {

    @Shadow
    protected abstract PlayerEntity getCameraPlayer();

    @Inject(method = "renderHotbar", at = @At(value = "TAIL"))
    private void renderTexture(float tickDelta, DrawContext context, CallbackInfo ci) {
        context.drawTexture(WIDGETS_TEXTURE, context.getScaledWindowWidth() / 2 - 91 - 1 + this.getCameraPlayer().getInventory().selectedSlot * 20, context.getScaledWindowHeight() - 5, 0, 22, 24, 1);
    }

}