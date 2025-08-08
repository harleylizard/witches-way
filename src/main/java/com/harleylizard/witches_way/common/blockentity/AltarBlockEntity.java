package com.harleylizard.witches_way.common.blockentity;

import com.harleylizard.witches_way.common.WitchesWayBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public final class AltarBlockEntity extends SyncedBlockEntity implements Comparable<AltarBlockEntity> {
    private final Altar altar = new Altar();

    public AltarBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(WitchesWayBlockEntities.ALTAR, blockPos, blockState);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        altar.save(tag);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        altar.load(tag);
    }

    @Override
    public int compareTo(@NotNull AltarBlockEntity o) {
        return altar.compareTo(o.altar);
    }

    public Altar getAltar() {
        return altar;
    }
}
