package com.harleylizard.witches_way.common;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public final class WitchesWayItems {
    public static final Item ALDER_LOG = blockItem(WitchesWayBlocks.ALDER_LOG);
    public static final Item ALDER_WOOD = blockItem(WitchesWayBlocks.ALDER_WOOD);
    public static final Item STRIPPED_ALDER_LOG = blockItem(WitchesWayBlocks.STRIPPED_ALDER_LOG);
    public static final Item STRIPPED_ALDER_WOOD = blockItem(WitchesWayBlocks.STRIPPED_ALDER_WOOD);
    public static final Item ALDER_PLANKS = blockItem(WitchesWayBlocks.ALDER_PLANKS);
    public static final Item ALDER_STAIRS = blockItem(WitchesWayBlocks.ALDER_STAIRS);
    public static final Item ALDER_LEAVES = blockItem(WitchesWayBlocks.ALDER_LEAVES);
    public static final Item HANGING_ALDER_LEAVES = blockItem(WitchesWayBlocks.HANGING_ALDER_LEAVES);
    public static final Item ALDER_SAPLING = blockItem(WitchesWayBlocks.ALDER_SAPLING);

    public static final Item HAWTHORN_LOG = blockItem(WitchesWayBlocks.HAWTHORN_LOG);
    public static final Item HAWTHORN_WOOD = blockItem(WitchesWayBlocks.HAWTHORN_WOOD);
    public static final Item STRIPPED_HAWTHORN_LOG = blockItem(WitchesWayBlocks.STRIPPED_HAWTHORN_LOG);
    public static final Item STRIPPED_HAWTHORN_WOOD = blockItem(WitchesWayBlocks.STRIPPED_HAWTHORN_WOOD);
    public static final Item HAWTHORN_PLANKS = blockItem(WitchesWayBlocks.HAWTHORN_PLANKS);
    public static final Item HAWTHORN_STAIRS = blockItem(WitchesWayBlocks.HAWTHORN_STAIRS);
    public static final Item HAWTHORN_LEAVES = blockItem(WitchesWayBlocks.HAWTHORN_LEAVES);
    public static final Item HANGING_HAWTHORN_LEAVES = blockItem(WitchesWayBlocks.HANGING_HAWTHORN_LEAVES);
    public static final Item HAWTHORN_SAPLING = blockItem(WitchesWayBlocks.HAWTHORN_SAPLING);

    public static final Item ROWAN_LOG = blockItem(WitchesWayBlocks.ROWAN_LOG);
    public static final Item ROWAN_WOOD = blockItem(WitchesWayBlocks.ROWAN_WOOD);
    public static final Item STRIPPED_ROWAN_LOG = blockItem(WitchesWayBlocks.STRIPPED_ROWAN_LOG);
    public static final Item STRIPPED_ROWAN_WOOD = blockItem(WitchesWayBlocks.STRIPPED_ROWAN_WOOD);
    public static final Item ROWAN_PLANKS = blockItem(WitchesWayBlocks.ROWAN_PLANKS);
    public static final Item ROWAN_STAIRS = blockItem(WitchesWayBlocks.ROWAN_STAIRS);
    public static final Item ROWAN_LEAVES = blockItem(WitchesWayBlocks.ROWAN_LEAVES);
    public static final Item HANGING_ROWAN_LEAVES = blockItem(WitchesWayBlocks.HANGING_ROWAN_LEAVES);
    public static final Item ROWAN_SAPLING = blockItem(WitchesWayBlocks.ROWAN_SAPLING);

    public static final Item SPANISH_MOSS = blockItem(WitchesWayBlocks.SPANISH_MOSS);
    public static final Item GLISTENING_WEED = blockItem(WitchesWayBlocks.GLISTENING_WEED);

    public static final Item BELLADONNA_SEEDS = new ItemNameBlockItem(Blocks.WHEAT, new Item.Properties());
    public static final Item MANDRAKE_SEEDS = new ItemNameBlockItem(Blocks.WHEAT, new Item.Properties());

    public static final Item WHITE_STONE_ALTAR = blockItem(WitchesWayBlocks.WHITE_STONE_ALTAR);
    public static final Item LIGHT_GRAY_STONE_ALTAR = blockItem(WitchesWayBlocks.LIGHT_GRAY_STONE_ALTAR);
    public static final Item GRAY_STONE_ALTAR = blockItem(WitchesWayBlocks.GRAY_STONE_ALTAR);
    public static final Item BLACK_STONE_ALTAR = blockItem(WitchesWayBlocks.BLACK_STONE_ALTAR);
    public static final Item BROWN_STONE_ALTAR = blockItem(WitchesWayBlocks.BROWN_STONE_ALTAR);
    public static final Item RED_STONE_ALTAR = blockItem(WitchesWayBlocks.RED_STONE_ALTAR);
    public static final Item ORANGE_STONE_ALTAR = blockItem(WitchesWayBlocks.ORANGE_STONE_ALTAR);
    public static final Item YELLOW_STONE_ALTAR = blockItem(WitchesWayBlocks.YELLOW_STONE_ALTAR);
    public static final Item LIME_STONE_ALTAR = blockItem(WitchesWayBlocks.LIME_STONE_ALTAR);
    public static final Item GREEN_STONE_ALTAR = blockItem(WitchesWayBlocks.GREEN_STONE_ALTAR);
    public static final Item CYAN_STONE_ALTAR = blockItem(WitchesWayBlocks.CYAN_STONE_ALTAR);
    public static final Item LIGHT_BLUE_STONE_ALTAR = blockItem(WitchesWayBlocks.LIGHT_BLUE_STONE_ALTAR);
    public static final Item BLUE_STONE_ALTAR = blockItem(WitchesWayBlocks.BLUE_STONE_ALTAR);
    public static final Item PURPLE_STONE_ALTAR = blockItem(WitchesWayBlocks.PURPLE_STONE_ALTAR);
    public static final Item MAGENTA_STONE_ALTAR = blockItem(WitchesWayBlocks.MAGENTA_STONE_ALTAR);
    public static final Item PINK_STONE_ALTAR = blockItem(WitchesWayBlocks.PINK_STONE_ALTAR);

    public static final CreativeModeTab CREATIVE_TAB = FabricItemGroup.builder().icon(Items.STICK::getDefaultInstance).displayItems((parameters, output) -> {
        output.accept(ALDER_LOG);
        output.accept(ALDER_WOOD);
        output.accept(STRIPPED_ALDER_LOG);
        output.accept(STRIPPED_ALDER_WOOD);
        output.accept(ALDER_PLANKS);
        output.accept(ALDER_STAIRS);

        output.accept(HAWTHORN_LOG);
        output.accept(HAWTHORN_WOOD);
        output.accept(STRIPPED_HAWTHORN_LOG);
        output.accept(STRIPPED_HAWTHORN_WOOD);
        output.accept(HAWTHORN_PLANKS);
        output.accept(HAWTHORN_STAIRS);

        output.accept(ROWAN_LOG);
        output.accept(ROWAN_WOOD);
        output.accept(STRIPPED_ROWAN_LOG);
        output.accept(STRIPPED_ROWAN_WOOD);
        output.accept(ROWAN_PLANKS);
        output.accept(ROWAN_STAIRS);

        output.accept(ALDER_LEAVES);
        output.accept(HANGING_ALDER_LEAVES);
        output.accept(ALDER_SAPLING);

        output.accept(HAWTHORN_LEAVES);
        output.accept(HANGING_HAWTHORN_LEAVES);
        output.accept(HAWTHORN_SAPLING);

        output.accept(ROWAN_LEAVES);
        output.accept(HANGING_ROWAN_LEAVES);
        output.accept(ROWAN_SAPLING);

        output.accept(SPANISH_MOSS);
        output.accept(GLISTENING_WEED);

        output.accept(BELLADONNA_SEEDS);
        output.accept(MANDRAKE_SEEDS);

        output.accept(WHITE_STONE_ALTAR);
        output.accept(LIGHT_GRAY_STONE_ALTAR);
        output.accept(GRAY_STONE_ALTAR);
        output.accept(BLACK_STONE_ALTAR);
        output.accept(BROWN_STONE_ALTAR);
        output.accept(RED_STONE_ALTAR);
        output.accept(ORANGE_STONE_ALTAR);
        output.accept(YELLOW_STONE_ALTAR);
        output.accept(LIME_STONE_ALTAR);
        output.accept(GREEN_STONE_ALTAR);
        output.accept(CYAN_STONE_ALTAR);
        output.accept(LIGHT_BLUE_STONE_ALTAR);
        output.accept(BLUE_STONE_ALTAR);
        output.accept(PURPLE_STONE_ALTAR);
        output.accept(MAGENTA_STONE_ALTAR);
        output.accept(PINK_STONE_ALTAR);

    }).title(Component.translatable("itemGroup.witches-way")).build();

    public static void register() {
        register("alder_log", ALDER_LOG);
        register("alder_wood", ALDER_WOOD);
        register("stripped_alder_log", STRIPPED_ALDER_LOG);
        register("stripped_alder_wood", STRIPPED_ALDER_WOOD);
        register("alder_planks", ALDER_PLANKS);
        register("alder_stairs", ALDER_STAIRS);
        register("alder_leaves", ALDER_LEAVES);
        register("hanging_alder_leaves", HANGING_ALDER_LEAVES);
        register("alder_sapling", ALDER_SAPLING);

        register("hawthorn_log", HAWTHORN_LOG);
        register("hawthorn_wood", HAWTHORN_WOOD);
        register("stripped_hawthorn_log", STRIPPED_HAWTHORN_LOG);
        register("stripped_hawthorn_wood", STRIPPED_HAWTHORN_WOOD);
        register("hawthorn_planks", HAWTHORN_PLANKS);
        register("hawthorn_stairs", HAWTHORN_STAIRS);
        register("hawthorn_leaves", HAWTHORN_LEAVES);
        register("hanging_hawthorn_leaves", HANGING_HAWTHORN_LEAVES);
        register("hawthorn_sapling", HAWTHORN_SAPLING);

        register("rowan_log", ROWAN_LOG);
        register("rowan_wood", ROWAN_WOOD);
        register("stripped_rowan_log", STRIPPED_ROWAN_WOOD);
        register("stripped_rowan_wood", STRIPPED_ROWAN_LOG);
        register("rowan_planks", ROWAN_PLANKS);
        register("rowan_stairs", ROWAN_STAIRS);
        register("rowan_leaves", ROWAN_LEAVES);
        register("hanging_rowan_leaves", HANGING_ROWAN_LEAVES);
        register("rowan_sapling", ROWAN_SAPLING);

        register("spanish_moss", SPANISH_MOSS);
        register("glistening_weed", GLISTENING_WEED);

        register("belladonna_seeds", BELLADONNA_SEEDS);
        register("mandrake_seeds", MANDRAKE_SEEDS);

        register("white_stone_altar", WHITE_STONE_ALTAR);
        register("light_gray_stone_altar", LIGHT_GRAY_STONE_ALTAR);
        register("gray_stone_altar", GRAY_STONE_ALTAR);
        register("black_stone_altar", BLACK_STONE_ALTAR);
        register("brown_stone_altar", BROWN_STONE_ALTAR);
        register("red_stone_altar", RED_STONE_ALTAR);
        register("orange_stone_altar", ORANGE_STONE_ALTAR);
        register("yellow_stone_altar", YELLOW_STONE_ALTAR);
        register("lime_stone_altar", LIME_STONE_ALTAR);
        register("green_stone_altar", GREEN_STONE_ALTAR);
        register("cyan_stone_altar", CYAN_STONE_ALTAR);
        register("light_blue_stone_altar", LIGHT_BLUE_STONE_ALTAR);
        register("blue_stone_altar", BLUE_STONE_ALTAR);
        register("purple_stone_altar", PURPLE_STONE_ALTAR);
        register("magenta_stone_altar", MAGENTA_STONE_ALTAR);
        register("pink_stone_altar", PINK_STONE_ALTAR);

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, WitchesWay.resourceLocation("creative_tab"), CREATIVE_TAB);

    }

    public static void register(String name, Item item) {
        Registry.register(BuiltInRegistries.ITEM, WitchesWay.resourceLocation(name), item);
    }

    public static Item blockItem(Block block) {
        return new BlockItem(block, new Item.Properties());
    }

}
