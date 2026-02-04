package com.bizcub.bedrockHotbar.mixin;

import com.bizcub.bedrockHotbar.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import squeek.appleskin.client.HUDOverlayHandler;

//? neoforge {
/*import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HUDOverlayHandler.Overlay.class)
public class AppleskinCompat {

    @Redirect(method = "render(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;guiHeight()I"))
    private static int onRender(GuiGraphics instance) {
        return Main.operation(instance.guiHeight());
    }
}

*///?} <=1.20.4 {
/*import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(HUDOverlayHandler.class)
public class AppleskinCompat {

    @ModifyVariable(method = "onRender", at = @At("STORE"), name = "top")
    private static int onRender(int value) {
        return Main.operation(value);
    }

    @ModifyVariable(method = "drawExhaustionOverlay", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private static int drawHealthOverlay(int value) {
        return Main.operation(value);
    }
}

*///?} else {
@Mixin(HUDOverlayHandler.class)
public class AppleskinCompat {}//?}
