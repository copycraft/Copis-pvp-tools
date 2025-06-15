package org.copycraftDev.copisPvpTools.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;

public class HealthBorderRendererImpl {
    private static final float MAX_HEALTH = 6f; // 3 hearts
    private static final float MIN_HEALTH = 0.5f; // half heart
    private static final int MIN_BORDER_WIDTH = 5;
    private static final int MAX_BORDER_WIDTH = 30;

    public static void register() {
        HudRenderCallback.EVENT.register((graphics, delta) -> {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player == null) return;

            float health = player.getHealth();

            // Only render if health is less or equal to MAX_HEALTH (3 hearts)
            if (health > MAX_HEALTH) return;

            // Clamp health to [MIN_HEALTH, MAX_HEALTH] to avoid weird values
            float clampedHealth = Math.max(MIN_HEALTH, Math.min(health, MAX_HEALTH));

            // Interpolate border width inversely proportional to health:
            // At MAX_HEALTH → MIN_BORDER_WIDTH, at MIN_HEALTH → MAX_BORDER_WIDTH
            float t = (MAX_HEALTH - clampedHealth) / (MAX_HEALTH - MIN_HEALTH);
            int borderWidth = MIN_BORDER_WIDTH + Math.round(t * (MAX_BORDER_WIDTH - MIN_BORDER_WIDTH));

            var window = Minecraft.getInstance().getWindow();
            int width = window.getGuiScaledWidth();
            int height = window.getGuiScaledHeight();

            // Draw the border with fading alpha
            for (int i = 0; i < borderWidth; i++) {
                int alpha = (int)(144 * (1.0 - (float)i / borderWidth)); // fade effect
                int color = (alpha << 24) | 0xFF0000; // Red with alpha

                // Top border
                graphics.fill(0, i, width, i + 1, color);

                // Bottom border
                graphics.fill(0, height - i - 1, width, height - i, color);

                // Left border
                graphics.fill(i, 0, i + 1, height, color);

                // Right border
                graphics.fill(width - i - 1, 0, width - i, height, color);
            }
        });
    }
}
