package com.bizcub.bedrockHotbar.mixin;

//? if >=1.21.6 {
/*import com.bizcub.bedrockHotbar.Offset;
import net.minecraft.client.gui.hud.bar.Bar;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Bar.class)
public interface BarOffsetMixin {

    @Inject(method = "getCenterY", at = @At(value = "RETURN"), cancellable = true)
    private void offsetHotbar(Window window, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(Offset.operation(cir.getReturnValue()));
    }

    @ModifyArgs(method = "drawExperienceLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)V"))
    private static void experienceLevel(Args args) {
        int color = args.get(4);
        if (color == -8323296) {
            args.set(3, Offset.operation(args.get(3)) - 3);
            args.set(5, true);
        } else {
            args.set(4, 0);
        }
    }
}

*///?} elif <=1.21.5 {
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(InGameHud.class)
public interface BarOffsetMixin {}
//?}
