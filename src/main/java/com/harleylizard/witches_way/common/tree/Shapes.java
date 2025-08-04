package com.harleylizard.witches_way.common.tree;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public final class Shapes {
    public static final BiMap<String, MapCodec<? extends Shape>> SHAPES = HashBiMap.create();

    public static Codec<Shape> CODEC = Codec.STRING.dispatch(shape -> Shapes.SHAPES.inverse().get(shape.getCodec()), Shapes.SHAPES::get);

    static {
        SHAPES.put("column", Column.CODEC);
        SHAPES.put("leaves", Leaves.CODEC);
        SHAPES.put("list", ShapeList.CODEC);
        SHAPES.put("random_shape_list", RandomShapeList.CODEC);

    }

    public static void set(WorldGenLevel level, BlockPos blockPos, BlockStateProvider block, RandomSource random) {
        if (canReplace(level.getBlockState(blockPos))) {
            level.setBlock(blockPos, block.getState(random, blockPos), Block.UPDATE_ALL);
        }

    }

    public static boolean canReplace(BlockState blockState) {
        return blockState.is(BlockTags.REPLACEABLE) || blockState.is(BlockTags.REPLACEABLE_BY_TREES);
    }

}
