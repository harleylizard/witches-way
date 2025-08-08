package com.harleylizard.witches_way.common.block;

import com.harleylizard.witches_way.common.WitchesWayBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public final class AltarBlock extends Block implements EntityBlock {
    public static final BooleanProperty CLOTHED = BooleanProperty.create("clothed");

    public AltarBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(CLOTHED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CLOTHED);
    }

    @Override
    protected BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor level, BlockPos blockPos, BlockPos blockPos2) {
        return blockState.setValue(CLOTHED, countBlocks(level, blockPos));
    }

    @Override
    protected void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        level.getBlockEntity(blockPos, WitchesWayBlockEntities.ALTAR).orElseThrow().getAltar().update(level);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return WitchesWayBlockEntities.ALTAR.create(blockPos, blockState);
    }

    public boolean countBlocks(LevelAccessor level, BlockPos blockPos) {
        Set<BlockPos> set = new HashSet<>();

        Queue<BlockPos> queue = new ArrayDeque<>();

        set.add(blockPos);
        queue.add(blockPos);

        var sides = 0;

        for (var tries = 0; !queue.isEmpty() && tries <= 4 * 6; tries++) {
            var current = queue.poll();

            sides += countSides(level, current);

            tries++;

            for (var direction : Direction.Plane.HORIZONTAL) {
                var relative = current.relative(direction);

                if (!set.contains(relative) && isAltar(level, relative)) {
                    queue.add(relative);

                    set.add(relative);
                }
            }
        }

        return set.size() == 6 && sides == 10;
    }

    public int countSides(LevelAccessor level, BlockPos blockPos) {
        var i = 0;

        for (var direction : Direction.Plane.HORIZONTAL) {
            if (!isAltar(level, blockPos.relative(direction))) {
                i++;
            }

        }

        return i;
    }

    public boolean isAltar(LevelAccessor level, BlockPos blockPos) {
        return level.getBlockState(blockPos).is(this);
    }

}
