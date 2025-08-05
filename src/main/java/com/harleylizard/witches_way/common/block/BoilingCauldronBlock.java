package com.harleylizard.witches_way.common.block;

import com.harleylizard.witches_way.common.WitchesWayBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public final class BoilingCauldronBlock extends Block implements EntityBlock {

    public BoilingCauldronBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return WitchesWayBlockEntities.BOILING_CAULDRON.create(blockPos, blockState);
    }

}
