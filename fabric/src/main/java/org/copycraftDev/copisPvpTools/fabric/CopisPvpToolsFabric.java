package org.copycraftDev.copisPvpTools.fabric;

import org.copycraftDev.copisPvpTools.CopisPvpTools;
import net.fabricmc.api.ModInitializer;

public final class CopisPvpToolsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CopisPvpTools.init();
    }
}
