package com.harleylizard.witches_way.common.feature;

import com.harleylizard.witches_way.common.tree.BlockStates;
import com.harleylizard.witches_way.common.tree.Shape;
import com.harleylizard.witches_way.common.tree.Shapes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record TreeFeatureConfiguration(BlockStates blocks, Shape shape) implements FeatureConfiguration {

    public static final Codec<TreeFeatureConfiguration> CODEC = RecordCodecBuilder.create(builder -> builder.group(BlockStates.CODEC.fieldOf("blocks").forGetter(TreeFeatureConfiguration::blocks), Shapes.CODEC.fieldOf("shape").forGetter(TreeFeatureConfiguration::shape)).apply(builder, TreeFeatureConfiguration::new));

}
