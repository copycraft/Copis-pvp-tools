package org.copycraftDev.copisPvpTools.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.copycraftDev.copisPvpTools.gg.KillGGClientHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "die", at = @At("HEAD"))
    private void onDie(DamageSource source, CallbackInfo ci) {
        LivingEntity self = (LivingEntity)(Object)this;
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        if (self instanceof Player) {
            if (source.getEntity() != null && source.getEntity().getUUID().equals(mc.player.getUUID())) {
                KillGGClientHandler.INSTANCE.onEntityKilled(self, source);
            }
        }
    }
}
