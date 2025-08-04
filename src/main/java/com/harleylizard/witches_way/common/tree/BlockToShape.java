package com.harleylizard.witches_way.common.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record BlockToShape(BlockStateProvider block, Shape shape) {
    public static final Codec<BlockToShape> CODEC = RecordCodecBuilder.create(builder -> {

        return builder.group(
                BlockStateProvider.CODEC.fieldOf("block").forGetter(BlockToShape::block), Shapes.CODEC.fieldOf("shape").forGetter(BlockToShape::shape)
        ).apply(builder, BlockToShape::new);
    });

    public void place(WorldGenLevel level, BlockPos blockPos, RandomSource random) {
        shape.place(level, blockPos, block, random);
    }

}
