package io.github.bizcub.bedrockHotbar.mixin;

import io.github.bizcub.bedrockHotbar.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.gui.contextualbar.ContextualBar;

@Mixin(ContextualBar.class)
public interface ContextualBarRendererMixin {

    @Redirect(method = "top", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
    default int offsetHotbar(Window instance) {
        return Main.operation(instance.getGuiScaledHeight());
    }

    @ModifyArgs(method = "extractExperienceLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V"))
    private static void experienceLevel(Args args) {
        Main.renderExperienceLevel(args, true);
    }
}
