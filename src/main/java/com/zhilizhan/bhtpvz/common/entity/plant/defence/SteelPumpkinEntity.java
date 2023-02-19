package com.zhilizhan.bhtpvz.common.entity.plant.defence;


import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.zhilizhan.bhtpvz.common.impl.plant.AddPlants;
import net.minecraft.entity.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.List;


public class SteelPumpkinEntity extends PlantDefenderEntity {


	private static final DataParameter<Boolean> IS_SOLID = EntityDataManager.defineId(SteelPumpkinEntity.class, DataSerializers.BOOLEAN);

	public SteelPumpkinEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canCollideWithPlant = false;
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
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(1.1f, 1.4f);
	}

	@Override
	public IPlantType getPlantType() {
		return AddPlants.STEEL_PUMPKIN;
	}


		@Override
		public void aiStep() {

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
						for (int j = 0; j < list.size(); ++j) {
							Entity entity = list.get(j);
							if (!entity.hasPassenger(this)) {
								if ( this.getPassengers().size() < 1 && !entity.isPassenger() && entity.getBbWidth() < this.getBbWidth() && entity instanceof PVZPlantEntity  ) {
									entity.startRiding(this);
									this.hasPlant();
									((PVZPlantEntity) entity).addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 2000, 5));
								}
							}else{setSolid(true);}
						}
					}

				}

			}
			super.aiStep();
		}

	   public void positionRider(Entity p_184232_1_) {
	   	if (this.hasPassenger(p_184232_1_)) {
	   		float f = 0.0F;
	   		if (this.getPassengers().size() > 1) {
	   			int i = this.getPassengers().indexOf(p_184232_1_);
	   			if (i == 0) {
	   				f = 0.2F;
	   			} else {
	   				f = -0.6F;
	   			}

	   			if (p_184232_1_ instanceof PVZPlantEntity) {
	   				f = (float)((double)f + 0.2);
	   			}
	   		}

	   		Vector3d vector3d = (new Vector3d(f, 0.0, 0.0)).yRot(-this.yRot * 0.017453292F - 1.5707964F);
	   		p_184232_1_.setPos(this.getX() + vector3d.x, this.getY(), this.getZ() + vector3d.z);

	   	}

	   }

	@Override
	public SoundEvent getHurtSound(DamageSource damageSourceIn) {
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
}

