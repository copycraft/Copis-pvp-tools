package org.copycraftDev.copisPvpTools.fabric.config;

import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.Screen;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;

import org.copycraftDev.copisPvpTools.gg.GGConfig;
import org.copycraftDev.copisPvpTools.combatTimer.CombatConfig;
import org.copycraftDev.copisPvpTools.pvp.PotionNotifierConfig;
import org.copycraftDev.copisPvpTools.autoHitbox.HitboxConfig;

public class MultiConfigScreen {
    public static Screen create(Screen parent) {
        var healthConfig = HealthBorderConfig.get();

        return YetAnotherConfigLib.createBuilder()
                .title(Component.literal("PvP Tools Settings"))

                // Health Border tab
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("Health Border"))
                        .option(Option.<Float>createBuilder()
                                .name(Component.literal("Max Health"))
                                .description(OptionDescription.of(Component.literal("Health above which no border is shown")))
                                .binding(6f, () -> healthConfig.maxHealth, v -> healthConfig.maxHealth = v)
                                .controller(opt -> FloatSliderControllerBuilder.create(opt).range(1f, 20f).step(0.1f))
                                .build())
                        .option(Option.<Float>createBuilder()
                                .name(Component.literal("Min Health"))
                                .description(OptionDescription.of(Component.literal("Health at which border is thickest")))
                                .binding(0.5f, () -> healthConfig.minHealth, v -> healthConfig.minHealth = v)
                                .controller(opt -> FloatSliderControllerBuilder.create(opt).range(0.1f, 10f).step(0.1f))
                                .build())
                        .option(Option.<Integer>createBuilder()
                                .name(Component.literal("Min Border Width"))
                                .description(OptionDescription.of(Component.literal("Smallest border thickness")))
                                .binding(5, () -> healthConfig.minBorderWidth, v -> healthConfig.minBorderWidth = v)
                                .controller(opt -> IntegerSliderControllerBuilder.create(opt).range(1, 30).step(1))
                                .build())
                        .option(Option.<Integer>createBuilder()
                                .name(Component.literal("Max Border Width"))
                                .description(OptionDescription.of(Component.literal("Largest border thickness")))
                                .binding(30, () -> healthConfig.maxBorderWidth, v -> healthConfig.maxBorderWidth = v)
                                .controller(opt -> IntegerSliderControllerBuilder.create(opt).range(10, 100).step(1))
                                .build())
                        .build())

                // Combat Tracker tab
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("Combat Tracker"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Component.literal("Enable Combat Tracker"))
                                .description(OptionDescription.of(Component.literal("Disables combat state tracking when off")))
                                .binding(true, () -> CombatConfig.enabled, v -> CombatConfig.enabled = v)
                                .controller(BooleanControllerBuilder::create)
                                .build())
                        .option(Option.<Long>createBuilder()
                                .name(Component.literal("Combat Timeout (seconds)"))
                                .description(OptionDescription.of(Component.literal("Seconds to stay in combat after last hit.")))
                                .binding(
                                        CombatConfig.timeoutMs / 1000L,
                                        () -> CombatConfig.timeoutMs / 1000L,
                                        v -> CombatConfig.timeoutMs = v * 1000L)
                                .controller(opt -> LongSliderControllerBuilder.create(opt).range(1L, 60L).step(1L))
                                .build())
                        .build())

                // Auto GG tab
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("Auto GG"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Component.literal("Send GG Message"))
                                .description(OptionDescription.of(Component.literal("Automatically sends '/gg' at the end of matches.")))
                                .binding(true, () -> GGConfig.shouldSendGGMessage, v -> GGConfig.shouldSendGGMessage = v)
                                .controller(BooleanControllerBuilder::create)
                                .build())
                        .build())

                // Combat Hitbox tab
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("Combat Hitbox"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Component.literal("Render Hitboxes During Combat"))
                                .description(OptionDescription.of(Component.literal("Shows red hitboxes on nearby entities while in combat.")))
                                .binding(true, () -> HitboxConfig.enabled, v -> HitboxConfig.enabled = v)
                                .controller(BooleanControllerBuilder::create)
                                .build())
                        .build())

                // Potion Notifier tab
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("Potion Notifier"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Component.literal("Enable Potion Expiry Notifier"))
                                .description(OptionDescription.of(Component.literal("Plays a sound when tracked potions expire.")))
                                .binding(true, () -> PotionNotifierConfig.enabled, v -> PotionNotifierConfig.enabled = v)
                                .controller(BooleanControllerBuilder::create)
                                .build())
                        .build())

                .save(() -> {
                    // Add config saving logic here if needed
                })
                .build()
                .generateScreen(parent);
    }
}
