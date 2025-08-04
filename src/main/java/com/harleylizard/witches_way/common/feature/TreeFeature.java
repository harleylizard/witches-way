package com.harleylizard.witches_way.common.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public final class TreeFeature extends Feature<TreeFeatureConfiguration> {

    public TreeFeature() {
        super(TreeFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<TreeFeatureConfiguration> featurePlaceContext) {
        var config = featurePlaceContext.config();

        var random = featurePlaceContext.random();
        var height = config.height().get(random);

        var level = featurePlaceContext.level();
        var blockPos = featurePlaceContext.origin();

        var log = config.log();

        for (var i = 0; i < height; i++) {
            place(level, blockPos.above(i), log, random);
        }

        return true;
    }

    public void place(WorldGenLevel level, BlockPos blockPos, BlockStateProvider provider, RandomSource random) {
        if (canReplace(level.getBlockState(blockPos))) {
            level.setBlock(blockPos, provider.getState(random, blockPos), Block.UPDATE_ALL);

        }
    }

    public boolean canReplace(BlockState blockState) {
        return blockState.is(BlockTags.REPLACEABLE) || blockState.is(BlockTags.REPLACEABLE_BY_TREES);
    }

}
