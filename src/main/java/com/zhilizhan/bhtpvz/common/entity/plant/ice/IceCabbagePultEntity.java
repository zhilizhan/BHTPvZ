package com.zhilizhan.bhtpvz.common.entity.plant.ice;

import com.hungteen.pvz.api.interfaces.IIceEffect;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.plant.arma.CabbagePultEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.IceCabbageEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

import java.util.Optional;

public class IceCabbagePultEntity extends CabbagePultEntity implements IIceEffect{
	private static final int FROZEN_TICK = 15;

	public IceCabbagePultEntity(EntityType<? extends CreatureEntity> type, World level) {
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
		return 50;
	}

	@Override
	public Optional<EffectInstance> getColdEffect() {
		return Optional.of(new EffectInstance(EffectRegister.COLD_EFFECT.get(), FROZEN_TICK + this.getColdDuration(), 2, false, false));
	}

	@Override
	public Optional<EffectInstance> getFrozenEffect() {
		return Optional.of(new EffectInstance(EffectRegister.FROZEN_EFFECT.get(), FROZEN_TICK, 2, false, false));
	}

	@Override
	public IPlantType getPlantType() {
		return BHTPvZPlants.ICE_CABBAGE_PULT;
	}
}
