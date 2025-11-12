package com.bizcub.bedrockHotbar.mixin;

import com.bizcub.bedrockHotbar.Constants;
import com.bizcub.bedrockHotbar.Offset;
import com.bizcub.bedrockHotbar.config.Compat;
import com.bizcub.bedrockHotbar.config.Configs;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

//? >=1.21.11 {
import net.minecraft.client.gui.Gui;

@Mixin(Gui.class)
public class InGameHudOffsetMixin {

    @Redirect(method = {"renderItemHotbar", "renderSelectedItemName", "renderPlayerHealth", "renderVehicleHealth"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;guiHeight()I"))
    private int offsetHotbar(GuiGraphics instance) {
        return Offset.operation(instance.guiHeight());
    }

//    //? neoforge {
//    /*@Redirect(method = {"renderHotbarVanilla", "renderSelectedItemName", "renderHealthLevel", "renderArmorLevel", "renderFoodLevel", "renderAirLevel"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
//    private int offsetHotbar(DrawContext instance) {
//        return Offset.operation(instance.getScaledWindowHeight());
//    }
//
//    *///?} fabric {
//    @Redirect(method = {"renderHotbar", "renderHeldItemTooltip", "renderStatusBars"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
//    private int offsetHotbar(DrawContext instance) {
//        return Offset.operation(instance.getScaledWindowHeight());
//    }//?}
//
//    @Redirect(method = "renderMountHealth", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
//    private int offsetMountHealth(DrawContext instance) {
//        return Offset.operation(instance.getScaledWindowHeight());
//    }
}

//?} >=1.20.5 {
/*import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;

@Mixin(InGameHud.class)
public class InGameHudOffsetMixin {

    //? neoforge {
    /^@Redirect(method = {"renderHotbarVanilla", "renderSelectedItemName", "renderHealthLevel", "renderArmorLevel", "renderFoodLevel", "renderAirLevel"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetHotbar(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }

    ^///?} fabric {
    @Redirect(method = {"renderHotbar", "renderHeldItemTooltip", "renderStatusBars"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetHotbar(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }//?}

    @Redirect(method = "renderMountHealth", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetMountHealth(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }

    //? <=1.21.5 {
    /^@Redirect(method = {"renderMountJumpBar", "renderExperienceBar"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetMountJumpBar(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }

    @ModifyArgs(method = "renderExperienceLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;IIIZ)I"))
    private static void experienceLevel(Args args) {
        int color = args.get(4);
        int offset = Offset.operation(args.get(3)) - 3;
        boolean number = color != 0;

        if (number) args.set(3, offset);
        else args.set(3, -10);
        args.set(5, true);

        if (Compat.isModLoaded(Constants.CLOTH_CONFIG_ID) && !(Configs.getInstance().xpLevelMode == Configs.XpLevelMode.Shadow)) {
            if (!number) args.set(3, offset);
            args.set(5, false);
        }
    }^///?}
}

*///?} <=1.20.4 {
/*import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.objectweb.asm.Opcodes;

@Mixin(InGameHud.class)
public class InGameHudOffsetMixin {

    @Shadow private int scaledHeight;

    @Redirect(method = {"renderHotbar", "renderHeldItemTooltip", "renderStatusBars", "renderMountHealth", "renderMountJumpBar", "renderExperienceBar"}, at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/hud/InGameHud;scaledHeight:I", opcode = Opcodes.GETFIELD))
    private int offsetHotbar(InGameHud instance) {
        return Offset.operation(scaledHeight);
    }

    //? <=1.20.1 {
    /^@ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 22, ordinal = 4))
    private int resizeSelection(int value) {
        return 24;
    }^///?}

    //? >=1.20 {
    @ModifyArgs(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;IIIZ)I"))
    private static void experienceLevel(Args args) {
        int color = args.get(4);
        int offset = args.get(3);
        offset -= 3;
        boolean number = color != 0;

        if (number) args.set(3, offset);
        else args.set(3, -10);
        args.set(5, true);

        if (Compat.isModLoaded(Constants.CLOTH_CONFIG_ID) && !(Configs.getInstance().xpLevelMode == Configs.XpLevelMode.Shadow)) {
            if (!number) args.set(3, offset);
            args.set(5, false);
        }
    }//?}

    //? <=1.19.4 {
    /^@Redirect(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I"))
    private static int experienceLevel(TextRenderer instance, MatrixStack matrices, String string, float k, float l, int color) {
        if (Compat.isModLoaded(Constants.CLOTH_CONFIG_ID) && (Configs.getInstance().xpLevelMode == Configs.XpLevelMode.Outline)) {
            return instance.draw(matrices, string, k, (int) l - 3, color);
        }
        if (color != 0) return instance.drawWithShadow(matrices, string, k, (int) l - 3, color);
        else return instance.draw(matrices, string, k, -10, color);
    }^///?}
}*///?}
