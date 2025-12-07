package com.bizcub.bedrockHotbar.mixin;

import com.bizcub.bedrockHotbar.Constants;
import com.bizcub.bedrockHotbar.Offset;
import com.bizcub.bedrockHotbar.config.Compat;
import com.bizcub.bedrockHotbar.config.Configs;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? >=1.20.2 {
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
//? >=1.21.9 {
import net.minecraft.client.DeltaTracker;
import net.minecraft.resources.Identifier;
//?} >=1.21.5 {
/*import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.RenderPipelines;
*///?} >=1.20.2 {
/*import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;*///?}

@Mixin(Gui.class)
public abstract class RenderBottomOfTextureMixin {

    @Shadow protected abstract Player getCameraPlayer();
    /*? >=1.21.9*/ @Final @Shadow private static Identifier HOTBAR_SELECTION_SPRITE;
    /*? <=1.21.8*/ /*@Final @Shadow private static ResourceLocation HOTBAR_SELECTION_SPRITE;*/

    /*? >=1.21*/ @Inject(method = "renderItemHotbar", at = @At(value = "TAIL"))
    /*? <=1.21*/ /*@Inject(method = "renderHotbar", at = @At(value = "TAIL"))*/
    /*? >=1.21*/ private void renderTexture(GuiGraphics context, DeltaTracker deltaTracker, CallbackInfo ci) {
    /*? <=1.20.6 && >=1.20.5*/ /*private void renderTexture(GuiGraphics context, float f, CallbackInfo ci) {*/
    /*? <=1.20.4*/ /*private void renderTexture(float f, GuiGraphics context, CallbackInfo ci) {*/
        if (Compat.isModLoaded(Constants.CLOTH_CONFIG_ID) && !Configs.getInstance().renderTexture) return;

        int x = context.guiWidth();
        int y = context.guiHeight();
        /*? >=1.21.5*/ int selectedSlot = getCameraPlayer().getInventory().getSelectedSlot();
        /*? <=1.21.4*/ /*int selectedSlot = getCameraPlayer().getInventory().selected;*/
        y = Offset.operation(y);
        /*? >=1.21.6*/ context.blitSprite(RenderPipelines.GUI_TEXTURED, HOTBAR_SELECTION_SPRITE, 24, 23, 0, 0, x / 2 - 91 - 1 + selectedSlot * 20, y, 24, 1);
        /*? <=1.21.5 && >=1.21.2*/ /*context.blitSprite(RenderType::guiTextured, HOTBAR_SELECTION_SPRITE, 24, 23, 0, 0, x / 2 - 91 - 1 + selectedSlot * 20, y, 24, 1);*/
        /*? <=1.21.1*/ /*context.blitSprite(HOTBAR_SELECTION_SPRITE, 24, 23, 0, 0, x / 2 - 91 - 1 + selectedSlot * 20, y, 24, 1);*/
    }
}

//?} <=1.20.1 {
/*@Mixin(Gui.class)
public class RenderBottomOfTextureMixin {}*///?}
