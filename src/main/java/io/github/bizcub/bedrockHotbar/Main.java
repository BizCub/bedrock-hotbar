package io.github.bizcub.bedrockHotbar;

import io.github.bizcub.bedrockHotbar.config.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

public class Main {
    public static final String MOD_ID = /*$ mod_id*/ "bedrock_hotbar";

    public static final int DEF_OFFSET = 4;
    public static final int MIN_OFFSET = 0;
    public static final int MAX_OFFSET = 100;

    public static void init() {
        if (ConfigHelper.isSimpleConfigLoaded()) {
            Config.set(SimpleConfig.getInstance().get());
        } else if (ConfigHelper.isClothConfigLoaded()) {
            ClothConfig.init();
            Config.set(ClothConfig.getInstance());
        }
    }

    public static void renderExperienceLevel(Args args, boolean shouldChangeHeight) {
        boolean isShadow = Config.get().xpLevelMode() == XpLevelMode.SHADOW;
        int color = args.get(4);
        int offset = args.get(3);

        //~ if >=1.21.6 '8453920' -> '-8323296'
        if (color == -8323296 || !isShadow) {
            if (shouldChangeHeight) {
                offset = operation(offset);
            }
        } else {
            offset = -10;
        }

        args.set(3, offset - 3);
        args.set(5, isShadow);
    }

    public static int operation(int x) {
        return x - Config.get().offset();
    }
}
