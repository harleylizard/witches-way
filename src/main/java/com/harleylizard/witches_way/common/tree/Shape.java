package com.harleylizard.witches_way.common.tree;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;

public sealed interface Shape permits Leaves, Log, RandomShapeList, ShapeList {

    BlockPos place(WorldGenLevel level, BlockPos blockPos, RandomSource random, Variables variables);

    MapCodec<? extends Shape> getCodec();

}
