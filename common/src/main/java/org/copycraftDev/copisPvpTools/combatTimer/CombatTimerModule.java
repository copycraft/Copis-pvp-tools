package org.copycraftDev.copisPvpTools.combatTimer;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import org.copycraftDev.copisPvpTools.Module;

public class CombatTimerModule implements Module {
    @Override public void tick() {}

    @Override
    public void onRenderHUD(GuiGraphics gfx, Minecraft client) {
        if (!CombatTracker.isInCombat()) return;

        long seconds = CombatTracker.getCombatDurationMs() / 1000;
        String text = "Combat: " + seconds + "s";
        FormattedCharSequence seq = FormattedCharSequence.forward(text, Style.EMPTY);

        client
                .font
                .drawInBatch8xOutline(
                        seq,
                        10f,
                        10f,
                        0xFFFF4444,
                        0xFF000000,
                        gfx.pose().last().pose(),
                        client.renderBuffers().bufferSource(),
                        0xF000F0
                );
        client.renderBuffers().bufferSource().endBatch();
    }
}
