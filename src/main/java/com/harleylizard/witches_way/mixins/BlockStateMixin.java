package com.harleylizard.witches_way.mixins;

import com.harleylizard.witches_way.common.BlockStateTag;
import com.harleylizard.witches_way.common.IsBlockStateTag;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockState.class)
public final class BlockStateMixin implements IsBlockStateTag {

    @Override
    public boolean witchesWay$is(BlockStateTag tag, RandomSource random, BlockPos blockPos) {
        return tag.compare((BlockState) (Object) this, random, blockPos);
    }

}
