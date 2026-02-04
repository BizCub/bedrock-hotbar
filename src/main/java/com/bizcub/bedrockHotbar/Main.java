package com.bizcub.bedrockHotbar;

import com.bizcub.bedrockHotbar.config.Compat;
import com.bizcub.bedrockHotbar.config.Configs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

public class Main {

    public static void renderExperienceLevel(Args args) {
        int color = args.get(4);
        int offset = operation(args.get(3)) - 3;
        boolean number = color == -8323296;

        if (number) args.set(3, offset);
        else args.set(3, -10);
        args.set(5, true);

        if (Compat.isModLoaded(Compat.clothConfigId) && (Configs.getInstance().xpLevelMode == Configs.XpLevelMode.Outline)) {
            if (!number) args.set(3, offset);
            args.set(5, false);
        }
    }

    public static int operation(int x) {
        if (Compat.isModLoaded(Compat.clothConfigId))
            return x - Configs.getInstance().offset;
        return x - BedrockHotbar.DEF_OFFSET;
    }
}
