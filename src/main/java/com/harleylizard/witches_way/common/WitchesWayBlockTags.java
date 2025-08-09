package com.harleylizard.witches_way.common;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class WitchesWayBlockTags {
    public static final TagKey<Block> CAN_MUTATE = tagOf("can_mutate");
    public static final TagKey<Block> CAN_NOT_MUTATE_INTO = tagOf("can_not_mutate_into");
    public static final TagKey<Block> GLISTENING_WEED_GROWS_ON = tagOf("glistening_weed_grows_on");
    public static final TagKey<Block> HANGING_LEAVES_CAN_HANG_ON = tagOf("hanging_leaves_can_hang_on");
    public static final TagKey<Block> HANGING_LEAVES_CAN_REPLACE = tagOf("hanging_leaves_can_replace");

    public static TagKey<Block> tagOf(String name) {
        return TagKey.create(Registries.BLOCK, WitchesWay.resourceLocation(name));
    }

}
