package com.harleylizard.witches_way.common.block;

import com.harleylizard.witches_way.common.WitchesWayItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;

public final class MandrakeBlock extends CropBlock {

    public MandrakeBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return WitchesWayItems.MANDRAKE_SEEDS;
    }

}
