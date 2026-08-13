package io.github.bizcub.bedrockHotbar.config;

public enum XpLevelMode {
    SHADOW("text.bedrock_hotbar.enum.XpLevelMode.SHADOW"),
    OUTLINE("text.bedrock_hotbar.enum.XpLevelMode.OUTLINE");

    private final String key;

    XpLevelMode(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return this.key;
    }
}
