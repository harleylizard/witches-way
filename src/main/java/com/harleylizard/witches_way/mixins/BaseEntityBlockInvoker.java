package com.harleylizard.witches_way.mixins;

import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BaseEntityBlock.class)
public interface BaseEntityBlockInvoker {

    @Invoker("createTickerHelper")
    static <T extends BlockEntity, R extends BlockEntity> BlockEntityTicker<R> witchesWay$createTickerHelper(BlockEntityType<R> left, BlockEntityType<T> right, BlockEntityTicker<? super T> ticker) {
        throw new AssertionError();
    }
}
