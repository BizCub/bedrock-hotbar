package com.bizcub.bedrockHotbar.mixin;

//? if >=1.21.6 {
/*import com.bizcub.bedrockHotbar.Offset;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.bar.Bar;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Bar.class)
public interface BarOffsetMixin {

    @Inject(method = "getCenterY", at = @At(value = "RETURN"), cancellable = true)
    private void offsetHotbar(Window window, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(Offset.operation(cir.getReturnValue()));
    }

    @Redirect(method = "drawExperienceLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"))
    private static int offsetExperienceLevel(DrawContext instance) {
        return Offset.operation(instance.getScaledWindowHeight()) - 3;
    }
}

*///?} elif <=1.21.5 {
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(InGameHud.class)
public interface BarOffsetMixin {}
//?}
