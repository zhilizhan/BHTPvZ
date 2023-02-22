package com.zhilizhan.bhtpvz.common.world.biome;

import com.hungteen.pvz.common.world.FeatureRegister;
import com.hungteen.pvz.common.world.feature.GenOres;
import com.hungteen.pvz.common.world.spawn.EntitySpawnRegister;
import com.hungteen.pvz.common.world.structure.StructureRegister;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.utils.BiomeUtil;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeRegistry {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, BHTPvZ.MOD_ID);

    public static final RegistryObject<Biome> NIGHT_BIOME = BIOMES.register("night_biome", NightBiome::getNightBiome);

<<<<<<< HEAD
=======

>>>>>>> 98a1f6f (增加仓库)
    public static void registerBiomes(final FMLCommonSetupEvent ev) {
        registerBiome(NIGHT_BIOME.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
    }

<<<<<<< HEAD
=======

>>>>>>> 98a1f6f (增加仓库)
    public static void biomeModification(final BiomeLoadingEvent event) {
        Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
        if(biome != null) {//prevent crash with other mod.
            if(biome.equals(BiomeRegistry.NIGHT_BIOME.get())){
                event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureRegister.CONFIGURED_DOUBLE_ORIGIN_ORE);
            } else {
                final RegistryKey<Biome> biomeKey = BiomeUtil.getKey(biome);
                StructureRegister.addStructureToBiome(event, biomeKey);
                EntitySpawnRegister.addEntitySpawnToBiome(event, biomeKey);
                GenOres.addOresToBiomes(event, biomeKey);
            }
        }
    }

    private static void registerBiome(final Biome biome, final BiomeDictionary.Type... types) {
        BiomeDictionary.addTypes(BiomeUtil.getKey(biome), types);
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BiomeUtil.getKey(biome), 10));
    }

    public static Biome makeBiome(Biome.Category category, Biome.RainType rainType, float depth, float scale, float temperature, float downFall, BiomeAmbience effect, MobSpawnInfo mobInfo, BiomeGenerationSettings generateSettings) {
        return new Biome.Builder()
                .biomeCategory(category)
                .precipitation(rainType)
                .depth(depth)
                .scale(scale)
                .temperature(temperature)
                .downfall(downFall)
                .specialEffects(effect)
                .mobSpawnSettings(mobInfo)
                .generationSettings(generateSettings)
                .build();
    }
}
