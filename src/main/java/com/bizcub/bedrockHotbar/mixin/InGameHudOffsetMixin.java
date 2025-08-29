package com.bizcub.bedrockHotbar.mixin;

import com.bizcub.bedrockHotbar.Offset;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

//? if >=1.20.5 {
/*import net.minecraft.client.gui.DrawContext;

@Mixin(InGameHud.class)
public class InGameHudOffsetMixin {

    //? if neoforge {
    /^@Redirect(method = {"renderHotbarVanilla", "renderSelectedItemName", "renderHealthLevel", "renderArmorLevel", "renderFoodLevel", "renderAirLevel"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetHotbar(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }

    ^///?} else {
    @Redirect(method = {"renderHotbar", "renderHeldItemTooltip", "renderStatusBars"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetHotbar(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }//?}

    @Redirect(method = "renderMountHealth", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetMountHealth(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }

    //? if <=1.21.5 {
    @Redirect(method = {"renderMountJumpBar", "renderExperienceBar"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetMountJumpBar(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }

    @ModifyArgs(method = "renderExperienceLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;IIIZ)I"))
    private static void experienceLevel(Args args) {
        int color = args.get(4);
        if (color != 0) {
            args.set(3, Offset.operation(args.get(3)) - 3);
            args.set(5, true);
        } else {
            args.set(3, -10);
        }
    }//?}
}

*///?} elif >=1.16.5 && <=1.20.4 {
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(InGameHud.class)
public class InGameHudOffsetMixin {

    @Shadow private int scaledHeight;

    @Redirect(method = {"renderHotbar", "renderHeldItemTooltip", "renderStatusBars", "renderMountHealth", "renderMountJumpBar", "renderExperienceBar"}, at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/hud/InGameHud;scaledHeight:I", opcode = Opcodes.GETFIELD))
    private int offsetHotbar(InGameHud instance) {
        return Offset.operation(scaledHeight);
    }

    //? if <=1.20.1 {
    @ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 22, ordinal = 4))
    private int resizeSelection(int value) {
        return 24;
    }//?}

    //? if >=1.20.1 {
    /*@ModifyArgs(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;IIIZ)I"))
    private static void experienceLevel(Args args) {
        int color = args.get(4);
        int offset = args.get(3);
        if (color != 0) {
            args.set(3, offset - 3);
            args.set(5, true);
        } else {
            args.set(3, -10);
        }
    }

    *///?} elif >=1.16.5 {
    @Redirect(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I"), slice = @Slice(
            from = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I", ordinal = 0),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I", ordinal = 3)))
    private static int experienceLevel(TextRenderer instance, MatrixStack matrices, String string, float k, float l, int color) {
        return instance.drawWithShadow(matrices, string, k, -10, 0);
    }

    @Redirect(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I", ordinal = 4))
    private static int experienceLevel1(TextRenderer instance, MatrixStack matrices, String string, float k, float l, int color) {
        return instance.drawWithShadow(matrices, string, k, (int) l - 3, color);
    }//?}
}//?}
