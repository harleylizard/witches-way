package com.harleylizard.witches_way.common;

import com.harleylizard.witches_way.common.blockentity.AltarBlockEntity;
import com.harleylizard.witches_way.common.blockentity.BoilingCauldronBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class WitchesWayBlockEntities {
    public static final BlockEntityType<AltarBlockEntity> ALTAR = BlockEntityType.Builder.of(AltarBlockEntity::new,
            WitchesWayBlocks.WHITE_STONE_ALTAR,
            WitchesWayBlocks.LIGHT_GRAY_STONE_ALTAR,
            WitchesWayBlocks.GRAY_STONE_ALTAR,
            WitchesWayBlocks.BLACK_STONE_ALTAR,
            WitchesWayBlocks.BROWN_STONE_ALTAR,
            WitchesWayBlocks.RED_STONE_ALTAR,
            WitchesWayBlocks.ORANGE_STONE_ALTAR,
            WitchesWayBlocks.YELLOW_STONE_ALTAR,
            WitchesWayBlocks.LIME_STONE_ALTAR,
            WitchesWayBlocks.GREEN_STONE_ALTAR,
            WitchesWayBlocks.CYAN_STONE_ALTAR,
            WitchesWayBlocks.LIGHT_BLUE_STONE_ALTAR,
            WitchesWayBlocks.BLUE_STONE_ALTAR,
            WitchesWayBlocks.PURPLE_STONE_ALTAR,
            WitchesWayBlocks.MAGENTA_STONE_ALTAR,
            WitchesWayBlocks.PINK_STONE_ALTAR
            ).build();

    public static final BlockEntityType<BoilingCauldronBlockEntity> BOILING_CAULDRON = BlockEntityType.Builder.of(BoilingCauldronBlockEntity::new, WitchesWayBlocks.BOILING_CAULDRON).build();

    public static void register() {
        register("altar", ALTAR);
        register("boiling_cauldron", BOILING_CAULDRON);

    }

    public static void register(String name, BlockEntityType<?> type) {
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, WitchesWay.resourceLocation(name), type);
    }

}
