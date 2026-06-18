package com.bizcub.bedrockHotbar.mixin;

import com.bizcub.bedrockHotbar.Main;
import net.minecraft.client.gui.Hud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? >=1.20.2 {
import net.minecraft.client.gui.GuiGraphicsExtractor;
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

@Mixin(Hud.class)
public abstract class RenderBottomOfTextureMixin {

    @Shadow protected abstract Player getCameraPlayer();
    @Final @Shadow private static Identifier HOTBAR_SELECTION_SPRITE;

    //~ hotbar
    @Inject(method = "extractItemHotbar", at = @At(value = "TAIL"))
    private void renderTexture(
            /*? >=1.21*/ GuiGraphicsExtractor graphics, DeltaTracker deltaTracker,
            /*? <=1.20.6 && >=1.20.5*/ //GuiGraphicsExtractor graphics, float f,
            /*? <=1.20.4*/ //float f, GuiGraphicsExtractor graphics,
            CallbackInfo ci
    ) {
        if (!Main.getConfig().renderTexture()) return;

        int selectedSlot = getCameraPlayer().getInventory().getSelectedSlot();
        int x = graphics.guiWidth() / 2 - 91 - 1 + selectedSlot * 20;
        int y = Main.operation(graphics.guiHeight());
        graphics.blitSprite(
            /*? >=1.21.6*/ RenderPipelines.GUI_TEXTURED,
            /*? <=1.21.5 && >=1.21.2*/ //RenderType::guiTextured,
            HOTBAR_SELECTION_SPRITE, 24, 23, 0, 0, x, y, 24, 1
        );
    }
}

//?} <=1.20.1 {
/*@Mixin(Hud.class)
public class RenderBottomOfTextureMixin {}*///?}
