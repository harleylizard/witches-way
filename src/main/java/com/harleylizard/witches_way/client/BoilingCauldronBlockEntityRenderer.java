package com.harleylizard.witches_way.client;

import com.harleylizard.witches_way.common.blockentity.BoilingCauldronBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.Blocks;

public final class BoilingCauldronBlockEntityRenderer implements BlockEntityRenderer<BoilingCauldronBlockEntity> {

    public BoilingCauldronBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(BoilingCauldronBlockEntity blockEntity, float f, PoseStack stack, MultiBufferSource bufferSource, int i, int j) {
        var fluid = blockEntity.getFluid();

        if (!fluid.isResourceBlank()) {
            stack.pushPose();

            var last = stack.last();

            var consumer = bufferSource.getBuffer(RenderType.translucent());

            var texture = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper().getBlockModel(Blocks.WATER.defaultBlockState()).getParticleIcon();

            var amount = (float) fluid.getAmount() / (float) fluid.getCapacity();

            var fromX = 2.0f / 16.0f;
            var fromZ = 2.0f / 16.0f;
            var toX = 14.0f / 16.0f;
            var toZ = 14.0f / 16.0f;

            var y = (3.0f / 16.0f) + (amount * 10.0f) / 16.0f;
            var color = BiomeColors.getAverageWaterColor(blockEntity.getLevel(), blockEntity.getBlockPos()) | (0xC0 << 24);

            vertex(consumer, last, toX,   y, toZ, texture.getU0(), texture.getV0(), color, i, 0.0f, 1.0f, 0.0f);
            vertex(consumer, last, toX,   y, fromZ, texture.getU0(), texture.getV1(), color, i, 0.0f, 1.0f, 0.0f);
            vertex(consumer, last, fromX, y, fromZ, texture.getU1(), texture.getV1(), color, i, 0.0f, 1.0f, 0.0f);
            vertex(consumer, last, fromX, y, toZ, texture.getU1(), texture.getV0(), color, i, 0.0f, 1.0f, 0.0f);

            stack.popPose();
        }
    }

    public void vertex(VertexConsumer consumer, PoseStack.Pose pose, float x, float y, float z, float u, float v, int color, int light, float nx, float ny, float nz) {
        consumer.addVertex(pose, x, y, z).setUv(u, v).setColor(color).setLight(light).setOverlay(OverlayTexture.NO_OVERLAY).setNormal(pose, nx, ny, nz);
    }

}
