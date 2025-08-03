package com.harleylizard.witches_way.common;

import com.harleylizard.witches_way.common.block.AltarBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public final class WitchesWayBlocks {
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

}
