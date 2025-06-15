package org.copycraftDev.copisPvpTools.neoforge;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import dev.architectury.event.events.client.ClientTickEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.copycraftDev.copisPvpTools.CopisPvpTools;
import org.copycraftDev.copisPvpTools.gg.KillGGClientHandler;
import org.copycraftDev.copisPvpTools.pvp.PotionExpiryNotifier;

@Mod(value = CopisPvpTools.MOD_ID, dist = Dist.CLIENT)
public final class CopisPvpToolsClient {

    private final PotionExpiryNotifier notifier = new PotionExpiryNotifier();
    private final KillGGClientHandler killGGClientHandler = KillGGClientHandler.INSTANCE;

    public CopisPvpToolsClient() {
        ClientTickEvent.CLIENT_POST.register(this::onClientTick);
        NeoForge.EVENT_BUS.register(new HealthBorderRendererNeoForge());
    }

    private void onClientTick(Minecraft minecraft) {
        if (minecraft.player != null) {
            notifier.tick(minecraft);
            killGGClientHandler.onClientTick();
        }
    }
}