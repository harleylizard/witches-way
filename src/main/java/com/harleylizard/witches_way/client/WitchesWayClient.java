package com.harleylizard.witches_way.client;

import com.harleylizard.witches_way.common.WitchesWayBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public final class WitchesWayClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutoutMipped(),
                WitchesWayBlocks.HANGING_ALDER_LEAVES,
                WitchesWayBlocks.HANGING_HAWTHORN_LEAVES,
                WitchesWayBlocks.HANGING_ROWAN_LEAVES
                );
    }
}
