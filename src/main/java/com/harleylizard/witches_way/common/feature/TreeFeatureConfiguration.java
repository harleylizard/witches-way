package com.harleylizard.witches_way.common.feature;

import com.harleylizard.witches_way.common.tree.BlockToShape;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record TreeFeatureConfiguration(
        BlockToShape log,
        BlockToShape leaves,
        BlockStateProvider hangingLeaves) implements FeatureConfiguration {

    public static final Codec<TreeFeatureConfiguration> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(
                BlockToShape.CODEC.fieldOf("log").forGetter(TreeFeatureConfiguration::log),
                BlockToShape.CODEC.fieldOf("leaves").forGetter(TreeFeatureConfiguration::leaves),
                BlockStateProvider.CODEC.fieldOf("hanging_leaves").forGetter(TreeFeatureConfiguration::hangingLeaves)).apply(builder, TreeFeatureConfiguration::new);
    });

}
