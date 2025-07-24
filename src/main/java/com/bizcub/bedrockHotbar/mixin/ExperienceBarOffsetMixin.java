package com.bizcub.bedrockHotbar.mixin;

//? if >=1.21.6 {
import com.bizcub.bedrockHotbar.Offset;
import net.minecraft.client.gui.hud.bar.ExperienceBar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ExperienceBar.class)
public class ExperienceBarOffsetMixin {

    @ModifyVariable(method = "renderBar", at = @At(value = "STORE"), ordinal = 1)
    public int offsetExperienceBar(int value) {
        return Offset.operation(value);
    }
}
//?} elif <=1.21.5 {
/*import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(InGameHud.class)
public class ExperienceBarOffsetMixin {}
*///?}
