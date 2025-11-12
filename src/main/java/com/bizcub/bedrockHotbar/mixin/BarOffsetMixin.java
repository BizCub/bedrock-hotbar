package com.bizcub.bedrockHotbar.mixin;

import com.bizcub.bedrockHotbar.Constants;
import com.bizcub.bedrockHotbar.Offset;
import com.bizcub.bedrockHotbar.config.Configs;
import com.bizcub.bedrockHotbar.config.Compat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

//? >=1.21.11 {
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.gui.contextualbar.ContextualBarRenderer;

@Mixin(ContextualBarRenderer.class)
public interface BarOffsetMixin {

    @Redirect(method = "top", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
    default int offsetHotbar(Window instance) {
        return Offset.operation(instance.getGuiScaledHeight());
    }

    @ModifyArgs(method = "renderExperienceLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V"))
    private static void experienceLevel(Args args) {
        int color = args.get(4);
        int offset = Offset.operation(args.get(3)) - 3;
        boolean number = color == -8323296;

        if (number) args.set(3, offset);
        else args.set(3, -10);
        args.set(5, true);

        if (Compat.isModLoaded(Constants.CLOTH_CONFIG_ID) && (Configs.getInstance().xpLevelMode == Configs.XpLevelMode.Outline)) {
            if (!number) args.set(3, offset);
            args.set(5, false);
        }
    }
}

//?} >=1.21.6 {
/*import net.minecraft.client.gui.hud.bar.Bar;
import net.minecraft.client.util.Window;

@Mixin(Bar.class)
public interface BarOffsetMixin {

    @Redirect(method = "getCenterY", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledHeight()I"))
    default int offsetHotbar(Window instance) {
        return Offset.operation(instance.getScaledHeight());
    }

    @ModifyArgs(method = "drawExperienceLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)V"))
    private static void experienceLevel(Args args) {
        int color = args.get(4);
        int offset = Offset.operation(args.get(3)) - 3;
        boolean number = color == -8323296;

        if (number) args.set(3, offset);
        else args.set(3, -10);
        args.set(5, true);

        if (Compat.isModLoaded(Constants.CLOTH_CONFIG_ID) && (Configs.getInstance().xpLevelMode == Configs.XpLevelMode.Outline)) {
            if (!number) args.set(3, offset);
            args.set(5, false);
        }
    }
}

*///?} <=1.21.5 {
/*import net.minecraft.client.gui.hud.InGameHud;

@Mixin(InGameHud.class)
public interface BarOffsetMixin {}*///?}
