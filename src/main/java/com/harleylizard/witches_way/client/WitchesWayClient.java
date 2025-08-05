package com.harleylizard.witches_way.client;

import com.harleylizard.witches_way.common.WitchesWayBlocks;
import com.harleylizard.witches_way.common.WitchesWayItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;

public final class WitchesWayClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutoutMipped(),
                WitchesWayBlocks.HANGING_ALDER_LEAVES,
                WitchesWayBlocks.ALDER_SAPLING,

                WitchesWayBlocks.HANGING_HAWTHORN_LEAVES,
                WitchesWayBlocks.HAWTHORN_SAPLING,

                WitchesWayBlocks.HANGING_ROWAN_LEAVES,
                WitchesWayBlocks.ROWAN_SAPLING
                );

        var alderLeaves = 0x3B9943;
        var hawthornLeaves = 0x499B51;
        var rowanLeaves = 0x8BAD4C;

        var block = ColorProviderRegistry.BLOCK;
        block.register((blockState, blockAndTintGetter, blockPos, i) -> tintedFoliage(alderLeaves, blockAndTintGetter, blockPos), WitchesWayBlocks.ALDER_LEAVES, WitchesWayBlocks.HANGING_ALDER_LEAVES);
        block.register((blockState, blockAndTintGetter, blockPos, i) -> tintedFoliage(hawthornLeaves, blockAndTintGetter, blockPos), WitchesWayBlocks.HAWTHORN_LEAVES, WitchesWayBlocks.HANGING_HAWTHORN_LEAVES);
        block.register((blockState, blockAndTintGetter, blockPos, i) -> tintedFoliage(rowanLeaves, blockAndTintGetter, blockPos), WitchesWayBlocks.ROWAN_LEAVES, WitchesWayBlocks.HANGING_ROWAN_LEAVES);

        var item = ColorProviderRegistry.ITEM;
        item.register((itemStack, i) -> alderLeaves, WitchesWayItems.ALDER_LEAVES, WitchesWayItems.HANGING_ALDER_LEAVES);
        item.register((itemStack, i) -> hawthornLeaves, WitchesWayItems.HAWTHORN_LEAVES, WitchesWayItems.HANGING_HAWTHORN_LEAVES);
        item.register((itemStack, i) -> rowanLeaves, WitchesWayItems.ROWAN_LEAVES, WitchesWayItems.HANGING_ROWAN_LEAVES);

    }

    public static int tintedFoliage(int color, BlockAndTintGetter getter, BlockPos blockPos) {
        var i = BiomeColors.getAverageFoliageColor(getter, blockPos);
        var leafR = (float) ((i >> 16) & 0xFF) / 255.0f;
        var leafG = (float) ((i >> 8) & 0xFF) / 255.0f;
        var leafB = (float) (i & 0xFF) / 255.0f;

        var r = (float) ((color >> 16) & 0xFF) / 255.0f;
        var g = (float) ((color >> 8) & 0xFF) / 255.0f;
        var b = (float) (color & 0xFF) / 255.0f;

        var blend = 0.325f;
        r = blend(r, leafR, blend);
        g = blend(g, leafG, blend);
        b = blend(b, leafB, blend);

        var u = ((int) (r * 255.0f)) & 0xFF;
        var v = ((int) (g * 255.0f)) & 0xFF;
        var w = ((int) (b * 255.0f)) & 0xFF;

        return (u << 16) | (v << 8) | w;
    }

    public static float blend(float start, float end, float amount) {
        return start + ((end - start) * amount);
    }

}
