package com.zhilizhan.bhtpvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.entity.plant.ice.IceCabbagePultEntity;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import net.minecraft.entity.*;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class IceCabbageEntity extends PultBulletEntity implements IRendersAsItem {

	public IceCabbageEntity(World level, LivingEntity shooter) {
		super(BHTPvZEntityTypes.ICE_CABBAGE.get(), level, shooter);
	}
	public IceCabbageEntity(EntityType<IceCabbageEntity> iceCabbageEntityEntityType, World worldIn) {
		super(BHTPvZEntityTypes.ICE_CABBAGE.get(), worldIn);
	}

	@Override
	protected void dealDamage(Entity entity) {
		PVZEntityDamageSource source = BHTPvZEntityDamageSource.iceCabbage(this, this.getThrower());
		if(this.getThrower() instanceof IceCabbagePultEntity) {
			source.addEffect(((IceCabbagePultEntity) this.getThrower()).getFrozenEffect().orElse(null));
			source.addEffect(((IceCabbagePultEntity) this.getThrower()).getColdEffect().orElse(null));
		}
		if(entity instanceof LivingEntity)entity.hurt(source, this.attackDamage);
	}

	@Override
	public EntitySize getDimensions(Pose pose) {
		return EntitySize.scalable(0.6f, 0.6f);
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(BHTPvZItems.ICE_CABBAGE.get());
	}
}
