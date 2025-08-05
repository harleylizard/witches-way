package com.harleylizard.witches_way.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public sealed class ShapeBlock extends Block permits HangingLeavesBlock {
    private final Map<BlockState, VoxelShape> shapes = Collections.unmodifiableMap(stateDefinition.getPossibleStates().stream().collect(Collectors.toMap(Function.identity(), this::shapeFrom)));

    public ShapeBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return shapes.get(blockState);
    }

    public VoxelShape shapeFrom(BlockState blockState) {
        return Shapes.block();
    }

}
