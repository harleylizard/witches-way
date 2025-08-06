package com.harleylizard.witches_way.common;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;

public interface IsBlockStateTag {

    boolean witchesWay$is(BlockStateTag tag, RandomSource random, BlockPos blockPos);
}
