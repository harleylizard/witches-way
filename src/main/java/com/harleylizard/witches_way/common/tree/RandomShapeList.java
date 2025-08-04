package com.harleylizard.witches_way.common.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record RandomShapeList(List<Entry> entries) implements Shape {
    public static final MapCodec<RandomShapeList> CODEC = RecordCodecBuilder.mapCodec(builder -> {

        return builder.group(Entry.CODEC.listOf().fieldOf("entries").forGetter(RandomShapeList::entries)).apply(builder, RandomShapeList::new);
    });

    @Override
    public MapCodec<? extends Shape> getCodec() {
        return CODEC;
    }

    public record Entry(Shape shape, int weight) {
        public static final Codec<Entry> CODEC = RecordCodecBuilder.create(builder -> {

            return builder.group(
                    Shapes.CODEC.fieldOf("shape").forGetter(Entry::shape),
                    Codec.INT.fieldOf("weight").forGetter(Entry::weight)
            ).apply(builder, Entry::new);
        });

    }

}
