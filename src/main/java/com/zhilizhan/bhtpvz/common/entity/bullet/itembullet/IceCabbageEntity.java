package com.zhilizhan.bhtpvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.entity.plant.ice.IceCabbagePultEntity;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class IceCabbageEntity extends PultBulletEntity implements ItemSupplier {

	public IceCabbageEntity(Level level, LivingEntity shooter) {
		super(BHTPvZEntityTypes.ICE_CABBAGE.get(), level, shooter);
	}
	public IceCabbageEntity(EntityType<IceCabbageEntity> iceCabbageEntityEntityType, Level worldIn) {
		super(BHTPvZEntityTypes.ICE_CABBAGE.get(), worldIn);
	}

	@Override
	protected void dealDamage(Entity entity) {
		PVZEntityDamageSource source = BHTPvZEntityDamageSource.iceCabbage(this, this.getThrower());
		if(this.getThrower() instanceof IceCabbagePultEntity) {
			source.addEffect(((IceCabbagePultEntity) this.getThrower()).getFrozenEffect().orElse(null));
			source.addEffect(((IceCabbagePultEntity) this.getThrower()).getColdEffect().orElse(null));
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
