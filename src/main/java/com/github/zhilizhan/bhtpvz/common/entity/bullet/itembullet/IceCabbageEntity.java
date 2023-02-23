package com.github.zhilizhan.bhtpvz.common.entity.bullet.itembullet;

import com.github.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.github.zhilizhan.bhtpvz.common.entity.plant.ice.IceCabbagePultEntity;
import com.github.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import com.github.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;

import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class IceCabbageEntity extends PultBulletEntity implements ItemSupplier {
	public IceCabbageEntity(EntityType<?> type, Level level) {
		super(type, level);
	}

	public IceCabbageEntity(Level level, LivingEntity shooter) {
		super(BHTPvZEntityTypes.ICE_CABBAGE.get(), level, shooter);
	}

	@Override
	protected void dealDamage(Entity entity) {
		PVZEntityDamageSource source = BHTPvZEntityDamageSource.iceCabbage(this, this.getThrower());
		if(this.getThrower() instanceof IceCabbagePultEntity) {
			source.addEffect(((IceCabbagePultEntity) this.getThrower()).getFrozenEffect().get());
			source.addEffect(((IceCabbagePultEntity) this.getThrower()).getColdEffect().get());
		}
		entity.hurt(source, this.attackDamage);
	}

	@Override
	public EntityDimensions getDimensions(Pose pose) {
		return EntityDimensions.scalable(0.6f, 0.6f);
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(BHTPvZItems.ICE_CABBAGE.get());
	}
}
