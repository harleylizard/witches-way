package com.harleylizard.witches_way.common;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public final class WitchesWayConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALDER_TREE = configuredFeatureOf("alder_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HAWTHORN_TREE = configuredFeatureOf("hawthorn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROWAN_TREE = configuredFeatureOf("rowan_tree");

    public static ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureOf(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, WitchesWay.resourceLocation(name));
    }

}
