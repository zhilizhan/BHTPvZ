package com.zhilizhan.bhtpvz.common.impl.zombie;

import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.client.model.entity.zombie.roof.EdgarRobotModel;
import com.hungteen.pvz.common.impl.RankTypes;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz.*;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BHTPvZZombies extends ZombieType {
	private static final List<IZombieType> LIST = new ArrayList<>();

	//埃德加-090547
	public static final ZombieType EDGAR_090547 = new BHTPvZZombies("edgar_090547", new ZombieFeatures().rank(RankTypes.PURPLE).xp(50).entityType(BHTPvZEntityTypes.EDGAR_090547::get).zombieModel(() -> EdgarRobotModel::new).scale(0.5f).eatCommonSkill(Collections.emptyList()));
	//花盆僵尸
	public static final ZombieType FLOWER_POT_ZOMBIE = new BHTPvZZombies("flower_pot_zombie", new ZombieFeatures().rank(RankTypes.GREEN).xp(10).entityType(BHTPvZEntityTypes.FLOWER_POT_ZOMBIE::get).zombieModel(() -> FlowerPotZombieModel::new).scale(0.5f).eatCommonSkill(Collections.emptyList()));
	//空降僵尸
	public static final ZombieType AIRBORNE_ZOMBIE = new BHTPvZZombies("airborne_zombie", new ZombieFeatures().rank(RankTypes.GREEN).xp(10).entityType(BHTPvZEntityTypes.AIRBORNE_ZOMBIE::get).zombieModel(() -> AirborneZombieModel::new).scale(0.5f).eatCommonSkill(Collections.emptyList()));
	//MC僵尸
	public static final ZombieType MC_ZOMBIE = new BHTPvZZombies("mc_zombie", new ZombieFeatures().rank(RankTypes.WHITE).xp(5).entityType(BHTPvZEntityTypes.MC_ZOMBIE::get).zombieModel(() -> MCZombieModel::new).scale(0.5f).eatCommonSkill(Collections.emptyList()));
	//钢南瓜僵尸
	public static final ZombieType STEEL_PUMPKIN_ZOMBIE = new BHTPvZZombies("steel_pumpkin_zombie", new ZombieFeatures().rank(RankTypes.BLUE).xp(25).entityType(BHTPvZEntityTypes.STEEL_PUMPKIN_ZOMBIE::get).zombieModel(() -> MCZombieModel::new).scale(0.5f).eatCommonSkill(Collections.emptyList()));
	//箭靶僵尸
	public static final ZombieType TARGET_ARROW_ZOMBIE = new BHTPvZZombies("target_arrow_zombie", new ZombieFeatures().rank(RankTypes.GREEN).xp(8).entityType(BHTPvZEntityTypes.TARGET_ARROW_ZOMBIE::get).zombieModel(() -> TargetArrowZombieModel::new).scale(0.5f).eatCommonSkill(Collections.emptyList()));
	//红刀僵尸
	public static final ZombieType RED_EDGE_ZOMBIE = new BHTPvZZombies("red_edge_zombie", new ZombieFeatures().rank(RankTypes.RED).xp(74).entityType(BHTPvZEntityTypes.RED_EDGE_ZOMBIE::get).zombieModel(() -> RedEdgeZombieModel::new).scale(0.5f).eatCommonSkill(Collections.emptyList()));
	//舞王僵尸
	public static final ZombieType MJ_ZOMBIE = new BHTPvZZombies("mj_zombie", new ZombieFeatures().rank(RankTypes.RED).xp(80).entityType(BHTPvZEntityTypes.MJ_ZOMBIE::get).zombieModel(() -> MJZombieModel::new).scale(0.5f).eatCommonSkill(Collections.emptyList()));
	//伴舞僵尸
	public static final ZombieType DANCER_BACKUP_ZOMBIE = new BHTPvZZombies("dancer_backup_zombie", new ZombieFeatures().rank(RankTypes.WHITE).xp(8).entityType(BHTPvZEntityTypes.DANCER_BACKUP_ZOMBIE::get).zombieModel(() -> DancerBackupModel::new).scale(0.5f).eatCommonSkill(Collections.emptyList()));
	//向日葵僵尸
	public static final ZombieType SUN_FLOWER_ZOMBIE = new BHTPvZZombies("sun_flower_zombie", new ZombieFeatures().rank(RankTypes.GREEN).xp(22).entityType(BHTPvZEntityTypes.SUN_FLOWER_ZOMBIE::get).zombieModel(() -> SunFlowerZombieModel::new).scale(0.5f).eatCommonSkill(Collections.emptyList()));
	//大嘴花僵尸
	public static final ZombieType CHOMPER_ZOMBIE = new BHTPvZZombies("chomper_zombie", new ZombieFeatures().rank(RankTypes.PURPLE).xp(22).entityType(BHTPvZEntityTypes.CHOMPER_ZOMBIE::get).zombieModel(() -> SunFlowerZombieModel::new).scale(0.5f).eatCommonSkill(Collections.emptyList()));

	public static void register() {
		registerZombies(LIST);
	}

	private BHTPvZZombies(String name, ZombieFeatures features) {
		super(name, features);
		LIST.add(this);
	}

	@Override
	public int getSortPriority() {
		return 80;
	}

	@Override
	public String getCategoryName() {
		return "bhtpvz";
	}

	@Override
	public String getModID() {
		return BHTPvZ.MOD_ID;
	}

	@Override
	protected ResourceLocation getEntityResource() {
		return new ResourceLocation(this.getModID(), "textures/entity/zombie/" + this.getCategoryName() + "/" + this + ".png");
	}
}
