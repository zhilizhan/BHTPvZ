package com.zhilizhan.bhtpvz.common.entity.plant.ice;

import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.IceCabbageEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;

import com.hungteen.pvz.api.interfaces.IIceEffect;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.plant.arma.CabbagePultEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.potion.EffectRegister;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class IceCabbagePultEntity extends CabbagePultEntity implements IIceEffect {
	private static final int FROZEN_TICK = 35;

	public IceCabbagePultEntity(EntityType<? extends PathfinderMob> type, Level level) {
		super(type, level);
	}

	@Override
	protected PultBulletEntity createBullet() {
		return new IceCabbageEntity(level, this);
	}

	@Override
	public float getAttackDamage() {
		return this.getSkillValue(SkillTypes.MORE_CABBAGE_DAMAGE);
	}

	public int getColdDuration() {
		return 60;
	}

	@Override
	public Optional<MobEffectInstance> getColdEffect() {
		return Optional.of(new MobEffectInstance(EffectRegister.COLD_EFFECT.get(), FROZEN_TICK + this.getColdDuration(), 2, false, false));
	}

	@Override
	public Optional<MobEffectInstance> getFrozenEffect() {
		return Optional.of(new MobEffectInstance(EffectRegister.FROZEN_EFFECT.get(), FROZEN_TICK, 1, false, false));
	}

	@Override
	public float getSuperDamage() {
		return this.getAttackDamage() + 20;
	}

	@Override
	public EntityDimensions getDimensions(Pose poseIn) {
		return EntityDimensions.scalable(0.8f, 1.0f);
	}

	@Override
	public IPlantType getPlantType() {
		return BHTPvZPlants.ICE_CABBAGE_PULT;
	}
}
