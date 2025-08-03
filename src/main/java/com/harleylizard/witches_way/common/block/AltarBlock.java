package com.harleylizard.witches_way.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public final class AltarBlock extends Block {
    public static final BooleanProperty CLOTHED = BooleanProperty.create("clothed");

    public AltarBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(CLOTHED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CLOTHED);
    }

}
