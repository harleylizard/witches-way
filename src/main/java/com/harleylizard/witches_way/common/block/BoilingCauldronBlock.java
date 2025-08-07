package com.harleylizard.witches_way.common.block;

import com.harleylizard.witches_way.common.BlockStateTag;
import com.harleylizard.witches_way.common.IsBlockStateTag;
import com.harleylizard.witches_way.common.WitchesWayBlockEntities;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorageUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoilingCauldronBlock extends Block implements EntityBlock {
    private static final VoxelShape SHAPE = Shapes.or(
            Shapes.box(0.0625, 0.125, 0.0625, 0.9375, 0.75, 0.9375),
            Shapes.box(0.6875, 0, 0.6875, 0.8125, 0.125, 0.8125),
            Shapes.box(0.1875, 0, 0.6875, 0.3125, 0.125, 0.8125),
            Shapes.box(0.1875, 0, 0.1875, 0.3125, 0.125, 0.3125),
            Shapes.box(0.6875, 0, 0.1875, 0.8125, 0.125, 0.3125),
            Shapes.box(0.125, 0.75, 0.125, 0.875, 0.9375, 0.875)
    );

    public static final BooleanProperty HEATED = BooleanProperty.create("heated");

    public BoilingCauldronBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X).setValue(HEATED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_AXIS, HEATED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return set(defaultBlockState(), blockPlaceContext.getHorizontalDirection().getAxis());
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        var water = itemStack.is(Items.WATER_BUCKET);

        if (water || itemStack.is(Items.BUCKET)) {
            var blockEntity = level.getBlockEntity(blockPos, WitchesWayBlockEntities.BOILING_CAULDRON).orElseThrow();

            var fluid = blockEntity.getFluid();

            if (!water && fluid.isResourceBlank()) {
                return ItemInteractionResult.FAIL;
            }

            if (FluidStorageUtil.interactWithFluidStorage(fluid, player, interactionHand) && !level.isClientSide) {
                return ItemInteractionResult.CONSUME;
            }

            return ItemInteractionResult.SUCCESS;
        }

        return ItemInteractionResult.FAIL;
    }

    @Override
    protected void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        var below = blockPos.below();

        serverLevel.setBlock(blockPos, blockState.setValue(HEATED, ((IsBlockStateTag) serverLevel.getBlockState(below)).witchesWay$is(BlockStateTag.get("witches-way:boiling_cauldron_heat_source"))), UPDATE_ALL);
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState blockState) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        var fluid = level.getBlockEntity(blockPos, WitchesWayBlockEntities.BOILING_CAULDRON).orElseThrow().getFluid();

        return (int) Math.ceil(((float) fluid.amount / (float) fluid.getCapacity()) * 6.0f);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return WitchesWayBlockEntities.BOILING_CAULDRON.create(blockPos, blockState);
    }

    public static BlockState set(BlockState blockState, Direction.Axis axis) {
        return blockState.setValue(BlockStateProperties.HORIZONTAL_AXIS, axis);
    }

}
