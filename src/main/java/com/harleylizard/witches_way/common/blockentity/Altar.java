package com.harleylizard.witches_way.common.blockentity;

import com.harleylizard.witches_way.common.WitchesWayBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.List;

import static java.util.Objects.requireNonNull;

public final class Altar implements Comparable<Altar> {
    private OptionalBlockPos optional = EmptyBlockPos.EMPTY;

    private long time;

    public void save(CompoundTag tag) {
        tag.putLong("Time", time);
        if (optional.isNotEmpty()) {
            tag.put("BlockPos", optional.save());
        }
    }

    public void load(CompoundTag tag) {
        time = tag.getLong("Time");
        if (tag.contains("BlockPos")) {
            optional = AltarBlockPos.of(tag);
        }
    }

    public void update(Level level) {
        time = level.dayTime();
    }

    @Nullable
    public Altar getAltar(Level level) {
        return optional.isEmpty(level) ? null : requireNonNull(((AltarBlockEntity) level.getBlockEntity(optional.asBlockPos()))).getAltar();
    }

    @Override
    public int compareTo(@NotNull Altar o) {
        return Long.compare(time, o.time);
    }

    public static Altar oldest(List<Altar> list) {
        return list.stream().min(Comparator.naturalOrder()).orElse(list.getFirst());
    }

    public interface OptionalBlockPos {

        boolean isEmpty(Level level);

        boolean isNotEmpty();

        Tag save();

        BlockPos asBlockPos();
    }

    private static final class EmptyBlockPos implements OptionalBlockPos {
        private static final OptionalBlockPos EMPTY = new EmptyBlockPos();

        private EmptyBlockPos() {}

        @Override
        public boolean isEmpty(Level level) {
            return true;
        }

        @Override
        public boolean isNotEmpty() {
            return false;
        }

        @Override
        public Tag save() {
            throw new UnsupportedOperationException();
        }

        @Override
        public BlockPos asBlockPos() {
            throw new UnsupportedOperationException();
        }
    }

    private record AltarBlockPos(BlockPos blockPos) implements OptionalBlockPos {

        @Override
        public boolean isEmpty(Level level) {
            return !level.isLoaded(blockPos) || level.getBlockEntity(blockPos, WitchesWayBlockEntities.ALTAR).isEmpty();
        }

        @Override
        public boolean isNotEmpty() {
            return true;
        }

        @Override
        public Tag save() {
            return NbtUtils.writeBlockPos(blockPos);
        }

        @Override
        public BlockPos asBlockPos() {
            return blockPos;
        }

        public static OptionalBlockPos of(CompoundTag tag) {
            return new AltarBlockPos(NbtUtils.readBlockPos(tag, "BlockPos").orElseThrow());
        }
    }

}
