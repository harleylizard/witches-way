package com.harleylizard.witches_way.mixins;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(HolderSet.Named.class)
public interface HolderSet$NamedAccessor<T> {

    @Accessor("contents")
    List<Holder<T>> witchesWay$contents();
}
