package com.bizcub.bedrockHotbar.mixin;

import com.bizcub.bedrockHotbar.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

//? >=1.21.6 {
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.gui.contextualbar.ContextualBarRenderer;

@Mixin(ContextualBarRenderer.class)
public interface ContextualBarRendererMixin {

    @Redirect(method = "top", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
    default int offsetHotbar(Window instance) {
        return Main.operation(instance.getGuiScaledHeight());
    }

    //~ if >=26.1 'renderExperienceLevel' -> 'extractExperienceLevel'
    @ModifyArgs(method = "extractExperienceLevel", at = @At(value = "INVOKE", target =
            /*? >=26.1*/ "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V"
            /*? <=1.21.11*/ //"Lnet/minecraft/client/gui/GuiGraphicsExtractor;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V"
    ))
    private static void experienceLevel(Args args) {
        Main.renderExperienceLevel(args);
    }
}

//?} <=1.21.5 {
/*import net.minecraft.client.gui.Gui;

@Mixin(Gui.class)
public interface ContextualBarRendererMixin {}*///?}
