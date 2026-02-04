package com.bizcub.bedrockHotbar.mixin;

import com.bizcub.bedrockHotbar.Main;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.Identifier;

//? >=1.21.11 {
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.RenderPipelines;
//?} >=1.21.5 {
/*import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.RenderType;
*///?} >=1.21 {
/*import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.RenderType;
*///?} >=1.20.2 {
/*import net.minecraft.client.renderer.RenderType;*///?}

@Mixin(Gui.class)
public abstract class RenderBottomOfTextureMixin {

    @Shadow protected abstract Player getCameraPlayer();
    @Final @Shadow private static Identifier HOTBAR_SELECTION_SPRITE;

    @Inject(method = "renderItemHotbar", at = @At(value = "TAIL"))
    private void renderTexture(
    /*? >=1.21*/ GuiGraphics context, DeltaTracker deltaTracker,
    /*? <=1.20.6 && >=1.20.5*/ //GuiGraphics context, float f,
    /*? <=1.20.4*/ //float f, GuiGraphics context,
    CallbackInfo ci) {
        if (Compat.isModLoaded(Compat.clothConfigId) && !Configs.getInstance().renderTexture) return;

        int selectedSlot = getCameraPlayer().getInventory().getSelectedSlot();
        int x = context.guiWidth() / 2 - 91 - 1 + selectedSlot * 20;
        int y = Main.operation(context.guiHeight());
        context.blitSprite(
            /*? >=1.21.6*/ RenderPipelines.GUI_TEXTURED,
            /*? <=1.21.5 && >=1.21.2*/ //RenderType::guiTextured,
            HOTBAR_SELECTION_SPRITE, 24, 23, 0, 0, x, y, 24, 1
        );
    }
}

//?} <=1.20.1 {
/*@Mixin(Gui.class)
public class RenderBottomOfTextureMixin {}*///?}
