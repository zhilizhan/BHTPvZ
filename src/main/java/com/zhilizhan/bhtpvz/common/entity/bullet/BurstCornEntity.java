package com.zhilizhan.bhtpvz.common.entity.bullet;

import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.util.Random;

public class BurstCornEntity extends CornEntity {

    protected Random random;
    private Entity attackEntity = null;

    public BurstCornEntity(EntityType<?> type, World worldIn) {
        super(type, worldIn);
    }
    public BurstCornEntity(World worldIn, LivingEntity shooter) {
        super(BHTPvZEntityTypes.BURST_CORN.get(), worldIn);
    }

    protected void dealDamage(Entity target) {
            PVZEntityDamageSource source = BHTPvZEntityDamageSource.burst_corn(this, this.getThrower());
            target.setSecondsOnFire(3);
            target.hurt(source, this.getAttackDamage());
          if(target instanceof LivingEntity && ((LivingEntity)target).hasEffect(EffectRegister.BUTTER_EFFECT.get())){
              setSecondsOnFire(3);
          }
        this.attackEntity = target;
        this.dealSplashDamage();
    }

    protected void onHitBlock() {
        this.dealSplashDamage();
    }

    public void dealSplashDamage() {
        float range = 3.0F;
        EntityUtil.getTargetableEntities(this.getOwnerOrSelf(), EntityUtil.getEntityAABB(this, range, range)).forEach((entity) -> {
            if (!entity.is(this.attackEntity) && this.shouldHit(entity)) {
                PVZEntityDamageSource source = BHTPvZEntityDamageSource.burst_corn(this, this.getThrower());
                source.addEffect(new EffectInstance( EffectRegister.BUTTER_EFFECT.get(), 60, 1, false, false));
                    entity.hurt(source, this.getAttackDamage());
            }

        });
        for(int i = 0; i < 10; ++i) {
            EntityUtil.spawnParticle(this,0);
        }

        EntityUtil.playSound(this, SoundEvents.GENERIC_EXPLODE);
    }


}

