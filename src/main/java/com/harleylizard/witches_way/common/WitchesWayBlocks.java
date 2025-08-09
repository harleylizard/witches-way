package com.harleylizard.witches_way.common;

import com.harleylizard.witches_way.common.block.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.Optional;

public final class WitchesWayBlocks {
    public static final Block ALDER_LOG = log();
    public static final Block ALDER_WOOD = log();
    public static final Block STRIPPED_ALDER_LOG = log();
    public static final Block STRIPPED_ALDER_WOOD = log();
    public static final Block ALDER_PLANKS = planks();
    public static final Block ALDER_STAIRS = stairs(ALDER_PLANKS);
    public static final Block ALDER_SLAB = slab();
    public static final Block ALDER_LEAVES = leaves();
    public static final Block HANGING_ALDER_LEAVES = hangingLeaves();
    public static final Block ALDER_SAPLING = sapling("alder", WitchesWayConfiguredFeatures.ALDER_TREE);

    public static final Block HAWTHORN_LOG = log();
    public static final Block HAWTHORN_WOOD = log();
    public static final Block STRIPPED_HAWTHORN_LOG = log();
    public static final Block STRIPPED_HAWTHORN_WOOD = log();
    public static final Block HAWTHORN_PLANKS = planks();
    public static final Block HAWTHORN_STAIRS = stairs(HAWTHORN_PLANKS);
    public static final Block HAWTHORN_SLAB = slab();
    public static final Block HAWTHORN_LEAVES = leaves();
    public static final Block HANGING_HAWTHORN_LEAVES = hangingLeaves();
    public static final Block HAWTHORN_SAPLING = sapling("hawthorn", WitchesWayConfiguredFeatures.HAWTHORN_TREE);

    public static final Block ROWAN_LOG = log();
    public static final Block ROWAN_WOOD = log();
    public static final Block STRIPPED_ROWAN_LOG = log();
    public static final Block STRIPPED_ROWAN_WOOD = log();
    public static final Block ROWAN_PLANKS = planks();
    public static final Block ROWAN_STAIRS = stairs(ROWAN_PLANKS);
    public static final Block ROWAN_SLAB = slab();
    public static final Block ROWAN_LEAVES = leaves();
    public static final Block HANGING_ROWAN_LEAVES = hangingLeaves();
    public static final Block ROWAN_SAPLING = sapling("rowan", WitchesWayConfiguredFeatures.ROWAN_TREE);

    public static final Block SPANISH_MOSS = new SpanishMossBlock(Properties.of().sound(SoundType.GRASS).noOcclusion().noCollission().randomTicks());
    public static final Block GLISTENING_WEED = new GlisteningWeedBlock(Properties.of().sound(SoundType.GRASS).noOcclusion().noCollission().randomTicks().lightLevel(blockState -> 6).emissiveRendering(Blocks::always).hasPostProcess(Blocks::always).offsetType(BlockBehaviour.OffsetType.XZ));

    public static final Block BOILING_CAULDRON = new BoilingCauldronBlock(Properties.of().requiresCorrectToolForDrops().strength(1.75f).sound(SoundType.METAL).noOcclusion().randomTicks());

    public static final Block WHITE_STONE_ALTAR = stoneAltar();
    public static final Block LIGHT_GRAY_STONE_ALTAR = stoneAltar();
    public static final Block GRAY_STONE_ALTAR = stoneAltar();
    public static final Block BLACK_STONE_ALTAR = stoneAltar();
    public static final Block BROWN_STONE_ALTAR = stoneAltar();
    public static final Block RED_STONE_ALTAR = stoneAltar();
    public static final Block ORANGE_STONE_ALTAR = stoneAltar();
    public static final Block YELLOW_STONE_ALTAR = stoneAltar();
    public static final Block LIME_STONE_ALTAR = stoneAltar();
    public static final Block GREEN_STONE_ALTAR = stoneAltar();
    public static final Block CYAN_STONE_ALTAR = stoneAltar();
    public static final Block LIGHT_BLUE_STONE_ALTAR = stoneAltar();
    public static final Block BLUE_STONE_ALTAR = stoneAltar();
    public static final Block PURPLE_STONE_ALTAR = stoneAltar();
    public static final Block MAGENTA_STONE_ALTAR = stoneAltar();
    public static final Block PINK_STONE_ALTAR = stoneAltar();

