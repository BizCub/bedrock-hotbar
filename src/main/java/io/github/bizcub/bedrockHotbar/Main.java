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

    public static void renderExperienceLevel(Args args) {
        int color = args.get(4);
        int offset = operation(args.get(3)) - 3;
        boolean number = color == -8323296;

        if (number) args.set(3, offset);
        else args.set(3, -10);
        args.set(5, true);

        if (Config.get().xpLevelMode() == XpLevelMode.OUTLINE) {
            if (!number) args.set(3, offset);
            args.set(5, false);
        }
    }

    public static int operation(int x) {
        return x - Config.get().offset();
    }
}
