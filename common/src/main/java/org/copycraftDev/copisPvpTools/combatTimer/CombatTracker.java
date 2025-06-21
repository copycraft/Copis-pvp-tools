package org.copycraftDev.copisPvpTools.combatTimer;

public class CombatTracker {
    private static long lastCombatTime = 0;
    public static final long COMBAT_TIMEOUT_MS = 8000;

    public static void markCombat() {
        lastCombatTime = System.currentTimeMillis();
    }

    public static boolean isInCombat() {
        return System.currentTimeMillis() - lastCombatTime < COMBAT_TIMEOUT_MS;
    }

    public static long getCombatDurationMs() {
        return isInCombat() ? System.currentTimeMillis() - lastCombatTime : 0;
    }
}
