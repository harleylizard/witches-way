package com.harleylizard.witches_way.common.block;

import com.harleylizard.witches_way.common.WitchesWayBlockEntities;
import it.unimi.dsi.fastutil.objects.AbstractObject2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.*;

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
        var altar = countBlocks(level, blockPos).isAltar();
        if (!altar) {
            level.getBlockEntity(blockPos, WitchesWayBlockEntities.ALTAR).orElseThrow().getAltar().makeEmpty();
        }
        return blockState.setValue(CLOTHED, altar);
    }

    @Override
    protected void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        level.getBlockEntity(blockPos, WitchesWayBlockEntities.ALTAR).orElseThrow().getAltar().update(level, this, blockPos);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return WitchesWayBlockEntities.ALTAR.create(blockPos, blockState);
    }

    public Counted countBlocks(LevelAccessor level, BlockPos blockPos) {
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
        return new Counted(Collections.unmodifiableSet(set), sides);
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

    public record Counted(Set<BlockPos> blocks, int sides) {

        public boolean isAltar() {
            return blocks.size() == 6 && sides == 10;
        }
    }
}
