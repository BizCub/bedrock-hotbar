package io.github.bizcub.bedrockHotbar.config;

public enum XpLevelMode {
    SHADOW("text.bedrock_hotbar.option.xpLevelMode.shadow"),
    OUTLINE("text.bedrock_hotbar.option.xpLevelMode.outline");

    private final String key;

    XpLevelMode(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return this.key;
    }
}
