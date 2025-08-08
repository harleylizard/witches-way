package com.harleylizard.witches_way.common.blockentity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public final class Altar implements Comparable<Altar> {
    private long time;

    public void save(CompoundTag tag) {
        tag.putLong("Time", time);
    }

    public void load(CompoundTag tag) {
        time = tag.getLong("Time");
    }

    public void update(Level level) {
        time = level.dayTime();
    }

    @Override
    public int compareTo(@NotNull Altar o) {
        return Long.compare(time, o.time);
    }

    public static Altar oldest(List<Altar> list) {
        return list.stream().min(Comparator.naturalOrder()).orElse(list.getFirst());
    }

}
