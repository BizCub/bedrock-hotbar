package io.github.bizcub.bedrockHotbar.config;

public enum XpLevelMode {
    SHADOW, OUTLINE;

    private final String key;

    XpLevelMode() {
        this.key = "text.bedrock_hotbar.enum.XpLevelMode." + this.name();
    }

    @Override
    public String toString() {
        return this.key;
    }
}
