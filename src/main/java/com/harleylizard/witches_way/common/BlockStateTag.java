package com.harleylizard.witches_way.common;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public record BlockStateTag(List<BlockStateRule> values) {
    public static final BlockStateTag EMPTY = new BlockStateTag(List.of());

    public static final Codec<BlockStateTag> CODEC = RecordCodecBuilder.create(builder -> builder.group(BlockStateRule.CODEC.listOf().fieldOf("values").forGetter(BlockStateTag::values)).apply(builder, list -> list.isEmpty() ? EMPTY : new BlockStateTag(Collections.unmodifiableList(list))));

    private static final Map<String, BlockStateTag> TAGS = new HashMap<>();

    public boolean compare(BlockState blockState) {
        return values.stream().anyMatch(rule -> rule.compare(blockState));
    }

    public BlockStateTag join(BlockStateTag other) {
        return new BlockStateTag(join(values, other.values));
    }

    public static <T> List<T> join(List<T> first, List<T> second) {
        List<T> list = new ArrayList<>(first);
        list.addAll(second);
        return Collections.unmodifiableList(list);
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

    public record BlockStateRule(Block block, Map<Property<?>, Comparable<?>> properties) {
        public static final Codec<BlockStateRule> CODEC = BuiltInRegistries.BLOCK.byNameCodec().dispatch("block", BlockStateRule::block, block -> {
            BiMap<String, Map.Entry<Property<?>, Comparable<?>>> values = HashBiMap.create(Collections.unmodifiableMap(block.getStateDefinition().getPossibleStates().stream().flatMap(blockState -> blockState.getValues().entrySet().stream()).collect(Collectors.toMap(entry -> entry.getKey().getName(), entry -> entry))));

            MapCodec<Map<Property<?>, Comparable<?>>> codec = Codec.unboundedMap(Codec.STRING, Codec.STRING).fieldOf("properties").orElse(Map.of()).xmap(properties -> {
                return properties.entrySet().stream().filter(entry -> {
                    var name = entry.getKey();

                    return values.containsKey(name) && values.get(name).getValue().toString().equals(entry.getValue());
                }).map(entry -> {
                    var property = values.get(entry.getKey());

                    return Map.entry(property.getKey(), property.getValue());
                }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            }, map -> {

                return map.entrySet().stream().map(entry -> Map.entry(values.inverse().get(entry), entry.getValue().toString())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            });

            return codec.xmap(properties -> new BlockStateRule(block, properties), BlockStateRule::properties);
        });

        public boolean compare(BlockState blockState) {
            if (!blockState.is(block)) {
                return false;
            }

            for (var entry : properties.entrySet()) {
                var key = entry.getKey();

                var values = blockState.getValues();

                if (!values.containsKey(key) || !values.get(key).equals(entry.getValue())) {
                    return false;
                }

            }

            return true;
        }

    }

}
