package com.harleylizard.witches_way.common.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;

public record Column(String block, IntProvider tallness) implements Shape {
    public static final MapCodec<Column> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(Codec.STRING.fieldOf("block").forGetter(Column::block), IntProvider.CODEC.fieldOf("tallness").forGetter(Column::tallness)).apply(builder, Column::new));

    @Override
    public BlockPos place(WorldGenLevel level, BlockPos blockPos, RandomSource random, BlockStates blocks) {
        var i = tallness.sample(random);
        
        for (var j = 0; j < i; j++) {
            Shapes.set(level, blockPos.above(j), blocks.get(block), random);
        }

        return blockPos.above(i - 1);
    }

    @Override
    public MapCodec<? extends Shape> getCodec() {
        return CODEC;
    }

}
