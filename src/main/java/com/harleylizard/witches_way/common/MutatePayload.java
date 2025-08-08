package com.harleylizard.witches_way.common;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record MutatePayload(BlockPos blockPos) implements CustomPacketPayload {
    public static final ResourceLocation NAME = WitchesWay.resourceLocation("mutate");

    public static final Type<MutatePayload> TYPE = new Type<>(NAME);

    public static final StreamCodec<FriendlyByteBuf, MutatePayload> CODEC = StreamCodec.composite(BlockPos.STREAM_CODEC, MutatePayload::blockPos, MutatePayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
