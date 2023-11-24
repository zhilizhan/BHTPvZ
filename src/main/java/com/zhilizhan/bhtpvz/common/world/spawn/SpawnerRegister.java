package com.zhilizhan.bhtpvz.common.world.spawn;

import com.hungteen.pvz.common.world.spawn.EntitySpawnRegister;
import com.hungteen.pvz.common.world.spawn.SpawnChecker;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = BHTPvZ.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
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
         }

}
