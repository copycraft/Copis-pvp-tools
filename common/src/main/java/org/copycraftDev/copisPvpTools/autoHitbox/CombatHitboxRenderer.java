package org.copycraftDev.copisPvpTools.autoHitbox;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import org.copycraftDev.copisPvpTools.combatTimer.CombatTracker;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.phys.AABB;
import net.minecraft.client.renderer.debug.DebugRenderer;

public class CombatHitboxRenderer {
    public static void renderCombatBoxes(PoseStack poseStack, MultiBufferSource buffer, float partialTick) {
        var client = Minecraft.getInstance();

        if (!HitboxConfig.enabled || !CombatTracker.isInCombat()) return;

        var level = client.level;
        var camera = client.gameRenderer.getMainCamera();
        var camPos = camera.getPosition();

        for (var entity : level.entitiesForRendering()) {
            if (entity == client.player) continue;

            AABB box = entity.getBoundingBox().move(-camPos.x, -camPos.y, -camPos.z);

            LevelRenderer.renderLineBox(
                    poseStack,
                    buffer.getBuffer(RenderType.lines()),
                    box,
                    1.0f, 0.0f, 0.0f, 1.0f
            );
        }
    }
}
