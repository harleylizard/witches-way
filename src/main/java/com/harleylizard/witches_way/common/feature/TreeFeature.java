package com.harleylizard.witches_way.common.feature;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public final class TreeFeature extends Feature<TreeFeatureConfiguration> {

    public TreeFeature() {
        super(TreeFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<TreeFeatureConfiguration> featurePlaceContext) {
        var config = featurePlaceContext.config();

        var random = featurePlaceContext.random();

        var level = featurePlaceContext.level();
        var blockPos = featurePlaceContext.origin();

        config.shape().place(level, blockPos, random, config.variables());

        return true;
    }

}
