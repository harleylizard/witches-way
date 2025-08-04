package com.harleylizard.witches_way.common;

import com.harleylizard.witches_way.common.block.AltarBlock;
import com.harleylizard.witches_way.common.block.HangingLeavesBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public final class WitchesWayBlocks {
    public static final Block ALDER_LOG = log();
    public static final Block ALDER_WOOD = log();
    public static final Block STRIPPED_ALDER_LOG = log();
    public static final Block STRIPPED_ALDER_WOOD = log();
    public static final Block ALDER_PLANKS = planks();
    public static final Block HANGING_ALDER_LEAVES = hangingLeaves();
    public static final Block HAWTHORN_LOG = log();
    public static final Block HAWTHORN_WOOD = log();
    public static final Block STRIPPED_HAWTHORN_LOG = log();
    public static final Block STRIPPED_HAWTHORN_WOOD = log();
    public static final Block HAWTHORN_PLANKS = planks();
    public static final Block HANGING_HAWTHORN_LEAVES = hangingLeaves();
    public static final Block ROWAN_LOG = log();
    public static final Block ROWAN_WOOD = log();
    public static final Block STRIPPED_ROWAN_LOG = log();
    public static final Block STRIPPED_ROWAN_WOOD = log();
    public static final Block ROWAN_PLANKS = planks();
    public static final Block HANGING_ROWAN_LEAVES = hangingLeaves();

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

    public static void register() {
        register("alder_log", ALDER_LOG);
        register("alder_wood", ALDER_WOOD);
        register("stripped_alder_log", STRIPPED_ALDER_LOG);
        register("stripped_alder_wood", STRIPPED_ALDER_WOOD);
        register("alder_planks", ALDER_PLANKS);
        register("hanging_alder_leaves", HANGING_ALDER_LEAVES);
        register("hawthorn_log", HAWTHORN_LOG);
        register("hawthorn_wood", HAWTHORN_WOOD);
        register("stripped_hawthorn_log", STRIPPED_HAWTHORN_LOG);
        register("stripped_hawthorn_wood", STRIPPED_HAWTHORN_WOOD);
        register("hawthorn_planks", HAWTHORN_PLANKS);
        register("hanging_hawthorn_leaves", HANGING_HAWTHORN_LEAVES);
        register("rowan_log", ROWAN_LOG);
        register("rowan_wood", ROWAN_WOOD);
        register("stripped_rowan_log", STRIPPED_ROWAN_LOG);
        register("stripped_rowan_wood", STRIPPED_ROWAN_WOOD);
        register("rowan_planks", ROWAN_PLANKS);
        register("hanging_rowan_leaves", HANGING_ROWAN_LEAVES);

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
    }

    public static void register(String name, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, WitchesWay.resourceLocation(name), block);
    }

    public static Block stoneAltar() {
        return new AltarBlock(Properties.ofFullCopy(Blocks.STONE));
    }

    public static Block log() {
        return new RotatedPillarBlock(Properties.ofFullCopy(Blocks.OAK_LOG));
    }

    public static Block planks() {
        return new Block(Properties.ofFullCopy(Blocks.OAK_PLANKS));
    }

    public static Block hangingLeaves() {
        return new HangingLeavesBlock(Properties.of().sound(SoundType.GRASS).noCollission().noOcclusion().randomTicks().mapColor(MapColor.GRASS).pushReaction(PushReaction.DESTROY));
    }

}
