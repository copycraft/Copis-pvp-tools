package org.copycraftDev.copisPvpTools.neoforge;

import net.neoforged.neoforge.common.NeoForge;
import org.copycraftDev.copisPvpTools.CopisPvpTools;
import net.neoforged.fml.common.Mod;

@Mod(CopisPvpTools.MOD_ID)
public final class CopisPvpToolsNeoForge {
    public CopisPvpToolsNeoForge() {
        // Run our common setup.
        CopisPvpTools.init();
        NeoForge.EVENT_BUS.register(new HealthBorderRendererNeoForge());
    }
}
