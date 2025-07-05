package org.copycraftDev.copisPvpTools.fabric;

import com.terraformersmc.modmenu.api.*;
import org.copycraftDev.copisPvpTools.fabric.config.MultiConfigScreen;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return MultiConfigScreen::create;
    }
}
