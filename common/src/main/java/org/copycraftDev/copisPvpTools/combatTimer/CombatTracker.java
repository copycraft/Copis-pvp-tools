package org.copycraftDev.copisPvpTools.combatTimer;

public class CombatTracker {
    private static long lastCombatTime = 0;

    public static void markCombat() {
        if (!CombatConfig.enabled) return;
        lastCombatTime = System.currentTimeMillis();
    }

    public static boolean isInCombat() {
        if (!CombatConfig.enabled) return false;
        return System.currentTimeMillis() - lastCombatTime < CombatConfig.timeoutMs;
    }

    public static long getCombatDurationMs() {
        return isInCombat()
                ? System.currentTimeMillis() - lastCombatTime
                : 0;
    }
}
