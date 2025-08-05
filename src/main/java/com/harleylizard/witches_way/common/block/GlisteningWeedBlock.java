package com.harleylizard.witches_way.common.block;

import com.harleylizard.witches_way.common.WitchesWayBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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

    @Override
    protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return canGrowOn(levelReader, blockPos.below());
    }

    public boolean canGrowOn(LevelReader level, BlockPos blockPos) {
        var blockState = level.getBlockState(blockPos);

        return blockState.isFaceSturdy(level, blockPos, Direction.UP) && blockState.is(WitchesWayBlockTags.GLISTENING_WEED_GROWS_ON);
    }

    @Override
    protected BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        return canGrowOn(levelAccessor, blockPos.below()) ? blockState : Blocks.AIR.defaultBlockState();
    }

}
