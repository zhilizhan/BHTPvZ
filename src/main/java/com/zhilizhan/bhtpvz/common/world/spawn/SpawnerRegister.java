package com.zhilizhan.bhtpvz.common.world.spawn;

import com.hungteen.pvz.common.world.spawn.EntitySpawnRegister;
import com.hungteen.pvz.common.world.spawn.SpawnChecker;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.entity.normal.OriginMoobEntity;
import com.zhilizhan.bhtpvz.common.world.biome.BHTPvZBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = BHTPvZ.MOD_ID)
public class SpawnerRegister {

    @SubscribeEvent
    public static void registerEntitySpawns(RegistryEvent.Register<EntityType<?>> evt) {
        SpawnPlacements.register((EntityType) BHTPvZEntityTypes.MC_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canZombieSpawn);
        SpawnPlacements.register((EntityType)BHTPvZEntityTypes.AIRBORNE_ZOMBIE.get(), EntitySpawnRegister.IN_SKY, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canZombieSpawn);
        SpawnPlacements.register((EntityType)BHTPvZEntityTypes.STEEL_PUMPKIN_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canZombieSpawn);
        SpawnPlacements.register((EntityType)BHTPvZEntityTypes.MJ_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canZombieSpawn);
        SpawnPlacements.register((EntityType)BHTPvZEntityTypes.EDGAR_090547.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canZombieSpawn);
        SpawnPlacements.register((EntityType)BHTPvZEntityTypes.FLOWER_POT_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canZombieSpawn);
        SpawnPlacements.register((EntityType)BHTPvZEntityTypes.TARGET_ARROW_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canZombieSpawn);
        SpawnPlacements.register((EntityType)BHTPvZEntityTypes.CHOMPER_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canZombieSpawn);
        SpawnPlacements.register((EntityType)BHTPvZEntityTypes.RED_EDGE_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canZombieSpawn);

       // SpawnPlacements.register((EntityType)BHTPvZEntityTypes.ORIGIN_MOOB.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpawnerRegister::checkOriginMoobSpawnRules);

    }
    @SubscribeEvent(priority =  EventPriority.HIGH)
    public static void addSpawn(BiomeLoadingEvent event) {
        ResourceKey<Biome> biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
        if(biomeKey== BHTPvZBiomes.BEWILDER_GARDEN_KEY) {
            event.getSpawns().addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData((EntityType<OriginMoobEntity>) BHTPvZEntityTypes.ORIGIN_MOOB.get(), 40, 1, 1));
        }
    }
    public static boolean checkOriginMoobSpawnRules(EntityType<OriginMoobEntity> originMoob, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, Random random) {
        return true;
    }

}
