package com.harleylizard.witches_way.common.blockentity;

import com.harleylizard.witches_way.common.WitchesWayBlockEntities;
import com.harleylizard.witches_way.common.block.AltarBlock;
import com.harleylizard.witches_way.common.power.PowerSource;
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
    private final PowerSource source = new PowerSource();

    private OptionalBlockPos optional = EmptyBlockPos.EMPTY;

    private long time;

    public void save(CompoundTag tag) {
        source.save(tag);

        tag.putLong("Time", time);
        if (optional.isNotEmpty()) {
            tag.put("BlockPos", optional.save());
        }
    }

    public void load(CompoundTag tag) {
        source.load(tag);

        time = tag.getLong("Time");
        if (tag.contains("BlockPos")) {
            optional = AltarBlockPos.of(tag);
        }
    }

    public void update(Level level, AltarBlock block, BlockPos blockPos) {
        time = level.dayTime();

        var counted = block.countBlocks(level, blockPos);
        if (counted.isAltar()) {
            var blockEntities = counted.blocks().stream().map(altar -> level.getBlockEntity(altar, WitchesWayBlockEntities.ALTAR).orElseThrow()).toList();

            var oldest = oldest(blockEntities);
            for (var blockEntity : blockEntities) {
                blockEntity.getAltar().optional = new AltarBlockPos(oldest);
            }
        }
    }

    public void makeEmpty() {
        optional = EmptyBlockPos.EMPTY;
        source.set(0);
    }

    public void tick() {

    }

    @Nullable
    public Altar realAltar(Level level) {
        return optional.isEmpty(level) ? null : requireNonNull(((AltarBlockEntity) level.getBlockEntity(optional.asBlockPos()))).getAltar();
    }

    public boolean is(BlockPos blockPos) {
        return optional.asBlockPos().equals(blockPos);
    }

    @Override
    public int compareTo(@NotNull Altar o) {
        return Long.compare(time, o.time);
    }

    public static BlockPos oldest(List<AltarBlockEntity> list) {
        return list.stream().min(Comparator.naturalOrder()).orElse(list.getFirst()).getBlockPos();
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
