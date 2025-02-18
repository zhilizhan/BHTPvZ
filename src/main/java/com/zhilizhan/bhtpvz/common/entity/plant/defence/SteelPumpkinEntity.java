package com.zhilizhan.bhtpvz.common.entity.plant.defence;


import com.hungteen.pvz.api.paz.IPlantEntity;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.PlantInfo;
import com.hungteen.pvz.common.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import com.zhilizhan.bhtpvz.common.util.BHTPVZUtils;
import net.minecraft.entity.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class SteelPumpkinEntity extends PlantDefenderEntity{
	private static final DataParameter<Boolean> IS_SOLID = EntityDataManager.defineId(SteelPumpkinEntity.class, DataSerializers.BOOLEAN);
	public SteelPumpkinEntity(EntityType<? extends CreatureEntity> type, World	 level) {
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

	@Nonnull
	@Override
	public EntitySize getDimensions(@Nonnull Pose poseIn) {
		return EntitySize.scalable(0.95f, 0.8f);
	}

	@Override
	public void normalPlantTick() {
		if (!this.level.isClientSide) {
			if (this.hasMetal()) {
				this.decreaseMetal();
				this.heal(275);
				this.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 1000, 5));
			}
			this.checkInsideBlocks();
			List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox());
			if (!list.isEmpty()) {
				if ( this.random.nextInt(4) == 0) {
					for (Entity entity : list) {
						if (!entity.hasPassenger(this)&&entity instanceof PVZPlantEntity) {
							if (this.getPassengers().isEmpty() && !entity.isPassenger() && entity.getBbWidth() < this.getBbWidth() && entity instanceof PVZPlantEntity) {
								entity.startRiding(this);
								this.hasPlant();
								((PVZPlantEntity)entity).addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 2000, 5));
							}
						} else {
							setSolid(true);
						}
					}
				}
			}
		}
		super.normalPlantTick();
	}

	public void positionRider(@Nonnull Entity entity) {
		if (this.hasPassenger(entity)) {
			float f = 0.0f;
			if (this.getPassengers().size() > 1) {
	   			int i = this.getPassengers().indexOf(entity);
	   			if (i == 0) {
	   				f = 0.2f;
	   			} else {
	   				f = -0.6f;
	   			}
	   			if (entity instanceof PVZPlantEntity) {
	   				f = (float)((double)f + 0.2);
	   			}
	   		}
			Vector3d vec3 = (new Vector3d(f, 0.0, 0.0)).yRot(-this.yRot * 0.017453292f - 1.5707964f);
	   		entity.setPos(this.getX() + vec3.x, this.getY(), this.getZ() + vec3.z);
	   	}
	}

	@Override
	public SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn) {
		return SoundRegister.METAL_HIT.get();
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("is_solid")) {
			this.setSolid(compound.getBoolean("is_solid"));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
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


	public static class SteelPumpkinInfo extends PlantInfo  {
		public static final float NORMAL_PUMPKIN_LIFE = 800.0F;
		public static final float EXTRA_PUMPKIN_LIFE = 100.0F;

		public static final float SUPER_PUMPKIN_LIFE = 1200.0F;

			public SteelPumpkinInfo() {
			}

			public void onSuper(IPlantEntity plantEntity) {
				super.onSuper(plantEntity);
				if(plantEntity instanceof PVZPlantEntity) {
					PVZPlantEntity plant = (PVZPlantEntity) plantEntity;
					BHTPVZUtils.setSteelPumpkin(plant,true);
				}
				plantEntity.setOuterDefenceLife(SUPER_PUMPKIN_LIFE);
			}

			public void placeOn(IPlantEntity plantEntity, int sunCost) {
				super.placeOn(plantEntity, sunCost);
				if(plantEntity instanceof PVZPlantEntity) {
					PVZPlantEntity plant = (PVZPlantEntity) plantEntity;
					BHTPVZUtils.setSteelPumpkin(plant,true);
				}else plantEntity.setPumpkin(true);
				plantEntity.setOuterDefenceLife(NORMAL_PUMPKIN_LIFE+EXTRA_PUMPKIN_LIFE);
			}

			public void onHeal(IPlantEntity plantEntity, float percent) {
				float max = plantEntity.getOuterDefenceLife() > NORMAL_PUMPKIN_LIFE ? SUPER_PUMPKIN_LIFE : NORMAL_PUMPKIN_LIFE;
				plantEntity.setOuterDefenceLife(MathHelper.clamp(plantEntity.getOuterDefenceLife() * (double)(1.0F + percent), 0.0, max));
			}
		}
	@Override
	public IPlantType getPlantType() {
		return BHTPvZPlants.STEEL_PUMPKIN;
	}

}
