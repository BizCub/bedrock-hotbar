package io.github.bizcub.bedrockHotbar.mixin;

import io.github.bizcub.bedrockHotbar.Main;
import io.github.bizcub.bedrockHotbar.config.Config;
import com.mojang.blaze3d.vertex.PoseStack;
/*? >=1.20.2*/ import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChatComponent.class)
public class ChatComponentMixin {

    //~ if >=26.1 'render' -> 'extractRenderState'
    @Redirect(method = "extractRenderState", at = @At(value = "INVOKE", ordinal = 0, target =
            /*? >=1.20.2 {*/ "Lnet/minecraft/client/gui/GuiGraphicsExtractor;guiHeight()I"
            /*?} else*/ //"Lcom/mojang/blaze3d/vertex/PoseStack;translate(FFF)V"
    ))
    private /*? >=1.20.2 {*/ int /*?} else {*/ /*void *//*?}*/ offsetChat
    /*? >=1.20.2 {*/ (GuiGraphicsExtractor instance)
    /*?} else*/ //(PoseStack instance, float f, float g, float h)
    {
        /*? >=1.20.2 {*/ return offset(instance.guiHeight());
     /*?} else*/ //instance.translate(f, offset((int) -g), h);
    }

    @Unique
    private int offset(int offset) {
        return Config.get().chatOffset()
                ? Main.operation(offset)
                : offset;
    }
}
