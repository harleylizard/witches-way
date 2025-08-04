package com.harleylizard.witches_way.common.tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record Column(IntProvider from, IntProvider to) implements Shape {
    public static final MapCodec<Column> CODEC = RecordCodecBuilder.mapCodec(builder -> {

        return builder.group(
                IntProvider.CODEC.fieldOf("from").forGetter(Column::from),
                IntProvider.CODEC.fieldOf("to").forGetter(Column::to)
        ).apply(builder, Column::new);
    });

    @Override
    public void place(WorldGenLevel level, BlockPos blockPos, BlockStateProvider block, RandomSource random) {
        var height = from.sample(random) + to.sample(random);

        for (var i = 0; i < height; i++) {
            Shape.set(level, blockPos.above(i), block, random);
        }

    }

    @Override
    public MapCodec<? extends Shape> getCodec() {
        return CODEC;
    }

}
