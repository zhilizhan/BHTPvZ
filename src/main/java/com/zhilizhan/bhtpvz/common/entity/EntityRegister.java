package com.zhilizhan.bhtpvz.common.entity;


import com.hungteen.pvz.common.entity.PVZEntityClassifications;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.StringUtil;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.client.render.entity.bullet.IceCabbageRender;
import com.zhilizhan.bhtpvz.common.client.render.entity.plant.defence.SteelPumpkinRender;
import com.zhilizhan.bhtpvz.common.client.render.entity.plant.ice.IceCabbagePultRender;
import com.zhilizhan.bhtpvz.common.client.render.entity.zombie.add.*;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.IceCabbageEntity;
import com.zhilizhan.bhtpvz.common.entity.plant.defence.SteelPumpkinEntity;
import com.zhilizhan.bhtpvz.common.entity.plant.ice.IceCabbagePultEntity;
import com.zhilizhan.bhtpvz.common.entity.zombie.add.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.IFactory;
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
public class EntityRegister {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES;
	//植物
	public static final RegistryObject<EntityType<SteelPumpkinEntity>> STEEL_PUMPKIN;//钢南瓜
	public static final RegistryObject<EntityType<IceCabbagePultEntity>> ICE_CABBAGE_PULT;//冰卷心菜投手
	//public static final RegistryObject<EntityType<BargeNutEntity>> BARGE_NUT;
	//public static final RegistryObject<EntityType<BargeNutEntity>> BARGE_NUT;
	//子弹
	public static final RegistryObject<EntityType<IceCabbageEntity>> ICE_CABBAGE;//冰卷心菜子弹
	//僵尸
	public static final RegistryObject<EntityType<Edgar090547Entity>> EDGAR_090547;//埃德加-090547
	public static final RegistryObject<EntityType<FlowerPotZombieEntity>> FLOWER_POT_ZOMBIE;//花盆僵尸
	public static final RegistryObject<EntityType<AirborneZombieEntity>> AIRBORNE_ZOMBIE;//空降僵尸
	public static final RegistryObject<EntityType<MCZombieEntity>> MC_ZOMBIE;//MC僵尸
	public static final RegistryObject<EntityType<SteelPumpkinZombieEntity>> STEEL_PUMPKIN_ZOMBIE;//钢南瓜僵尸
	public static final RegistryObject<EntityType<TargetArrowZombieEntity>> TARGET_ARROW_ZOMBIE;//箭靶僵尸
	//普通生物

	static {
		ENTITY_TYPES =  DeferredRegister.create(ForgeRegistries.ENTITIES, BHTPvZ.MOD_ID);
		//植物
		STEEL_PUMPKIN = registerPlantEntityType(SteelPumpkinEntity::new, "steel_pumpkin");//钢南瓜
		ICE_CABBAGE_PULT = registerPlantEntityType(IceCabbagePultEntity::new, "ice_cabbage_pult");//冰卷心菜投手
		//BARGE_NUT = registerPlantEntityType(BargeNutEntity::new, "barge_nut");
		//BARGE_NUT = registerEntityType(BargeNutEntity::new, "barge_nut", EntityClassification.MISC);
		//子弹
		ICE_CABBAGE = registerEntityType(IceCabbageEntity::new, "ice_cabbage", EntityClassification.MISC);//冰卷心菜子弹
		//僵尸
		EDGAR_090547 = registerZombieEntityType(Edgar090547Entity::new, "edgar_090547");//埃德加-090547
		FLOWER_POT_ZOMBIE = registerZombieEntityType(FlowerPotZombieEntity::new, "flower_pot_zombie");//花盆僵尸
		AIRBORNE_ZOMBIE = registerZombieEntityType(AirborneZombieEntity::new, "airborne_zombie");//空降僵尸
		MC_ZOMBIE = registerZombieEntityType(MCZombieEntity::new, "mc_zombie");//MC僵尸
		STEEL_PUMPKIN_ZOMBIE = registerZombieEntityType(SteelPumpkinZombieEntity::new, "steel_pumpkin_zombie");//钢南瓜僵尸
		TARGET_ARROW_ZOMBIE = registerZombieEntityType(TargetArrowZombieEntity::new, "target_arrow_zombie");//箭靶僵尸
		//普通生物
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onClientSetUpEvent(FMLClientSetupEvent event) {
		//植物
		RenderingRegistry.registerEntityRenderingHandler(STEEL_PUMPKIN.get(), SteelPumpkinRender::new);//钢南瓜
		RenderingRegistry.registerEntityRenderingHandler(ICE_CABBAGE_PULT.get(), IceCabbagePultRender::new);//冰卷心菜投手
		//RenderingRegistry.registerEntityRenderingHandler(BARGE_NUT.get(), BargeNutRender::new);
		//子弹
		RenderingRegistry.registerEntityRenderingHandler(ICE_CABBAGE.get(), IceCabbageRender::new);//冰卷心菜子弹
		//僵尸
		RenderingRegistry.registerEntityRenderingHandler(EDGAR_090547.get(), Edgar090547Render::new);//埃德加-090547
		RenderingRegistry.registerEntityRenderingHandler(FLOWER_POT_ZOMBIE.get(), FlowerPotZombieRender::new);//花盆僵尸
		RenderingRegistry.registerEntityRenderingHandler(AIRBORNE_ZOMBIE.get(), AirborneZombieRender::new);//空降僵尸
		RenderingRegistry.registerEntityRenderingHandler(MC_ZOMBIE.get(), MCZombieRender::new);//MC僵尸
		RenderingRegistry.registerEntityRenderingHandler(STEEL_PUMPKIN_ZOMBIE.get(), SteelPumpkinZombieRender::new);//钢南瓜僵尸
		RenderingRegistry.registerEntityRenderingHandler(TARGET_ARROW_ZOMBIE.get(), TargetArrowZombieRender::new);//箭靶僵尸
		//普通生物
	}



	private static <T extends PVZPlantEntity> RegistryObject<EntityType<T>> registerPlantEntityType(IFactory<T> factory, String name){
		return ENTITY_TYPES.register(name, () -> {return EntityType.Builder.of(factory, PVZEntityClassifications.PVZ_PLANT).build(StringUtil.prefix(name).toString());});
	}
	private static <T extends PVZZombieEntity> RegistryObject<EntityType<T>> registerZombieEntityType(IFactory<T> factory, String name){
		return ENTITY_TYPES.register(name, () -> {return EntityType.Builder.of(factory, PVZEntityClassifications.PVZ_ZOMBIE).fireImmune().build(StringUtil.prefix(name).toString());});
	}


	private static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(IFactory<T> factory, String name, EntityClassification classification){
		return ENTITY_TYPES.register(name, () -> {return EntityType.Builder.of(factory, classification).build(StringUtil.prefix(name).toString());});
	}
	
	private static <T extends Entity> RegistryObject<EntityType<T>> registerImmuneFireEntityType(IFactory<T> factory, String name, EntityClassification classification){
		return ENTITY_TYPES.register(name, () -> {return EntityType.Builder.of(factory, classification).fireImmune().build(StringUtil.prefix(name).toString());});
	}
	
	private static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(IFactory<T> factory, String name, EntityClassification classification, float w, float h){
		return ENTITY_TYPES.register(name, () -> {return EntityType.Builder.of(factory, classification).sized(w, h).build(StringUtil.prefix(name).toString());});
	}
}
