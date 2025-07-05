package org.copycraftDev.copisPvpTools.pvp;

public class PotionNotifierConfig {
    public static volatile boolean enabled = true;

    private PotionNotifierConfig() {}
    public static PotionNotifierConfig get() { return new PotionNotifierConfig(); }
}
