package com.harleylizard.witches_way.common.tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;

public record Column(IntProvider from, IntProvider to) implements Shape {
    public static final MapCodec<Column> CODEC = RecordCodecBuilder.mapCodec(builder -> {

        return builder.group(
                IntProvider.CODEC.fieldOf("from").forGetter(Column::from),
                IntProvider.CODEC.fieldOf("to").forGetter(Column::to)
        ).apply(builder, Column::new);
    });

    @Override
    public MapCodec<? extends Shape> getCodec() {
        return CODEC;
    }

}
