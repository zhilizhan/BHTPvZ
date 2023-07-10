package com.zhilizhan.bhtpvz.common.entity;

import com.hungteen.pvz.client.render.entity.plant.ice.SnowPeaRender;
import com.hungteen.pvz.common.entity.PVZEntityClassifications;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.StringUtil;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.client.render.entity.bullet.*;
import com.zhilizhan.bhtpvz.client.render.entity.misc.RedSunRender;
import com.zhilizhan.bhtpvz.client.render.entity.normal.OriginMoobRender;
import com.zhilizhan.bhtpvz.client.render.entity.plant.appease.GrassCrapRender;
import com.zhilizhan.bhtpvz.client.render.entity.plant.arma.BurstKernelPultRender;
import com.zhilizhan.bhtpvz.client.render.entity.plant.arma.ChorusFruitPultRender;
import com.zhilizhan.bhtpvz.client.render.entity.plant.defence.SelfImitaterRender;
import com.zhilizhan.bhtpvz.client.render.entity.plant.defence.SteelPumpkinRender;
import com.zhilizhan.bhtpvz.client.render.entity.plant.enforce.NutBowlingRender;
import com.zhilizhan.bhtpvz.client.render.entity.plant.enforce.RotateRadishRender;
import com.zhilizhan.bhtpvz.client.render.entity.plant.fire.BlazeWartRender;
import com.zhilizhan.bhtpvz.client.render.entity.plant.fire.FirePeashooterRender;
import com.zhilizhan.bhtpvz.client.render.entity.plant.ice.IceCabbagePultRender;
import com.zhilizhan.bhtpvz.client.render.entity.zombie.bhtpvz.*;
import com.zhilizhan.bhtpvz.common.entity.bullet.BurstCornEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.ChorusFruitEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.CornEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.PoppedChorusFruitEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.FishPeaEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.IceCabbageEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.IcePeaEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.PopCornEntity;
import com.zhilizhan.bhtpvz.common.entity.misc.RedSunEntity;
import com.zhilizhan.bhtpvz.common.entity.normal.OriginMoobEntity;
import com.zhilizhan.bhtpvz.common.entity.plant.appease.GrassCarpEntity;
import com.zhilizhan.bhtpvz.common.entity.plant.arma.BurstKernelPultEntity;
import com.zhilizhan.bhtpvz.common.entity.plant.arma.ChorusFruitPultEntity;
import com.zhilizhan.bhtpvz.common.entity.plant.defence.SelfImitaterEntity;
import com.zhilizhan.bhtpvz.common.entity.plant.defence.SteelPumpkinEntity;
import com.zhilizhan.bhtpvz.common.entity.plant.enforce.NutBowlingEntity;
import com.zhilizhan.bhtpvz.common.entity.plant.enforce.RotateRadishEntity;
import com.zhilizhan.bhtpvz.common.entity.plant.fire.BlazeWartEntity;
import com.zhilizhan.bhtpvz.common.entity.plant.fire.FirePeashooterEntity;
import com.zhilizhan.bhtpvz.common.entity.plant.ice.IceCabbagePultEntity;
import com.zhilizhan.bhtpvz.common.entity.plant.ice.ReIcePeaEntity;
import com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BHTPvZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BHTPvZEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, BHTPvZ.MOD_ID);
	// 植物
	public static final RegistryObject<EntityType<SteelPumpkinEntity>> STEEL_PUMPKIN = registerPlantEntityType(SteelPumpkinEntity::new, "steel_pumpkin"); // 钢南瓜
	public static final RegistryObject<EntityType<IceCabbagePultEntity>> ICE_CABBAGE_PULT = registerPlantEntityType(IceCabbagePultEntity::new, "ice_cabbage_pult"); // 冰卷心菜投手
	public static final RegistryObject<EntityType<FirePeashooterEntity>> FIRE_PEASHOOTER = registerPlantEntityType(FirePeashooterEntity::new, "fire_peashooter"); // 火焰豌豆
	public static final RegistryObject<EntityType<NutBowlingEntity>> NUT_BOWLING = registerPlantEntityType(NutBowlingEntity::new, "nut_bowling"); // 保龄球坚果
	public static final RegistryObject<EntityType<ChorusFruitPultEntity>> CHORUS_FRUIT_PULT = registerPlantEntityType(ChorusFruitPultEntity::new, "chorus_fruit_pult"); // 紫颂果投手
	public static final RegistryObject<EntityType<RotateRadishEntity>> ROTATE_RADISH = registerPlantEntityType(RotateRadishEntity::new, "rotate_radish"); // 转转萝卜
	public static final RegistryObject<EntityType<BurstKernelPultEntity>> BURST_KERNEL_PULT = registerPlantEntityType(BurstKernelPultEntity::new, "burst_kernel_pult"); // 爆裂玉米投手
	public static final RegistryObject<EntityType<BlazeWartEntity>> BLAZE_WART = registerPlantEntityType(BlazeWartEntity::new, "blaze_wart"); // 烈焰庞
	public static final RegistryObject<EntityType<GrassCarpEntity>> GRASS_CARP = registerPlantEntityType(GrassCarpEntity::new, "grass_carp");//草鱼
	public static final RegistryObject<EntityType<SelfImitaterEntity>> SELF_IMITATER = registerPlantEntityType(SelfImitaterEntity::new, "self_imitater");//自己模仿者
	public static final RegistryObject<EntityType<ReIcePeaEntity>> RE_ICEPEA = registerPlantEntityType(ReIcePeaEntity::new, "re_icepea");//双发寒冰

	// 子弹
	public static final RegistryObject<EntityType<IceCabbageEntity>> ICE_CABBAGE = registerEntityType(IceCabbageEntity::new, "ice_cabbage", MobCategory.MISC); // 冰卷心菜子弹
	public static final RegistryObject<EntityType<PoppedChorusFruitEntity>> POPPED_CHORUS_FRUIT = registerEntityType(PoppedChorusFruitEntity::new, "popped_chorus_fruit", MobCategory.MISC); // 爆裂紫颂果子弹
	public static final RegistryObject<EntityType<ChorusFruitEntity>> CHORUS_FRUIT = registerEntityType(ChorusFruitEntity::new, "chorus_fruit", MobCategory.MISC); // 紫颂果子弹
	public static final RegistryObject<EntityType<CornEntity>> CORN = registerEntityType(CornEntity::new, "corn", MobCategory.MISC); // 玉米子弹
	public static final RegistryObject<EntityType<BurstCornEntity>> BURST_CORN = registerEntityType(BurstCornEntity::new, "burst_corn", MobCategory.MISC); // 炸裂玉米子弹
	public static final RegistryObject<EntityType<PopCornEntity>> POP_CORN = registerEntityType(PopCornEntity::new, "pop_corn", MobCategory.MISC); // 爆米花子弹
	public static final RegistryObject<EntityType<FishPeaEntity>> FISH_PEA = registerEntityType(FishPeaEntity::new, "fish_pea",MobCategory.MISC);//草鱼生的（豌豆
	public static final RegistryObject<EntityType<IcePeaEntity>> ICE_PEA = registerEntityType(IcePeaEntity::new, "ice_pea",MobCategory.MISC);//冰结豌豆

	// 僵尸
	public static final RegistryObject<EntityType<Edgar090547Entity>> EDGAR_090547 = registerZombieEntityType(Edgar090547Entity::new, "edgar_090547"); // 埃德加-090547
	public static final RegistryObject<EntityType<FlowerPotZombieEntity>> FLOWER_POT_ZOMBIE = registerZombieEntityType(FlowerPotZombieEntity::new, "flower_pot_zombie"); // 花盆僵尸
	public static final RegistryObject<EntityType<AirborneZombieEntity>> AIRBORNE_ZOMBIE = registerZombieEntityType(AirborneZombieEntity::new, "airborne_zombie"); // 空降僵尸
	public static final RegistryObject<EntityType<MCZombieEntity>> MC_ZOMBIE = registerZombieEntityType(MCZombieEntity::new, "mc_zombie"); // MC僵尸
	public static final RegistryObject<EntityType<SteelPumpkinZombieEntity>> STEEL_PUMPKIN_ZOMBIE = registerZombieEntityType(SteelPumpkinZombieEntity::new, "steel_pumpkin_zombie"); // 钢南瓜僵尸
	public static final RegistryObject<EntityType<TargetArrowZombieEntity>> TARGET_ARROW_ZOMBIE = registerZombieEntityType(TargetArrowZombieEntity::new, "target_arrow_zombie"); // 箭靶僵尸
	public static final RegistryObject<EntityType<RedEdgeZombieEntity>> RED_EDGE_ZOMBIE = registerZombieEntityType(RedEdgeZombieEntity::new, "red_edge_zombie"); // 红刀僵尸
	public static final RegistryObject<EntityType<MJZombieEntity>> MJ_ZOMBIE = registerZombieEntityType(MJZombieEntity::new, "mj_zombie"); // 舞王僵尸
	public static final RegistryObject<EntityType<DancerBackupEntity>> DANCER_BACKUP_ZOMBIE = registerZombieEntityType(DancerBackupEntity::new, "dancer_backup_zombie"); // 伴舞僵尸
	public static final RegistryObject<EntityType<SunFlowerZombieEntity>> SUN_FLOWER_ZOMBIE = registerZombieEntityType(SunFlowerZombieEntity::new, "sun_flower_zombie"); // 太阳花僵尸

	// 普通生物
	public static final RegistryObject<EntityType<OriginMoobEntity>> ORIGIN_MOOB = registerEntityType(OriginMoobEntity::new, "origin_moob",MobCategory.CREATURE);//起源蘑菇牛
	//杂项
	public static final RegistryObject<EntityType<RedSunEntity>> RED_SUN = registerEntityType(RedSunEntity::new, "red_sun",MobCategory.MISC);//红色阳光



	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onClientSetUpEvent(FMLClientSetupEvent event) {
		// 植物
		RenderingRegistry.registerEntityRenderingHandler(STEEL_PUMPKIN.get(), SteelPumpkinRender::new); // 钢南瓜
		RenderingRegistry.registerEntityRenderingHandler(ICE_CABBAGE_PULT.get(), IceCabbagePultRender::new); // 冰卷心菜投手
		RenderingRegistry.registerEntityRenderingHandler(FIRE_PEASHOOTER.get(), FirePeashooterRender::new); // 火焰豌豆
		RenderingRegistry.registerEntityRenderingHandler(NUT_BOWLING.get(), NutBowlingRender::new); // 保龄球坚果
		RenderingRegistry.registerEntityRenderingHandler(CHORUS_FRUIT_PULT.get(), ChorusFruitPultRender::new); // 紫颂果投手
		RenderingRegistry.registerEntityRenderingHandler(ROTATE_RADISH.get(), RotateRadishRender::new); // 转转萝卜
		RenderingRegistry.registerEntityRenderingHandler(BURST_KERNEL_PULT.get(), BurstKernelPultRender::new); // 爆裂玉米投手
		RenderingRegistry.registerEntityRenderingHandler(BLAZE_WART.get(), BlazeWartRender::new); // 烈焰庞
		RenderingRegistry.registerEntityRenderingHandler(SELF_IMITATER.get(), SelfImitaterRender::new); // 自己模仿者
		RenderingRegistry.registerEntityRenderingHandler(RE_ICEPEA.get(), SnowPeaRender::new); // 双发寒冰

		// 子弹
		RenderingRegistry.registerEntityRenderingHandler(ICE_CABBAGE.get(), IceCabbageRender::new); // 冰卷心菜子弹
		RenderingRegistry.registerEntityRenderingHandler(POPPED_CHORUS_FRUIT.get(), PoppedChorusFruitRender::new); // 爆裂紫颂果子弹
		RenderingRegistry.registerEntityRenderingHandler(CHORUS_FRUIT.get(), ChorusFruitRender::new); // 紫颂果子弹
		RenderingRegistry.registerEntityRenderingHandler(CORN.get(), CornRender::new); // 玉米子弹
		RenderingRegistry.registerEntityRenderingHandler(BURST_CORN.get(), BurstCornRender::new); // 爆裂玉米子弹
		RenderingRegistry.registerEntityRenderingHandler(POP_CORN.get(), PopCornRender::new); // 爆米花子弹
		RenderingRegistry.registerEntityRenderingHandler(FISH_PEA.get(), FishPeRender::new); // 鱼豌豆
		RenderingRegistry.registerEntityRenderingHandler(ICE_PEA.get(), IcePeaRender::new); // 冰结豌豆

		// 僵尸
		RenderingRegistry.registerEntityRenderingHandler(EDGAR_090547.get(), Edgar090547Render::new); // 埃德加-090547
		RenderingRegistry.registerEntityRenderingHandler(FLOWER_POT_ZOMBIE.get(), FlowerPotZombieRender::new); // 花盆僵尸
		RenderingRegistry.registerEntityRenderingHandler(AIRBORNE_ZOMBIE.get(), AirborneZombieRender::new); // 空降僵尸
		RenderingRegistry.registerEntityRenderingHandler(MC_ZOMBIE.get(), MCZombieRender::new); // MC僵尸
		RenderingRegistry.registerEntityRenderingHandler(STEEL_PUMPKIN_ZOMBIE.get(), SteelPumpkinZombieRender::new); // 钢南瓜僵尸
		RenderingRegistry.registerEntityRenderingHandler(TARGET_ARROW_ZOMBIE.get(), TargetArrowZombieRender::new); // 箭靶僵尸
		RenderingRegistry.registerEntityRenderingHandler(RED_EDGE_ZOMBIE.get(), RedEdgeZombieRender::new); // 红刀僵尸
		RenderingRegistry.registerEntityRenderingHandler(MJ_ZOMBIE.get(), MJZombieRender::new); // 舞王僵尸
		RenderingRegistry.registerEntityRenderingHandler(DANCER_BACKUP_ZOMBIE.get(), DancerBackupRender::new); // 伴舞僵尸
		RenderingRegistry.registerEntityRenderingHandler(SUN_FLOWER_ZOMBIE.get(), SunFlowerZombieRender::new); // 太阳花僵尸

		// 普通生物
		RenderingRegistry.registerEntityRenderingHandler(ORIGIN_MOOB.get(), OriginMoobRender::new); // 起源蘑菇牛
		RenderingRegistry.registerEntityRenderingHandler(GRASS_CARP.get(), GrassCrapRender::new); // 草鱼

		//杂项
		RenderingRegistry.registerEntityRenderingHandler(RED_SUN.get(), RedSunRender::new); // 红太阳

	}
	private static <T extends PVZPlantEntity> RegistryObject<EntityType<T>> registerPlantEntityType(EntityType.EntityFactory<T> factory, String name){
		return ENTITY_TYPES.register(name, () -> EntityType.Builder.of(factory, PVZEntityClassifications.PVZ_PLANT).build(StringUtil.prefix(name).toString()));
	}

	private static <T extends PVZZombieEntity> RegistryObject<EntityType<T>> registerZombieEntityType(EntityType.EntityFactory<T> factory, String name){
		return ENTITY_TYPES.register(name, () -> EntityType.Builder.of(factory, PVZEntityClassifications.PVZ_ZOMBIE).fireImmune().build(StringUtil.prefix(name).toString()));
	}

	private static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(EntityType.EntityFactory<T> factory, String name, MobCategory category){
		return ENTITY_TYPES.register(name, () -> EntityType.Builder.of(factory, category).build(StringUtil.prefix(name).toString()));
	}
}
