package com.harleylizard.witches_way.common.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Recipes {
    public static final Map<Item, Set<Recipe>> SEARCH = new HashMap<>();

    public static void parse() {

    }

    public static Recipe get(List<ItemStack> list) {
        for (var itemStack : list) {
            var item = itemStack.getItem();

            if (!SEARCH.containsKey(item)) {
                return Recipe.EMPTY;
            }

            for (var recipe : SEARCH.get(item)) {
                if (recipe.compare(list)) {
                    return recipe;
                }

            }
        }

        return Recipe.EMPTY;
    }

}
