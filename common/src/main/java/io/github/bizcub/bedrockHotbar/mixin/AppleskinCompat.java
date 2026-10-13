package io.github.bizcub.bedrockHotbar.mixin;

import io.github.bizcub.bedrockHotbar.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//? neoforge {
/*import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.spongepowered.asm.mixin.injection.Redirect;
import squeek.appleskin.client.HUDOverlayHandler;

@Mixin(HUDOverlayHandler.Overlay.class)
public class AppleskinCompat {

    @Redirect(method = "render(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;guiHeight()I"))
    private static int onRender(GuiGraphicsExtractor instance) {
        return Main.operation(instance.guiHeight());
    }
}

*///?} else {
@Mixin(value = {})
public class AppleskinCompat {}//?}
