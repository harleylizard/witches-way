package com.harleylizard.witches_way.common;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class WitchesWayBlockTags {
    public static final TagKey<Block> HANGING_LEAVES_CAN_REPLACE = tagOf("hanging_leaves_can_replace");
    public static final TagKey<Block> HANGING_LEAVES_CAN_STAY_ON = tagOf("hanging_leaves_can_stay_on");

    public static TagKey<Block> tagOf(String name) {
        return TagKey.create(Registries.BLOCK, WitchesWay.resourceLocation(name));
    }

}
