package com.harleylizard.witches_way.common.tree;

import com.harleylizard.witches_way.common.block.HangingLeavesBlock;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.ticks.TickPriority;

import java.util.HashSet;
import java.util.Set;

public record Leaves(String leaves, String hangingLeaves, int wideness, int tallness) implements Shape {
    public static final MapCodec<Leaves> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
            Codec.STRING.fieldOf("leaves").forGetter(Leaves::leaves),
            Codec.STRING.fieldOf("hanging_leaves").forGetter(Leaves::hangingLeaves), Codec.INT.fieldOf("wideness").forGetter(Leaves::wideness), Codec.INT.fieldOf("tallness").forGetter(Leaves::tallness)).apply(builder, Leaves::new));

    @Override
    public BlockPos place(WorldGenLevel level, BlockPos blockPos, RandomSource random, BlockStates blocks) {
        Set<BlockPos> set = new HashSet<>();

        var k = wideness + 1;
        for (var i = -k; i <= k; i++) {
            for (var j = -k; j <= k; j++) {
                var relative = blockPos.offset(i, 0, j);

                var x = relative.getX() - blockPos.getX();
                var z = relative.getZ() - blockPos.getZ();

                var distance = Math.sqrt((x * x + z * z)) * 2.25;
                var radius = wideness * wideness;

                if (distance < radius - 1 ) {
                    Shapes.set(level, relative, blocks.get(leaves), random);

                    level.scheduleTick(blockPos, level.getBlockState(relative).getBlock(), 1, TickPriority.LOW);
                    set.add(relative);
                }

            }
        }

        for (var relative : set) {
            for (var direction : Direction.Plane.HORIZONTAL) {
                leaf(level, relative.relative(direction), random, direction.getOpposite().ordinal(), blocks);
            }
        }

        return blockPos;
    }

    @Override
    public MapCodec<? extends Shape> getCodec() {
        return CODEC;
    }

    public void leaf(WorldGenLevel level, BlockPos blockPos, RandomSource random, int i, BlockStates blocks) {
        var blockState = level.getBlockState(blockPos);

        if (blockState.is(BlockTags.REPLACEABLE) && random.nextBoolean()) {
            blockState = blocks.get(hangingLeaves).getState(random, blockPos);

            if (blockState.getBlock() instanceof HangingLeavesBlock) {
                var j = HangingLeavesBlock.toHorizontal(i);
                level.setBlock(blockPos, blockState.setValue(HangingLeavesBlock.FACES, 1 << j), Block.UPDATE_ALL);

                var below = blockPos.below();
                if (level.getBlockState(below).is(BlockTags.REPLACEABLE)) {
                    level.setBlock(below, blockState.setValue(HangingLeavesBlock.FACES, 2 << j), Block.UPDATE_ALL);

                }
            }

        }
    }

}
