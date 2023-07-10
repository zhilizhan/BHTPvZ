package com.zhilizhan.bhtpvz.common.entity.plant.defence;


import com.hungteen.pvz.api.paz.IPlantEntity;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.PlantInfo;
import com.hungteen.pvz.common.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SteelPumpkinEntity extends PlantDefenderEntity{
	private static final EntityDataAccessor<Boolean> IS_SOLID = SynchedEntityData.defineId(SteelPumpkinEntity.class, EntityDataSerializers.BOOLEAN);

	public SteelPumpkinEntity(EntityType<? extends PathfinderMob> type, Level level) {
		super(type, level);
		this.canCollideWithPlant = false;
		this.isImmuneToWeak =true;
	}

	@Override
	public float getLife() {
		return 500;
	}

	@Override
	public float getSuperLife() {
		return 800;
	}

	@Override
	public EntityDimensions getDimensions(Pose poseIn) {
		return EntityDimensions.scalable(1.1f, 1.4f);
	}



	@Override
	public void normalPlantTick() {
		if (!this.level.isClientSide) {
			if (this.hasMetal()) {
				this.decreaseMetal();
				this.heal(275);
				this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1000, 5));
			}
			this.checkInsideBlocks();
			List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox());
			if (!list.isEmpty()) {
				if ( this.random.nextInt(4) == 0) {
					for (Entity entity : list) {
						if (!entity.hasPassenger(this)) {
							if (this.getPassengers().size() < 1 && !entity.isPassenger() && entity.getBbWidth() < this.getBbWidth() && entity instanceof PVZPlantEntity) {
								entity.startRiding(this);
								this.hasPlant();
								((PVZPlantEntity) entity).addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2000, 5));
							}
						} else {
							setSolid(true);
						}
					}
				}
			}
		}
		super.aiStep();
	}

	public void positionRider(Entity p_184232_1_) {
		if (this.hasPassenger(p_184232_1_)) {
			float f = 0.0f;
			if (this.getPassengers().size() > 1) {
	   			int i = this.getPassengers().indexOf(p_184232_1_);
	   			if (i == 0) {
	   				f = 0.2f;
	   			} else {
	   				f = -0.6f;
	   			}
	   			if (p_184232_1_ instanceof PVZPlantEntity) {
	   				f = (float)((double)f + 0.2);
	   			}
	   		}
			Vec3 vec3 = (new Vec3(f, 0.0, 0.0)).yRot(-this.yRot * 0.017453292f - 1.5707964f);
	   		p_184232_1_.setPos(this.getX() + vec3.x, this.getY(), this.getZ() + vec3.z);
	   	}
	}

	@Override
	public SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundRegister.METAL_HIT.get();
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("is_solid")) {
			this.setSolid(compound.getBoolean("is_solid"));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("is_solid", this.isSolid());
	}

	public void setSolid(boolean has) {
		this.entityData.set(IS_SOLID, has);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(IS_SOLID, true);
	}

	public void hasPlant() {
		if (!level.isClientSide) {
			SoundRegister.METAL_HIT.get();
		}
		this.setSolid(false);

	}

	public boolean isSolid() {
		return this.entityData.get(IS_SOLID);
	}
	public PVZPlantEntity plantEntity;




	public static class SteelPumpkinInfo extends PlantInfo  {
			private static final float NORMAL_PUMPKIN_LIFE = 400.0F;
			private static final float SUPER_PUMPKIN_LIFE = 800.0F;

			public SteelPumpkinInfo() {
			}

			public void onSuper(IPlantEntity plantEntity) {
				super.onSuper(plantEntity);
				plantEntity.setPumpkin(true);
				plantEntity.setOuterDefenceLife(1200.0);
			}

			public void placeOn(IPlantEntity plantEntity, int sunCost) {
				super.placeOn(plantEntity, sunCost);
				plantEntity.setPumpkin(true);
				plantEntity.setOuterDefenceLife(900.0);
			}

			public void onHeal(IPlantEntity plantEntity, float percent) {
				float max = plantEntity.getOuterDefenceLife() > 900 ? 1200F : 900F;
				plantEntity.setOuterDefenceLife(Mth.clamp(plantEntity.getOuterDefenceLife() * (double)(1.0F + percent), 0.0, (double)max));
			}
		}
	@Override
	public IPlantType getPlantType() {
		return BHTPvZPlants.STEEL_PUMPKIN;
	}

}
