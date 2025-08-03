package com.harleylizard.witches_way.common;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class WitchesWayItems {

    public static void register() {

    }

    public static void register(String name, Item item) {
        Registry.register(BuiltInRegistries.ITEM, WitchesWay.resourceLocation(name), item);
    }

    public static Item blockItem(Block block) {
        return new BlockItem(block, new Item.Properties());
    }

}
