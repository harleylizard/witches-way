package com.harleylizard.witches_way.common.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record Log(BlockStateProvider block, String variable) implements Shape {
    public static final MapCodec<Log> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(BlockStateProvider.CODEC.fieldOf("block").forGetter(Log::block), Codec.STRING.fieldOf("variable").forGetter(Log::variable)).apply(builder, Log::new));

    @Override
    public BlockPos place(WorldGenLevel level, BlockPos blockPos, RandomSource random, Variables variables) {
        var i = variables.get(variable).sample(random);

        for (var j = 0; j < i; j++) {
            Shapes.set(level, blockPos.above(j), block, random);
        }

        return blockPos.above(i - 1);
    }

    @Override
    public MapCodec<? extends Shape> getCodec() {
        return CODEC;
    }

}
