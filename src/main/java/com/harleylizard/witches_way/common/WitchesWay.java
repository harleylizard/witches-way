package com.harleylizard.witches_way.common;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

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

        LootTableEvents.MODIFY.register((resourceKey, builder, lootTableSource, provider) -> {
            if (Blocks.SHORT_GRASS.getLootTable() == resourceKey && lootTableSource.isBuiltin()) {
                builder.withPool(LootPool.lootPool().with(LootItem.lootTableItem(WitchesWayItems.BELLADONNA_SEEDS).build()).conditionally(LootItemRandomChanceCondition.randomChance(0.75f / 16.0f).build()));
                builder.withPool(LootPool.lootPool().with(LootItem.lootTableItem(WitchesWayItems.MANDRAKE_SEEDS).build()).conditionally(LootItemRandomChanceCondition.randomChance(0.75f / 16.0f).build()));
            }

        });

        FluidStorage.SIDED.registerForBlockEntity((cauldron, direction) -> direction != null && direction.getAxis().isHorizontal() ? cauldron.getFluid() : null, WitchesWayBlockEntities.BOILING_CAULDRON);

    }

    public static ResourceLocation resourceLocation(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

}
