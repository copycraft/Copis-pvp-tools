package org.copycraftDev.copisPvpTools;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

public interface Module {
    void tick();
    /**
     * @param gfx    the GuiGraphics for this frame
     * @param client the Minecraft instance
     */
    void onRenderHUD(GuiGraphics gfx, Minecraft client);
}

