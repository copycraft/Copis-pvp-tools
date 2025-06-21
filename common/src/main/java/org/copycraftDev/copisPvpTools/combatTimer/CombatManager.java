package org.copycraftDev.copisPvpTools.combatTimer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;
import org.copycraftDev.copisPvpTools.Module;
import org.copycraftDev.copisPvpTools.shieldStatusModule.ShieldStatusModule;

import java.util.List;

public class CombatManager {
    private static final List<Module> MODULES = List.of(
            new CombatTimerModule(),
            new ShieldStatusModule()
    );

    public static void tickAll() {
        MODULES.forEach(Module::tick);
    }

    public static void renderAll(GuiGraphics gfx, Minecraft client) {
        MODULES.forEach(m -> m.onRenderHUD(gfx, client));
    }
}
