package com.bizcub.bedrockHotbar.mixin;

import com.bizcub.bedrockHotbar.Offset;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import squeek.appleskin.client.HUDOverlayHandler;

//? neoforge {
/*import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HUDOverlayHandler.Overlay.class)
public class AppleskinCompat {

    @Redirect(method = "render(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private static int onRender(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight());
    }
}

*///?} <=1.20.4 {
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(HUDOverlayHandler.class)
public class AppleskinCompat {

    @ModifyVariable(method = "onRender", at = @At("STORE"), ordinal = 0)
    private static int onRender(int value) {
        return Offset.operation(value);
    }

    @ModifyVariable(method = "drawExhaustionOverlay", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private static int drawHealthOverlay(int value) {
        return Offset.operation(value);
    }
}

//?} else {
/*@Mixin(HUDOverlayHandler.class)
public class AppleskinCompat {}*///?}
