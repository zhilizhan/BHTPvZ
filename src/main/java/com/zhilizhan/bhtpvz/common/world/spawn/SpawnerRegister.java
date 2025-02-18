package com.zhilizhan.bhtpvz.common.world.spawn;

import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.world.biome.BHTPvZBiomes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = BHTPvZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpawnerRegister {

    @SubscribeEvent
    public static void addSpawn(BiomeLoadingEvent event) {
        RegistryKey<Biome> biomeKey = RegistryKey.create(Registry.BIOME_REGISTRY, Objects.requireNonNull(event.getName()));
        if(biomeKey== BHTPvZBiomes.NIGHT_KEY) {
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(BHTPvZEntityTypes.TOXIC_MOOB.get(), 3, 1, 1));
        }
        if(biomeKey== BHTPvZBiomes.ZEN_GANIGHT_KEY) {
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(BHTPvZEntityTypes.ORIGIN_MOOB.get(), 5, 1, 1)); }
    }

}
