package com.bizcub.bedrockHotbar.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

//? >=1.21.6 {
import com.bizcub.bedrockHotbar.BedrockHotbar;
import com.bizcub.bedrockHotbar.config.Compat;
import com.bizcub.bedrockHotbar.config.Configs;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.gui.contextualbar.ContextualBarRenderer;

@Mixin(ContextualBarRenderer.class)
public interface BarOffsetMixin {

    @Redirect(method = "top", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
    default int offsetHotbar(Window instance) {
        return BedrockHotbar.operation(instance.getGuiScaledHeight());
    }

    @ModifyArgs(method = "renderExperienceLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V"))
    private static void experienceLevel(Args args) {
        int color = args.get(4);
        int offset = BedrockHotbar.operation(args.get(3)) - 3;
        boolean number = color == -8323296;

        if (number) args.set(3, offset);
        else args.set(3, -10);
        args.set(5, true);

        if (Compat.isModLoaded(Compat.clothConfigId) && (Configs.getInstance().xpLevelMode == Configs.XpLevelMode.Outline)) {
            if (!number) args.set(3, offset);
            args.set(5, false);
        }
    }
}

//?} <=1.21.5 {
/*import net.minecraft.client.gui.Gui;

@Mixin(Gui.class)
public interface BarOffsetMixin {}*///?}
