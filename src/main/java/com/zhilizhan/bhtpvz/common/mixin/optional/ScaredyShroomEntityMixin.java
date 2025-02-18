package com.zhilizhan.bhtpvz.common.mixin.optional;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.common.entity.plant.toxic.ScaredyShroomEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.config.BHTPvZConfig;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;
import java.util.stream.Collectors;

@Mixin(value = ScaredyShroomEntity.class,remap = false)
public abstract class ScaredyShroomEntityMixin extends PlantShooterEntity {

	@Shadow public abstract boolean isScared();

	public ScaredyShroomEntityMixin(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}


	@Override
	public boolean canBeTargetBy(LivingEntity living) {
		return (!this.isScared() || this.getNearbyPlant().isEmpty() || !BHTPvZConfig.COMMON_CONFIG.EntitySettings.PlantSetting.ScaredyShroomSurrender.get()) && !this.hasMetal();
	}
	@Unique
	private List<LivingEntity> getNearbyPlant() {
		float range = 10.0F;
		List<LivingEntity> originalList = EntityUtil.getFriendlyLivings(this, EntityUtil.getEntityAABB(this, range, range));

		List<LivingEntity> filteredList = originalList.stream()
				.filter(entity -> entity instanceof PVZPlantEntity && !(entity instanceof ScaredyShroomEntity && ((ScaredyShroomEntity)entity).isScared())&&entity!=this)
				.collect(Collectors.toList());
		return filteredList;
	}
}
