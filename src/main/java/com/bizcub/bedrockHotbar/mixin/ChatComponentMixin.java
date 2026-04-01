package com.bizcub.bedrockHotbar.mixin;

import com.bizcub.bedrockHotbar.Main;
import com.bizcub.bedrockHotbar.config.Compat;
import com.bizcub.bedrockHotbar.config.Configs;
import com.mojang.blaze3d.systems.RenderSystem;
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
            /*? >=1.20.2*/ "Lnet/minecraft/client/gui/GuiGraphicsExtractor;guiHeight()I"
            /*? <=1.20.1 && >=1.19.4*/ //"Lcom/mojang/blaze3d/vertex/PoseStack;translate(FFF)V"
            /*? <=1.19.3 && >=1.17.1*/ //"Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V"
            /*? <=1.16.5*/ //"Lcom/mojang/blaze3d/systems/RenderSystem;translatef(FFF)V"
    ))
    private /*? >=1.20.2 {*/ int /*?} else {*/ /*void *//*?}*/ offsetChat
    /*? >=1.20.2*/ (GuiGraphicsExtractor instance)
    /*? <=1.20.1 && >=1.19.4*/ //(PoseStack instance, float f, float g, float h)
    /*? <=1.19.3 && >=1.17.1*/ //(PoseStack instance, double f, double g, double h)
    /*? <=1.16.5*/ //(float f, float g, float h)
    {
        /*? >=1.20.2*/ return offset(instance.guiHeight());
        /*? <=1.20.1 && >=1.17.1*/ //instance.translate(f, offset((int) -g), h);
        /*? <=1.16.5*/ //RenderSystem.translatef(f, offset((int) g), h);
    }

    @Unique
    private int offset(int offset) {
        return (Compat.isClothConfigLoaded() && Configs.getInstance().chatOffset)
                ? Main.operation(offset)
                : offset;
    }
}
