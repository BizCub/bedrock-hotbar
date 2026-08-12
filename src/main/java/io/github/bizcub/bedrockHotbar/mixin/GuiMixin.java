package io.github.bizcub.bedrockHotbar.mixin;

import io.github.bizcub.bedrockHotbar.Main;
import net.minecraft.client.gui.Hud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

//? >=1.20.5 {
import net.minecraft.client.gui.GuiGraphicsExtractor;

@Mixin(Hud.class)
public class GuiMixin {

    //~ if >=26.1 'render' -> 'extract' {
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;guiHeight()I"), method = {
            /*? fabric || forge {*/ "extractPlayerHealth", "extractSelectedItemName*",
            /*?} else*/ //"extractHealthLevel", "extractArmorLevel", "extractFoodLevel", "extractAirLevel", "extractSelectedItemName(Lnet/minecraft/client/gui/GuiGraphicsExtractor;I)V",
            "extractOverlayMessage", "extractVehicleHealth", "extractItemHotbar"
    })
    private int offsetMountHealth(GuiGraphicsExtractor instance) {
        return Main.operation(instance.guiHeight());
    }//~}

    //? <=1.21.5 {
    /*@Redirect(method = {"renderJumpMeter", "renderExperienceBar"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;guiHeight()I"))
    private int offsetMountJumpBar(GuiGraphicsExtractor instance) {
        return Main.operation(instance.guiHeight());
    }

    @ModifyArgs(method = "renderExperienceLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)I"))
    private static void experienceLevel(Args args) {
        Main.renderExperienceLevel(args, true);
    }*///?}
}

//?} <=1.20.4 {
/*import org.objectweb.asm.Opcodes;

@Mixin(Hud.class)
public class GuiMixin {

    @Shadow private int screenHeight;

    @ModifyArg(method = "render", index = 1, at = @At(value = "INVOKE", ordinal = 0, target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(FFF)V"))
    private float offsetActionbar(float value) {
        return Main.operation((int) value);
    }

    @Redirect(method = {"renderHotbar", "renderExperienceBar", "renderPlayerHealth", "renderVehicleHealth", "renderSelectedItemName", "renderJumpMeter"}, at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/Gui;screenHeight:I", opcode = Opcodes.GETFIELD))
    private int offsetHotbar(Gui instance) {
        return Main.operation(screenHeight);
    }

    @ModifyArgs(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)I"))
    private static void experienceLevel(Args args) {
        Main.renderExperienceLevel(args,  false);
    }

    //? <=1.20.1 {
    /^@ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 22, ordinal = 4))
    private int resizeSelection(int value) {
        return 24;
    }^///?}
}*///?}
