package org.copycraftDev.copisPvpTools.neoforge;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

public final class HealthBorderRendererNeoForge {
    private static final float MAX_HEALTH = 6f; // 3 hearts
    private static final float MIN_HEALTH = 0.5f; // half heart
    private static final int MIN_BORDER_WIDTH = 5;
    private static final int MAX_BORDER_WIDTH = 60;

    @SubscribeEvent
    public void onRenderGui(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null) return;

        float health = player.getHealth();
        if (health > MAX_HEALTH) return;

        float clampedHealth = Math.max(MIN_HEALTH, Math.min(health, MAX_HEALTH));
        float t = (MAX_HEALTH - clampedHealth) / (MAX_HEALTH - MIN_HEALTH);
        int borderWidth = MIN_BORDER_WIDTH + Math.round(t * (MAX_BORDER_WIDTH - MIN_BORDER_WIDTH));

        int width = mc.getWindow().getGuiScaledWidth();
        int height = mc.getWindow().getGuiScaledHeight();

        GuiGraphics graphics = event.getGuiGraphics();
        PoseStack poseStack = graphics.pose();

        for (int i = 0; i < borderWidth; i++) {
            int alpha = (int)(144 * (1.0 - (float)i / borderWidth)); // fade alpha
            int color = (alpha << 24) | 0xFF0000; // red with alpha

            // Top border
            fill(graphics, 0, i, width, i + 1, color);
            // Bottom border
            fill(graphics, 0, height - i - 1, width, height - i, color);
            // Left border
            fill(graphics, i, 0, i + 1, height, color);
            // Right border
            fill(graphics, width - i - 1, 0, width - i, height, color);
        }
    }

    private void fill(GuiGraphics graphics, int x1, int y1, int x2, int y2, int color) {
        graphics.fill(x1, y1, x2, y2, color);
    }
}
