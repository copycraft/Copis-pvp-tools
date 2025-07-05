package org.copycraftDev.copisPvpTools.fabric.config;

public class HealthBorderConfig {
    public float maxHealth = 6f;        // 3 hearts
    public float minHealth = 0.5f;      // Half a heart
    public int minBorderWidth = 5;
    public int maxBorderWidth = 30;

    private static final HealthBorderConfig INSTANCE = new HealthBorderConfig();

    public static HealthBorderConfig get() {
        return INSTANCE;
    }
}
