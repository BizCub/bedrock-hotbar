package com.bizcub.bedrockHotbar.mixin;

import com.bizcub.bedrockHotbar.Offset;
import com.bizcub.bedrockHotbar.config.Configs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if >=1.21.6 {
/*import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

@Mixin(InGameHud.class)
public abstract class RenderBottomOfTextureMixin {

    @Shadow protected abstract PlayerEntity getCameraPlayer();

    @Final @Shadow private static Identifier HOTBAR_SELECTION_TEXTURE;

    @Inject(method = "renderHotbar", at = @At(value = "TAIL"))
    private void renderTexture(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (Configs.getInstance().renderTexture) {
            int x = context.getScaledWindowWidth();
            int y = context.getScaledWindowHeight();
            int selectedSlot = this.getCameraPlayer().getInventory().getSelectedSlot();
            y = Offset.operation(y);
            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, HOTBAR_SELECTION_TEXTURE, 24, 23, 0, 0, x / 2 - 91 - 1 + selectedSlot * 20, y, 24, 1);
        }
    }
}

*///?} elif >=1.21.2 && <=1.21.5 {
/*import net.minecraft.client.gui.DrawContext;
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
        if (Configs.getInstance().renderTexture) {
            int x = context.getScaledWindowWidth();
            int y = context.getScaledWindowHeight();
            //? if 1.21.5 {
            /^int selectedSlot = this.getCameraPlayer().getInventory().getSelectedSlot();
            ^///?} else {
            int selectedSlot = this.getCameraPlayer().getInventory().selectedSlot;
            //?}
            y = Offset.operation(y);
            context.drawGuiTexture(RenderLayer::getGuiTextured, HOTBAR_SELECTION_TEXTURE, 24, 23, 0, 0, x / 2 - 91 - 1 + selectedSlot * 20, y, 24, 1);
        }
    }
}

*///?} elif >=1.21 && <=1.21.1 {
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
    private void renderTexture(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (Configs.getInstance().renderTexture) {
            int x = context.getScaledWindowWidth();
            int y = context.getScaledWindowHeight();
            int selectedSlot = this.getCameraPlayer().getInventory().selectedSlot;
            y = Offset.operation(y);
            context.drawGuiTexture(HOTBAR_SELECTION_TEXTURE, 24, 23, 0, 0, x / 2 - 91 - 1 + selectedSlot * 20, y, 24, 1);
        }
    }
}

*///?} elif >=1.20.5 && <=1.20.6 {
/*import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

@Mixin(InGameHud.class)
public abstract class RenderBottomOfTextureMixin {

    @Shadow protected abstract PlayerEntity getCameraPlayer();

    @Final @Shadow private static Identifier HOTBAR_SELECTION_TEXTURE;

    @Inject(method = "renderHotbar", at = @At(value = "TAIL"))
    private void renderTexture(DrawContext context, float tickDelta, CallbackInfo ci) {
        if (Configs.getInstance().renderTexture) {
            int x = context.getScaledWindowWidth();
            int y = context.getScaledWindowHeight();
            int selectedSlot = this.getCameraPlayer().getInventory().selectedSlot;
            y = Offset.operation(y);
            context.drawGuiTexture(HOTBAR_SELECTION_TEXTURE, 24, 23, 0, 0, x / 2 - 91 - 1 + selectedSlot * 20, y, 24, 1);
        }
    }
}

*///?} elif >=1.20.2 && <=1.20.4 {
/*import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

@Mixin(InGameHud.class)
public abstract class RenderBottomOfTextureMixin {

    @Shadow protected abstract PlayerEntity getCameraPlayer();

    @Final @Shadow private static Identifier HOTBAR_SELECTION_TEXTURE;

    @Inject(method = "renderHotbar", at = @At(value = "TAIL"))
    private void renderTexture(float tickDelta, DrawContext context, CallbackInfo ci) {
        if (Configs.getInstance().renderTexture) {
            int x = context.getScaledWindowWidth();
            int y = context.getScaledWindowHeight();
            int selectedSlot = this.getCameraPlayer().getInventory().selectedSlot;
            y = Offset.operation(y);
            context.drawGuiTexture(HOTBAR_SELECTION_TEXTURE, 24, 23, 0, 0, x / 2 - 91 - 1 + selectedSlot * 20, y, 24, 1);
        }
    }
}

*///?} elif <=1.20.1 {
import net.minecraft.client.gui.hud.InGameHud;

@Mixin(InGameHud.class)
public class RenderBottomOfTextureMixin {}//?}
