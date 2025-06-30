package com.bizcub.bedrockHotbar.mixin;

import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
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
    private void renderTexture(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, HOTBAR_SELECTION_TEXTURE, 24, 23, 0, 0, context.getScaledWindowWidth() / 2 - 91 - 1 + this.getCameraPlayer().getInventory().getSelectedSlot() * 20, context.getScaledWindowHeight() - 5, 24, 1);
    }

}
