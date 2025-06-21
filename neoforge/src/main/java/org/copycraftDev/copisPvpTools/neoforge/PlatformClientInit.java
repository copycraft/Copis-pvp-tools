package org.copycraftDev.copisPvpTools.neoforge;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import dev.architectury.event.events.client.ClientTickEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.copycraftDev.copisPvpTools.CopisPvpTools;
import org.copycraftDev.copisPvpTools.gg.KillGGClientHandler;
import org.copycraftDev.copisPvpTools.pvp.PotionExpiryNotifier;


/**
 * NeoForge implementation: registers client-side handlers for NeoForge.
 */
@Mod(value = CopisPvpTools.MOD_ID, dist = Dist.CLIENT)
public final class PlatformClientInit {

    private final PotionExpiryNotifier notifier = new PotionExpiryNotifier();
    private final KillGGClientHandler killGGClientHandler = KillGGClientHandler.INSTANCE;

    public PlatformClientInit() {
        // Run common init
        CopisPvpTools.init();
        // Register tick handler
        ClientTickEvent.CLIENT_POST.register(this::onClientTick);
        // Register health border renderer on NeoForge event bus
        NeoForge.EVENT_BUS.register(new HealthBorderRendererNeoForge());
    }

    private void onClientTick(Minecraft minecraft) {
        if (minecraft.player != null) {
            notifier.tick(minecraft);
            killGGClientHandler.onClientTick();
        }
    }
}