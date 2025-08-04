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

        config.shape().place(featurePlaceContext.level(), featurePlaceContext.origin(), featurePlaceContext.random(), config.blocks());

        return true;
    }

}
