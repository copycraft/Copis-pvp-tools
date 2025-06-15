package org.copycraftDev.copisPvpTools.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.copycraftDev.copisPvpTools.CopisPvpTools;
import org.copycraftDev.copisPvpTools.fabric.HealthBorderRendererImpl;
import org.copycraftDev.copisPvpTools.gg.KillGGClientHandler;
import org.copycraftDev.copisPvpTools.pvp.PotionExpiryNotifier;

public final class CopisPvpToolsFabricClient implements ClientModInitializer {
    private final PotionExpiryNotifier notifier = new PotionExpiryNotifier();
    private final KillGGClientHandler killGGClientHandler = KillGGClientHandler.INSTANCE;

    @Override
    public void onInitializeClient() {
        CopisPvpTools.init();
        HealthBorderRendererImpl.register();

        ClientTickEvents.END_CLIENT_TICK.register(notifier::tick);
        ClientTickEvents.END_CLIENT_TICK.register(client -> killGGClientHandler.onClientTick());
    }
}
