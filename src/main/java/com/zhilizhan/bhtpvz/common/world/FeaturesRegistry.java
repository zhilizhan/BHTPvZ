package com.zhilizhan.bhtpvz.common.world;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.hungteen.pvz.common.block.BlockRegister;
import com.zhilizhan.bhtpvz.common.block.BlockRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class FeaturesRegistry {
    public static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String p_1, ConfiguredFeature<FC, ?> p_2) {
        return (ConfiguredFeature)Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, p_1, p_2);
    }

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CHERRY_TREE;//樱桃树
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> STAR_FRUIT_TREE;//杨桃树

    public static final ConfiguredFeature<?, ?> TREES_CHERRY;//樱桃树生成
    public static final ConfiguredFeature<?, ?> TREES_STAR_FRUIT;//杨桃树生成
    public static final ConfiguredFeature<?, ?> ORE_MORION;//黑晶矿生成
    public static final ConfiguredFeature<?, ?> PATCH_SQUASH;//窝瓜生成
    public static final ConfiguredFeature<?, ?> PATCH_TOXIC_SHROOM;//孢子生成
    public static final ConfiguredFeature<?, ?> PATCH_QUESTION_MARK_POT;//问号罐生成
    public static final ConfiguredFeature<?, ?> PATCH_PLANT_POT;//植物罐生成

    static {
        CHERRY_TREE = Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.OAK_LOG.defaultBlockState()), new SimpleBlockStateProvider((BlockRegistry.CHERRY_LEAVES.get()).defaultBlockState()), new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3), new StraightTrunkPlacer(6, 3, 0), new TwoLayerFeature(1, 0, 1))).ignoreVines().build());//樱桃树
        STAR_FRUIT_TREE = Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.OAK_LOG.defaultBlockState()), new SimpleBlockStateProvider((BlockRegistry.STAR_FRUIT_LEAVES.get()).defaultBlockState()), new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3), new StraightTrunkPlacer(6, 3, 0), new TwoLayerFeature(1, 0, 1))).ignoreVines().build());//杨桃树

        TREES_CHERRY = register("trees_cherry", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CHERRY_TREE.weighted(1.0F)), CHERRY_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.02F, 1))));//樱桃树生成
        TREES_STAR_FRUIT = register("trees_cherry", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(STAR_FRUIT_TREE.weighted(1.0F)), STAR_FRUIT_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1,0.02F, 1))));//杨桃树生成
        ORE_MORION = register("ore_morion", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, BlockRegistry.MORION_ORE.get().defaultBlockState(), 10)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(32, 10, 80)).squared().count(7)));//黑晶矿生成
        PATCH_SQUASH = register("patch_squash", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider((BlockRegistry.SQUASH.get()).defaultBlockState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).noProjection().build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(32));//窝瓜生成
        PATCH_TOXIC_SHROOM = register("patch_toxic_shroom", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider((BlockRegister.TOXIC_SHROOM.get()).defaultBlockState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(Blocks.MYCELIUM.getBlock())).noProjection().build()).decorated(Features.Placements.HEIGHTMAP_SQUARE).chance(32));//孢子生成
        PATCH_QUESTION_MARK_POT = register("patch_question_mark_pot", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider((BlockRegistry.QUESTION_MARK_POT.get()).defaultBlockState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).noProjection().build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(32));//问号罐生成
        PATCH_PLANT_POT = register("patch_plant_pot", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider((BlockRegistry.PLANT_POT.get()).defaultBlockState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).noProjection().build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(32));//植物罐生成
    }
}
