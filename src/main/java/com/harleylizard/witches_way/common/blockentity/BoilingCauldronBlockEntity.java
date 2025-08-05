package com.harleylizard.witches_way.common.blockentity;

import com.harleylizard.witches_way.common.WitchesWayBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public final class BoilingCauldronBlockEntity extends BlockEntity {

    public BoilingCauldronBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(WitchesWayBlockEntities.BOILING_CAULDRON, blockPos, blockState);
    }
}
