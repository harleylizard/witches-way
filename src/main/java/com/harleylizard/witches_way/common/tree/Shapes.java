package com.harleylizard.witches_way.common.tree;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;

public final class Shapes {
    public static final BiMap<String, MapCodec<? extends Shape>> SHAPES = HashBiMap.create();

    public static Codec<Shape> CODEC = Codec.STRING.dispatch(shape -> Shapes.SHAPES.inverse().get(shape.getCodec()), Shapes.SHAPES::get);

    static {
        SHAPES.put("column", Column.CODEC);
        SHAPES.put("shape_list", ShapeList.CODEC);
        SHAPES.put("random_shape_list", RandomShapeList.CODEC);

    }
}
