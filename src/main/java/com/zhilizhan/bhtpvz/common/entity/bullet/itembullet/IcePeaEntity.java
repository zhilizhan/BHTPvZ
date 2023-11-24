package com.zhilizhan.bhtpvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.common.entity.bullet.AbstractShootBulletEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Optional;
@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class IcePeaEntity extends AbstractShootBulletEntity implements ItemSupplier {
    public IcePeaEntity(EntityType<?> type, Level worldIn) {
        super(type, worldIn);
    }

    public IcePeaEntity(Level level, LivingEntity entity) {
        super(BHTPvZEntityTypes.ICE_PEA.get(), level, entity);
    }

    @Override
    protected int getMaxLiveTick() {
        return 40;
    }
    @Override
    protected void onImpact(HitResult hitResult) {
        boolean flag = false;
        if (hitResult.getType() == net.minecraft.world.phys.HitResult.Type.ENTITY) {
            Entity target = ((EntityHitResult)hitResult).getEntity();
            if (this.shouldHit(target)) {
                target.invulnerableTime = 0;
                this.dealPeaDamage(target);
                flag = true;
            }
        }

        this.level.broadcastEntityEvent(this, (byte)3);
        if (flag || !this.checkLive(hitResult)) {
            this.remove();
        }
    }

    private void dealPeaDamage(Entity target) {
        float damage = this.getAttackDamage();
            PVZEntityDamageSource source = BHTPvZEntityDamageSource.icePea(this, this.getThrower());
            source.addEffect( this.getFrozenEffect().get());
            target.hurt(source, damage);
    }

    public Optional<MobEffectInstance> getFrozenEffect() {
        return Optional.of(new MobEffectInstance(EffectRegister.FROZEN_EFFECT.get(), 15, 1, false, false));
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.scalable(0.4f, 0.4f);
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(BHTPvZItems.ICE_PEA.get());
    }
}
