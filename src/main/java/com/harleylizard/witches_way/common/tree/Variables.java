package com.harleylizard.witches_way.common.tree;

import com.mojang.serialization.Codec;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;

import java.util.Map;

public record Variables(Map<String, IntProvider> variables) {
    public static final Codec<Variables> CODEC = Codec.unboundedMap(Codec.STRING, IntProvider.CODEC).xmap(Variables::new, Variables::variables);

    public IntProvider get(String name) {
        return variables.getOrDefault(name, ConstantInt.ZERO);
    }

}
