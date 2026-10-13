package io.github.bizcub.bedrockHotbar.mixin;

import io.github.bizcub.bedrockHotbar.Main;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Hud.class)
public class GuiMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;guiHeight()I"), method = {
            "renderSelectedItemName*", "extractPlayerHealth", "extractSelectedItemName", "extractOverlayMessage", "extractVehicleHealth", "extractItemHotbar"
    })
    private int offsetMountHealth(GuiGraphicsExtractor instance) {
        return Main.operation(instance.guiHeight());
    }
}
