package com.harleylizard.witches_way.common.tree;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;

public sealed interface Shape permits Leaves, Column, RandomShapeList, ShapeList {

    BlockPos place(WorldGenLevel level, BlockPos blockPos, RandomSource random, BlockStates blocks);

    MapCodec<? extends Shape> getCodec();

}
