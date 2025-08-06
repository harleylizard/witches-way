package com.harleylizard.witches_way.common;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record BlockStateTag(List<BlockStateProvider> blockStates) {
    public static final Codec<BlockStateTag> CODEC = RecordCodecBuilder.create(builder -> builder.group(BlockStateProvider.CODEC.listOf().fieldOf("values").forGetter(BlockStateTag::blockStates)).apply(builder, BlockStateTag::new));

    public boolean compare(BlockState blockState, RandomSource random, BlockPos blockPos) {
        return blockStates.stream().map(provider -> provider.getState(random, blockPos)).anyMatch(comparing -> compare(comparing, blockState));
    }

    public static boolean compare(BlockState comparing, BlockState blockState) {
        for (var entry : comparing.getValues().entrySet()) {
            var key = entry.getKey();

            var values = blockState.getValues();
            if (!values.containsKey(key) || !values.get(key).equals(entry.getValue())) {

                return false;
            }
        }

        return true;
    }

}