    public static final Block WHITE_MOHAIR = mohair(DyeColor.WHITE);
    public static final Block WHITE_BLOODY_MOHAIR = mohair(DyeColor.WHITE);
    public static final Block LIGHT_GRAY_MOHAIR = mohair(DyeColor.LIGHT_GRAY);
    public static final Block LIGHT_GRAY_BLOODY_MOHAIR = mohair(DyeColor.LIGHT_GRAY);
    public static final Block GRAY_MOHAIR = mohair(DyeColor.GRAY);
    public static final Block GRAY_BLOODY_MOHAIR = mohair(DyeColor.GRAY);
    public static final Block BLACK_MOHAIR = mohair(DyeColor.BLACK);
    public static final Block BLACK_BLOODY_MOHAIR = mohair(DyeColor.BLACK);
    public static final Block BROWN_MOHAIR = mohair(DyeColor.BROWN);
    public static final Block BROWN_BLOODY_MOHAIR = mohair(DyeColor.BROWN);
    public static final Block RED_MOHAIR = mohair(DyeColor.RED);
    public static final Block RED_BLOODY_MOHAIR = mohair(DyeColor.RED);
    public static final Block ORANGE_MOHAIR = mohair(DyeColor.ORANGE);
    public static final Block ORANGE_BLOODY_MOHAIR = mohair(DyeColor.ORANGE);
    public static final Block YELLOW_MOHAIR = mohair(DyeColor.YELLOW);
    public static final Block YELLOW_BLOODY_MOHAIR = mohair(DyeColor.YELLOW);
    public static final Block LIME_MOHAIR = mohair(DyeColor.LIME);
    public static final Block LIME_BLOODY_MOHAIR = mohair(DyeColor.LIME);
    public static final Block GREEN_MOHAIR = mohair(DyeColor.GREEN);
    public static final Block GREEN_BLOODY_MOHAIR = mohair(DyeColor.GREEN);
    public static final Block CYAN_MOHAIR = mohair(DyeColor.CYAN);
    public static final Block CYAN_BLOODY_MOHAIR = mohair(DyeColor.CYAN);
    public static final Block LIGHT_BLUE_MOHAIR = mohair(DyeColor.LIGHT_BLUE);
    public static final Block LIGHT_BLUE_BLOODY_MOHAIR = mohair(DyeColor.LIGHT_BLUE);
    public static final Block BLUE_MOHAIR = mohair(DyeColor.BLUE);
    public static final Block BLUE_BLOODY_MOHAIR = mohair(DyeColor.BLUE);
    public static final Block PURPLE_MOHAIR = mohair(DyeColor.PURPLE);
    public static final Block PURPLE_BLOODY_MOHAIR = mohair(DyeColor.PURPLE);
    public static final Block MAGENTA_MOHAIR = mohair(DyeColor.MAGENTA);
    public static final Block MAGENTA_BLOODY_MOHAIR = mohair(DyeColor.MAGENTA);
    public static final Block PINK_MOHAIR = mohair(DyeColor.PINK);
    public static final Block PINK_BLOODY_MOHAIR = mohair(DyeColor.PINK);

