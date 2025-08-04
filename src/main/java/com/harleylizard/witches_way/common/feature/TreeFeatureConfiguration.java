package com.harleylizard.witches_way.common.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record TreeFeatureConfiguration(
        TreeHeight height,
        BlockStateProvider log,
        BlockStateProvider leaves,
        BlockStateProvider hangingLeaves) implements FeatureConfiguration {

    public static final Codec<TreeFeatureConfiguration> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(
                TreeHeight.CODEC.fieldOf("height").forGetter(TreeFeatureConfiguration::height),
                BlockStateProvider.CODEC.fieldOf("log").forGetter(TreeFeatureConfiguration::log),
                BlockStateProvider.CODEC.fieldOf("leaves").forGetter(TreeFeatureConfiguration::leaves),
                BlockStateProvider.CODEC.fieldOf("hanging_leaves").forGetter(TreeFeatureConfiguration::hangingLeaves)).apply(builder, TreeFeatureConfiguration::new);
    });

}
