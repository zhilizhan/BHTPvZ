package com.zhilizhan.bhtpvz.common.mixin.optional;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.common.entity.plant.toxic.ScaredyShroomEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.zhilizhan.bhtpvz.config.BHTPvZConfig;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = ScaredyShroomEntity.class,remap = false)
public abstract class ScaredyShroomEntityMixin extends PlantShooterEntity {

	public ScaredyShroomEntityMixin(EntityType<? extends PathfinderMob> type, Level worldIn) {
		super(type, worldIn);
	}
	@Shadow public abstract boolean canShoot();

	@Shadow public abstract boolean isScared();

	@Override
	public boolean canBeTargetBy(LivingEntity living) {
		return (!this.isScared() || !BHTPvZConfig.COMMON_CONFIG.EntitySettings.PlantSetting.ScaredyShroomSurrender.get()) && !this.hasMetal();
	}

	@Override
	public IPlantType getPlantType() {
		return PVZPlants.SCAREDY_SHROOM;
	}

}
