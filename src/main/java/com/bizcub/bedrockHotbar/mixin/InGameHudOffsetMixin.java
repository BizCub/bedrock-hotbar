package com.bizcub.bedrockHotbar.mixin;

import com.bizcub.bedrockHotbar.BedrockHotbar;
import com.bizcub.bedrockHotbar.config.Compat;
import com.bizcub.bedrockHotbar.config.Configs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

//? >=1.20.5 {
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;

@Mixin(Gui.class)
public class InGameHudOffsetMixin {

    @Redirect(method = {"renderVehicleHealth", "renderPlayerHealth", "renderItemHotbar", "renderSelectedItemName"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;guiHeight()I"))
    private int offsetMountHealth(GuiGraphics instance) {
        return BedrockHotbar.operation(instance.guiHeight());
    }

    //? <=1.21.5 {
    /*@Redirect(method = {"renderJumpMeter", "renderExperienceBar"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;guiHeight()I"))
    private int offsetMountJumpBar(GuiGraphics instance) {
        return BedrockHotbar.operation(instance.guiHeight());
    }

    @ModifyArgs(method = "renderExperienceLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)I"))
    private static void experienceLevel(Args args) {
        int color = args.get(4);
        int offset = BedrockHotbar.operation(args.get(3)) - 3;
        boolean number = color != 0;

        if (number) args.set(3, offset);
        else args.set(3, -10);
        args.set(5, true);

        if (Compat.isModLoaded(Compat.clothConfigId) && !(Configs.getInstance().xpLevelMode == Configs.XpLevelMode.Shadow)) {
            if (!number) args.set(3, offset);
            args.set(5, false);
        }
    }*///?}
}

//?} <=1.20.4 {
/*import org.objectweb.asm.Opcodes;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;

@Mixin(Gui.class)
public class InGameHudOffsetMixin {

    @Shadow private int screenHeight;

    @Redirect(method = {"renderHotbar", "renderExperienceBar", "renderPlayerHealth", "renderVehicleHealth", "renderSelectedItemName", "renderJumpMeter"}, at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/Gui;screenHeight:I", opcode = Opcodes.GETFIELD))
    private int offsetHotbar(Gui instance) {
        return BedrockHotbar.operation(screenHeight);
    }

    //? <=1.20.1 {
    /^@ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 22, ordinal = 4))
    private int resizeSelection(int value) {
        return 24;
    }^///?}

    //? >=1.20 {
    @ModifyArgs(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)I"))
    private static void experienceLevel(Args args) {
        int color = args.get(4);
        int offset = args.get(3);
        offset -= 3;
        boolean number = color != 0;

        if (number) args.set(3, offset);
        else args.set(3, -10);
        args.set(5, true);

        if (Compat.isModLoaded(Compat.clothConfigId) && !(Configs.getInstance().xpLevelMode == Configs.XpLevelMode.Shadow)) {
            if (!number) args.set(3, offset);
            args.set(5, false);
        }
    }//?}

    //? <=1.19.4 {
    /^@Redirect(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Ljava/lang/String;FFI)I"))
    private static int experienceLevel(Font instance, PoseStack poseStack, String string, float k, float l, int color) {
        if (Compat.isModLoaded(Compat.clothConfigId) && (Configs.getInstance().xpLevelMode == Configs.XpLevelMode.Outline))
            return instance.draw(poseStack, string, k, (int) l - 3, color);
        if (color != 0) return instance.drawShadow(poseStack, string, k, (int) l - 3, color);
        else return instance.draw(poseStack, string, k, -10, color);
    }^///?}
}*///?}
