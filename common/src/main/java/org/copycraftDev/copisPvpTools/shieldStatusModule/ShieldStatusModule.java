package org.copycraftDev.copisPvpTools.shieldStatusModule;

import com.mojang.blaze3d.vertex.PoseStack; // still needed by Module, but not by this method
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Items;
import org.copycraftDev.copisPvpTools.Module;

public class ShieldStatusModule implements Module {
    @Override public void tick() {}

    @Override
    public void onRenderHUD(GuiGraphics gfx, Minecraft client) {
        if (client.player == null) return;
        if (client.player.getCooldowns().isOnCooldown(Items.SHIELD)) {
            // fill(x1, y1, x2, y2, ARGB)
            gfx.fill(5, 30, 25, 50, 0x88FF0000);
        }
    }
}
