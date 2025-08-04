package com.harleylizard.witches_way.common.block;

import com.harleylizard.witches_way.common.WitchesWayBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public final class HangingLeavesBlock extends Block {
    public static final IntegerProperty FACES = IntegerProperty.create("faces", 0, 256);

    public HangingLeavesBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(FACES, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACES);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return setFaces(defaultBlockState(), 1 << toHorizontal(blockPlaceContext.getHorizontalDirection().ordinal()));
    }

    @Override
    protected boolean isRandomlyTicking(BlockState blockState) {
        return super.isRandomlyTicking(blockState) && getFaces(blockState) > 0;
    }

    @Override
    protected void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        var faces = getFaces(blockState);

        for (var direction : Direction.Plane.HORIZONTAL) {
            var i = direction.ordinal();

            if (stage(faces, i) > 0 && randomSource.nextInt(15) == 0) {

                growTo(serverLevel, blockPos.below(), i);
            }

        }
    }

    public void growTo(Level level, BlockPos blockPos, int i) {
        var blockState = level.getBlockState(blockPos);

        var value = 2 << toHorizontal(i);

        if (blockState.is(WitchesWayBlockTags.HANGING_LEAVES_CAN_REPLACE)) {
            level.setBlock(blockPos, setFaces(defaultBlockState(), value), UPDATE_ALL);

        } else if (blockState.is(this)) {
            var faces = getFaces(blockState);

            if (stage(faces, i) == 0) {
                level.setBlock(blockPos, setFaces(blockState, faces | value), UPDATE_ALL);
            }

        }
    }

    public int stage(int i, int j) {
        return (i >> toHorizontal(j)) & 3;
    }

    public int toHorizontal(int i) {
        return 2 * (i - 2);
    }

    public int getFaces(BlockState blockState) {
        return blockState.getValue(FACES);
    }

    public BlockState setFaces(BlockState blockState, int i) {
        return blockState.setValue(FACES, i);
    }

}
