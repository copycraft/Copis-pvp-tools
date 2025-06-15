package org.copycraftDev.copisPvpTools.gg;

import net.minecraft.client.Minecraft;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class KillGGClientHandler {
    public static final KillGGClientHandler INSTANCE = new KillGGClientHandler();
    private final Minecraft client = Minecraft.getInstance();

    private final Map<UUID, Integer> killTimers = new HashMap<>();
    private static final int DELAY_TICKS = 40; // 2 seconds

    public void onClientTick() {
        if (client.player == null || client.level == null) return;

        Iterator<Map.Entry<UUID, Integer>> it = killTimers.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<UUID, Integer> entry = it.next();
            int time = entry.getValue() - 1;
            if (time <= 0) {
                System.out.println("Sending gg message!");
                client.player.connection.sendChat("gg");
                it.remove();
            } else {
                entry.setValue(time);
            }
        }
    }

    public void onEntityKilled(LivingEntity deadEntity, DamageSource source) {
        if (client.player == null) return;
        if (!(deadEntity instanceof Player)) return;

        if (source.getEntity() != null && source.getEntity().getUUID().equals(client.player.getUUID())) {
            System.out.println("Kill detected, scheduling gg...");
            killTimers.put(deadEntity.getUUID(), DELAY_TICKS);
        }
    }
}
