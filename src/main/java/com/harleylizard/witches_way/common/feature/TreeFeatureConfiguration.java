package com.harleylizard.witches_way.common.feature;

import com.harleylizard.witches_way.common.tree.Shape;
import com.harleylizard.witches_way.common.tree.Shapes;
import com.harleylizard.witches_way.common.tree.Variables;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record TreeFeatureConfiguration(Variables variables, Shape shape, BlockStateProvider hangingLeaves) implements FeatureConfiguration {

    public static final Codec<TreeFeatureConfiguration> CODEC = RecordCodecBuilder.create(builder -> builder.group(Variables.CODEC.fieldOf("variables").forGetter(TreeFeatureConfiguration::variables), Shapes.CODEC.fieldOf("shape").forGetter(TreeFeatureConfiguration::shape), BlockStateProvider.CODEC.fieldOf("hanging_leaves").forGetter(TreeFeatureConfiguration::hangingLeaves)).apply(builder, TreeFeatureConfiguration::new));

}
