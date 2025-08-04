package com.harleylizard.witches_way.common.tree;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;

import java.util.Map;

public final class Shapes {
    public static final BiMap<String, MapCodec<? extends Shape>> SHAPES = HashBiMap.create(Map.of(
            "column", Column.CODEC,
            "shape_list", ShapeList.CODEC,
            "random_shape_list", RandomShapeList.CODEC
    ));

    public static final Codec<Shape> CODEC = Codec.STRING.dispatch(shape -> SHAPES.inverse().get(shape.getCodec()), SHAPES::get);

}
