package com.harleylizard.witches_way.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record Recipe(List<Ingredient> ingredients, Ingredient result) {
    public static final Codec<Recipe> CODEC = RecordCodecBuilder.create(builder -> builder.group(Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(Recipe::ingredients), Ingredient.CODEC.fieldOf("result").forGetter(Recipe::result)).apply(builder, Recipe::new));

    public void compare(List<ItemStack> items) {

    }

    public List<ItemStack> consume(List<ItemStack> items) {
        return List.of();
    }

}
