package com.bizcub.bedrockHotbar.mixin;

import com.bizcub.bedrockHotbar.Constants;
import com.bizcub.bedrockHotbar.Offset;
import com.bizcub.bedrockHotbar.config.Compat;
import com.bizcub.bedrockHotbar.config.Configs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? >=1.21.11 {
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.Identifier;

@Mixin(Gui.class)
public abstract class RenderBottomOfTextureMixin {

    @Shadow protected abstract Player getCameraPlayer();
    @Final @Shadow private static Identifier HOTBAR_SELECTION_SPRITE;

    @Inject(method = "renderItemHotbar", at = @At(value = "TAIL"))
    private void renderTexture(GuiGraphics context, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (Compat.isModLoaded(Constants.CLOTH_CONFIG_ID) && !Configs.getInstance().renderTexture) return;

        int x = context.guiWidth();
        int y = context.guiHeight();
        int selectedSlot = getCameraPlayer().getInventory().getSelectedSlot();
        y = Offset.operation(y);
        context.blitSprite(RenderPipelines.GUI_TEXTURED, HOTBAR_SELECTION_SPRITE, 24, 23, 0, 0, x / 2 - 91 - 1 + selectedSlot * 20, y, 24, 1);
    }
}

//?} >=1.21.2 {
/*/^? >=1.21.6^/ import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

@Mixin(InGameHud.class)
public abstract class RenderBottomOfTextureMixin {

    @Shadow protected abstract PlayerEntity getCameraPlayer();
    @Final @Shadow private static Identifier HOTBAR_SELECTION_TEXTURE;

    @Inject(method = "renderHotbar", at = @At(value = "TAIL"))
    private void renderTexture(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (Compat.isModLoaded(Constants.CLOTH_CONFIG_ID) && !Configs.getInstance().renderTexture) return;

        int x = context.getScaledWindowWidth();
        int y = context.getScaledWindowHeight();
        /^? >=1.21.5^/ int selectedSlot = getCameraPlayer().getInventory().getSelectedSlot();
        /^? <= 1.21.4^/ /^int selectedSlot = getCameraPlayer().getInventory().selectedSlot;^/
        y = Offset.operation(y);
        /^? >=1.21.6^/ context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, HOTBAR_SELECTION_TEXTURE, 24, 23, 0, 0, x / 2 - 91 - 1 + selectedSlot * 20, y, 24, 1);
        /^? <=1.21.5^/ /^context.drawGuiTexture(RenderLayer::getGuiTextured, HOTBAR_SELECTION_TEXTURE, 24, 23, 0, 0, x / 2 - 91 - 1 + selectedSlot * 20, y, 24, 1);^/
    }
}

*///?} >=1.20.2 && <=1.21.1 {
/*import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

@Mixin(InGameHud.class)
public abstract class RenderBottomOfTextureMixin {

    @Shadow protected abstract PlayerEntity getCameraPlayer();
    @Final @Shadow private static Identifier HOTBAR_SELECTION_TEXTURE;

    @Inject(method = "renderHotbar", at = @At(value = "TAIL"))
    /^? >=1.21^/ /^private void renderTexture(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {^/
    /^? <=1.20.6 && >=1.20.5^/ /^private void renderTexture(DrawContext context, float tickDelta, CallbackInfo ci) {^/
    /^? <=1.20.4^/ private void renderTexture(float tickDelta, DrawContext context, CallbackInfo ci) {
        if (Compat.isModLoaded(Constants.CLOTH_CONFIG_ID) && !Configs.getInstance().renderTexture) return;

        int x = context.getScaledWindowWidth();
        int y = context.getScaledWindowHeight();
        int selectedSlot = getCameraPlayer().getInventory().selectedSlot;
        y = Offset.operation(y);
        context.drawGuiTexture(HOTBAR_SELECTION_TEXTURE, 24, 23, 0, 0, x / 2 - 91 - 1 + selectedSlot * 20, y, 24, 1);
    }
}

*///?} <=1.20.1 {
/*@Mixin(InGameHud.class)
public class RenderBottomOfTextureMixin {}*///?}
