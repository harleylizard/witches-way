package com.harleylizard.witches_way.common.block;

import com.harleylizard.witches_way.common.WitchesWayBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public final class BoilingCauldronBlock extends Block implements EntityBlock {
    private static final VoxelShape SHAPE = Shapes.or(
            Shapes.box(0.0625, 0.125, 0.0625, 0.9375, 0.75, 0.9375),
            Shapes.box(0.6875, 0, 0.6875, 0.8125, 0.125, 0.8125),
            Shapes.box(0.1875, 0, 0.6875, 0.3125, 0.125, 0.8125),
            Shapes.box(0.1875, 0, 0.1875, 0.3125, 0.125, 0.3125),
            Shapes.box(0.6875, 0, 0.1875, 0.8125, 0.125, 0.3125),
            Shapes.box(0.125, 0.75, 0.125, 0.875, 0.9375, 0.875)
    );

    public BoilingCauldronBlock(Properties properties) {
        super(properties);
        registerDefaultState(set(getStateDefinition().any(), Direction.Axis.X));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_AXIS);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return set(defaultBlockState(), blockPlaceContext.getHorizontalDirection().getAxis());
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return WitchesWayBlockEntities.BOILING_CAULDRON.create(blockPos, blockState);
    }

    public static BlockState set(BlockState blockState, Direction.Axis axis) {
        return blockState.setValue(BlockStateProperties.HORIZONTAL_AXIS, axis);
    }

    public static Direction.Axis get(BlockState blockState) {
        return blockState.getValue(BlockStateProperties.HORIZONTAL_AXIS);
    }

}
