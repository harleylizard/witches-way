package com.harleylizard.witches_way.common;

import com.harleylizard.witches_way.common.feature.TreeFeature;
import com.harleylizard.witches_way.common.feature.TreeFeatureConfiguration;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;

public final class WitchesWayFeatures {
    public static final Feature<TreeFeatureConfiguration> TREE = new TreeFeature();

    public static void register() {
        register("tree", TREE);

    }

    public static void register(String name, Feature<?> feature) {
        Registry.register(BuiltInRegistries.FEATURE, WitchesWay.resourceLocation(name), feature);
    }

}
