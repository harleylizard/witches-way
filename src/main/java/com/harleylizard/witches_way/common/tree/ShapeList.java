package com.harleylizard.witches_way.common.tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record ShapeList(List<Shape> shapes) implements Shape {
    public static final MapCodec<ShapeList> CODEC = RecordCodecBuilder.mapCodec(builder -> {

        return builder.group(Shapes.CODEC.listOf().fieldOf("shapes").forGetter(ShapeList::shapes)).apply(builder, ShapeList::new);
    });

    @Override
    public MapCodec<? extends Shape> getCodec() {
        return CODEC;
    }

}
