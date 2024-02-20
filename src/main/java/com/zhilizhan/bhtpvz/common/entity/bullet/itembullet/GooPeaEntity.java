package com.zhilizhan.bhtpvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.effect.BHTPvZMobEffects;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Optional;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class GooPeaEntity extends BHTPvZPeaEntity{
    public GooPeaEntity(EntityType<?> type, Level worldIn) {
        super(type, worldIn);
    }
    public GooPeaEntity(Level worldIn, LivingEntity shooter) {
        super(BHTPvZEntityTypes.GOO_PEA.get(), worldIn, shooter);
    }

    protected void dealPeaDamage(Entity target) {
        float damage = this.getAttackDamage();
        PVZEntityDamageSource source = BHTPvZEntityDamageSource.gooPea(this, this.getThrower());
        source.addEffect(this.getPoisonEffect().orElse(null));
        target.hurt(source, damage);
    }
    public Optional<MobEffectInstance> getPoisonEffect() {
        return Optional.of(new MobEffectInstance(BHTPvZMobEffects.GOO_POISON.get(), 100, 0, false, false));
    }
    @Override
    public ItemStack getItem() {
        return  new ItemStack(BHTPvZItems.GOO_PEA.get());
    }
}
