package io.github.bizcub.bedrockHotbar.mixin;

import io.github.bizcub.bedrockHotbar.Main;
import io.github.bizcub.bedrockHotbar.config.Config;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Hud;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Hud.class)
public abstract class RenderBottomOfTextureMixin {

    @Shadow protected abstract Player getCameraPlayer();
    @Final @Shadow private static Identifier HOTBAR_SELECTION_SPRITE;

    @Inject(method = "extractItemHotbar", at = @At(value = "TAIL"))
    private void renderTexture(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (!Config.get().renderTexture()) return;

        int selectedSlot = getCameraPlayer().getInventory().getSelectedSlot();
        int x = graphics.guiWidth() / 2 - 91 - 1 + selectedSlot * 20;
        int y = Main.operation(graphics.guiHeight());
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, HOTBAR_SELECTION_SPRITE, 24, 23, 0, 0, x, y, 24, 1);
    }
}
