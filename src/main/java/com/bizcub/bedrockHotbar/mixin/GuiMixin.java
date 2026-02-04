package com.bizcub.bedrockHotbar.mixin;

import com.bizcub.bedrockHotbar.Main;
import com.bizcub.bedrockHotbar.config.Compat;
import com.bizcub.bedrockHotbar.config.Configs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

//? >=1.20.5 {
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;

@Mixin(Gui.class)
public class GuiMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;guiHeight()I"), method = {
            /*? fabric*/ "renderPlayerHealth", "renderSelectedItemName",
            /*? !fabric*/ //"renderHealthLevel", "renderArmorLevel", "renderFoodLevel", "renderAirLevel", "renderSelectedItemName(Lnet/minecraft/client/gui/GuiGraphics;I)V",
            "renderOverlayMessage", "renderVehicleHealth", "renderItemHotbar"
    })
    private int offsetMountHealth(GuiGraphics instance) {
        return Main.operation(instance.guiHeight());
    }

    //? <=1.21.5 {
    /*@Redirect(method = {"renderJumpMeter", "renderExperienceBar"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;guiHeight()I"))
    private int offsetMountJumpBar(GuiGraphics instance) {
        return Main.operation(instance.guiHeight());
    }

    @ModifyArgs(method = "renderExperienceLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)I"))
    private static void experienceLevel(Args args) {
        Main.renderExperienceLevel(args);
    }*///?}
}

//?} <=1.20.4 {
/*import org.objectweb.asm.Opcodes;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Gui.class)
public class GuiMixin {

    @Shadow private int screenHeight;

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(FFF)V", ordinal = 0), index = 1)
    private float offsetActionbar(float value) {
        return Main.operation((int) value);
    }

    @Redirect(method = {"renderItemHotbar", "renderExperienceBar", "renderPlayerHealth", "renderVehicleHealth", "renderSelectedItemName", "renderJumpMeter"}, at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/Gui;screenHeight:I", opcode = Opcodes.GETFIELD))
    private int offsetHotbar(Gui instance) {
        return Main.operation(screenHeight);
    }

    //? >=1.20 {
    @ModifyArgs(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)I"))
    private static void experienceLevel(Args args) {
        Main.renderExperienceLevel(args);
    }//?}

    //? <=1.20.1 {
    /^@ModifyConstant(method = "renderItemHotbar", constant = @Constant(intValue = 22, ordinal = 4))
    private int resizeSelection(int value) {
        return 24;
    }^///?}

    //? <=1.19.4 {
    /^@Redirect(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Ljava/lang/String;FFI)I"))
    private static int experienceLevel(Font instance, PoseStack poseStack, String string, float k, float l, int color) {
        if (Compat.isModLoaded(Compat.clothConfigId) && (Configs.getInstance().xpLevelMode == Configs.XpLevelMode.Outline))
            return instance.draw(poseStack, string, k, (int) l - 3, color);
        if (color != 0) return instance.drawShadow(poseStack, string, k, (int) l - 3, color);
        else return instance.draw(poseStack, string, k, -10, color);
    }^///?}
}*///?}
