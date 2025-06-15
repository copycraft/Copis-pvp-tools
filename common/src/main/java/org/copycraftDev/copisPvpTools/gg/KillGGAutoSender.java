package org.copycraftDev.copisPvpTools.gg;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class KillGGAutoSender {
    public static final Map<UUID, Integer> killTimers = new ConcurrentHashMap<>();

    public static void tick() {
        if (!GGConfig.shouldSendGGMessage) {
            killTimers.clear();
            return;
        }

        killTimers.forEach((uuid, ticksLeft) -> {
            System.out.println("[KillGGAutoSender] Timer for " + uuid + ": " + ticksLeft);
            if (ticksLeft > 0) {
                killTimers.put(uuid, ticksLeft - 1);
            }
        });
    }

    public static void onEntityKilled(LivingEntity deadEntity, DamageSource source, UUID localPlayerUUID) {
        if (!GGConfig.shouldSendGGMessage) return;
        if (!(deadEntity instanceof Player)) return;

        if (source.getEntity() != null && source.getEntity().getUUID().equals(localPlayerUUID)) {
            System.out.println("[KillGGAutoSender] Detected kill on player " + deadEntity.getUUID() + ", starting timer");
            killTimers.put(deadEntity.getUUID(), 40);
        }
    }
}
