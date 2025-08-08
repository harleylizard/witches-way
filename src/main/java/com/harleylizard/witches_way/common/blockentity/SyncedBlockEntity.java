package com.harleylizard.witches_way.common.blockentity;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public sealed class SyncedBlockEntity extends BlockEntity permits AltarBlockEntity, BoilingCauldronBlockEntity {

    public SyncedBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, BlockEntity::getUpdateTag);
    }

    @Override
    public final CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        return saveCustomAndMetadata(provider);
    }

    public final void sync() {
        if (!level.isClientSide) {
            for (var player : PlayerLookup.tracking(this)) {
                ServerPlayNetworking.getSender(player).sendPacket(getUpdatePacket());

                setChanged();
            }
        }
    }

}
