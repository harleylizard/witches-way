package com.harleylizard.witches_way.common;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.resources.ResourceLocation;

public final class WitchesWay implements ModInitializer {
    public static final String MOD_ID = "witches-way";

    @Override
    public void onInitialize() {
        WitchesWayBlocks.register();
        WitchesWayItems.register();

        WitchesWayBlockEntities.register();
        WitchesWayFeatures.register();

        StrippableBlockRegistry.register(WitchesWayBlocks.ALDER_LOG, WitchesWayBlocks.STRIPPED_ALDER_LOG);
        StrippableBlockRegistry.register(WitchesWayBlocks.ALDER_WOOD, WitchesWayBlocks.STRIPPED_ALDER_WOOD);

        StrippableBlockRegistry.register(WitchesWayBlocks.HAWTHORN_LOG, WitchesWayBlocks.STRIPPED_HAWTHORN_LOG);
        StrippableBlockRegistry.register(WitchesWayBlocks.HAWTHORN_WOOD, WitchesWayBlocks.STRIPPED_HAWTHORN_WOOD);

        StrippableBlockRegistry.register(WitchesWayBlocks.ROWAN_LOG, WitchesWayBlocks.STRIPPED_ROWAN_LOG);
        StrippableBlockRegistry.register(WitchesWayBlocks.ROWAN_WOOD, WitchesWayBlocks.STRIPPED_ROWAN_WOOD);

    }

    public static ResourceLocation resourceLocation(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

}
