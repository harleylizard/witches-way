package com.harleylizard.witches_way.common;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.io.IOException;
import java.util.*;

public record BlockStateTag(List<BlockStateProvider> blockStates) {
    public static final BlockStateTag EMPTY = new BlockStateTag(List.of());

    public static final Codec<BlockStateTag> CODEC = RecordCodecBuilder.create(builder -> builder.group(BlockStateProvider.CODEC.listOf().fieldOf("values").forGetter(BlockStateTag::blockStates)).apply(builder, list -> list.isEmpty() ? EMPTY : new BlockStateTag(Collections.unmodifiableList(list))));

    private static final Map<String, BlockStateTag> TAGS = new HashMap<>();

    public boolean compare(BlockState blockState, RandomSource random, BlockPos blockPos) {
        return blockStates.stream().map(provider -> provider.getState(random, blockPos)).anyMatch(comparing -> compare(comparing, blockState));
    }

    public BlockStateTag join(BlockStateTag other) {
        return new BlockStateTag(join(blockStates, other.blockStates));
    }

    public static <T> List<T> join(List<T> first, List<T> second) {
        List<T> list = new ArrayList<>(first);
        list.addAll(second);
        return Collections.unmodifiableList(list);
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

    public static void parse(ResourceManager manager) throws IOException {
        TAGS.clear();

        var gson = new GsonBuilder().create();

        for (var entry : manager.listResources("blockstates", path -> path.toString().endsWith(".json")).entrySet()) {
            var name = entry.getKey().withPath(path -> path.substring(0, path.indexOf(".json")).substring(path.lastIndexOf("/") + 1)).toString();

            try (var reader = entry.getValue().openAsReader()) {
                var result = CODEC.parse(JsonOps.INSTANCE, gson.fromJson(reader, JsonElement.class));

                if (result.isError()) {
                    continue;
                }

                var tag = result.getOrThrow();

                if (TAGS.containsKey(name)) {
                    TAGS.put(name, TAGS.get(name).join(tag));

                    continue;
                }

                TAGS.put(name, result.getOrThrow());
            }
        }
    }

    public static BlockStateTag get(String name) {
        return TAGS.getOrDefault(name, EMPTY);
    }

}
