package com.zhilizhan.bhtpvz.common.world.biome;

import com.hungteen.pvz.common.world.biome.BiomeRegister;
import com.zhilizhan.bhtpvz.common.world.FeaturesRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class DecorationGenerate {

    @SubscribeEvent
    public static void addOresToBiomes(final BiomeLoadingEvent event) {
        if (event.getCategory().equals(Biome.Category.NETHER)) {
            event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeaturesRegistry.ORE_MORION);//黑晶矿生成
        }
    }

    @SubscribeEvent
    public static void addTreesToBiomes(final BiomeLoadingEvent event) {
        Biome biome = (Biome) ForgeRegistries.BIOMES.getValue(event.getName());

        if (biome != null) {
            if (biome.equals(BiomeRegister.ZEN_GARDEN.get())) {
                event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeaturesRegistry.TREES_CHERRY);//樱桃树生成
                event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeaturesRegistry.TREES_STAR_FRUIT);//杨桃树生成
            }
        }
    }

    @SubscribeEvent
    public static void addBlocksToBiomes(final BiomeLoadingEvent event) {
        Biome biome = (Biome) ForgeRegistries.BIOMES.getValue(event.getName());

        if (biome != null) {
            if (biome.equals(BiomeRegister.ZEN_GARDEN.get())) {
                event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeaturesRegistry.PATCH_SQUASH);//窝瓜生成
                event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeaturesRegistry.PATCH_QUESTION_MARK_POT);//问号罐生成
                event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeaturesRegistry.PATCH_PLANT_POT);//植物罐生成
            }
        }
    }
}
