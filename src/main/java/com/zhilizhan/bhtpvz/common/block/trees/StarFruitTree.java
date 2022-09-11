package com.zhilizhan.bhtpvz.common.block.trees;

import com.zhilizhan.bhtpvz.common.world.FeaturesRegistry;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class StarFruitTree extends Tree {
    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random p_225546_1_, boolean p_225546_2_) {
        return FeaturesRegistry.STAR_FRUIT_TREE;
    }
}
