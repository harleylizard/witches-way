package com.harleylizard.witches_way.common;

import com.harleylizard.witches_way.common.feature.TreeWithHangingLeavesFeature;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public final class WitchesWayFeatures {
    public static final Feature<NoneFeatureConfiguration> ALDER_TREE = new TreeWithHangingLeavesFeature();
    public static final Feature<NoneFeatureConfiguration> HAWTHORN_TREE = new TreeWithHangingLeavesFeature();
    public static final Feature<NoneFeatureConfiguration> ROWAN_TREE = new TreeWithHangingLeavesFeature();

    public static void register() {
        register("alder_tree", ALDER_TREE);
        register("hawthorn_tree", HAWTHORN_TREE);
        register("rowan_tree", ROWAN_TREE);

    }

    public static void register(String name, Feature<?> feature) {
        Registry.register(BuiltInRegistries.FEATURE, WitchesWay.resourceLocation(name), feature);
    }

}
