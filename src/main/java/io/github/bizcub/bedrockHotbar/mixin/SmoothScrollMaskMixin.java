package io.github.bizcub.bedrockHotbar.mixin;

import org.spongepowered.asm.mixin.Mixin;

//? fabric {
import io.github.bizcub.bedrockHotbar.config.Config;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Hud.class)
public class SmoothScrollMaskMixin {

    @Redirect(method = "enableMask", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;enableScissor(IIII)V"))
    private void raiseMask(GuiGraphicsExtractor graphics, int x1, int y1, int x2, int y2) {
        int offset = Config.get().offset();
        graphics.enableScissor(x1, y1 - offset, x2, y2 - offset);
    }
}

//?} else {
/*import net.minecraft.client.Minecraft;

@Mixin(Minecraft.class)
public class SmoothScrollMaskMixin {

}*///?}
