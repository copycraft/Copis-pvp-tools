package org.copycraftDev.copisPvpTools.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import org.copycraftDev.copisPvpTools.fabric.config.HealthBorderConfig;

public class HealthBorderRendererImpl {
    public static void register() {
        HudRenderCallback.EVENT.register((graphics, delta) -> {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player == null) return;

            // Load configuration values
            HealthBorderConfig config = HealthBorderConfig.get();
            float maxHealth = config.maxHealth;
            float minHealth = config.minHealth;
            int minBorderWidth = config.minBorderWidth;
            int maxBorderWidth = config.maxBorderWidth;

            float health = player.getHealth();

            // Only render if health is less or equal to maxHealth
            if (health > maxHealth) return;

            // Clamp health to [minHealth, maxHealth]
            float clampedHealth = Math.max(minHealth, Math.min(health, maxHealth));

            // Interpolate border width inversely proportional to health
            float t = (maxHealth - clampedHealth) / (maxHealth - minHealth);
            int borderWidth = minBorderWidth + Math.round(t * (maxBorderWidth - minBorderWidth));

            var window = Minecraft.getInstance().getWindow();
            int width = window.getGuiScaledWidth();
            int height = window.getGuiScaledHeight();

            // Draw the border with fading alpha
            for (int i = 0; i < borderWidth; i++) {
                int alpha = (int) (144 * (1.0 - (float) i / borderWidth)); // fade effect
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
