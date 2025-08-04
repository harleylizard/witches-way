package com.harleylizard.witches_way.common.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;

import java.util.List;

public record RandomShapeList(List<Entry> entries) implements Shape {
    public static final MapCodec<RandomShapeList> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(Entry.CODEC.listOf().fieldOf("entries").forGetter(RandomShapeList::entries)).apply(builder, RandomShapeList::new));

    @Override
    public BlockPos place(WorldGenLevel level, BlockPos blockPos, RandomSource random, Variables variables) {
        return blockPos;
    }

    @Override
    public MapCodec<? extends Shape> getCodec() {
        return CODEC;
    }

    public record Entry(Shape shape, int weight) {
        public static final Codec<Entry> CODEC = RecordCodecBuilder.create(builder -> builder.group(Shapes.CODEC.fieldOf("shape").forGetter(Entry::shape), Codec.INT.fieldOf("weight").forGetter(Entry::weight)).apply(builder, Entry::new));

    }

}
