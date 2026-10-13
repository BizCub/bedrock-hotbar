package io.github.bizcub.bedrockHotbar.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.bizcub.bedrockHotbar.Main;
import io.github.bizcub.bedrockHotbar.config.Config;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ChatComponent.class)
public class ChatComponentMixin {

    @ModifyExpressionValue(method = "extractRenderState", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;guiHeight()I"))
    private int offsetChat(int original) {
        return offset(original);
    }

    @Unique
    private int offset(int offset) {
        return Config.get().chatOffset()
                ? Main.operation(offset)
                : offset;
    }
}
