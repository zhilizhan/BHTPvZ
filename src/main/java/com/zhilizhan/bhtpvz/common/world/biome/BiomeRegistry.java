package com.zhilizhan.bhtpvz.common.world.biome;

import com.hungteen.pvz.utils.BiomeUtil;
import com.zhilizhan.bhtpvz.BHTPvZConfig;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.BiomeDictionary.Type;

public class BiomeRegistry {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, "bhtpvz");
    public static final RegistryObject<Biome> NIGHT_BIOME = BIOMES.register("night_biome", NightBiome::getNightBiome);

    public static void registryBiomes(FMLCommonSetupEvent event) {
        registryBiome(NIGHT_BIOME.get(), BiomeType.WARM, BHTPvZConfig.COMMON_CONFIG.WorldSettings.NightBiomeChance.get(), Type.MUSHROOM, Type.PLAINS, Type.FOREST, Type.OVERWORLD);
    }

    public static void registryBiome(Biome biome, BiomeType biomeType, int weight, Type... type) {
        BiomeDictionary.addTypes(BiomeUtil.getKey(biome), type);
        BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(BiomeUtil.getKey(biome), weight));
    }

    public static Biome makeBiome(Biome.Category category, Biome.RainType rainType, float depth, float scale, float temperature, float downFall, BiomeAmbience effect, MobSpawnInfo mobInfo, BiomeGenerationSettings generateSettings) {
        return (new Biome.Builder()).biomeCategory(category).precipitation(rainType).depth(depth).scale(scale).temperature(temperature).downfall(downFall).specialEffects(effect).mobSpawnSettings(mobInfo).generationSettings(generateSettings).build();
    }
}
