package com.harleylizard.witches_way.common.block;

import com.harleylizard.witches_way.common.WitchesWayItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;

public final class BelladonnaBlock extends CropBlock {

    public BelladonnaBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return WitchesWayItems.BELLADONNA_SEEDS;
    }

}
