package com.zhilizhan.bhtpvz.common.block.grower;

import com.zhilizhan.bhtpvz.common.world.BHTPvZFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nonnull;
import java.util.Random;

public class CherryTreeGrower extends Tree {
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(@Nonnull Random random, boolean largeHive) {
        return BHTPvZFeatures.CHERRY;
    }
}
