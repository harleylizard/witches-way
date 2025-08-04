package com.harleylizard.witches_way.common.tree;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public sealed interface Shape permits Column, RandomShapeList, ShapeList {

    void place(WorldGenLevel level, BlockPos blockPos, BlockStateProvider block, RandomSource random);

    MapCodec<? extends Shape> getCodec();

    static void set(WorldGenLevel level, BlockPos blockPos, BlockStateProvider block, RandomSource random) {
        if (canReplace(level.getBlockState(blockPos))) {
            level.setBlock(blockPos, block.getState(random, blockPos), Block.UPDATE_ALL);
        }

    }

    static boolean canReplace(BlockState blockState) {
        return blockState.is(BlockTags.REPLACEABLE) || blockState.is(BlockTags.REPLACEABLE_BY_TREES);
    }

}
