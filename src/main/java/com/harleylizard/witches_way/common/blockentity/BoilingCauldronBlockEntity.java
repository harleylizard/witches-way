package com.harleylizard.witches_way.common.blockentity;

import com.harleylizard.witches_way.common.WitchesWayBlockEntities;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public final class BoilingCauldronBlockEntity extends BlockEntity {
    private final SingleFluidStorage fluid = new SingleFluidStorage() {

        @Override
        protected long getCapacity(FluidVariant variant) {
            return FluidConstants.BUCKET * 3;
        }

        @Override
        protected void onFinalCommit() {
            setChanged();
        }

    };

    public BoilingCauldronBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(WitchesWayBlockEntities.BOILING_CAULDRON, blockPos, blockState);
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        var fluidTag = new CompoundTag();
        fluid.writeNbt(fluidTag, provider);

        compoundTag.put("Fluid", fluidTag);
    }

    @Override
    protected void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        fluid.readNbt(compoundTag.getCompound("Fluid"), provider);
    }

    public SingleFluidStorage getFluid() {
        return fluid;
    }

}
