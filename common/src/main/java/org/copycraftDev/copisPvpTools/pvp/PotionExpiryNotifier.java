package org.copycraftDev.copisPvpTools.pvp;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PotionExpiryNotifier {
    private final Set<Holder<MobEffect>> trackedEffects = new HashSet<>();
    private final Set<Holder<MobEffect>> activeEffects = new HashSet<>();

    public PotionExpiryNotifier() {
        trackedEffects.add(MobEffects.DAMAGE_BOOST);     // Strength
        trackedEffects.add(MobEffects.MOVEMENT_SPEED);    // Speed
        trackedEffects.add(MobEffects.REGENERATION);      // Regeneration
        trackedEffects.add(MobEffects.DAMAGE_RESISTANCE); // Resistance
    }

    public void tick(Minecraft minecraft) {
        if (minecraft.player == null) return;

        Set<Holder<MobEffect>> currentEffects = minecraft.player.getActiveEffects().stream()
                .map(MobEffectInstance::getEffect)
                .filter(trackedEffects::contains)
                .collect(Collectors.toSet());

        for (Holder<MobEffect> effect : activeEffects) {
            if (!currentEffects.contains(effect)) {
                playExpirationSound(minecraft);
                break;
            }
        }

        activeEffects.clear();
        activeEffects.addAll(currentEffects);
    }

    private void playExpirationSound(Minecraft minecraft) {
        minecraft.player.playSound(SoundEvents.PLAYER_LEVELUP, 1.0F, 1.0F);
    }
}
