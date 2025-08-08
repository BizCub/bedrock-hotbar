package com.bizcub.bedrockHotbar.mixin;

//? if >=1.20.5 {
/*import com.bizcub.bedrockHotbar.Offset;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(InGameHud.class)
public class InGameHudOffsetMixin {

    //? if neoforge {
    /^@Redirect(method = "renderHotbarVanilla", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetHotbar(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }

    @Redirect(method = "renderSelectedItemName", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetHeldItemTooltip(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }

    @Redirect(method = "renderHealthLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetHealthLevel(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }

    @Redirect(method = "renderArmorLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetArmorLevel(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }

    @Redirect(method = "renderFoodLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetFoodLevel(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }

    @Redirect(method = "renderAirLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetAirLevel(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }

    ^///?} else {
    @Redirect(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetHotbar(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }

    @Redirect(method = "renderHeldItemTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetHeldItemTooltip(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }

    @Redirect(method = "renderStatusBars", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetStatusBars(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }
    //?}

    @Redirect(method = "renderMountHealth", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetMountHealth(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }

    //? if <=1.21.5 {
    @Redirect(method = "renderMountJumpBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetMountJumpBar(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }

    @Redirect(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private int offsetExperienceBar(DrawContext instance) {
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
    }
    //?}
}

*///?} elif >=1.19.4 && <=1.20.4 {
import com.bizcub.bedrockHotbar.Offset;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(InGameHud.class)
public class InGameHudOffsetMixin {

    @Shadow private int scaledHeight;

    //? if <=1.20.1 {
    @ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 22, ordinal = 4))
    private int resizeSelection(int value) {
        return 24;
    }
    //?}

    @Redirect(method = "renderHotbar", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/hud/InGameHud;scaledHeight:I", opcode = Opcodes.GETFIELD))
    private int offsetHotbar(InGameHud instance) {
        return Offset.operation(scaledHeight);
    }

    @Redirect(method = "renderHeldItemTooltip", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/hud/InGameHud;scaledHeight:I", opcode = Opcodes.GETFIELD))
    private int offsetHeldItemTooltip(InGameHud instance) {
        return Offset.operation(scaledHeight);
    }

    @Redirect(method = "renderStatusBars", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/hud/InGameHud;scaledHeight:I", opcode = Opcodes.GETFIELD))
    private int offsetStatusBars(InGameHud instance) {
        return Offset.operation(scaledHeight);
    }

    @Redirect(method = "renderMountHealth", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/hud/InGameHud;scaledHeight:I", opcode = Opcodes.GETFIELD))
    private int offsetMountHealth(InGameHud instance) {
        return Offset.operation(scaledHeight);
    }

    @Redirect(method = "renderMountJumpBar", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/hud/InGameHud;scaledHeight:I", opcode = Opcodes.GETFIELD))
    private int offsetMountJumpBar(InGameHud instance) {
        return Offset.operation(scaledHeight);
    }

    @Redirect(method = "renderExperienceBar", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/hud/InGameHud;scaledHeight:I", opcode = Opcodes.GETFIELD, ordinal = 0))
    private int offsetExperienceBar(InGameHud instance) {
        return Offset.operation(scaledHeight);
    }

    //? if >=1.20.2 {
    /*@ModifyArgs(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;IIIZ)I"))
    private static void experienceLevel(Args args) {
        int color = args.get(4);
        if (color != 0) {
            args.set(3, Offset.operation(args.get(3)) - 3);
            args.set(5, true);
        } else {
            args.set(3, -10);
        }
    }
    *///?} elif >=1.19.4 {
    @Redirect(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I", ordinal = 0))
    private static int experienceLevel0(TextRenderer instance, MatrixStack matrices, String string, float k, float l, int color) {
        return instance.drawWithShadow(matrices, string, k, -10, 0);
    }

    @Redirect(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I", ordinal = 1))
    private static int experienceLevel1(TextRenderer instance, MatrixStack matrices, String string, float k, float l, int color) {
        return instance.drawWithShadow(matrices, string, k, -10, 0);
    }

    @Redirect(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I", ordinal = 2))
    private static int experienceLevel2(TextRenderer instance, MatrixStack matrices, String string, float k, float l, int color) {
        return instance.drawWithShadow(matrices, string, k, -10, 0);
    }

    @Redirect(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I", ordinal = 3))
    private static int experienceLevel3(TextRenderer instance, MatrixStack matrices, String string, float k, float l, int color) {
        return instance.drawWithShadow(matrices, string, k, -10, 0);
    }

    @Redirect(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I", ordinal = 4))
    private static int experienceLevel4(TextRenderer instance, MatrixStack matrices, String string, float k, float l, int color) {
        return instance.drawWithShadow(matrices, string, k, Offset.operation((int) l) - 3, color);
    }
    //?}
}
//?}
