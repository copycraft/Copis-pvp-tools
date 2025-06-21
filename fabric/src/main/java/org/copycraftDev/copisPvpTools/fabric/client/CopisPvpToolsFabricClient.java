package org.copycraftDev.copisPvpTools.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import org.copycraftDev.copisPvpTools.CopisPvpTools;
import org.copycraftDev.copisPvpTools.fabric.HealthBorderRendererImpl;
import org.copycraftDev.copisPvpTools.gg.KillGGClientHandler;
import org.copycraftDev.copisPvpTools.pvp.PotionExpiryNotifier;
import org.copycraftDev.copisPvpTools.combatTimer.CombatManager;

public final class CopisPvpToolsFabricClient implements ClientModInitializer {
    private final PotionExpiryNotifier notifier = new PotionExpiryNotifier();
    private final KillGGClientHandler killGGClientHandler = KillGGClientHandler.INSTANCE;

    @Override
    public void onInitializeClient() {
        CopisPvpTools.init();
        HealthBorderRendererImpl.register();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                CombatManager.tickAll();
                notifier.tick(client);
                killGGClientHandler.onClientTick();
            }
        });

        HudRenderCallback.EVENT.register((GuiGraphics gfx, DeltaTracker deltaTracker) -> {
            CombatManager.renderAll(gfx, Minecraft.getInstance());
        });
    }
}
