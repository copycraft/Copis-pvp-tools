package org.copycraftDev.copisPvpTools.combatTimer;

public class CombatConfig {
    /** Whether to actually track combat at all */
    public static volatile boolean enabled = true;
    /** How many milliseconds after last hit you remain “in combat” */
    public static volatile long timeoutMs = 8000;

    private CombatConfig() {}
    public static CombatConfig get() { return new CombatConfig(); }
}
