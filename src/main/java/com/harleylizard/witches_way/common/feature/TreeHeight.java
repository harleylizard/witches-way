package com.harleylizard.witches_way.common.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;

public record TreeHeight(int from, IntProvider to) {
    public static final Codec<TreeHeight> CODEC = RecordCodecBuilder.create(builder -> {

        return builder.group(Codec.INT.fieldOf("from").forGetter(TreeHeight::from), IntProvider.CODEC.fieldOf("to").forGetter(TreeHeight::to)).apply(builder, TreeHeight::new);
    });

    public int get(RandomSource random) {
        return from + to.sample(random);
    }

}
