package com.harleylizard.witches_way.common.tree;

import com.mojang.serialization.MapCodec;

public sealed interface Shape permits Column, RandomShapeList, ShapeList {

    MapCodec<? extends Shape> getCodec();

}
