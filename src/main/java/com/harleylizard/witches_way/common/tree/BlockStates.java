package com.harleylizard.witches_way.common.tree;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.Map;

public record BlockStates(Map<String, BlockStateProvider> variables) {
    public static final BlockStateProvider EMPTY = BlockStateProvider.simple(Blocks.AIR);

    public static final Codec<BlockStates> CODEC = Codec.unboundedMap(Codec.STRING, BlockStateProvider.CODEC).xmap(BlockStates::new, BlockStates::variables);

    public BlockStateProvider get(String name) {
        return variables.getOrDefault(name, EMPTY);
    }

}
