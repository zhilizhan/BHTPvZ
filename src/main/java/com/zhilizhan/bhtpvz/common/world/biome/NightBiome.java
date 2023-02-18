package com.zhilizhan.bhtpvz.common.world.biome;

import com.zhilizhan.bhtpvz.common.world.FeaturesRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraft.world.biome.BiomeAmbience.Builder;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;

public class NightBiome {



    public static Biome getNightBiome() {
        Builder effects = (new Builder()).fogColor(0x4F4E55).waterColor(0x8A8997).waterFogColor(0x8A8997).skyColor(0x4F4E55).foliageColorOverride(0x2BBB0F).grassColorOverride(0x55C93F).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS);
        net.minecraft.world.biome.MobSpawnInfo.Builder mobSpawnInfo = new net.minecraft.world.biome.MobSpawnInfo.Builder();
        mobSpawnInfo.setPlayerCanSpawn();
        mobSpawnInfo.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.MOOSHROOM, 8, 4, 8));
        net.minecraft.world.biome.BiomeGenerationSettings.Builder biomeGenerationSettings = (new net.minecraft.world.biome.BiomeGenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.MYCELIUM);
        biomeGenerationSettings.addStructureStart(StructureFeatures.STRONGHOLD);//生成末地传送门
        DefaultBiomeFeatures.addMushroomFieldVegetation(biomeGenerationSettings);//生成大蘑菇
        DefaultBiomeFeatures.addDefaultMushrooms(biomeGenerationSettings);//生成蘑菇
        DefaultBiomeFeatures.addDefaultCarvers(biomeGenerationSettings);//生成矿洞和峡谷
        DefaultBiomeFeatures.addDefaultOres(biomeGenerationSettings);//生成矿石
        DefaultBiomeFeatures.addSurfaceFreezing(biomeGenerationSettings);
        biomeGenerationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeaturesRegistry.PATCH_TOXIC_SHROOM);//生成孢子
        biomeGenerationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeaturesRegistry.PATCH_QUESTION_MARK_POT);//生成问号罐
        return BiomeRegistry.makeBiome(Category.PLAINS, RainType.RAIN, 0.125F, 0.25F, 0.8F, 0.4F, effects.build(), mobSpawnInfo.build(),biomeGenerationSettings.build());
    }
}
