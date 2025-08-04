package com.harleylizard.witches_way.common.tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;

import java.util.List;

public record ShapeList(List<Shape> shapes) implements Shape {
    public static final MapCodec<ShapeList> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(Shapes.CODEC.listOf().fieldOf("shapes").forGetter(ShapeList::shapes)).apply(builder, ShapeList::new));

    @Override
    public BlockPos place(WorldGenLevel level, BlockPos blockPos, RandomSource random, Variables variables) {
        for (var shape : shapes) {
            blockPos = shape.place(level, blockPos, random, variables);
        }

        return blockPos;
    }

    @Override
    public MapCodec<? extends Shape> getCodec() {
        return CODEC;
    }

}
