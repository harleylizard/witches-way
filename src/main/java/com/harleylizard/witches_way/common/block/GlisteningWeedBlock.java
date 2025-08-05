package com.harleylizard.witches_way.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class GlisteningWeedBlock extends Block {
    public static final VoxelShape SHAPE = Block.box(2.0d, 0.0d, 2.0d, 14.0d, 13.0d, 14.0d);

    public GlisteningWeedBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        var offset = blockState.getOffset(blockGetter, blockPos);

        return SHAPE.move(offset.x, offset.y, offset.z);
    }
}
