package com.harleylizard.witches_way.common.blockentity;

import com.harleylizard.witches_way.common.WitchesWayBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public final class AltarBlockEntity extends SyncedBlockEntity implements Comparable<AltarBlockEntity> {
    private final Altar altar = new Altar();

    private int ticks;

    public AltarBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(WitchesWayBlockEntities.ALTAR, blockPos, blockState);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        altar.save(tag);
        tag.putInt("Ticks", ticks);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        altar.load(tag);
        ticks = tag.getInt("Ticks");
    }

    @Override
    public int compareTo(@NotNull AltarBlockEntity o) {
        return altar.compareTo(o.altar);
    }

    public Altar getAltar() {
        return altar;
    }

    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, AltarBlockEntity blockEntity) {
        var altar = blockEntity.getAltar().realAltar(blockEntity.level);
        if (altar != null && altar.is(blockEntity.getBlockPos())) {
            blockEntity.ticks++;
            if (blockEntity.ticks % 20 == 0) {
                altar.tick();
                blockEntity.sync();
            }
        }
    }
}
