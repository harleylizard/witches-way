package com.harleylizard.witches_way.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public record Ingredient(Item item, int count) {
    public static final Ingredient EMPTY = new Ingredient(null, 0);

    public static final Codec<Ingredient> CODEC = RecordCodecBuilder.create(builder -> builder.group(BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(Ingredient::item), Codec.INT.fieldOf("count").orElse(1).forGetter(Ingredient::count)).apply(builder, Ingredient::new));

    public ItemStack asItemStack() {
        return new ItemStack(item, count);
    }

}