    public static void register() {
        register("alder_log", ALDER_LOG);
        register("alder_wood", ALDER_WOOD);
        register("stripped_alder_log", STRIPPED_ALDER_LOG);
        register("stripped_alder_wood", STRIPPED_ALDER_WOOD);
        register("alder_planks", ALDER_PLANKS);
        register("alder_stairs", ALDER_STAIRS);
        register("alder_slab", ALDER_SLAB);
        register("alder_leaves", ALDER_LEAVES);
        register("hanging_alder_leaves", HANGING_ALDER_LEAVES);
        register("alder_sapling", ALDER_SAPLING);

        register("hawthorn_log", HAWTHORN_LOG);
        register("hawthorn_wood", HAWTHORN_WOOD);
        register("stripped_hawthorn_log", STRIPPED_HAWTHORN_LOG);
        register("stripped_hawthorn_wood", STRIPPED_HAWTHORN_WOOD);
        register("hawthorn_planks", HAWTHORN_PLANKS);
        register("hawthorn_stairs", HAWTHORN_STAIRS);
        register("hawthorn_slab", HAWTHORN_SLAB);
        register("hawthorn_leaves", HAWTHORN_LEAVES);
        register("hanging_hawthorn_leaves", HANGING_HAWTHORN_LEAVES);
        register("hawthorn_sapling", HAWTHORN_SAPLING);

        register("rowan_log", ROWAN_LOG);
        register("rowan_wood", ROWAN_WOOD);
        register("stripped_rowan_log", STRIPPED_ROWAN_LOG);
        register("stripped_rowan_wood", STRIPPED_ROWAN_WOOD);
        register("rowan_planks", ROWAN_PLANKS);
        register("rowan_stairs", ROWAN_STAIRS);
        register("rowan_slab", ROWAN_SLAB);
        register("rowan_leaves", ROWAN_LEAVES);
        register("hanging_rowan_leaves", HANGING_ROWAN_LEAVES);
        register("rowan_sapling", ROWAN_SAPLING);

        register("spanish_moss", SPANISH_MOSS);
        register("glistening_weed", GLISTENING_WEED);

        register("boiling_cauldron", BOILING_CAULDRON);

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

        register("white_mohair", WHITE_MOHAIR);
        register("white_bloody_mohair", WHITE_BLOODY_MOHAIR);
        register("light_gray_mohair", LIGHT_GRAY_MOHAIR);
        register("light_gray_bloody_mohair", LIGHT_GRAY_BLOODY_MOHAIR);
        register("gray_mohair", GRAY_MOHAIR);
        register("gray_bloody_mohair", GRAY_BLOODY_MOHAIR);
        register("black_mohair", BLACK_MOHAIR);
        register("black_bloody_mohair", BLACK_BLOODY_MOHAIR);
        register("brown_mohair", BROWN_MOHAIR);
        register("brown_bloody_mohair", BROWN_BLOODY_MOHAIR);
        register("red_mohair", RED_MOHAIR);
        register("red_bloody_mohair", RED_BLOODY_MOHAIR);
        register("orange_mohair", ORANGE_MOHAIR);
        register("orange_bloody_mohair", ORANGE_BLOODY_MOHAIR);
        register("yellow_mohair", YELLOW_MOHAIR);
        register("yellow_bloody_mohair", YELLOW_BLOODY_MOHAIR);
        register("lime_mohair", LIME_MOHAIR);
        register("lime_bloody_mohair", LIME_BLOODY_MOHAIR);
        register("green_mohair", GREEN_MOHAIR);
        register("green_bloody_mohair", GREEN_BLOODY_MOHAIR);
        register("cyan_mohair", CYAN_MOHAIR);
        register("cyan_bloody_mohair", CYAN_BLOODY_MOHAIR);
        register("light_blue_mohair", LIGHT_BLUE_MOHAIR);
        register("light_blue_bloody_mohair", LIGHT_BLUE_BLOODY_MOHAIR);
        register("blue_mohair", BLUE_MOHAIR);
        register("blue_bloody_mohair", BLUE_BLOODY_MOHAIR);
        register("purple_mohair", PURPLE_MOHAIR);
        register("purple_bloody_mohair", PURPLE_BLOODY_MOHAIR);
        register("magenta_mohair", MAGENTA_MOHAIR);
        register("magenta_bloody_mohair", MAGENTA_BLOODY_MOHAIR);
        register("pink_mohair", PINK_MOHAIR);
        register("pink_bloody_mohair", PINK_BLOODY_MOHAIR);
    }

    public static void register(String name, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, WitchesWay.resourceLocation(name), block);
    }

    private static Block stoneAltar() {
        return new AltarBlock(Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops());
    }

    private static Block mohair(DyeColor color) {
        return new Block(Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(color));
    }

    private static Block log() {
        return new RotatedPillarBlock(Properties.ofFullCopy(Blocks.OAK_LOG));
    }

    private static Block planks() {
        return new Block(Properties.ofFullCopy(Blocks.OAK_PLANKS));
    }

    private static Block stairs(Block block) {
        return new StairBlock(block.defaultBlockState(), Properties.ofFullCopy(block));
    }

    private static Block slab() {
        return new SlabBlock(Properties.ofFullCopy(Blocks.OAK_PLANKS));
    }

    private static Block leaves() {
        return Blocks.leaves(SoundType.GRASS);
    }

    private static Block hangingLeaves() {
        return new HangingLeavesBlock(Properties.of().isValidSpawn(Blocks::never).sound(SoundType.GRASS).noCollission().noOcclusion().randomTicks().mapColor(MapColor.GRASS).pushReaction(PushReaction.DESTROY));
    }

    private static Block sapling(String name, ResourceKey<ConfiguredFeature<?, ?>> feature) {
        return new SaplingBlock(new TreeGrower(name, Optional.empty(), Optional.of(feature), Optional.empty()), Properties.ofFullCopy(Blocks.OAK_SAPLING));
    }

}
