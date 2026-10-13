package io.github.bizcub.bedrockHotbar.mixin;

import io.github.bizcub.bedrockHotbar.Main;
import net.minecraft.client.gui.contextualbar.ContextualBar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ContextualBar.class)
public interface ContextualBarRendererMixin {

    @Inject(method = "top", at = @At("RETURN"), cancellable = true)
    default void offsetHotbar(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(Main.operation(cir.getReturnValue()));
    }

    @ModifyArgs(method = "extractExperienceLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V"))
    private static void experienceLevel(Args args) {
        Main.renderExperienceLevel(args, true);
    }
}
