package com.harleylizard.witches_way.common;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;

public final class WitchesWay implements ModInitializer {
    public static final String MOD_ID = "witches-way";

    @Override
    public void onInitialize() {
        WitchesWayBlocks.register();
        WitchesWayItems.register();

    }

    public static ResourceLocation resourceLocation(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

}
