package com.harleylizard.witches_way.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public final class HangingLeavesBlock extends Block {
    public static final IntegerProperty FACES = IntegerProperty.create("faces", 0, 10);

    public HangingLeavesBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(FACES, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACES);
    }

}
