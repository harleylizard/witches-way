package com.harleylizard.witches_way.common.block;

import com.harleylizard.witches_way.common.Util;
import com.harleylizard.witches_way.common.WitchesWayBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public sealed class HangingLeavesBlock extends ShapeBlock permits SpanishMossBlock {
    public static final IntegerProperty FACES = IntegerProperty.create("faces", 0, 256);

    public HangingLeavesBlock(Properties properties) {
        super(properties);
        registerDefaultState(setFaces(getStateDefinition().any(), 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACES);
    }

    @Override
    protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return subtracted(levelReader, blockPos, blockState) > 0;
    }

    @Override
    protected BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        var faces = subtracted(levelAccessor, blockPos, blockState);

        return faces == 0 ? Blocks.AIR.defaultBlockState() : blockState.setValue(FACES, faces);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return setFaces(defaultBlockState(), 1 << toHorizontal(blockPlaceContext.getHorizontalDirection().ordinal()));
    }

    @Override
    protected boolean isRandomlyTicking(BlockState blockState) {
        return super.isRandomlyTicking(blockState) && getFaces(blockState) > 0;
    }

    @Override
    protected void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        var faces = getFaces(blockState);

        for (var direction : Direction.Plane.HORIZONTAL) {
            var i = direction.ordinal();

            if (stage(faces, i) == 1 && randomSource.nextInt(15) == 0) {

                growTo(serverLevel, blockPos.below(), i);
            }
        }
    }

    @Override
    public VoxelShape shapeFrom(BlockState blockState) {
        var shape = Shapes.empty();

        var faces = getFaces(blockState);

        for (var direction : Direction.Plane.HORIZONTAL) {
            var i = direction.ordinal();

            var stage = stage(faces, i);
            if (stage > 0) {
                var min = stage == 1 ? 0.0d : (6.0d / 16.0f);

                shape = Shapes.or(shape, Util.rotateShape(Shapes.box(0.0d, min, 0.0, 1.0d, 1.0d, 1.0d / 16.0d), i));
            }
        }

        return shape;
    }

    public void growTo(Level level, BlockPos blockPos, int i) {
        var blockState = level.getBlockState(blockPos);

        var value = 2 << toHorizontal(i);

        if (blockState.is(WitchesWayBlockTags.HANGING_LEAVES_CAN_REPLACE)) {
            blockState = setFaces(defaultBlockState(), value);

            if (blockState.canSurvive(level, blockPos)) {
                level.setBlock(blockPos, blockState, UPDATE_ALL);
            }

        } else if (blockState.is(this)) {
            var faces = getFaces(blockState);

            if (stage(faces, i) == 0) {
                level.setBlock(blockPos, setFaces(blockState, faces | value), UPDATE_ALL);
            }

        }
    }

    public int subtracted(LevelReader level, BlockPos blockPos, BlockState blockState) {
        var faces = getFaces(blockState);

        for (var direction : Direction.Plane.HORIZONTAL) {
            var i = direction.ordinal();

            var stage = stage(faces, i);
            if ((stage == 1 && !growsOn(level, blockPos.relative(direction))) || (stage == 2 && !fullStage(level.getBlockState(blockPos.above()), i))) {
                faces &= ~(3 << toHorizontal(i));
            }

        }

        return faces;
    }

    public boolean fullStage(BlockState blockState, int i) {
        return blockState.is(this) && stage(getFaces(blockState), i) == 1;
    }

    public boolean growsOn(LevelReader level, BlockPos blockPos) {
        return growsOn(level, blockPos, level.getBlockState(blockPos));
    }

    public boolean growsOn(BlockGetter getter, BlockPos blockPos, BlockState blockState) {
        return blockState.is(WitchesWayBlockTags.HANGING_LEAVES_CAN_HANG_ON) && blockState.isCollisionShapeFullBlock(getter, blockPos);
    }

    public int stage(int i, int j) {
        return (i >> toHorizontal(j)) & 3;
    }

    public static int toHorizontal(int i) {
        return 2 * (i - 2);
    }

    public static int getFaces(BlockState blockState) {
        return blockState.getValue(FACES);
    }

    public static BlockState setFaces(BlockState blockState, int i) {
        return blockState.setValue(FACES, i);
    }

}
