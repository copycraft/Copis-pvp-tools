package org.copycraftDev.copisPvpTools.autoHitbox;

public class HitboxConfig {
    public static volatile boolean enabled = true;

    private HitboxConfig() {}
    public static HitboxConfig get() {
        return new HitboxConfig();
    }
}
